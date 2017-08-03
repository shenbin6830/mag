package com.zmax.common.utils.string;

import org.apache.commons.lang.StringUtils;

public class UrlUtil {
	/**
	 * 两个相对值的URL相加
	 * ex. /a + /b = /a/b
	 * ex. "" + /b = /b
	 * ex. / + /b = /b
	 * ex. / + / =""
	 * @param u1
	 * @param u2
	 * @return 全部为空，返回 ""
	 */
	public static String urlAdd(String u1,String u2){
		u1=(StringUtils.isBlank(u1) || "/".equals(u1))?"":u1;
		u2=(StringUtils.isBlank(u2) || "/".equals(u2))?"":u2;
		String ret=u1+u2;
		ret=ret.replace("//", "/").replace("//", "/");
		return ret;
	}
	/**
	 * 替换url中某参数的值，产生新的URL
	 * ex. a?k1=v1  + k1,v2  => a?k1=v2
	 * ex. a?k1=v1  + k2,v2  => a?k1=v1&k2=v2
	 * 
	 * @param url   "http://so1.maykeys.com/sowww/index.html?shopId=166&popenid=166#/app/home"
	 * @param paramName  "openid"
	 * @param paramValue  "aaaa"
	 * @return
	 */
	public static String changeURLParameter(String url, String paramName, String paramValue) {
		if (StringUtils.isBlank(paramName) || StringUtils.isBlank(paramValue)) {
			return url;
		}
		// ? #拆开
		// 先把?拆开 a?b#c ->{a,b,c}
		String a = "";
		String b = "";
		String c = "";

		String[] abcArray = url.split("[?]");
		a = abcArray[0];
		if (abcArray.length > 1) {
			String bc = abcArray[1];
			String[] bcArray = bc.split("#");
			b = bcArray[0];
			if (bcArray.length > 0) {
				c = bcArray[1];
			}
		}
		if (StringUtils.isBlank(b)) {
			return url;
		}

		// 用&拆p, p1=1&p2=2 ，{p1=1,p2=2}
		String[] bArray = b.split("&");
		String newb = "";
		boolean found = false;
		for (int i = 0; i < bArray.length; i++) {
			String bi = bArray[i];
			if (StringUtils.isBlank(bi))
				continue;
			String key = "";
			String value = "";

			String[] biArray = bi.split("="); // {p1,1}
			key = biArray[0];
			if (biArray.length > 1)
				value = biArray[1];

			if (key.equals(paramName)) {
				found = true;
				if (StringUtils.isNotBlank(paramValue)) {
					newb = newb + "&" + key + "=" + paramValue;
				} else {
					continue;
				}
			} else {
				newb = newb + "&" + key + "=" + value;
			}
		}
		// 如果没找到，加上
		if (!found && StringUtils.isNotBlank(paramValue)) {
			newb = newb + "&" + paramName + "=" + paramValue;
		}
		if (StringUtils.isNotBlank(newb))
			a = a + "?" + newb;
		if (StringUtils.isNotBlank(c))
			a = a + "#" + c;
		return a;
	}
	/**
	 * 从url中获取域名
	 * http://test2.aaa.com/bo/pub/wx/support/1.html --> http://test2.maykeys.com
	 * @param url
	 * @return http://test2.aaa.com
	 */
	public static String domainFromUrl(String url){
		String ret=url;
		if(StringUtils.countMatches(url, "/")<3)
			return url;
		int atg=0;
		if(url.indexOf("http://")==0)
			atg=url.indexOf("/", "http://".length());
		if(url.indexOf("https://")==0)
			atg=url.indexOf("/", "https://".length());
		if(atg!=0)
			ret=url.substring(0,atg);
		return ret;
	}

	/**
	 * 从url地址中根据key获取value
	 * 具体形如
	 * @param url  "http://so1.maykeys.com/sowww/index.html?shopId=166&popenid=166#/app/home"
	 * @param paramName "shopid"
	 * @return  166
	 */
	public static String takeURLParamValue(String url, String paramName) {
		if(StringUtils.isBlank(paramName)){
			return null;
		}
		// ? #拆开
		// 先把?拆开 a?b#c ->{a,b,c}
		String b = "";
		String[] abcArray = url.split("[?]");
		if (abcArray.length > 1) {
			String bc = abcArray[1];
			String[] bcArray = bc.split("#");
			b = bcArray[0];
		}
		if (StringUtils.isBlank(b)) {
			return null;
		}

		// 用&拆p, p1=1&p2=2 ，{p1=1,p2=2}
		String[] bArray = b.split("&");
		for (int i = 0; i < bArray.length; i++) {
			String bi = bArray[i];
			if (StringUtils.isBlank(bi))
				continue;
			String key = "";
			String value = "";
			String[] biArray = bi.split("="); // {p1,1}
			key = biArray[0];
			if (biArray.length > 1)
				value = biArray[1];
			if (key.equals(paramName)) {
				return value;
			} 
		}
		return null;
	}
}
