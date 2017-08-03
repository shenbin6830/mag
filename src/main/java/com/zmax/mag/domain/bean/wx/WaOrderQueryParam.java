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
public class WaOrderQueryParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "支付需要的参数";

	//date formats
	
	//columns START
	/**公众账号ID String  微信分配的公众账号ID */
	
	
	private String appid;
	/**商户号 String  微信支付分配的商户号 */
	
	
	private String mchId;
	/**微信订单号 String  微信的订单号，优先使用 */
	
	
	private String transactionId;
	/**商户订单号 String  商户系统内部的订单号，当没提供transaction_id时需要传这个。 */
	
	
	private String outTradeNo;
	/**随机字符串 String  随机字符串，不长于32位。 */
	
	
	private String nonceStr;
	/**签名 String  签名 */
	
	
	private String sign;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaOrderQueryParam _this){
	}
	public WaOrderQueryParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param appid String 公众账号ID  微信分配的公众账号ID 
	 * @param mchId String 商户号  微信支付分配的商户号 
	 * @param transactionId String 微信订单号  微信的订单号，优先使用 
	 * @param outTradeNo String 商户订单号  商户系统内部的订单号，当没提供transaction_id时需要传这个。 
	 * @param nonceStr String 随机字符串  随机字符串，不长于32位。 
	 * @param sign String 签名  签名 
	* @param notuse String 没用
	 */
	public WaOrderQueryParam(String appid ,String mchId ,String transactionId ,String outTradeNo ,String nonceStr ,String sign ,String notuse) {
		super();
		this.appid = appid;
		this.mchId = mchId;
		this.transactionId = transactionId;
		this.outTradeNo = outTradeNo;
		this.nonceStr = nonceStr;
		this.sign = sign;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param appid String 公众账号ID  微信分配的公众账号ID 
	 * @param mchId String 商户号  微信支付分配的商户号 
	 * @param transactionId String 微信订单号  微信的订单号，优先使用 
	 * @param outTradeNo String 商户订单号  商户系统内部的订单号，当没提供transaction_id时需要传这个。 
	 * @param nonceStr String 随机字符串  随机字符串，不长于32位。 
	 * @param sign String 签名  签名 
	* @param notuse String 没用
	 */
	public WaOrderQueryParam(String appid ,String mchId ,String transactionId ,String outTradeNo ,String nonceStr ,String sign ,String notuse,Object notuse2) {
		super();
		this.appid = appid;
		this.mchId = mchId;
		this.transactionId = transactionId;
		this.outTradeNo = outTradeNo;
		this.nonceStr = nonceStr;
		this.sign = sign;
	}
	/**
	 * 构造之不能为空的参数
	 * @param appid String 公众账号ID 
	 * @param mchId String 商户号 
	 * @param nonceStr String 随机字符串 
	 * @param sign String 签名 
	* @param notuse String 没用
	 */
	public WaOrderQueryParam(String _appid,String _mchId,String _nonceStr,String _sign,String notuse){
		this.appid=_appid;
		this.mchId=_mchId;
		this.nonceStr=_nonceStr;
		this.sign=_sign;
		makedefault(this);
	}

	
	/**获取公众账号ID 微信分配的公众账号ID */
	@XmlElement(name="appid")

	
	public String getAppid() {
		return this.appid;
	}
	/**设置公众账号ID 微信分配的公众账号ID */

	public void setAppid(String value) {
		this.appid = value;
	}
	/**获取商户号 微信支付分配的商户号 */
	@XmlElement(name="mch_id")

	
	public String getMchId() {
		return this.mchId;
	}
	/**设置商户号 微信支付分配的商户号 */

	public void setMchId(String value) {
		this.mchId = value;
	}
	/**获取微信订单号 微信的订单号，优先使用 */
	@XmlElement(name="transaction_id")

	
	public String getTransactionId() {
		return this.transactionId;
	}
	/**设置微信订单号 微信的订单号，优先使用 */

	public void setTransactionId(String value) {
		this.transactionId = value;
	}
	/**获取商户订单号 商户系统内部的订单号，当没提供transaction_id时需要传这个。 */
	@XmlElement(name="out_trade_no")

	
	public String getOutTradeNo() {
		return this.outTradeNo;
	}
	/**设置商户订单号 商户系统内部的订单号，当没提供transaction_id时需要传这个。 */

	public void setOutTradeNo(String value) {
		this.outTradeNo = value;
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
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Appid:",getAppid())
			.append(",MchId:",getMchId())
			.append(",TransactionId:",getTransactionId())
			.append(",OutTradeNo:",getOutTradeNo())
			.append(",NonceStr:",getNonceStr())
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
//精简构造 支付需要的参数
WaOrderQueryParam waOrderQueryParam = new WaOrderQueryParam(
	appid , //String 公众账号ID  微信分配的公众账号ID 
	mchId , //String 商户号  微信支付分配的商户号 
	transactionId , //String 微信订单号  微信的订单号，优先使用 
	outTradeNo , //String 商户订单号  商户系统内部的订单号，当没提供transaction_id时需要传这个。 
	nonceStr , //String 随机字符串  随机字符串，不长于32位。 
	sign , //String 签名  签名 
	null
);
*/
}
