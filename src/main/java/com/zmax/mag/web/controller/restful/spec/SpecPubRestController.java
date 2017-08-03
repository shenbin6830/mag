package com.zmax.mag.web.controller.restful.spec;

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
import com.zmax.common.exception.NeedLoginException;
import com.zmax.common.exception.RightException;
import com.zmax.common.utils.easyui.Json;
import com.zmax.common.utils.string.Md5PwdEncoder;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.mag.domain.bean.Member;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.utils.AESUtil;
import com.zmax.mag.service.my.UserService;
import com.zmax.mag.service.spec.SpecRoleService;
import com.zmax.mag.service.spec.SpecSmsService;
import com.zmax.mag.service.spec.SpecUserService;
import com.zmax.mag.web.controller.restful.entity.ClientInfo;

/**
 * 
 * @author zmax
 *
 */
@RestController
@RequestMapping("/rest/pub")
public class SpecPubRestController {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	UserService userService;
	
	@Autowired
	SpecUserService specUserService;
	@Autowired
	SpecSmsService specSmsService;
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
			json = specUserService.loginCheck(user ,  isNeedPm);
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
	public User dologin(HttpServletRequest request,HttpServletResponse response,ModelMap model,ClientInfo clientInfo,User user,Integer isauto,Integer iscreate,String nowURL)throws BoException, NeedLoginException, RightException, Exception{
		if(user==null){

			throw new BoException("账密为空");
		}
		//必须项包括：username password roleid
		if(StringUtils.isBlank(user.getUsername())
				||StringUtils.isBlank(user.getPassword())
				//||null==user.getRoleId()
				){
			throw new BoException("账密为空");
		}
		//返回的jr.obj=ret,ret.put("user",dbuser);
		Map<String,Object> ret=checklogin(request, response, model, clientInfo, user, isauto, iscreate);
		
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
		//放下Token
		putToken(retuser);
		return retuser;
		
	}
	/**
	 * 用户请求验证码
	 * @param request
	 * @param mobile 手机号
	 * @throws Exception 
	 */
	@RequestMapping(value = "/requestCode", method = RequestMethod.GET)
	public Json requestCode(HttpServletRequest request, String mobile) throws BoException, Exception {
		specSmsService.requestCode(mobile);
		return new Json(true);
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
			if(SpecRoleService.isAdmin(userdb)||SpecRoleService.isMember(userdb))
				return true;
		}			
		return false;
	}
	/**
	 * 登录后,放下Token
	 * @param user
	 * @return
	 */
	private boolean putToken(User user){
		//个人或商户放token
		if(SpecRoleService.isMember(user) 
				){
			user.setToken(AESUtil.getInstance().encryptUser(user));
		}
		return true;
	}
	/**
	 * 登录检查，根据来源执行不同函数：账密检查 or 账号检查
	 * @param request
	 * @param response
	 * @param model
	 * @param clientInfo
	 * @param user
	 * @param isauto
	 * @param iscreate 如果空用户是否创建，0不创建(检查时检查账密)，1如果空创建用户,不空返回用户(检查时只查账号即可)，2如果空创建用户,不空则抛错(检查时只查账号即可) 
	 * @return
	 */
	private Map<String,Object> checklogin(HttpServletRequest request,HttpServletResponse response,ModelMap model,ClientInfo clientInfo,User user,Integer isauto,Integer iscreate)throws BoException, NeedLoginException, RightException, Exception{
		if(user==null){
			throw new BoException("账密为空");
		}
		logger.info("登录：user="+user.getUsername()+",clientInfo="+clientInfo.toString()+",isauto="+isauto);
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
			jr=specUserService.loginCheck(user,false);
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
		//如果带了微信OPENID，更新绑定关系，更新wxouser，更新user...
//		if(userRet!=null && clientInfo!=null)
//			specWxService.updateOpenidUserIdBind(userRet.getId(), clientInfo.getOpenid(),userRet.getRoleId());
		//更下设备
//		specRestService.updateLastDevice(userRet, clientInfo);
		return ret;
	}

}
