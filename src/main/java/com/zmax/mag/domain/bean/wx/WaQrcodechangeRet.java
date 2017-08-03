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
 * 二维码长链接转短链接返回
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaQrcodechangeRet extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "二维码长链接转短链接返回";

	//date formats
	
	//columns START
	/**短链接 String  http://w.url.cn/s/AvCo6Ih */
	
	
	private String shortUrl;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaQrcodechangeRet _this){
	}
	public WaQrcodechangeRet(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param shortUrl String 短链接  http://w.url.cn/s/AvCo6Ih 
	* @param notuse String 没用
	 */
	public WaQrcodechangeRet(String shortUrl ,String notuse) {
		super();
		this.shortUrl = shortUrl;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param shortUrl String 短链接  http://w.url.cn/s/AvCo6Ih 
	* @param notuse String 没用
	 */
	public WaQrcodechangeRet(String shortUrl ,String notuse,Object notuse2) {
		super();
		this.shortUrl = shortUrl;
	}

	
	/**获取短链接 http://w.url.cn/s/AvCo6Ih */
	
@JSONField(name="long_url")
	
	public String getShortUrl() {
		return this.shortUrl;
	}
	/**设置短链接 http://w.url.cn/s/AvCo6Ih */
@JSONField(name="long_url")
	public void setShortUrl(String value) {
		this.shortUrl = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",ShortUrl:",getShortUrl())
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
//精简构造 二维码长链接转短链接返回
WaQrcodechangeRet waQrcodechangeRet = new WaQrcodechangeRet(
	shortUrl , //String 短链接  http://w.url.cn/s/AvCo6Ih 
	null
);
*/
}
