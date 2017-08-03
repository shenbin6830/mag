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
 * 素材管理之获取永久素材返回
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaMaterialGetvideoRet extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "素材管理之获取永久素材返回";

	//date formats
	
	//columns START
	/**图文消息的标题 String   */
	
	
	private String title;
	/**description String   */
	
	
	private String description;
	/**downUrl String   */
	
	
	private String downUrl;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaMaterialGetvideoRet _this){
	}
	public WaMaterialGetvideoRet(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param title String 图文消息的标题   
	 * @param description String description   
	 * @param downUrl String downUrl   
	* @param notuse String 没用
	 */
	public WaMaterialGetvideoRet(String title ,String description ,String downUrl ,String notuse) {
		super();
		this.title = title;
		this.description = description;
		this.downUrl = downUrl;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param title String 图文消息的标题   
	 * @param description String description   
	 * @param downUrl String downUrl   
	* @param notuse String 没用
	 */
	public WaMaterialGetvideoRet(String title ,String description ,String downUrl ,String notuse,Object notuse2) {
		super();
		this.title = title;
		this.description = description;
		this.downUrl = downUrl;
	}

	
	/**获取图文消息的标题  */
	
@JSONField(name="title")
	
	public String getTitle() {
		return this.title;
	}
	/**设置图文消息的标题  */
@JSONField(name="title")
	public void setTitle(String value) {
		this.title = value;
	}
	/**获取description  */
	
@JSONField(name="description")
	
	public String getDescription() {
		return this.description;
	}
	/**设置description  */
@JSONField(name="description")
	public void setDescription(String value) {
		this.description = value;
	}
	/**获取downUrl  */
	
@JSONField(name="down_url")
	
	public String getDownUrl() {
		return this.downUrl;
	}
	/**设置downUrl  */
@JSONField(name="down_url")
	public void setDownUrl(String value) {
		this.downUrl = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Title:",getTitle())
			.append(",Description:",getDescription())
			.append(",DownUrl:",getDownUrl())
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
//精简构造 素材管理之获取永久素材返回
WaMaterialGetvideoRet waMaterialGetvideoRet = new WaMaterialGetvideoRet(
	title , //String 图文消息的标题   
	description , //String description   
	downUrl , //String downUrl   
	null
);
*/
}
