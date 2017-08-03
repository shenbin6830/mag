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
 * 根据openid进行群发之视频第二步
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaAdvmassOpenidvideosParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "根据openid进行群发之视频第二步";

	//date formats
	
	//columns START
	/**填写图文消息的接收者，*/
	private List<String> touser;
	/**视频*/
	private WaAdvmassMpvideo video;
	/**群发的消息类型 String default=video 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video */
	
	
	private String msgtype;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaAdvmassOpenidvideosParam _this){
		_this.msgtype="video";
	}
	public WaAdvmassOpenidvideosParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param msgtype String 群发的消息类型 default=video 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video 
	* @param notuse String 没用
	 */
	public WaAdvmassOpenidvideosParam(String msgtype ,String notuse) {
		super();
		this.msgtype = msgtype;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param touser String 填写图文消息的接收者，  填写图文消息的接收者，一串OpenID列表，OpenID最少个，最多10000个 
	 * @param video String 视频   
	 * @param msgtype String 群发的消息类型 default=video 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video 
	* @param notuse String 没用
	 */
	public WaAdvmassOpenidvideosParam(List<String> touser ,WaAdvmassMpvideo video ,String msgtype ,String notuse,Object notuse2) {
		super();
		this.touser = touser;
		this.video = video;
		this.msgtype = msgtype;
	}

	
	/**对象 获取填写图文消息的接收者， 填写图文消息的接收者，一串OpenID列表，OpenID最少个，最多10000个 */
	
	
@JSONField(name="touser")
	public List<String> getTouser() {
		return this.touser;
	}
	/**设置填写图文消息的接收者， 填写图文消息的接收者，一串OpenID列表，OpenID最少个，最多10000个 */
@JSONField(name="touser")
	public void setTouser(List<String> value) {
		this.touser = value;
	}
	/**对象 获取视频  */
	
	
@JSONField(name="video")
	public WaAdvmassMpvideo getVideo() {
		return this.video;
	}
	/**设置视频  */
@JSONField(name="video")
	public void setVideo(WaAdvmassMpvideo value) {
		this.video = value;
	}
	/**获取群发的消息类型 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video */
	
@JSONField(name="msgtype")
	
	public String getMsgtype() {
		return this.msgtype;
	}
	/**设置群发的消息类型 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video */
@JSONField(name="msgtype")
	public void setMsgtype(String value) {
		this.msgtype = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Msgtype:",getMsgtype())
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
//精简构造 根据openid进行群发之视频第二步
WaAdvmassOpenidvideosParam waAdvmassOpenidvideosParam = new WaAdvmassOpenidvideosParam(
	msgtype , //String 群发的消息类型 default=video 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video 
	null
);
*/
}
