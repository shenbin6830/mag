package com.zmax.mag.domain.conf;
/**
 * 这里是代码用的静态变量，或者基本上不会改变的常量
 * @author Administrator
 *
 */
public class Conf {
	/////////数据库关键字系列
	/**微信共享图文信息之用户关注*/
	public final static String DBKW_WAENTITYARTICLE_subscribe="subscribe";	
	/**微信共享图文信息之商家指令*/
	public final static String DBKW_WAENTITYARTICLE_SJ="sj";	
	
	/////////支付系列
	//以下订单前缀 如：AA:商品订单_支付宝,AB商品订单_微信,BA:充值订单_支付宝,BB充值订单_微信,TA,TB测试
	/**支付ID号前缀：测试支付宝*/
	public final static String PAY_FORHEAD_ALIPAY_TEST="TA";
	/**支付ID号前缀：订单之支付宝*/
	public final static String PAY_FORHEAD_ALIPAY_ORDERR="AA";
	/**支付ID号前缀：测试微信*/
	public final static String PAY_FORHEAD_WX_TEST="TB";	
	/**支付ID号前缀：订单之微信*/
	public final static String PAY_FORHEAD_WX_ORDERR="AB";
	/**微信问题支付*/
	public final static String PAY_FORQUESTION_WX="WQUT";
	/**微信问题观看会员支付*/
	public final static String PAY_FORQUESTIONMEMBER_WX="WQUM";
	/**微信抢答支付*/
	public final static String PAY_FORQUICK_WX="WQUK";
	/**微信抢答观看会员支付*/
	public final static String PAY_FORQUICKMEMBER_WX="WQKM";
	/**阿里问题支付*/
	public final static String PAY_FORQUESTION_ALI="AQUT";
	/**阿里问题观看会员支付*/
	public final static String PAY_FORQUESTIONMEMBER_ALI="AQUM";
	/**阿里抢答支付*/
	public final static String PAY_FORQUICK_ALI="AQUK";
	/**阿里抢答观看会员支付*/
	public final static String PAY_FORQUICKMEMBER_ALI="AQKM";	
	////resuful的CMD指令
	/**usa的query*/
	public final static String REST_CMD_USA="USA";	

	/**测试手机开头*/
	public static String TESTUSER="101234";
	
	/**文件上传最终路径,ex./userfiles/users/{0}/files/{1} ,0=user.id,1=yyyyMM 硬盘上在/static 目录下*/
	public static String fileUuploadPath="/userfiles/users/{0,number,#}/files/{1}";
	/**资源路径名*/
	public static final String url_res="/res";
	/**自己的商户ID*/
	public static final int BIZID=1;
	/**JAVA代码用的网站根目录 ex. /xx 或 "" */
	public static final String JAVABASE="/mag";
	/**一页多少数据*/
	public static final int PAGE_SIZE=20;
}
