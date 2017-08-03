package com.zmax.mag.web.controller.page.base;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zmax.common.utils.easyui.Json;
import com.zmax.common.utils.string.FastjsonFilter;
import com.zmax.mag.domain.bean.User;


/**
 * 
 * <br>
 * <b>类描述:</b>
 * 
 * <pre>
 * 所有Controller的基类
 * </pre>
 * 
 * @see
 * @since
 */
public class BaseController {
	/**日志实例*/
	private static final Logger logger = Logger.getLogger(BaseController.class);
	/**所有的出错页面指向*/
	public static final String ERRPAGE="/error/err";
	/**出错参数名*/
	public static final String ERROR_MSG_KEY = "errorMsg";
	/**SESSION为空*/
	public static final String NULLSESSION="sm.error.null.session";
	/**数据为空*/
	public static final String NULLDATA="sm.error.null.data";
	/**数据库为空*/
	public static final String NULLDB="sm.error.null.db";
	/**没有权限*/
	public static final String NOTALLOWED="sm.error.not.allowed";
	/**防重复提交的变量名称*/
	static final String FORM_TOKEN_NAME="_form_token_id_";
	/**防重复提交的错误信息*/
	static final String ERROR_TOKEN="sm.error.duplicateSubmit";	
	/**没登录的JSON返回信息*/
	static final String ERROR_NOLOGIN="nologin";	
	static final String success_jsonpCallback="success_jsonpCallback";
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		dateFormat.setLenient(false); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, false)); 
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true)); 
		binder.registerCustomEditor(int.class, null, new CustomNumberEditor(Integer.class, null , true)); 
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true)); 
		binder.registerCustomEditor(long.class, null, new CustomNumberEditor(Long.class, null, true)); 
		binder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true)); 
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true)); 
		binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(BigInteger.class, true)); 
	} 
	/**
	 * js提交来的数据，有可能是null,undefined,NAN等，这时判断收到的数据为null
	 * @param str
	 * @return
	 */
	public boolean jsnull(String str){
		if("null".equalsIgnoreCase(str))
			return true;
		if("undefined".equalsIgnoreCase(str))
			return true;
		if("nan".equalsIgnoreCase(str))
			return true;
		return false;
	}
	/**
	 * 防重复提交，放置form token
	 * 使用方法在get的代码中放：putToken(request);
	 * 在页面中放 < @z.formtoken bind=" $ {className}.*"/>
	 * @param request
	 */
	public void putToken(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.setAttribute(FORM_TOKEN_NAME, RandomStringUtils.randomAlphanumeric(32));
	}
	/**
	 * 防重复提交，检查form token，错误信息放到BindingResult中
	 * 使用方法在post的代码中放：checkToken(request, errors);
	 * @param request
	 * @param errors
	 * @return 是否通过检查
	 */
	public boolean checkToken(HttpServletRequest request,BindingResult errors){
		HttpSession session=request.getSession();
		String sessionTokenid=(String)session.getAttribute(FORM_TOKEN_NAME);
		if(sessionTokenid==null){
			retErr(session, errors);
			return false;
		}
		String formTokenid=request.getParameter(FORM_TOKEN_NAME);
		if(formTokenid==null){
			retErr(session, errors);
			return false;
		}
		if(!formTokenid.equals(sessionTokenid)){
			retErr(session, errors);
			return false;
		}
		retErr(session, null);
		return true;
	}	
	/**
	 * 放置错误信息，清session
	 * @param request
	 * @param errors
	 */
	private void retErr(HttpSession session,BindingResult errors){
		if(errors!=null){
			ObjectError err=new FieldError("token", "id", ERROR_TOKEN);
			errors.addError(err);
		}
		session.removeAttribute(FORM_TOKEN_NAME);
	}
	/**
	 * 获取基于应用程序的url绝对路径
	 * 
	 * @param request
	 * @param url
	 *            以"/"打头的URL地址
	 * @return 基于应用程序的url绝对路径
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}
	/**
	 * 返回出错页
	 * @param request
	 * @param model
	 * @param msg
	 * @return
	 */
	public String errRet(HttpServletRequest request,ModelMap model,String msg){
		model.addAttribute("errorMsg", msg);
		return wm(request,null, "/error/msg",null,null);
	}
	/**
	 * 跳到出错页
	 * @param msg 错误信息
	 * @return
	 */
	public String err(String msg){
		try {
			return "redirect:"+ERRPAGE+"?"+ERROR_MSG_KEY+"="+URLEncoder.encode(msg,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			logger.error(e);
			return null;
		}
	}
	/**
	 * 跳到出错页之手机版
	 * @param model
	 * @param msg
	 * @return
	 */
	public String errm(ModelMap model,String msg){
		model.addAttribute("errorMsg", msg);
		return "/error/errm";
	}
	/**
	 * 返回出错页_JSON版
	 * @param request
	 * @param response
	 */
	public void errJson(HttpServletRequest request,HttpServletResponse response,String msg){
		msg=(msg==null)?"":msg;
		writeJson(request, response, new Json(false,"操作失败！"+msg));
		return;
	}
	/**
	 * 远程版_返回出错页_JSON版
	 * @param request
	 * @param response
	 */
	public void errJsonR(HttpServletRequest request,HttpServletResponse response){
		writeJsonR(request, response, new Json(false,"操作失败！"));
		return;
	}
	/**
	 * 返回出错去登录页_JSON版
	 * @param request
	 * @param response
	 */
	public void errJsonNologin(HttpServletRequest request,HttpServletResponse response){
		writeJson(request, response, new Json(false,ERROR_NOLOGIN));
		return;
	}	
	/**
	 * 远程版_返回出错去登录页_JSON版
	 * @param request
	 * @param response
	 */
	public void errJsonNologinR(HttpServletRequest request,HttpServletResponse response){
		writeJsonR(request, response, new Json(false,ERROR_NOLOGIN));
		return;
	}	
	/**
	 * 返回系统消息
	 * @param msg 信息
	 * @return
	 */
	public String msg(String msg,ModelMap model){
		model.addAttribute(ERROR_MSG_KEY,msg);
		return ERRPAGE+"/msg";
	}
	/**
	 * 去重，留后面那个
	 * @param param
	 * @return
	 */
	public String keepOne(String param){
		if(!StringUtils.isBlank(param)&& param.split(",").length>1)
			param=param.split(",")[1];
		return param;
	}
	/**
	 * 把request收到的参数放回model
	 * @param model
	 * @param request
	 * @param param String
	 */
	public void r2m(ModelMap model,HttpServletRequest request,String param){
		if(StringUtils.isNotBlank(request.getParameter(param)))
			model.addAttribute(param,keepOne(request.getParameter(param)));
	}
	/**
	 * index几要素的保持，同时去重
	 * @param model
	 * @param request
	 * @param param
	 */
	public void keepParam(ModelMap model,HttpServletRequest request){
		r2m(model, request, "queryhq");
		r2m(model, request, "newobj");
		r2m(model, request, "search");
		r2m(model, request, "returnUrl");
		r2m(model, request, "returnUrlOut");
	}

	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * 
	 * @param object
	 * @param includesProperties
	 *            需要转换的属性
	 * @param excludesProperties
	 *            不需要转换的属性
	 */
	public void writeJsonByFilter(HttpServletRequest request,HttpServletResponse response,Object object, String[] includesProperties, String[] excludesProperties,boolean isRemote) {
		try {
			FastjsonFilter filter = new FastjsonFilter();// excludes优先于includes
			if (excludesProperties != null && excludesProperties.length > 0) {
				filter.getExcludes().addAll(Arrays.<String> asList(excludesProperties));
			}
			if (includesProperties != null && includesProperties.length > 0) {
				filter.getIncludes().addAll(Arrays.<String> asList(includesProperties));
			}
			if (logger.isDebugEnabled())
				logger.debug("对象转JSON：要排除的属性[" + excludesProperties + "]要包含的属性[" + includesProperties + "]");
			String json;
			String User_Agent = request.getHeader("User-Agent");
			if (User_Agent.indexOf("MSIE") > -1 && (User_Agent.indexOf("MSIE 6") > -1)) {
				// 使用SerializerFeature.BrowserCompatible特性会把所有的中文都会序列化为\\uXXXX这种格式，字节数会多一些，但是能兼容IE6
				json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.BrowserCompatible);
			} else {
				// 使用SerializerFeature.WriteDateUseDateFormat特性来序列化日期格式的类型为yyyy-MM-dd hh24:mi:ss
				// 使用SerializerFeature.DisableCircularReferenceDetect特性关闭引用检测和生成
				json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
			}
			String w=json;
			if(isRemote){
				String callbackparam=request.getParameter("callbackparam");
				if(StringUtils.isBlank(callbackparam))
					callbackparam=success_jsonpCallback;
				w=callbackparam+"("+json+")";
			}
			if (logger.isDebugEnabled())	
				logger.debug("返回：" + w);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(w);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印JSON给前端
	 * 
	 * @param object
	 * @throws IOException
	 */
	public void writeJson(HttpServletRequest request,HttpServletResponse response,Object object) {
		writeJsonByFilter(request,response,object, null, null,false);
	}
	/**
	 * 打印JSON给前端并且返回Json
	 * @param request
	 * @param response
	 * @param json
	 * @return
	 */
	public Json retAndWriteJson(HttpServletRequest request,HttpServletResponse response,Json json){
		writeJson(request, response, json);
		return json;
	}
	/**
	 * 跨域版CORS非JSONP方式的返回
	 * 
	 * @param object
	 * @throws IOException
	 */
	public void writeJsonC(HttpServletRequest request,HttpServletResponse response,Object object) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		writeJsonByFilter(request,response,object, null, null,false);
	}
	/**
	 * 远程版打印JSON给前端
	 * 
	 * @param object
	 * @throws IOException
	 */
	public void writeJsonR(HttpServletRequest request,HttpServletResponse response,Object object) {
		writeJsonByFilter(request,response,object, null, null,true);
	}
	/**
	 * 打印并且返回Json
	 * @param request
	 * @param response
	 * @param json
	 * @return
	 */
	public Json retAndWriteJsonR(HttpServletRequest request,HttpServletResponse response,Json json){
		writeJsonR(request, response, json);
		return json;
	}
	/**
	 * 将对象转换成JSON字符串，并响应回前端
	 * 
	 * @param object
	 * @param includesProperties
	 *            需要转换的属性
	 */
	public void writeJsonByIncludesProperties(HttpServletRequest request,HttpServletResponse response,Object object, String[] includesProperties) {
		writeJsonByFilter(request,response,object, includesProperties, null,false);
	}

	/**
	 * 将对象转换成JSON字符串，并响应回前端
	 * 
	 * @param object
	 * @param excludesProperties
	 *            不需要转换的属性
	 */
	public void writeJsonByExcludesProperties(HttpServletRequest request,HttpServletResponse response,Object object, String[] excludesProperties) {
		writeJsonByFilter(request,response,object, null, excludesProperties,false);
	}
	/**
	 * 打印错误给前端
	 * @param request
	 * @param response
	 */
	public void error(HttpServletRequest request,HttpServletResponse response){
		writeJson(request, response, new Json(true,"操作失败！"));
		return;
	}
	/**
	 * 远程版打印错误给前端
	 * @param request
	 * @param response
	 */
	public void errorR(HttpServletRequest request,HttpServletResponse response){
		writeJsonR(request, response, new Json(true,"操作失败！"));
		return;
	}
	/**
	 * 普通的打印到响应中
	 * @param response
	 * @param msg
	 */
	public void outPrint(HttpServletResponse response,String msg){
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(msg);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// e.printStackTrace();
			logger.error(e);
		}
	}
	/**
	 * 返回网页版或手机版，手机版=网页版+m
	 * ex."/user/login"     or "/user/loginm" 
	 * 
	 * @param request
	 * @param ret 标准返回
	 * @param clazz 类名 ex.User
	 * @param act 操作 ex.index/show/new/edit/editGetEmpty
	 * @return
	 */
	public String wm(HttpServletRequest request,User sessionUser,String ret,String clazz,String act){
		//		Browser browser=Browser.browserByUseragent(request);
		//		if(browser.ios || browser.android)
		//			return ret+"m";
		//如果不是/pages开头加上
		if(ret.indexOf("/pages/")==0)
			return ret;

		return "/pages"+ret;
	}
}