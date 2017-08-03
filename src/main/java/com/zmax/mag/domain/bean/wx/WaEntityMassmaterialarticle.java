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
 * 对象之上传素材图文<br/>用于高级群发和素材
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaEntityMassmaterialarticle extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "对象之上传素材图文";

	//date formats
	
	//columns START
	/**消息缩略图的media_id String  图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得 */
	
	
	private String thumbMediaId;
	/**作者 String  图文消息的作者 */
	
	
	private String author;
	/**标题 String  图文消息的标题 */
	
	
	private String title;
	/**点击“阅读原文”后的页面 String  在图文消息页面点击“阅读原文”后的页面 */
	
	
	private String contentSourceUrl;
	/**页面内容 String  图文消息页面的内容，支持HTML标签 */
	
	
	private String content;
	/**描述 String  图文消息的描述 */
	
	
	private String digest;
	/**是否显示封面 String  是否显示封面，1为显示，0为不显示 */
	
	
	private String showCoverPic;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaEntityMassmaterialarticle _this){
	}
	public WaEntityMassmaterialarticle(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param thumbMediaId String 消息缩略图的media_id  图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得 
	 * @param author String 作者  图文消息的作者 
	 * @param title String 标题  图文消息的标题 
	 * @param contentSourceUrl String 点击“阅读原文”后的页面  在图文消息页面点击“阅读原文”后的页面 
	 * @param content String 页面内容  图文消息页面的内容，支持HTML标签 
	 * @param digest String 描述  图文消息的描述 
	 * @param showCoverPic String 是否显示封面  是否显示封面，1为显示，0为不显示 
	* @param notuse String 没用
	 */
	public WaEntityMassmaterialarticle(String thumbMediaId ,String author ,String title ,String contentSourceUrl ,String content ,String digest ,String showCoverPic ,String notuse) {
		super();
		this.thumbMediaId = thumbMediaId;
		this.author = author;
		this.title = title;
		this.contentSourceUrl = contentSourceUrl;
		this.content = content;
		this.digest = digest;
		this.showCoverPic = showCoverPic;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param thumbMediaId String 消息缩略图的media_id  图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得 
	 * @param author String 作者  图文消息的作者 
	 * @param title String 标题  图文消息的标题 
	 * @param contentSourceUrl String 点击“阅读原文”后的页面  在图文消息页面点击“阅读原文”后的页面 
	 * @param content String 页面内容  图文消息页面的内容，支持HTML标签 
	 * @param digest String 描述  图文消息的描述 
	 * @param showCoverPic String 是否显示封面  是否显示封面，1为显示，0为不显示 
	* @param notuse String 没用
	 */
	public WaEntityMassmaterialarticle(String thumbMediaId ,String author ,String title ,String contentSourceUrl ,String content ,String digest ,String showCoverPic ,String notuse,Object notuse2) {
		super();
		this.thumbMediaId = thumbMediaId;
		this.author = author;
		this.title = title;
		this.contentSourceUrl = contentSourceUrl;
		this.content = content;
		this.digest = digest;
		this.showCoverPic = showCoverPic;
	}

	
	/**获取消息缩略图的media_id 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得 */
	
@JSONField(name="thumb_media_id")
	
	public String getThumbMediaId() {
		return this.thumbMediaId;
	}
	/**设置消息缩略图的media_id 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得 */
@JSONField(name="thumb_media_id")
	public void setThumbMediaId(String value) {
		this.thumbMediaId = value;
	}
	/**获取作者 图文消息的作者 */
	
@JSONField(name="author")
	
	public String getAuthor() {
		return this.author;
	}
	/**设置作者 图文消息的作者 */
@JSONField(name="author")
	public void setAuthor(String value) {
		this.author = value;
	}
	/**获取标题 图文消息的标题 */
	
@JSONField(name="title")
	
	public String getTitle() {
		return this.title;
	}
	/**设置标题 图文消息的标题 */
@JSONField(name="title")
	public void setTitle(String value) {
		this.title = value;
	}
	/**获取点击“阅读原文”后的页面 在图文消息页面点击“阅读原文”后的页面 */
	
@JSONField(name="content_source_url")
	
	public String getContentSourceUrl() {
		return this.contentSourceUrl;
	}
	/**设置点击“阅读原文”后的页面 在图文消息页面点击“阅读原文”后的页面 */
@JSONField(name="content_source_url")
	public void setContentSourceUrl(String value) {
		this.contentSourceUrl = value;
	}
	/**获取页面内容 图文消息页面的内容，支持HTML标签 */
	
@JSONField(name="content")
	
	public String getContent() {
		return this.content;
	}
	/**设置页面内容 图文消息页面的内容，支持HTML标签 */
@JSONField(name="content")
	public void setContent(String value) {
		this.content = value;
	}
	/**获取描述 图文消息的描述 */
	
@JSONField(name="digest")
	
	public String getDigest() {
		return this.digest;
	}
	/**设置描述 图文消息的描述 */
@JSONField(name="digest")
	public void setDigest(String value) {
		this.digest = value;
	}
	/**获取是否显示封面 是否显示封面，1为显示，0为不显示 */
	
@JSONField(name="show_cover_pic")
	
	public String getShowCoverPic() {
		return this.showCoverPic;
	}
	/**设置是否显示封面 是否显示封面，1为显示，0为不显示 */
@JSONField(name="show_cover_pic")
	public void setShowCoverPic(String value) {
		this.showCoverPic = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",ThumbMediaId:",getThumbMediaId())
			.append(",Author:",getAuthor())
			.append(",Title:",getTitle())
			.append(",ContentSourceUrl:",getContentSourceUrl())
			.append(",Content:",getContent())
			.append(",Digest:",getDigest())
			.append(",ShowCoverPic:",getShowCoverPic())
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
//精简构造 对象之上传素材图文
WaEntityMassmaterialarticle waEntityMassmaterialarticle = new WaEntityMassmaterialarticle(
	thumbMediaId , //String 消息缩略图的media_id  图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得 
	author , //String 作者  图文消息的作者 
	title , //String 标题  图文消息的标题 
	contentSourceUrl , //String 点击“阅读原文”后的页面  在图文消息页面点击“阅读原文”后的页面 
	content , //String 页面内容  图文消息页面的内容，支持HTML标签 
	digest , //String 描述  图文消息的描述 
	showCoverPic , //String 是否显示封面  是否显示封面，1为显示，0为不显示 
	null
);
*/
}
