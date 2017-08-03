package com.zmax.mag.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.zmax.common.conf.SessionName;
import com.zmax.common.utils.easyui.Json;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.conf.PropMy;
import com.zmax.mag.domain.conf.PropSys;
import com.zmax.mag.service.my.UserService;
import com.zmax.mag.service.spec.SpecUserService;
import com.zmax.mag.web.controller.page.base.BaseController;
/**
 * 用户拦截器
 * @author Administrator
 *
 */
//@Configuration
public class UserInterceptor implements HandlerInterceptor {
	private final Log logger = LogFactory.getLog(getClass());
	PropMy propMy;
	PropSys propSys;
	UserService userService;	
	SpecUserService specUserService;
	
	public UserInterceptor(PropMy propMy, PropSys propSys,
			UserService userService,SpecUserService specUserService) {
		super();
		this.propMy = propMy;
		this.propSys = propSys;
		this.userService = userService;
		this.specUserService = specUserService;
	}

	/**
	 * 返回true表示继续执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//if (logger.isDebugEnabled())logger.debug("请求前");
		//if (logger.isDebugEnabled())logger.debug("权限预处理");
		String uri = getURI(request);
		// 不在验证的范围内
		if (exclude(uri)) {
			return true;
		}
		String loginurl=propMy.getLoginurl();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute(SessionName.user);
		// 用户为null跳转到登陆页面
		if (user == null) {
			if(propSys.getDevmode()<1){
				response.sendRedirect(loginurl);
				return false;
			}else{
				user=userService.get(null,propSys.getDevmode());
				specUserService.calcuUserAfterLogin(user, true,false);
				session.setAttribute(SessionName.user, user);
				return true;
			}
		}
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
	/**
	 * 获得第二个路径分隔符的位置/zmax-zcms/user/index.do=>/user/index.do
	 * 
	 * @param request
	 * @throws IllegalStateException
	 *             访问路径错误，没有二个'/'
	 */
	private static String getURI(HttpServletRequest request)
			throws IllegalStateException {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request); //    /zmax-zcms/user/index.do
		String ctxPath = helper.getOriginatingContextPath(request);  //   /zmax-zcms
		int start = 0, i = 0, count = 1;
		if (!StringUtils.isBlank(ctxPath)) {
			count++;
		}
		while (i < count && start != -1) {
			start = uri.indexOf('/', start + 1);
			i++;
		}
		if (start <= 0) {
			throw new IllegalStateException(
					"user access path not like '/user/some/...' pattern: "
							+ uri);
		}
		return uri.substring(start);
	}
	/**
	 * 是否是排除路径，是表示无需权限 
	 * @param uri
	 * @return
	 */
	private boolean exclude(String uri) {
		if(propMy==null || propMy.getLoginignor() ==null)
			return true;
		List<String> listIgnor=propMy.getLoginignor();
		for (String string : listIgnor) {
			if (string.equals(uri)) {
				return true;
			}
		}
		return false;
	}
}

