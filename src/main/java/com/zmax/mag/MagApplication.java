package com.zmax.mag;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zmax.mag.web.utils.ApplicationInit;


@SpringBootApplication
@ServletComponentScan
@EnableScheduling
//@EnableCaching
public class MagApplication extends WebMvcConfigurerAdapter{
	/**自定义启动者*/
	public static ApplicationInit applicationInit=null;

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		//为了治 ajax 406
		configurer.favorPathExtension(false);
	}


	public static void main(String[] args) {
		SpringApplication springApplication =new SpringApplication(MagApplication.class);
		applicationInit=new ApplicationInit();
		springApplication.addListeners(applicationInit);
		springApplication.run(args);
	}
	/**
	 * 注入sessionfatory
	 * @return
	 */
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		System.out.println("HibernateJpaSessionFactoryBean sessionFactory()");
		return new HibernateJpaSessionFactoryBean();
	}
	/**
	 * 注入messageSource，国际化用
	 * @return
	 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		System.out.println("ReloadableResourceBundleMessageSource messageSource()");
		return new ReloadableResourceBundleMessageSource();
	}
	/**
	 * 使用FastJson替换原来的Json解析器，测试@JSONField(serialize=false)
	 * @return
	 */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
       FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
       FastJsonConfig fastJsonConfig = new FastJsonConfig();
       fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
       fastConverter.setFastJsonConfig(fastJsonConfig);
       HttpMessageConverter<?> converter = fastConverter;
       return new HttpMessageConverters(converter);

    }

}
