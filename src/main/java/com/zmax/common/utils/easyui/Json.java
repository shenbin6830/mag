package com.zmax.common.utils.easyui;

/**
 * 
 * JSON模型
 * 
 * 用户后台向前台返回的JSON对象
 * 
 * @author zmax
 * 
 */
public class Json implements java.io.Serializable {

	private boolean success = false;

	private String msg = "";

	private Object obj = null;
	public Json() {
	}
	public Json(boolean success) {
		super();
		this.success = success;
	}	
	public Json(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public Json(boolean success, String msg, Object obj) {
		super();
		this.success = success;
		this.msg = msg;
		this.obj = obj;
	}
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
