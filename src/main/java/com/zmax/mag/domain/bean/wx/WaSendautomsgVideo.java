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


import com.zmax.mag.domain.bean.*;


/**
 * 被动回复消息之视频
 * @author zmax
 * @version 1.0
 * @since 
 */

@XmlRootElement(name="xml")
public class WaSendautomsgVideo extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "被动回复消息之视频";

	//date formats
	
	//columns START
	/**服务者微信号 String   */
	
	
	private String tousername;
	/**发送方帐号 String  发送方帐号（一个OpenID） */
	
	
	private String fromusername;
	/**消息创建时间 Long  消息创建时间 （整型） */
	
	
	private Long createtime;
	/**消息类型 String default=video  */
	
	
	private String msgtype;
	/**视频对象*/
	private WaEntityVideo video;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaSendautomsgVideo _this){
		_this.msgtype="video";
	}
	public WaSendautomsgVideo(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param tousername String 服务者微信号   
	 * @param fromusername String 发送方帐号  发送方帐号（一个OpenID） 
	 * @param createtime Long 消息创建时间  消息创建时间 （整型） 
	 * @param msgtype String 消息类型 default=video  
	* @param notuse String 没用
	 */
	public WaSendautomsgVideo(String tousername ,String fromusername ,Long createtime ,String msgtype ,String notuse) {
		super();
		this.tousername = tousername;
		this.fromusername = fromusername;
		this.createtime = createtime;
		this.msgtype = msgtype;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param tousername String 服务者微信号   
	 * @param fromusername String 发送方帐号  发送方帐号（一个OpenID） 
	 * @param createtime Long 消息创建时间  消息创建时间 （整型） 
	 * @param msgtype String 消息类型 default=video  
	 * @param video String 视频对象   
	* @param notuse String 没用
	 */
	public WaSendautomsgVideo(String tousername ,String fromusername ,Long createtime ,String msgtype ,WaEntityVideo video ,String notuse,Object notuse2) {
		super();
		this.tousername = tousername;
		this.fromusername = fromusername;
		this.createtime = createtime;
		this.msgtype = msgtype;
		this.video = video;
	}

	
	/**获取服务者微信号  */
	@XmlElement(name="ToUserName")

	
	public String getTousername() {
		return this.tousername;
	}
	/**设置服务者微信号  */

	public void setTousername(String value) {
		this.tousername = value;
	}
	/**获取发送方帐号 发送方帐号（一个OpenID） */
	@XmlElement(name="FromUserName")

	
	public String getFromusername() {
		return this.fromusername;
	}
	/**设置发送方帐号 发送方帐号（一个OpenID） */

	public void setFromusername(String value) {
		this.fromusername = value;
	}
	/**获取消息创建时间 消息创建时间 （整型） */
	@XmlElement(name="CreateTime")

	
	public Long getCreatetime() {
		return this.createtime;
	}
	/**设置消息创建时间 消息创建时间 （整型） */

	public void setCreatetime(Long value) {
		this.createtime = value;
	}
	/**获取消息类型  */
	@XmlElement(name="MsgType")

	
	public String getMsgtype() {
		return this.msgtype;
	}
	/**设置消息类型  */

	public void setMsgtype(String value) {
		this.msgtype = value;
	}
	/**对象 获取视频对象  */
	
	@XmlElement(name="Video")

	public WaEntityVideo getVideo() {
		return this.video;
	}
	/**设置视频对象  */

	public void setVideo(WaEntityVideo value) {
		this.video = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Tousername:",getTousername())
			.append(",Fromusername:",getFromusername())
			.append(",Createtime:",getCreatetime())
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
//精简构造 被动回复消息之视频
WaSendautomsgVideo waSendautomsgVideo = new WaSendautomsgVideo(
	tousername , //String 服务者微信号   
	fromusername , //String 发送方帐号  发送方帐号（一个OpenID） 
	createtime , //Long 消息创建时间  消息创建时间 （整型） 
	msgtype , //String 消息类型 default=video  
	null
);
*/
}
