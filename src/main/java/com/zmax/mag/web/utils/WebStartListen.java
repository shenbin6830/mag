package com.zmax.mag.web.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zmax.mag.domain.conf.PropMy;
import com.zmax.mag.domain.conf.Rt;
/**
 * 网站启动监听器
 * 启动顺序
 * 1. WebStartListen
 * 1.5 service.utils.MyEnv
 * 1.6...9 web.utils.xx.yy [at]Configuration [at]Bean
 * 2. ApplicationInit
 * @author Administrator
 *
 */
@WebListener
public class WebStartListen implements ServletContextListener {
	private final Log logger = LogFactory.getLog(getClass());
	public static ServletContext servletContext=null;
	/**CLASS的本地硬盘路径*/
	public static String webinfclasses="";
	@Autowired
	PropMy propMy;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("web stop!");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		logger.info("[1]启动WebStartListen,hi:" +propMy.getName());
		// SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())
		.getAutowireCapableBeanFactory().autowireBean(this);
		servletContext=sce.getServletContext();
		webinfclasses=servletContext.getRealPath("/");
		//运行根目录之硬盘路径，linux下是/opt/xxx/static ?
		webinfclasses=webinfclasses.replace("\\", "/");
		Rt.runtime_harddisk_rootpath=webinfclasses;
		Rt.runtime_harddisk_rootpath_userupload=Rt.runtime_harddisk_rootpath;
		Rt.runtime_MODE=propMy.getName();
		showpath();
		logger.info("[1]Rt.runtime_MODE="+Rt.runtime_MODE);
		logger.info("[1]Rt.runtime_harddisk_rootpath="+Rt.runtime_harddisk_rootpath);
		logger.info("[1]Rt.runtime_harddisk_rootpath_userupload="+Rt.runtime_harddisk_rootpath_userupload);
		logger.info("[1]启动WebStartListen完成!");
	}

	public void showpath(){
		if (logger.isDebugEnabled()){
			//以下linux都是null
			/*
			//1.得到的是当前类FileTest.class文件的URI目录。不包括自己！
			logger.debug("class.getResource.space="+FilePath.class.getResource(""));
			//2.得到的是当前的classPath的绝对URI路径。
			logger.debug("class.getResource.root="+FilePath.class.getResource("/")); // Class文件所在路径
			//3.得到的也是当前ClassPath的绝对URI路径。
			logger.debug("getContextClassLoader().getResource="+Thread.currentThread().getContextClassLoader().getResource(""));
			//4.得到的也是当前ClassPath的绝对URI路径。
			logger.debug("getClassLoader.getResource="+FilePath.class.getClassLoader().getResource(""));
			//5.得到的也是当前ClassPath的绝对URI路径。
			logger.debug("getSystemResource="+ClassLoader.getSystemResource(""));	 
			//人为得到
			logger.debug("getAbsolutePath="+new File("/").getAbsolutePath());
			*/
			//以上linux都是null
			// linux = /opt/xxx ,eclipse = D:\z_java\xxx\xxx\trunk ,win =D:\opt\xxx
			logger.debug("[1]user.dir="+System.getProperty("user.dir"));			
		}
	}
}