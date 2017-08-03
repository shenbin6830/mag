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
 * 操作日志
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "oplog")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Oplog extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "操作日志";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**序号 Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**ip String   */
	@Length(max=15)
	
	private String ip;
	/**操作者 Integer   */
	@Max(99999999999L)
	
	private Integer userId;
	/**操作类型 Integer default=0  map={'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}*/
	@Max(999L)
	
	private Integer itype = 0;
	/**操作表 String   */
	@Length(max=50)
	
	private String tbname;
	/**操作表id String   */
	@Length(max=64)
	
	private String tbid;
	/**原始操作 String   */
	@Length(max=200)
	
	private String uri;
	/**备注 String   */
	@Length(max=1000)
	
	private String cmemo;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Oplog _this){
		_this.gmtCreate=new Date();
		_this.itype=0;
	}
	public Oplog(){
		makedefault(this);
	}
	public Oplog(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param ip String ip   
	 * @param userId Integer 操作者   
	 * @param itype Integer 操作类型 default=0  {'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}
	 * @param tbname String 操作表   
	 * @param tbid String 操作表id   
	 * @param uri String 原始操作   
	 * @param cmemo String 备注   
	* @param notuse String 没用
	 */
	public Oplog(String ip ,Integer userId ,Integer itype ,String tbname ,String tbid ,String uri ,String cmemo ,String notuse) {
		super();
		this.ip = ip;
		this.userId = userId;
		this.itype = itype;
		this.tbname = tbname;
		this.tbid = tbid;
		this.uri = uri;
		this.cmemo = cmemo;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param ip String ip   
	 * @param userId Integer 操作者   
	 * @param itype Integer 操作类型 default=0  {'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}
	 * @param tbname String 操作表   
	 * @param tbid String 操作表id   
	 * @param uri String 原始操作   
	 * @param cmemo String 备注   
	* @param notuse String 没用
	 */
	public Oplog(Integer id ,String ip ,Integer userId ,Integer itype ,String tbname ,String tbid ,String uri ,String cmemo ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.ip = ip;
		this.userId = userId;
		this.itype = itype;
		this.tbname = tbname;
		this.tbid = tbid;
		this.uri = uri;
		this.cmemo = cmemo;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param gmtCreate Date 创建时间   
	 * @param ip String ip   
	 * @param userId Integer 操作者   
	 * @param itype Integer 操作类型 default=0  {'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}
	 * @param tbname String 操作表   
	 * @param tbid String 操作表id   
	 * @param uri String 原始操作   
	 * @param cmemo String 备注   
	* @param notuse String 没用
	 */
	public Oplog(Integer id ,Date gmtCreate ,String ip ,Integer userId ,Integer itype ,String tbname ,String tbid ,String uri ,String cmemo ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.ip = ip;
		this.userId = userId;
		this.itype = itype;
		this.tbname = tbname;
		this.tbid = tbid;
		this.uri = uri;
		this.cmemo = cmemo;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
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
	/**获取ip  */
	

	@Column(name = "ip", unique = false, nullable = true, insertable = true, updatable = true, length = 15)
	public String getIp() {
		return this.ip;
	}
	/**设置ip  */

	public void setIp(String value) {
		this.ip = value;
	}
	/**获取操作者  */
	

	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getUserId() {
		return this.userId;
	}
	/**设置操作者  */

	public void setUserId(Integer value) {
		this.userId = value;
	}
	/**获取操作类型  {'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}*/
	

	@Column(name = "itype", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getItype() {
		return this.itype;
	}
	/**设置操作类型  {'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}*/

	public void setItype(Integer value) {
		this.itype = value;
	}
	/**获取操作表  */
	

	@Column(name = "tbname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getTbname() {
		return this.tbname;
	}
	/**设置操作表  */

	public void setTbname(String value) {
		this.tbname = value;
	}
	/**获取操作表id  */
	

	@Column(name = "tbid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getTbid() {
		return this.tbid;
	}
	/**设置操作表id  */

	public void setTbid(String value) {
		this.tbid = value;
	}
	/**获取原始操作  */
	

	@Column(name = "uri", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getUri() {
		return this.uri;
	}
	/**设置原始操作  */

	public void setUri(String value) {
		this.uri = value;
	}
	/**获取备注  */
	

	@Column(name = "cmemo", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getCmemo() {
		return this.cmemo;
	}
	/**设置备注  */

	public void setCmemo(String value) {
		this.cmemo = value;
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
	/**操作者String*/
	private String userIdString;
	/**获取操作者String*/
	@Transient
	
	public String getUserIdString() {
		return userIdString;
	}
	/**设置操作者String*/
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
	/**设置 User - 操作者Obj*/
	public void setUserIdUserObj(User value) {
		this.userIdUserObj=value;
	}
	/**操作者Stringid*/
	private String userIdStringid;
	/**获取操作者Stringid*/
	@Transient
	
	public String getUserIdStringid() {
		return userIdStringid;
	}
	/**设置操作者Stringid*/
	public void setUserIdStringid(String value) {
		this.userIdStringid=value;
	}
	/**操作类型String*/
	private String itypeString;
	/**获取操作类型String*/
	@Transient
	
	public String getItypeString() {
		return itypeString;
	}
	/**设置操作类型String*/
	public void setItypeString(String value) {
		this.itypeString=value;
	}
	/**操作类型Map*/
	private Map<String, Object> itypeMap;
	/**获取操作类型Map*/
	@Transient
	
	public Map<String, Object> getItypeMap() {
		return itypeMap;
	}
	/**设置操作类型Map*/
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
		this.userIdUserObj=null; //User
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
			.append(getGmtCreate())
			.append(getIp())
			.append(getUserId())
			.append(getItype())
			.append(getTbname())
			.append(getTbid())
			.append(getUri())
			.append(getCmemo())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Oplog == false) return false;
		if(this == obj) return true;
		Oplog other = (Oplog)obj;
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
			.append(",Ip:",getIp())
			.append(",UserId:",getUserId())
			.append(",Itype:",getItype())
			.append(",Tbname:",getTbname())
			.append(",Tbid:",getTbid())
			.append(",Uri:",getUri())
			.append(",Cmemo:",getCmemo())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","userIdString","userIdUserObj","userIdStringid","itypeString","itypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","userIdString","userIdUserObj","userIdStringid","itypeString","itypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 操作日志
Oplog oplog = new Oplog(
	ip , //String ip   
	userId , //Integer 操作者   
	itype , //Integer 操作类型 default=0  {'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}
	tbname , //String 操作表   
	tbid , //String 操作表id   
	uri , //String 原始操作   
	cmemo , //String 备注   
	null
);
*/
}
