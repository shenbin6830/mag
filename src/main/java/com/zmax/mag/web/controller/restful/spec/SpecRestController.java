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
import com.zmax.mag.service.spec.SpecAliSmsService;
import com.zmax.mag.service.spec.SpecUserService;
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
public class SpecRestController extends BaseController{
	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(SpecRestController.class);

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
	SpecAliSmsService specAliSmsService;
	/**
	 * 用户请求验证码 不需要通过图形校验码
	 * @param request
	 * @param mobile 手机号
	 * @throws Exception 
	 */
	@RequestMapping(value = "/requestCode", method = RequestMethod.GET)
	public Json requestCode(HttpServletRequest request, String mobile) throws BoException, Exception {
		specAliSmsService.requestCode(mobile);
		return new Json(true);
	}
	/**
	 * 允许登录的角色
	 * @param userform 页面提交用户
	 * @param userdb 数据库用户
	 * @return
	 */
	private boolean canLogin(User userform,User userdb){
		if(userform==null || userdb==null)
			return false;
		if(userform.getRoleId()==null || userdb.getRoleId()==null)
			return false;
		if(userform.getRoleId().intValue()==userdb.getRoleId().intValue()){
			if(userform.getRoleId().intValue()==AttrStatic.ROLE_MEMBER 
					)
				return true;
		}			
		return false;
	}
	/**
	 * 允许注册的角色
	 * @param userform 页面提交用户
	 * @return
	 */
	private boolean canReg(User userform){
		if(userform==null)
			return false;
		if(userform.getRoleId()==null)
			return false;
		if(userform.getRoleId().intValue()==AttrStatic.ROLE_MEMBER 
				//|| userform.getRoleId().intValue()==AttrStatic.ROLE_DOCTOR
				)
			return true;
		return false;
	}


	/**
	 * 会员通过账号+密码+角色方式进行登录的检查
	 * @param request
	 * @param response
	 * @param model
	 * @param clientInfo
	 * @param user  必须项包括：username password roleid
	 * @param isauto 1自动登录，这时候传来的密码是md5后的 0非自动登录
	 * @param iscreate 如果空用户是否创建，0不创建(检查时检查账密)，1如果空创建用户,不空返回用户(检查时只查账号即可)，2如果空创建用户,不空则抛错(检查时只查账号即可) 
	 * @param nowURL
	 * @return
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws RightException
	 * @throws Exception
	 */
	@RequestMapping(value = "/dologin", method = RequestMethod.GET)
	public User dologin(HttpServletRequest request,HttpServletResponse response,ModelMap model,ClientInfo clientInfo,User user,Integer iscreate,String nowURL)throws BoException, NeedLoginException, RightException, Exception{
		if(user==null){
			throw new BoException("账密为空");
		}
		//必须项包括：username password roleid
		if(StringUtils.isBlank(user.getUsername())
				||StringUtils.isBlank(user.getPassword())
				||null==user.getRoleId()
				){
			throw new BoException("账密为空");
		}
		//返回的jr.obj=ret,ret.put("user",dbuser);
		Map<String,Object> ret=checklogin(request, response, model, clientInfo, user, iscreate);
		
		if(ret==null){
			throw new BoException("账号密码错误!");
		}
		User retuser=(User)(ret.get("user"));
		logger.debug(retuser+"----retuser");
		if(retuser==null){
			throw new BoException("账号密码错误!!");
		}
		//检查下角色是否允许登录
		if(!canLogin(user, retuser)){
			throw new BoException("账号密码错误!!!");
		}
		logger.debug("去dologin进wxr关系");
		//去看看微信user跟wxr的关系
		specUserService.wxouserAndWxr(retuser, clientInfo);
		//放下Token
		putToken(retuser);
		return retuser;
		
	}
	/**
	 * 会员通过短信验证登录，如果用户不存在，则注册一个，密码如果是空则为111111
	 * @param request
	 * @param response
	 * @param model
	 * @param clientInfo
	 * @param user User 必须：username手机号,roleId角色;非必须：password
	 * @param code 验证码
	 * @param isauto
	 * @param nowURL
	 * @param password
	 * @return
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws RightException
	 * @throws Exception
	 */
	@RequestMapping(value = "/dologinbysms", method = RequestMethod.GET)
	public User dologinbysms(HttpServletRequest request,HttpServletResponse response,ModelMap model,ClientInfo clientInfo,User user,String code,String nowURL)throws BoException, NeedLoginException, RightException, Exception{
		int iscreate=1;
		if(user==null){
			throw new BoException("手机号为空!");
		}
		//必须项包括：username roleid
		if(StringUtils.isBlank(user.getUsername())
				||null==user.getRoleId()
				){
			throw new BoException("手机号为空!!");
		}	
		//检查下角色是否允许登录
		if(!canLogin(user,user)){
			throw new BoException("用户不允许使用此方式登录!!!");
		}
		specAliSmsService.checkCode(user.getUsername(), code);

		if(StringUtils.isBlank(user.getNickname()))
			user.setNickname(user.getUsername());
		user.setOpenid(clientInfo.getOpenid());
		user.setStatus(1);

		//返回的jr.obj=ret,ret.put("user",dbuser);
		Map<String,Object> ret=checklogin(request, response, model, clientInfo, user, iscreate);
		if(ret==null){
			throw new BoException("账号密码错误");
		}
		User retuser=(User)(ret.get("user"));
		if(retuser==null||(retuser.getRoleId().intValue()!=AttrStatic.ROLE_MEMBER )){
			throw new BoException("账号密码错误");
		}
		logger.debug("去进wxr关系");
		//去看看微信user跟wxr的关系
		specUserService.wxouserAndWxr(retuser, clientInfo);
		//放下Token
		putToken(retuser);
		return retuser;
	}
	/**
	 * 
	 *  
	 *  ①验证登录 主要是验证码和客户端的验证登录
	 *  ②修改密码
	 *  注意 ：防止多角色用户修改不对应角色客服端的账号密码
	 * @param request
	 * @param response
	 * @param model
	 * @param clientInfo
	 * @param user
	 * @param code
	 * @param isauto
	 * @param nowURL
	 * @return
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws RightException
	 * @throws Exception
	 */
	@RequestMapping(value = "/changePwd", method = RequestMethod.GET)
	@ResponseBody
	public User changePwd(HttpServletRequest request,HttpServletResponse response,ModelMap model,ClientInfo clientInfo,User user,String code,String nowURL)throws BoException, NeedLoginException, RightException, Exception{
		if(user==null){
			throw new BoException("手机号为空!");
		}
		//必须项包括：username roleid
		if(StringUtils.isBlank(user.getUsername())
				||null==user.getRoleId()
				){
			throw new BoException("手机号为空!!");
		}	
		User userDb=(User)userService.getFirst(null, "username=?0",null, new Object[]{user.getUsername()});
		if(userDb==null){
			throw new BoException("用户不存在!!");
		}
		String PwdMd5=Md5PwdEncoder.encodePassword(user.getPassword());
		//短信验证
		//specAliSmsService.checkCode(user.getUsername(), code);
		//检查下角色是否允许登录
		if(!canLogin(user,userDb)){
			throw new BoException("用户不允许使用此方式登录!!!");
		}
		if(StringUtils.isBlank(user.getNickname()))
			user.setNickname(user.getUsername());
		user.setOpenid(clientInfo.getOpenid());
		user.setStatus(1);

		//返回的jr.obj=ret,ret.put("user",dbuser);
		Map<String,Object> ret=checklogin(request, response, model, clientInfo, user, 1);
		User retuser=(User)(ret.get("user"));
		retuser.setPassword(user.getPassword());
		userService.execute(null, "update User set password=?0 where username=?1", new Object[]{PwdMd5,user.getUsername()});
		//放下Token
		putToken(retuser);
		return retuser;
	}
	/**
	 * 重置密码为111111
	 * @param request
	 * @param response
	 * @param model
	 * @param clientInfo
	 * @param mobile
	 * @param code
	 * @param isauto
	 * @param nowURL
	 * @return
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws RightException
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetPwd", method = RequestMethod.GET)
	public User resetPwd(HttpServletRequest request,HttpServletResponse response,ModelMap model,ClientInfo clientInfo,String mobile,String code,String nowURL)throws BoException, NeedLoginException, RightException, Exception{
		//specAliSmsService.checkCode(mobile, code);
		String defultPwd="111111";//数据库存的是Md5PwdEncoder.encodePassword(defultPwd)
		String defaultPwdMd5=Md5PwdEncoder.encodePassword(defultPwd);
		User user=new User(
				1, //Integer 状态 default=0  {'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'}
				5, //Integer 角色 default=0 这里如果改变，权限表也要变化 {'0':'超管','1':'一般管理员','4':'企业会员','5':'个人会员','6':'水站'}
				mobile, //String 账号  唯一值 
				defultPwd, //String 密码   
				mobile, //String 昵称   
				null, //Integer 隶属于   
				clientInfo.getOpenid() , //String 微信openid   
				null
				);
		//返回的jr.obj=ret,ret.put("user",dbuser);
		Map<String,Object> ret=checklogin(request, response, model, clientInfo, user, 0);
		if(ret==null){
			throw new BoException("账号密码错误");
		}
		User retuser=(User)(ret.get("user"));
		retuser.setPassword(defaultPwdMd5);
		userService.execute(null, "update User set password=?0 where username=?1", new Object[]{mobile,defaultPwdMd5});
		if(retuser==null||(retuser.getRoleId().intValue()!=AttrStatic.ROLE_MEMBER )){
			throw new BoException("账号密码错误");
		}
		//放下Token
		putToken(retuser);
		return retuser;
	}
	/**
	 * 登录检查，根据来源执行不同函数：账密检查 or 账号检查
	 * @param request
	 * @param response
	 * @param model
	 * @param clientInfo
	 * @param user
	 * @param iscreate 如果空用户是否创建，0不创建(检查时检查账密)，1如果空创建用户,不空返回用户(检查时只查账号即可)，2如果空创建用户,不空则抛错(检查时只查账号即可) 
	 * @return
	 */
	private Map<String,Object> checklogin(HttpServletRequest request,HttpServletResponse response,ModelMap model,ClientInfo clientInfo,User user,Integer iscreate)throws BoException, NeedLoginException, RightException, Exception{
		if(user==null){
			throw new BoException("账密为空");
		}
		logger.info("登录：user="+user.getUsername()+",clientInfo="+clientInfo.toString());
		//clientInfo放到obj1，后面有可能会用到
		//user.setObj1(clientInfo);
		Json jr=null;
		//一个微信客服端（openid）只能登录一个角色
		User userCheck=(User)userService.getFirst(null, "openid=?0", null, new Object[]{clientInfo.getOpenid()});
		if(userCheck!=null){
			logger.info("原用户="+userCheck.getRoleId().intValue()+"客户端提交roleid="+user.getRoleId().intValue());
			if(userCheck.getRoleId().intValue()!=user.getRoleId().intValue()){
				throw new BoException("没有登录权限,请选择正确入口登入");
			}
		}
		if(StringUtilz.integerNullOr0(iscreate)){
			jr=specUserService.loginCheck(user, false);
		}else if(iscreate.intValue()==1){
			jr=specUserService.createLoginCheckCreateOne(user);
		}else{ 
			jr=specUserService.createLoginCheckCreateOneOrThrowException(user);
		}

		if(!jr.isSuccess()){
			logger.debug("!jr.isSuccess()="+jr.getMsg());
			throw new BoException(jr.getMsg());
		}	
		//返回的jr.obj=ret,ret.put("user",dbuser);
		Map<String,Object> ret=(Map<String,Object>)jr.getObj();
		if(ret==null){
			throw new BoException("登录失败");
		}
		User userRet=(User)(ret.get("user"));
		return ret;
	}

	/**
	 * 登录后,放下Token
	 * @param user
	 * @return
	 */
	public boolean putToken(User user){
		user.setToken(AESUtil.getInstance().encryptUser(user));
		return true;
	}

	/**
	 * 版本更新，在手机端的my页面中
	 * @param request
	 * @param clientInfo
	 * @return Json msg="FORCE:强升/ADVICE:建议/NO:不用升级"
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws RightException
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateVer", method = RequestMethod.GET)
	@ResponseBody
	public Json updateVer(HttpServletRequest request, ClientInfo clientInfo) throws BoException, NeedLoginException, RightException, Exception {		
		if(clientInfo.getVer()==null){
			logger.info("传进来的clientInfo.getVer为空！");
			return new Json(true,"NO");
		}
		Webset wsupdateVer=websetService.getckey(null, "updateVer");
		if(wsupdateVer==null || StringUtils.isBlank(wsupdateVer.getCvalue())){
			logger.info("updateVer没有设置");
			return new Json(true,"NO");
		}
		if(clientInfo.getVer()!=Integer.parseInt(wsupdateVer.getCvalue())){
			return new Json(true,"Yes");
		}
		return new Json(true,"NO");
	}
	/**
	 * 版本控件大御医版
	 * @param request
	 * @param clientInfo
	 * @return Json msg="FORCE:强升/ADVICE:建议/NO:不用升级"
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws RightException
	 * @throws Exception
	 */
	@RequestMapping(value = "/verControlYjtcm", method = RequestMethod.GET)
	@ResponseBody
	public Json verControlYjtcm(HttpServletRequest request, ClientInfo clientInfo) throws BoException, NeedLoginException, RightException, Exception {		
		if(clientInfo.getVer()==null){
			logger.info("传进来的clientInfo.getVer为空！");
			return new Json(true,"NO");
		}
		if(clientInfo.getCli()!=null&&clientInfo.getCli()==1){//安卓
			//安卓强升
			Webset wsApuAndForce=websetService.getckey(null, "ApuAndForceYjtcm");
			if(wsApuAndForce==null || StringUtils.isBlank(wsApuAndForce.getCvalue())){
				logger.info("wsApuAndForce没有设置");
				return new Json(true,"NO");
			}
			if(clientInfo.getVer()<=Integer.parseInt(wsApuAndForce.getCvalue())){//如果版本号小于强升的最低版本号，就要强升
				return new Json(true,"FORCE");
			}
			//安卓建议
			Webset wsApuAndAdvice=websetService.getckey(null, "ApuAndAdviceYjtcm");
			if(wsApuAndAdvice==null || StringUtils.isBlank(wsApuAndAdvice.getCvalue())){
				logger.info("ApuAndAdvice没有设置");
				return new Json(true,"NO");
			}
			if(clientInfo.getVer()<=Integer.parseInt(wsApuAndAdvice.getCvalue())){//如果版本号小于强升的最低版本号，就要建议
				return new Json(true,"ADVICE");
			}
		}
		if(clientInfo.getCli()!=null&&clientInfo.getCli()==2){//苹果
			Webset wsApuIosForce=websetService.getckey(null, "ApuIosForceYjtcm");
			if(wsApuIosForce==null || StringUtils.isBlank(wsApuIosForce.getCvalue())){
				logger.info("ApuIosForce没有设置");
				return new Json(true,"NO");
			}
			if(clientInfo.getVer()<=Integer.parseInt(wsApuIosForce.getCvalue())){//如果版本号小于强升的最低版本号，就要强升
				return new Json(true,"FORCE");
			}
			Webset wsApuIosAdvice=websetService.getckey(null, "ApuIosAdviceYjtcm");
			if(wsApuIosAdvice==null || StringUtils.isBlank(wsApuIosAdvice.getCvalue())){
				logger.info("ApuIosAdvice没有设置");
				return new Json(true,"NO");
			}
			if(clientInfo.getVer()<=Integer.parseInt(wsApuIosAdvice.getCvalue())){//如果版本号小于强升的最低版本号，就要建议
				return new Json(true,"ADVICE");
			}
		}
		return new Json(true,"NO");
	}
	/**
	 * 版本控件
	 * @param request
	 * @param clientInfo
	 * @return Json msg="FORCE:强升/ADVICE:建议/NO:不用升级"
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws RightException
	 * @throws Exception
	 */
	@RequestMapping(value = "/verControl", method = RequestMethod.GET)
	@ResponseBody
	public Json verControl(HttpServletRequest request, ClientInfo clientInfo) throws BoException, NeedLoginException, RightException, Exception {		
		if(clientInfo.getVer()==null){
			logger.info("传进来的clientInfo.getVer为空！");
			return new Json(true,"NO");
		}
		if(clientInfo.getCli()!=null&&clientInfo.getCli()==1){//安卓
			//安卓强升
			Webset wsApuAndForce=websetService.getckey(null, "ApuAndForce");
			if(wsApuAndForce==null || StringUtils.isBlank(wsApuAndForce.getCvalue())){
				logger.info("wsApuAndForce没有设置");
				return new Json(true,"NO");
			}
			if(clientInfo.getVer()<=Integer.parseInt(wsApuAndForce.getCvalue())){//如果版本号小于强升的最低版本号，就要强升
				return new Json(true,"FORCE");
			}
			//安卓建议
			Webset wsApuAndAdvice=websetService.getckey(null, "ApuAndAdvice");
			if(wsApuAndAdvice==null || StringUtils.isBlank(wsApuAndAdvice.getCvalue())){
				logger.info("ApuAndAdvice没有设置");
				return new Json(true,"NO");
			}
			if(clientInfo.getVer()<=Integer.parseInt(wsApuAndAdvice.getCvalue())){//如果版本号小于强升的最低版本号，就要建议
				return new Json(true,"ADVICE");
			}
		}
		if(clientInfo.getCli()!=null&&clientInfo.getCli()==2){//苹果
			Webset wsApuIosForce=websetService.getckey(null, "ApuIosForce");
			if(wsApuIosForce==null || StringUtils.isBlank(wsApuIosForce.getCvalue())){
				logger.info("ApuIosForce没有设置");
				return new Json(true,"NO");
			}
			if(clientInfo.getVer()<=Integer.parseInt(wsApuIosForce.getCvalue())){//如果版本号小于强升的最低版本号，就要强升
				return new Json(true,"FORCE");
			}
			Webset wsApuIosAdvice=websetService.getckey(null, "ApuIosAdvice");
			if(wsApuIosAdvice==null || StringUtils.isBlank(wsApuIosAdvice.getCvalue())){
				logger.info("ApuIosAdvice没有设置");
				return new Json(true,"NO");
			}
			if(clientInfo.getVer()<=Integer.parseInt(wsApuIosAdvice.getCvalue())){//如果版本号小于强升的最低版本号，就要建议
				return new Json(true,"ADVICE");
			}
		}
		return new Json(true,"NO");
	}
	
}
