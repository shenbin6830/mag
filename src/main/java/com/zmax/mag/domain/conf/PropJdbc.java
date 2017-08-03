package com.zmax.mag.domain.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.datasource")
public class PropJdbc {

	private String url="jdbc:mysql://localhost:3306/yijian?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	private String username="root";
	private String password="root123";
	private String driverClassName="com.mysql.jdbc.Driver";
	
	private int initialSize=1;
	private int minIdle=1;
	private int maxActive=1;
	private int maxWait=1;
	private int timeBetweenEvictionRunsMillis=1;
	private int minEvictableIdleTimeMillis=1;
	
	
	
	public PropJdbc() {
		//System.out.println("PropJdbc!!!!!!!!!!!!");
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public int getInitialSize() {
		return initialSize;
	}
	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}
	public int getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	public int getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	public int getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}
	public int getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}
	public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}
	public int getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}
	public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}
	
	
	
	
}
