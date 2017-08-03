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
 * 被动回复消息之文本
 * @author zmax
 * @version 1.0
 * @since 
 */

@XmlRootElement(name="xml")
public class WaSendautomsgText extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "被动回复消息之文本";

	//date formats
	
	//columns START
	/**手机用户微信号 String   */
	
	
	private String tousername;
	/**公众平台的openid String   */
	
	
	private String fromusername;
	/**消息创建时间 （整型） Long   */
	
	
	private Long createtime;
	/**消息类型 String default=text  */
	
	
	private String msgtype;
	/**文本消息内容 String   */
	
	
	private String content;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaSendautomsgText _this){
		_this.msgtype="text";
	}
	public WaSendautomsgText(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param tousername String 手机用户微信号   
	 * @param fromusername String 公众平台的openid   
	 * @param createtime Long 消息创建时间 （整型）   
	 * @param msgtype String 消息类型 default=text  
	 * @param content String 文本消息内容   
	* @param notuse String 没用
	 */
	public WaSendautomsgText(String tousername ,String fromusername ,Long createtime ,String msgtype ,String content ,String notuse) {
		super();
		this.tousername = tousername;
		this.fromusername = fromusername;
		this.createtime = createtime;
		this.msgtype = msgtype;
		this.content = content;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param tousername String 手机用户微信号   
	 * @param fromusername String 公众平台的openid   
	 * @param createtime Long 消息创建时间 （整型）   
	 * @param msgtype String 消息类型 default=text  
	 * @param content String 文本消息内容   
	* @param notuse String 没用
	 */
	public WaSendautomsgText(String tousername ,String fromusername ,Long createtime ,String msgtype ,String content ,String notuse,Object notuse2) {
		super();
		this.tousername = tousername;
		this.fromusername = fromusername;
		this.createtime = createtime;
		this.msgtype = msgtype;
		this.content = content;
	}

	
	/**获取手机用户微信号  */
	@XmlElement(name="ToUserName")

	
	public String getTousername() {
		return this.tousername;
	}
	/**设置手机用户微信号  */

	public void setTousername(String value) {
		this.tousername = value;
	}
	/**获取公众平台的openid  */
	@XmlElement(name="FromUserName")

	
	public String getFromusername() {
		return this.fromusername;
	}
	/**设置公众平台的openid  */

	public void setFromusername(String value) {
		this.fromusername = value;
	}
	/**获取消息创建时间 （整型）  */
	@XmlElement(name="CreateTime")

	
	public Long getCreatetime() {
		return this.createtime;
	}
	/**设置消息创建时间 （整型）  */

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
	/**获取文本消息内容  */
	@XmlElement(name="Content")

	
	public String getContent() {
		return this.content;
	}
	/**设置文本消息内容  */

	public void setContent(String value) {
		this.content = value;
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
			.append(",Content:",getContent())
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
//精简构造 被动回复消息之文本
WaSendautomsgText waSendautomsgText = new WaSendautomsgText(
	tousername , //String 手机用户微信号   
	fromusername , //String 公众平台的openid   
	createtime , //Long 消息创建时间 （整型）   
	msgtype , //String 消息类型 default=text  
	content , //String 文本消息内容   
	null
);
*/
}
