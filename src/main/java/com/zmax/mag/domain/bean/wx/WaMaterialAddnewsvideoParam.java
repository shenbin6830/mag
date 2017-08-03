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
 * 素材管理之新增其他类型永久素材(这里指视频)
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaMaterialAddnewsvideoParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "素材管理之新增其他类型永久素材(这里指视频)";

	//date formats
	
	//columns START
	/**视频素材的标题 String   */
	
	
	private String title;
	/**视频素材的描述 String   */
	
	
	private String introduction;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaMaterialAddnewsvideoParam _this){
	}
	public WaMaterialAddnewsvideoParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param title String 视频素材的标题   
	 * @param introduction String 视频素材的描述   
	* @param notuse String 没用
	 */
	public WaMaterialAddnewsvideoParam(String title ,String introduction ,String notuse) {
		super();
		this.title = title;
		this.introduction = introduction;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param title String 视频素材的标题   
	 * @param introduction String 视频素材的描述   
	* @param notuse String 没用
	 */
	public WaMaterialAddnewsvideoParam(String title ,String introduction ,String notuse,Object notuse2) {
		super();
		this.title = title;
		this.introduction = introduction;
	}

	
	/**获取视频素材的标题  */
	
@JSONField(name="title")
	
	public String getTitle() {
		return this.title;
	}
	/**设置视频素材的标题  */
@JSONField(name="title")
	public void setTitle(String value) {
		this.title = value;
	}
	/**获取视频素材的描述  */
	
@JSONField(name="introduction")
	
	public String getIntroduction() {
		return this.introduction;
	}
	/**设置视频素材的描述  */
@JSONField(name="introduction")
	public void setIntroduction(String value) {
		this.introduction = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Title:",getTitle())
			.append(",Introduction:",getIntroduction())
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
//精简构造 素材管理之新增其他类型永久素材(这里指视频)
WaMaterialAddnewsvideoParam waMaterialAddnewsvideoParam = new WaMaterialAddnewsvideoParam(
	title , //String 视频素材的标题   
	introduction , //String 视频素材的描述   
	null
);
*/
}
