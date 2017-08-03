/*
 * zmax 
 * 
 */


//  
package com.zmax.mag.domain.bean;



import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.text.*;
import java.util.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.mag.domain.bean.base.BaseEntity;

import javax.xml.bind.annotation.*;


 
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 接收到的消息<br/>把text,image,event等所有对象的字段时行了整合，得到的对象
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wa_recvmsg")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
@XmlRootElement(name="xml")
public class WaRecvmsg extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "接收到的消息";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**序号ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**服务者微信号 String   */
	@Length(max=50)
	
	private String tousername;
	/**发送方帐号 String  （一个OpenID） */
	@Length(max=50)
	
	private String fromusername;
	/**消息创建时间 Long   （整型） */
	@Max(999999999999999999L)
	
	private Long createtime;
	/**消息类型 String   map={'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'}*/
	@Length(max=20)
	
	private String msgtype;
	/**文本消息内容 String   */
	@Length(max=1000)
	
	private String content;
	/**消息id String  ，64位整型 */
	@Length(max=64)
	
	private String msgid;
	/**图片链接 String   */
	@Length(max=200)
	
	private String picurl;
	/**消息媒体id String  图片消息媒体id，可以调用多媒体文件下载接口拉取数据。 */
	@Length(max=64)
	
	private String mediaid;
	/**语音格式 String  amr，speex等 */
	@Length(max=20)
	
	private String format;
	/**缩略图的媒体id String  可以调用多媒体文件下载接口拉取数据。 */
	@Length(max=50)
	
	private String thumbmediaid;
	/**地理位置维度 Double default=0.0  */
	
	
	private Double locationX;
	/**地理位置经度 Double default=0.0  */
	
	
	private Double locationY;
	/**地图缩放大小 Integer   */
	@Max(999L)
	
	private Integer scale;
	/**地理位置信息 String   */
	@Length(max=50)
	
	private String label1;
	/**消息标题 String   */
	@Length(max=50)
	
	private String title;
	/**消息描述 String   */
	@Length(max=1000)
	
	private String description;
	/**消息链接 String   */
	@Length(max=200)
	
	private String url;
	/**事件类型 String   map={'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'}*/
	@Length(max=20)
	
	private String event;
	/**事件KEY值 String  不同event有不同定义 */
	@Length(max=300)
	
	private String eventkey;
	/**二维码的ticket String  可用来换取二维码图片 */
	@Length(max=150)
	
	private String ticket;
	/**地理位置纬度 Double default=0.0  */
	
	
	private Double latitude;
	/**地理位置经度 Double default=0.0  */
	
	
	private Double longitude;
	/**地理位置精度 Double default=0.0  */
	
	
	private Double precision1;
	/**语音识别结果 String  ，UTF8编码 */
	@Length(max=400)
	
	private String recognition;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaRecvmsg _this){
		_this.gmtCreate=new Date();
		_this.locationX=0.0;
		_this.locationY=0.0;
		_this.latitude=0.0;
		_this.longitude=0.0;
		_this.precision1=0.0;
	}
	public WaRecvmsg(){
		makedefault(this);
	}
	public WaRecvmsg(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param tousername String 服务者微信号   
	 * @param fromusername String 发送方帐号  （一个OpenID） 
	 * @param createtime Long 消息创建时间   （整型） 
	 * @param msgtype String 消息类型   {'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'}
	 * @param content String 文本消息内容   
	 * @param msgid String 消息id  ，64位整型 
	 * @param picurl String 图片链接   
	 * @param mediaid String 消息媒体id  图片消息媒体id，可以调用多媒体文件下载接口拉取数据。 
	 * @param format String 语音格式  amr，speex等 
	 * @param thumbmediaid String 缩略图的媒体id  可以调用多媒体文件下载接口拉取数据。 
	 * @param locationX Double 地理位置维度 default=0.0  
	 * @param locationY Double 地理位置经度 default=0.0  
	 * @param scale Integer 地图缩放大小   
	 * @param label1 String 地理位置信息   
	 * @param title String 消息标题   
	 * @param description String 消息描述   
	 * @param url String 消息链接   
	 * @param event String 事件类型   {'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'}
	 * @param eventkey String 事件KEY值  不同event有不同定义 
	 * @param ticket String 二维码的ticket  可用来换取二维码图片 
	 * @param latitude Double 地理位置纬度 default=0.0  
	 * @param longitude Double 地理位置经度 default=0.0  
	 * @param precision1 Double 地理位置精度 default=0.0  
	 * @param recognition String 语音识别结果  ，UTF8编码 
	* @param notuse String 没用
	 */
	public WaRecvmsg(String tousername ,String fromusername ,Long createtime ,String msgtype ,String content ,String msgid ,String picurl ,String mediaid ,String format ,String thumbmediaid ,Double locationX ,Double locationY ,Integer scale ,String label1 ,String title ,String description ,String url ,String event ,String eventkey ,String ticket ,Double latitude ,Double longitude ,Double precision1 ,String recognition ,String notuse) {
		super();
		this.tousername = tousername;
		this.fromusername = fromusername;
		this.createtime = createtime;
		this.msgtype = msgtype;
		this.content = content;
		this.msgid = msgid;
		this.picurl = picurl;
		this.mediaid = mediaid;
		this.format = format;
		this.thumbmediaid = thumbmediaid;
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.label1 = label1;
		this.title = title;
		this.description = description;
		this.url = url;
		this.event = event;
		this.eventkey = eventkey;
		this.ticket = ticket;
		this.latitude = latitude;
		this.longitude = longitude;
		this.precision1 = precision1;
		this.recognition = recognition;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param tousername String 服务者微信号   
	 * @param fromusername String 发送方帐号  （一个OpenID） 
	 * @param createtime Long 消息创建时间   （整型） 
	 * @param msgtype String 消息类型   {'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'}
	 * @param content String 文本消息内容   
	 * @param msgid String 消息id  ，64位整型 
	 * @param picurl String 图片链接   
	 * @param mediaid String 消息媒体id  图片消息媒体id，可以调用多媒体文件下载接口拉取数据。 
	 * @param format String 语音格式  amr，speex等 
	 * @param thumbmediaid String 缩略图的媒体id  可以调用多媒体文件下载接口拉取数据。 
	 * @param locationX Double 地理位置维度 default=0.0  
	 * @param locationY Double 地理位置经度 default=0.0  
	 * @param scale Integer 地图缩放大小   
	 * @param label1 String 地理位置信息   
	 * @param title String 消息标题   
	 * @param description String 消息描述   
	 * @param url String 消息链接   
	 * @param event String 事件类型   {'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'}
	 * @param eventkey String 事件KEY值  不同event有不同定义 
	 * @param ticket String 二维码的ticket  可用来换取二维码图片 
	 * @param latitude Double 地理位置纬度 default=0.0  
	 * @param longitude Double 地理位置经度 default=0.0  
	 * @param precision1 Double 地理位置精度 default=0.0  
	 * @param recognition String 语音识别结果  ，UTF8编码 
	* @param notuse String 没用
	 */
	public WaRecvmsg(Integer id ,String tousername ,String fromusername ,Long createtime ,String msgtype ,String content ,String msgid ,String picurl ,String mediaid ,String format ,String thumbmediaid ,Double locationX ,Double locationY ,Integer scale ,String label1 ,String title ,String description ,String url ,String event ,String eventkey ,String ticket ,Double latitude ,Double longitude ,Double precision1 ,String recognition ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.tousername = tousername;
		this.fromusername = fromusername;
		this.createtime = createtime;
		this.msgtype = msgtype;
		this.content = content;
		this.msgid = msgid;
		this.picurl = picurl;
		this.mediaid = mediaid;
		this.format = format;
		this.thumbmediaid = thumbmediaid;
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.label1 = label1;
		this.title = title;
		this.description = description;
		this.url = url;
		this.event = event;
		this.eventkey = eventkey;
		this.ticket = ticket;
		this.latitude = latitude;
		this.longitude = longitude;
		this.precision1 = precision1;
		this.recognition = recognition;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param gmtCreate Date 创建时间   
	 * @param tousername String 服务者微信号   
	 * @param fromusername String 发送方帐号  （一个OpenID） 
	 * @param createtime Long 消息创建时间   （整型） 
	 * @param msgtype String 消息类型   {'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'}
	 * @param content String 文本消息内容   
	 * @param msgid String 消息id  ，64位整型 
	 * @param picurl String 图片链接   
	 * @param mediaid String 消息媒体id  图片消息媒体id，可以调用多媒体文件下载接口拉取数据。 
	 * @param format String 语音格式  amr，speex等 
	 * @param thumbmediaid String 缩略图的媒体id  可以调用多媒体文件下载接口拉取数据。 
	 * @param locationX Double 地理位置维度 default=0.0  
	 * @param locationY Double 地理位置经度 default=0.0  
	 * @param scale Integer 地图缩放大小   
	 * @param label1 String 地理位置信息   
	 * @param title String 消息标题   
	 * @param description String 消息描述   
	 * @param url String 消息链接   
	 * @param event String 事件类型   {'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'}
	 * @param eventkey String 事件KEY值  不同event有不同定义 
	 * @param ticket String 二维码的ticket  可用来换取二维码图片 
	 * @param latitude Double 地理位置纬度 default=0.0  
	 * @param longitude Double 地理位置经度 default=0.0  
	 * @param precision1 Double 地理位置精度 default=0.0  
	 * @param recognition String 语音识别结果  ，UTF8编码 
	* @param notuse String 没用
	 */
	public WaRecvmsg(Integer id ,Date gmtCreate ,String tousername ,String fromusername ,Long createtime ,String msgtype ,String content ,String msgid ,String picurl ,String mediaid ,String format ,String thumbmediaid ,Double locationX ,Double locationY ,Integer scale ,String label1 ,String title ,String description ,String url ,String event ,String eventkey ,String ticket ,Double latitude ,Double longitude ,Double precision1 ,String recognition ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.tousername = tousername;
		this.fromusername = fromusername;
		this.createtime = createtime;
		this.msgtype = msgtype;
		this.content = content;
		this.msgid = msgid;
		this.picurl = picurl;
		this.mediaid = mediaid;
		this.format = format;
		this.thumbmediaid = thumbmediaid;
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.label1 = label1;
		this.title = title;
		this.description = description;
		this.url = url;
		this.event = event;
		this.eventkey = eventkey;
		this.ticket = ticket;
		this.latitude = latitude;
		this.longitude = longitude;
		this.precision1 = precision1;
		this.recognition = recognition;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	@XmlTransient
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+id:myname;
		return myname;
	}
	/**我的中文名称*/
	public void setMyname(String myname) {
		this.myname = myname;
	}
	/**我的带id中文名称*/
	private String mynameid;
	/**我的带id中文名称*/
	@Transient
	@XmlTransient
	//@JSONField(serialize = false)
	public String getMynameid() {
		mynameid=(mynameid==null)?"["+id+"]":mynameid;
		return mynameid;
	}
	/**我的带id中文名称*/
	public void setMynameid(String mynameid) {
		this.mynameid = mynameid;
	}
	/**设置主键*/
	public void setId(Integer value) {
		this.id = value;
	}
	/**获取主键*/
	

	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "identity")
	@Column(name = "id",  unique = false, nullable = false, insertable = true, updatable = true, length = 11)
	public Integer getId() {
		return this.id;
	}
	
	/**获取创建时间  */
	

	@Column(name = "gmt_create", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtCreate() {
		return this.gmtCreate;
	}
	/**设置创建时间  */

	public void setGmtCreate(Date value) {
		this.gmtCreate = value;
	}
	/**获取服务者微信号  */
	@XmlElement(name="ToUserName")

	@Column(name = "tousername", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getTousername() {
		return this.tousername;
	}
	/**设置服务者微信号  */

	public void setTousername(String value) {
		this.tousername = value;
	}
	/**获取发送方帐号 （一个OpenID） */
	@XmlElement(name="FromUserName")

	@Column(name = "fromusername", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFromusername() {
		return this.fromusername;
	}
	/**设置发送方帐号 （一个OpenID） */

	public void setFromusername(String value) {
		this.fromusername = value;
	}
	/**获取消息创建时间  （整型） */
	@XmlElement(name="CreateTime")

	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public Long getCreatetime() {
		return this.createtime;
	}
	/**设置消息创建时间  （整型） */

	public void setCreatetime(Long value) {
		this.createtime = value;
	}
	/**获取消息类型  {'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'}*/
	@XmlElement(name="MsgType")

	@Column(name = "msgtype", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getMsgtype() {
		return this.msgtype;
	}
	/**设置消息类型  {'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'}*/

	public void setMsgtype(String value) {
		this.msgtype = value;
	}
	/**获取文本消息内容  */
	@XmlElement(name="Content")

	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getContent() {
		return this.content;
	}
	/**设置文本消息内容  */

	public void setContent(String value) {
		this.content = value;
	}
	/**获取消息id ，64位整型 */
	@XmlElement(name="MsgId")

	@Column(name = "msgid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getMsgid() {
		return this.msgid;
	}
	/**设置消息id ，64位整型 */

	public void setMsgid(String value) {
		this.msgid = value;
	}
	/**获取图片链接  */
	@XmlElement(name="PicUr")

	@Column(name = "picurl", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getPicurl() {
		return this.picurl;
	}
	/**设置图片链接  */

	public void setPicurl(String value) {
		this.picurl = value;
	}
	/**获取消息媒体id 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。 */
	@XmlElement(name="MediaId")

	@Column(name = "mediaid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getMediaid() {
		return this.mediaid;
	}
	/**设置消息媒体id 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。 */

	public void setMediaid(String value) {
		this.mediaid = value;
	}
	/**获取语音格式 amr，speex等 */
	@XmlElement(name="Format")

	@Column(name = "format", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getFormat() {
		return this.format;
	}
	/**设置语音格式 amr，speex等 */

	public void setFormat(String value) {
		this.format = value;
	}
	/**获取缩略图的媒体id 可以调用多媒体文件下载接口拉取数据。 */
	@XmlElement(name="ThumbMediaId")

	@Column(name = "thumbmediaid", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getThumbmediaid() {
		return this.thumbmediaid;
	}
	/**设置缩略图的媒体id 可以调用多媒体文件下载接口拉取数据。 */

	public void setThumbmediaid(String value) {
		this.thumbmediaid = value;
	}
	/**获取地理位置维度  */
	@XmlElement(name="Location_X")

	@Column(name = "location_x", columnDefinition="double(11,6) default '0.00'", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Double getLocationX() {
		return this.locationX;
	}
	/**设置地理位置维度  */

	public void setLocationX(Double value) {
		this.locationX = value;
	}
	/**获取地理位置经度  */
	@XmlElement(name="Location_Y")

	@Column(name = "location_y", columnDefinition="double(11,6) default '0.00'", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Double getLocationY() {
		return this.locationY;
	}
	/**设置地理位置经度  */

	public void setLocationY(Double value) {
		this.locationY = value;
	}
	/**获取地图缩放大小  */
	@XmlElement(name="Scale")

	@Column(name = "scale", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getScale() {
		return this.scale;
	}
	/**设置地图缩放大小  */

	public void setScale(Integer value) {
		this.scale = value;
	}
	/**获取地理位置信息  */
	@XmlElement(name="Label")

	@Column(name = "label1", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getLabel1() {
		return this.label1;
	}
	/**设置地理位置信息  */

	public void setLabel1(String value) {
		this.label1 = value;
	}
	/**获取消息标题  */
	@XmlElement(name="Title")

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getTitle() {
		return this.title;
	}
	/**设置消息标题  */

	public void setTitle(String value) {
		this.title = value;
	}
	/**获取消息描述  */
	@XmlElement(name="Description")

	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getDescription() {
		return this.description;
	}
	/**设置消息描述  */

	public void setDescription(String value) {
		this.description = value;
	}
	/**获取消息链接  */
	@XmlElement(name="Url")

	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getUrl() {
		return this.url;
	}
	/**设置消息链接  */

	public void setUrl(String value) {
		this.url = value;
	}
	/**获取事件类型  {'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'}*/
	@XmlElement(name="Event")

	@Column(name = "event", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getEvent() {
		return this.event;
	}
	/**设置事件类型  {'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'}*/

	public void setEvent(String value) {
		this.event = value;
	}
	/**获取事件KEY值 不同event有不同定义 */
	@XmlElement(name="EventKey")

	@Column(name = "eventkey", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public String getEventkey() {
		return this.eventkey;
	}
	/**设置事件KEY值 不同event有不同定义 */

	public void setEventkey(String value) {
		this.eventkey = value;
	}
	/**获取二维码的ticket 可用来换取二维码图片 */
	@XmlElement(name="Ticket")

	@Column(name = "ticket", unique = false, nullable = true, insertable = true, updatable = true, length = 150)
	public String getTicket() {
		return this.ticket;
	}
	/**设置二维码的ticket 可用来换取二维码图片 */

	public void setTicket(String value) {
		this.ticket = value;
	}
	/**获取地理位置纬度  */
	@XmlElement(name="Latitude")

	@Column(name = "latitude", columnDefinition="double(11,6) default '0.00'", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Double getLatitude() {
		return this.latitude;
	}
	/**设置地理位置纬度  */

	public void setLatitude(Double value) {
		this.latitude = value;
	}
	/**获取地理位置经度  */
	@XmlElement(name="Longitude")

	@Column(name = "longitude", columnDefinition="double(11,6) default '0.00'", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Double getLongitude() {
		return this.longitude;
	}
	/**设置地理位置经度  */

	public void setLongitude(Double value) {
		this.longitude = value;
	}
	/**获取地理位置精度  */
	@XmlElement(name="Precision")

	@Column(name = "precision1", columnDefinition="double(11,6) default '0.00'", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Double getPrecision1() {
		return this.precision1;
	}
	/**设置地理位置精度  */

	public void setPrecision1(Double value) {
		this.precision1 = value;
	}
	/**获取语音识别结果 ，UTF8编码 */
	@XmlElement(name="Recognition")

	@Column(name = "recognition", unique = false, nullable = true, insertable = true, updatable = true, length = 400)
	public String getRecognition() {
		return this.recognition;
	}
	/**设置语音识别结果 ，UTF8编码 */

	public void setRecognition(String value) {
		this.recognition = value;
	}
	/**创建时间String*/
	private String gmtCreateString;
	/**获取创建时间String*/
	@Transient
	@XmlTransient
	public String getGmtCreateString() {
		if(gmtCreateString==null && gmtCreate!=null)
			gmtCreateString=new SimpleDateFormat(DATE_FORMAT).format(gmtCreate);
		return gmtCreateString;
	}
	/**设置创建时间String*/
	public void setGmtCreateString(String value) {
		this.gmtCreateString=value;
	}
	/**消息类型String*/
	private String msgtypeString;
	/**获取消息类型String*/
	@Transient
	@XmlTransient
	public String getMsgtypeString() {
		return msgtypeString;
	}
	/**设置消息类型String*/
	public void setMsgtypeString(String value) {
		this.msgtypeString=value;
	}
	/**消息类型Map*/
	private Map<String, Object> msgtypeMap;
	/**获取消息类型Map*/
	@Transient
	@XmlTransient
	public Map<String, Object> getMsgtypeMap() {
		return msgtypeMap;
	}
	/**设置消息类型Map*/
	public void setMsgtypeMap(Map<String, Object> value) {
		this.msgtypeMap=value;
	}
	/**事件类型String*/
	private String eventString;
	/**获取事件类型String*/
	@Transient
	@XmlTransient
	public String getEventString() {
		return eventString;
	}
	/**设置事件类型String*/
	public void setEventString(String value) {
		this.eventString=value;
	}
	/**事件类型Map*/
	private Map<String, Object> eventMap;
	/**获取事件类型Map*/
	@Transient
	@XmlTransient
	public Map<String, Object> getEventMap() {
		return eventMap;
	}
	/**设置事件类型Map*/
	public void setEventMap(Map<String, Object> value) {
		this.eventMap=value;
	}

	/**
	 * 清空ONE MANY对象，用于WS,JSON等场景
	 */
	public void makeNullObj(){
		this.obj1=null;
		this.obj2=null;
		this.obj3=null;
		this.obj4=null;
		this.obj5=null;
		this.objint1=null;
		this.objint2=null;
		this.objint3=null;
		this.objstring1=null;
		this.objstring2=null;
		this.objstring3=null;
		this.msgtypeMap=null;
		this.eventMap=null;
	}
	/**备用对象1*/
	private Object obj1;
	/**
	 * 获取备用对象1
	 * @return Object
	 */
	@Transient
	@XmlTransient
	public Object getObj1() {
		return obj1;
	}
	/**
	 * 设置备用对象1
	 * @param obj1
	 */
	public void setObj1(Object obj1) {
		this.obj1 = obj1;
	}
	/**备用对象2*/
	private Object obj2;
	/**
	 * 获取备用对象2
	 * @return Object
	 */
	@Transient
	@XmlTransient
	public Object getObj2() {
		return obj2;
	}
	/**
	 * 设置备用对象2
	 * @param obj2
	 */
	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}
	/**备用对象3*/
	private Object obj3;
	/**
	 * 获取备用对象3
	 * @return Object
	 */
	@Transient
	@XmlTransient
	public Object getObj3() {
		return obj3;
	}
	/**
	 * 设置备用对象3
	 * @param obj3
	 */
	public void setObj3(Object obj3) {
		this.obj3 = obj3;
	}
	/**备用对象4*/
	private Object obj4;
	/**
	 * 获取备用对象4
	 * @return Object
	 */
	@Transient
	@XmlTransient
	public Object getObj4() {
		return obj4;
	}
	/**
	 * 设置备用对象4
	 * @param obj4
	 */
	public void setObj4(Object obj4) {
		this.obj4 = obj4;
	}
	/**备用对象5*/
	private Object obj5;
	/**
	 * 获取备用对象5
	 * @return Object
	 */
	@Transient
	@XmlTransient
	public Object getObj5() {
		return obj5;
	}
	/**
	 * 设置备用对象5
	 * @param obj5
	 */
	public void setObj5(Object obj5) {
		this.obj5 = obj5;
	}
	/**备用对象 整数1*/
	private Integer objint1;
	/**
	 * 获取备用对象 整数1
	 * @return int
	 */
	@Transient
	@XmlTransient
	public Integer getObjint1() {
		return objint1;
	}
	/**
	 * 设置备用对象 整数1
	 * @param objint1 int
	 */
	public void setObjint1(Integer objint1) {
		this.objint1 = objint1;
	}
	/**备用对象 文本1*/
	private String objstring1;
	/**
	 * 获取备用对象 文本1
	 * @return string
	 */
	@Transient
	@XmlTransient
	public String getObjstring1() {
		return objstring1;
	}
	/**
	 * 设置备用对象 文本1
	 * @param objstring1 String
	 */
	public void setObjstring1(String objstring1) {
		this.objstring1 = objstring1;
	}
	/**备用对象 整数2*/
	private Integer objint2;
	/**
	 * 获取备用对象 整数2
	 * @return int
	 */
	@Transient
	@XmlTransient
	public Integer getObjint2() {
		return objint2;
	}
	/**
	 * 设置备用对象 整数2
	 * @param objint2 int
	 */
	public void setObjint2(Integer objint2) {
		this.objint2 = objint2;
	}
	/**备用对象 文本2*/
	private String objstring2;
	/**
	 * 获取备用对象 文本2
	 * @return string
	 */
	@Transient
	@XmlTransient
	public String getObjstring2() {
		return objstring2;
	}
	/**
	 * 设置备用对象 文本2
	 * @param objstring2 String
	 */
	public void setObjstring2(String objstring2) {
		this.objstring2 = objstring2;
	}
	/**备用对象 整数3*/
	private Integer objint3;
	/**
	 * 获取备用对象 整数3
	 * @return int
	 */
	@Transient
	@XmlTransient
	public Integer getObjint3() {
		return objint3;
	}
	/**
	 * 设置备用对象 整数3
	 * @param objint3 int
	 */
	public void setObjint3(Integer objint3) {
		this.objint3 = objint3;
	}
	/**备用对象 文本3*/
	private String objstring3;
	/**
	 * 获取备用对象 文本3
	 * @return string
	 */
	@Transient
	@XmlTransient
	public String getObjstring3() {
		return objstring3;
	}
	/**
	 * 设置备用对象 文本3
	 * @param objstring3 String
	 */
	public void setObjstring3(String objstring3) {
		this.objstring3 = objstring3;
	}
	/**
	 * Hash，同所有字段相加判断
	 */
	public int hashCode() {
		if(getId()==null)
		return new HashCodeBuilder()
			.append(getId())
			.append(getGmtCreate())
			.append(getTousername())
			.append(getFromusername())
			.append(getCreatetime())
			.append(getMsgtype())
			.append(getContent())
			.append(getMsgid())
			.append(getPicurl())
			.append(getMediaid())
			.append(getFormat())
			.append(getThumbmediaid())
			.append(getLocationX())
			.append(getLocationY())
			.append(getScale())
			.append(getLabel1())
			.append(getTitle())
			.append(getDescription())
			.append(getUrl())
			.append(getEvent())
			.append(getEventkey())
			.append(getTicket())
			.append(getLatitude())
			.append(getLongitude())
			.append(getPrecision1())
			.append(getRecognition())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof WaRecvmsg == false) return false;
		if(this == obj) return true;
		WaRecvmsg other = (WaRecvmsg)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Id:",getId())
			.append(",GmtCreate:",getGmtCreate())
			.append(",Tousername:",getTousername())
			.append(",Fromusername:",getFromusername())
			.append(",Createtime:",getCreatetime())
			.append(",Msgtype:",getMsgtype())
			.append(",Content:",getContent())
			.append(",Msgid:",getMsgid())
			.append(",Picurl:",getPicurl())
			.append(",Mediaid:",getMediaid())
			.append(",Format:",getFormat())
			.append(",Thumbmediaid:",getThumbmediaid())
			.append(",LocationX:",getLocationX())
			.append(",LocationY:",getLocationY())
			.append(",Scale:",getScale())
			.append(",Label1:",getLabel1())
			.append(",Title:",getTitle())
			.append(",Description:",getDescription())
			.append(",Url:",getUrl())
			.append(",Event:",getEvent())
			.append(",Eventkey:",getEventkey())
			.append(",Ticket:",getTicket())
			.append(",Latitude:",getLatitude())
			.append(",Longitude:",getLongitude())
			.append(",Precision1:",getPrecision1())
			.append(",Recognition:",getRecognition())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","msgtypeString","msgtypeMap","eventString","eventMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"id","gmtCreate","myname","mynameid","gmtCreateString","msgtypeString","msgtypeMap","eventString","eventMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 接收到的消息
WaRecvmsg waRecvmsg = new WaRecvmsg(
	tousername , //String 服务者微信号   
	fromusername , //String 发送方帐号  （一个OpenID） 
	createtime , //Long 消息创建时间   （整型） 
	msgtype , //String 消息类型   {'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'}
	content , //String 文本消息内容   
	msgid , //String 消息id  ，64位整型 
	picurl , //String 图片链接   
	mediaid , //String 消息媒体id  图片消息媒体id，可以调用多媒体文件下载接口拉取数据。 
	format , //String 语音格式  amr，speex等 
	thumbmediaid , //String 缩略图的媒体id  可以调用多媒体文件下载接口拉取数据。 
	locationX , //Double 地理位置维度 default=0.0  
	locationY , //Double 地理位置经度 default=0.0  
	scale , //Integer 地图缩放大小   
	label1 , //String 地理位置信息   
	title , //String 消息标题   
	description , //String 消息描述   
	url , //String 消息链接   
	event , //String 事件类型   {'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'}
	eventkey , //String 事件KEY值  不同event有不同定义 
	ticket , //String 二维码的ticket  可用来换取二维码图片 
	latitude , //Double 地理位置纬度 default=0.0  
	longitude , //Double 地理位置经度 default=0.0  
	precision1 , //Double 地理位置精度 default=0.0  
	recognition , //String 语音识别结果  ，UTF8编码 
	null
);
*/
}
