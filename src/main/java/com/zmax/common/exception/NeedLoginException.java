package com.zmax.common.exception;
/**
 * 需要登录
 * @author zmax
 *
 */
public class NeedLoginException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NeedLoginException() {
		super("没有登录");
		// TODO Auto-generated constructor stub
	}
	public NeedLoginException(String message) {
		super(message);
	}
}
