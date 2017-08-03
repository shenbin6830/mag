/*
 * zmax 
 * 
 */


// BaseResult 
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
 * 调用微信JS接口的临时票据<br/>jsapi_ticket是公众号用于调用微信JS接口的临时票据。正常情况下，jsapi_ticket的有效期为7200秒，通过access_token来获取。由于获取jsapi_ticket的api调用次数非常有限，频繁刷新jsapi_ticket会导致api调用受限，影响自身业务，开发者必须在自己的服务全局缓存jsapi_ticket 。 
 * @author zmax
 * @version 1.0
 * @since 
 */

public class JsapiTicket extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "调用微信JS接口的临时票据";

	//date formats
	
	//columns START
	/**ticket String   */
	
	
	private String ticket;
	/**凭证有效时间 Integer  单位：秒 */
	
	
	private Integer expiresIn;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(JsapiTicket _this){
	}
	public JsapiTicket(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param ticket String ticket   
	 * @param expiresIn Integer 凭证有效时间  单位：秒 
	* @param notuse String 没用
	 */
	public JsapiTicket(String ticket ,Integer expiresIn ,String notuse) {
		super();
		this.ticket = ticket;
		this.expiresIn = expiresIn;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param ticket String ticket   
	 * @param expiresIn Integer 凭证有效时间  单位：秒 
	* @param notuse String 没用
	 */
	public JsapiTicket(String ticket ,Integer expiresIn ,String notuse,Object notuse2) {
		super();
		this.ticket = ticket;
		this.expiresIn = expiresIn;
	}

	
	/**获取ticket  */
	
@JSONField(name="ticket")
	
	public String getTicket() {
		return this.ticket;
	}
	/**设置ticket  */
@JSONField(name="ticket")
	public void setTicket(String value) {
		this.ticket = value;
	}
	/**获取凭证有效时间 单位：秒 */
	
@JSONField(name="expires_in")
	
	public Integer getExpiresIn() {
		return this.expiresIn;
	}
	/**设置凭证有效时间 单位：秒 */
@JSONField(name="expires_in")
	public void setExpiresIn(Integer value) {
		this.expiresIn = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Ticket:",getTicket())
			.append(",ExpiresIn:",getExpiresIn())
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
//精简构造 调用微信JS接口的临时票据
JsapiTicket jsapiTicket = new JsapiTicket(
	ticket , //String ticket   
	expiresIn , //Integer 凭证有效时间  单位：秒 
	null
);
*/
}
