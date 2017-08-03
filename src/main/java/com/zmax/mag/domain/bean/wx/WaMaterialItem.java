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

public class WaMaterialItem extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "回复消息之获取素材列表";

	//date formats
	
	//columns START
	/**素材id String   */
	
	
	private String mediaId;
	/**图文消息的具体内容*/
	private List<WaMaterialNewsitem> content;
	/**文件名称 String   */
	
	
	private String name;
	/**updateTime String   */
	
	
	private String updateTime;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaMaterialItem _this){
	}
	public WaMaterialItem(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param mediaId String 素材id   
	 * @param name String 文件名称   
	 * @param updateTime String updateTime   
	* @param notuse String 没用
	 */
	public WaMaterialItem(String mediaId ,String name ,String updateTime ,String notuse) {
		super();
		this.mediaId = mediaId;
		this.name = name;
		this.updateTime = updateTime;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param mediaId String 素材id   
	 * @param content String 图文消息的具体内容  图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS 
	 * @param name String 文件名称   
	 * @param updateTime String updateTime   
	* @param notuse String 没用
	 */
	public WaMaterialItem(String mediaId ,List<WaMaterialNewsitem> content ,String name ,String updateTime ,String notuse,Object notuse2) {
		super();
		this.mediaId = mediaId;
		this.content = content;
		this.name = name;
		this.updateTime = updateTime;
	}

	
	/**获取素材id  */
	
@JSONField(name="media_id")
	
	public String getMediaId() {
		return this.mediaId;
	}
	/**设置素材id  */
@JSONField(name="media_id")
	public void setMediaId(String value) {
		this.mediaId = value;
	}
	/**对象 获取图文消息的具体内容 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS */
	
	
@JSONField(name="content")
	public List<WaMaterialNewsitem> getContent() {
		return this.content;
	}
	/**设置图文消息的具体内容 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS */
@JSONField(name="content")
	public void setContent(List<WaMaterialNewsitem> value) {
		this.content = value;
	}
	/**获取文件名称  */
	
@JSONField(name="name")
	
	public String getName() {
		return this.name;
	}
	/**设置文件名称  */
@JSONField(name="name")
	public void setName(String value) {
		this.name = value;
	}
	/**获取updateTime  */
	
@JSONField(name="update_time")
	
	public String getUpdateTime() {
		return this.updateTime;
	}
	/**设置updateTime  */
@JSONField(name="update_time")
	public void setUpdateTime(String value) {
		this.updateTime = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",MediaId:",getMediaId())
			.append(",Name:",getName())
			.append(",UpdateTime:",getUpdateTime())
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
WaMaterialItem waMaterialItem = new WaMaterialItem(
	mediaId , //String 素材id   
	name , //String 文件名称   
	updateTime , //String updateTime   
	null
);
*/
}
