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
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.domain.utils.AESUtil;
import com.zmax.mag.service.my.*;
import com.zmax.mag.service.spec.SpecMagPayService;
import com.zmax.mag.service.spec.SpecRestService;
import com.zmax.mag.service.spec.SpecUserService;
import com.zmax.mag.web.controller.page.base.BaseController;
import com.zmax.mag.web.controller.page.spec.SpecPubController;
import com.zmax.mag.web.controller.restful.RestSpecUtils;
import com.zmax.mag.web.controller.restful.RestUtils;
import com.zmax.mag.web.controller.restful.entity.ClientInfo;
import com.zmax.mag.web.controller.restful.entity.RestPage;

/**
 * RestFul控制 自定义功能
 * 
 *
 */
@RestController
@RequestMapping("/rest")
public class SpecCartOrderrRestController extends BaseController {
	/** 日志实例 */
	private static final Logger logger = Logger
			.getLogger(SpecCartOrderrRestController.class);

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
	SpecMagPayService specMagPayService;

	/**
	 * 提问题去支付
	 * 
	 * @param request
	 * @param response
	 * @param clientInfo
	 * @throws BoException
	 * @throws Exception
	 */
	@RequestMapping(value = "/OrderrQuestionPay", method = RequestMethod.POST)
	@ResponseBody
	public Json OrderrQuestionPay(HttpServletRequest request,
			HttpServletResponse response, ClientInfo clientInfo,
			Integer questionId) throws BoException, Exception {
		// 登录检查
		logger.debug("questionId=" + questionId);
		User tokenUser = restUtils.checkAccessToken(clientInfo, null,
				new User(), "get");
		Question question = questionService.get(null, questionId);
		if (question == null) {
			throw new BoException("该问题不存在！");
		}
		/*
		 * 如果会员的积分够的，就直接扣积分，如果会员的积分不够就去微信支付
		 */
		Member member = memberService.get(null, tokenUser.getId());
		if(member!=null)
		{
			if(member.getScore()!=null&&member.getScore()>=question.getPrice()*10){
				return specMagPayService.updateMemberQuestionCashPay(member,question);
			}
		}
		return specMagPayService.saveOrderrQuestionPay(request, tokenUser,
				clientInfo, question);
	}

	/**
	 * 发布抢答去支付
	 * 
	 * @param request
	 * @param response
	 * @param clientInfo
	 * @throws BoException
	 * @throws Exception
	 */
	@RequestMapping(value = "/OrderrQuickPay", method = RequestMethod.POST)
	@ResponseBody
	public Json OrderrQuickPay(HttpServletRequest request,
			HttpServletResponse response, ClientInfo clientInfo, Integer quickId)
			throws BoException, Exception {
		// 登录检查
		logger.debug("quickId=" + quickId);
		User tokenUser = restUtils.checkAccessToken(clientInfo, null,
				new User(), "get");
		Quick quick = quickService.get(null, quickId);
		if (quick == null) {
			throw new BoException("该抢答不存在！");
		}
		Member member =memberService.get(null, tokenUser.getId());
		if(member!=null)
		{
			if(member.getScore()!=null&&member.getScore()>=quick.getPrice())
			{
				return specMagPayService.updateMemberQuickCashPay(member,quick);
			}
		}
		return specMagPayService.saveOrderrQuickPay(request, tokenUser,
				clientInfo, quick);
	}

	/**
	 * 问题观看会员去支付
	 * 
	 * @param request
	 * @param response
	 * @param clientInfo
	 * @throws BoException
	 * @throws Exception
	 */
	@RequestMapping(value = "/OrderrQuestionviewPay", method = RequestMethod.POST)
	@ResponseBody
	public Json OrderrQuestionviewPay(HttpServletRequest request,
			HttpServletResponse response, ClientInfo clientInfo,
			Integer questionId) throws BoException, Exception {
		// 登录检查
		logger.debug("questionId=" + questionId + "clientInfo.Openid="
				+ clientInfo.getOpenid());
		User tokenUser = restUtils.checkAccessToken(clientInfo, null,
				new User(), "get");
		Question question = questionService.get(null, questionId);
		if (question == null) {
			throw new BoException("该问题不存在！");
		}
		Member member = memberService.get(null, tokenUser.getId());
		if (member != null) {
			if (member.getScore() != null
					&& member.getScore() >= question.getViewprice()) {// 会员的余额是分。问题观看费为元。所以得相乘
				return specMagPayService.updateMemberCashPay(member, question);
			}
		}
		return specMagPayService.saveOrderrQuestionviewPay(request, tokenUser,
				clientInfo, question);
	}

	/**
	 * 抢答观看会员去支付
	 * 
	 * @param request
	 * @param response
	 * @param clientInfo
	 * @throws BoException
	 * @throws Exception
	 */
	@RequestMapping(value = "/OrderrQuickviewPay", method = RequestMethod.POST)
	@ResponseBody
	public Json OrderrQuickviewPay(HttpServletRequest request,
			HttpServletResponse response, ClientInfo clientInfo, Integer quickId)
			throws BoException, Exception {
		// 登录检查
		logger.debug("quickId=" + quickId);
		User tokenUser = restUtils.checkAccessToken(clientInfo, null,
				new User(), "get");
		Quick quick = quickService.get(null, quickId);
		if (quick == null) {
			throw new BoException("该抢答不存在！");
		}
		Member member = memberService.get(null, tokenUser.getId());
		if (member != null) {
			if (member.getScore() != null
					&& member.getScore() >= quick.getViewprice()) {// 会员的余额是分，悬赏观看费为元，所以相乘
				return specMagPayService.updateMemberCashPay(member, quick);
			}
		}
		return specMagPayService.saveOrderrQuickviewPay(request, tokenUser,
				clientInfo, quick);
	}
}
