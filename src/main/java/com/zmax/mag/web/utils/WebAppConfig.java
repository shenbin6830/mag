package com.zmax.mag.web.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zmax.common.utils.string.UrlUtil;
import com.zmax.mag.domain.conf.PropMy;
import com.zmax.mag.domain.conf.PropSys;
import com.zmax.mag.service.my.OplogService;
import com.zmax.mag.service.my.UserService;
import com.zmax.mag.service.spec.SpecUserService;
import com.zmax.mag.web.interceptor.RestInterceptor;
import com.zmax.mag.web.interceptor.RestpageInterceptor;
import com.zmax.mag.web.interceptor.UserInterceptor;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{	
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	PropSys propSys;
	@Autowired
	PropMy propMy;
	@Autowired
	UserService userService;	
	@Autowired
	SpecUserService specUserService;
	@Autowired
	OplogUtils oplogUtils;	
     /**
     * 配置拦截器
     * @author lance
     * @param registry
     */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截		
		String interceptorUrl="";
		//interceptorUrl=UrlUtil.urlAdd(propSys.getBase(), "/user")+"/**";
		interceptorUrl="/user/**";
    	registry.addInterceptor(new UserInterceptor(propMy, propSys, userService, specUserService)).addPathPatterns(interceptorUrl);
    	if (logger.isDebugEnabled())
			logger.debug("拦截器,interceptorUrl=" + interceptorUrl);
		interceptorUrl="/restpage/**";
    	registry.addInterceptor(new RestpageInterceptor(oplogUtils)).addPathPatterns(interceptorUrl);
    	if (logger.isDebugEnabled())
			logger.debug("拦截器,interceptorUrl=" + interceptorUrl);
    	interceptorUrl="/rest/**";
    	registry.addInterceptor(new RestInterceptor()).addPathPatterns(interceptorUrl);
    	if (logger.isDebugEnabled())
			logger.debug("拦截器,interceptorUrl=" + interceptorUrl);
    	
	}
}
