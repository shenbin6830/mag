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
 * 客服管理之增删改参数
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wa_custom_aud_param")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class WaCustomAudParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "客服管理之增删改参数";

	//date formats
	
	//columns START
	/**序号ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**卖家 Integer   */
	@Max(99999999999L)
	
	private Integer userId;
	/**状态 Integer default=1 有效的不超过10个 */
	@Max(999L)
	
	private Integer statusValidOrNot = 1;
	/**完整客服账号 String  完整客服账号，格式为：账号前缀@公众号微信号，工号不能重复，一旦输入不能修改，由字母、数字组成(店家只需要填写账号前缀即可，@公众号微信号会由系统添加)。 */
	@Length(max=50)
	
	private String kfAccount;
	/**客服昵称 String  客服昵称，最长6个汉字或12个英文字符 */
	@Length(max=12)
	
	private String nickname;
	/**登录密码 String  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 */
	@Length(max=40)
	
	private String password;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaCustomAudParam _this){
		_this.statusValidOrNot=1;
	}
	public WaCustomAudParam(){
		makedefault(this);
	}
	public WaCustomAudParam(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param userId Integer 卖家   
	 * @param statusValidOrNot Integer 状态 default=1 有效的不超过10个 
	 * @param kfAccount String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号，工号不能重复，一旦输入不能修改，由字母、数字组成(店家只需要填写账号前缀即可，@公众号微信号会由系统添加)。 
	 * @param nickname String 客服昵称  客服昵称，最长6个汉字或12个英文字符 
	 * @param password String 登录密码  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 
	* @param notuse String 没用
	 */
	public WaCustomAudParam(Integer userId ,Integer statusValidOrNot ,String kfAccount ,String nickname ,String password ,String notuse) {
		super();
		this.userId = userId;
		this.statusValidOrNot = statusValidOrNot;
		this.kfAccount = kfAccount;
		this.nickname = nickname;
		this.password = password;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param userId Integer 卖家   
	 * @param statusValidOrNot Integer 状态 default=1 有效的不超过10个 
	 * @param kfAccount String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号，工号不能重复，一旦输入不能修改，由字母、数字组成(店家只需要填写账号前缀即可，@公众号微信号会由系统添加)。 
	 * @param nickname String 客服昵称  客服昵称，最长6个汉字或12个英文字符 
	 * @param password String 登录密码  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 
	* @param notuse String 没用
	 */
	public WaCustomAudParam(Integer id ,Integer userId ,Integer statusValidOrNot ,String kfAccount ,String nickname ,String password ,String notuse) {
		super();
		this.id = id;
		this.userId = userId;
		this.statusValidOrNot = statusValidOrNot;
		this.kfAccount = kfAccount;
		this.nickname = nickname;
		this.password = password;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param userId Integer 卖家   
	 * @param statusValidOrNot Integer 状态 default=1 有效的不超过10个 
	 * @param kfAccount String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号，工号不能重复，一旦输入不能修改，由字母、数字组成(店家只需要填写账号前缀即可，@公众号微信号会由系统添加)。 
	 * @param nickname String 客服昵称  客服昵称，最长6个汉字或12个英文字符 
	 * @param password String 登录密码  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 
	* @param notuse String 没用
	 */
	public WaCustomAudParam(Integer id ,Integer userId ,Integer statusValidOrNot ,String kfAccount ,String nickname ,String password ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.userId = userId;
		this.statusValidOrNot = statusValidOrNot;
		this.kfAccount = kfAccount;
		this.nickname = nickname;
		this.password = password;
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
	
	/**获取卖家  */
	

	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getUserId() {
		return this.userId;
	}
	/**设置卖家  */

	public void setUserId(Integer value) {
		this.userId = value;
	}
	/**获取状态 有效的不超过10个 */
	

	@Column(name = "status_valid_or_not", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatusValidOrNot() {
		return this.statusValidOrNot;
	}
	/**设置状态 有效的不超过10个 */

	public void setStatusValidOrNot(Integer value) {
		this.statusValidOrNot = value;
	}
	/**获取完整客服账号 完整客服账号，格式为：账号前缀@公众号微信号，工号不能重复，一旦输入不能修改，由字母、数字组成(店家只需要填写账号前缀即可，@公众号微信号会由系统添加)。 */
	

	@Column(name = "kf_account", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getKfAccount() {
		return this.kfAccount;
	}
	/**设置完整客服账号 完整客服账号，格式为：账号前缀@公众号微信号，工号不能重复，一旦输入不能修改，由字母、数字组成(店家只需要填写账号前缀即可，@公众号微信号会由系统添加)。 */

	public void setKfAccount(String value) {
		this.kfAccount = value;
	}
	/**获取客服昵称 客服昵称，最长6个汉字或12个英文字符 */
	

	@Column(name = "nickname", unique = false, nullable = true, insertable = true, updatable = true, length = 12)
	public String getNickname() {
		return this.nickname;
	}
	/**设置客服昵称 客服昵称，最长6个汉字或12个英文字符 */

	public void setNickname(String value) {
		this.nickname = value;
	}
	/**获取登录密码 客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 */
	

	@Column(name = "password", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getPassword() {
		return this.password;
	}
	/**设置登录密码 客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 */

	public void setPassword(String value) {
		this.password = value;
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
	/**状态String*/
	private String statusValidOrNotString;
	/**获取状态String*/
	@Transient
	
	public String getStatusValidOrNotString() {
		return statusValidOrNotString;
	}
	/**设置状态String*/
	public void setStatusValidOrNotString(String value) {
		this.statusValidOrNotString=value;
	}
	/**状态Map*/
	private Map<String, Object> statusValidOrNotMap;
	/**获取状态Map*/
	@Transient
	
	public Map<String, Object> getStatusValidOrNotMap() {
		return statusValidOrNotMap;
	}
	/**设置状态Map*/
	public void setStatusValidOrNotMap(Map<String, Object> value) {
		this.statusValidOrNotMap=value;
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
		this.statusValidOrNotMap=null;
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
			.append(getUserId())
			.append(getStatusValidOrNot())
			.append(getKfAccount())
			.append(getNickname())
			.append(getPassword())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof WaCustomAudParam == false) return false;
		if(this == obj) return true;
		WaCustomAudParam other = (WaCustomAudParam)obj;
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
			.append(",StatusValidOrNot:",getStatusValidOrNot())
			.append(",KfAccount:",getKfAccount())
			.append(",Nickname:",getNickname())
			.append(",Password:",getPassword())
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
		String[] excludesProperties={"myname","mynameid","userIdString","userIdUserObj","userIdStringid","statusValidOrNotString","statusValidOrNotMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"id","userId","statusValidOrNot","myname","mynameid","userIdString","userIdUserObj","userIdStringid","statusValidOrNotString","statusValidOrNotMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 客服管理之增删改参数
WaCustomAudParam waCustomAudParam = new WaCustomAudParam(
	userId , //Integer 卖家   
	statusValidOrNot , //Integer 状态 default=1 有效的不超过10个 
	kfAccount , //String 完整客服账号  完整客服账号，格式为：账号前缀@公众号微信号，工号不能重复，一旦输入不能修改，由字母、数字组成(店家只需要填写账号前缀即可，@公众号微信号会由系统添加)。 
	nickname , //String 客服昵称  客服昵称，最长6个汉字或12个英文字符 
	password , //String 登录密码  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码 
	null
);
*/
}
