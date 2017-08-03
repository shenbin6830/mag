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
 * 二维码ticket参数
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaQrcodeticketParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "二维码ticket参数";

	//date formats
	
	//columns START
	/**该二维码有效时间 Integer default=2592000 ，以秒为单位。 最大不超过2592000（即30天）。 */
	
	
	private Integer expireSeconds = 2592000;
	/**二维码类型 String default=QR_SCENE ，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值 */
	
	
	private String actionName;
	/**二维码详细信息*/
	private WaQrcodeticketActioninfo actionInfo;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaQrcodeticketParam _this){
		_this.expireSeconds=2592000;
		_this.actionName="QR_SCENE";
	}
	public WaQrcodeticketParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param expireSeconds Integer 该二维码有效时间 default=2592000 ，以秒为单位。 最大不超过2592000（即30天）。 
	 * @param actionName String 二维码类型 default=QR_SCENE ，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值 
	* @param notuse String 没用
	 */
	public WaQrcodeticketParam(Integer expireSeconds ,String actionName ,String notuse) {
		super();
		this.expireSeconds = expireSeconds;
		this.actionName = actionName;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param expireSeconds Integer 该二维码有效时间 default=2592000 ，以秒为单位。 最大不超过2592000（即30天）。 
	 * @param actionName String 二维码类型 default=QR_SCENE ，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值 
	 * @param actionInfo String 二维码详细信息  ，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000） 
	* @param notuse String 没用
	 */
	public WaQrcodeticketParam(Integer expireSeconds ,String actionName ,WaQrcodeticketActioninfo actionInfo ,String notuse,Object notuse2) {
		super();
		this.expireSeconds = expireSeconds;
		this.actionName = actionName;
		this.actionInfo = actionInfo;
	}

	
	/**获取该二维码有效时间 ，以秒为单位。 最大不超过2592000（即30天）。 */
	
@JSONField(name="expire_seconds")
	
	public Integer getExpireSeconds() {
		return this.expireSeconds;
	}
	/**设置该二维码有效时间 ，以秒为单位。 最大不超过2592000（即30天）。 */
@JSONField(name="expire_seconds")
	public void setExpireSeconds(Integer value) {
		this.expireSeconds = value;
	}
	/**获取二维码类型 ，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值 */
	
@JSONField(name="action_name")
	
	public String getActionName() {
		return this.actionName;
	}
	/**设置二维码类型 ，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值 */
@JSONField(name="action_name")
	public void setActionName(String value) {
		this.actionName = value;
	}
	/**对象 获取二维码详细信息 ，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000） */
	
	
@JSONField(name="action_info")
	public WaQrcodeticketActioninfo getActionInfo() {
		return this.actionInfo;
	}
	/**设置二维码详细信息 ，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000） */
@JSONField(name="action_info")
	public void setActionInfo(WaQrcodeticketActioninfo value) {
		this.actionInfo = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",ExpireSeconds:",getExpireSeconds())
			.append(",ActionName:",getActionName())
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
//精简构造 二维码ticket参数
WaQrcodeticketParam waQrcodeticketParam = new WaQrcodeticketParam(
	expireSeconds , //Integer 该二维码有效时间 default=2592000 ，以秒为单位。 最大不超过2592000（即30天）。 
	actionName , //String 二维码类型 default=QR_SCENE ，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值 
	null
);
*/
}
