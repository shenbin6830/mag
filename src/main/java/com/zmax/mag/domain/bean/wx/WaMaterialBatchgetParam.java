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
 * 回复消息之获取素材列表
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaMaterialBatchgetParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "回复消息之获取素材列表";

	//date formats
	
	//columns START
	/**素材的类型 String  素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news） */
	
	
	private String type;
	/**开始返回的位置 Integer  从全部素材的该偏移位置开始返回，0表示从第一个素材 返回 */
	
	
	private Integer offset;
	/**返回素材的数量 Integer  返回素材的数量，取值在1到20之间 */
	
	
	private Integer count;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaMaterialBatchgetParam _this){
	}
	public WaMaterialBatchgetParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param type String 素材的类型  素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news） 
	 * @param offset Integer 开始返回的位置  从全部素材的该偏移位置开始返回，0表示从第一个素材 返回 
	 * @param count Integer 返回素材的数量  返回素材的数量，取值在1到20之间 
	* @param notuse String 没用
	 */
	public WaMaterialBatchgetParam(String type ,Integer offset ,Integer count ,String notuse) {
		super();
		this.type = type;
		this.offset = offset;
		this.count = count;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param type String 素材的类型  素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news） 
	 * @param offset Integer 开始返回的位置  从全部素材的该偏移位置开始返回，0表示从第一个素材 返回 
	 * @param count Integer 返回素材的数量  返回素材的数量，取值在1到20之间 
	* @param notuse String 没用
	 */
	public WaMaterialBatchgetParam(String type ,Integer offset ,Integer count ,String notuse,Object notuse2) {
		super();
		this.type = type;
		this.offset = offset;
		this.count = count;
	}

	
	/**获取素材的类型 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news） */
	
@JSONField(name="type")
	
	public String getType() {
		return this.type;
	}
	/**设置素材的类型 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news） */
@JSONField(name="type")
	public void setType(String value) {
		this.type = value;
	}
	/**获取开始返回的位置 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回 */
	
@JSONField(name="offset")
	
	public Integer getOffset() {
		return this.offset;
	}
	/**设置开始返回的位置 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回 */
@JSONField(name="offset")
	public void setOffset(Integer value) {
		this.offset = value;
	}
	/**获取返回素材的数量 返回素材的数量，取值在1到20之间 */
	
@JSONField(name="count")
	
	public Integer getCount() {
		return this.count;
	}
	/**设置返回素材的数量 返回素材的数量，取值在1到20之间 */
@JSONField(name="count")
	public void setCount(Integer value) {
		this.count = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Type:",getType())
			.append(",Offset:",getOffset())
			.append(",Count:",getCount())
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
//精简构造 回复消息之获取素材列表
WaMaterialBatchgetParam waMaterialBatchgetParam = new WaMaterialBatchgetParam(
	type , //String 素材的类型  素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news） 
	offset , //Integer 开始返回的位置  从全部素材的该偏移位置开始返回，0表示从第一个素材 返回 
	count , //Integer 返回素材的数量  返回素材的数量，取值在1到20之间 
	null
);
*/
}
