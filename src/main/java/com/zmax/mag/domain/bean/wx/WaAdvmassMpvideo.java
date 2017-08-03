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
 * mass视频
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaAdvmassMpvideo extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "mass视频";

	//date formats
	
	//columns START
	/**多媒体文件id String  通过上传多媒体文件，得到的id */
	
	
	private String mediaid;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaAdvmassMpvideo _this){
	}
	public WaAdvmassMpvideo(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param mediaid String 多媒体文件id  通过上传多媒体文件，得到的id 
	* @param notuse String 没用
	 */
	public WaAdvmassMpvideo(String mediaid ,String notuse) {
		super();
		this.mediaid = mediaid;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param mediaid String 多媒体文件id  通过上传多媒体文件，得到的id 
	* @param notuse String 没用
	 */
	public WaAdvmassMpvideo(String mediaid ,String notuse,Object notuse2) {
		super();
		this.mediaid = mediaid;
	}

	
	/**获取多媒体文件id 通过上传多媒体文件，得到的id */
	
@JSONField(name="media_id")
	
	public String getMediaid() {
		return this.mediaid;
	}
	/**设置多媒体文件id 通过上传多媒体文件，得到的id */
@JSONField(name="media_id")
	public void setMediaid(String value) {
		this.mediaid = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Mediaid:",getMediaid())
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
//精简构造 mass视频
WaAdvmassMpvideo waAdvmassMpvideo = new WaAdvmassMpvideo(
	mediaid , //String 多媒体文件id  通过上传多媒体文件，得到的id 
	null
);
*/
}
