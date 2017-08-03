/*
 * zmax 2015
 * 
 */


//  
package com.zmax.mag.domain.bean.wxa;

import com.alibaba.fastjson.annotation.*;

import org.apache.commons.lang.builder.*;

import java.text.*;
import java.util.*;

/**用于页面脚本的JsSDK微信配置
 * @author zmax
 *
 */
public class WxConfig {
	private String appId=""; // 必填，公众号的唯一标识
	private long timestamp=0L; // 必填，生成签名的时间戳
	private String noncestr=""; // 必填，生成签名的随机串
	private String signature="";// 必填，签名，见附录1
	private String jsapi_ticket=""; //ticket
	private String url=""; //当前网页的URL，不包含#及其后面部分
	public WxConfig(){}

	public WxConfig(String appId, long timestamp, String noncestr,
			String signature, String jsapi_ticket, String url) {
		super();
		this.appId = appId;
		this.timestamp = timestamp;
		this.noncestr = noncestr;
		this.signature = signature;
		this.jsapi_ticket = jsapi_ticket;
		this.url = url;
	}



	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getJsapi_ticket() {
		return jsapi_ticket;
	}

	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "WxConfig [appId=" + appId + ", timestamp=" + timestamp
				+ ", noncestr=" + noncestr + ", signature=" + signature
				+ ", jsapi_ticket=" + jsapi_ticket + ", url=" + url + "]";
	}

	
}
