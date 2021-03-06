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
 * 客服管理之获取所有客服账号
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaSendcustommanageListRet extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "客服管理之获取所有客服账号";

	//date formats
	
	//columns START
	/**登录密码*/
	private List<WaSendcustommanageAccount> kfList;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaSendcustommanageListRet _this){
	}
	public WaSendcustommanageListRet(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	* @param notuse String 没用
	 */
	public WaSendcustommanageListRet(String notuse) {
		super();
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param kfList String 登录密码  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 
	* @param notuse String 没用
	 */
	public WaSendcustommanageListRet(List<WaSendcustommanageAccount> kfList ,String notuse,Object notuse2) {
		super();
		this.kfList = kfList;
	}

	
	/**对象 获取登录密码 客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 */
	
	
@JSONField(name="kf_list")
	public List<WaSendcustommanageAccount> getKfList() {
		return this.kfList;
	}
	/**设置登录密码 客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 */
@JSONField(name="kf_list")
	public void setKfList(List<WaSendcustommanageAccount> value) {
		this.kfList = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
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
//精简构造 客服管理之获取所有客服账号
WaSendcustommanageListRet waSendcustommanageListRet = new WaSendcustommanageListRet(
	null
);
*/
}
