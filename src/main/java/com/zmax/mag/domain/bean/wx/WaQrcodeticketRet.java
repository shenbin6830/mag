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
 * 二维码ticket返回
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaQrcodeticketRet extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "二维码ticket返回";

	//date formats
	
	//columns START
	/**获取的二维码ticket String  凭借此ticket可以在有效时间内换取二维码。   */
	
	
	private String ticket;
	/**二维码的有效时间 Integer  以秒为单位。最大不超过1800。   */
	
	
	private Integer expireSeconds;
	/**二维码图片解析后的地址 String  开发者可根据该地址自行生成需要的二维码图片   */
	
	
	private String url;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaQrcodeticketRet _this){
	}
	public WaQrcodeticketRet(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param ticket String 获取的二维码ticket  凭借此ticket可以在有效时间内换取二维码。   
	 * @param expireSeconds Integer 二维码的有效时间  以秒为单位。最大不超过1800。   
	 * @param url String 二维码图片解析后的地址  开发者可根据该地址自行生成需要的二维码图片   
	* @param notuse String 没用
	 */
	public WaQrcodeticketRet(String ticket ,Integer expireSeconds ,String url ,String notuse) {
		super();
		this.ticket = ticket;
		this.expireSeconds = expireSeconds;
		this.url = url;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param ticket String 获取的二维码ticket  凭借此ticket可以在有效时间内换取二维码。   
	 * @param expireSeconds Integer 二维码的有效时间  以秒为单位。最大不超过1800。   
	 * @param url String 二维码图片解析后的地址  开发者可根据该地址自行生成需要的二维码图片   
	* @param notuse String 没用
	 */
	public WaQrcodeticketRet(String ticket ,Integer expireSeconds ,String url ,String notuse,Object notuse2) {
		super();
		this.ticket = ticket;
		this.expireSeconds = expireSeconds;
		this.url = url;
	}

	
	/**获取获取的二维码ticket 凭借此ticket可以在有效时间内换取二维码。   */
	
@JSONField(name="ticket")
	
	public String getTicket() {
		return this.ticket;
	}
	/**设置获取的二维码ticket 凭借此ticket可以在有效时间内换取二维码。   */
@JSONField(name="ticket")
	public void setTicket(String value) {
		this.ticket = value;
	}
	/**获取二维码的有效时间 以秒为单位。最大不超过1800。   */
	
@JSONField(name="expire_seconds")
	
	public Integer getExpireSeconds() {
		return this.expireSeconds;
	}
	/**设置二维码的有效时间 以秒为单位。最大不超过1800。   */
@JSONField(name="expire_seconds")
	public void setExpireSeconds(Integer value) {
		this.expireSeconds = value;
	}
	/**获取二维码图片解析后的地址 开发者可根据该地址自行生成需要的二维码图片   */
	
@JSONField(name="url")
	
	public String getUrl() {
		return this.url;
	}
	/**设置二维码图片解析后的地址 开发者可根据该地址自行生成需要的二维码图片   */
@JSONField(name="url")
	public void setUrl(String value) {
		this.url = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Ticket:",getTicket())
			.append(",ExpireSeconds:",getExpireSeconds())
			.append(",Url:",getUrl())
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
//精简构造 二维码ticket返回
WaQrcodeticketRet waQrcodeticketRet = new WaQrcodeticketRet(
	ticket , //String 获取的二维码ticket  凭借此ticket可以在有效时间内换取二维码。   
	expireSeconds , //Integer 二维码的有效时间  以秒为单位。最大不超过1800。   
	url , //String 二维码图片解析后的地址  开发者可根据该地址自行生成需要的二维码图片   
	null
);
*/
}
