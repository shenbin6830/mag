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

import com.alibaba.fastjson.annotation.*;


import com.zmax.mag.domain.bean.*;


/**
 * APP调起支付API
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaAppapiPaymentParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "APP调起支付API";

	//date formats
	
	//columns START
	/**公众账号ID String  微信分配的公众账号ID */
	
	
	private String appid;
	/**商户号 String  微信支付分配的商户号 */
	
	
	private String partnerid;
	/**预支付交易会话ID String  微信返回的支付交易会话ID */
	
	
	private String prepayid;
	/**扩展字段 String  暂填写固定值Sign=WXPay */
	
	
	private String package1;
	/**随机字符串 String  随机字符串，不长于32位。推荐随机数生成算法 */
	
	
	private String noncestr;
	/**时间戳 String  时间戳，请见接口规则-参数规定 */
	
	
	private String timestamp1;
	/**签名 String  签名，详见签名生成算法 */
	
	
	private String sign;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaAppapiPaymentParam _this){
	}
	public WaAppapiPaymentParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param appid String 公众账号ID  微信分配的公众账号ID 
	 * @param partnerid String 商户号  微信支付分配的商户号 
	 * @param prepayid String 预支付交易会话ID  微信返回的支付交易会话ID 
	 * @param package1 String 扩展字段  暂填写固定值Sign=WXPay 
	 * @param noncestr String 随机字符串  随机字符串，不长于32位。推荐随机数生成算法 
	 * @param timestamp1 String 时间戳  时间戳，请见接口规则-参数规定 
	 * @param sign String 签名  签名，详见签名生成算法 
	* @param notuse String 没用
	 */
	public WaAppapiPaymentParam(String appid ,String partnerid ,String prepayid ,String package1 ,String noncestr ,String timestamp1 ,String sign ,String notuse) {
		super();
		this.appid = appid;
		this.partnerid = partnerid;
		this.prepayid = prepayid;
		this.package1 = package1;
		this.noncestr = noncestr;
		this.timestamp1 = timestamp1;
		this.sign = sign;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param appid String 公众账号ID  微信分配的公众账号ID 
	 * @param partnerid String 商户号  微信支付分配的商户号 
	 * @param prepayid String 预支付交易会话ID  微信返回的支付交易会话ID 
	 * @param package1 String 扩展字段  暂填写固定值Sign=WXPay 
	 * @param noncestr String 随机字符串  随机字符串，不长于32位。推荐随机数生成算法 
	 * @param timestamp1 String 时间戳  时间戳，请见接口规则-参数规定 
	 * @param sign String 签名  签名，详见签名生成算法 
	* @param notuse String 没用
	 */
	public WaAppapiPaymentParam(String appid ,String partnerid ,String prepayid ,String package1 ,String noncestr ,String timestamp1 ,String sign ,String notuse,Object notuse2) {
		super();
		this.appid = appid;
		this.partnerid = partnerid;
		this.prepayid = prepayid;
		this.package1 = package1;
		this.noncestr = noncestr;
		this.timestamp1 = timestamp1;
		this.sign = sign;
	}

	
	/**获取公众账号ID 微信分配的公众账号ID */
	

	
	public String getAppid() {
		return this.appid;
	}
	/**设置公众账号ID 微信分配的公众账号ID */

	public void setAppid(String value) {
		this.appid = value;
	}
	/**获取商户号 微信支付分配的商户号 */
	

	
	public String getPartnerid() {
		return this.partnerid;
	}
	/**设置商户号 微信支付分配的商户号 */

	public void setPartnerid(String value) {
		this.partnerid = value;
	}
	/**获取预支付交易会话ID 微信返回的支付交易会话ID */
	

	
	public String getPrepayid() {
		return this.prepayid;
	}
	/**设置预支付交易会话ID 微信返回的支付交易会话ID */

	public void setPrepayid(String value) {
		this.prepayid = value;
	}
	/**获取扩展字段 暂填写固定值Sign=WXPay */
	

	
	public String getPackage1() {
		return this.package1;
	}
	/**设置扩展字段 暂填写固定值Sign=WXPay */

	public void setPackage1(String value) {
		this.package1 = value;
	}
	/**获取随机字符串 随机字符串，不长于32位。推荐随机数生成算法 */
	

	
	public String getNoncestr() {
		return this.noncestr;
	}
	/**设置随机字符串 随机字符串，不长于32位。推荐随机数生成算法 */

	public void setNoncestr(String value) {
		this.noncestr = value;
	}
	/**获取时间戳 时间戳，请见接口规则-参数规定 */
	

	
	public String getTimestamp1() {
		return this.timestamp1;
	}
	/**设置时间戳 时间戳，请见接口规则-参数规定 */

	public void setTimestamp1(String value) {
		this.timestamp1 = value;
	}
	/**获取签名 签名，详见签名生成算法 */
	

	
	public String getSign() {
		return this.sign;
	}
	/**设置签名 签名，详见签名生成算法 */

	public void setSign(String value) {
		this.sign = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Appid:",getAppid())
			.append(",Partnerid:",getPartnerid())
			.append(",Prepayid:",getPrepayid())
			.append(",Package1:",getPackage1())
			.append(",Noncestr:",getNoncestr())
			.append(",Timestamp1:",getTimestamp1())
			.append(",Sign:",getSign())
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
//精简构造 APP调起支付API
WaAppapiPaymentParam waAppapiPaymentParam = new WaAppapiPaymentParam(
	appid , //String 公众账号ID  微信分配的公众账号ID 
	partnerid , //String 商户号  微信支付分配的商户号 
	prepayid , //String 预支付交易会话ID  微信返回的支付交易会话ID 
	package1 , //String 扩展字段  暂填写固定值Sign=WXPay 
	noncestr , //String 随机字符串  随机字符串，不长于32位。推荐随机数生成算法 
	timestamp1 , //String 时间戳  时间戳，请见接口规则-参数规定 
	sign , //String 签名  签名，详见签名生成算法 
	null
);
*/
}
