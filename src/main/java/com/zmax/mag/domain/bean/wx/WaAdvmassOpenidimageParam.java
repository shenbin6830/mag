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
 * 根据openid进行群发之图片
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaAdvmassOpenidimageParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "根据openid进行群发之图片";

	//date formats
	
	//columns START
	/**填写图文消息的接收者，*/
	private List<String> touser;
	/**图片*/
	private WaEntityImage image;
	/**群发的消息类型 String default=image 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video */
	
	
	private String msgtype;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaAdvmassOpenidimageParam _this){
		_this.msgtype="image";
	}
	public WaAdvmassOpenidimageParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param msgtype String 群发的消息类型 default=image 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video 
	* @param notuse String 没用
	 */
	public WaAdvmassOpenidimageParam(String msgtype ,String notuse) {
		super();
		this.msgtype = msgtype;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param touser String 填写图文消息的接收者，  填写图文消息的接收者，一串OpenID列表，OpenID最少个，最多10000个 
	 * @param image String 图片   
	 * @param msgtype String 群发的消息类型 default=image 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video 
	* @param notuse String 没用
	 */
	public WaAdvmassOpenidimageParam(List<String> touser ,WaEntityImage image ,String msgtype ,String notuse,Object notuse2) {
		super();
		this.touser = touser;
		this.image = image;
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
	/**对象 获取图片  */
	
	
@JSONField(name="image")
	public WaEntityImage getImage() {
		return this.image;
	}
	/**设置图片  */
@JSONField(name="image")
	public void setImage(WaEntityImage value) {
		this.image = value;
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
//精简构造 根据openid进行群发之图片
WaAdvmassOpenidimageParam waAdvmassOpenidimageParam = new WaAdvmassOpenidimageParam(
	msgtype , //String 群发的消息类型 default=image 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video 
	null
);
*/
}
