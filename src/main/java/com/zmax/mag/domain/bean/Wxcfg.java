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



 
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 微信配置
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wxcfg")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Wxcfg extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "微信配置";

	//date formats
	public static final String FORMAT_GMT_MODIFIED = DATE_FORMAT;
	
	//columns START
	/**所属用户 Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**修改时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;
	/**状态 Integer default=0  */
	@Max(999L)
	
	private Integer statusApplyPassRefuse = 0;
	/**名称 String   */
	@Length(max=20)
	
	private String cname;
	/**微信号 String   */
	@Length(max=20)
	
	private String wxnum;
	/**类型 Integer default=1  map={'1':'服务号','2':'企业号','3':'订阅号'}*/
	@Max(999L)
	
	private Integer itype = 1;
	/**appid String  从微信后台复制，关键字段 */
	@Length(max=50)
	
	private String appid;
	/**appsecret String  从微信后台复制 */
	@Length(max=50)
	
	private String appsecret;
	/**token String  从微信后台复制(php产生) */
	@Length(max=64)
	
	private String token;
	/**encodingaeskey String  从微信后台复制(php产生) */
	@Length(max=50)
	
	private String encodingaeskey;
	/**微信支付商户号 String  支付相关从微信后台复制(支付关键字段) */
	@Length(max=20)
	
	private String mchid;
	/**商户平台登录帐号 String  支付相关从微信后台复制 */
	@Length(max=50)
	
	private String partnerid;
	/**商户平台登录密码 String  支付相关从微信后台复制 */
	@Length(max=50)
	
	private String partnerkey;
	/**商户支付密钥 String  支付相关从微信后台复制(支付关键字段) */
	@Length(max=50)
	
	private String paykey;
	/**app的appid String  从微信后台复制，关键字段 */
	@Length(max=50)
	
	private String appAppid;
	/**app的微信支付商户号 String  支付相关从微信后台复制(支付关键字段) */
	@Length(max=20)
	
	private String appMchid;
	/**app的商户支付密钥 String  支付相关从微信后台复制(支付关键字段) */
	@Length(max=50)
	
	private String appPaykey;
	/**accesstoken String   */
	@Length(max=256)
	
	private String accesstoken;
	/**ticket String   */
	@Length(max=128)
	
	private String ticket;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Wxcfg _this){
		_this.statusApplyPassRefuse=0;
		_this.itype=1;
	}
	public Wxcfg(){
		makedefault(this);
	}
	public Wxcfg(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param statusApplyPassRefuse Integer 状态 default=0  
	 * @param cname String 名称   
	 * @param wxnum String 微信号   
	 * @param itype Integer 类型 default=1  {'1':'服务号','2':'企业号','3':'订阅号'}
	 * @param appid String appid  从微信后台复制，关键字段 
	 * @param appsecret String appsecret  从微信后台复制 
	 * @param token String token  从微信后台复制(php产生) 
	 * @param encodingaeskey String encodingaeskey  从微信后台复制(php产生) 
	 * @param mchid String 微信支付商户号  支付相关从微信后台复制(支付关键字段) 
	 * @param partnerid String 商户平台登录帐号  支付相关从微信后台复制 
	 * @param partnerkey String 商户平台登录密码  支付相关从微信后台复制 
	 * @param paykey String 商户支付密钥  支付相关从微信后台复制(支付关键字段) 
	 * @param appAppid String app的appid  从微信后台复制，关键字段 
	 * @param appMchid String app的微信支付商户号  支付相关从微信后台复制(支付关键字段) 
	 * @param appPaykey String app的商户支付密钥  支付相关从微信后台复制(支付关键字段) 
	 * @param accesstoken String accesstoken   
	 * @param ticket String ticket   
	* @param notuse String 没用
	 */
	public Wxcfg(Integer statusApplyPassRefuse ,String cname ,String wxnum ,Integer itype ,String appid ,String appsecret ,String token ,String encodingaeskey ,String mchid ,String partnerid ,String partnerkey ,String paykey ,String appAppid ,String appMchid ,String appPaykey ,String accesstoken ,String ticket ,String notuse) {
		super();
		this.statusApplyPassRefuse = statusApplyPassRefuse;
		this.cname = cname;
		this.wxnum = wxnum;
		this.itype = itype;
		this.appid = appid;
		this.appsecret = appsecret;
		this.token = token;
		this.encodingaeskey = encodingaeskey;
		this.mchid = mchid;
		this.partnerid = partnerid;
		this.partnerkey = partnerkey;
		this.paykey = paykey;
		this.appAppid = appAppid;
		this.appMchid = appMchid;
		this.appPaykey = appPaykey;
		this.accesstoken = accesstoken;
		this.ticket = ticket;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 所属用户   
	 * @param gmtModified Date 修改时间   
	 * @param statusApplyPassRefuse Integer 状态 default=0  
	 * @param cname String 名称   
	 * @param wxnum String 微信号   
	 * @param itype Integer 类型 default=1  {'1':'服务号','2':'企业号','3':'订阅号'}
	 * @param appid String appid  从微信后台复制，关键字段 
	 * @param appsecret String appsecret  从微信后台复制 
	 * @param token String token  从微信后台复制(php产生) 
	 * @param encodingaeskey String encodingaeskey  从微信后台复制(php产生) 
	 * @param mchid String 微信支付商户号  支付相关从微信后台复制(支付关键字段) 
	 * @param partnerid String 商户平台登录帐号  支付相关从微信后台复制 
	 * @param partnerkey String 商户平台登录密码  支付相关从微信后台复制 
	 * @param paykey String 商户支付密钥  支付相关从微信后台复制(支付关键字段) 
	 * @param appAppid String app的appid  从微信后台复制，关键字段 
	 * @param appMchid String app的微信支付商户号  支付相关从微信后台复制(支付关键字段) 
	 * @param appPaykey String app的商户支付密钥  支付相关从微信后台复制(支付关键字段) 
	 * @param accesstoken String accesstoken   
	 * @param ticket String ticket   
	* @param notuse String 没用
	 */
	public Wxcfg(Integer id ,Date gmtModified ,Integer statusApplyPassRefuse ,String cname ,String wxnum ,Integer itype ,String appid ,String appsecret ,String token ,String encodingaeskey ,String mchid ,String partnerid ,String partnerkey ,String paykey ,String appAppid ,String appMchid ,String appPaykey ,String accesstoken ,String ticket ,String notuse) {
		super();
		this.id = id;
		this.gmtModified = gmtModified;
		this.statusApplyPassRefuse = statusApplyPassRefuse;
		this.cname = cname;
		this.wxnum = wxnum;
		this.itype = itype;
		this.appid = appid;
		this.appsecret = appsecret;
		this.token = token;
		this.encodingaeskey = encodingaeskey;
		this.mchid = mchid;
		this.partnerid = partnerid;
		this.partnerkey = partnerkey;
		this.paykey = paykey;
		this.appAppid = appAppid;
		this.appMchid = appMchid;
		this.appPaykey = appPaykey;
		this.accesstoken = accesstoken;
		this.ticket = ticket;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 所属用户   
	 * @param gmtModified Date 修改时间   
	 * @param statusApplyPassRefuse Integer 状态 default=0  
	 * @param cname String 名称   
	 * @param wxnum String 微信号   
	 * @param itype Integer 类型 default=1  {'1':'服务号','2':'企业号','3':'订阅号'}
	 * @param appid String appid  从微信后台复制，关键字段 
	 * @param appsecret String appsecret  从微信后台复制 
	 * @param token String token  从微信后台复制(php产生) 
	 * @param encodingaeskey String encodingaeskey  从微信后台复制(php产生) 
	 * @param mchid String 微信支付商户号  支付相关从微信后台复制(支付关键字段) 
	 * @param partnerid String 商户平台登录帐号  支付相关从微信后台复制 
	 * @param partnerkey String 商户平台登录密码  支付相关从微信后台复制 
	 * @param paykey String 商户支付密钥  支付相关从微信后台复制(支付关键字段) 
	 * @param appAppid String app的appid  从微信后台复制，关键字段 
	 * @param appMchid String app的微信支付商户号  支付相关从微信后台复制(支付关键字段) 
	 * @param appPaykey String app的商户支付密钥  支付相关从微信后台复制(支付关键字段) 
	 * @param accesstoken String accesstoken   
	 * @param ticket String ticket   
	* @param notuse String 没用
	 */
	public Wxcfg(Integer id ,Date gmtModified ,Integer statusApplyPassRefuse ,String cname ,String wxnum ,Integer itype ,String appid ,String appsecret ,String token ,String encodingaeskey ,String mchid ,String partnerid ,String partnerkey ,String paykey ,String appAppid ,String appMchid ,String appPaykey ,String accesstoken ,String ticket ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtModified = gmtModified;
		this.statusApplyPassRefuse = statusApplyPassRefuse;
		this.cname = cname;
		this.wxnum = wxnum;
		this.itype = itype;
		this.appid = appid;
		this.appsecret = appsecret;
		this.token = token;
		this.encodingaeskey = encodingaeskey;
		this.mchid = mchid;
		this.partnerid = partnerid;
		this.partnerkey = partnerkey;
		this.paykey = paykey;
		this.appAppid = appAppid;
		this.appMchid = appMchid;
		this.appPaykey = appPaykey;
		this.accesstoken = accesstoken;
		this.ticket = ticket;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+id+" "+cname+" ":myname;
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
		mynameid=(mynameid==null)?"["+id+"]"+id+" "+cname+" ":mynameid;
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
	

	@Id 
	@Column(name = "id",  unique = false, nullable = false, insertable = true, updatable = true, length = 11)
	public Integer getId() {
		return this.id;
	}
	
	/**获取修改时间  */
	

	@Column(name = "gmt_modified", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtModified() {
		return this.gmtModified;
	}
	/**设置修改时间  */

	public void setGmtModified(Date value) {
		this.gmtModified = value;
	}
	/**获取状态  */
	

	@Column(name = "status_apply_pass_refuse", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatusApplyPassRefuse() {
		return this.statusApplyPassRefuse;
	}
	/**设置状态  */

	public void setStatusApplyPassRefuse(Integer value) {
		this.statusApplyPassRefuse = value;
	}
	/**获取名称  */
	

	@Column(name = "cname", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getCname() {
		return this.cname;
	}
	/**设置名称  */

	public void setCname(String value) {
		this.cname = value;
	}
	/**获取微信号  */
	

	@Column(name = "wxnum", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getWxnum() {
		return this.wxnum;
	}
	/**设置微信号  */

	public void setWxnum(String value) {
		this.wxnum = value;
	}
	/**获取类型  {'1':'服务号','2':'企业号','3':'订阅号'}*/
	

	@Column(name = "itype", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getItype() {
		return this.itype;
	}
	/**设置类型  {'1':'服务号','2':'企业号','3':'订阅号'}*/

	public void setItype(Integer value) {
		this.itype = value;
	}
	/**获取appid 从微信后台复制，关键字段 */
	

	@Column(name = "appid", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getAppid() {
		return this.appid;
	}
	/**设置appid 从微信后台复制，关键字段 */

	public void setAppid(String value) {
		this.appid = value;
	}
	/**获取appsecret 从微信后台复制 */
	

	@Column(name = "appsecret", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getAppsecret() {
		return this.appsecret;
	}
	/**设置appsecret 从微信后台复制 */

	public void setAppsecret(String value) {
		this.appsecret = value;
	}
	/**获取token 从微信后台复制(php产生) */
	

	@Column(name = "token", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getToken() {
		return this.token;
	}
	/**设置token 从微信后台复制(php产生) */

	public void setToken(String value) {
		this.token = value;
	}
	/**获取encodingaeskey 从微信后台复制(php产生) */
	

	@Column(name = "encodingaeskey", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getEncodingaeskey() {
		return this.encodingaeskey;
	}
	/**设置encodingaeskey 从微信后台复制(php产生) */

	public void setEncodingaeskey(String value) {
		this.encodingaeskey = value;
	}
	/**获取微信支付商户号 支付相关从微信后台复制(支付关键字段) */
	

	@Column(name = "mchid", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getMchid() {
		return this.mchid;
	}
	/**设置微信支付商户号 支付相关从微信后台复制(支付关键字段) */

	public void setMchid(String value) {
		this.mchid = value;
	}
	/**获取商户平台登录帐号 支付相关从微信后台复制 */
	

	@Column(name = "partnerid", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getPartnerid() {
		return this.partnerid;
	}
	/**设置商户平台登录帐号 支付相关从微信后台复制 */

	public void setPartnerid(String value) {
		this.partnerid = value;
	}
	/**获取商户平台登录密码 支付相关从微信后台复制 */
	

	@Column(name = "partnerkey", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getPartnerkey() {
		return this.partnerkey;
	}
	/**设置商户平台登录密码 支付相关从微信后台复制 */

	public void setPartnerkey(String value) {
		this.partnerkey = value;
	}
	/**获取商户支付密钥 支付相关从微信后台复制(支付关键字段) */
	

	@Column(name = "paykey", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getPaykey() {
		return this.paykey;
	}
	/**设置商户支付密钥 支付相关从微信后台复制(支付关键字段) */

	public void setPaykey(String value) {
		this.paykey = value;
	}
	/**获取app的appid 从微信后台复制，关键字段 */
	

	@Column(name = "app_appid", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getAppAppid() {
		return this.appAppid;
	}
	/**设置app的appid 从微信后台复制，关键字段 */

	public void setAppAppid(String value) {
		this.appAppid = value;
	}
	/**获取app的微信支付商户号 支付相关从微信后台复制(支付关键字段) */
	

	@Column(name = "app_mchid", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getAppMchid() {
		return this.appMchid;
	}
	/**设置app的微信支付商户号 支付相关从微信后台复制(支付关键字段) */

	public void setAppMchid(String value) {
		this.appMchid = value;
	}
	/**获取app的商户支付密钥 支付相关从微信后台复制(支付关键字段) */
	

	@Column(name = "app_paykey", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getAppPaykey() {
		return this.appPaykey;
	}
	/**设置app的商户支付密钥 支付相关从微信后台复制(支付关键字段) */

	public void setAppPaykey(String value) {
		this.appPaykey = value;
	}
	/**获取accesstoken  */
	

	@Column(name = "accesstoken", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getAccesstoken() {
		return this.accesstoken;
	}
	/**设置accesstoken  */

	public void setAccesstoken(String value) {
		this.accesstoken = value;
	}
	/**获取ticket  */
	

	@Column(name = "ticket", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public String getTicket() {
		return this.ticket;
	}
	/**设置ticket  */

	public void setTicket(String value) {
		this.ticket = value;
	}
	/**修改时间String*/
	private String gmtModifiedString;
	/**获取修改时间String*/
	@Transient
	
	public String getGmtModifiedString() {
		if(gmtModifiedString==null && gmtModified!=null)
			gmtModifiedString=new SimpleDateFormat(DATE_FORMAT).format(gmtModified);
		return gmtModifiedString;
	}
	/**设置修改时间String*/
	public void setGmtModifiedString(String value) {
		this.gmtModifiedString=value;
	}
	/**状态String*/
	private String statusApplyPassRefuseString;
	/**获取状态String*/
	@Transient
	
	public String getStatusApplyPassRefuseString() {
		return statusApplyPassRefuseString;
	}
	/**设置状态String*/
	public void setStatusApplyPassRefuseString(String value) {
		this.statusApplyPassRefuseString=value;
	}
	/**状态Map*/
	private Map<String, Object> statusApplyPassRefuseMap;
	/**获取状态Map*/
	@Transient
	
	public Map<String, Object> getStatusApplyPassRefuseMap() {
		return statusApplyPassRefuseMap;
	}
	/**设置状态Map*/
	public void setStatusApplyPassRefuseMap(Map<String, Object> value) {
		this.statusApplyPassRefuseMap=value;
	}
	/**类型String*/
	private String itypeString;
	/**获取类型String*/
	@Transient
	
	public String getItypeString() {
		return itypeString;
	}
	/**设置类型String*/
	public void setItypeString(String value) {
		this.itypeString=value;
	}
	/**类型Map*/
	private Map<String, Object> itypeMap;
	/**获取类型Map*/
	@Transient
	
	public Map<String, Object> getItypeMap() {
		return itypeMap;
	}
	/**设置类型Map*/
	public void setItypeMap(Map<String, Object> value) {
		this.itypeMap=value;
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
		this.statusApplyPassRefuseMap=null;
		this.itypeMap=null;
	}
	/**
	 * 非常严格的检查对象，不能空不能0
	 * @param checkId 是否检查id
	 * @return
	 */
	public boolean errCheckObjHard(boolean checkId){
		if (errCheckObj(checkId))
			return true;
		if(checkId){
			if(0==this.id)
				return true;
		}
		return false;
	}
	/**
	 * 较松的的检查对象，不能空，可以是0
	 * @param checkId 是否检查id
	 * @return
	 */
	public boolean errCheckObj(boolean checkId){
		if(checkId){
			if(this.id==null)
				return true;
		}
		return false;
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
			.append(getGmtModified())
			.append(getStatusApplyPassRefuse())
			.append(getCname())
			.append(getWxnum())
			.append(getItype())
			.append(getAppid())
			.append(getAppsecret())
			.append(getToken())
			.append(getEncodingaeskey())
			.append(getMchid())
			.append(getPartnerid())
			.append(getPartnerkey())
			.append(getPaykey())
			.append(getAppAppid())
			.append(getAppMchid())
			.append(getAppPaykey())
			.append(getAccesstoken())
			.append(getTicket())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Wxcfg == false) return false;
		if(this == obj) return true;
		Wxcfg other = (Wxcfg)obj;
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
			.append(",GmtModified:",getGmtModified())
			.append(",StatusApplyPassRefuse:",getStatusApplyPassRefuse())
			.append(",Cname:",getCname())
			.append(",Wxnum:",getWxnum())
			.append(",Itype:",getItype())
			.append(",Appid:",getAppid())
			.append(",Appsecret:",getAppsecret())
			.append(",Token:",getToken())
			.append(",Encodingaeskey:",getEncodingaeskey())
			.append(",Mchid:",getMchid())
			.append(",Partnerid:",getPartnerid())
			.append(",Partnerkey:",getPartnerkey())
			.append(",Paykey:",getPaykey())
			.append(",AppAppid:",getAppAppid())
			.append(",AppMchid:",getAppMchid())
			.append(",AppPaykey:",getAppPaykey())
			.append(",Accesstoken:",getAccesstoken())
			.append(",Ticket:",getTicket())
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
		String[] excludesProperties={"myname","mynameid","gmtModifiedString","statusApplyPassRefuseString","statusApplyPassRefuseMap","itypeString","itypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtModifiedString","statusApplyPassRefuseString","statusApplyPassRefuseMap","itypeString","itypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 微信配置
Wxcfg wxcfg = new Wxcfg(
	statusApplyPassRefuse , //Integer 状态 default=0  
	cname , //String 名称   
	wxnum , //String 微信号   
	itype , //Integer 类型 default=1  {'1':'服务号','2':'企业号','3':'订阅号'}
	appid , //String appid  从微信后台复制，关键字段 
	appsecret , //String appsecret  从微信后台复制 
	token , //String token  从微信后台复制(php产生) 
	encodingaeskey , //String encodingaeskey  从微信后台复制(php产生) 
	mchid , //String 微信支付商户号  支付相关从微信后台复制(支付关键字段) 
	partnerid , //String 商户平台登录帐号  支付相关从微信后台复制 
	partnerkey , //String 商户平台登录密码  支付相关从微信后台复制 
	paykey , //String 商户支付密钥  支付相关从微信后台复制(支付关键字段) 
	appAppid , //String app的appid  从微信后台复制，关键字段 
	appMchid , //String app的微信支付商户号  支付相关从微信后台复制(支付关键字段) 
	appPaykey , //String app的商户支付密钥  支付相关从微信后台复制(支付关键字段) 
	accesstoken , //String accesstoken   
	ticket , //String ticket   
	null
);
*/
}
