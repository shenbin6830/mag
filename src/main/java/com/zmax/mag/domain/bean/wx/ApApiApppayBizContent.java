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
 * 支付宝App业务请求参数的集合
 * @author zmax
 * @version 1.0
 * @since 
 */

public class ApApiApppayBizContent extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "支付宝App业务请求参数的集合";

	//date formats
	
	//columns START
	/**具体描述信息 String  对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body */
	
	
	private String body;
	/**商品的标题 String  交易标题/订单标题/订单关键字等 */
	
	
	private String subject;
	/**唯一订单号 String   */
	
	
	private String outTradeNo;
	/**允许的最晚付款时间 String  取值范围：1m～15d */
	
	
	private String timeoutExpress;
	/**订单总金额 String  单位为元，精确到小数点后两位，取值范围[0.01,100000000] */
	
	
	private String totalAmount;
	/**收款支付宝用户ID String   */
	
	
	private String sellerId;
	/**销售产品码*/
	private String productCode;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(ApApiApppayBizContent _this){
		_this.productCode="QUICK_MSECURITY_PAY";
	}
	public ApApiApppayBizContent(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param body String 具体描述信息  对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body 
	 * @param subject String 商品的标题  交易标题/订单标题/订单关键字等 
	 * @param outTradeNo String 唯一订单号   
	 * @param timeoutExpress String 允许的最晚付款时间  取值范围：1m～15d 
	 * @param totalAmount String 订单总金额  单位为元，精确到小数点后两位，取值范围[0.01,100000000] 
	 * @param sellerId String 收款支付宝用户ID   
	* @param notuse String 没用
	 */
	public ApApiApppayBizContent(String body ,String subject ,String outTradeNo ,String timeoutExpress ,String totalAmount ,String sellerId ,String notuse) {
		super();
		this.body = body;
		this.subject = subject;
		this.outTradeNo = outTradeNo;
		this.timeoutExpress = timeoutExpress;
		this.totalAmount = totalAmount;
		this.sellerId = sellerId;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param body String 具体描述信息  对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body 
	 * @param subject String 商品的标题  交易标题/订单标题/订单关键字等 
	 * @param outTradeNo String 唯一订单号   
	 * @param timeoutExpress String 允许的最晚付款时间  取值范围：1m～15d 
	 * @param totalAmount String 订单总金额  单位为元，精确到小数点后两位，取值范围[0.01,100000000] 
	 * @param sellerId String 收款支付宝用户ID   
	 * @param productCode String 销售产品码 default=QUICK_MSECURITY_PAY  
	* @param notuse String 没用
	 */
	public ApApiApppayBizContent(String body ,String subject ,String outTradeNo ,String timeoutExpress ,String totalAmount ,String sellerId ,String productCode ,String notuse,Object notuse2) {
		super();
		this.body = body;
		this.subject = subject;
		this.outTradeNo = outTradeNo;
		this.timeoutExpress = timeoutExpress;
		this.totalAmount = totalAmount;
		this.sellerId = sellerId;
		this.productCode = productCode;
	}

	
	/**获取具体描述信息 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body */
	
@JSONField(name="body")
	
	public String getBody() {
		return this.body;
	}
	/**设置具体描述信息 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body */
@JSONField(name="body")
	public void setBody(String value) {
		this.body = value;
	}
	/**获取商品的标题 交易标题/订单标题/订单关键字等 */
	
@JSONField(name="subject")
	
	public String getSubject() {
		return this.subject;
	}
	/**设置商品的标题 交易标题/订单标题/订单关键字等 */
@JSONField(name="subject")
	public void setSubject(String value) {
		this.subject = value;
	}
	/**获取唯一订单号  */
	
@JSONField(name="out_trade_no")
	
	public String getOutTradeNo() {
		return this.outTradeNo;
	}
	/**设置唯一订单号  */
@JSONField(name="out_trade_no")
	public void setOutTradeNo(String value) {
		this.outTradeNo = value;
	}
	/**获取允许的最晚付款时间 取值范围：1m～15d */
	
@JSONField(name="timeout_express")
	
	public String getTimeoutExpress() {
		return this.timeoutExpress;
	}
	/**设置允许的最晚付款时间 取值范围：1m～15d */
@JSONField(name="timeout_express")
	public void setTimeoutExpress(String value) {
		this.timeoutExpress = value;
	}
	/**获取订单总金额 单位为元，精确到小数点后两位，取值范围[0.01,100000000] */
	
@JSONField(name="total_amount")
	
	public String getTotalAmount() {
		return this.totalAmount;
	}
	/**设置订单总金额 单位为元，精确到小数点后两位，取值范围[0.01,100000000] */
@JSONField(name="total_amount")
	public void setTotalAmount(String value) {
		this.totalAmount = value;
	}
	/**获取收款支付宝用户ID  */
	
@JSONField(name="seller_id")
	
	public String getSellerId() {
		return this.sellerId;
	}
	/**设置收款支付宝用户ID  */
@JSONField(name="seller_id")
	public void setSellerId(String value) {
		this.sellerId = value;
	}
	/**对象 获取销售产品码  */
	
	
@JSONField(name="product_code")
	public String getProductCode() {
		return this.productCode;
	}
	/**设置销售产品码  */
@JSONField(name="product_code")
	public void setProductCode(String value) {
		this.productCode = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Body:",getBody())
			.append(",Subject:",getSubject())
			.append(",OutTradeNo:",getOutTradeNo())
			.append(",TimeoutExpress:",getTimeoutExpress())
			.append(",TotalAmount:",getTotalAmount())
			.append(",SellerId:",getSellerId())
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
//精简构造 支付宝App业务请求参数的集合
ApApiApppayBizContent apApiApppayBizContent = new ApApiApppayBizContent(
	body , //String 具体描述信息  对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body 
	subject , //String 商品的标题  交易标题/订单标题/订单关键字等 
	outTradeNo , //String 唯一订单号   
	timeoutExpress , //String 允许的最晚付款时间  取值范围：1m～15d 
	totalAmount , //String 订单总金额  单位为元，精确到小数点后两位，取值范围[0.01,100000000] 
	sellerId , //String 收款支付宝用户ID   
	null
);
*/
}
