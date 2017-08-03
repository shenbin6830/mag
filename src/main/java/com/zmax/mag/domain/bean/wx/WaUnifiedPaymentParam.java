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
public class WaUnifiedPaymentParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "支付需要的参数";

	//date formats
	
	//columns START
	/**公众账号ID String  微信分配的公众账号ID */
	
	
	private String appid;
	/**商户号 String  微信支付分配的商户号 */
	
	
	private String mchId;
	/**设备号 String  终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" */
	
	
	private String deviceInfo;
	/**随机字符串 String  随机字符串，不长于32位 */
	
	
	private String nonceStr;
	/**签名 String  签名 */
	
	
	private String sign;
	/**商品描述 String  商品或支付单简要描述 */
	
	
	private String body;
	/**商品详情 String  商品名称明细列表 */
	
	
	private String detail;
	/**附加数据 String  附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 */
	
	
	private String attach;
	/**商户订单号 String  商户系统内部的订单号,32个字符内、可包含字母, */
	
	
	private String outTradeNo;
	/**货币类型 String default=CNY 符合ISO 4217标准的三位字母代码，默认人民币：CNY， */
	
	
	private String feeType;
	/**总金额 Integer  订单总金额，只能为整数 */
	
	
	private Integer totalFee;
	/**终端IP String  APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。 */
	
	
	private String spbillCreateIp;
	/**交易起始时间 String  订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 */
	
	
	private String timeStart;
	/**交易结束时间 String  订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。 */
	
	
	private String timeExpire;
	/**商品标记 String  商品标记，代金券或立减优惠功能的参数， */
	
	
	private String goodsTag;
	/**通知地址 String  接收微信支付异步通知回调地址 */
	
	
	private String notifyUrl;
	/**交易类型 String  取值如下：JSAPI，NATIVE，APP，WAP, */
	
	
	private String tradeType;
	/**商品ID String   */
	
	
	private String productId;
	/**用户标识 String   */
	
	
	private String openid;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaUnifiedPaymentParam _this){
		_this.feeType="CNY";
	}
	public WaUnifiedPaymentParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param appid String 公众账号ID  微信分配的公众账号ID 
	 * @param mchId String 商户号  微信支付分配的商户号 
	 * @param deviceInfo String 设备号  终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" 
	 * @param nonceStr String 随机字符串  随机字符串，不长于32位 
	 * @param sign String 签名  签名 
	 * @param body String 商品描述  商品或支付单简要描述 
	 * @param detail String 商品详情  商品名称明细列表 
	 * @param attach String 附加数据  附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 
	 * @param outTradeNo String 商户订单号  商户系统内部的订单号,32个字符内、可包含字母, 
	 * @param feeType String 货币类型 default=CNY 符合ISO 4217标准的三位字母代码，默认人民币：CNY， 
	 * @param totalFee Integer 总金额  订单总金额，只能为整数 
	 * @param spbillCreateIp String 终端IP  APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。 
	 * @param timeStart String 交易起始时间  订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 
	 * @param timeExpire String 交易结束时间  订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。 
	 * @param goodsTag String 商品标记  商品标记，代金券或立减优惠功能的参数， 
	 * @param notifyUrl String 通知地址  接收微信支付异步通知回调地址 
	 * @param tradeType String 交易类型  取值如下：JSAPI，NATIVE，APP，WAP, 
	 * @param productId String 商品ID   
	 * @param openid String 用户标识   
	* @param notuse String 没用
	 */
	public WaUnifiedPaymentParam(String appid ,String mchId ,String deviceInfo ,String nonceStr ,String sign ,String body ,String detail ,String attach ,String outTradeNo ,String feeType ,Integer totalFee ,String spbillCreateIp ,String timeStart ,String timeExpire ,String goodsTag ,String notifyUrl ,String tradeType ,String productId ,String openid ,String notuse) {
		super();
		this.appid = appid;
		this.mchId = mchId;
		this.deviceInfo = deviceInfo;
		this.nonceStr = nonceStr;
		this.sign = sign;
		this.body = body;
		this.detail = detail;
		this.attach = attach;
		this.outTradeNo = outTradeNo;
		this.feeType = feeType;
		this.totalFee = totalFee;
		this.spbillCreateIp = spbillCreateIp;
		this.timeStart = timeStart;
		this.timeExpire = timeExpire;
		this.goodsTag = goodsTag;
		this.notifyUrl = notifyUrl;
		this.tradeType = tradeType;
		this.productId = productId;
		this.openid = openid;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param appid String 公众账号ID  微信分配的公众账号ID 
	 * @param mchId String 商户号  微信支付分配的商户号 
	 * @param deviceInfo String 设备号  终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" 
	 * @param nonceStr String 随机字符串  随机字符串，不长于32位 
	 * @param sign String 签名  签名 
	 * @param body String 商品描述  商品或支付单简要描述 
	 * @param detail String 商品详情  商品名称明细列表 
	 * @param attach String 附加数据  附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 
	 * @param outTradeNo String 商户订单号  商户系统内部的订单号,32个字符内、可包含字母, 
	 * @param feeType String 货币类型 default=CNY 符合ISO 4217标准的三位字母代码，默认人民币：CNY， 
	 * @param totalFee Integer 总金额  订单总金额，只能为整数 
	 * @param spbillCreateIp String 终端IP  APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。 
	 * @param timeStart String 交易起始时间  订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 
	 * @param timeExpire String 交易结束时间  订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。 
	 * @param goodsTag String 商品标记  商品标记，代金券或立减优惠功能的参数， 
	 * @param notifyUrl String 通知地址  接收微信支付异步通知回调地址 
	 * @param tradeType String 交易类型  取值如下：JSAPI，NATIVE，APP，WAP, 
	 * @param productId String 商品ID   
	 * @param openid String 用户标识   
	* @param notuse String 没用
	 */
	public WaUnifiedPaymentParam(String appid ,String mchId ,String deviceInfo ,String nonceStr ,String sign ,String body ,String detail ,String attach ,String outTradeNo ,String feeType ,Integer totalFee ,String spbillCreateIp ,String timeStart ,String timeExpire ,String goodsTag ,String notifyUrl ,String tradeType ,String productId ,String openid ,String notuse,Object notuse2) {
		super();
		this.appid = appid;
		this.mchId = mchId;
		this.deviceInfo = deviceInfo;
		this.nonceStr = nonceStr;
		this.sign = sign;
		this.body = body;
		this.detail = detail;
		this.attach = attach;
		this.outTradeNo = outTradeNo;
		this.feeType = feeType;
		this.totalFee = totalFee;
		this.spbillCreateIp = spbillCreateIp;
		this.timeStart = timeStart;
		this.timeExpire = timeExpire;
		this.goodsTag = goodsTag;
		this.notifyUrl = notifyUrl;
		this.tradeType = tradeType;
		this.productId = productId;
		this.openid = openid;
	}
	/**
	 * 构造之不能为空的参数
	 * @param appid String 公众账号ID 
	 * @param mchId String 商户号 
	 * @param nonceStr String 随机字符串 
	 * @param sign String 签名 
	 * @param body String 商品描述 
	 * @param outTradeNo String 商户订单号 
	 * @param totalFee Integer 总金额 
	 * @param spbillCreateIp String 终端IP 
	 * @param notifyUrl String 通知地址 
	 * @param tradeType String 交易类型 
	* @param notuse String 没用
	 */
	public WaUnifiedPaymentParam(String _appid,String _mchId,String _nonceStr,String _sign,String _body,String _outTradeNo,Integer _totalFee,String _spbillCreateIp,String _notifyUrl,String _tradeType,String notuse){
		this.appid=_appid;
		this.mchId=_mchId;
		this.nonceStr=_nonceStr;
		this.sign=_sign;
		this.body=_body;
		this.outTradeNo=_outTradeNo;
		this.totalFee=_totalFee;
		this.spbillCreateIp=_spbillCreateIp;
		this.notifyUrl=_notifyUrl;
		this.tradeType=_tradeType;
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
	/**获取设备号 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" */
	@XmlElement(name="device_info")

	
	public String getDeviceInfo() {
		return this.deviceInfo;
	}
	/**设置设备号 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" */

	public void setDeviceInfo(String value) {
		this.deviceInfo = value;
	}
	/**获取随机字符串 随机字符串，不长于32位 */
	@XmlElement(name="nonce_str")

	
	public String getNonceStr() {
		return this.nonceStr;
	}
	/**设置随机字符串 随机字符串，不长于32位 */

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
	/**获取商品描述 商品或支付单简要描述 */
	@XmlElement(name="body")

	
	public String getBody() {
		return this.body;
	}
	/**设置商品描述 商品或支付单简要描述 */

	public void setBody(String value) {
		this.body = value;
	}
	/**获取商品详情 商品名称明细列表 */
	@XmlElement(name="detail")

	
	public String getDetail() {
		return this.detail;
	}
	/**设置商品详情 商品名称明细列表 */

	public void setDetail(String value) {
		this.detail = value;
	}
	/**获取附加数据 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 */
	@XmlElement(name="attach")

	
	public String getAttach() {
		return this.attach;
	}
	/**设置附加数据 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 */

	public void setAttach(String value) {
		this.attach = value;
	}
	/**获取商户订单号 商户系统内部的订单号,32个字符内、可包含字母, */
	@XmlElement(name="out_trade_no")

	
	public String getOutTradeNo() {
		return this.outTradeNo;
	}
	/**设置商户订单号 商户系统内部的订单号,32个字符内、可包含字母, */

	public void setOutTradeNo(String value) {
		this.outTradeNo = value;
	}
	/**获取货币类型 符合ISO 4217标准的三位字母代码，默认人民币：CNY， */
	@XmlElement(name="fee_type")

	
	public String getFeeType() {
		return this.feeType;
	}
	/**设置货币类型 符合ISO 4217标准的三位字母代码，默认人民币：CNY， */

	public void setFeeType(String value) {
		this.feeType = value;
	}
	/**获取总金额 订单总金额，只能为整数 */
	@XmlElement(name="total_fee")

	
	public Integer getTotalFee() {
		return this.totalFee;
	}
	/**设置总金额 订单总金额，只能为整数 */

	public void setTotalFee(Integer value) {
		this.totalFee = value;
	}
	/**获取终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。 */
	@XmlElement(name="spbill_create_ip")

	
	public String getSpbillCreateIp() {
		return this.spbillCreateIp;
	}
	/**设置终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。 */

	public void setSpbillCreateIp(String value) {
		this.spbillCreateIp = value;
	}
	/**获取交易起始时间 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 */
	@XmlElement(name="time_start")

	
	public String getTimeStart() {
		return this.timeStart;
	}
	/**设置交易起始时间 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 */

	public void setTimeStart(String value) {
		this.timeStart = value;
	}
	/**获取交易结束时间 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。 */
	@XmlElement(name="time_expire")

	
	public String getTimeExpire() {
		return this.timeExpire;
	}
	/**设置交易结束时间 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。 */

	public void setTimeExpire(String value) {
		this.timeExpire = value;
	}
	/**获取商品标记 商品标记，代金券或立减优惠功能的参数， */
	@XmlElement(name="goods_tag")

	
	public String getGoodsTag() {
		return this.goodsTag;
	}
	/**设置商品标记 商品标记，代金券或立减优惠功能的参数， */

	public void setGoodsTag(String value) {
		this.goodsTag = value;
	}
	/**获取通知地址 接收微信支付异步通知回调地址 */
	@XmlElement(name="notify_url")

	
	public String getNotifyUrl() {
		return this.notifyUrl;
	}
	/**设置通知地址 接收微信支付异步通知回调地址 */

	public void setNotifyUrl(String value) {
		this.notifyUrl = value;
	}
	/**获取交易类型 取值如下：JSAPI，NATIVE，APP，WAP, */
	@XmlElement(name="trade_type")

	
	public String getTradeType() {
		return this.tradeType;
	}
	/**设置交易类型 取值如下：JSAPI，NATIVE，APP，WAP, */

	public void setTradeType(String value) {
		this.tradeType = value;
	}
	/**获取商品ID  */
	@XmlElement(name="product_id")

	
	public String getProductId() {
		return this.productId;
	}
	/**设置商品ID  */

	public void setProductId(String value) {
		this.productId = value;
	}
	/**获取用户标识  */
	@XmlElement(name="openid")

	
	public String getOpenid() {
		return this.openid;
	}
	/**设置用户标识  */

	public void setOpenid(String value) {
		this.openid = value;
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
			.append(",DeviceInfo:",getDeviceInfo())
			.append(",NonceStr:",getNonceStr())
			.append(",Sign:",getSign())
			.append(",Body:",getBody())
			.append(",Detail:",getDetail())
			.append(",Attach:",getAttach())
			.append(",OutTradeNo:",getOutTradeNo())
			.append(",FeeType:",getFeeType())
			.append(",TotalFee:",getTotalFee())
			.append(",SpbillCreateIp:",getSpbillCreateIp())
			.append(",TimeStart:",getTimeStart())
			.append(",TimeExpire:",getTimeExpire())
			.append(",GoodsTag:",getGoodsTag())
			.append(",NotifyUrl:",getNotifyUrl())
			.append(",TradeType:",getTradeType())
			.append(",ProductId:",getProductId())
			.append(",Openid:",getOpenid())
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
WaUnifiedPaymentParam waUnifiedPaymentParam = new WaUnifiedPaymentParam(
	appid , //String 公众账号ID  微信分配的公众账号ID 
	mchId , //String 商户号  微信支付分配的商户号 
	deviceInfo , //String 设备号  终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" 
	nonceStr , //String 随机字符串  随机字符串，不长于32位 
	sign , //String 签名  签名 
	body , //String 商品描述  商品或支付单简要描述 
	detail , //String 商品详情  商品名称明细列表 
	attach , //String 附加数据  附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 
	outTradeNo , //String 商户订单号  商户系统内部的订单号,32个字符内、可包含字母, 
	feeType , //String 货币类型 default=CNY 符合ISO 4217标准的三位字母代码，默认人民币：CNY， 
	totalFee , //Integer 总金额  订单总金额，只能为整数 
	spbillCreateIp , //String 终端IP  APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。 
	timeStart , //String 交易起始时间  订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。 
	timeExpire , //String 交易结束时间  订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。 
	goodsTag , //String 商品标记  商品标记，代金券或立减优惠功能的参数， 
	notifyUrl , //String 通知地址  接收微信支付异步通知回调地址 
	tradeType , //String 交易类型  取值如下：JSAPI，NATIVE，APP，WAP, 
	productId , //String 商品ID   
	openid , //String 用户标识   
	null
);
*/
}
