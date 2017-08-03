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





/**
 * 微信自定义菜单
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wxmenu")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Wxmenu extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "微信自定义菜单";

	//date formats
	
	//columns START
	/**序号ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**卖家 Integer   */
	@Max(99999999999L)
	
	private Integer userId;
	/**菜单类型 String   map={'menu':'菜单','button':'一级菜单','sub_button':'二级菜单'}*/
	@Length(max=10)
	
	private String mtype;
	/**响应动作类型 String  菜单的响应动作类型 map={'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}*/
	@NotBlank @Length(max=30)
	
	private String type;
	/**菜单标题 String  菜单标题，不超过16个字节，子菜单不超过40个字节 */
	@Length(max=16)
	
	private String name;
	/**菜单KEY值 String  click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 */
	@Length(max=128)
	
	private String key1;
	/**网页链接 String  view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 */
	@Length(max=256)
	
	private String url;
	/**合法media_id String  调用新增永久素材接口返回的合法media_id */
	@Length(max=64)
	
	private String mediaId;
	/**父ID Integer default=0  */
	@Max(99999999999L)
	
	private Integer parentid = 0;
	/**孩子数量 Integer default=0  */
	@Max(99999999999L)
	
	private Integer childrennum = 0;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Wxmenu _this){
		_this.parentid=0;
		_this.childrennum=0;
	}
	public Wxmenu(){
		makedefault(this);
	}
	public Wxmenu(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param userId Integer 卖家   
	 * @param mtype String 菜单类型   {'menu':'菜单','button':'一级菜单','sub_button':'二级菜单'}
	 * @param type String 响应动作类型  菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}
	 * @param name String 菜单标题  菜单标题，不超过16个字节，子菜单不超过40个字节 
	 * @param key1 String 菜单KEY值  click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 
	 * @param url String 网页链接  view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 
	 * @param mediaId String 合法media_id  调用新增永久素材接口返回的合法media_id 
	 * @param parentid Integer 父ID default=0  
	 * @param childrennum Integer 孩子数量 default=0  
	* @param notuse String 没用
	 */
	public Wxmenu(Integer userId ,String mtype ,String type ,String name ,String key1 ,String url ,String mediaId ,Integer parentid ,Integer childrennum ,String notuse) {
		super();
		this.userId = userId;
		this.mtype = mtype;
		this.type = type;
		this.name = name;
		this.key1 = key1;
		this.url = url;
		this.mediaId = mediaId;
		this.parentid = parentid;
		this.childrennum = childrennum;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param userId Integer 卖家   
	 * @param mtype String 菜单类型   {'menu':'菜单','button':'一级菜单','sub_button':'二级菜单'}
	 * @param type String 响应动作类型  菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}
	 * @param name String 菜单标题  菜单标题，不超过16个字节，子菜单不超过40个字节 
	 * @param key1 String 菜单KEY值  click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 
	 * @param url String 网页链接  view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 
	 * @param mediaId String 合法media_id  调用新增永久素材接口返回的合法media_id 
	 * @param parentid Integer 父ID default=0  
	 * @param childrennum Integer 孩子数量 default=0  
	* @param notuse String 没用
	 */
	public Wxmenu(Integer id ,Integer userId ,String mtype ,String type ,String name ,String key1 ,String url ,String mediaId ,Integer parentid ,Integer childrennum ,String notuse) {
		super();
		this.id = id;
		this.userId = userId;
		this.mtype = mtype;
		this.type = type;
		this.name = name;
		this.key1 = key1;
		this.url = url;
		this.mediaId = mediaId;
		this.parentid = parentid;
		this.childrennum = childrennum;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param userId Integer 卖家   
	 * @param mtype String 菜单类型   {'menu':'菜单','button':'一级菜单','sub_button':'二级菜单'}
	 * @param type String 响应动作类型  菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}
	 * @param name String 菜单标题  菜单标题，不超过16个字节，子菜单不超过40个字节 
	 * @param key1 String 菜单KEY值  click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 
	 * @param url String 网页链接  view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 
	 * @param mediaId String 合法media_id  调用新增永久素材接口返回的合法media_id 
	 * @param parentid Integer 父ID default=0  
	 * @param childrennum Integer 孩子数量 default=0  
	* @param notuse String 没用
	 */
	public Wxmenu(Integer id ,Integer userId ,String mtype ,String type ,String name ,String key1 ,String url ,String mediaId ,Integer parentid ,Integer childrennum ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.userId = userId;
		this.mtype = mtype;
		this.type = type;
		this.name = name;
		this.key1 = key1;
		this.url = url;
		this.mediaId = mediaId;
		this.parentid = parentid;
		this.childrennum = childrennum;
	}
	/**
	 * 构造之不能为空的参数
	 * @param type String 响应动作类型 
	* @param notuse String 没用
	 */
	public Wxmenu(Integer _id,String _type,String notuse){
		this.type=_type;
		makedefault(this);
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+name+" ":myname;
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
		mynameid=(mynameid==null)?"["+id+"]"+name+" ":mynameid;
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
	
	/**获取卖家  */
	

	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getUserId() {
		return this.userId;
	}
	/**设置卖家  */

	public void setUserId(Integer value) {
		this.userId = value;
	}
	/**获取菜单类型  {'menu':'菜单','button':'一级菜单','sub_button':'二级菜单'}*/
	

	@Column(name = "mtype", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getMtype() {
		return this.mtype;
	}
	/**设置菜单类型  {'menu':'菜单','button':'一级菜单','sub_button':'二级菜单'}*/

	public void setMtype(String value) {
		this.mtype = value;
	}
	/**获取响应动作类型 菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}*/
	

	@Column(name = "type", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getType() {
		return this.type;
	}
	/**设置响应动作类型 菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}*/

	public void setType(String value) {
		this.type = value;
	}
	/**获取菜单标题 菜单标题，不超过16个字节，子菜单不超过40个字节 */
	

	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getName() {
		return this.name;
	}
	/**设置菜单标题 菜单标题，不超过16个字节，子菜单不超过40个字节 */

	public void setName(String value) {
		this.name = value;
	}
	/**获取菜单KEY值 click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 */
	

	@Column(name = "key1", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public String getKey1() {
		return this.key1;
	}
	/**设置菜单KEY值 click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 */

	public void setKey1(String value) {
		this.key1 = value;
	}
	/**获取网页链接 view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 */
	

	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getUrl() {
		return this.url;
	}
	/**设置网页链接 view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 */

	public void setUrl(String value) {
		this.url = value;
	}
	/**获取合法media_id 调用新增永久素材接口返回的合法media_id */
	

	@Column(name = "media_id", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getMediaId() {
		return this.mediaId;
	}
	/**设置合法media_id 调用新增永久素材接口返回的合法media_id */

	public void setMediaId(String value) {
		this.mediaId = value;
	}
	/**获取父ID  */
	

	@Column(name = "parentid", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getParentid() {
		return this.parentid;
	}
	/**设置父ID  */

	public void setParentid(Integer value) {
		this.parentid = value;
	}
	/**获取孩子数量  */
	

	@Column(name = "childrennum", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getChildrennum() {
		return this.childrennum;
	}
	/**设置孩子数量  */

	public void setChildrennum(Integer value) {
		this.childrennum = value;
	}
	/**卖家String*/
	private String userIdString;
	/**获取卖家String*/
	@Transient
	
	public String getUserIdString() {
		return userIdString;
	}
	/**设置卖家String*/
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
	/**设置 User - 卖家Obj*/
	public void setUserIdUserObj(User value) {
		this.userIdUserObj=value;
	}
	/**卖家Stringid*/
	private String userIdStringid;
	/**获取卖家Stringid*/
	@Transient
	
	public String getUserIdStringid() {
		return userIdStringid;
	}
	/**设置卖家Stringid*/
	public void setUserIdStringid(String value) {
		this.userIdStringid=value;
	}
	/**菜单类型String*/
	private String mtypeString;
	/**获取菜单类型String*/
	@Transient
	
	public String getMtypeString() {
		return mtypeString;
	}
	/**设置菜单类型String*/
	public void setMtypeString(String value) {
		this.mtypeString=value;
	}
	/**菜单类型Map*/
	private Map<String, Object> mtypeMap;
	/**获取菜单类型Map*/
	@Transient
	
	public Map<String, Object> getMtypeMap() {
		return mtypeMap;
	}
	/**设置菜单类型Map*/
	public void setMtypeMap(Map<String, Object> value) {
		this.mtypeMap=value;
	}
	/**响应动作类型String*/
	private String typeString;
	/**获取响应动作类型String*/
	@Transient
	
	public String getTypeString() {
		return typeString;
	}
	/**设置响应动作类型String*/
	public void setTypeString(String value) {
		this.typeString=value;
	}
	/**响应动作类型Map*/
	private Map<String, Object> typeMap;
	/**获取响应动作类型Map*/
	@Transient
	
	public Map<String, Object> getTypeMap() {
		return typeMap;
	}
	/**设置响应动作类型Map*/
	public void setTypeMap(Map<String, Object> value) {
		this.typeMap=value;
	}
	/**父IDString*/
	private String parentidString;
	/**获取父IDString*/
	@Transient
	
	public String getParentidString() {
		return parentidString;
	}
	/**设置父IDString*/
	public void setParentidString(String value) {
		this.parentidString=value;
	}
	/**父IDStringid*/
	private String parentidStringid;
	/**获取父IDString*/
	@Transient
	
	public String getParentidStringid() {
		return parentidStringid;
	}
	/**设置父IDString*/
	public void setParentidStringid(String value) {
		this.parentidStringid=value;
	}
	/**孩子List*/
	private List<Wxmenu> children=new ArrayList<Wxmenu>();
	/**获取孩子List*/
	@Transient
	
	public List<Wxmenu> getChildren() {
		return children;
	}
	/**设置孩子List*/
	public void setChildren(List<Wxmenu> children) {
		this.children = children;
	}
	/**父母*/
	private Wxmenu parent=null;
	/**获取父母对象Wxmenu*/
	@Transient
	
	public Wxmenu getParent() {
		return parent;
	}
	/**设置父母对象Wxmenu*/
	public void setParent(Wxmenu parent) {
		this.parent = parent;
	}
	/**父母List*/
	private List<Wxmenu> parentList;
	/**获取父母List*/
	@Transient
	
	public List<Wxmenu> getParentList() {
		return parentList;
	}
	/**设置父母List*/
	public void setParentList(List<Wxmenu> parentList) {
		this.parentList = parentList;
	}
	/**TreeGrid用，形状open,close,null 关闭=close*/
	private String state="closed";
	/**TreeGrid用，形状open,close,null 关闭=close*/
	@Transient
	
	public String getState() {
		return state;
	}
	/**TreeGrid用，形状open,close,null 关闭=close*/
	public void setState(String state) {
		this.state = state;
	}
	/**TreeGrid用，已经被勾选上*/
	private boolean checked=false;
	/**TreeGrid用，已经被勾选上*/
	@Transient
	
	public boolean getChecked() {
		return checked;
	}
	/**TreeGrid用，已经被勾选上*/
	public void setChecked(boolean checked) {
		this.checked = checked;
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
		this.userIdUserObj=null; //User
		this.mtypeMap=null;
		this.typeMap=null;
		this.children=null;
		this.parent=null;
		this.parentList=null;
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
		if("".equals(this.type))
			return true;
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
		if(this.type==null)
			return true;
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
			.append(getUserId())
			.append(getMtype())
			.append(getType())
			.append(getName())
			.append(getKey1())
			.append(getUrl())
			.append(getMediaId())
			.append(getParentid())
			.append(getChildrennum())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Wxmenu == false) return false;
		if(this == obj) return true;
		Wxmenu other = (Wxmenu)obj;
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
			.append(",UserId:",getUserId())
			.append(",Mtype:",getMtype())
			.append(",Type:",getType())
			.append(",Name:",getName())
			.append(",Key1:",getKey1())
			.append(",Url:",getUrl())
			.append(",MediaId:",getMediaId())
			.append(",Parentid:",getParentid())
			.append(",Childrennum:",getChildrennum())
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
		String[] excludesProperties={"myname","mynameid","userIdString","userIdUserObj","userIdStringid","mtypeString","mtypeMap","typeString","typeMap","parentidString","parentidStringid","children","parent","parentList","state","checked","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"id","userId","mtype","parentid","childrennum","myname","mynameid","userIdString","userIdUserObj","userIdStringid","mtypeString","mtypeMap","typeString","typeMap","parentidString","parentidStringid","children","parent","parentList","state","checked","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 微信自定义菜单
Wxmenu wxmenu = new Wxmenu(
	userId , //Integer 卖家   
	mtype , //String 菜单类型   {'menu':'菜单','button':'一级菜单','sub_button':'二级菜单'}
	type , //String 响应动作类型  菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}
	name , //String 菜单标题  菜单标题，不超过16个字节，子菜单不超过40个字节 
	key1 , //String 菜单KEY值  click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 
	url , //String 网页链接  view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 
	mediaId , //String 合法media_id  调用新增永久素材接口返回的合法media_id 
	parentid , //Integer 父ID default=0  
	childrennum , //Integer 孩子数量 default=0  
	null
);
*/
}
