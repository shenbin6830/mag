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
 * 模板消息接口之发送模板消息参数value和color
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaTemplateVc extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "模板消息接口之发送模板消息参数value和color";

	//date formats
	
	//columns START
	/**显示的值 String   */
	
	
	private String value;
	/**颜色 String  类似于#000000 */
	
	
	private String color;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaTemplateVc _this){
	}
	public WaTemplateVc(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param value String 显示的值   
	 * @param color String 颜色  类似于#000000 
	* @param notuse String 没用
	 */
	public WaTemplateVc(String value ,String color ,String notuse) {
		super();
		this.value = value;
		this.color = color;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param value String 显示的值   
	 * @param color String 颜色  类似于#000000 
	* @param notuse String 没用
	 */
	public WaTemplateVc(String value ,String color ,String notuse,Object notuse2) {
		super();
		this.value = value;
		this.color = color;
	}

	
	/**获取显示的值  */
	
@JSONField(name="value")
	
	public String getValue() {
		return this.value;
	}
	/**设置显示的值  */
@JSONField(name="value")
	public void setValue(String value) {
		this.value = value;
	}
	/**获取颜色 类似于#000000 */
	
@JSONField(name="color")
	
	public String getColor() {
		return this.color;
	}
	/**设置颜色 类似于#000000 */
@JSONField(name="color")
	public void setColor(String value) {
		this.color = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Value:",getValue())
			.append(",Color:",getColor())
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
//精简构造 模板消息接口之发送模板消息参数value和color
WaTemplateVc waTemplateVc = new WaTemplateVc(
	value , //String 显示的值   
	color , //String 颜色  类似于#000000 
	null
);
*/
}
