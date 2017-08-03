package com.zmax.common.exception;
/**
 * 需要让用户走微信通道，https://open.weixin.qq.com/connect/oauth2/authorize?appid=
 * @author zmax
 *
 */
public class OpenWeixinQQException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OpenWeixinQQException() {
		super("请从微信进入");
		// TODO Auto-generated constructor stub
	}
	public OpenWeixinQQException(String message) {
		super(message);
	}
}
