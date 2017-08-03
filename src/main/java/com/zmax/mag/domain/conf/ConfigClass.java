package com.zmax.mag.domain.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 这个用于加载XML的类
 * classpath路径：locations={"classpath:application-bean1.xml","classpath:application-bean2.xml"}
 * file路径： locations ={"file:d:/test/application-bean1.xml"};
 * @author zmax
 *
 */
@Configuration
@ImportResource(locations={"classpath:jcaptcha.xml"})
public class ConfigClass {

}
