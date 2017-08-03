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
 * 一对一问题之追加
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "question_add")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class QuestionAdd extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "一对一问题之追加";

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
	/**一对一问题 Integer   */
	@Max(99999999999L)
	
	private Integer questionId;
	/**提问者 Integer default=0  */
	@Max(99999999999L)
	
	private Integer memberQu = 0;
	/**回答者 Integer default=0  */
	@Max(99999999999L)
	
	private Integer memberAn = 0;
	/**内容 String   */
	@Length(max=1000)
	
	private String qa;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(QuestionAdd _this){
		_this.gmtCreate=new Date();
		_this.memberQu=0;
		_this.memberAn=0;
	}
	public QuestionAdd(){
		makedefault(this);
	}
	public QuestionAdd(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param questionId Integer 一对一问题   
	 * @param memberQu Integer 提问者 default=0  
	 * @param memberAn Integer 回答者 default=0  
	 * @param qa String 内容   
	* @param notuse String 没用
	 */
	public QuestionAdd(Integer questionId ,Integer memberQu ,Integer memberAn ,String qa ,String notuse) {
		super();
		this.questionId = questionId;
		this.memberQu = memberQu;
		this.memberAn = memberAn;
		this.qa = qa;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param gmtModified Date 修改时间   
	 * @param questionId Integer 一对一问题   
	 * @param memberQu Integer 提问者 default=0  
	 * @param memberAn Integer 回答者 default=0  
	 * @param qa String 内容   
	* @param notuse String 没用
	 */
	public QuestionAdd(Integer id ,Date gmtModified ,Integer questionId ,Integer memberQu ,Integer memberAn ,String qa ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.gmtModified = gmtModified;
		this.questionId = questionId;
		this.memberQu = memberQu;
		this.memberAn = memberAn;
		this.qa = qa;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param gmtCreate Date 创建时间   
	 * @param gmtModified Date 修改时间   
	 * @param questionId Integer 一对一问题   
	 * @param memberQu Integer 提问者 default=0  
	 * @param memberAn Integer 回答者 default=0  
	 * @param qa String 内容   
	* @param notuse String 没用
	 */
	public QuestionAdd(Integer id ,Date gmtCreate ,Date gmtModified ,Integer questionId ,Integer memberQu ,Integer memberAn ,String qa ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.questionId = questionId;
		this.memberQu = memberQu;
		this.memberAn = memberAn;
		this.qa = qa;
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
	/**获取修改时间  */
	

	@Column(name = "gmt_modified", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtModified() {
		return this.gmtModified;
	}
	/**设置修改时间  */

	public void setGmtModified(Date value) {
		this.gmtModified = value;
	}
	/**获取一对一问题  */
	

	@Column(name = "question_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getQuestionId() {
		return this.questionId;
	}
	/**设置一对一问题  */

	public void setQuestionId(Integer value) {
		this.questionId = value;
	}
	/**获取提问者  */
	

	@Column(name = "member_qu", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getMemberQu() {
		return this.memberQu;
	}
	/**设置提问者  */

	public void setMemberQu(Integer value) {
		this.memberQu = value;
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
	/**获取内容  */
	

	@Column(name = "qa", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getQa() {
		return this.qa;
	}
	/**设置内容  */

	public void setQa(String value) {
		this.qa = value;
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
	/**一对一问题String*/
	private String questionIdString;
	/**获取一对一问题String*/
	@Transient
	
	public String getQuestionIdString() {
		return questionIdString;
	}
	/**设置一对一问题String*/
	public void setQuestionIdString(String value) {
		this.questionIdString=value;
	}
	/**Question - questionIdQuestionObj*/
	private Question questionIdQuestionObj;
	/**获取 Question - questionIdQuestionObj*/
	@Transient
	
	public Question getQuestionIdQuestionObj() {
		return questionIdQuestionObj;
	}
	/**设置 Question - 一对一问题Obj*/
	public void setQuestionIdQuestionObj(Question value) {
		this.questionIdQuestionObj=value;
	}
	/**一对一问题Stringid*/
	private String questionIdStringid;
	/**获取一对一问题Stringid*/
	@Transient
	
	public String getQuestionIdStringid() {
		return questionIdStringid;
	}
	/**设置一对一问题Stringid*/
	public void setQuestionIdStringid(String value) {
		this.questionIdStringid=value;
	}
	/**提问者String*/
	private String memberQuString;
	/**获取提问者String*/
	@Transient
	
	public String getMemberQuString() {
		return memberQuString;
	}
	/**设置提问者String*/
	public void setMemberQuString(String value) {
		this.memberQuString=value;
	}
	/**Member - memberQuMemberObj*/
	private Member memberQuMemberObj;
	/**获取 Member - memberQuMemberObj*/
	@Transient
	
	public Member getMemberQuMemberObj() {
		return memberQuMemberObj;
	}
	/**设置 Member - 提问者Obj*/
	public void setMemberQuMemberObj(Member value) {
		this.memberQuMemberObj=value;
	}
	/**提问者Stringid*/
	private String memberQuStringid;
	/**获取提问者Stringid*/
	@Transient
	
	public String getMemberQuStringid() {
		return memberQuStringid;
	}
	/**设置提问者Stringid*/
	public void setMemberQuStringid(String value) {
		this.memberQuStringid=value;
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
		this.questionIdQuestionObj=null; //Question
		this.memberQuMemberObj=null; //Member
		this.memberAnMemberObj=null; //Member
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
			.append(getGmtModified())
			.append(getQuestionId())
			.append(getMemberQu())
			.append(getMemberAn())
			.append(getQa())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof QuestionAdd == false) return false;
		if(this == obj) return true;
		QuestionAdd other = (QuestionAdd)obj;
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
			.append(",QuestionId:",getQuestionId())
			.append(",MemberQu:",getMemberQu())
			.append(",MemberAn:",getMemberAn())
			.append(",Qa:",getQa())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","gmtModifiedString","questionIdString","questionIdQuestionObj","questionIdStringid","memberQuString","memberQuMemberObj","memberQuStringid","memberAnString","memberAnMemberObj","memberAnStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","gmtModifiedString","questionIdString","questionIdQuestionObj","questionIdStringid","memberQuString","memberQuMemberObj","memberQuStringid","memberAnString","memberAnMemberObj","memberAnStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 一对一问题之追加
QuestionAdd questionAdd = new QuestionAdd(
	questionId , //Integer 一对一问题   
	memberQu , //Integer 提问者 default=0  
	memberAn , //Integer 回答者 default=0  
	qa , //String 内容   
	null
);
*/
}
