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
 * 短消息
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "message")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Message extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "短消息";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**序号 Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**发送者 Integer default=0  */
	@Max(99999999999L)
	
	private Integer memberFr = 0;
	/**接收者 Integer default=0  */
	@Max(99999999999L)
	
	private Integer memberTo = 0;
	/**内容 String   */
	@Length(max=200)
	
	private String msg;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Message _this){
		_this.gmtCreate=new Date();
		_this.memberFr=0;
		_this.memberTo=0;
	}
	public Message(){
		makedefault(this);
	}
	public Message(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param memberFr Integer 发送者 default=0  
	 * @param memberTo Integer 接收者 default=0  
	 * @param msg String 内容   
	* @param notuse String 没用
	 */
	public Message(Integer memberFr ,Integer memberTo ,String msg ,String notuse) {
		super();
		this.memberFr = memberFr;
		this.memberTo = memberTo;
		this.msg = msg;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param memberFr Integer 发送者 default=0  
	 * @param memberTo Integer 接收者 default=0  
	 * @param msg String 内容   
	* @param notuse String 没用
	 */
	public Message(Integer id ,Integer memberFr ,Integer memberTo ,String msg ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.memberFr = memberFr;
		this.memberTo = memberTo;
		this.msg = msg;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param gmtCreate Date 创建时间   
	 * @param memberFr Integer 发送者 default=0  
	 * @param memberTo Integer 接收者 default=0  
	 * @param msg String 内容   
	* @param notuse String 没用
	 */
	public Message(Integer id ,Date gmtCreate ,Integer memberFr ,Integer memberTo ,String msg ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.memberFr = memberFr;
		this.memberTo = memberTo;
		this.msg = msg;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+msg+" ":myname;
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
		mynameid=(mynameid==null)?"["+id+"]"+msg+" ":mynameid;
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
	/**获取发送者  */
	

	@Column(name = "member_fr", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getMemberFr() {
		return this.memberFr;
	}
	/**设置发送者  */

	public void setMemberFr(Integer value) {
		this.memberFr = value;
	}
	/**获取接收者  */
	

	@Column(name = "member_to", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getMemberTo() {
		return this.memberTo;
	}
	/**设置接收者  */

	public void setMemberTo(Integer value) {
		this.memberTo = value;
	}
	/**获取内容  */
	

	@Column(name = "msg", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getMsg() {
		return this.msg;
	}
	/**设置内容  */

	public void setMsg(String value) {
		this.msg = value;
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
	/**发送者String*/
	private String memberFrString;
	/**获取发送者String*/
	@Transient
	
	public String getMemberFrString() {
		return memberFrString;
	}
	/**设置发送者String*/
	public void setMemberFrString(String value) {
		this.memberFrString=value;
	}
	/**Member - memberFrMemberObj*/
	private Member memberFrMemberObj;
	/**获取 Member - memberFrMemberObj*/
	@Transient
	
	public Member getMemberFrMemberObj() {
		return memberFrMemberObj;
	}
	/**设置 Member - 发送者Obj*/
	public void setMemberFrMemberObj(Member value) {
		this.memberFrMemberObj=value;
	}
	/**发送者Stringid*/
	private String memberFrStringid;
	/**获取发送者Stringid*/
	@Transient
	
	public String getMemberFrStringid() {
		return memberFrStringid;
	}
	/**设置发送者Stringid*/
	public void setMemberFrStringid(String value) {
		this.memberFrStringid=value;
	}
	/**接收者String*/
	private String memberToString;
	/**获取接收者String*/
	@Transient
	
	public String getMemberToString() {
		return memberToString;
	}
	/**设置接收者String*/
	public void setMemberToString(String value) {
		this.memberToString=value;
	}
	/**Member - memberToMemberObj*/
	private Member memberToMemberObj;
	/**获取 Member - memberToMemberObj*/
	@Transient
	
	public Member getMemberToMemberObj() {
		return memberToMemberObj;
	}
	/**设置 Member - 接收者Obj*/
	public void setMemberToMemberObj(Member value) {
		this.memberToMemberObj=value;
	}
	/**接收者Stringid*/
	private String memberToStringid;
	/**获取接收者Stringid*/
	@Transient
	
	public String getMemberToStringid() {
		return memberToStringid;
	}
	/**设置接收者Stringid*/
	public void setMemberToStringid(String value) {
		this.memberToStringid=value;
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
		this.memberFrMemberObj=null; //Member
		this.memberToMemberObj=null; //Member
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
			.append(getMemberFr())
			.append(getMemberTo())
			.append(getMsg())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Message == false) return false;
		if(this == obj) return true;
		Message other = (Message)obj;
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
			.append(",MemberFr:",getMemberFr())
			.append(",MemberTo:",getMemberTo())
			.append(",Msg:",getMsg())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","memberFrString","memberFrMemberObj","memberFrStringid","memberToString","memberToMemberObj","memberToStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","memberFrString","memberFrMemberObj","memberFrStringid","memberToString","memberToMemberObj","memberToStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 短消息
Message message = new Message(
	memberFr , //Integer 发送者 default=0  
	memberTo , //Integer 接收者 default=0  
	msg , //String 内容   
	null
);
*/
}
