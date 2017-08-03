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
 * 对象之客服
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaSendcustommanageAccount extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "对象之客服";

	//date formats
	
	//columns START
	/**完整客服账号 String  完整客服账号，格式为：账号前缀@公众号微信号 */
	
	
	private String kfAccount;
	/**客服昵称 String   */
	
	
	private String kfNick;
	/**客服工号 String   */
	
	
	private String kfId;
	/**客服头像url String   */
	
	
	private String kfHeadimgurl;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaSendcustommanageAccount _this){
	}
	public WaSendcustommanageAccount(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param kfAccount String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号 
	 * @param kfNick String 客服昵称   
	 * @param kfId String 客服工号   
	 * @param kfHeadimgurl String 客服头像url   
	* @param notuse String 没用
	 */
	public WaSendcustommanageAccount(String kfAccount ,String kfNick ,String kfId ,String kfHeadimgurl ,String notuse) {
		super();
		this.kfAccount = kfAccount;
		this.kfNick = kfNick;
		this.kfId = kfId;
		this.kfHeadimgurl = kfHeadimgurl;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param kfAccount String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号 
	 * @param kfNick String 客服昵称   
	 * @param kfId String 客服工号   
	 * @param kfHeadimgurl String 客服头像url   
	* @param notuse String 没用
	 */
	public WaSendcustommanageAccount(String kfAccount ,String kfNick ,String kfId ,String kfHeadimgurl ,String notuse,Object notuse2) {
		super();
		this.kfAccount = kfAccount;
		this.kfNick = kfNick;
		this.kfId = kfId;
		this.kfHeadimgurl = kfHeadimgurl;
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
	/**获取客服昵称  */
	
@JSONField(name="kf_nick")
	
	public String getKfNick() {
		return this.kfNick;
	}
	/**设置客服昵称  */
@JSONField(name="kf_nick")
	public void setKfNick(String value) {
		this.kfNick = value;
	}
	/**获取客服工号  */
	
@JSONField(name="kf_id")
	
	public String getKfId() {
		return this.kfId;
	}
	/**设置客服工号  */
@JSONField(name="kf_id")
	public void setKfId(String value) {
		this.kfId = value;
	}
	/**获取客服头像url  */
	
@JSONField(name="kf_headimgurl")
	
	public String getKfHeadimgurl() {
		return this.kfHeadimgurl;
	}
	/**设置客服头像url  */
@JSONField(name="kf_headimgurl")
	public void setKfHeadimgurl(String value) {
		this.kfHeadimgurl = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",KfAccount:",getKfAccount())
			.append(",KfNick:",getKfNick())
			.append(",KfId:",getKfId())
			.append(",KfHeadimgurl:",getKfHeadimgurl())
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
//精简构造 对象之客服
WaSendcustommanageAccount waSendcustommanageAccount = new WaSendcustommanageAccount(
	kfAccount , //String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号 
	kfNick , //String 客服昵称   
	kfId , //String 客服工号   
	kfHeadimgurl , //String 客服头像url   
	null
);
*/
}
