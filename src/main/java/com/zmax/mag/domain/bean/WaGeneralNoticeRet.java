/*
 * zmax 
 * 
 */


//  
package com.zmax.mag.domain.bean;



import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.text.*;
import java.util.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.mag.domain.bean.base.BaseEntity;

import javax.xml.bind.annotation.*;




/**
 * 微信支付回调通用结果
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wa_general_notice_ret")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
@XmlRootElement(name="xml")
public class WaGeneralNoticeRet extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "微信支付回调通用结果";

	//date formats
	
	//columns START
	/**序号ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**返回状态码 String  SUCCESS/FAIL */
	@Length(max=16)
	
	private String returnCode;
	/**返回信息 String  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 */
	@Length(max=128)
	
	private String returnMsg;
	/**公众账号ID String  微信分配的公众账号ID */
	@Length(max=32)
	
	private String appid;
	/**商户号 String  微信支付分配的商户号 */
	@Length(max=32)
	
	private String mchId;
	/**设备号 String  终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" */
	@Length(max=32)
	
	private String deviceInfo;
	/**随机字符串 String  随机字符串，不长于32位 */
	@Length(max=32)
	
	private String nonceStr;
	/**签名 String  签名 */
	@Length(max=32)
	
	private String sign;
	/**业务结果 String  SUCCESS/FAIL */
	@Length(max=16)
	
	private String resultCode;
	/**错误代码 String  详细参见第6节错误列表 */
	@Length(max=32)
	
	private String errCode;
	/**错误代码描述 String  错误返回的信息描述 */
	@Length(max=128)
	
	private String errCodeDes;
	/**用户标识 String  用户在商户appid下的唯一标识 */
	@Length(max=128)
	
	private String openid;
	/**是否关注公众账号 String  用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 */
	@Length(max=1)
	
	private String isSubscribe;
	/**交易类型 String  JSAPI、NATIVE、APP */
	@Length(max=16)
	
	private String tradeType;
	/**付款银行 String  银行类型，采用字符串类型的银行标识，银行类型见附表 */
	@Length(max=16)
	
	private String bankType;
	/**总金额 Integer  订单总金额，单位为分 */
	@Max(99999999999L)
	
	private Integer totalFee;
	/**货币种类 String  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， */
	@Length(max=8)
	
	private String feeType;
	/**现金支付金额 Integer  现金支付金额订单现金支付金额 */
	@Max(99999999999L)
	
	private Integer cashFee;
	/**现金支付货币类型 String  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY */
	@Length(max=16)
	
	private String cashFeeType;
	/**代金券或立减优惠金额 Integer  代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额 */
	@Max(99999999999L)
	
	private Integer couponFee;
	/**代金券或立减优惠使用数量 Integer  代金券或立减优惠使用数量 */
	@Max(99999999999L)
	
	private Integer couponCount;
	/**微信支付订单号 String  微信支付订单号 */
	@Length(max=32)
	
	private String transactionId;
	/**商户订单号 String  商户系统的订单号，与请求一致。 */
	@Length(max=32)
	
	private String outTradeNo;
	/**商家数据包 String  商家数据包，原样返回 */
	@Length(max=128)
	
	private String attach;
	/**支付完成时间 String  支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 */
	@Length(max=14)
	
	private String timeEnd;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaGeneralNoticeRet _this){
	}
	public WaGeneralNoticeRet(){
		makedefault(this);
	}
	public WaGeneralNoticeRet(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param returnCode String 返回状态码  SUCCESS/FAIL 
	 * @param returnMsg String 返回信息  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 
	 * @param appid String 公众账号ID  微信分配的公众账号ID 
	 * @param mchId String 商户号  微信支付分配的商户号 
	 * @param deviceInfo String 设备号  终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" 
	 * @param nonceStr String 随机字符串  随机字符串，不长于32位 
	 * @param sign String 签名  签名 
	 * @param resultCode String 业务结果  SUCCESS/FAIL 
	 * @param errCode String 错误代码  详细参见第6节错误列表 
	 * @param errCodeDes String 错误代码描述  错误返回的信息描述 
	 * @param openid String 用户标识  用户在商户appid下的唯一标识 
	 * @param isSubscribe String 是否关注公众账号  用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 
	 * @param tradeType String 交易类型  JSAPI、NATIVE、APP 
	 * @param bankType String 付款银行  银行类型，采用字符串类型的银行标识，银行类型见附表 
	 * @param totalFee Integer 总金额  订单总金额，单位为分 
	 * @param feeType String 货币种类  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， 
	 * @param cashFee Integer 现金支付金额  现金支付金额订单现金支付金额 
	 * @param cashFeeType String 现金支付货币类型  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY 
	 * @param couponFee Integer 代金券或立减优惠金额  代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额 
	 * @param couponCount Integer 代金券或立减优惠使用数量  代金券或立减优惠使用数量 
	 * @param transactionId String 微信支付订单号  微信支付订单号 
	 * @param outTradeNo String 商户订单号  商户系统的订单号，与请求一致。 
	 * @param attach String 商家数据包  商家数据包，原样返回 
	 * @param timeEnd String 支付完成时间  支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 
	* @param notuse String 没用
	 */
	public WaGeneralNoticeRet(String returnCode ,String returnMsg ,String appid ,String mchId ,String deviceInfo ,String nonceStr ,String sign ,String resultCode ,String errCode ,String errCodeDes ,String openid ,String isSubscribe ,String tradeType ,String bankType ,Integer totalFee ,String feeType ,Integer cashFee ,String cashFeeType ,Integer couponFee ,Integer couponCount ,String transactionId ,String outTradeNo ,String attach ,String timeEnd ,String notuse) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.appid = appid;
		this.mchId = mchId;
		this.deviceInfo = deviceInfo;
		this.nonceStr = nonceStr;
		this.sign = sign;
		this.resultCode = resultCode;
		this.errCode = errCode;
		this.errCodeDes = errCodeDes;
		this.openid = openid;
		this.isSubscribe = isSubscribe;
		this.tradeType = tradeType;
		this.bankType = bankType;
		this.totalFee = totalFee;
		this.feeType = feeType;
		this.cashFee = cashFee;
		this.cashFeeType = cashFeeType;
		this.couponFee = couponFee;
		this.couponCount = couponCount;
		this.transactionId = transactionId;
		this.outTradeNo = outTradeNo;
		this.attach = attach;
		this.timeEnd = timeEnd;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param returnCode String 返回状态码  SUCCESS/FAIL 
	 * @param returnMsg String 返回信息  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 
	 * @param appid String 公众账号ID  微信分配的公众账号ID 
	 * @param mchId String 商户号  微信支付分配的商户号 
	 * @param deviceInfo String 设备号  终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" 
	 * @param nonceStr String 随机字符串  随机字符串，不长于32位 
	 * @param sign String 签名  签名 
	 * @param resultCode String 业务结果  SUCCESS/FAIL 
	 * @param errCode String 错误代码  详细参见第6节错误列表 
	 * @param errCodeDes String 错误代码描述  错误返回的信息描述 
	 * @param openid String 用户标识  用户在商户appid下的唯一标识 
	 * @param isSubscribe String 是否关注公众账号  用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 
	 * @param tradeType String 交易类型  JSAPI、NATIVE、APP 
	 * @param bankType String 付款银行  银行类型，采用字符串类型的银行标识，银行类型见附表 
	 * @param totalFee Integer 总金额  订单总金额，单位为分 
	 * @param feeType String 货币种类  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， 
	 * @param cashFee Integer 现金支付金额  现金支付金额订单现金支付金额 
	 * @param cashFeeType String 现金支付货币类型  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY 
	 * @param couponFee Integer 代金券或立减优惠金额  代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额 
	 * @param couponCount Integer 代金券或立减优惠使用数量  代金券或立减优惠使用数量 
	 * @param transactionId String 微信支付订单号  微信支付订单号 
	 * @param outTradeNo String 商户订单号  商户系统的订单号，与请求一致。 
	 * @param attach String 商家数据包  商家数据包，原样返回 
	 * @param timeEnd String 支付完成时间  支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 
	* @param notuse String 没用
	 */
	public WaGeneralNoticeRet(Integer id ,String returnCode ,String returnMsg ,String appid ,String mchId ,String deviceInfo ,String nonceStr ,String sign ,String resultCode ,String errCode ,String errCodeDes ,String openid ,String isSubscribe ,String tradeType ,String bankType ,Integer totalFee ,String feeType ,Integer cashFee ,String cashFeeType ,Integer couponFee ,Integer couponCount ,String transactionId ,String outTradeNo ,String attach ,String timeEnd ,String notuse) {
		super();
		this.id = id;
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.appid = appid;
		this.mchId = mchId;
		this.deviceInfo = deviceInfo;
		this.nonceStr = nonceStr;
		this.sign = sign;
		this.resultCode = resultCode;
		this.errCode = errCode;
		this.errCodeDes = errCodeDes;
		this.openid = openid;
		this.isSubscribe = isSubscribe;
		this.tradeType = tradeType;
		this.bankType = bankType;
		this.totalFee = totalFee;
		this.feeType = feeType;
		this.cashFee = cashFee;
		this.cashFeeType = cashFeeType;
		this.couponFee = couponFee;
		this.couponCount = couponCount;
		this.transactionId = transactionId;
		this.outTradeNo = outTradeNo;
		this.attach = attach;
		this.timeEnd = timeEnd;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param returnCode String 返回状态码  SUCCESS/FAIL 
	 * @param returnMsg String 返回信息  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 
	 * @param appid String 公众账号ID  微信分配的公众账号ID 
	 * @param mchId String 商户号  微信支付分配的商户号 
	 * @param deviceInfo String 设备号  终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" 
	 * @param nonceStr String 随机字符串  随机字符串，不长于32位 
	 * @param sign String 签名  签名 
	 * @param resultCode String 业务结果  SUCCESS/FAIL 
	 * @param errCode String 错误代码  详细参见第6节错误列表 
	 * @param errCodeDes String 错误代码描述  错误返回的信息描述 
	 * @param openid String 用户标识  用户在商户appid下的唯一标识 
	 * @param isSubscribe String 是否关注公众账号  用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 
	 * @param tradeType String 交易类型  JSAPI、NATIVE、APP 
	 * @param bankType String 付款银行  银行类型，采用字符串类型的银行标识，银行类型见附表 
	 * @param totalFee Integer 总金额  订单总金额，单位为分 
	 * @param feeType String 货币种类  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， 
	 * @param cashFee Integer 现金支付金额  现金支付金额订单现金支付金额 
	 * @param cashFeeType String 现金支付货币类型  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY 
	 * @param couponFee Integer 代金券或立减优惠金额  代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额 
	 * @param couponCount Integer 代金券或立减优惠使用数量  代金券或立减优惠使用数量 
	 * @param transactionId String 微信支付订单号  微信支付订单号 
	 * @param outTradeNo String 商户订单号  商户系统的订单号，与请求一致。 
	 * @param attach String 商家数据包  商家数据包，原样返回 
	 * @param timeEnd String 支付完成时间  支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 
	* @param notuse String 没用
	 */
	public WaGeneralNoticeRet(Integer id ,String returnCode ,String returnMsg ,String appid ,String mchId ,String deviceInfo ,String nonceStr ,String sign ,String resultCode ,String errCode ,String errCodeDes ,String openid ,String isSubscribe ,String tradeType ,String bankType ,Integer totalFee ,String feeType ,Integer cashFee ,String cashFeeType ,Integer couponFee ,Integer couponCount ,String transactionId ,String outTradeNo ,String attach ,String timeEnd ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.appid = appid;
		this.mchId = mchId;
		this.deviceInfo = deviceInfo;
		this.nonceStr = nonceStr;
		this.sign = sign;
		this.resultCode = resultCode;
		this.errCode = errCode;
		this.errCodeDes = errCodeDes;
		this.openid = openid;
		this.isSubscribe = isSubscribe;
		this.tradeType = tradeType;
		this.bankType = bankType;
		this.totalFee = totalFee;
		this.feeType = feeType;
		this.cashFee = cashFee;
		this.cashFeeType = cashFeeType;
		this.couponFee = couponFee;
		this.couponCount = couponCount;
		this.transactionId = transactionId;
		this.outTradeNo = outTradeNo;
		this.attach = attach;
		this.timeEnd = timeEnd;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	@XmlTransient
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+id:myname;
		return myname;
	}
	/**我的中文名称*/
	public void setMyname(String myname) {
		this.myname = myname;
	}
	/**我的带id中文名称*/
	private String mynameid;
	/**我的带id中文名称*/
	@Transient
	@XmlTransient
	//@JSONField(serialize = false)
	public String getMynameid() {
		mynameid=(mynameid==null)?"["+id+"]":mynameid;
		return mynameid;
	}
	/**我的带id中文名称*/
	public void setMynameid(String mynameid) {
		this.mynameid = mynameid;
	}
	/**设置主键*/
	public void setId(Integer value) {
		this.id = value;
	}
	/**获取主键*/
	@XmlTransient

	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "identity")
	@Column(name = "id",  unique = false, nullable = false, insertable = true, updatable = true, length = 11)
	public Integer getId() {
		return this.id;
	}
	
	/**获取返回状态码 SUCCESS/FAIL */
	@XmlElement(name="return_code")

	@Column(name = "return_code", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getReturnCode() {
		return this.returnCode;
	}
	/**设置返回状态码 SUCCESS/FAIL */

	public void setReturnCode(String value) {
		this.returnCode = value;
	}
	/**获取返回信息 返回信息，如非空，为错误原因 签名失败 参数格式校验错误 */
	@XmlElement(name="return_msg")

	@Column(name = "return_msg", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public String getReturnMsg() {
		return this.returnMsg;
	}
	/**设置返回信息 返回信息，如非空，为错误原因 签名失败 参数格式校验错误 */

	public void setReturnMsg(String value) {
		this.returnMsg = value;
	}
	/**获取公众账号ID 微信分配的公众账号ID */
	@XmlElement(name="appid")

	@Column(name = "appid", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getAppid() {
		return this.appid;
	}
	/**设置公众账号ID 微信分配的公众账号ID */

	public void setAppid(String value) {
		this.appid = value;
	}
	/**获取商户号 微信支付分配的商户号 */
	@XmlElement(name="mch_id")

	@Column(name = "mch_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getMchId() {
		return this.mchId;
	}
	/**设置商户号 微信支付分配的商户号 */

	public void setMchId(String value) {
		this.mchId = value;
	}
	/**获取设备号 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" */
	@XmlElement(name="device_info")

	@Column(name = "device_info", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getDeviceInfo() {
		return this.deviceInfo;
	}
	/**设置设备号 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" */

	public void setDeviceInfo(String value) {
		this.deviceInfo = value;
	}
	/**获取随机字符串 随机字符串，不长于32位 */
	@XmlElement(name="nonce_str")

	@Column(name = "nonce_str", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getNonceStr() {
		return this.nonceStr;
	}
	/**设置随机字符串 随机字符串，不长于32位 */

	public void setNonceStr(String value) {
		this.nonceStr = value;
	}
	/**获取签名 签名 */
	@XmlElement(name="sign")

	@Column(name = "sign", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSign() {
		return this.sign;
	}
	/**设置签名 签名 */

	public void setSign(String value) {
		this.sign = value;
	}
	/**获取业务结果 SUCCESS/FAIL */
	@XmlElement(name="result_code")

	@Column(name = "result_code", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getResultCode() {
		return this.resultCode;
	}
	/**设置业务结果 SUCCESS/FAIL */

	public void setResultCode(String value) {
		this.resultCode = value;
	}
	/**获取错误代码 详细参见第6节错误列表 */
	@XmlElement(name="err_code")

	@Column(name = "err_code", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getErrCode() {
		return this.errCode;
	}
	/**设置错误代码 详细参见第6节错误列表 */

	public void setErrCode(String value) {
		this.errCode = value;
	}
	/**获取错误代码描述 错误返回的信息描述 */
	@XmlElement(name="err_code_des")

	@Column(name = "err_code_des", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public String getErrCodeDes() {
		return this.errCodeDes;
	}
	/**设置错误代码描述 错误返回的信息描述 */

	public void setErrCodeDes(String value) {
		this.errCodeDes = value;
	}
	/**获取用户标识 用户在商户appid下的唯一标识 */
	@XmlElement(name="openid")

	@Column(name = "openid", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public String getOpenid() {
		return this.openid;
	}
	/**设置用户标识 用户在商户appid下的唯一标识 */

	public void setOpenid(String value) {
		this.openid = value;
	}
	/**获取是否关注公众账号 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 */
	@XmlElement(name="is_subscribe")

	@Column(name = "is_subscribe", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getIsSubscribe() {
		return this.isSubscribe;
	}
	/**设置是否关注公众账号 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 */

	public void setIsSubscribe(String value) {
		this.isSubscribe = value;
	}
	/**获取交易类型 JSAPI、NATIVE、APP */
	@XmlElement(name="trade_type")

	@Column(name = "trade_type", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getTradeType() {
		return this.tradeType;
	}
	/**设置交易类型 JSAPI、NATIVE、APP */

	public void setTradeType(String value) {
		this.tradeType = value;
	}
	/**获取付款银行 银行类型，采用字符串类型的银行标识，银行类型见附表 */
	@XmlElement(name="bank_type")

	@Column(name = "bank_type", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getBankType() {
		return this.bankType;
	}
	/**设置付款银行 银行类型，采用字符串类型的银行标识，银行类型见附表 */

	public void setBankType(String value) {
		this.bankType = value;
	}
	/**获取总金额 订单总金额，单位为分 */
	@XmlElement(name="total_fee")

	@Column(name = "total_fee", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getTotalFee() {
		return this.totalFee;
	}
	/**设置总金额 订单总金额，单位为分 */

	public void setTotalFee(Integer value) {
		this.totalFee = value;
	}
	/**获取货币种类 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， */
	@XmlElement(name="fee_type")

	@Column(name = "fee_type", unique = false, nullable = true, insertable = true, updatable = true, length = 8)
	public String getFeeType() {
		return this.feeType;
	}
	/**设置货币种类 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， */

	public void setFeeType(String value) {
		this.feeType = value;
	}
	/**获取现金支付金额 现金支付金额订单现金支付金额 */
	@XmlElement(name="cash_fee")

	@Column(name = "cash_fee", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getCashFee() {
		return this.cashFee;
	}
	/**设置现金支付金额 现金支付金额订单现金支付金额 */

	public void setCashFee(Integer value) {
		this.cashFee = value;
	}
	/**获取现金支付货币类型 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY */
	@XmlElement(name="cash_fee_type")

	@Column(name = "cash_fee_type", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getCashFeeType() {
		return this.cashFeeType;
	}
	/**设置现金支付货币类型 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY */

	public void setCashFeeType(String value) {
		this.cashFeeType = value;
	}
	/**获取代金券或立减优惠金额 代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额 */
	@XmlElement(name="coupon_fee")

	@Column(name = "coupon_fee", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getCouponFee() {
		return this.couponFee;
	}
	/**设置代金券或立减优惠金额 代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额 */

	public void setCouponFee(Integer value) {
		this.couponFee = value;
	}
	/**获取代金券或立减优惠使用数量 代金券或立减优惠使用数量 */
	@XmlElement(name="coupon_count")

	@Column(name = "coupon_count", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getCouponCount() {
		return this.couponCount;
	}
	/**设置代金券或立减优惠使用数量 代金券或立减优惠使用数量 */

	public void setCouponCount(Integer value) {
		this.couponCount = value;
	}
	/**获取微信支付订单号 微信支付订单号 */
	@XmlElement(name="transaction_id")

	@Column(name = "transaction_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getTransactionId() {
		return this.transactionId;
	}
	/**设置微信支付订单号 微信支付订单号 */

	public void setTransactionId(String value) {
		this.transactionId = value;
	}
	/**获取商户订单号 商户系统的订单号，与请求一致。 */
	@XmlElement(name="out_trade_no")

	@Column(name = "out_trade_no", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getOutTradeNo() {
		return this.outTradeNo;
	}
	/**设置商户订单号 商户系统的订单号，与请求一致。 */

	public void setOutTradeNo(String value) {
		this.outTradeNo = value;
	}
	/**获取商家数据包 商家数据包，原样返回 */
	@XmlElement(name="attach")

	@Column(name = "attach", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public String getAttach() {
		return this.attach;
	}
	/**设置商家数据包 商家数据包，原样返回 */

	public void setAttach(String value) {
		this.attach = value;
	}
	/**获取支付完成时间 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 */
	@XmlElement(name="time_end")

	@Column(name = "time_end", unique = false, nullable = true, insertable = true, updatable = true, length = 14)
	public String getTimeEnd() {
		return this.timeEnd;
	}
	/**设置支付完成时间 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 */

	public void setTimeEnd(String value) {
		this.timeEnd = value;
	}

	/**
	 * 清空ONE MANY对象，用于WS,JSON等场景
	 */
	public void makeNullObj(){
		this.obj1=null;
		this.obj2=null;
		this.obj3=null;
		this.obj4=null;
		this.obj5=null;
		this.objint1=null;
		this.objint2=null;
		this.objint3=null;
		this.objstring1=null;
		this.objstring2=null;
		this.objstring3=null;
	}
	/**备用对象1*/
	private Object obj1;
	/**
	 * 获取备用对象1
	 * @return Object
	 */
	@Transient
	@XmlTransient
	public Object getObj1() {
		return obj1;
	}
	/**
	 * 设置备用对象1
	 * @param obj1
	 */
	public void setObj1(Object obj1) {
		this.obj1 = obj1;
	}
	/**备用对象2*/
	private Object obj2;
	/**
	 * 获取备用对象2
	 * @return Object
	 */
	@Transient
	@XmlTransient
	public Object getObj2() {
		return obj2;
	}
	/**
	 * 设置备用对象2
	 * @param obj2
	 */
	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}
	/**备用对象3*/
	private Object obj3;
	/**
	 * 获取备用对象3
	 * @return Object
	 */
	@Transient
	@XmlTransient
	public Object getObj3() {
		return obj3;
	}
	/**
	 * 设置备用对象3
	 * @param obj3
	 */
	public void setObj3(Object obj3) {
		this.obj3 = obj3;
	}
	/**备用对象4*/
	private Object obj4;
	/**
	 * 获取备用对象4
	 * @return Object
	 */
	@Transient
	@XmlTransient
	public Object getObj4() {
		return obj4;
	}
	/**
	 * 设置备用对象4
	 * @param obj4
	 */
	public void setObj4(Object obj4) {
		this.obj4 = obj4;
	}
	/**备用对象5*/
	private Object obj5;
	/**
	 * 获取备用对象5
	 * @return Object
	 */
	@Transient
	@XmlTransient
	public Object getObj5() {
		return obj5;
	}
	/**
	 * 设置备用对象5
	 * @param obj5
	 */
	public void setObj5(Object obj5) {
		this.obj5 = obj5;
	}
	/**备用对象 整数1*/
	private Integer objint1;
	/**
	 * 获取备用对象 整数1
	 * @return int
	 */
	@Transient
	@XmlTransient
	public Integer getObjint1() {
		return objint1;
	}
	/**
	 * 设置备用对象 整数1
	 * @param objint1 int
	 */
	public void setObjint1(Integer objint1) {
		this.objint1 = objint1;
	}
	/**备用对象 文本1*/
	private String objstring1;
	/**
	 * 获取备用对象 文本1
	 * @return string
	 */
	@Transient
	@XmlTransient
	public String getObjstring1() {
		return objstring1;
	}
	/**
	 * 设置备用对象 文本1
	 * @param objstring1 String
	 */
	public void setObjstring1(String objstring1) {
		this.objstring1 = objstring1;
	}
	/**备用对象 整数2*/
	private Integer objint2;
	/**
	 * 获取备用对象 整数2
	 * @return int
	 */
	@Transient
	@XmlTransient
	public Integer getObjint2() {
		return objint2;
	}
	/**
	 * 设置备用对象 整数2
	 * @param objint2 int
	 */
	public void setObjint2(Integer objint2) {
		this.objint2 = objint2;
	}
	/**备用对象 文本2*/
	private String objstring2;
	/**
	 * 获取备用对象 文本2
	 * @return string
	 */
	@Transient
	@XmlTransient
	public String getObjstring2() {
		return objstring2;
	}
	/**
	 * 设置备用对象 文本2
	 * @param objstring2 String
	 */
	public void setObjstring2(String objstring2) {
		this.objstring2 = objstring2;
	}
	/**备用对象 整数3*/
	private Integer objint3;
	/**
	 * 获取备用对象 整数3
	 * @return int
	 */
	@Transient
	@XmlTransient
	public Integer getObjint3() {
		return objint3;
	}
	/**
	 * 设置备用对象 整数3
	 * @param objint3 int
	 */
	public void setObjint3(Integer objint3) {
		this.objint3 = objint3;
	}
	/**备用对象 文本3*/
	private String objstring3;
	/**
	 * 获取备用对象 文本3
	 * @return string
	 */
	@Transient
	@XmlTransient
	public String getObjstring3() {
		return objstring3;
	}
	/**
	 * 设置备用对象 文本3
	 * @param objstring3 String
	 */
	public void setObjstring3(String objstring3) {
		this.objstring3 = objstring3;
	}
	/**
	 * Hash，同所有字段相加判断
	 */
	public int hashCode() {
		if(getId()==null)
		return new HashCodeBuilder()
			.append(getId())
			.append(getReturnCode())
			.append(getReturnMsg())
			.append(getAppid())
			.append(getMchId())
			.append(getDeviceInfo())
			.append(getNonceStr())
			.append(getSign())
			.append(getResultCode())
			.append(getErrCode())
			.append(getErrCodeDes())
			.append(getOpenid())
			.append(getIsSubscribe())
			.append(getTradeType())
			.append(getBankType())
			.append(getTotalFee())
			.append(getFeeType())
			.append(getCashFee())
			.append(getCashFeeType())
			.append(getCouponFee())
			.append(getCouponCount())
			.append(getTransactionId())
			.append(getOutTradeNo())
			.append(getAttach())
			.append(getTimeEnd())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof WaGeneralNoticeRet == false) return false;
		if(this == obj) return true;
		WaGeneralNoticeRet other = (WaGeneralNoticeRet)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Id:",getId())
			.append(",ReturnCode:",getReturnCode())
			.append(",ReturnMsg:",getReturnMsg())
			.append(",Appid:",getAppid())
			.append(",MchId:",getMchId())
			.append(",DeviceInfo:",getDeviceInfo())
			.append(",NonceStr:",getNonceStr())
			.append(",Sign:",getSign())
			.append(",ResultCode:",getResultCode())
			.append(",ErrCode:",getErrCode())
			.append(",ErrCodeDes:",getErrCodeDes())
			.append(",Openid:",getOpenid())
			.append(",IsSubscribe:",getIsSubscribe())
			.append(",TradeType:",getTradeType())
			.append(",BankType:",getBankType())
			.append(",TotalFee:",getTotalFee())
			.append(",FeeType:",getFeeType())
			.append(",CashFee:",getCashFee())
			.append(",CashFeeType:",getCashFeeType())
			.append(",CouponFee:",getCouponFee())
			.append(",CouponCount:",getCouponCount())
			.append(",TransactionId:",getTransactionId())
			.append(",OutTradeNo:",getOutTradeNo())
			.append(",Attach:",getAttach())
			.append(",TimeEnd:",getTimeEnd())
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
		String[] excludesProperties={"myname","mynameid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"id","myname","mynameid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 微信支付回调通用结果
WaGeneralNoticeRet waGeneralNoticeRet = new WaGeneralNoticeRet(
	returnCode , //String 返回状态码  SUCCESS/FAIL 
	returnMsg , //String 返回信息  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 
	appid , //String 公众账号ID  微信分配的公众账号ID 
	mchId , //String 商户号  微信支付分配的商户号 
	deviceInfo , //String 设备号  终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" 
	nonceStr , //String 随机字符串  随机字符串，不长于32位 
	sign , //String 签名  签名 
	resultCode , //String 业务结果  SUCCESS/FAIL 
	errCode , //String 错误代码  详细参见第6节错误列表 
	errCodeDes , //String 错误代码描述  错误返回的信息描述 
	openid , //String 用户标识  用户在商户appid下的唯一标识 
	isSubscribe , //String 是否关注公众账号  用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 
	tradeType , //String 交易类型  JSAPI、NATIVE、APP 
	bankType , //String 付款银行  银行类型，采用字符串类型的银行标识，银行类型见附表 
	totalFee , //Integer 总金额  订单总金额，单位为分 
	feeType , //String 货币种类  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY， 
	cashFee , //Integer 现金支付金额  现金支付金额订单现金支付金额 
	cashFeeType , //String 现金支付货币类型  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY 
	couponFee , //Integer 代金券或立减优惠金额  代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额 
	couponCount , //Integer 代金券或立减优惠使用数量  代金券或立减优惠使用数量 
	transactionId , //String 微信支付订单号  微信支付订单号 
	outTradeNo , //String 商户订单号  商户系统的订单号，与请求一致。 
	attach , //String 商家数据包  商家数据包，原样返回 
	timeEnd , //String 支付完成时间  支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 
	null
);
*/
}
