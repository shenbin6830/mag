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
 * 权限之表设定
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "permittable")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Permittable extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "权限之表设定";

	//date formats
	
	//columns START
	/**序号 Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**角色 Integer default=0 参看user map={'0':'超管','1':'一般管理员','5':'会员'}*/
	@Max(999L)
	
	private Integer roleId = 0;
	/**表名 String   */
	@Length(max=50)
	
	private String tbname;
	/**表说明 String   */
	@Length(max=50)
	
	private String tbalias;
	/**表权限 Integer default=0 如果设置成为“无”，后面不用设置了，全部无权；如果设成为“全部”，后面也基本不用设置，角色对此表有着和管理员差不多的权限，除了增HQL和修改检查脚本 map={'0':'无','9':'部分','91':'全部'}*/
	@Max(999L)
	
	private Integer pall = 0;
	/**按钮增 Integer default=0 如果设成为“全部”，此操作所有字段全部可见 map={'0':'无','9':'部分','91':'全部'}*/
	@Max(999L)
	
	private Integer btnnew = 0;
	/**按钮改 Integer default=0 如果设成为“全部”，此操作所有字段全部可见 map={'0':'无','9':'部分','91':'全部'}*/
	@Max(999L)
	
	private Integer btnedit = 0;
	/**按钮删 Integer default=0 “部分”与“全部”没有区别 map={'0':'无','9':'部分','91':'全部'}*/
	@Max(999L)
	
	private Integer btndel = 0;
	/**按钮查 Integer default=0 如果设成为“全部”，此列表和查询操作所有字段全部可见 map={'0':'无','9':'部分','91':'全部'}*/
	@Max(999L)
	
	private Integer btnshow = 0;
	/**查询增加的hql String  例：\"userId=\"+sessionobj.id */
	@Length(max=200)
	
	private String queryaddhql;
	/**修改检查脚本 String  例：dbobj.userId==sessionobj.id */
	@Length(max=200)
	
	private String modigvy;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Permittable _this){
		_this.roleId=0;
		_this.pall=0;
		_this.btnnew=0;
		_this.btnedit=0;
		_this.btndel=0;
		_this.btnshow=0;
	}
	public Permittable(){
		makedefault(this);
	}
	public Permittable(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param roleId Integer 角色 default=0 参看user {'0':'超管','1':'一般管理员','5':'会员'}
	 * @param tbname String 表名   
	 * @param tbalias String 表说明   
	 * @param pall Integer 表权限 default=0 如果设置成为“无”，后面不用设置了，全部无权；如果设成为“全部”，后面也基本不用设置，角色对此表有着和管理员差不多的权限，除了增HQL和修改检查脚本 {'0':'无','9':'部分','91':'全部'}
	 * @param btnnew Integer 按钮增 default=0 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	 * @param btnedit Integer 按钮改 default=0 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	 * @param btndel Integer 按钮删 default=0 “部分”与“全部”没有区别 {'0':'无','9':'部分','91':'全部'}
	 * @param btnshow Integer 按钮查 default=0 如果设成为“全部”，此列表和查询操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	 * @param queryaddhql String 查询增加的hql  例：\"userId=\"+sessionobj.id 
	 * @param modigvy String 修改检查脚本  例：dbobj.userId==sessionobj.id 
	* @param notuse String 没用
	 */
	public Permittable(Integer roleId ,String tbname ,String tbalias ,Integer pall ,Integer btnnew ,Integer btnedit ,Integer btndel ,Integer btnshow ,String queryaddhql ,String modigvy ,String notuse) {
		super();
		this.roleId = roleId;
		this.tbname = tbname;
		this.tbalias = tbalias;
		this.pall = pall;
		this.btnnew = btnnew;
		this.btnedit = btnedit;
		this.btndel = btndel;
		this.btnshow = btnshow;
		this.queryaddhql = queryaddhql;
		this.modigvy = modigvy;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param roleId Integer 角色 default=0 参看user {'0':'超管','1':'一般管理员','5':'会员'}
	 * @param tbname String 表名   
	 * @param tbalias String 表说明   
	 * @param pall Integer 表权限 default=0 如果设置成为“无”，后面不用设置了，全部无权；如果设成为“全部”，后面也基本不用设置，角色对此表有着和管理员差不多的权限，除了增HQL和修改检查脚本 {'0':'无','9':'部分','91':'全部'}
	 * @param btnnew Integer 按钮增 default=0 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	 * @param btnedit Integer 按钮改 default=0 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	 * @param btndel Integer 按钮删 default=0 “部分”与“全部”没有区别 {'0':'无','9':'部分','91':'全部'}
	 * @param btnshow Integer 按钮查 default=0 如果设成为“全部”，此列表和查询操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	 * @param queryaddhql String 查询增加的hql  例：\"userId=\"+sessionobj.id 
	 * @param modigvy String 修改检查脚本  例：dbobj.userId==sessionobj.id 
	* @param notuse String 没用
	 */
	public Permittable(Integer id ,Integer roleId ,String tbname ,String tbalias ,Integer pall ,Integer btnnew ,Integer btnedit ,Integer btndel ,Integer btnshow ,String queryaddhql ,String modigvy ,String notuse) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.tbname = tbname;
		this.tbalias = tbalias;
		this.pall = pall;
		this.btnnew = btnnew;
		this.btnedit = btnedit;
		this.btndel = btndel;
		this.btnshow = btnshow;
		this.queryaddhql = queryaddhql;
		this.modigvy = modigvy;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param roleId Integer 角色 default=0 参看user {'0':'超管','1':'一般管理员','5':'会员'}
	 * @param tbname String 表名   
	 * @param tbalias String 表说明   
	 * @param pall Integer 表权限 default=0 如果设置成为“无”，后面不用设置了，全部无权；如果设成为“全部”，后面也基本不用设置，角色对此表有着和管理员差不多的权限，除了增HQL和修改检查脚本 {'0':'无','9':'部分','91':'全部'}
	 * @param btnnew Integer 按钮增 default=0 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	 * @param btnedit Integer 按钮改 default=0 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	 * @param btndel Integer 按钮删 default=0 “部分”与“全部”没有区别 {'0':'无','9':'部分','91':'全部'}
	 * @param btnshow Integer 按钮查 default=0 如果设成为“全部”，此列表和查询操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	 * @param queryaddhql String 查询增加的hql  例：\"userId=\"+sessionobj.id 
	 * @param modigvy String 修改检查脚本  例：dbobj.userId==sessionobj.id 
	* @param notuse String 没用
	 */
	public Permittable(Integer id ,Integer roleId ,String tbname ,String tbalias ,Integer pall ,Integer btnnew ,Integer btnedit ,Integer btndel ,Integer btnshow ,String queryaddhql ,String modigvy ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.tbname = tbname;
		this.tbalias = tbalias;
		this.pall = pall;
		this.btnnew = btnnew;
		this.btnedit = btnedit;
		this.btndel = btndel;
		this.btnshow = btnshow;
		this.queryaddhql = queryaddhql;
		this.modigvy = modigvy;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+tbname+" ":myname;
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
		mynameid=(mynameid==null)?"["+id+"]"+tbname+" ":mynameid;
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
	
	/**获取角色 参看user {'0':'超管','1':'一般管理员','5':'会员'}*/
	

	@Column(name = "role_id", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getRoleId() {
		return this.roleId;
	}
	/**设置角色 参看user {'0':'超管','1':'一般管理员','5':'会员'}*/

	public void setRoleId(Integer value) {
		this.roleId = value;
	}
	/**获取表名  */
	

	@Column(name = "tbname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getTbname() {
		return this.tbname;
	}
	/**设置表名  */

	public void setTbname(String value) {
		this.tbname = value;
	}
	/**获取表说明  */
	

	@Column(name = "tbalias", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getTbalias() {
		return this.tbalias;
	}
	/**设置表说明  */

	public void setTbalias(String value) {
		this.tbalias = value;
	}
	/**获取表权限 如果设置成为“无”，后面不用设置了，全部无权；如果设成为“全部”，后面也基本不用设置，角色对此表有着和管理员差不多的权限，除了增HQL和修改检查脚本 {'0':'无','9':'部分','91':'全部'}*/
	

	@Column(name = "pall", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getPall() {
		return this.pall;
	}
	/**设置表权限 如果设置成为“无”，后面不用设置了，全部无权；如果设成为“全部”，后面也基本不用设置，角色对此表有着和管理员差不多的权限，除了增HQL和修改检查脚本 {'0':'无','9':'部分','91':'全部'}*/

	public void setPall(Integer value) {
		this.pall = value;
	}
	/**获取按钮增 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}*/
	

	@Column(name = "btnnew", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getBtnnew() {
		return this.btnnew;
	}
	/**设置按钮增 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}*/

	public void setBtnnew(Integer value) {
		this.btnnew = value;
	}
	/**获取按钮改 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}*/
	

	@Column(name = "btnedit", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getBtnedit() {
		return this.btnedit;
	}
	/**设置按钮改 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}*/

	public void setBtnedit(Integer value) {
		this.btnedit = value;
	}
	/**获取按钮删 “部分”与“全部”没有区别 {'0':'无','9':'部分','91':'全部'}*/
	

	@Column(name = "btndel", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getBtndel() {
		return this.btndel;
	}
	/**设置按钮删 “部分”与“全部”没有区别 {'0':'无','9':'部分','91':'全部'}*/

	public void setBtndel(Integer value) {
		this.btndel = value;
	}
	/**获取按钮查 如果设成为“全部”，此列表和查询操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}*/
	

	@Column(name = "btnshow", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getBtnshow() {
		return this.btnshow;
	}
	/**设置按钮查 如果设成为“全部”，此列表和查询操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}*/

	public void setBtnshow(Integer value) {
		this.btnshow = value;
	}
	/**获取查询增加的hql 例：\"userId=\"+sessionobj.id */
	

	@Column(name = "queryaddhql", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getQueryaddhql() {
		return this.queryaddhql;
	}
	/**设置查询增加的hql 例：\"userId=\"+sessionobj.id */

	public void setQueryaddhql(String value) {
		this.queryaddhql = value;
	}
	/**获取修改检查脚本 例：dbobj.userId==sessionobj.id */
	

	@Column(name = "modigvy", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getModigvy() {
		return this.modigvy;
	}
	/**设置修改检查脚本 例：dbobj.userId==sessionobj.id */

	public void setModigvy(String value) {
		this.modigvy = value;
	}
	/**角色String*/
	private String roleIdString;
	/**获取角色String*/
	@Transient
	
	public String getRoleIdString() {
		return roleIdString;
	}
	/**设置角色String*/
	public void setRoleIdString(String value) {
		this.roleIdString=value;
	}
	/**角色Map*/
	private Map<String, Object> roleIdMap;
	/**获取角色Map*/
	@Transient
	
	public Map<String, Object> getRoleIdMap() {
		return roleIdMap;
	}
	/**设置角色Map*/
	public void setRoleIdMap(Map<String, Object> value) {
		this.roleIdMap=value;
	}
	/**表权限String*/
	private String pallString;
	/**获取表权限String*/
	@Transient
	
	public String getPallString() {
		return pallString;
	}
	/**设置表权限String*/
	public void setPallString(String value) {
		this.pallString=value;
	}
	/**表权限Map*/
	private Map<String, Object> pallMap;
	/**获取表权限Map*/
	@Transient
	
	public Map<String, Object> getPallMap() {
		return pallMap;
	}
	/**设置表权限Map*/
	public void setPallMap(Map<String, Object> value) {
		this.pallMap=value;
	}
	/**按钮增String*/
	private String btnnewString;
	/**获取按钮增String*/
	@Transient
	
	public String getBtnnewString() {
		return btnnewString;
	}
	/**设置按钮增String*/
	public void setBtnnewString(String value) {
		this.btnnewString=value;
	}
	/**按钮增Map*/
	private Map<String, Object> btnnewMap;
	/**获取按钮增Map*/
	@Transient
	
	public Map<String, Object> getBtnnewMap() {
		return btnnewMap;
	}
	/**设置按钮增Map*/
	public void setBtnnewMap(Map<String, Object> value) {
		this.btnnewMap=value;
	}
	/**按钮改String*/
	private String btneditString;
	/**获取按钮改String*/
	@Transient
	
	public String getBtneditString() {
		return btneditString;
	}
	/**设置按钮改String*/
	public void setBtneditString(String value) {
		this.btneditString=value;
	}
	/**按钮改Map*/
	private Map<String, Object> btneditMap;
	/**获取按钮改Map*/
	@Transient
	
	public Map<String, Object> getBtneditMap() {
		return btneditMap;
	}
	/**设置按钮改Map*/
	public void setBtneditMap(Map<String, Object> value) {
		this.btneditMap=value;
	}
	/**按钮删String*/
	private String btndelString;
	/**获取按钮删String*/
	@Transient
	
	public String getBtndelString() {
		return btndelString;
	}
	/**设置按钮删String*/
	public void setBtndelString(String value) {
		this.btndelString=value;
	}
	/**按钮删Map*/
	private Map<String, Object> btndelMap;
	/**获取按钮删Map*/
	@Transient
	
	public Map<String, Object> getBtndelMap() {
		return btndelMap;
	}
	/**设置按钮删Map*/
	public void setBtndelMap(Map<String, Object> value) {
		this.btndelMap=value;
	}
	/**按钮查String*/
	private String btnshowString;
	/**获取按钮查String*/
	@Transient
	
	public String getBtnshowString() {
		return btnshowString;
	}
	/**设置按钮查String*/
	public void setBtnshowString(String value) {
		this.btnshowString=value;
	}
	/**按钮查Map*/
	private Map<String, Object> btnshowMap;
	/**获取按钮查Map*/
	@Transient
	
	public Map<String, Object> getBtnshowMap() {
		return btnshowMap;
	}
	/**设置按钮查Map*/
	public void setBtnshowMap(Map<String, Object> value) {
		this.btnshowMap=value;
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
		this.roleIdMap=null;
		this.pallMap=null;
		this.btnnewMap=null;
		this.btneditMap=null;
		this.btndelMap=null;
		this.btnshowMap=null;
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
			.append(getRoleId())
			.append(getTbname())
			.append(getTbalias())
			.append(getPall())
			.append(getBtnnew())
			.append(getBtnedit())
			.append(getBtndel())
			.append(getBtnshow())
			.append(getQueryaddhql())
			.append(getModigvy())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Permittable == false) return false;
		if(this == obj) return true;
		Permittable other = (Permittable)obj;
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
			.append(",RoleId:",getRoleId())
			.append(",Tbname:",getTbname())
			.append(",Tbalias:",getTbalias())
			.append(",Pall:",getPall())
			.append(",Btnnew:",getBtnnew())
			.append(",Btnedit:",getBtnedit())
			.append(",Btndel:",getBtndel())
			.append(",Btnshow:",getBtnshow())
			.append(",Queryaddhql:",getQueryaddhql())
			.append(",Modigvy:",getModigvy())
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
		String[] excludesProperties={"myname","mynameid","roleIdString","roleIdMap","pallString","pallMap","btnnewString","btnnewMap","btneditString","btneditMap","btndelString","btndelMap","btnshowString","btnshowMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","roleIdString","roleIdMap","pallString","pallMap","btnnewString","btnnewMap","btneditString","btneditMap","btndelString","btndelMap","btnshowString","btnshowMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 权限之表设定
Permittable permittable = new Permittable(
	roleId , //Integer 角色 default=0 参看user {'0':'超管','1':'一般管理员','5':'会员'}
	tbname , //String 表名   
	tbalias , //String 表说明   
	pall , //Integer 表权限 default=0 如果设置成为“无”，后面不用设置了，全部无权；如果设成为“全部”，后面也基本不用设置，角色对此表有着和管理员差不多的权限，除了增HQL和修改检查脚本 {'0':'无','9':'部分','91':'全部'}
	btnnew , //Integer 按钮增 default=0 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	btnedit , //Integer 按钮改 default=0 如果设成为“全部”，此操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	btndel , //Integer 按钮删 default=0 “部分”与“全部”没有区别 {'0':'无','9':'部分','91':'全部'}
	btnshow , //Integer 按钮查 default=0 如果设成为“全部”，此列表和查询操作所有字段全部可见 {'0':'无','9':'部分','91':'全部'}
	queryaddhql , //String 查询增加的hql  例：\"userId=\"+sessionobj.id 
	modigvy , //String 修改检查脚本  例：dbobj.userId==sessionobj.id 
	null
);
*/
}
