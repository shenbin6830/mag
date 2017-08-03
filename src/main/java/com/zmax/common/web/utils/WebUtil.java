package com.zmax.common.web.utils;

import javax.servlet.http.HttpServletRequest;
/**
 * 使用在网站的工具
 * @author zmax
 *
 */
public class WebUtil {

	/**
	 * 从request中取出url
	 * 如果有参数，就是http://aaa.bbb.com/ddd/eee?fff&ggg
	 * 如果没参数，就是http://aaa.bbb.com/ddd/eee
	 * @param request
	 * @return
	 */
	public static String urlAndParamsFromRequest(HttpServletRequest request){
		return (null==request.getQueryString())?request.getRequestURL().toString():request.getRequestURL().toString()+ "?" + (request.getQueryString()); 
	}

}
