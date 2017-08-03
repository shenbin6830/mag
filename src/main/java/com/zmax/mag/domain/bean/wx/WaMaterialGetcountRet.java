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
 * 回复消息之获取素材总数
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaMaterialGetcountRet extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "回复消息之获取素材总数";

	//date formats
	
	//columns START
	/**语音总数量 Integer   */
	
	
	private Integer voiceCount;
	/**视频总数量 Integer   */
	
	
	private Integer videoCount;
	/**图片总数量 Integer   */
	
	
	private Integer imageCount;
	/**图文总数量 Integer   */
	
	
	private Integer newsCount;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaMaterialGetcountRet _this){
	}
	public WaMaterialGetcountRet(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param voiceCount Integer 语音总数量   
	 * @param videoCount Integer 视频总数量   
	 * @param imageCount Integer 图片总数量   
	 * @param newsCount Integer 图文总数量   
	* @param notuse String 没用
	 */
	public WaMaterialGetcountRet(Integer voiceCount ,Integer videoCount ,Integer imageCount ,Integer newsCount ,String notuse) {
		super();
		this.voiceCount = voiceCount;
		this.videoCount = videoCount;
		this.imageCount = imageCount;
		this.newsCount = newsCount;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param voiceCount Integer 语音总数量   
	 * @param videoCount Integer 视频总数量   
	 * @param imageCount Integer 图片总数量   
	 * @param newsCount Integer 图文总数量   
	* @param notuse String 没用
	 */
	public WaMaterialGetcountRet(Integer voiceCount ,Integer videoCount ,Integer imageCount ,Integer newsCount ,String notuse,Object notuse2) {
		super();
		this.voiceCount = voiceCount;
		this.videoCount = videoCount;
		this.imageCount = imageCount;
		this.newsCount = newsCount;
	}

	
	/**获取语音总数量  */
	
@JSONField(name="voice_count")
	
	public Integer getVoiceCount() {
		return this.voiceCount;
	}
	/**设置语音总数量  */
@JSONField(name="voice_count")
	public void setVoiceCount(Integer value) {
		this.voiceCount = value;
	}
	/**获取视频总数量  */
	
@JSONField(name="video_count")
	
	public Integer getVideoCount() {
		return this.videoCount;
	}
	/**设置视频总数量  */
@JSONField(name="video_count")
	public void setVideoCount(Integer value) {
		this.videoCount = value;
	}
	/**获取图片总数量  */
	
@JSONField(name="image_count")
	
	public Integer getImageCount() {
		return this.imageCount;
	}
	/**设置图片总数量  */
@JSONField(name="image_count")
	public void setImageCount(Integer value) {
		this.imageCount = value;
	}
	/**获取图文总数量  */
	
@JSONField(name="news_count")
	
	public Integer getNewsCount() {
		return this.newsCount;
	}
	/**设置图文总数量  */
@JSONField(name="news_count")
	public void setNewsCount(Integer value) {
		this.newsCount = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",VoiceCount:",getVoiceCount())
			.append(",VideoCount:",getVideoCount())
			.append(",ImageCount:",getImageCount())
			.append(",NewsCount:",getNewsCount())
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
//精简构造 回复消息之获取素材总数
WaMaterialGetcountRet waMaterialGetcountRet = new WaMaterialGetcountRet(
	voiceCount , //Integer 语音总数量   
	videoCount , //Integer 视频总数量   
	imageCount , //Integer 图片总数量   
	newsCount , //Integer 图文总数量   
	null
);
*/
}
