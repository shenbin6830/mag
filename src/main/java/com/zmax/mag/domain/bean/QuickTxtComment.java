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
 * 抢答的评价
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "quick_txt_comment")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class QuickTxtComment extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "抢答的评价";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**序号 Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**抢答回答ID Integer   */
	@Max(99999999999L)
	
	private Integer quickTxtId;
	/**回答者 Integer default=0  */
	@Max(99999999999L)
	
	private Integer memberAn = 0;
	/**观看者 Integer  也就是评论者 */
	@Max(99999999999L)
	
	private Integer memberVi;
	/**评论情况 Integer   map={'0':'以后再评','1':'好评','-1':'差评'}*/
	@Max(999L)
	
	private Integer ctype;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(QuickTxtComment _this){
		_this.gmtCreate=new Date();
		_this.memberAn=0;
	}
	public QuickTxtComment(){
		makedefault(this);
	}
	public QuickTxtComment(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param quickTxtId Integer 抢答回答ID   
	 * @param memberVi Integer 观看者  也就是评论者 
	 * @param ctype Integer 评论情况   {'0':'以后再评','1':'好评','-1':'差评'}
	* @param notuse String 没用
	 */
	public QuickTxtComment(Integer quickTxtId ,Integer memberVi ,Integer ctype ,String notuse) {
		super();
		this.quickTxtId = quickTxtId;
		this.memberVi = memberVi;
		this.ctype = ctype;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param quickTxtId Integer 抢答回答ID   
	 * @param memberAn Integer 回答者 default=0  
	 * @param memberVi Integer 观看者  也就是评论者 
	 * @param ctype Integer 评论情况   {'0':'以后再评','1':'好评','-1':'差评'}
	* @param notuse String 没用
	 */
	public QuickTxtComment(Integer id ,Integer quickTxtId ,Integer memberAn ,Integer memberVi ,Integer ctype ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.quickTxtId = quickTxtId;
		this.memberAn = memberAn;
		this.memberVi = memberVi;
		this.ctype = ctype;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param gmtCreate Date 创建时间   
	 * @param quickTxtId Integer 抢答回答ID   
	 * @param memberAn Integer 回答者 default=0  
	 * @param memberVi Integer 观看者  也就是评论者 
	 * @param ctype Integer 评论情况   {'0':'以后再评','1':'好评','-1':'差评'}
	* @param notuse String 没用
	 */
	public QuickTxtComment(Integer id ,Date gmtCreate ,Integer quickTxtId ,Integer memberAn ,Integer memberVi ,Integer ctype ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.quickTxtId = quickTxtId;
		this.memberAn = memberAn;
		this.memberVi = memberVi;
		this.ctype = ctype;
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
	/**获取抢答回答ID  */
	

	@Column(name = "quick_txt_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getQuickTxtId() {
		return this.quickTxtId;
	}
	/**设置抢答回答ID  */

	public void setQuickTxtId(Integer value) {
		this.quickTxtId = value;
	}
	/**获取回答者  */
	

	@Column(name = "member_an", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getMemberAn() {
		return this.memberAn;
	}
	/**设置回答者  */

	public void setMemberAn(Integer value) {
		this.memberAn = value;
	}
	/**获取观看者 也就是评论者 */
	

	@Column(name = "member_vi", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getMemberVi() {
		return this.memberVi;
	}
	/**设置观看者 也就是评论者 */

	public void setMemberVi(Integer value) {
		this.memberVi = value;
	}
	/**获取评论情况  {'0':'以后再评','1':'好评','-1':'差评'}*/
	

	@Column(name = "ctype", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getCtype() {
		return this.ctype;
	}
	/**设置评论情况  {'0':'以后再评','1':'好评','-1':'差评'}*/

	public void setCtype(Integer value) {
		this.ctype = value;
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
	/**抢答回答IDString*/
	private String quickTxtIdString;
	/**获取抢答回答IDString*/
	@Transient
	
	public String getQuickTxtIdString() {
		return quickTxtIdString;
	}
	/**设置抢答回答IDString*/
	public void setQuickTxtIdString(String value) {
		this.quickTxtIdString=value;
	}
	/**QuickTxt - quickTxtIdQuickTxtObj*/
	private QuickTxt quickTxtIdQuickTxtObj;
	/**获取 QuickTxt - quickTxtIdQuickTxtObj*/
	@Transient
	
	public QuickTxt getQuickTxtIdQuickTxtObj() {
		return quickTxtIdQuickTxtObj;
	}
	/**设置 QuickTxt - 抢答回答IDObj*/
	public void setQuickTxtIdQuickTxtObj(QuickTxt value) {
		this.quickTxtIdQuickTxtObj=value;
	}
	/**抢答回答IDStringid*/
	private String quickTxtIdStringid;
	/**获取抢答回答IDStringid*/
	@Transient
	
	public String getQuickTxtIdStringid() {
		return quickTxtIdStringid;
	}
	/**设置抢答回答IDStringid*/
	public void setQuickTxtIdStringid(String value) {
		this.quickTxtIdStringid=value;
	}
	/**回答者String*/
	private String memberAnString;
	/**获取回答者String*/
	@Transient
	
	public String getMemberAnString() {
		return memberAnString;
	}
	/**设置回答者String*/
	public void setMemberAnString(String value) {
		this.memberAnString=value;
	}
	/**Member - memberAnMemberObj*/
	private Member memberAnMemberObj;
	/**获取 Member - memberAnMemberObj*/
	@Transient
	
	public Member getMemberAnMemberObj() {
		return memberAnMemberObj;
	}
	/**设置 Member - 回答者Obj*/
	public void setMemberAnMemberObj(Member value) {
		this.memberAnMemberObj=value;
	}
	/**回答者Stringid*/
	private String memberAnStringid;
	/**获取回答者Stringid*/
	@Transient
	
	public String getMemberAnStringid() {
		return memberAnStringid;
	}
	/**设置回答者Stringid*/
	public void setMemberAnStringid(String value) {
		this.memberAnStringid=value;
	}
	/**观看者String*/
	private String memberViString;
	/**获取观看者String*/
	@Transient
	
	public String getMemberViString() {
		return memberViString;
	}
	/**设置观看者String*/
	public void setMemberViString(String value) {
		this.memberViString=value;
	}
	/**Member - memberViMemberObj*/
	private Member memberViMemberObj;
	/**获取 Member - memberViMemberObj*/
	@Transient
	
	public Member getMemberViMemberObj() {
		return memberViMemberObj;
	}
	/**设置 Member - 观看者Obj*/
	public void setMemberViMemberObj(Member value) {
		this.memberViMemberObj=value;
	}
	/**观看者Stringid*/
	private String memberViStringid;
	/**获取观看者Stringid*/
	@Transient
	
	public String getMemberViStringid() {
		return memberViStringid;
	}
	/**设置观看者Stringid*/
	public void setMemberViStringid(String value) {
		this.memberViStringid=value;
	}
	/**评论情况String*/
	private String ctypeString;
	/**获取评论情况String*/
	@Transient
	
	public String getCtypeString() {
		return ctypeString;
	}
	/**设置评论情况String*/
	public void setCtypeString(String value) {
		this.ctypeString=value;
	}
	/**评论情况Map*/
	private Map<String, Object> ctypeMap;
	/**获取评论情况Map*/
	@Transient
	
	public Map<String, Object> getCtypeMap() {
		return ctypeMap;
	}
	/**设置评论情况Map*/
	public void setCtypeMap(Map<String, Object> value) {
		this.ctypeMap=value;
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
		this.quickTxtIdQuickTxtObj=null; //QuickTxt
		this.memberAnMemberObj=null; //Member
		this.memberViMemberObj=null; //Member
		this.ctypeMap=null;
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
			.append(getQuickTxtId())
			.append(getMemberAn())
			.append(getMemberVi())
			.append(getCtype())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof QuickTxtComment == false) return false;
		if(this == obj) return true;
		QuickTxtComment other = (QuickTxtComment)obj;
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
			.append(",QuickTxtId:",getQuickTxtId())
			.append(",MemberAn:",getMemberAn())
			.append(",MemberVi:",getMemberVi())
			.append(",Ctype:",getCtype())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","quickTxtIdString","quickTxtIdQuickTxtObj","quickTxtIdStringid","memberAnString","memberAnMemberObj","memberAnStringid","memberViString","memberViMemberObj","memberViStringid","ctypeString","ctypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","quickTxtIdString","quickTxtIdQuickTxtObj","quickTxtIdStringid","memberAnString","memberAnMemberObj","memberAnStringid","memberViString","memberViMemberObj","memberViStringid","ctypeString","ctypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 抢答的评价
QuickTxtComment quickTxtComment = new QuickTxtComment(
	quickTxtId , //Integer 抢答回答ID   
	memberVi , //Integer 观看者  也就是评论者 
	ctype , //Integer 评论情况   {'0':'以后再评','1':'好评','-1':'差评'}
	null
);
*/
}
