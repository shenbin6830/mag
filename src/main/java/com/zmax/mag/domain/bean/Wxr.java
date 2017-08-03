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
 * 微信用户关系<br/>首推
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wxr")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Wxr extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "微信用户关系";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**微信openid String   */
	@Length(max=50)
	
	private String id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**父openid String   */
	@Length(max=50)
	
	private String parentid;
	/**孩子数量 Integer default=0  */
	@Max(99999999999L)
	
	private Integer childrennum = 0;
	/**平台用户 Integer  冗余字段，从wxouser中取得 */
	@Max(99999999999L)
	
	private Integer userId;
	/**角色 Integer default=5  map={'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商','5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师'}*/
	@Max(999L)
	
	private Integer roleId = 5;
	/**二维码 String  600x600 */
	@Length(max=200)
	
	private String imgqr;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Wxr _this){
		_this.gmtCreate=new Date();
		_this.childrennum=0;
		_this.roleId=5;
	}
	public Wxr(){
		makedefault(this);
	}
	public Wxr(String _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param parentid String 父openid   
	 * @param childrennum Integer 孩子数量 default=0  
	 * @param imgqr String 二维码  600x600 
	* @param notuse String 没用
	 */
	public Wxr(String parentid ,Integer childrennum ,String imgqr ,String notuse) {
		super();
		this.parentid = parentid;
		this.childrennum = childrennum;
		this.imgqr = imgqr;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id String 微信openid   
	 * @param parentid String 父openid   
	 * @param childrennum Integer 孩子数量 default=0  
	 * @param userId Integer 平台用户  冗余字段，从wxouser中取得 
	 * @param roleId Integer 角色 default=5  {'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商','5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师'}
	 * @param imgqr String 二维码  600x600 
	* @param notuse String 没用
	 */
	public Wxr(String id ,String parentid ,Integer childrennum ,Integer userId ,Integer roleId ,String imgqr ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.parentid = parentid;
		this.childrennum = childrennum;
		this.userId = userId;
		this.roleId = roleId;
		this.imgqr = imgqr;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id String 微信openid   
	 * @param gmtCreate Date 创建时间   
	 * @param parentid String 父openid   
	 * @param childrennum Integer 孩子数量 default=0  
	 * @param userId Integer 平台用户  冗余字段，从wxouser中取得 
	 * @param roleId Integer 角色 default=5  {'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商','5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师'}
	 * @param imgqr String 二维码  600x600 
	* @param notuse String 没用
	 */
	public Wxr(String id ,Date gmtCreate ,String parentid ,Integer childrennum ,Integer userId ,Integer roleId ,String imgqr ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.parentid = parentid;
		this.childrennum = childrennum;
		this.userId = userId;
		this.roleId = roleId;
		this.imgqr = imgqr;
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
	public void setId(String value) {
		this.id = value;
	}
	/**获取主键*/
	

	@Id 
	@Column(name = "id",  unique = true, nullable = false, insertable = true, updatable = true, length = 50)
	public String getId() {
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
	/**获取父openid  */
	

	@Column(name = "parentid", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getParentid() {
		return this.parentid;
	}
	/**设置父openid  */

	public void setParentid(String value) {
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
	/**获取平台用户 冗余字段，从wxouser中取得 */
	

	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getUserId() {
		return this.userId;
	}
	/**设置平台用户 冗余字段，从wxouser中取得 */

	public void setUserId(Integer value) {
		this.userId = value;
	}
	/**获取角色  {'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商','5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师'}*/
	

	@Column(name = "role_id", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getRoleId() {
		return this.roleId;
	}
	/**设置角色  {'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商','5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师'}*/

	public void setRoleId(Integer value) {
		this.roleId = value;
	}
	/**获取二维码 600x600 */
	

	@Column(name = "imgqr", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getImgqr() {
		return this.imgqr;
	}
	/**设置二维码 600x600 */

	public void setImgqr(String value) {
		this.imgqr = value;
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
	/**父openidString*/
	private String parentidString;
	/**获取父openidString*/
	@Transient
	
	public String getParentidString() {
		return parentidString;
	}
	/**设置父openidString*/
	public void setParentidString(String value) {
		this.parentidString=value;
	}
	/**父openidStringid*/
	private String parentidStringid;
	/**获取父openidString*/
	@Transient
	
	public String getParentidStringid() {
		return parentidStringid;
	}
	/**设置父openidString*/
	public void setParentidStringid(String value) {
		this.parentidStringid=value;
	}
	/**孩子List*/
	private List<Wxr> children=new ArrayList<Wxr>();
	/**获取孩子List*/
	@Transient
	
	public List<Wxr> getChildren() {
		return children;
	}
	/**设置孩子List*/
	public void setChildren(List<Wxr> children) {
		this.children = children;
	}
	/**父母*/
	private Wxr parent=null;
	/**获取父母对象Wxr*/
	@Transient
	
	public Wxr getParent() {
		return parent;
	}
	/**设置父母对象Wxr*/
	public void setParent(Wxr parent) {
		this.parent = parent;
	}
	/**父母List*/
	private List<Wxr> parentList;
	/**获取父母List*/
	@Transient
	
	public List<Wxr> getParentList() {
		return parentList;
	}
	/**设置父母List*/
	public void setParentList(List<Wxr> parentList) {
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
		this.children=null;
		this.parent=null;
		this.parentList=null;
		this.userIdUserObj=null; //User
		this.roleIdMap=null;
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
			if("".equals(this.id))
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
			.append(getParentid())
			.append(getChildrennum())
			.append(getUserId())
			.append(getRoleId())
			.append(getImgqr())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Wxr == false) return false;
		if(this == obj) return true;
		Wxr other = (Wxr)obj;
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
			.append(",Parentid:",getParentid())
			.append(",Childrennum:",getChildrennum())
			.append(",UserId:",getUserId())
			.append(",RoleId:",getRoleId())
			.append(",Imgqr:",getImgqr())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","parentidString","parentidStringid","children","parent","parentList","state","checked","userIdString","userIdUserObj","userIdStringid","roleIdString","roleIdMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","parentidString","parentidStringid","children","parent","parentList","state","checked","userIdString","userIdUserObj","userIdStringid","roleIdString","roleIdMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 微信用户关系
Wxr wxr = new Wxr(
	parentid , //String 父openid   
	childrennum , //Integer 孩子数量 default=0  
	imgqr , //String 二维码  600x600 
	null
);
*/
}
