package com.zmax.mag.domain.conf;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration implements EnvironmentAware {

	@Autowired
	PropJdbc propJdbc;
	
    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }
    
    @Bean(destroyMethod = "close", initMethod = "init")
    public DataSource writeDataSource() {
        System.out.println("注入druid！！！");
        
        DruidDataSource datasource = new DruidDataSource();
        writeByFile(datasource);
        //writeByBean(datasource);
        return datasource;
    }
    /**
     * Bean注入方式
     * @param datasource
     */
    private void writeByBean(DruidDataSource datasource){
        datasource.setUrl(propJdbc.getUrl());
        datasource.setUsername(propJdbc.getUsername());
        datasource.setPassword(propJdbc.getPassword());
        datasource.setDriverClassName(propJdbc.getDriverClassName());
        datasource.setInitialSize(propJdbc.getInitialSize());
        datasource.setMinIdle(propJdbc.getMinIdle());
        datasource.setMaxWait(propJdbc.getMaxWait());
        datasource.setMaxActive(propJdbc.getMaxActive());
        datasource.setMinEvictableIdleTimeMillis(propJdbc.getMinEvictableIdleTimeMillis());
    }
    /**
     * Prop文件注入
     * @param datasource
     */
    private void writeByFile(DruidDataSource datasource){
    	String classname=propertyResolver.getProperty("driver-class-name");
    	System.out.println("driver-class-name=" + classname);
    	classname=propertyResolver.getProperty("driverClassName");
    	System.out.println("driverClassName=" + classname);
    	
        datasource.setUrl(propertyResolver.getProperty("url"));
        datasource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        datasource.setUsername(propertyResolver.getProperty("username"));
        datasource.setPassword(propertyResolver.getProperty("password"));
        datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initial-size")));
        datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("min-idle")));
        datasource.setMaxWait(Long.valueOf(propertyResolver.getProperty("max-wait")));
        datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("max-active")));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("min-evictable-idle-time-millis")));

        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}