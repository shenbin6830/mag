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
 * access_token
 * @author zmax
 * @version 1.0
 * @since 
 */

public class AccessToken extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "access_token";

	//date formats
	
	//columns START
	/**获取到的凭证 String   */
	
	
	private String accessToken;
	/**凭证有效时间 Integer default=0 单位：秒 */
	
	
	private Integer expiresIn = 0;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(AccessToken _this){
		_this.expiresIn=0;
	}
	public AccessToken(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param accessToken String 获取到的凭证   
	 * @param expiresIn Integer 凭证有效时间 default=0 单位：秒 
	* @param notuse String 没用
	 */
	public AccessToken(String accessToken ,Integer expiresIn ,String notuse) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param accessToken String 获取到的凭证   
	 * @param expiresIn Integer 凭证有效时间 default=0 单位：秒 
	* @param notuse String 没用
	 */
	public AccessToken(String accessToken ,Integer expiresIn ,String notuse,Object notuse2) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}

	
	/**获取获取到的凭证  */
	
@JSONField(name="access_token")
	
	public String getAccessToken() {
		return this.accessToken;
	}
	/**设置获取到的凭证  */
@JSONField(name="access_token")
	public void setAccessToken(String value) {
		this.accessToken = value;
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
			.append(",AccessToken:",getAccessToken())
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
//精简构造 access_token
AccessToken accessToken = new AccessToken(
	accessToken , //String 获取到的凭证   
	expiresIn , //Integer 凭证有效时间 default=0 单位：秒 
	null
);
*/
}
