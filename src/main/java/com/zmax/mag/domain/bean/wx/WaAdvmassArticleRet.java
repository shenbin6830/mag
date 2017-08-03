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
 * 上传图文消息素材返回
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaAdvmassArticleRet extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "上传图文消息素材返回";

	//date formats
	
	//columns START
	/**媒体文件类型 String  媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息 */
	
	
	private String type;
	/**上传后的唯一标识 String  媒体文件/图文消息上传后获取的唯一标识 */
	
	
	private String mediaId;
	/**上传时间 String  媒体文件上传时间 */
	
	
	private String createdAt;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaAdvmassArticleRet _this){
	}
	public WaAdvmassArticleRet(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param type String 媒体文件类型  媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息 
	 * @param mediaId String 上传后的唯一标识  媒体文件/图文消息上传后获取的唯一标识 
	 * @param createdAt String 上传时间  媒体文件上传时间 
	* @param notuse String 没用
	 */
	public WaAdvmassArticleRet(String type ,String mediaId ,String createdAt ,String notuse) {
		super();
		this.type = type;
		this.mediaId = mediaId;
		this.createdAt = createdAt;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param type String 媒体文件类型  媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息 
	 * @param mediaId String 上传后的唯一标识  媒体文件/图文消息上传后获取的唯一标识 
	 * @param createdAt String 上传时间  媒体文件上传时间 
	* @param notuse String 没用
	 */
	public WaAdvmassArticleRet(String type ,String mediaId ,String createdAt ,String notuse,Object notuse2) {
		super();
		this.type = type;
		this.mediaId = mediaId;
		this.createdAt = createdAt;
	}

	
	/**获取媒体文件类型 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息 */
	
@JSONField(name="type")
	
	public String getType() {
		return this.type;
	}
	/**设置媒体文件类型 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息 */
@JSONField(name="type")
	public void setType(String value) {
		this.type = value;
	}
	/**获取上传后的唯一标识 媒体文件/图文消息上传后获取的唯一标识 */
	
@JSONField(name="media_id")
	
	public String getMediaId() {
		return this.mediaId;
	}
	/**设置上传后的唯一标识 媒体文件/图文消息上传后获取的唯一标识 */
@JSONField(name="media_id")
	public void setMediaId(String value) {
		this.mediaId = value;
	}
	/**获取上传时间 媒体文件上传时间 */
	
@JSONField(name="created_at")
	
	public String getCreatedAt() {
		return this.createdAt;
	}
	/**设置上传时间 媒体文件上传时间 */
@JSONField(name="created_at")
	public void setCreatedAt(String value) {
		this.createdAt = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Type:",getType())
			.append(",MediaId:",getMediaId())
			.append(",CreatedAt:",getCreatedAt())
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
//精简构造 上传图文消息素材返回
WaAdvmassArticleRet waAdvmassArticleRet = new WaAdvmassArticleRet(
	type , //String 媒体文件类型  媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息 
	mediaId , //String 上传后的唯一标识  媒体文件/图文消息上传后获取的唯一标识 
	createdAt , //String 上传时间  媒体文件上传时间 
	null
);
*/
}
