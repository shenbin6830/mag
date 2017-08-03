/*
 * zmax 2015
 * 
 */


//  
package com.zmax.mag.domain.bean.wxa;




/**
 * 手工写的
 * 支付需要的参数，用于签名与验证
 * @author zmax
 * @version 1.0
 * @since 2015年06月
 */

public class AHandWaUnifiedPaymentParamSigned{
	String 	appid	;//		32		NO	公众账号ID
	String 	attach	;//		127			附加数据
	String 	body	;//		32		NO	商品描述
	String 	detail	;//		1000			商品详情
	String 	device_info	;//		32			设备号
	String 	fee_type	;//		16	CNY		货币类型
	String 	goods_tag	;//		32			商品标记
	String 	mch_id	;//		32		NO	商户号
	String 	nonce_str	;//		32		NO	随机字符串
	String 	notify_url	;//		256		NO	通知地址
	String 	openid	;//		128			用户标识
	String 	out_trade_no	;//		32		NO	商户订单号
	String 	product_id	;//		32			商品ID
	String 	sign	;//		32		NO	签名
	String 	spbill_create_ip	;//		16		NO	终端IP
	String 	time_expire	;//		14			交易结束时间
	String 	time_start	;//		14			交易起始时间
	int	total_fee	;//	int	11		NO	总金额
	String 	trade_type	;//		16		NO	交易类型
	public AHandWaUnifiedPaymentParamSigned(String appid, String attach,
			String body, String detail, String device_info, String fee_type,
			String goods_tag, String mch_id, String nonce_str,
			String notify_url, String openid, String out_trade_no,
			String product_id, String sign, String spbill_create_ip,
			String time_expire, String time_start, int total_fee,
			String trade_type) {
		super();
		this.appid = appid;
		this.attach = attach;
		this.body = body;
		this.detail = detail;
		this.device_info = device_info;
		this.fee_type = fee_type;
		this.goods_tag = goods_tag;
		this.mch_id = mch_id;
		this.nonce_str = nonce_str;
		this.notify_url = notify_url;
		this.openid = openid;
		this.out_trade_no = out_trade_no;
		this.product_id = product_id;
		this.sign = sign;
		this.spbill_create_ip = spbill_create_ip;
		this.time_expire = time_expire;
		this.time_start = time_start;
		this.total_fee = total_fee;
		this.trade_type = trade_type;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}


}
