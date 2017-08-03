package com.zmax.mag.web.controller.page.spec;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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

import com.zmax.common.conf.AttrStatic;
import com.zmax.common.conf.SessionName;
import com.zmax.common.exception.BoException;
import com.zmax.common.utils.easyui.Json;
import com.zmax.common.utils.string.Md5PwdEncoder;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.service.my.UserService;
import com.zmax.mag.service.spec.DbSpec;
import com.zmax.mag.service.spec.SpecWxService;
import com.zmax.mag.service.utils.PermitCheckUtils;
import com.zmax.mag.service.utils.PermitDbSvrUitls;
import com.zmax.mag.web.controller.page.base.BaseController;







/**
 * 
 * <br>
 * <b>类描述:</b>
 * 
 * <pre>
 * 不用登录的页，如：登录，退出
 * 以及一些共用的页，如：出错页，成功页，消息页
 * </pre>
 * 
 * @see
 * @since
 */
@Controller
@RequestMapping("/")
public class SpecPubController extends BaseController{
	/**日志实例*/
	private static final Logger logger = Logger.getLogger(SpecPubController.class);
	/**平台id，用于单用户，多微信用户的路径是/pub/wx/{bizId}/xxxx，同时参数带@PathVariable Integer bizId*/
	private Integer bizId=Conf.BIZID;

	@Autowired
	DbSpec dbSpec;
	@Autowired
	PermitDbSvrUitls permitDbSvrUitls;
	@Autowired
	PermitCheckUtils permitCheckUtils;
	
	@Autowired
	SpecWxService specWxService;

	@Autowired
	UserService userService;

	/**
	 * 初始化或重新更新参数
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/user/paraminit",method=RequestMethod.GET)
	public String paraminit(HttpServletRequest request,ModelMap model) {
		//权限表字段数据库初始化
		permitDbSvrUitls.doInit();
		//权限初始化
		permitCheckUtils.doInit();

		//微信相关
		specWxService.init();
		specWxService.refreshTokens();
		
		if(model!=null)
			model.addAttribute("errorMsg", "完成");
		return wm(request,null, "/error/msg",null,null);
	}	

	/**
	 * 首页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		return wm(request,null, "/pub/index",null,null);
	}
	/**
	 * 登录 get
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginget(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user) {
		if(user==null)
			user=new User();
		model.addAttribute("user",user);
		return wm(request,null, "/pub/login",null,null);
	}
	
	/**
	 * 注册GET
	 * @param request
	 * @param response
	 * @param model
	 * @param user 基本上是空的
	 * @param roleId 角色
	 * @return
	 */
	@RequestMapping(value="/reg",method=RequestMethod.GET)
	public String reg(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user,Integer roleId) {
		if(user==null)
			user=new User();
		if(roleId!=null){
			user.setRoleId(roleId);
		}else{
			//放个默认值
			user.setRoleId(AttrStatic.ROLE_MEMBER);
		}
		//下面的角色，不允许通过注册页注册
		if(user.getRoleId().intValue()==AttrStatic.ROLE_ADMIN 
				|| user.getRoleId().intValue()==AttrStatic.ROLE_CADMIN){
			return err("系统权限限止此项操作，请联系管理员");
		}
		try {
			userService.addString(null, user);
		} catch (BoException e) {
			//e.printStackTrace();
			logger.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		model.addAttribute("user",user);
		return wm(request,null, "/pub/reg",null,null);
	}

	/**
	 * 退出
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		request.getSession().removeAttribute(SessionName.user);
		return loginget(request, response, model, null);
	}		
	

}
