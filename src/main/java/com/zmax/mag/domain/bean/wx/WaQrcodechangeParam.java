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
 * 二维码长链接转短链接参数
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaQrcodechangeParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "二维码长链接转短链接参数";

	//date formats
	
	//columns START
	/**此处填long2short String default=long2short 代表长链接转短链接   */
	
	
	private String action1;
	/**需要转换的长链接 String  支持http://、https://、weixin://wxpay 格式的url   */
	
	
	private String longUrl;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaQrcodechangeParam _this){
		_this.action1="long2short";
	}
	public WaQrcodechangeParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param action1 String 此处填long2short default=long2short 代表长链接转短链接   
	 * @param longUrl String 需要转换的长链接  支持http://、https://、weixin://wxpay 格式的url   
	* @param notuse String 没用
	 */
	public WaQrcodechangeParam(String action1 ,String longUrl ,String notuse) {
		super();
		this.action1 = action1;
		this.longUrl = longUrl;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param action1 String 此处填long2short default=long2short 代表长链接转短链接   
	 * @param longUrl String 需要转换的长链接  支持http://、https://、weixin://wxpay 格式的url   
	* @param notuse String 没用
	 */
	public WaQrcodechangeParam(String action1 ,String longUrl ,String notuse,Object notuse2) {
		super();
		this.action1 = action1;
		this.longUrl = longUrl;
	}

	
	/**获取此处填long2short 代表长链接转短链接   */
	
@JSONField(name="action")
	
	public String getAction1() {
		return this.action1;
	}
	/**设置此处填long2short 代表长链接转短链接   */
@JSONField(name="action")
	public void setAction1(String value) {
		this.action1 = value;
	}
	/**获取需要转换的长链接 支持http://、https://、weixin://wxpay 格式的url   */
	
@JSONField(name="long_url")
	
	public String getLongUrl() {
		return this.longUrl;
	}
	/**设置需要转换的长链接 支持http://、https://、weixin://wxpay 格式的url   */
@JSONField(name="long_url")
	public void setLongUrl(String value) {
		this.longUrl = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Action1:",getAction1())
			.append(",LongUrl:",getLongUrl())
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
//精简构造 二维码长链接转短链接参数
WaQrcodechangeParam waQrcodechangeParam = new WaQrcodechangeParam(
	action1 , //String 此处填long2short default=long2short 代表长链接转短链接   
	longUrl , //String 需要转换的长链接  支持http://、https://、weixin://wxpay 格式的url   
	null
);
*/
}
