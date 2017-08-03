package com.zmax.mag.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.zmax.common.conf.SessionName;
import com.zmax.common.web.utils.RequestUtils;
import com.zmax.mag.domain.bean.Oplog;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.service.my.OplogService;
import com.zmax.mag.web.utils.OplogUtils;
/**
 * 用户拦截器 /prj/restpage/user/xxx
 * @author Administrator
 *
 */
public class RestpageInterceptor implements HandlerInterceptor {
	private final Log logger = LogFactory.getLog(getClass());

	OplogUtils oplogUtils;	

	
	public RestpageInterceptor(OplogUtils oplogUtils) {
		super();
		this.oplogUtils = oplogUtils;
	}

	/**
	 * 返回true表示继续执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		oplogUtils.createFormRestPage(request, response);
		return true;			
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//if (logger.isDebugEnabled())logger.debug("请求后");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//if (logger.isDebugEnabled())logger.debug("请求结束后");

	}


}

