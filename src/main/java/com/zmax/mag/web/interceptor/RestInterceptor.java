package com.zmax.mag.web.interceptor;

import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.zmax.common.web.utils.RequestUtils;


/**
 * REST api 权限上下文信息拦截器
 *  /prj/rest/xxx
 * 
 * 
 * @author 
 * 
 */
public class RestInterceptor implements HandlerInterceptor {
	private final Log logger = LogFactory.getLog(getClass());

	private String accessKeyParameterName="accessKey";
	private String[] defaultAccessAllowedFrom={"*"};


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		/*
		String accessKey=request.getParameter(accessKeyParameterName);
		String referer = request.getHeader("Referer");
		URL u = new URL(referer);
		String host = u.getHost().toLowerCase();
		if(accessKey==null){
			logger.error("====================================ILLEGAL ACCESS: ACCESS_KEY_MISSING!=======================");
		}else{
			logger.debug("====================================ACCESS WITH Access KEY:"+accessKey+"====================");
			IAccess access = accessService.getAccess(UserSessionUtils.getUserSession(request), accessKey);
			if(access!=null){
				defaultAccessAllowedFrom=access.getAccessAllowedFrom();
			}else{
				log.warn("======================================ACCESS KEY:"+accessKey+" DOES NOT EXIST!=================");	
			}
		}
		 */
		if (logger.isDebugEnabled()){
			logger.debug("rest:"+reqInfo(request, response));
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		return true;
	}
	/**
	 * 访问者信息
	 * @param request
	 * @param response
	 * @return
	 */
	private String reqInfo(HttpServletRequest request,
			HttpServletResponse response){
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String ip = RequestUtils.getIpAddr(request);
		return "uri="+uri+",ip="+ip;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mav)
					throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
					throws Exception {

	}

	public String[] getDefaultAccessAllowedFrom() {
		return defaultAccessAllowedFrom;
	}

	public void setDefaultAccessAllowedFrom(String[] defaultAccessAllowedFrom) {
		this.defaultAccessAllowedFrom = defaultAccessAllowedFrom;
	}

	public String getAccessKeyParameterName() {
		return accessKeyParameterName;
	}

	public void setAccessKeyParameterName(String accessKeyParameterName) {
		this.accessKeyParameterName = accessKeyParameterName;
	}
}




