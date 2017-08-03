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
 * 网页端调起支付API
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaJsapiPaymentParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "网页端调起支付API";

	//date formats
	
	//columns START
	/**公众号id String  商户注册具有支付权限的公众号成功后即可获得 */
	
	
	private String appid;
	/**时间戳 Long  当前的时间 */
	
	
	private Long timestamp1;
	/**随机字符串 String  随机字符串，不长于32位。 */
	
	
	private String noncestr;
	/**订单详情扩展字符串 String  统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=*** */
	
	
	private String package1;
	/**签名方式 String default=MD5 签名算法，暂支持MD5 */
	
	
	private String signtype;
	/**签名 String  签名 */
	
	
	private String paysign;
	/**预支付交易会话ID String  H5对象不需要这个 */
	
	
	private String prepayid;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaJsapiPaymentParam _this){
		_this.signtype="MD5";
	}
	public WaJsapiPaymentParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param appid String 公众号id  商户注册具有支付权限的公众号成功后即可获得 
	 * @param timestamp1 Long 时间戳  当前的时间 
	 * @param noncestr String 随机字符串  随机字符串，不长于32位。 
	 * @param package1 String 订单详情扩展字符串  统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=*** 
	 * @param signtype String 签名方式 default=MD5 签名算法，暂支持MD5 
	 * @param paysign String 签名  签名 
	 * @param prepayid String 预支付交易会话ID  H5对象不需要这个 
	* @param notuse String 没用
	 */
	public WaJsapiPaymentParam(String appid ,Long timestamp1 ,String noncestr ,String package1 ,String signtype ,String paysign ,String prepayid ,String notuse) {
		super();
		this.appid = appid;
		this.timestamp1 = timestamp1;
		this.noncestr = noncestr;
		this.package1 = package1;
		this.signtype = signtype;
		this.paysign = paysign;
		this.prepayid = prepayid;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param appid String 公众号id  商户注册具有支付权限的公众号成功后即可获得 
	 * @param timestamp1 Long 时间戳  当前的时间 
	 * @param noncestr String 随机字符串  随机字符串，不长于32位。 
	 * @param package1 String 订单详情扩展字符串  统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=*** 
	 * @param signtype String 签名方式 default=MD5 签名算法，暂支持MD5 
	 * @param paysign String 签名  签名 
	 * @param prepayid String 预支付交易会话ID  H5对象不需要这个 
	* @param notuse String 没用
	 */
	public WaJsapiPaymentParam(String appid ,Long timestamp1 ,String noncestr ,String package1 ,String signtype ,String paysign ,String prepayid ,String notuse,Object notuse2) {
		super();
		this.appid = appid;
		this.timestamp1 = timestamp1;
		this.noncestr = noncestr;
		this.package1 = package1;
		this.signtype = signtype;
		this.paysign = paysign;
		this.prepayid = prepayid;
	}

	
	/**获取公众号id 商户注册具有支付权限的公众号成功后即可获得 */
	

	
	public String getAppid() {
		return this.appid;
	}
	/**设置公众号id 商户注册具有支付权限的公众号成功后即可获得 */

	public void setAppid(String value) {
		this.appid = value;
	}
	/**获取时间戳 当前的时间 */
	

	
	public Long getTimestamp1() {
		return this.timestamp1;
	}
	/**设置时间戳 当前的时间 */

	public void setTimestamp1(Long value) {
		this.timestamp1 = value;
	}
	/**获取随机字符串 随机字符串，不长于32位。 */
	

	
	public String getNoncestr() {
		return this.noncestr;
	}
	/**设置随机字符串 随机字符串，不长于32位。 */

	public void setNoncestr(String value) {
		this.noncestr = value;
	}
	/**获取订单详情扩展字符串 统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=*** */
	

	
	public String getPackage1() {
		return this.package1;
	}
	/**设置订单详情扩展字符串 统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=*** */

	public void setPackage1(String value) {
		this.package1 = value;
	}
	/**获取签名方式 签名算法，暂支持MD5 */
	

	
	public String getSigntype() {
		return this.signtype;
	}
	/**设置签名方式 签名算法，暂支持MD5 */

	public void setSigntype(String value) {
		this.signtype = value;
	}
	/**获取签名 签名 */
	

	
	public String getPaysign() {
		return this.paysign;
	}
	/**设置签名 签名 */

	public void setPaysign(String value) {
		this.paysign = value;
	}
	/**获取预支付交易会话ID H5对象不需要这个 */
	

	
	public String getPrepayid() {
		return this.prepayid;
	}
	/**设置预支付交易会话ID H5对象不需要这个 */

	public void setPrepayid(String value) {
		this.prepayid = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Appid:",getAppid())
			.append(",Timestamp1:",getTimestamp1())
			.append(",Noncestr:",getNoncestr())
			.append(",Package1:",getPackage1())
			.append(",Signtype:",getSigntype())
			.append(",Paysign:",getPaysign())
			.append(",Prepayid:",getPrepayid())
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
//精简构造 网页端调起支付API
WaJsapiPaymentParam waJsapiPaymentParam = new WaJsapiPaymentParam(
	appid , //String 公众号id  商户注册具有支付权限的公众号成功后即可获得 
	timestamp1 , //Long 时间戳  当前的时间 
	noncestr , //String 随机字符串  随机字符串，不长于32位。 
	package1 , //String 订单详情扩展字符串  统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=*** 
	signtype , //String 签名方式 default=MD5 签名算法，暂支持MD5 
	paysign , //String 签名  签名 
	prepayid , //String 预支付交易会话ID  H5对象不需要这个 
	null
);
*/
}
