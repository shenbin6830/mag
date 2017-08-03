package com.zmax.mag.service.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zmax.mag.domain.conf.Rt;

/**
 * 环境变量工具
 * 系统启动时，setEnvironment会被执行 时间在1.5
 * @author Administrator
 *
 */
@Service
public class MyEnv implements EnvironmentAware{
	private final Log logger = LogFactory.getLog(getClass());

	private RelaxedPropertyResolver propertyResolver;

	private Environment _environment;
	
	@Override
	public void setEnvironment(Environment environment) {
		_environment=environment;
		logger.info("[1.5]JAVA_HOME="+environment.getProperty("JAVA_HOME"));
		logger.info("[1.5]spring.profiles.active="+environment.getProperty("spring.profiles.active"));
		logger.info("[1.5]spring.datasource.url="+environment.getProperty("spring.datasource.url"));
		Rt.runtime_hddir=environment.getProperty("prop.my.hddir").replace("file://", "");
		logger.info("[1.5]Rt.runtime_hddir="+Rt.runtime_hddir);
		propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
		String url = propertyResolver.getProperty("url");
		logger.info("[1.5]RelaxedPropertyResolver_spring.datasource.url="+url);
		
	}

	public Environment getEnvironment() {
		return _environment;
	} 

}
