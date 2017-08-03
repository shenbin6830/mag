/*
 * zmax 
 * 
 */

package com.zmax.mag.web.controller.restful.spec;

import groovy.transform.Undefined;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.zmax.common.conf.AttrStatic;
import com.zmax.common.exception.BoException;
import com.zmax.common.exception.NeedLoginException;
import com.zmax.common.exception.RightException;
import com.zmax.common.utils.easyui.Json;
import com.zmax.common.utils.string.Md5PwdEncoder;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wxa.WxConfig;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.domain.utils.AESUtil;
import com.zmax.mag.service.my.*;
import com.zmax.mag.service.spec.*;
import com.zmax.mag.service.utils.MyUtils;
import com.zmax.mag.web.controller.page.base.BaseController;
import com.zmax.mag.web.controller.page.spec.SpecPubController;
import com.zmax.mag.web.controller.restful.RestSpecUtils;
import com.zmax.mag.web.controller.restful.RestUtils;
import com.zmax.mag.web.controller.restful.entity.ClientInfo;
import com.zmax.mag.web.controller.restful.entity.RestPage;



/**
 * RestFul控制 
 * 自定义功能
 * 
 *
 */
@RestController
@RequestMapping("/rest")
public class SpecRest1Controller extends BaseController{
	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(SpecRest1Controller.class);

	@Autowired
	UserService userService;
	@Autowired
	WebsetService websetService;	

	@Autowired
	RestUtils restUtils;
	@Autowired
	RestSpecUtils restSpecUtils;

	@Autowired
	SpecPubController specPubController;

	@Autowired
	SpecUserService specUserService;
	@Autowired
	MemberService memberService;
	@Autowired
	ImageCaptchaService imageCaptchaService;
	@Autowired
	QuickTxtService quickTxtService;
	@Autowired
	QuickService quickService;
	@Autowired
	QuestionService questionService;
	@Autowired
	SpecRestService specRestService;
	@Autowired
	SpecWxService specWxService;
	/**
	 * 会员签到
	 * @param request
	 * @param response
	 * @param clientInfo
	 * @throws BoException
	 * @throws Exception
	 */
	 @RequestMapping(value = "/memberSign", method = RequestMethod.POST)
	@ResponseBody
	public void memberSign(HttpServletRequest request,HttpServletResponse response,ClientInfo clientInfo)throws BoException, Exception{
		User tokenUser = restUtils.checkAccessToken(clientInfo, null, new User(), "get");
		Member member=memberService.get(null, tokenUser.getId());
		if(member==null){
			throw new BoException("您不是会员");
		}
		specRestService.saveMemberSign(member);
	}
	/**
	 * 去抢答
	 * @param request
	 * @param response
	 * @param clientInfo
	 * @throws BoException
	 * @throws Exception
	 */
	@RequestMapping(value = "/findQuickListForAnswer", method = RequestMethod.POST)
	@ResponseBody
	public List<Quick>  findQuickListForAnswer(HttpServletRequest request,HttpServletResponse response,ClientInfo clientInfo)throws BoException, Exception {
		User tokenUser = restUtils.checkAccessToken(clientInfo, null, new User(), "get");
		List<Quick> listQuicks=new ArrayList<Quick>();
		List<QuickTxt> listquickTxts=quickTxtService.listfind(null, "memberAn=?0", null, new Object[]{tokenUser.getId()});		
		if(listquickTxts.size()>0){
			String aString="";
			for (int i = 0; i < listquickTxts.size(); i++) {
				aString+=listquickTxts.get(i).getQuickId()+",";
			}
			aString=aString.substring(0, aString.length()-1);
			listQuicks=quickService.listfind(null, "id not in ("+aString+")", null, null);
		}else{
			listQuicks=quickService.listAll(null);
		}
		for (Quick quick : listQuicks) {
			quickService.addObj(null, quick);
		}
		return listQuicks;
	}
	/**
	 * 会员查看问题，等待付款
	 * @param request
	 * @param response
	 * @param clientInfo
	 * @param questionId
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	@RequestMapping(value = "/readyPayForQuestion", method = RequestMethod.POST)
	@ResponseBody
	public void  readyPayForQuestion(HttpServletRequest request,HttpServletResponse response,ClientInfo clientInfo,Integer questionId)throws BoException, Exception {
		User tokenUser = restUtils.checkAccessToken(clientInfo, null, new User(), "get");
		if(questionId==null){
			throw new BoException("传入的id为空！");
		}
		Question question=questionService.get(null, questionId);
		if(question==null){
			throw new BoException("未找到相应的问题！");
		}
		if(question.getMemberQu().intValue()==tokenUser.getId()){
			throw new BoException("此问题是自己发布的，直接在会员中心查看即可！");
		}
		specRestService.saveQuestionLinkMemberView(tokenUser, question);
	}
	/**
	 * 会员查看抢答，看答案
	 * @param request
	 * @param response
	 * @param clientInfo
	 * @param quickId
	 * @throws BoException
	 * @throws Exception
	 */
	@RequestMapping(value = "/readyPayForQuick", method = RequestMethod.POST)
	@ResponseBody
	public void  readyPayForQuick(HttpServletRequest request,HttpServletResponse response,ClientInfo clientInfo,Integer quickId)throws BoException, Exception {
		User tokenUser = restUtils.checkAccessToken(clientInfo, null, new User(), "get");
		if(quickId==null){
			throw new BoException("传入的id为空！");
		}
		Quick quick=quickService.get(null, quickId);
		if(quick==null){
			throw new BoException("未找到相应的问题！");
		}
		if(quick.getMemberQu().intValue()==tokenUser.getId()){
			throw new BoException("此抢答是自己发布的，直接在会员中心查看即可！");
		}
		specRestService.saveQuickLinkMemberView(tokenUser, quick);
	}
	/**
	 * 获取微信配置
	 * @param request
	 * @param clientInfo
	 * @param surl
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	@RequestMapping(value="/wxConfig")
	@ResponseBody
	public WxConfig wxConfig(HttpServletRequest request,ClientInfo clientInfo,String surl) throws BoException, Exception{
		if(StringUtils.isBlank(surl))
			surl=MyUtils.wxUrl(request);		
		WxConfig wxConfig=specWxService.takeWxConfig(Conf.BIZID,surl);
		logger.debug("获取WxConfig成功"+wxConfig.toString());
		return wxConfig;
	}
}
