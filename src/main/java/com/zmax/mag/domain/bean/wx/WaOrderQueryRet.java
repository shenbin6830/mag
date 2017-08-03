/*
 * zmax 
 * 
 */


//  
package com.zmax.mag.domain.bean.wx;



import java.text.*;
import java.util.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.mag.domain.bean.base.BaseEntity;

import javax.xml.bind.annotation.*;


import com.zmax.mag.domain.bean.*;


/**
 * 支付需要的参数
 * @author zmax
 * @version 1.0
 * @since 
 */

@XmlRootElement(name="xml")
public class WaOrderQueryRet extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "支付需要的参数";

	//date formats
	
	//columns START
	/**返回状态码 String  SUCCESS/FAIL */
	
	
	private String returnCode;
	/**返回信息 String  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 */
	
	
	private String returnMsg;
	/**微信订单号 String  微信的订单号，优先使用 */
	
	
	private String appid;
	/**商户订单号 String  商户系统内部的订单号，当没提供transaction_id时需要传这个。 */
	
	
	private String mchId;
	/**随机字符串 String  随机字符串，不长于32位。 */
	
	
	private String nonceStr;
	/**签名 String  签名 */
	
	
	private String sign;
	/**业务结果 String   */
	
	
	private String resultCode;
	/**错误代码 String   */
	
	
	private String errCode;
	/**错误代码描述 String   */
	
	
	private String errCodeDes;
	/**设备号 String  微信支付分配的终端设备号 */
	
	
	private String deviceInfo;
	/**用户标识 String  用户在商户appid下的唯一标识 */
	
	
	private String openid;
	/**是否关注公众账号 String  用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 */
	
	
	private String isSubscribe;
	/**交易类型 String  调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY， */
	
	
	private String tradeType;
	/**交易状态 String  七种状态，详情见卡妨碍文档 */
	
	
	private String tradeState;
	/**付款银行 String  银行类型，采用字符串类型的银行标识 */
	
	
	private String bankType;
	/**总金额 Integer  订单总金额，单位为分 */
	
	
	private Integer totalFee;
	/**货币种类 String default=CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY */
	
	
	private String feeType;
	/**现金支付金额 Integer  现金支付金额订单现金支付金额 */
	
	
	private Integer cashFee;
	/**现金支付货币类型 String default=CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， */
	
	
	private String cashFeeType;
	/**代金券或立减优惠金额 Integer  “代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额， */
	
	
	private Integer couponFee;
	/**代金券或立减优惠使用数量 Integer  代金券或立减优惠使用数量 */
	
	
	private Integer couponCount;
	/**代金券或立减优惠批次ID String  代金券或立减优惠批次ID ,$n为下标，从0开始编号 */
	
	
	private String couponBatchId;
	/**代金券或立减优惠ID String  代金券或立减优惠ID, $n为下标，从0开始编号 */
	
	
	private String couponId;
	/**微信支付订单号 String  微信支付订单号 */
	
	
	private String transactionId;
	/**商户订单号 String  商户系统的订单号，与请求一致。 */
	
	
	private String outTradeNo;
	/**商家数据包 String  商家数据包，原样返回 */
	
	
	private String attach;
	/**支付完成时间 String  订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 */
	
	
	private String timeEnd;
	/**交易状态描述 String  对当前查询订单状态的描述和下一步操作的指引 */
	
	
	private String tradeStateDesc;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaOrderQueryRet _this){
		_this.feeType="CNY";
		_this.cashFeeType="CNY";
	}
	public WaOrderQueryRet(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param returnCode String 返回状态码  SUCCESS/FAIL 
	 * @param returnMsg String 返回信息  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 
	 * @param appid String 微信订单号  微信的订单号，优先使用 
	 * @param mchId String 商户订单号  商户系统内部的订单号，当没提供transaction_id时需要传这个。 
	 * @param nonceStr String 随机字符串  随机字符串，不长于32位。 
	 * @param sign String 签名  签名 
	 * @param resultCode String 业务结果   
	 * @param errCode String 错误代码   
	 * @param errCodeDes String 错误代码描述   
	 * @param deviceInfo String 设备号  微信支付分配的终端设备号 
	 * @param openid String 用户标识  用户在商户appid下的唯一标识 
	 * @param isSubscribe String 是否关注公众账号  用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 
	 * @param tradeType String 交易类型  调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY， 
	 * @param tradeState String 交易状态  七种状态，详情见卡妨碍文档 
	 * @param bankType String 付款银行  银行类型，采用字符串类型的银行标识 
	 * @param totalFee Integer 总金额  订单总金额，单位为分 
	 * @param feeType String 货币种类 default=CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY 
	 * @param cashFee Integer 现金支付金额  现金支付金额订单现金支付金额 
	 * @param cashFeeType String 现金支付货币类型 default=CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， 
	 * @param couponFee Integer 代金券或立减优惠金额  “代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额， 
	 * @param couponCount Integer 代金券或立减优惠使用数量  代金券或立减优惠使用数量 
	 * @param couponBatchId String 代金券或立减优惠批次ID  代金券或立减优惠批次ID ,$n为下标，从0开始编号 
	 * @param couponId String 代金券或立减优惠ID  代金券或立减优惠ID, $n为下标，从0开始编号 
	 * @param transactionId String 微信支付订单号  微信支付订单号 
	 * @param outTradeNo String 商户订单号  商户系统的订单号，与请求一致。 
	 * @param attach String 商家数据包  商家数据包，原样返回 
	 * @param timeEnd String 支付完成时间  订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 
	 * @param tradeStateDesc String 交易状态描述  对当前查询订单状态的描述和下一步操作的指引 
	* @param notuse String 没用
	 */
	public WaOrderQueryRet(String returnCode ,String returnMsg ,String appid ,String mchId ,String nonceStr ,String sign ,String resultCode ,String errCode ,String errCodeDes ,String deviceInfo ,String openid ,String isSubscribe ,String tradeType ,String tradeState ,String bankType ,Integer totalFee ,String feeType ,Integer cashFee ,String cashFeeType ,Integer couponFee ,Integer couponCount ,String couponBatchId ,String couponId ,String transactionId ,String outTradeNo ,String attach ,String timeEnd ,String tradeStateDesc ,String notuse) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.appid = appid;
		this.mchId = mchId;
		this.nonceStr = nonceStr;
		this.sign = sign;
		this.resultCode = resultCode;
		this.errCode = errCode;
		this.errCodeDes = errCodeDes;
		this.deviceInfo = deviceInfo;
		this.openid = openid;
		this.isSubscribe = isSubscribe;
		this.tradeType = tradeType;
		this.tradeState = tradeState;
		this.bankType = bankType;
		this.totalFee = totalFee;
		this.feeType = feeType;
		this.cashFee = cashFee;
		this.cashFeeType = cashFeeType;
		this.couponFee = couponFee;
		this.couponCount = couponCount;
		this.couponBatchId = couponBatchId;
		this.couponId = couponId;
		this.transactionId = transactionId;
		this.outTradeNo = outTradeNo;
		this.attach = attach;
		this.timeEnd = timeEnd;
		this.tradeStateDesc = tradeStateDesc;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param returnCode String 返回状态码  SUCCESS/FAIL 
	 * @param returnMsg String 返回信息  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 
	 * @param appid String 微信订单号  微信的订单号，优先使用 
	 * @param mchId String 商户订单号  商户系统内部的订单号，当没提供transaction_id时需要传这个。 
	 * @param nonceStr String 随机字符串  随机字符串，不长于32位。 
	 * @param sign String 签名  签名 
	 * @param resultCode String 业务结果   
	 * @param errCode String 错误代码   
	 * @param errCodeDes String 错误代码描述   
	 * @param deviceInfo String 设备号  微信支付分配的终端设备号 
	 * @param openid String 用户标识  用户在商户appid下的唯一标识 
	 * @param isSubscribe String 是否关注公众账号  用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 
	 * @param tradeType String 交易类型  调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY， 
	 * @param tradeState String 交易状态  七种状态，详情见卡妨碍文档 
	 * @param bankType String 付款银行  银行类型，采用字符串类型的银行标识 
	 * @param totalFee Integer 总金额  订单总金额，单位为分 
	 * @param feeType String 货币种类 default=CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY 
	 * @param cashFee Integer 现金支付金额  现金支付金额订单现金支付金额 
	 * @param cashFeeType String 现金支付货币类型 default=CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， 
	 * @param couponFee Integer 代金券或立减优惠金额  “代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额， 
	 * @param couponCount Integer 代金券或立减优惠使用数量  代金券或立减优惠使用数量 
	 * @param couponBatchId String 代金券或立减优惠批次ID  代金券或立减优惠批次ID ,$n为下标，从0开始编号 
	 * @param couponId String 代金券或立减优惠ID  代金券或立减优惠ID, $n为下标，从0开始编号 
	 * @param transactionId String 微信支付订单号  微信支付订单号 
	 * @param outTradeNo String 商户订单号  商户系统的订单号，与请求一致。 
	 * @param attach String 商家数据包  商家数据包，原样返回 
	 * @param timeEnd String 支付完成时间  订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 
	 * @param tradeStateDesc String 交易状态描述  对当前查询订单状态的描述和下一步操作的指引 
	* @param notuse String 没用
	 */
	public WaOrderQueryRet(String returnCode ,String returnMsg ,String appid ,String mchId ,String nonceStr ,String sign ,String resultCode ,String errCode ,String errCodeDes ,String deviceInfo ,String openid ,String isSubscribe ,String tradeType ,String tradeState ,String bankType ,Integer totalFee ,String feeType ,Integer cashFee ,String cashFeeType ,Integer couponFee ,Integer couponCount ,String couponBatchId ,String couponId ,String transactionId ,String outTradeNo ,String attach ,String timeEnd ,String tradeStateDesc ,String notuse,Object notuse2) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.appid = appid;
		this.mchId = mchId;
		this.nonceStr = nonceStr;
		this.sign = sign;
		this.resultCode = resultCode;
		this.errCode = errCode;
		this.errCodeDes = errCodeDes;
		this.deviceInfo = deviceInfo;
		this.openid = openid;
		this.isSubscribe = isSubscribe;
		this.tradeType = tradeType;
		this.tradeState = tradeState;
		this.bankType = bankType;
		this.totalFee = totalFee;
		this.feeType = feeType;
		this.cashFee = cashFee;
		this.cashFeeType = cashFeeType;
		this.couponFee = couponFee;
		this.couponCount = couponCount;
		this.couponBatchId = couponBatchId;
		this.couponId = couponId;
		this.transactionId = transactionId;
		this.outTradeNo = outTradeNo;
		this.attach = attach;
		this.timeEnd = timeEnd;
		this.tradeStateDesc = tradeStateDesc;
	}
	/**
	 * 构造之不能为空的参数
	 * @param returnMsg String 返回信息 
	 * @param appid String 微信订单号 
	 * @param mchId String 商户订单号 
	 * @param nonceStr String 随机字符串 
	 * @param sign String 签名 
	 * @param openid String 用户标识 
	 * @param isSubscribe String 是否关注公众账号 
	 * @param tradeType String 交易类型 
	 * @param tradeState String 交易状态 
	 * @param bankType String 付款银行 
	 * @param totalFee Integer 总金额 
	 * @param cashFee Integer 现金支付金额 
	 * @param transactionId String 微信支付订单号 
	 * @param outTradeNo String 商户订单号 
	 * @param timeEnd String 支付完成时间 
	 * @param tradeStateDesc String 交易状态描述 
	* @param notuse String 没用
	 */
	public WaOrderQueryRet(String _returnMsg,String _appid,String _mchId,String _nonceStr,String _sign,String _openid,String _isSubscribe,String _tradeType,String _tradeState,String _bankType,Integer _totalFee,Integer _cashFee,String _transactionId,String _outTradeNo,String _timeEnd,String _tradeStateDesc,String notuse){
		this.returnMsg=_returnMsg;
		this.appid=_appid;
		this.mchId=_mchId;
		this.nonceStr=_nonceStr;
		this.sign=_sign;
		this.openid=_openid;
		this.isSubscribe=_isSubscribe;
		this.tradeType=_tradeType;
		this.tradeState=_tradeState;
		this.bankType=_bankType;
		this.totalFee=_totalFee;
		this.cashFee=_cashFee;
		this.transactionId=_transactionId;
		this.outTradeNo=_outTradeNo;
		this.timeEnd=_timeEnd;
		this.tradeStateDesc=_tradeStateDesc;
		makedefault(this);
	}

	
	/**获取返回状态码 SUCCESS/FAIL */
	@XmlElement(name="return_code")

	
	public String getReturnCode() {
		return this.returnCode;
	}
	/**设置返回状态码 SUCCESS/FAIL */

	public void setReturnCode(String value) {
		this.returnCode = value;
	}
	/**获取返回信息 返回信息，如非空，为错误原因 签名失败 参数格式校验错误 */
	@XmlElement(name="return_msg")

	
	public String getReturnMsg() {
		return this.returnMsg;
	}
	/**设置返回信息 返回信息，如非空，为错误原因 签名失败 参数格式校验错误 */

	public void setReturnMsg(String value) {
		this.returnMsg = value;
	}
	/**获取微信订单号 微信的订单号，优先使用 */
	@XmlElement(name="appid")

	
	public String getAppid() {
		return this.appid;
	}
	/**设置微信订单号 微信的订单号，优先使用 */

	public void setAppid(String value) {
		this.appid = value;
	}
	/**获取商户订单号 商户系统内部的订单号，当没提供transaction_id时需要传这个。 */
	@XmlElement(name="mch_id")

	
	public String getMchId() {
		return this.mchId;
	}
	/**设置商户订单号 商户系统内部的订单号，当没提供transaction_id时需要传这个。 */

	public void setMchId(String value) {
		this.mchId = value;
	}
	/**获取随机字符串 随机字符串，不长于32位。 */
	@XmlElement(name="nonce_str")

	
	public String getNonceStr() {
		return this.nonceStr;
	}
	/**设置随机字符串 随机字符串，不长于32位。 */

	public void setNonceStr(String value) {
		this.nonceStr = value;
	}
	/**获取签名 签名 */
	@XmlElement(name="sign")

	
	public String getSign() {
		return this.sign;
	}
	/**设置签名 签名 */

	public void setSign(String value) {
		this.sign = value;
	}
	/**获取业务结果  */
	@XmlElement(name="result_code")

	
	public String getResultCode() {
		return this.resultCode;
	}
	/**设置业务结果  */

	public void setResultCode(String value) {
		this.resultCode = value;
	}
	/**获取错误代码  */
	@XmlElement(name="err_code")

	
	public String getErrCode() {
		return this.errCode;
	}
	/**设置错误代码  */

	public void setErrCode(String value) {
		this.errCode = value;
	}
	/**获取错误代码描述  */
	@XmlElement(name="err_code_des")

	
	public String getErrCodeDes() {
		return this.errCodeDes;
	}
	/**设置错误代码描述  */

	public void setErrCodeDes(String value) {
		this.errCodeDes = value;
	}
	/**获取设备号 微信支付分配的终端设备号 */
	@XmlElement(name="device_info")

	
	public String getDeviceInfo() {
		return this.deviceInfo;
	}
	/**设置设备号 微信支付分配的终端设备号 */

	public void setDeviceInfo(String value) {
		this.deviceInfo = value;
	}
	/**获取用户标识 用户在商户appid下的唯一标识 */
	@XmlElement(name="openid")

	
	public String getOpenid() {
		return this.openid;
	}
	/**设置用户标识 用户在商户appid下的唯一标识 */

	public void setOpenid(String value) {
		this.openid = value;
	}
	/**获取是否关注公众账号 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 */
	@XmlElement(name="is_subscribe")

	
	public String getIsSubscribe() {
		return this.isSubscribe;
	}
	/**设置是否关注公众账号 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 */

	public void setIsSubscribe(String value) {
		this.isSubscribe = value;
	}
	/**获取交易类型 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY， */
	@XmlElement(name="trade_type")

	
	public String getTradeType() {
		return this.tradeType;
	}
	/**设置交易类型 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY， */

	public void setTradeType(String value) {
		this.tradeType = value;
	}
	/**获取交易状态 七种状态，详情见卡妨碍文档 */
	@XmlElement(name="trade_state")

	
	public String getTradeState() {
		return this.tradeState;
	}
	/**设置交易状态 七种状态，详情见卡妨碍文档 */

	public void setTradeState(String value) {
		this.tradeState = value;
	}
	/**获取付款银行 银行类型，采用字符串类型的银行标识 */
	@XmlElement(name="bank_type")

	
	public String getBankType() {
		return this.bankType;
	}
	/**设置付款银行 银行类型，采用字符串类型的银行标识 */

	public void setBankType(String value) {
		this.bankType = value;
	}
	/**获取总金额 订单总金额，单位为分 */
	@XmlElement(name="total_fee")

	
	public Integer getTotalFee() {
		return this.totalFee;
	}
	/**设置总金额 订单总金额，单位为分 */

	public void setTotalFee(Integer value) {
		this.totalFee = value;
	}
	/**获取货币种类 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY */
	@XmlElement(name="fee_type")

	
	public String getFeeType() {
		return this.feeType;
	}
	/**设置货币种类 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY */

	public void setFeeType(String value) {
		this.feeType = value;
	}
	/**获取现金支付金额 现金支付金额订单现金支付金额 */
	@XmlElement(name="cash_fee")

	
	public Integer getCashFee() {
		return this.cashFee;
	}
	/**设置现金支付金额 现金支付金额订单现金支付金额 */

	public void setCashFee(Integer value) {
		this.cashFee = value;
	}
	/**获取现金支付货币类型 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， */
	@XmlElement(name="cash_fee_type")

	
	public String getCashFeeType() {
		return this.cashFeeType;
	}
	/**设置现金支付货币类型 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， */

	public void setCashFeeType(String value) {
		this.cashFeeType = value;
	}
	/**获取代金券或立减优惠金额 “代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额， */
	@XmlElement(name="coupon_fee")

	
	public Integer getCouponFee() {
		return this.couponFee;
	}
	/**设置代金券或立减优惠金额 “代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额， */

	public void setCouponFee(Integer value) {
		this.couponFee = value;
	}
	/**获取代金券或立减优惠使用数量 代金券或立减优惠使用数量 */
	@XmlElement(name="coupon_count")

	
	public Integer getCouponCount() {
		return this.couponCount;
	}
	/**设置代金券或立减优惠使用数量 代金券或立减优惠使用数量 */

	public void setCouponCount(Integer value) {
		this.couponCount = value;
	}
	/**获取代金券或立减优惠批次ID 代金券或立减优惠批次ID ,$n为下标，从0开始编号 */
	@XmlElement(name="coupon_batch_id_$n")

	
	public String getCouponBatchId() {
		return this.couponBatchId;
	}
	/**设置代金券或立减优惠批次ID 代金券或立减优惠批次ID ,$n为下标，从0开始编号 */

	public void setCouponBatchId(String value) {
		this.couponBatchId = value;
	}
	/**获取代金券或立减优惠ID 代金券或立减优惠ID, $n为下标，从0开始编号 */
	@XmlElement(name="coupon_id_$n")

	
	public String getCouponId() {
		return this.couponId;
	}
	/**设置代金券或立减优惠ID 代金券或立减优惠ID, $n为下标，从0开始编号 */

	public void setCouponId(String value) {
		this.couponId = value;
	}
	/**获取微信支付订单号 微信支付订单号 */
	@XmlElement(name="transaction_id")

	
	public String getTransactionId() {
		return this.transactionId;
	}
	/**设置微信支付订单号 微信支付订单号 */

	public void setTransactionId(String value) {
		this.transactionId = value;
	}
	/**获取商户订单号 商户系统的订单号，与请求一致。 */
	@XmlElement(name="out_trade_no")

	
	public String getOutTradeNo() {
		return this.outTradeNo;
	}
	/**设置商户订单号 商户系统的订单号，与请求一致。 */

	public void setOutTradeNo(String value) {
		this.outTradeNo = value;
	}
	/**获取商家数据包 商家数据包，原样返回 */
	@XmlElement(name="attach")

	
	public String getAttach() {
		return this.attach;
	}
	/**设置商家数据包 商家数据包，原样返回 */

	public void setAttach(String value) {
		this.attach = value;
	}
	/**获取支付完成时间 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 */
	@XmlElement(name="time_end")

	
	public String getTimeEnd() {
		return this.timeEnd;
	}
	/**设置支付完成时间 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 */

	public void setTimeEnd(String value) {
		this.timeEnd = value;
	}
	/**获取交易状态描述 对当前查询订单状态的描述和下一步操作的指引 */
	@XmlElement(name="trade_state_desc")

	
	public String getTradeStateDesc() {
		return this.tradeStateDesc;
	}
	/**设置交易状态描述 对当前查询订单状态的描述和下一步操作的指引 */

	public void setTradeStateDesc(String value) {
		this.tradeStateDesc = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",ReturnCode:",getReturnCode())
			.append(",ReturnMsg:",getReturnMsg())
			.append(",Appid:",getAppid())
			.append(",MchId:",getMchId())
			.append(",NonceStr:",getNonceStr())
			.append(",Sign:",getSign())
			.append(",ResultCode:",getResultCode())
			.append(",ErrCode:",getErrCode())
			.append(",ErrCodeDes:",getErrCodeDes())
			.append(",DeviceInfo:",getDeviceInfo())
			.append(",Openid:",getOpenid())
			.append(",IsSubscribe:",getIsSubscribe())
			.append(",TradeType:",getTradeType())
			.append(",TradeState:",getTradeState())
			.append(",BankType:",getBankType())
			.append(",TotalFee:",getTotalFee())
			.append(",FeeType:",getFeeType())
			.append(",CashFee:",getCashFee())
			.append(",CashFeeType:",getCashFeeType())
			.append(",CouponFee:",getCouponFee())
			.append(",CouponCount:",getCouponCount())
			.append(",CouponBatchId:",getCouponBatchId())
			.append(",CouponId:",getCouponId())
			.append(",TransactionId:",getTransactionId())
			.append(",OutTradeNo:",getOutTradeNo())
			.append(",Attach:",getAttach())
			.append(",TimeEnd:",getTimeEnd())
			.append(",TradeStateDesc:",getTradeStateDesc())
			.toString();

	}
	/**
	 * 返回JSON之不显示第0级，对于一些不能传递的对象，不进行json编码，比如password明文，用得较少。
	 * @return
	 */
	public String toJsonNotshow(){
		String[] excludesProperties={"null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第1级，nowshow+notshow1('notshow1'+xxxObj+xxxString+xxxList)
	 * <br>在内部服务通讯时，防止get页面参数过大，通常设置为notshow1，返回时可以是notshow或notshow1
	 * @return
	 */
	public String toJsonNotshow1(){
		String[] excludesProperties={"null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 支付需要的参数
WaOrderQueryRet waOrderQueryRet = new WaOrderQueryRet(
	returnCode , //String 返回状态码  SUCCESS/FAIL 
	returnMsg , //String 返回信息  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 
	appid , //String 微信订单号  微信的订单号，优先使用 
	mchId , //String 商户订单号  商户系统内部的订单号，当没提供transaction_id时需要传这个。 
	nonceStr , //String 随机字符串  随机字符串，不长于32位。 
	sign , //String 签名  签名 
	resultCode , //String 业务结果   
	errCode , //String 错误代码   
	errCodeDes , //String 错误代码描述   
	deviceInfo , //String 设备号  微信支付分配的终端设备号 
	openid , //String 用户标识  用户在商户appid下的唯一标识 
	isSubscribe , //String 是否关注公众账号  用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 
	tradeType , //String 交易类型  调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY， 
	tradeState , //String 交易状态  七种状态，详情见卡妨碍文档 
	bankType , //String 付款银行  银行类型，采用字符串类型的银行标识 
	totalFee , //Integer 总金额  订单总金额，单位为分 
	feeType , //String 货币种类 default=CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY 
	cashFee , //Integer 现金支付金额  现金支付金额订单现金支付金额 
	cashFeeType , //String 现金支付货币类型 default=CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， 
	couponFee , //Integer 代金券或立减优惠金额  “代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额， 
	couponCount , //Integer 代金券或立减优惠使用数量  代金券或立减优惠使用数量 
	couponBatchId , //String 代金券或立减优惠批次ID  代金券或立减优惠批次ID ,$n为下标，从0开始编号 
	couponId , //String 代金券或立减优惠ID  代金券或立减优惠ID, $n为下标，从0开始编号 
	transactionId , //String 微信支付订单号  微信支付订单号 
	outTradeNo , //String 商户订单号  商户系统的订单号，与请求一致。 
	attach , //String 商家数据包  商家数据包，原样返回 
	timeEnd , //String 支付完成时间  订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 
	tradeStateDesc , //String 交易状态描述  对当前查询订单状态的描述和下一步操作的指引 
	null
);
*/
}
