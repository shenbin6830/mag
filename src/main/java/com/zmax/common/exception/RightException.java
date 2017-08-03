package com.zmax.common.exception;
/**
 * 一个正确的异常，其实是没错，只是为了让代码不运行下去。
 * @author zmax
 *
 */
public class RightException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RightException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RightException(String message) {
		super(message);
	}
}
