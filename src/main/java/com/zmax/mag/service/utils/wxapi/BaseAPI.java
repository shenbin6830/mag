package com.zmax.mag.service.utils.wxapi;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
/**
 * 接口
 * @author zmax
 *
 */
public abstract class BaseAPI {
	public static final String CHARSET="UTF-8";
	protected static final String BASE_URI = "https://api.weixin.qq.com";
	protected static final String MEDIA_URI = "http://file.api.weixin.qq.com";
	protected static final String MP_URI = "https://mp.weixin.qq.com";
	protected static final String MCH_URI = "https://api.mch.weixin.qq.com";

	protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_JSON.toString());
	protected static Header xmlHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_XML.toString());
}
