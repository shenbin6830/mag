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
 * 被动回复消息之图文
 * @author zmax
 * @version 1.0
 * @since 
 */

@XmlRootElement(name="xml")
public class WaSendautomsgArticles extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "被动回复消息之图文";

	//date formats
	
	//columns START
	/**服务者微信号 String   */
	
	
	private String tousername;
	/**发送方帐号 String  发送方帐号（一个OpenID） */
	
	
	private String fromusername;
	/**消息创建时间 Long  消息创建时间 （整型） */
	
	
	private Long createtime;
	/**消息类型 String default=news  */
	
	
	private String msgtype;
	/**图文消息个数 Integer default=1 限制为10条以内  */
	
	
	private Integer articlecount = 1;
	/**图文列表对象*/
	private WaEntityArticles news;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaSendautomsgArticles _this){
		_this.msgtype="news";
		_this.articlecount=1;
	}
	public WaSendautomsgArticles(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param tousername String 服务者微信号   
	 * @param fromusername String 发送方帐号  发送方帐号（一个OpenID） 
	 * @param createtime Long 消息创建时间  消息创建时间 （整型） 
	 * @param msgtype String 消息类型 default=news  
	 * @param articlecount Integer 图文消息个数 default=1 限制为10条以内  
	* @param notuse String 没用
	 */
	public WaSendautomsgArticles(String tousername ,String fromusername ,Long createtime ,String msgtype ,Integer articlecount ,String notuse) {
		super();
		this.tousername = tousername;
		this.fromusername = fromusername;
		this.createtime = createtime;
		this.msgtype = msgtype;
		this.articlecount = articlecount;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param tousername String 服务者微信号   
	 * @param fromusername String 发送方帐号  发送方帐号（一个OpenID） 
	 * @param createtime Long 消息创建时间  消息创建时间 （整型） 
	 * @param msgtype String 消息类型 default=news  
	 * @param articlecount Integer 图文消息个数 default=1 限制为10条以内  
	 * @param news String 图文列表对象   
	* @param notuse String 没用
	 */
	public WaSendautomsgArticles(String tousername ,String fromusername ,Long createtime ,String msgtype ,Integer articlecount ,WaEntityArticles news ,String notuse,Object notuse2) {
		super();
		this.tousername = tousername;
		this.fromusername = fromusername;
		this.createtime = createtime;
		this.msgtype = msgtype;
		this.articlecount = articlecount;
		this.news = news;
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
	/**获取图文消息个数 限制为10条以内  */
	@XmlElement(name="ArticleCount")

	
	public Integer getArticlecount() {
		return this.articlecount;
	}
	/**设置图文消息个数 限制为10条以内  */

	public void setArticlecount(Integer value) {
		this.articlecount = value;
	}
	/**对象 获取图文列表对象  */
	
	@XmlElement(name="Articles")

	public WaEntityArticles getNews() {
		return this.news;
	}
	/**设置图文列表对象  */

	public void setNews(WaEntityArticles value) {
		this.news = value;
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
			.append(",Articlecount:",getArticlecount())
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
		String[] excludesProperties={"fromusername","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 被动回复消息之图文
WaSendautomsgArticles waSendautomsgArticles = new WaSendautomsgArticles(
	tousername , //String 服务者微信号   
	fromusername , //String 发送方帐号  发送方帐号（一个OpenID） 
	createtime , //Long 消息创建时间  消息创建时间 （整型） 
	msgtype , //String 消息类型 default=news  
	articlecount , //Integer 图文消息个数 default=1 限制为10条以内  
	null
);
*/
}
