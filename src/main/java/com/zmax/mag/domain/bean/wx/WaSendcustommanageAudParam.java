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
 * 客服管理之增删改参数
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaSendcustommanageAudParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "客服管理之增删改参数";

	//date formats
	
	//columns START
	/**完整客服账号 String  完整客服账号，格式为：账号前缀@公众号微信号 */
	
	
	private String kfAccount;
	/**客服昵称 String  客服昵称，最长6个汉字或12个英文字符 */
	
	
	private String nickname;
	/**登录密码 String  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 */
	
	
	private String password;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaSendcustommanageAudParam _this){
	}
	public WaSendcustommanageAudParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param kfAccount String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号 
	 * @param nickname String 客服昵称  客服昵称，最长6个汉字或12个英文字符 
	 * @param password String 登录密码  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 
	* @param notuse String 没用
	 */
	public WaSendcustommanageAudParam(String kfAccount ,String nickname ,String password ,String notuse) {
		super();
		this.kfAccount = kfAccount;
		this.nickname = nickname;
		this.password = password;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param kfAccount String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号 
	 * @param nickname String 客服昵称  客服昵称，最长6个汉字或12个英文字符 
	 * @param password String 登录密码  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 
	* @param notuse String 没用
	 */
	public WaSendcustommanageAudParam(String kfAccount ,String nickname ,String password ,String notuse,Object notuse2) {
		super();
		this.kfAccount = kfAccount;
		this.nickname = nickname;
		this.password = password;
	}

	
	/**获取完整客服账号 完整客服账号，格式为：账号前缀@公众号微信号 */
	
@JSONField(name="kf_account")
	
	public String getKfAccount() {
		return this.kfAccount;
	}
	/**设置完整客服账号 完整客服账号，格式为：账号前缀@公众号微信号 */
@JSONField(name="kf_account")
	public void setKfAccount(String value) {
		this.kfAccount = value;
	}
	/**获取客服昵称 客服昵称，最长6个汉字或12个英文字符 */
	
@JSONField(name="nickname")
	
	public String getNickname() {
		return this.nickname;
	}
	/**设置客服昵称 客服昵称，最长6个汉字或12个英文字符 */
@JSONField(name="nickname")
	public void setNickname(String value) {
		this.nickname = value;
	}
	/**获取登录密码 客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 */
	
@JSONField(name="password")
	
	public String getPassword() {
		return this.password;
	}
	/**设置登录密码 客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 */
@JSONField(name="password")
	public void setPassword(String value) {
		this.password = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",KfAccount:",getKfAccount())
			.append(",Nickname:",getNickname())
			.append(",Password:",getPassword())
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
//精简构造 客服管理之增删改参数
WaSendcustommanageAudParam waSendcustommanageAudParam = new WaSendcustommanageAudParam(
	kfAccount , //String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号 
	nickname , //String 客服昵称  客服昵称，最长6个汉字或12个英文字符 
	password , //String 登录密码  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 
	null
);
*/
}
