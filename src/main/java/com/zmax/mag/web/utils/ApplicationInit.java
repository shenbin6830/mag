package com.zmax.mag.web.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.ckfinder.connector.configuration.Constants;
import com.ckfinder.connector.configuration.ZresConfiguration;
import com.zmax.common.utils.string.UrlUtil;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.domain.conf.PropMy;
import com.zmax.mag.domain.conf.PropSys;
import com.zmax.mag.domain.conf.Rt;
import com.zmax.mag.web.controller.page.spec.SpecPubController;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

/**
 * 自下定义的初始化
 * 启动顺序见 1. WebStartListen
 * @author zmax
 *
 */
public class ApplicationInit implements ApplicationListener<ContextRefreshedEvent> {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	FreeMarkerConfigurer freeMarkerConfigurer=null;
//	FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean=null;
	PropMy propMy=null;
	PropSys propSys=null;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (logger.isDebugEnabled())
			logger.debug("[2]启动ApplicationInit.onApplicationEvent.............");
		try {
			propMy = event.getApplicationContext().getBean(PropMy.class);
			propSys = event.getApplicationContext().getBean(PropSys.class);
			logger.info("[2]参数：propMy=" + propMy.toString());
			logger.info("[2]参数：propSys=" + propSys.toString());
			freemarkerInit(event);
			messageSourceInit(event);
			SpecPubController specPubController = event.getApplicationContext().getBean(SpecPubController.class);
			specPubController.paraminit(null,null);
			//zres富文本
			ZresConfiguration.xmlpath=propMy.getHddir().replace("file://", "")+"/config/zres.xml";			
			Constants.BASE_DIR=propMy.getHddir().replace("file://", "")+"/static/userfiles";
			Constants.BASE_URL=propSys.getBase()+"/userfiles";
			logger.info("[2]zres 富文本 参数：=" + ZresConfiguration.xmlpath+",本地="+Constants.BASE_DIR+",网络路径url="+Constants.BASE_URL);
			if("zmaxdEFAULT".equals(Rt.runtime_MODE)){
				Rt.runtime_harddisk_rootpath_userupload=propMy.getHddir().replace("file://", "")+"/static";
				logger.info("[2.5]zmaxdEFAULT chage:Rt.runtime_harddisk_rootpath_userupload=" + Rt.runtime_harddisk_rootpath_userupload);
			}
			//自动增加
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FreeMarkerConfigurer getFreeMarkerConfigurer() {
		return freeMarkerConfigurer;
	}
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}
	/**
	 * freeMarker的初始化，放置公共数据
	 * @param event
	 * @throws Exception
	 */
	private void freemarkerInit(ContextRefreshedEvent event) throws Exception{
		this.freeMarkerConfigurer = event.getApplicationContext().getBean(FreeMarkerConfigurer.class);
		Configuration cfg = this.freeMarkerConfigurer.getConfiguration();

		cfg.setSharedVariable("author", propMy.getName());
		
		String u1=propSys.getBase();
		u1=(StringUtils.isBlank(u1) || "/".equals(u1))?"":u1;
		//相对URL-网站根目录，例："/aaa" 或者 ""
		cfg.setSharedVariable("base",u1);
		//相对URL-网站根目录，例："/aaa" 或者 ""
		cfg.setSharedVariable("baserest",u1+"/restpage");
		//绝对URL-网站根目录，例："http://bbb.com/aaa" 或者 "http://bbb.com"
		cfg.setSharedVariable("wbase",UrlUtil.urlAdd(propSys.getWebsite(),u1));
		//服务器根，也就是域名 ex.http://bbb.com
		cfg.setSharedVariable("hbase",UrlUtil.urlAdd(propSys.getWebsite(),""));
		cfg.setSharedVariable("res", propMy.getResbase());
		//资源管理器ck的根目录
		cfg.setSharedVariable("zres", u1);
		cfg.setSharedVariable("imgbase", propMy.getImgbase());
	}
	/**
	 * 国际化
	 * @param event
	 */
	private void messageSourceInit(ContextRefreshedEvent event){
		ReloadableResourceBundleMessageSource messageSource=event.getApplicationContext().getBean(ReloadableResourceBundleMessageSource.class);
		messageSource.setCacheSeconds(-1);
		messageSource.setBasenames("classpath:/languages/db/messages","classpath:/languages/global/messages");
	}
}