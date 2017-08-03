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
 * 账号信息修改
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "user")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class User extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "账号信息修改";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	public static final String FORMAT_GMT_MODIFIED = DATE_FORMAT;
	
	//columns START
	/**序号 Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**修改时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;
	/**状态 Integer default=0  map={'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'}*/
	@Max(999L)
	
	private Integer status = 0;
	/**角色 Integer default=0 这里如果改变，要变的包括：权限表，wxr map={'0':'超管','1':'一般管理员','5':'会员'}*/
	@Max(999L)
	
	private Integer roleId = 0;
	/**账号 String  唯一值 */
	@NotBlank @Length(max=20)
	
	private String username;
	/**密码 String   */
	@NotBlank @Length(max=32)
	
	private String password;
	/**昵称 String   */
	@Length(max=20)
	
	private String nickname;
	/**隶属于 Integer   */
	@Max(99999999999L)
	
	private Integer userId;
	/**微信openid String  以微信用户表为主，此字段是冗余字段 */
	@Length(max=50)
	
	private String openid;
	/**类型对象*/
	private Object userobj;
	/**权限列表*/
	private Map<String,String> pmtmap;
	/**拥有的对象*/
	private Map<String,Object> objmap;
	/**微信openidMd5*/
	private String openidmd5;
	/**token*/
	private String token;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(User _this){
		_this.gmtCreate=new Date();
		_this.status=0;
		_this.roleId=0;
	}
	public User(){
		makedefault(this);
	}
	public User(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param status Integer 状态 default=0  {'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'}
	 * @param roleId Integer 角色 default=0 这里如果改变，要变的包括：权限表，wxr {'0':'超管','1':'一般管理员','5':'会员'}
	 * @param username String 账号  唯一值 
	 * @param password String 密码   
	 * @param nickname String 昵称   
	 * @param userId Integer 隶属于   
	 * @param openid String 微信openid  以微信用户表为主，此字段是冗余字段 
	* @param notuse String 没用
	 */
	public User(Integer status ,Integer roleId ,String username ,String password ,String nickname ,Integer userId ,String openid ,String notuse) {
		super();
		this.status = status;
		this.roleId = roleId;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.userId = userId;
		this.openid = openid;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param gmtModified Date 修改时间   
	 * @param status Integer 状态 default=0  {'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'}
	 * @param roleId Integer 角色 default=0 这里如果改变，要变的包括：权限表，wxr {'0':'超管','1':'一般管理员','5':'会员'}
	 * @param username String 账号  唯一值 
	 * @param password String 密码   
	 * @param nickname String 昵称   
	 * @param userId Integer 隶属于   
	 * @param openid String 微信openid  以微信用户表为主，此字段是冗余字段 
	* @param notuse String 没用
	 */
	public User(Integer id ,Date gmtModified ,Integer status ,Integer roleId ,String username ,String password ,String nickname ,Integer userId ,String openid ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.gmtModified = gmtModified;
		this.status = status;
		this.roleId = roleId;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.userId = userId;
		this.openid = openid;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param gmtCreate Date 创建时间   
	 * @param gmtModified Date 修改时间   
	 * @param status Integer 状态 default=0  {'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'}
	 * @param roleId Integer 角色 default=0 这里如果改变，要变的包括：权限表，wxr {'0':'超管','1':'一般管理员','5':'会员'}
	 * @param username String 账号  唯一值 
	 * @param password String 密码   
	 * @param nickname String 昵称   
	 * @param userId Integer 隶属于   
	 * @param openid String 微信openid  以微信用户表为主，此字段是冗余字段 
	 * @param userobj String 类型对象   
	 * @param pmtmap String 权限列表  后台管理员会用到 
	 * @param objmap String 拥有的对象  会放到session中,key=表名,value=对象 
	 * @param openidmd5 String 微信openidMd5   
	 * @param token String token   
	* @param notuse String 没用
	 */
	public User(Integer id ,Date gmtCreate ,Date gmtModified ,Integer status ,Integer roleId ,String username ,String password ,String nickname ,Integer userId ,String openid ,Object userobj ,Map<String,String> pmtmap ,Map<String,Object> objmap ,String openidmd5 ,String token ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.status = status;
		this.roleId = roleId;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.userId = userId;
		this.openid = openid;
		this.userobj = userobj;
		this.pmtmap = pmtmap;
		this.objmap = objmap;
		this.openidmd5 = openidmd5;
		this.token = token;
	}
	/**
	 * 构造之不能为空的参数
	 * @param username String 账号 
	 * @param password String 密码 
	* @param notuse String 没用
	 */
	public User(Integer _id,String _username,String _password,String notuse){
		this.username=_username;
		this.password=_password;
		makedefault(this);
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
	/**获取修改时间  */
	

	@Column(name = "gmt_modified", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtModified() {
		return this.gmtModified;
	}
	/**设置修改时间  */

	public void setGmtModified(Date value) {
		this.gmtModified = value;
	}
	/**获取状态  {'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'}*/
	

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	/**设置状态  {'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'}*/

	public void setStatus(Integer value) {
		this.status = value;
	}
	/**获取角色 这里如果改变，要变的包括：权限表，wxr {'0':'超管','1':'一般管理员','5':'会员'}*/
	

	@Column(name = "role_id", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getRoleId() {
		return this.roleId;
	}
	/**设置角色 这里如果改变，要变的包括：权限表，wxr {'0':'超管','1':'一般管理员','5':'会员'}*/

	public void setRoleId(Integer value) {
		this.roleId = value;
	}
	/**获取账号 唯一值 */
	

	@Column(name = "username", unique = true, nullable = false, insertable = true, updatable = true, length = 20)
	public String getUsername() {
		return this.username;
	}
	/**设置账号 唯一值 */

	public void setUsername(String value) {
		this.username = value;
	}
	/**获取密码  */
	

	@Column(name = "password", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	public String getPassword() {
		return this.password;
	}
	/**设置密码  */

	public void setPassword(String value) {
		this.password = value;
	}
	/**获取昵称  */
	

	@Column(name = "nickname", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getNickname() {
		return this.nickname;
	}
	/**设置昵称  */

	public void setNickname(String value) {
		this.nickname = value;
	}
	/**获取隶属于  */
	

	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getUserId() {
		return this.userId;
	}
	/**设置隶属于  */

	public void setUserId(Integer value) {
		this.userId = value;
	}
	/**获取微信openid 以微信用户表为主，此字段是冗余字段 */
	

	@Column(name = "openid", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getOpenid() {
		return this.openid;
	}
	/**设置微信openid 以微信用户表为主，此字段是冗余字段 */

	public void setOpenid(String value) {
		this.openid = value;
	}
	/**对象 获取类型对象  */
	@Transient
	

	public Object getUserobj() {
		return this.userobj;
	}
	/**设置类型对象  */

	public void setUserobj(Object value) {
		this.userobj = value;
	}
	/**对象 获取权限列表 后台管理员会用到 */
	@Transient
	

	public Map<String,String> getPmtmap() {
		return this.pmtmap;
	}
	/**设置权限列表 后台管理员会用到 */

	public void setPmtmap(Map<String,String> value) {
		this.pmtmap = value;
	}
	/**对象 获取拥有的对象 会放到session中,key=表名,value=对象 */
	@Transient
	

	public Map<String,Object> getObjmap() {
		return this.objmap;
	}
	/**设置拥有的对象 会放到session中,key=表名,value=对象 */

	public void setObjmap(Map<String,Object> value) {
		this.objmap = value;
	}
	/**对象 获取微信openidMd5  */
	@Transient
	

	public String getOpenidmd5() {
		return this.openidmd5;
	}
	/**设置微信openidMd5  */

	public void setOpenidmd5(String value) {
		this.openidmd5 = value;
	}
	/**对象 获取token  */
	@Transient
	

	public String getToken() {
		return this.token;
	}
	/**设置token  */

	public void setToken(String value) {
		this.token = value;
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
	private String statusString;
	/**获取状态String*/
	@Transient
	
	public String getStatusString() {
		return statusString;
	}
	/**设置状态String*/
	public void setStatusString(String value) {
		this.statusString=value;
	}
	/**状态Map*/
	private Map<String, Object> statusMap;
	/**获取状态Map*/
	@Transient
	
	public Map<String, Object> getStatusMap() {
		return statusMap;
	}
	/**设置状态Map*/
	public void setStatusMap(Map<String, Object> value) {
		this.statusMap=value;
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
		this.statusMap=null;
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
			if(0==this.id)
				return true;
		}
		if("".equals(this.username))
			return true;
		if("".equals(this.password))
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
		if(this.username==null)
			return true;
		if(this.password==null)
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
			.append(getGmtCreate())
			.append(getGmtModified())
			.append(getStatus())
			.append(getRoleId())
			.append(getUsername())
			.append(getPassword())
			.append(getNickname())
			.append(getUserId())
			.append(getOpenid())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof User == false) return false;
		if(this == obj) return true;
		User other = (User)obj;
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
			.append(",GmtModified:",getGmtModified())
			.append(",Status:",getStatus())
			.append(",RoleId:",getRoleId())
			.append(",Username:",getUsername())
			.append(",Password:",getPassword())
			.append(",Nickname:",getNickname())
			.append(",UserId:",getUserId())
			.append(",Openid:",getOpenid())
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
		String[] excludesProperties={"pmtmap","myname","mynameid","gmtCreateString","gmtModifiedString","statusString","statusMap","roleIdString","roleIdMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"pmtmap","myname","mynameid","gmtCreateString","gmtModifiedString","statusString","statusMap","roleIdString","roleIdMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 账号信息修改
User user = new User(
	status , //Integer 状态 default=0  {'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'}
	roleId , //Integer 角色 default=0 这里如果改变，要变的包括：权限表，wxr {'0':'超管','1':'一般管理员','5':'会员'}
	username , //String 账号  唯一值 
	password , //String 密码   
	nickname , //String 昵称   
	userId , //Integer 隶属于   
	openid , //String 微信openid  以微信用户表为主，此字段是冗余字段 
	null
);
*/
}
