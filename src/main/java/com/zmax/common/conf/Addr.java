package com.zmax.common.conf;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个文件以及相应的Addr.xml由架构部门编写和分发
 * 服务器地址
 * i开头的是内网
 * o开头的是外网
 * @author zmax
 *
 */
public class Addr {
	/**后台登录地址*/
	private String backLoginUrl="http://bo.maykeys.com/login.html";
	/**后台登录地址*/
	public String getBackLoginUrl() {
		return backLoginUrl;
	}
	/**后台登录地址*/
	public void setBackLoginUrl(String backLoginUrl) {
		this.backLoginUrl = backLoginUrl;
	}

	/**其它服务的wbase 在spring init时被赋值*/
	private Map<String,String> wwbase=new HashMap<String, String>() ;
	/**其它服务的wbase 在spring init时被赋值*/
	public Map<String, String> getWwbase() {
		return wwbase;
	}
	/**其它服务的wbase 在spring init时被赋值*/
	public void setWwbase(Map<String, String> wwbase) {
		this.wwbase = wwbase;
	}
	@Override
	public String toString() {
		return "Addr [backLoginUrl=" + backLoginUrl + ", wwbase=" + wwbase
				+ "]";
	}
	
}
