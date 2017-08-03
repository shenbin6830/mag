/*
 * zmax 
 * 
 */


// BaseResult 
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

import com.alibaba.fastjson.annotation.*;

import com.zmax.mag.domain.bean.wx.*;

 
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 微信用户
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wxouser")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Wxouser extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "微信用户";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**微信openid String   */
	@Length(max=128)
	
	private String id;
	/**微信openid*/
	private String openid;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**所属卖家 Integer  实际上是bizId */
	@Max(99999999999L)
	
	private Integer sellerId;
	/**平台用户 Integer   */
	@Max(99999999999L)
	
	private Integer userId;
	/**用户信息类型 Integer default=1  map={'0':'只有openid','1':'完全版'}*/
	@Max(999L)
	
	private Integer statusuf = 1;
	/**用户昵称 String   */
	@Length(max=50)
	
	private String nickname;
	/**性别 Integer default=0  map={'1':'男','2':'女','0':'未知'}*/
	@Max(999L)
	
	private Integer sex = 0;
	/**省份 String  160x160 */
	@Length(max=20)
	
	private String province;
	/**城市 String default=0  */
	@Length(max=40)
	
	private String city;
	/**国家 String default=0  */
	@Length(max=20)
	
	private String country;
	/**用户头像 String   */
	@Length(max=200)
	
	private String headimgurl;
	/**用户特权信息 String   */
	@Length(max=20)
	
	private String privilege;
	/**微信标识 String   */
	@Length(max=50)
	
	private String unionid;
	/**真实姓名 String   */
	@Length(max=20)
	
	private String realname;
	/**手机号 String   */
	@Length(max=11)
	
	private String mobile;
	/**电子邮件 String   */
	@Length(max=50)
	
	private String email;
	/**收货邮编 String   */
	@Length(max=20)
	
	private String orderZip;
	/**收货地址 String   */
	@Length(max=200)
	
	private String orderAddress;
	/**是否订阅 Integer default=0 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 map={'0':'没有关注该公众号','1':'关注过了该公众号'}*/
	@Max(999L)
	
	private Integer subscribe = 0;
	/**关注时间 String  用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 */
	@Length(max=20)
	
	private String subscribeTime;
	/**备注 String  公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 */
	@Length(max=100)
	
	private String remark;
	/**所在的分组ID Integer  用户所在的分组ID */
	@Max(99999999999L)
	
	private Integer groupid;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Wxouser _this){
		_this.gmtCreate=new Date();
		_this.statusuf=1;
		_this.sex=0;
		_this.city="0";
		_this.country="0";
		_this.subscribe=0;
	}
	public Wxouser(){
		makedefault(this);
	}
	public Wxouser(String _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param sellerId Integer 所属卖家  实际上是bizId 
	 * @param userId Integer 平台用户   
	 * @param statusuf Integer 用户信息类型 default=1  {'0':'只有openid','1':'完全版'}
	 * @param nickname String 用户昵称   
	 * @param sex Integer 性别 default=0  {'1':'男','2':'女','0':'未知'}
	 * @param province String 省份  160x160 
	 * @param city String 城市 default=0  
	 * @param country String 国家 default=0  
	 * @param headimgurl String 用户头像   
	 * @param privilege String 用户特权信息   
	 * @param unionid String 微信标识   
	 * @param realname String 真实姓名   
	 * @param mobile String 手机号   
	 * @param email String 电子邮件   
	 * @param orderZip String 收货邮编   
	 * @param orderAddress String 收货地址   
	 * @param subscribe Integer 是否订阅 default=0 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 {'0':'没有关注该公众号','1':'关注过了该公众号'}
	 * @param subscribeTime String 关注时间  用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 
	 * @param remark String 备注  公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 
	 * @param groupid Integer 所在的分组ID  用户所在的分组ID 
	* @param notuse String 没用
	 */
	public Wxouser(Integer sellerId ,Integer userId ,Integer statusuf ,String nickname ,Integer sex ,String province ,String city ,String country ,String headimgurl ,String privilege ,String unionid ,String realname ,String mobile ,String email ,String orderZip ,String orderAddress ,Integer subscribe ,String subscribeTime ,String remark ,Integer groupid ,String notuse) {
		super();
		this.sellerId = sellerId;
		this.userId = userId;
		this.statusuf = statusuf;
		this.nickname = nickname;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.country = country;
		this.headimgurl = headimgurl;
		this.privilege = privilege;
		this.unionid = unionid;
		this.realname = realname;
		this.mobile = mobile;
		this.email = email;
		this.orderZip = orderZip;
		this.orderAddress = orderAddress;
		this.subscribe = subscribe;
		this.subscribeTime = subscribeTime;
		this.remark = remark;
		this.groupid = groupid;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id String 微信openid   
	 * @param sellerId Integer 所属卖家  实际上是bizId 
	 * @param userId Integer 平台用户   
	 * @param statusuf Integer 用户信息类型 default=1  {'0':'只有openid','1':'完全版'}
	 * @param nickname String 用户昵称   
	 * @param sex Integer 性别 default=0  {'1':'男','2':'女','0':'未知'}
	 * @param province String 省份  160x160 
	 * @param city String 城市 default=0  
	 * @param country String 国家 default=0  
	 * @param headimgurl String 用户头像   
	 * @param privilege String 用户特权信息   
	 * @param unionid String 微信标识   
	 * @param realname String 真实姓名   
	 * @param mobile String 手机号   
	 * @param email String 电子邮件   
	 * @param orderZip String 收货邮编   
	 * @param orderAddress String 收货地址   
	 * @param subscribe Integer 是否订阅 default=0 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 {'0':'没有关注该公众号','1':'关注过了该公众号'}
	 * @param subscribeTime String 关注时间  用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 
	 * @param remark String 备注  公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 
	 * @param groupid Integer 所在的分组ID  用户所在的分组ID 
	* @param notuse String 没用
	 */
	public Wxouser(String id ,Integer sellerId ,Integer userId ,Integer statusuf ,String nickname ,Integer sex ,String province ,String city ,String country ,String headimgurl ,String privilege ,String unionid ,String realname ,String mobile ,String email ,String orderZip ,String orderAddress ,Integer subscribe ,String subscribeTime ,String remark ,Integer groupid ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.sellerId = sellerId;
		this.userId = userId;
		this.statusuf = statusuf;
		this.nickname = nickname;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.country = country;
		this.headimgurl = headimgurl;
		this.privilege = privilege;
		this.unionid = unionid;
		this.realname = realname;
		this.mobile = mobile;
		this.email = email;
		this.orderZip = orderZip;
		this.orderAddress = orderAddress;
		this.subscribe = subscribe;
		this.subscribeTime = subscribeTime;
		this.remark = remark;
		this.groupid = groupid;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id String 微信openid   
	 * @param openid String 微信openid   
	 * @param gmtCreate Date 创建时间   
	 * @param sellerId Integer 所属卖家  实际上是bizId 
	 * @param userId Integer 平台用户   
	 * @param statusuf Integer 用户信息类型 default=1  {'0':'只有openid','1':'完全版'}
	 * @param nickname String 用户昵称   
	 * @param sex Integer 性别 default=0  {'1':'男','2':'女','0':'未知'}
	 * @param province String 省份  160x160 
	 * @param city String 城市 default=0  
	 * @param country String 国家 default=0  
	 * @param headimgurl String 用户头像   
	 * @param privilege String 用户特权信息   
	 * @param unionid String 微信标识   
	 * @param realname String 真实姓名   
	 * @param mobile String 手机号   
	 * @param email String 电子邮件   
	 * @param orderZip String 收货邮编   
	 * @param orderAddress String 收货地址   
	 * @param subscribe Integer 是否订阅 default=0 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 {'0':'没有关注该公众号','1':'关注过了该公众号'}
	 * @param subscribeTime String 关注时间  用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 
	 * @param remark String 备注  公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 
	 * @param groupid Integer 所在的分组ID  用户所在的分组ID 
	* @param notuse String 没用
	 */
	public Wxouser(String id ,String openid ,Date gmtCreate ,Integer sellerId ,Integer userId ,Integer statusuf ,String nickname ,Integer sex ,String province ,String city ,String country ,String headimgurl ,String privilege ,String unionid ,String realname ,String mobile ,String email ,String orderZip ,String orderAddress ,Integer subscribe ,String subscribeTime ,String remark ,Integer groupid ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.openid = openid;
		this.gmtCreate = gmtCreate;
		this.sellerId = sellerId;
		this.userId = userId;
		this.statusuf = statusuf;
		this.nickname = nickname;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.country = country;
		this.headimgurl = headimgurl;
		this.privilege = privilege;
		this.unionid = unionid;
		this.realname = realname;
		this.mobile = mobile;
		this.email = email;
		this.orderZip = orderZip;
		this.orderAddress = orderAddress;
		this.subscribe = subscribe;
		this.subscribeTime = subscribeTime;
		this.remark = remark;
		this.groupid = groupid;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+nickname+" ":myname;
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
	
	//@JSONField(serialize = false)
	public String getMynameid() {
		mynameid=(mynameid==null)?"["+id+"]"+nickname+" ":mynameid;
		return mynameid;
	}
	/**我的带id中文名称*/
	public void setMynameid(String mynameid) {
		this.mynameid = mynameid;
	}
	/**设置主键*/
	public void setId(String value) {
		this.id = value;
	}
	/**获取主键*/
	

	@Id 
	@Column(name = "id",  unique = true, nullable = false, insertable = true, updatable = true, length = 128)
	public String getId() {
		return this.id;
	}
	
	/**对象 获取微信openid  */
	@Transient
	
@JSONField(name="openid")
	public String getOpenid() {
		return this.openid;
	}
	/**设置微信openid  */
@JSONField(name="openid")
	public void setOpenid(String value) {
		this.openid = value;
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
	/**获取所属卖家 实际上是bizId */
	

	@Column(name = "seller_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getSellerId() {
		return this.sellerId;
	}
	/**设置所属卖家 实际上是bizId */

	public void setSellerId(Integer value) {
		this.sellerId = value;
	}
	/**获取平台用户  */
	

	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getUserId() {
		return this.userId;
	}
	/**设置平台用户  */

	public void setUserId(Integer value) {
		this.userId = value;
	}
	/**获取用户信息类型  {'0':'只有openid','1':'完全版'}*/
	
@JSONField(name="statusuf")
	@Column(name = "statusuf", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatusuf() {
		return this.statusuf;
	}
	/**设置用户信息类型  {'0':'只有openid','1':'完全版'}*/
@JSONField(name="statusuf")
	public void setStatusuf(Integer value) {
		this.statusuf = value;
	}
	/**获取用户昵称  */
	
@JSONField(name="nickname")
	@Column(name = "nickname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getNickname() {
		return this.nickname;
	}
	/**设置用户昵称  */
@JSONField(name="nickname")
	public void setNickname(String value) {
		this.nickname = value;
	}
	/**获取性别  {'1':'男','2':'女','0':'未知'}*/
	
@JSONField(name="sex")
	@Column(name = "sex", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSex() {
		return this.sex;
	}
	/**设置性别  {'1':'男','2':'女','0':'未知'}*/
@JSONField(name="sex")
	public void setSex(Integer value) {
		this.sex = value;
	}
	/**获取省份 160x160 */
	
@JSONField(name="province")
	@Column(name = "province", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getProvince() {
		return this.province;
	}
	/**设置省份 160x160 */
@JSONField(name="province")
	public void setProvince(String value) {
		this.province = value;
	}
	/**获取城市  */
	
@JSONField(name="city")
	@Column(name = "city", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getCity() {
		return this.city;
	}
	/**设置城市  */
@JSONField(name="city")
	public void setCity(String value) {
		this.city = value;
	}
	/**获取国家  */
	
@JSONField(name="country")
	@Column(name = "country", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getCountry() {
		return this.country;
	}
	/**设置国家  */
@JSONField(name="country")
	public void setCountry(String value) {
		this.country = value;
	}
	/**获取用户头像  */
	
@JSONField(name="headimgurl")
	@Column(name = "headimgurl", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getHeadimgurl() {
		return this.headimgurl;
	}
	/**设置用户头像  */
@JSONField(name="headimgurl")
	public void setHeadimgurl(String value) {
		this.headimgurl = value;
	}
	/**获取用户特权信息  */
	
@JSONField(name="privilege")
	@Column(name = "privilege", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getPrivilege() {
		return this.privilege;
	}
	/**设置用户特权信息  */
@JSONField(name="privilege")
	public void setPrivilege(String value) {
		this.privilege = value;
	}
	/**获取微信标识  */
	
@JSONField(name="unionid")
	@Column(name = "unionid", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getUnionid() {
		return this.unionid;
	}
	/**设置微信标识  */
@JSONField(name="unionid")
	public void setUnionid(String value) {
		this.unionid = value;
	}
	/**获取真实姓名  */
	

	@Column(name = "realname", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getRealname() {
		return this.realname;
	}
	/**设置真实姓名  */

	public void setRealname(String value) {
		this.realname = value;
	}
	/**获取手机号  */
	
@JSONField(name="notshow3")
	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public String getMobile() {
		return this.mobile;
	}
	/**设置手机号  */
@JSONField(name="notshow3")
	public void setMobile(String value) {
		this.mobile = value;
	}
	/**获取电子邮件  */
	
@JSONField(name="notshow4")
	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getEmail() {
		return this.email;
	}
	/**设置电子邮件  */
@JSONField(name="notshow4")
	public void setEmail(String value) {
		this.email = value;
	}
	/**获取收货邮编  */
	
@JSONField(name="notshow5")
	@Column(name = "order_zip", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getOrderZip() {
		return this.orderZip;
	}
	/**设置收货邮编  */
@JSONField(name="notshow5")
	public void setOrderZip(String value) {
		this.orderZip = value;
	}
	/**获取收货地址  */
	
@JSONField(name="notshow6")
	@Column(name = "order_address", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getOrderAddress() {
		return this.orderAddress;
	}
	/**设置收货地址  */
@JSONField(name="notshow6")
	public void setOrderAddress(String value) {
		this.orderAddress = value;
	}
	/**获取是否订阅 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 {'0':'没有关注该公众号','1':'关注过了该公众号'}*/
	
@JSONField(name="subscribe")
	@Column(name = "subscribe", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSubscribe() {
		return this.subscribe;
	}
	/**设置是否订阅 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 {'0':'没有关注该公众号','1':'关注过了该公众号'}*/
@JSONField(name="subscribe")
	public void setSubscribe(Integer value) {
		this.subscribe = value;
	}
	/**获取关注时间 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 */
	
@JSONField(name="subscribe_time")
	@Column(name = "subscribe_time", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getSubscribeTime() {
		return this.subscribeTime;
	}
	/**设置关注时间 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 */
@JSONField(name="subscribe_time")
	public void setSubscribeTime(String value) {
		this.subscribeTime = value;
	}
	/**获取备注 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 */
	
@JSONField(name="remark")
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getRemark() {
		return this.remark;
	}
	/**设置备注 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 */
@JSONField(name="remark")
	public void setRemark(String value) {
		this.remark = value;
	}
	/**获取所在的分组ID 用户所在的分组ID */
	
@JSONField(name="groupid")
	@Column(name = "groupid", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getGroupid() {
		return this.groupid;
	}
	/**设置所在的分组ID 用户所在的分组ID */
@JSONField(name="groupid")
	public void setGroupid(Integer value) {
		this.groupid = value;
	}
	/**创建时间String*/
	private String gmtCreateString;
	/**获取创建时间String*/
	@Transient
	
	public String getGmtCreateString() {
		if(gmtCreateString==null && gmtCreate!=null)
			gmtCreateString=new SimpleDateFormat(DATE_FORMAT).format(gmtCreate);
		return gmtCreateString;
	}
	/**设置创建时间String*/
	public void setGmtCreateString(String value) {
		this.gmtCreateString=value;
	}
	/**所属卖家String*/
	private String sellerIdString;
	/**获取所属卖家String*/
	@Transient
	
	public String getSellerIdString() {
		return sellerIdString;
	}
	/**设置所属卖家String*/
	public void setSellerIdString(String value) {
		this.sellerIdString=value;
	}
	/**User - sellerIdUserObj*/
	private User sellerIdUserObj;
	/**获取 User - sellerIdUserObj*/
	@Transient
	
	public User getSellerIdUserObj() {
		return sellerIdUserObj;
	}
	/**设置 User - 所属卖家Obj*/
	public void setSellerIdUserObj(User value) {
		this.sellerIdUserObj=value;
	}
	/**所属卖家Stringid*/
	private String sellerIdStringid;
	/**获取所属卖家Stringid*/
	@Transient
	
	public String getSellerIdStringid() {
		return sellerIdStringid;
	}
	/**设置所属卖家Stringid*/
	public void setSellerIdStringid(String value) {
		this.sellerIdStringid=value;
	}
	/**平台用户String*/
	private String userIdString;
	/**获取平台用户String*/
	@Transient
	
	public String getUserIdString() {
		return userIdString;
	}
	/**设置平台用户String*/
	public void setUserIdString(String value) {
		this.userIdString=value;
	}
	/**User - userIdUserObj*/
	private User userIdUserObj;
	/**获取 User - userIdUserObj*/
	@Transient
	
	public User getUserIdUserObj() {
		return userIdUserObj;
	}
	/**设置 User - 平台用户Obj*/
	public void setUserIdUserObj(User value) {
		this.userIdUserObj=value;
	}
	/**平台用户Stringid*/
	private String userIdStringid;
	/**获取平台用户Stringid*/
	@Transient
	
	public String getUserIdStringid() {
		return userIdStringid;
	}
	/**设置平台用户Stringid*/
	public void setUserIdStringid(String value) {
		this.userIdStringid=value;
	}
	/**用户信息类型String*/
	private String statusufString;
	/**获取用户信息类型String*/
	@Transient
	
	public String getStatusufString() {
		return statusufString;
	}
	/**设置用户信息类型String*/
	public void setStatusufString(String value) {
		this.statusufString=value;
	}
	/**用户信息类型Map*/
	private Map<String, Object> statusufMap;
	/**获取用户信息类型Map*/
	@Transient
	
	public Map<String, Object> getStatusufMap() {
		return statusufMap;
	}
	/**设置用户信息类型Map*/
	public void setStatusufMap(Map<String, Object> value) {
		this.statusufMap=value;
	}
	/**性别String*/
	private String sexString;
	/**获取性别String*/
	@Transient
	
	public String getSexString() {
		return sexString;
	}
	/**设置性别String*/
	public void setSexString(String value) {
		this.sexString=value;
	}
	/**性别Map*/
	private Map<String, Object> sexMap;
	/**获取性别Map*/
	@Transient
	
	public Map<String, Object> getSexMap() {
		return sexMap;
	}
	/**设置性别Map*/
	public void setSexMap(Map<String, Object> value) {
		this.sexMap=value;
	}
	/**是否订阅String*/
	private String subscribeString;
	/**获取是否订阅String*/
	@Transient
	
	public String getSubscribeString() {
		return subscribeString;
	}
	/**设置是否订阅String*/
	public void setSubscribeString(String value) {
		this.subscribeString=value;
	}
	/**是否订阅Map*/
	private Map<String, Object> subscribeMap;
	/**获取是否订阅Map*/
	@Transient
	
	public Map<String, Object> getSubscribeMap() {
		return subscribeMap;
	}
	/**设置是否订阅Map*/
	public void setSubscribeMap(Map<String, Object> value) {
		this.subscribeMap=value;
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
		this.sellerIdUserObj=null; //User
		this.userIdUserObj=null; //User
		this.statusufMap=null;
		this.sexMap=null;
		this.subscribeMap=null;
	}
	/**备用对象1*/
	private Object obj1;
	/**
	 * 获取备用对象1
	 * @return Object
	 */
	@Transient
	
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
			.append(getSellerId())
			.append(getUserId())
			.append(getStatusuf())
			.append(getNickname())
			.append(getSex())
			.append(getProvince())
			.append(getCity())
			.append(getCountry())
			.append(getHeadimgurl())
			.append(getPrivilege())
			.append(getUnionid())
			.append(getRealname())
			.append(getMobile())
			.append(getEmail())
			.append(getOrderZip())
			.append(getOrderAddress())
			.append(getSubscribe())
			.append(getSubscribeTime())
			.append(getRemark())
			.append(getGroupid())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Wxouser == false) return false;
		if(this == obj) return true;
		Wxouser other = (Wxouser)obj;
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
			.append(",SellerId:",getSellerId())
			.append(",UserId:",getUserId())
			.append(",Statusuf:",getStatusuf())
			.append(",Nickname:",getNickname())
			.append(",Sex:",getSex())
			.append(",Province:",getProvince())
			.append(",City:",getCity())
			.append(",Country:",getCountry())
			.append(",Headimgurl:",getHeadimgurl())
			.append(",Privilege:",getPrivilege())
			.append(",Unionid:",getUnionid())
			.append(",Realname:",getRealname())
			.append(",Mobile:",getMobile())
			.append(",Email:",getEmail())
			.append(",OrderZip:",getOrderZip())
			.append(",OrderAddress:",getOrderAddress())
			.append(",Subscribe:",getSubscribe())
			.append(",SubscribeTime:",getSubscribeTime())
			.append(",Remark:",getRemark())
			.append(",Groupid:",getGroupid())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","sellerIdString","sellerIdUserObj","sellerIdStringid","userIdString","userIdUserObj","userIdStringid","statusufString","statusufMap","sexString","sexMap","subscribeString","subscribeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"id","gmtCreate","sellerId","userId","realname","myname","mynameid","gmtCreateString","sellerIdString","sellerIdUserObj","sellerIdStringid","userIdString","userIdUserObj","userIdStringid","statusufString","statusufMap","sexString","sexMap","subscribeString","subscribeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 微信用户
Wxouser wxouser = new Wxouser(
	sellerId , //Integer 所属卖家  实际上是bizId 
	userId , //Integer 平台用户   
	statusuf , //Integer 用户信息类型 default=1  {'0':'只有openid','1':'完全版'}
	nickname , //String 用户昵称   
	sex , //Integer 性别 default=0  {'1':'男','2':'女','0':'未知'}
	province , //String 省份  160x160 
	city , //String 城市 default=0  
	country , //String 国家 default=0  
	headimgurl , //String 用户头像   
	privilege , //String 用户特权信息   
	unionid , //String 微信标识   
	realname , //String 真实姓名   
	mobile , //String 手机号   
	email , //String 电子邮件   
	orderZip , //String 收货邮编   
	orderAddress , //String 收货地址   
	subscribe , //Integer 是否订阅 default=0 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 {'0':'没有关注该公众号','1':'关注过了该公众号'}
	subscribeTime , //String 关注时间  用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 
	remark , //String 备注  公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 
	groupid , //Integer 所在的分组ID  用户所在的分组ID 
	null
);
*/
}
