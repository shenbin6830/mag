package com.zmax.mag.domain.bean.wxa;

public class AHandWaOrderQueryParamSigned {
	private String appid;		//32			NO	公众账号ID
	private String mch_id;		//32			NO	商户号
	private String transaction_id;		//32				微信订单号
	private String out_trade_no;		//32				商户订单号
	private String nonce_str;		//32			NO	随机字符串
	private String sign;		//32			NO	签名
	
	
	public AHandWaOrderQueryParamSigned(String appid, String mch_id,
			String transaction_id, String out_trade_no, String nonce_str,
			String sign) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.transaction_id = transaction_id;
		this.out_trade_no = out_trade_no;
		this.nonce_str = nonce_str;
		this.sign = sign;
	}


	public String getAppid() {
		return appid;
	}


	public void setAppid(String appid) {
		this.appid = appid;
	}


	public String getMch_id() {
		return mch_id;
	}


	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}


	public String getTransaction_id() {
		return transaction_id;
	}


	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}


	public String getOut_trade_no() {
		return out_trade_no;
	}


	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}


	public String getNonce_str() {
		return nonce_str;
	}


	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
	

}
