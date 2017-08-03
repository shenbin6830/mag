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
 * 查询用户所在分组参数
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WxUsergroupsQueryParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "查询用户所在分组参数";

	//date formats
	
	//columns START
	/**openid String   */
	
	
	private String openid;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WxUsergroupsQueryParam _this){
	}
	public WxUsergroupsQueryParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param openid String openid   
	* @param notuse String 没用
	 */
	public WxUsergroupsQueryParam(String openid ,String notuse) {
		super();
		this.openid = openid;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param openid String openid   
	* @param notuse String 没用
	 */
	public WxUsergroupsQueryParam(String openid ,String notuse,Object notuse2) {
		super();
		this.openid = openid;
	}

	
	/**获取openid  */
	

	
	public String getOpenid() {
		return this.openid;
	}
	/**设置openid  */

	public void setOpenid(String value) {
		this.openid = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Openid:",getOpenid())
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
//精简构造 查询用户所在分组参数
WxUsergroupsQueryParam wxUsergroupsQueryParam = new WxUsergroupsQueryParam(
	openid , //String openid   
	null
);
*/
}
