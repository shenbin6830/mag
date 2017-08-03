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
 * 新增永久素材返回
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaMaterialAddnewsRet extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "新增永久素材返回";

	//date formats
	
	//columns START
	/**素材id String  媒体文件上传后，获取时的唯一标识 */
	
	
	private String mediaId;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaMaterialAddnewsRet _this){
	}
	public WaMaterialAddnewsRet(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param mediaId String 素材id  媒体文件上传后，获取时的唯一标识 
	* @param notuse String 没用
	 */
	public WaMaterialAddnewsRet(String mediaId ,String notuse) {
		super();
		this.mediaId = mediaId;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param mediaId String 素材id  媒体文件上传后，获取时的唯一标识 
	* @param notuse String 没用
	 */
	public WaMaterialAddnewsRet(String mediaId ,String notuse,Object notuse2) {
		super();
		this.mediaId = mediaId;
	}

	
	/**获取素材id 媒体文件上传后，获取时的唯一标识 */
	
@JSONField(name="media_id")
	
	public String getMediaId() {
		return this.mediaId;
	}
	/**设置素材id 媒体文件上传后，获取时的唯一标识 */
@JSONField(name="media_id")
	public void setMediaId(String value) {
		this.mediaId = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",MediaId:",getMediaId())
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
//精简构造 新增永久素材返回
WaMaterialAddnewsRet waMaterialAddnewsRet = new WaMaterialAddnewsRet(
	mediaId , //String 素材id  媒体文件上传后，获取时的唯一标识 
	null
);
*/
}
