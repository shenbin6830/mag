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
 * 共用对象之客服服务参数<br/>如果需要以某个客服帐号来发消息（在微信6.0.2及以上版本中显示自定义头像），则需在JSON数据包的后半部分加入customservice参数
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaSendcustommsgCustomservice extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "共用对象之客服服务参数";

	//date formats
	
	//columns START
	/**完整客服账号 String  完整客服账号，格式为：账号前缀@公众号微信号 */
	
	
	private String kfAccount;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaSendcustommsgCustomservice _this){
	}
	public WaSendcustommsgCustomservice(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param kfAccount String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号 
	* @param notuse String 没用
	 */
	public WaSendcustommsgCustomservice(String kfAccount ,String notuse) {
		super();
		this.kfAccount = kfAccount;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param kfAccount String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号 
	* @param notuse String 没用
	 */
	public WaSendcustommsgCustomservice(String kfAccount ,String notuse,Object notuse2) {
		super();
		this.kfAccount = kfAccount;
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
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",KfAccount:",getKfAccount())
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
//精简构造 共用对象之客服服务参数
WaSendcustommsgCustomservice waSendcustommsgCustomservice = new WaSendcustommsgCustomservice(
	kfAccount , //String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号 
	null
);
*/
}
