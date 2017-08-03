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

import javax.xml.bind.annotation.*;
import com.alibaba.fastjson.annotation.*;


import com.zmax.mag.domain.bean.*;


/**
 * 共用对象之音乐
 * @author zmax
 * @version 1.0
 * @since 
 */

@XmlRootElement(name="xml")
public class WaEntityMusic extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "共用对象之音乐";

	//date formats
	
	//columns START
	/**音乐标题 String   */
	
	
	private String title;
	/**音乐描述 String   */
	
	
	private String description;
	/**音乐链接 String   */
	
	
	private String musicurl;
	/**高质量音乐链接 String  高质量音乐链接，WIFI环境优先使用该链接播放音乐 */
	
	
	private String hqmusicurl;
	/**缩略图的媒体id String  缩略图的媒体id，通过上传多媒体文件，得到的id */
	
	
	private String thumbmediaid;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaEntityMusic _this){
	}
	public WaEntityMusic(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param title String 音乐标题   
	 * @param description String 音乐描述   
	 * @param musicurl String 音乐链接   
	 * @param hqmusicurl String 高质量音乐链接  高质量音乐链接，WIFI环境优先使用该链接播放音乐 
	 * @param thumbmediaid String 缩略图的媒体id  缩略图的媒体id，通过上传多媒体文件，得到的id 
	* @param notuse String 没用
	 */
	public WaEntityMusic(String title ,String description ,String musicurl ,String hqmusicurl ,String thumbmediaid ,String notuse) {
		super();
		this.title = title;
		this.description = description;
		this.musicurl = musicurl;
		this.hqmusicurl = hqmusicurl;
		this.thumbmediaid = thumbmediaid;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param title String 音乐标题   
	 * @param description String 音乐描述   
	 * @param musicurl String 音乐链接   
	 * @param hqmusicurl String 高质量音乐链接  高质量音乐链接，WIFI环境优先使用该链接播放音乐 
	 * @param thumbmediaid String 缩略图的媒体id  缩略图的媒体id，通过上传多媒体文件，得到的id 
	* @param notuse String 没用
	 */
	public WaEntityMusic(String title ,String description ,String musicurl ,String hqmusicurl ,String thumbmediaid ,String notuse,Object notuse2) {
		super();
		this.title = title;
		this.description = description;
		this.musicurl = musicurl;
		this.hqmusicurl = hqmusicurl;
		this.thumbmediaid = thumbmediaid;
	}

	
	/**获取音乐标题  */
	@XmlElement(name="Title")

	
	public String getTitle() {
		return this.title;
	}
	/**设置音乐标题  */

	public void setTitle(String value) {
		this.title = value;
	}
	/**获取音乐描述  */
	@XmlElement(name="Description")

	
	public String getDescription() {
		return this.description;
	}
	/**设置音乐描述  */

	public void setDescription(String value) {
		this.description = value;
	}
	/**获取音乐链接  */
	@XmlElement(name="MusicUrl")

	
	public String getMusicurl() {
		return this.musicurl;
	}
	/**设置音乐链接  */

	public void setMusicurl(String value) {
		this.musicurl = value;
	}
	/**获取高质量音乐链接 高质量音乐链接，WIFI环境优先使用该链接播放音乐 */
	@XmlElement(name="HQMusicUrl")

	
	public String getHqmusicurl() {
		return this.hqmusicurl;
	}
	/**设置高质量音乐链接 高质量音乐链接，WIFI环境优先使用该链接播放音乐 */

	public void setHqmusicurl(String value) {
		this.hqmusicurl = value;
	}
	/**获取缩略图的媒体id 缩略图的媒体id，通过上传多媒体文件，得到的id */
	@XmlElement(name="ThumbMediaId")

	
	public String getThumbmediaid() {
		return this.thumbmediaid;
	}
	/**设置缩略图的媒体id 缩略图的媒体id，通过上传多媒体文件，得到的id */

	public void setThumbmediaid(String value) {
		this.thumbmediaid = value;
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
			.append(",Musicurl:",getMusicurl())
			.append(",Hqmusicurl:",getHqmusicurl())
			.append(",Thumbmediaid:",getThumbmediaid())
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
//精简构造 共用对象之音乐
WaEntityMusic waEntityMusic = new WaEntityMusic(
	title , //String 音乐标题   
	description , //String 音乐描述   
	musicurl , //String 音乐链接   
	hqmusicurl , //String 高质量音乐链接  高质量音乐链接，WIFI环境优先使用该链接播放音乐 
	thumbmediaid , //String 缩略图的媒体id  缩略图的媒体id，通过上传多媒体文件，得到的id 
	null
);
*/
}
