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
 * 权限之字段设定
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "permitfield")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Permitfield extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "权限之字段设定";

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
	/**字段名 String   */
	@Length(max=50)
	
	private String fieldname;
	/**字段说明 String   */
	@Length(max=50)
	
	private String fieldalias;
	/**增可写字段41 String default=0  map={'0':'无','9':'有','1！sessionobj.id':'1！sessionobj.id'}*/
	@Length(max=100)
	
	private String pf41;
	/**改可写字段42 String default=0  map={'0':'无','9':'有'}*/
	@Length(max=100)
	
	private String pf42;
	/**查可看字段51 String default=0  map={'0':'无','9':'有'}*/
	@Length(max=100)
	
	private String pf51;
	/**列表显示字段52 String default=0  map={'0':'无','9':'有'}*/
	@Length(max=100)
	
	private String pf52;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Permitfield _this){
		_this.roleId=0;
		_this.pf41="0";
		_this.pf42="0";
		_this.pf51="0";
		_this.pf52="0";
	}
	public Permitfield(){
		makedefault(this);
	}
	public Permitfield(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param roleId Integer 角色 default=0 参看user {'0':'超管','1':'一般管理员','5':'会员'}
	 * @param tbname String 表名   
	 * @param fieldname String 字段名   
	 * @param fieldalias String 字段说明   
	 * @param pf41 String 增可写字段41 default=0  {'0':'无','9':'有','1！sessionobj.id':'1！sessionobj.id'}
	 * @param pf42 String 改可写字段42 default=0  {'0':'无','9':'有'}
	 * @param pf51 String 查可看字段51 default=0  {'0':'无','9':'有'}
	 * @param pf52 String 列表显示字段52 default=0  {'0':'无','9':'有'}
	* @param notuse String 没用
	 */
	public Permitfield(Integer roleId ,String tbname ,String fieldname ,String fieldalias ,String pf41 ,String pf42 ,String pf51 ,String pf52 ,String notuse) {
		super();
		this.roleId = roleId;
		this.tbname = tbname;
		this.fieldname = fieldname;
		this.fieldalias = fieldalias;
		this.pf41 = pf41;
		this.pf42 = pf42;
		this.pf51 = pf51;
		this.pf52 = pf52;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param roleId Integer 角色 default=0 参看user {'0':'超管','1':'一般管理员','5':'会员'}
	 * @param tbname String 表名   
	 * @param fieldname String 字段名   
	 * @param fieldalias String 字段说明   
	 * @param pf41 String 增可写字段41 default=0  {'0':'无','9':'有','1！sessionobj.id':'1！sessionobj.id'}
	 * @param pf42 String 改可写字段42 default=0  {'0':'无','9':'有'}
	 * @param pf51 String 查可看字段51 default=0  {'0':'无','9':'有'}
	 * @param pf52 String 列表显示字段52 default=0  {'0':'无','9':'有'}
	* @param notuse String 没用
	 */
	public Permitfield(Integer id ,Integer roleId ,String tbname ,String fieldname ,String fieldalias ,String pf41 ,String pf42 ,String pf51 ,String pf52 ,String notuse) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.tbname = tbname;
		this.fieldname = fieldname;
		this.fieldalias = fieldalias;
		this.pf41 = pf41;
		this.pf42 = pf42;
		this.pf51 = pf51;
		this.pf52 = pf52;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param roleId Integer 角色 default=0 参看user {'0':'超管','1':'一般管理员','5':'会员'}
	 * @param tbname String 表名   
	 * @param fieldname String 字段名   
	 * @param fieldalias String 字段说明   
	 * @param pf41 String 增可写字段41 default=0  {'0':'无','9':'有','1！sessionobj.id':'1！sessionobj.id'}
	 * @param pf42 String 改可写字段42 default=0  {'0':'无','9':'有'}
	 * @param pf51 String 查可看字段51 default=0  {'0':'无','9':'有'}
	 * @param pf52 String 列表显示字段52 default=0  {'0':'无','9':'有'}
	* @param notuse String 没用
	 */
	public Permitfield(Integer id ,Integer roleId ,String tbname ,String fieldname ,String fieldalias ,String pf41 ,String pf42 ,String pf51 ,String pf52 ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.tbname = tbname;
		this.fieldname = fieldname;
		this.fieldalias = fieldalias;
		this.pf41 = pf41;
		this.pf42 = pf42;
		this.pf51 = pf51;
		this.pf52 = pf52;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+fieldname+" ":myname;
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
		mynameid=(mynameid==null)?"["+id+"]"+fieldname+" ":mynameid;
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
	/**获取字段名  */
	

	@Column(name = "fieldname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFieldname() {
		return this.fieldname;
	}
	/**设置字段名  */

	public void setFieldname(String value) {
		this.fieldname = value;
	}
	/**获取字段说明  */
	

	@Column(name = "fieldalias", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFieldalias() {
		return this.fieldalias;
	}
	/**设置字段说明  */

	public void setFieldalias(String value) {
		this.fieldalias = value;
	}
	/**获取增可写字段41  {'0':'无','9':'有','1！sessionobj.id':'1！sessionobj.id'}*/
	

	@Column(name = "pf41", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getPf41() {
		return this.pf41;
	}
	/**设置增可写字段41  {'0':'无','9':'有','1！sessionobj.id':'1！sessionobj.id'}*/

	public void setPf41(String value) {
		this.pf41 = value;
	}
	/**获取改可写字段42  {'0':'无','9':'有'}*/
	

	@Column(name = "pf42", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getPf42() {
		return this.pf42;
	}
	/**设置改可写字段42  {'0':'无','9':'有'}*/

	public void setPf42(String value) {
		this.pf42 = value;
	}
	/**获取查可看字段51  {'0':'无','9':'有'}*/
	

	@Column(name = "pf51", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getPf51() {
		return this.pf51;
	}
	/**设置查可看字段51  {'0':'无','9':'有'}*/

	public void setPf51(String value) {
		this.pf51 = value;
	}
	/**获取列表显示字段52  {'0':'无','9':'有'}*/
	

	@Column(name = "pf52", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getPf52() {
		return this.pf52;
	}
	/**设置列表显示字段52  {'0':'无','9':'有'}*/

	public void setPf52(String value) {
		this.pf52 = value;
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
	/**增可写字段41String*/
	private String pf41String;
	/**获取增可写字段41String*/
	@Transient
	
	public String getPf41String() {
		return pf41String;
	}
	/**设置增可写字段41String*/
	public void setPf41String(String value) {
		this.pf41String=value;
	}
	/**增可写字段41Map*/
	private Map<String, Object> pf41Map;
	/**获取增可写字段41Map*/
	@Transient
	
	public Map<String, Object> getPf41Map() {
		return pf41Map;
	}
	/**设置增可写字段41Map*/
	public void setPf41Map(Map<String, Object> value) {
		this.pf41Map=value;
	}
	/**改可写字段42String*/
	private String pf42String;
	/**获取改可写字段42String*/
	@Transient
	
	public String getPf42String() {
		return pf42String;
	}
	/**设置改可写字段42String*/
	public void setPf42String(String value) {
		this.pf42String=value;
	}
	/**改可写字段42Map*/
	private Map<String, Object> pf42Map;
	/**获取改可写字段42Map*/
	@Transient
	
	public Map<String, Object> getPf42Map() {
		return pf42Map;
	}
	/**设置改可写字段42Map*/
	public void setPf42Map(Map<String, Object> value) {
		this.pf42Map=value;
	}
	/**查可看字段51String*/
	private String pf51String;
	/**获取查可看字段51String*/
	@Transient
	
	public String getPf51String() {
		return pf51String;
	}
	/**设置查可看字段51String*/
	public void setPf51String(String value) {
		this.pf51String=value;
	}
	/**查可看字段51Map*/
	private Map<String, Object> pf51Map;
	/**获取查可看字段51Map*/
	@Transient
	
	public Map<String, Object> getPf51Map() {
		return pf51Map;
	}
	/**设置查可看字段51Map*/
	public void setPf51Map(Map<String, Object> value) {
		this.pf51Map=value;
	}
	/**列表显示字段52String*/
	private String pf52String;
	/**获取列表显示字段52String*/
	@Transient
	
	public String getPf52String() {
		return pf52String;
	}
	/**设置列表显示字段52String*/
	public void setPf52String(String value) {
		this.pf52String=value;
	}
	/**列表显示字段52Map*/
	private Map<String, Object> pf52Map;
	/**获取列表显示字段52Map*/
	@Transient
	
	public Map<String, Object> getPf52Map() {
		return pf52Map;
	}
	/**设置列表显示字段52Map*/
	public void setPf52Map(Map<String, Object> value) {
		this.pf52Map=value;
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
		this.pf41Map=null;
		this.pf42Map=null;
		this.pf51Map=null;
		this.pf52Map=null;
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
			.append(getFieldname())
			.append(getFieldalias())
			.append(getPf41())
			.append(getPf42())
			.append(getPf51())
			.append(getPf52())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Permitfield == false) return false;
		if(this == obj) return true;
		Permitfield other = (Permitfield)obj;
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
			.append(",Fieldname:",getFieldname())
			.append(",Fieldalias:",getFieldalias())
			.append(",Pf41:",getPf41())
			.append(",Pf42:",getPf42())
			.append(",Pf51:",getPf51())
			.append(",Pf52:",getPf52())
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
		String[] excludesProperties={"myname","mynameid","roleIdString","roleIdMap","pf41String","pf41Map","pf42String","pf42Map","pf51String","pf51Map","pf52String","pf52Map","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","roleIdString","roleIdMap","pf41String","pf41Map","pf42String","pf42Map","pf51String","pf51Map","pf52String","pf52Map","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 权限之字段设定
Permitfield permitfield = new Permitfield(
	roleId , //Integer 角色 default=0 参看user {'0':'超管','1':'一般管理员','5':'会员'}
	tbname , //String 表名   
	fieldname , //String 字段名   
	fieldalias , //String 字段说明   
	pf41 , //String 增可写字段41 default=0  {'0':'无','9':'有','1！sessionobj.id':'1！sessionobj.id'}
	pf42 , //String 改可写字段42 default=0  {'0':'无','9':'有'}
	pf51 , //String 查可看字段51 default=0  {'0':'无','9':'有'}
	pf52 , //String 列表显示字段52 default=0  {'0':'无','9':'有'}
	null
);
*/
}
