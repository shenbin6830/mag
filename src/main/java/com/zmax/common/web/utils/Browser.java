package com.zmax.common.web.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 用户浏览器
 * @author zmax
 *
 */
public class Browser {
	/**IE内核*/
	public boolean trident;
	/**opera内核*/
	public boolean presto;
	/**苹果、谷歌内核*/
	public boolean webKit;
	/**火狐内核*/
	public boolean gecko;
	/**移动终端*/
	public boolean mobile;
	/**ios终端*/
	public boolean ios;
	/**android终端或uc浏览器*/
	public boolean android;
	/**iPhone或者QQHD浏览器*/
	public boolean iPhone;
	/**iPad*/
	public boolean iPad;
	/**webApp程序*/
	public boolean webApp;
	/**
	 * 根据request 返回用户浏览器对象
	 * @param request
	 * @return
	 */
	public static Browser browserByUseragent(HttpServletRequest request){
		if(request==null)
			return new Browser();
		return browserByUseragent(request.getHeader("user-agent"));
	}
	/**
	 * 根据userAgent 返回用户浏览器对象
	 * @param u userAgent 通常是 request.getHeader("user-agent")
	 * @return
	 */
	public static Browser browserByUseragent(String u){
		Browser browser=new Browser();
		if(StringUtils.isBlank(u))
			return browser;
		browser.trident= u.indexOf("Trident") > -1; //IE内核
		browser.presto= u.indexOf("Presto") > -1; //opera内核
		browser.webKit= u.indexOf("AppleWebKit") > -1; //苹果、谷歌内核
		browser.gecko= u.indexOf("Gecko") > -1 && u.indexOf("KHTML") == -1; //火狐内核
		browser.mobile= regex("AppleWebKit.*Mobile.*",u);//!!u.match("AppleWebKit.*Mobile.*"); //是否为移动终端
		browser.ios= regex("i.* CPU.* Mac OS X.*",u);//!!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		browser.android= u.indexOf("Android") > -1 || u.indexOf("Linux") > -1; //android终端或uc浏览器
		browser.iPhone= u.indexOf("iPhone") > -1 ; //是否为iPhone或者QQHD浏览器
		browser.iPad= u.indexOf("iPad") > -1; //是否iPad
		browser.webApp= u.indexOf("Safari") == -1 ;//是否webApp程序，没有头部与底部
		return browser;
	}
	/**
	 * 正则匹配
	 * @param key 关键字
	 * @param src 原文
	 * @return
	 */
    public static boolean regex(String key,String src){  
        Pattern p =Pattern.compile(key,Pattern.MULTILINE);  
        Matcher m=p.matcher(src);  
        return m.find();  
    }
    
    
    
    
    @Override
	public String toString() {
		return "Browser [trident=" + trident + ", presto=" + presto
				+ ", webKit=" + webKit + ", gecko=" + gecko + ", mobile="
				+ mobile + ", ios=" + ios + ", android=" + android
				+ ", iPhone=" + iPhone + ", iPad=" + iPad + ", webApp="
				+ webApp + "]";
	}
    /*
	public static void main(String[] args) {
        String ie9    ="Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)";    
        String ie8    ="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322)";    
        String ie7    ="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2; .NET CLR 1.1.4322)";    
        String ie6    ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)";    
        String aoyou  ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; Maxthon 2.0)";    
        String qq     ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322) QQBrowser/6.8.10793.201";    
        String green  ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; GreenBrowser)";    
        String se360  ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; 360SE)";    
            
        String chrome ="Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/534.11 (KHTML, like Gecko) Chrome/9.0.570.0 Safari/534.11";                   
        String safari ="Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN) AppleWebKit/533.17.8 (KHTML, like Gecko) Version/5.0.1 Safari/533.17.8";    
        String fireFox="Mozilla/5.0 (Windows NT 5.2; rv:7.0.1) Gecko/20100101 Firefox/7.0.1";    
        String opera  ="Opera/9.80  (Windows NT 5.2; U; zh-cn) Presto/2.9.168 Version/11.51";    
        String other  ="(Windows NT 5.2; U; zh-cn) Presto/2.9.168 Version/11.51";
        String iphone ="Mozilla/5.0 (iPhone; U; CPU iPhone OS 5_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";    
        Browser b=Browser.browserByUseragent(iphone);
        System.out.println("b.toString()=" + b.toString());
    }*/

}
