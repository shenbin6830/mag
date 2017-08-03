package com.zmax.mag.web.controller.restpage.spec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zmax.common.conf.AttrStatic;
import com.zmax.common.conf.SessionName;
import com.zmax.common.exception.BoException;
import com.zmax.common.utils.easyui.Json;
import com.zmax.common.utils.string.Md5PwdEncoder;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.mag.domain.bean.Member;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.conf.PropMy;
import com.zmax.mag.service.spec.DbSpec;
import com.zmax.mag.service.spec.SpecUserService;
import com.zmax.mag.web.utils.OplogUtils;

/**
 * 
 * @author zmax
 *
 */
@RestController
@RequestMapping("/restpage/pub")
public class SpecPubRestpageController {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	SpecUserService specUserService;
	@Autowired
	OplogUtils oplogUtils;
	@Autowired
	DbSpec dbSpec;
	@Autowired
	PropMy propMy;
	/**
	 * http://127.0.0.1/xxx/restpage/pub/dbinit?pwd=admin123
	 * @param pwd
	 * @return
	 * @throws BoException
	 */
	@RequestMapping(value="/dbinit",method=RequestMethod.GET)
	public Json dbinit(String pwd) throws BoException {
		if(StringUtils.isNotBlank(pwd) && pwd.equals(propMy.getAdminpwd())){
			dbSpec.init();
			return new Json(true,"ok");
		}
		return new Json(true,"ok!");
	}
	
	/**
	 * 会员登录，收到的密码是明文密码
	 * 用于输入账号密码登录
	 * @param user
	 * @param errors
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @return new Json(success, msg, {"user",user})
	 * @throws BoException
	 */
	@RequestMapping(value="/test")
	public Json test(HttpServletRequest request,HttpServletResponse response,HttpSession session,ModelMap model) throws BoException {
		return new Json(true,"hi！ "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}	
	/**
	 * 会员登录，收到的密码是明文密码
	 * 用于输入账号密码登录
	 * @param user
	 * @param errors
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @return new Json(success, msg, {"user",user})
	 * @throws BoException
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Json loginpost(@Valid User user,BindingResult errors,HttpServletRequest request,HttpServletResponse response,HttpSession session,ModelMap model) throws BoException {
		/*
		ZbabiUtils.checkCaptcha(imageCaptchaService, request, errors);
		if(errors.hasErrors()) {
			return new Json(false,"验证码错误！");
		}
		 */
		if(errors!=null && errors.hasErrors()) {
			return new Json(false,"格式错误！",errors.getFieldErrors());
		}
		Json json=null;
		try {
			json = specUserService.loginCheck(user , true);
			//放session
			User user2session=calcRetJsonUser(json);
			request.getSession().setAttribute(SessionName.user, user2session);
			oplogUtils.createOne(request, response, 0, "User", ""+user2session.getId(), "登录成功");
			//zres
			if(user2session.getRoleId()==0){
				request.getSession().setAttribute(SessionName.ZRES_SESSIONUSERTYPE, "admin");
				request.getSession().setAttribute(SessionName.ZRES_SESSIONUSERID, 0);
			}else{
				request.getSession().setAttribute(SessionName.ZRES_SESSIONUSERTYPE, "user");
				request.getSession().setAttribute(SessionName.ZRES_SESSIONUSERID, user2session.getUserId());
			}
			
		} catch (BoException e) {
			return new Json(false,e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Json(false,"系统正忙，请稍后再试!");
		}		
		return json;
	}	
	/**
	 * 登录检查之收到的密码是MD5密码，用于自动登录，客户端存的就是md5，极有可能是前端页来的。
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @param isNeedPm 是否需要放入权限列表
	 * @return  new Json(success, msg, {"user",user})
	 */
	@RequestMapping(value="/loginmd5",method=RequestMethod.POST)
	public Json loginCheckMd5(@Valid User user,BindingResult errors,HttpServletRequest request,HttpServletResponse response,ModelMap model,boolean isNeedPm) {
		if(errors!=null && errors.hasErrors()) {
			return new Json(false,"格式错误！",errors.getFieldErrors());
		}
		Json json=null;
		try {
			json = specUserService.loginCheck(user , isNeedPm);
			User user2session=calcRetJsonUser(json);
			if(isNeedPm){
				//放session
				request.getSession().setAttribute(SessionName.user, user2session);
			}
			
		} catch (BoException e) {
			return new Json(false,"needlogin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Json(false,"系统正忙，请稍后再试!");
		}		
		return json;
	}
	
	/**
	 * 登录检查账号创建版
	 * 如果账号不存创建一个User，同时创建Member
	 * 如果账号已经存在，只查账号，创建完后，自动登录，用于H5前端 
	 * @param user
	 * @param errors
	 * @param request
	 * @param response
	 * @param model
	 * @param code 短信验证码
	 * @return
	 */
	@RequestMapping(value="/loginCheckCreateUserMember",method=RequestMethod.POST)
	public Json loginCheckCreateUserMember(@Valid User user,BindingResult errors,HttpServletRequest request,HttpServletResponse response,ModelMap model,String code) {
		if(errors!=null && errors.hasErrors()) {
			return new Json(false,"格式错误！",errors.getFieldErrors());
		}
		
		Json json=null;
		try {
			//specSmsService.checkCode(user.getUsername(), code);
			json = specUserService.loginCheck(user , false);
		} catch (BoException e) {
			return new Json(false,"needlogin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Json(false,"系统正忙，请稍后再试!");
		}		
		return json;
	}

	
	/**
	 * 注册提交
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/reg",method=RequestMethod.POST)
	public Json createUser(@Valid User user,BindingResult errors,ModelMap model,HttpServletRequest request,HttpServletResponse response)  {
		if(errors!=null && errors.hasErrors()) {
			return new Json(false,"格式错误！",errors.getFieldErrors());
		}
		Json json=null;
		try {
			json=specUserService.createReg(user);
		} catch (BoException e) {
			return new Json(false,e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Json(false,"系统正忙，请稍后再试!");
		}
		return json;
	}
	/**
	 * 注册提交之快速注册
	 * 同时创建User+Member
	 * @param request
	 * @param response
	 * @param model
	 * @param user 必须：username，非必须：password/111111，最好有：openid
	 * @param isauto 0/null明文,1密文
	 * @param isFromRest 是否从rest过来的
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	@RequestMapping(value="/regUserMember",method=RequestMethod.POST)
	public Json createUserMember(@Valid User user,BindingResult errors,HttpServletRequest request,HttpServletResponse response,ModelMap model,String code) throws BoException, Exception {
		if(errors!=null && errors.hasErrors()) {
			return new Json(false,"格式错误！",errors.getFieldErrors());
		}
		Json json=null;
		try {
			//specSmsService.checkCode(user.getUsername(), code);
			json=specUserService.createReg(user);
		} catch (BoException e) {
			return new Json(false,e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Json(false,"系统正忙，请稍后再试!");
		}
		return json;
	}


	/**
	 * AJAX检查是否已经登录之消费者
	 * <br>TODO:把流量登记一起做掉
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/islogin")
	public Json islogin(HttpServletRequest request,HttpServletResponse response,HttpSession session,ModelMap model) {
		if(null==session.getAttribute(SessionName.user)){
			return new Json(false,"未登录");
		}
		return new Json(true,"已登录");
	}

	/**
	 * SESSION处理及JSOn整理
	 * 处理一下返回的JSON，不需要把Pmtmap返回，太大了。
	 * 处理后，json中的User不带Pmtmap
	 * @param json
	 * @return User 原来的user的复制，带pm列表
	 */
	private User calcRetJsonUser(Json json){
		Map<String,Object> map=(Map<String,Object>)json.getObj();
		if(map==null)
			return null;
		User user=(User)map.get("user");
		if(user==null)
			return null;
		User usercp=new User();
		BeanUtils.copyProperties(user, usercp);
		user.setPmtmap(null);		
		return usercp;
	}
}
