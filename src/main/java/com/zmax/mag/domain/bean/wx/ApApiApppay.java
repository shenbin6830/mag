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
 * 支付宝App支付请求参数<br/>2016版，外部商户App唤起快捷SDK创建订单并支付
 * @author zmax
 * @version 1.0
 * @since 
 */

public class ApApiApppay extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "支付宝App支付请求参数";

	//date formats
	
	//columns START
	/**应用ID String   */
	
	
	private String appId;
	/**接口名称*/
	private String method;
	/**仅支持JSON*/
	private String format;
	/**编码格式*/
	private String charset;
	/**签名算法类型*/
	private String signType;
	/**签名 String   */
	
	
	private String sign;
	/**发送请求的时间 String  yyyy-MM-dd HH:mm:ss */
	
	
	private String timestamp1;
	/**调用的接口版本*/
	private String version;
	/**app异步回调路径 String  https://api.xx.com/receive_notify.htm */
	
	
	private String notifyUrl;
	/**业务请求参数的集合 String  没长度限制，是ApApiApppayBizContent的JSON串 */
	
	
	private String bizContent;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(ApApiApppay _this){
		_this.method="alipay.trade.app.pay";
		_this.format="JSON";
		_this.charset="UTF-8";
		_this.signType="RSA";
		_this.version="1.0";
	}
	public ApApiApppay(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param appId String 应用ID   
	 * @param sign String 签名   
	 * @param timestamp1 String 发送请求的时间  yyyy-MM-dd HH:mm:ss 
	 * @param notifyUrl String app异步回调路径  https://api.xx.com/receive_notify.htm 
	 * @param bizContent String 业务请求参数的集合  没长度限制，是ApApiApppayBizContent的JSON串 
	* @param notuse String 没用
	 */
	public ApApiApppay(String appId ,String sign ,String timestamp1 ,String notifyUrl ,String bizContent ,String notuse) {
		super();
		this.appId = appId;
		this.sign = sign;
		this.timestamp1 = timestamp1;
		this.notifyUrl = notifyUrl;
		this.bizContent = bizContent;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param appId String 应用ID   
	 * @param method String 接口名称 default=alipay.trade.app.pay  
	 * @param format String 仅支持JSON default=JSON  
	 * @param charset String 编码格式 default=UTF-8  
	 * @param signType String 签名算法类型 default=RSA  
	 * @param sign String 签名   
	 * @param timestamp1 String 发送请求的时间  yyyy-MM-dd HH:mm:ss 
	 * @param version String 调用的接口版本 default=1.0  
	 * @param notifyUrl String app异步回调路径  https://api.xx.com/receive_notify.htm 
	 * @param bizContent String 业务请求参数的集合  没长度限制，是ApApiApppayBizContent的JSON串 
	* @param notuse String 没用
	 */
	public ApApiApppay(String appId ,String method ,String format ,String charset ,String signType ,String sign ,String timestamp1 ,String version ,String notifyUrl ,String bizContent ,String notuse,Object notuse2) {
		super();
		this.appId = appId;
		this.method = method;
		this.format = format;
		this.charset = charset;
		this.signType = signType;
		this.sign = sign;
		this.timestamp1 = timestamp1;
		this.version = version;
		this.notifyUrl = notifyUrl;
		this.bizContent = bizContent;
	}

	
	/**获取应用ID  */
	
@JSONField(name="app_id")
	
	public String getAppId() {
		return this.appId;
	}
	/**设置应用ID  */
@JSONField(name="app_id")
	public void setAppId(String value) {
		this.appId = value;
	}
	/**对象 获取接口名称  */
	
	
@JSONField(name="method")
	public String getMethod() {
		return this.method;
	}
	/**设置接口名称  */
@JSONField(name="method")
	public void setMethod(String value) {
		this.method = value;
	}
	/**对象 获取仅支持JSON  */
	
	
@JSONField(name="format")
	public String getFormat() {
		return this.format;
	}
	/**设置仅支持JSON  */
@JSONField(name="format")
	public void setFormat(String value) {
		this.format = value;
	}
	/**对象 获取编码格式  */
	
	
@JSONField(name="charset")
	public String getCharset() {
		return this.charset;
	}
	/**设置编码格式  */
@JSONField(name="charset")
	public void setCharset(String value) {
		this.charset = value;
	}
	/**对象 获取签名算法类型  */
	
	
@JSONField(name="sign_type")
	public String getSignType() {
		return this.signType;
	}
	/**设置签名算法类型  */
@JSONField(name="sign_type")
	public void setSignType(String value) {
		this.signType = value;
	}
	/**获取签名  */
	
@JSONField(name="sign")
	
	public String getSign() {
		return this.sign;
	}
	/**设置签名  */
@JSONField(name="sign")
	public void setSign(String value) {
		this.sign = value;
	}
	/**获取发送请求的时间 yyyy-MM-dd HH:mm:ss */
	
@JSONField(name="timestamp")
	
	public String getTimestamp1() {
		return this.timestamp1;
	}
	/**设置发送请求的时间 yyyy-MM-dd HH:mm:ss */
@JSONField(name="timestamp")
	public void setTimestamp1(String value) {
		this.timestamp1 = value;
	}
	/**对象 获取调用的接口版本  */
	
	
@JSONField(name="version")
	public String getVersion() {
		return this.version;
	}
	/**设置调用的接口版本  */
@JSONField(name="version")
	public void setVersion(String value) {
		this.version = value;
	}
	/**获取app异步回调路径 https://api.xx.com/receive_notify.htm */
	
@JSONField(name="notify_url")
	
	public String getNotifyUrl() {
		return this.notifyUrl;
	}
	/**设置app异步回调路径 https://api.xx.com/receive_notify.htm */
@JSONField(name="notify_url")
	public void setNotifyUrl(String value) {
		this.notifyUrl = value;
	}
	/**获取业务请求参数的集合 没长度限制，是ApApiApppayBizContent的JSON串 */
	
@JSONField(name="biz_content")
	
	public String getBizContent() {
		return this.bizContent;
	}
	/**设置业务请求参数的集合 没长度限制，是ApApiApppayBizContent的JSON串 */
@JSONField(name="biz_content")
	public void setBizContent(String value) {
		this.bizContent = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",AppId:",getAppId())
			.append(",Sign:",getSign())
			.append(",Timestamp1:",getTimestamp1())
			.append(",NotifyUrl:",getNotifyUrl())
			.append(",BizContent:",getBizContent())
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
//精简构造 支付宝App支付请求参数
ApApiApppay apApiApppay = new ApApiApppay(
	appId , //String 应用ID   
	sign , //String 签名   
	timestamp1 , //String 发送请求的时间  yyyy-MM-dd HH:mm:ss 
	notifyUrl , //String app异步回调路径  https://api.xx.com/receive_notify.htm 
	bizContent , //String 业务请求参数的集合  没长度限制，是ApApiApppayBizContent的JSON串 
	null
);
*/
}
