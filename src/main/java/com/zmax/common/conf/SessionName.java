/**
 * 
 */
package com.zmax.common.conf;

/**
 * @author zmax
 *
 */
public class SessionName {


	/**用户对象，由于freemarker页面上写死了，这个只能叫uuser*/
	public static final String user="uuser"; 
	/**用户类型*/
	public static final String usertype="usertype"; 
	/**管理员对象*/
	public static final String aadmin="aadmin";
	/**站点操作员对象*/
	public static final String site="site";
	/**会员对象*/
	public static final String member="member";
	/**企业对象*/
	public static final String company="company";
	
	public static final String userid="userId"; 

	public static final String adminid="adminId"; 	

	public static final String siteid="siteId"; 
	
	//是否为手机用户在访问，默认null为网站，不空为手机
	public static final String ismobile="ismobile";
	
	//以下为zres使用
	/**SESSION之用户类型，在config.xml中配置的 admin or user*/
	public final static  String ZRES_SESSIONUSERTYPE="CKFinder_UserRole";
	/**Zres SESSION之用户ID，此SESSION中的值会将用户指向/usefiles/users/$[userid]/		*/
	public final static  String ZRES_SESSIONUSERID="zresuserId";
	
	//////以下为微信系列使用
	/**其它应用来访问微信进行中转时，保存下gobackUrl，做为临时变量，可以让跳转参数再少一些，经过若干跳转完成操作后，让用户跳回此url*/
	public static final String wxAppGobackUrl="wxAppGobackUrl";
	/**微信openid*/
	public static final String openId="OPENID";
	/**微信父openid*/
	public static final String popenId="POPENID";
	/**访问者之微信用户*/
	public static final String WxouserVisiter="WxouserVisiter";

}
