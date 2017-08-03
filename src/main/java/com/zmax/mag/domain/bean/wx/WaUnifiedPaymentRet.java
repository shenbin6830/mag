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
 * 请求支付的返回值
 * @author zmax
 * @version 1.0
 * @since 
 */

@XmlRootElement(name="xml")
public class WaUnifiedPaymentRet extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "请求支付的返回值";

	//date formats
	
	//columns START
	/**返回状态码 String  SUCCESS/FAIL */
	
	
	private String returnCode;
	/**返回信息 String  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 */
	
	
	private String returnMsg;
	/**公众账号ID String  调用接口提交的公众账号ID */
	
	
	private String appid;
	/**商户号 String  调用接口提交的商户号 */
	
	
	private String mchId;
	/**设备号 String  调用接口提交的终端设备号 */
	
	
	private String deviceInfo;
	/**随机字符串 String  微信返回的随机字符串 */
	
	
	private String nonceStr;
	/**签名 String  微信返回的签名 */
	
	
	private String sign;
	/**业务结果 String  SUCCESS/FAIL */
	
	
	private String resultCode;
	/**错误代码 String   */
	
	
	private String errCode;
	/**错误代码描述 String  错误返回的信息描述 */
	
	
	private String errCodeDes;
	/**交易类型 String  调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP */
	
	
	private String tradeType;
	/**预支付交易会话标识 String  微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时 */
	
	
	private String prepayId;
	/**二维码链接 String  trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付 */
	
	
	private String codeUrl;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaUnifiedPaymentRet _this){
	}
	public WaUnifiedPaymentRet(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param returnCode String 返回状态码  SUCCESS/FAIL 
	 * @param returnMsg String 返回信息  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 
	 * @param appid String 公众账号ID  调用接口提交的公众账号ID 
	 * @param mchId String 商户号  调用接口提交的商户号 
	 * @param deviceInfo String 设备号  调用接口提交的终端设备号 
	 * @param nonceStr String 随机字符串  微信返回的随机字符串 
	 * @param sign String 签名  微信返回的签名 
	 * @param resultCode String 业务结果  SUCCESS/FAIL 
	 * @param errCode String 错误代码   
	 * @param errCodeDes String 错误代码描述  错误返回的信息描述 
	 * @param tradeType String 交易类型  调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP 
	 * @param prepayId String 预支付交易会话标识  微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时 
	 * @param codeUrl String 二维码链接  trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付 
	* @param notuse String 没用
	 */
	public WaUnifiedPaymentRet(String returnCode ,String returnMsg ,String appid ,String mchId ,String deviceInfo ,String nonceStr ,String sign ,String resultCode ,String errCode ,String errCodeDes ,String tradeType ,String prepayId ,String codeUrl ,String notuse) {
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
		this.tradeType = tradeType;
		this.prepayId = prepayId;
		this.codeUrl = codeUrl;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param returnCode String 返回状态码  SUCCESS/FAIL 
	 * @param returnMsg String 返回信息  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 
	 * @param appid String 公众账号ID  调用接口提交的公众账号ID 
	 * @param mchId String 商户号  调用接口提交的商户号 
	 * @param deviceInfo String 设备号  调用接口提交的终端设备号 
	 * @param nonceStr String 随机字符串  微信返回的随机字符串 
	 * @param sign String 签名  微信返回的签名 
	 * @param resultCode String 业务结果  SUCCESS/FAIL 
	 * @param errCode String 错误代码   
	 * @param errCodeDes String 错误代码描述  错误返回的信息描述 
	 * @param tradeType String 交易类型  调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP 
	 * @param prepayId String 预支付交易会话标识  微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时 
	 * @param codeUrl String 二维码链接  trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付 
	* @param notuse String 没用
	 */
	public WaUnifiedPaymentRet(String returnCode ,String returnMsg ,String appid ,String mchId ,String deviceInfo ,String nonceStr ,String sign ,String resultCode ,String errCode ,String errCodeDes ,String tradeType ,String prepayId ,String codeUrl ,String notuse,Object notuse2) {
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
		this.tradeType = tradeType;
		this.prepayId = prepayId;
		this.codeUrl = codeUrl;
	}
	/**
	 * 构造之不能为空的参数
	 * @param returnCode String 返回状态码 
	 * @param returnMsg String 返回信息 
	 * @param appid String 公众账号ID 
	 * @param mchId String 商户号 
	 * @param nonceStr String 随机字符串 
	 * @param sign String 签名 
	 * @param resultCode String 业务结果 
	* @param notuse String 没用
	 */
	public WaUnifiedPaymentRet(String _returnCode,String _returnMsg,String _appid,String _mchId,String _nonceStr,String _sign,String _resultCode,String notuse){
		this.returnCode=_returnCode;
		this.returnMsg=_returnMsg;
		this.appid=_appid;
		this.mchId=_mchId;
		this.nonceStr=_nonceStr;
		this.sign=_sign;
		this.resultCode=_resultCode;
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
	/**获取公众账号ID 调用接口提交的公众账号ID */
	@XmlElement(name="appid")

	
	public String getAppid() {
		return this.appid;
	}
	/**设置公众账号ID 调用接口提交的公众账号ID */

	public void setAppid(String value) {
		this.appid = value;
	}
	/**获取商户号 调用接口提交的商户号 */
	@XmlElement(name="mch_id")

	
	public String getMchId() {
		return this.mchId;
	}
	/**设置商户号 调用接口提交的商户号 */

	public void setMchId(String value) {
		this.mchId = value;
	}
	/**获取设备号 调用接口提交的终端设备号 */
	@XmlElement(name="device_info")

	
	public String getDeviceInfo() {
		return this.deviceInfo;
	}
	/**设置设备号 调用接口提交的终端设备号 */

	public void setDeviceInfo(String value) {
		this.deviceInfo = value;
	}
	/**获取随机字符串 微信返回的随机字符串 */
	@XmlElement(name="nonce_str")

	
	public String getNonceStr() {
		return this.nonceStr;
	}
	/**设置随机字符串 微信返回的随机字符串 */

	public void setNonceStr(String value) {
		this.nonceStr = value;
	}
	/**获取签名 微信返回的签名 */
	@XmlElement(name="sign")

	
	public String getSign() {
		return this.sign;
	}
	/**设置签名 微信返回的签名 */

	public void setSign(String value) {
		this.sign = value;
	}
	/**获取业务结果 SUCCESS/FAIL */
	@XmlElement(name="result_code")

	
	public String getResultCode() {
		return this.resultCode;
	}
	/**设置业务结果 SUCCESS/FAIL */

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
	/**获取错误代码描述 错误返回的信息描述 */
	@XmlElement(name="err_code_des")

	
	public String getErrCodeDes() {
		return this.errCodeDes;
	}
	/**设置错误代码描述 错误返回的信息描述 */

	public void setErrCodeDes(String value) {
		this.errCodeDes = value;
	}
	/**获取交易类型 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP */
	@XmlElement(name="trade_type")

	
	public String getTradeType() {
		return this.tradeType;
	}
	/**设置交易类型 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP */

	public void setTradeType(String value) {
		this.tradeType = value;
	}
	/**获取预支付交易会话标识 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时 */
	@XmlElement(name="prepay_id")

	
	public String getPrepayId() {
		return this.prepayId;
	}
	/**设置预支付交易会话标识 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时 */

	public void setPrepayId(String value) {
		this.prepayId = value;
	}
	/**获取二维码链接 trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付 */
	@XmlElement(name="code_url")

	
	public String getCodeUrl() {
		return this.codeUrl;
	}
	/**设置二维码链接 trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付 */

	public void setCodeUrl(String value) {
		this.codeUrl = value;
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
			.append(",DeviceInfo:",getDeviceInfo())
			.append(",NonceStr:",getNonceStr())
			.append(",Sign:",getSign())
			.append(",ResultCode:",getResultCode())
			.append(",ErrCode:",getErrCode())
			.append(",ErrCodeDes:",getErrCodeDes())
			.append(",TradeType:",getTradeType())
			.append(",PrepayId:",getPrepayId())
			.append(",CodeUrl:",getCodeUrl())
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
//精简构造 请求支付的返回值
WaUnifiedPaymentRet waUnifiedPaymentRet = new WaUnifiedPaymentRet(
	returnCode , //String 返回状态码  SUCCESS/FAIL 
	returnMsg , //String 返回信息  返回信息，如非空，为错误原因 签名失败 参数格式校验错误 
	appid , //String 公众账号ID  调用接口提交的公众账号ID 
	mchId , //String 商户号  调用接口提交的商户号 
	deviceInfo , //String 设备号  调用接口提交的终端设备号 
	nonceStr , //String 随机字符串  微信返回的随机字符串 
	sign , //String 签名  微信返回的签名 
	resultCode , //String 业务结果  SUCCESS/FAIL 
	errCode , //String 错误代码   
	errCodeDes , //String 错误代码描述  错误返回的信息描述 
	tradeType , //String 交易类型  调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP 
	prepayId , //String 预支付交易会话标识  微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时 
	codeUrl , //String 二维码链接  trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付 
	null
);
*/
}
