package com.zmax.mag.domain.bean.wxa;
/**
 * 手工写的
 * 微信支付回调通知，用于签名与验证
 * @author zmax
 *
 */
public class AHandWaGeneralNoticeRetSigned {
	private String return_code;		//16			NO	返回状态码
	private String return_msg;		//128			NO	返回信息
	private String appid;		//32			NO	公众账号ID
	private String mch_id;		//32			NO	商户号
	private String device_info;		//32				设备号
	private String nonce_str;		//32			NO	随机字符串
	private String sign;	//32			NO	签名
	private String result_code;		//16			NO	业务结果
	private String err_code;	//32				错误代码
	private String err_code_des;		//128				错误代码描述
	private String openid;		//128			NO	用户标识
	private String is_subscribe;	//1			NO	是否关注公众账号
	private String trade_type;		//16			NO	交易类型
	private String bank_type;		//16			NO	付款银行
	private Integer total_fee;	 	//11			NO	总金额
	private String fee_type;		//8				货币种类
	private Integer cash_fee;	 	//11			NO	现金支付金额
	private String cash_fee_type;		//16				现金支付货币类型
	private Integer coupon_fee;	 	//11				代金券或立减优惠金额
	private Integer coupon_count;	 	//11				代金券或立减优惠使用数量
//	private String coupon_batch_id_$n;			//			代金券或立减优惠批次ID
//	private String coupon_id_$n;				//	代金券或立减优惠ID
//	private int coupon_fee_$n;	 	//11				单个代金券或立减优惠支付金额
	private String transaction_id;		//32			NO	微信支付订单号
	private String out_trade_no;		//32			NO	商户订单号
	private String attach;		//128				商家数据包
	private String time_end;		//14			NO	支付完成时间
	public AHandWaGeneralNoticeRetSigned(String return_code, String return_msg,
			String appid, String mch_id, String device_info, String nonce_str,
			String sign, String result_code, String err_code,
			String err_code_des, String openid, String is_subscribe,
			String trade_type, String bank_type, Integer total_fee,
			String fee_type, Integer cash_fee, String cash_fee_type,
			Integer coupon_fee, Integer coupon_count, String transaction_id,
			String out_trade_no, String attach, String time_end) {
		super();
		this.return_code = return_code;
		this.return_msg = return_msg;
		this.appid = appid;
		this.mch_id = mch_id;
		this.device_info = device_info;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.result_code = result_code;
		this.err_code = err_code;
		this.err_code_des = err_code_des;
		this.openid = openid;
		this.is_subscribe = is_subscribe;
		this.trade_type = trade_type;
		this.bank_type = bank_type;
		this.total_fee = total_fee;
		this.fee_type = fee_type;
		this.cash_fee = cash_fee;
		this.cash_fee_type = cash_fee_type;
		this.coupon_fee = coupon_fee;
		this.coupon_count = coupon_count;
		this.transaction_id = transaction_id;
		this.out_trade_no = out_trade_no;
		this.attach = attach;
		this.time_end = time_end;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
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
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
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
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public Integer getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	public Integer getCoupon_fee() {
		return coupon_fee;
	}
	public void setCoupon_fee(Integer coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	public Integer getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(Integer coupon_count) {
		this.coupon_count = coupon_count;
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
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	
	
	
}
