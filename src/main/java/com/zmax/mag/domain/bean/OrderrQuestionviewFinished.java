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


import com.zmax.mag.domain.bean.wx.*;

 
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 订单之一对一问题观看归档
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "orderr_questionview_finished")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class OrderrQuestionviewFinished extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "订单之一对一问题观看归档";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	public static final String FORMAT_GMT_PAY = DATE_FORMAT;
	
	//columns START
	/**序号ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**支付时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtPay;
	/**支付状态 Integer default=0  map={'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}*/
	@Max(999L)
	
	private Integer status = 0;
	/**支付方式 Integer default=0  map={'0':'余额支付','2':'微信支付','3':'支付宝支付'}*/
	@Max(999L)
	
	private Integer itypePay = 0;
	/**会员 Integer   */
	@Max(99999999999L)
	
	private Integer memberId;
	/**姓名 String   */
	@Length(max=20)
	
	private String name;
	/**手机 String   */
	@Length(max=20)
	
	private String mobile;
	/**一对一问题ID Integer   */
	@Max(99999999999L)
	
	private Integer questionId;
	/**问题 String  问题名 */
	@Length(max=100)
	
	private String title;
	/**总价 Double default=0.0  */
	
	
	private Double price;
	/**微信支付H5对象*/
	private WaJsapiPaymentParam paywxh5;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(OrderrQuestionviewFinished _this){
		_this.gmtCreate=new Date();
		_this.status=0;
		_this.itypePay=0;
		_this.price=0.0;
	}
	public OrderrQuestionviewFinished(){
		makedefault(this);
	}
	public OrderrQuestionviewFinished(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param gmtPay Date 支付时间   
	 * @param status Integer 支付状态 default=0  {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
	 * @param itypePay Integer 支付方式 default=0  {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
	 * @param memberId Integer 会员   
	 * @param questionId Integer 一对一问题ID   
	* @param notuse String 没用
	 */
	public OrderrQuestionviewFinished(Date gmtPay ,Integer status ,Integer itypePay ,Integer memberId ,Integer questionId ,String notuse) {
		super();
		this.gmtPay = gmtPay;
		this.status = status;
		this.itypePay = itypePay;
		this.memberId = memberId;
		this.questionId = questionId;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param gmtPay Date 支付时间   
	 * @param status Integer 支付状态 default=0  {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
	 * @param itypePay Integer 支付方式 default=0  {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
	 * @param memberId Integer 会员   
	 * @param name String 姓名   
	 * @param mobile String 手机   
	 * @param questionId Integer 一对一问题ID   
	 * @param title String 问题  问题名 
	 * @param price Double 总价 default=0.0  
	* @param notuse String 没用
	 */
	public OrderrQuestionviewFinished(Integer id ,Date gmtPay ,Integer status ,Integer itypePay ,Integer memberId ,String name ,String mobile ,Integer questionId ,String title ,Double price ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.gmtPay = gmtPay;
		this.status = status;
		this.itypePay = itypePay;
		this.memberId = memberId;
		this.name = name;
		this.mobile = mobile;
		this.questionId = questionId;
		this.title = title;
		this.price = price;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param gmtCreate Date 创建时间   
	 * @param gmtPay Date 支付时间   
	 * @param status Integer 支付状态 default=0  {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
	 * @param itypePay Integer 支付方式 default=0  {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
	 * @param memberId Integer 会员   
	 * @param name String 姓名   
	 * @param mobile String 手机   
	 * @param questionId Integer 一对一问题ID   
	 * @param title String 问题  问题名 
	 * @param price Double 总价 default=0.0  
	 * @param paywxh5 String 微信支付H5对象   
	* @param notuse String 没用
	 */
	public OrderrQuestionviewFinished(Integer id ,Date gmtCreate ,Date gmtPay ,Integer status ,Integer itypePay ,Integer memberId ,String name ,String mobile ,Integer questionId ,String title ,Double price ,WaJsapiPaymentParam paywxh5 ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.gmtPay = gmtPay;
		this.status = status;
		this.itypePay = itypePay;
		this.memberId = memberId;
		this.name = name;
		this.mobile = mobile;
		this.questionId = questionId;
		this.title = title;
		this.price = price;
		this.paywxh5 = paywxh5;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+id+" "+title+" ":myname;
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
		mynameid=(mynameid==null)?"["+id+"]"+id+" "+title+" ":mynameid;
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
	

	@Id 
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
	/**获取支付时间  */
	

	@Column(name = "gmt_pay", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtPay() {
		return this.gmtPay;
	}
	/**设置支付时间  */

	public void setGmtPay(Date value) {
		this.gmtPay = value;
	}
	/**获取支付状态  {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}*/
	

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	/**设置支付状态  {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}*/

	public void setStatus(Integer value) {
		this.status = value;
	}
	/**获取支付方式  {'0':'余额支付','2':'微信支付','3':'支付宝支付'}*/
	

	@Column(name = "itype_pay", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getItypePay() {
		return this.itypePay;
	}
	/**设置支付方式  {'0':'余额支付','2':'微信支付','3':'支付宝支付'}*/

	public void setItypePay(Integer value) {
		this.itypePay = value;
	}
	/**获取会员  */
	

	@Column(name = "member_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getMemberId() {
		return this.memberId;
	}
	/**设置会员  */

	public void setMemberId(Integer value) {
		this.memberId = value;
	}
	/**获取姓名  */
	

	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getName() {
		return this.name;
	}
	/**设置姓名  */

	public void setName(String value) {
		this.name = value;
	}
	/**获取手机  */
	

	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getMobile() {
		return this.mobile;
	}
	/**设置手机  */

	public void setMobile(String value) {
		this.mobile = value;
	}
	/**获取一对一问题ID  */
	

	@Column(name = "question_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getQuestionId() {
		return this.questionId;
	}
	/**设置一对一问题ID  */

	public void setQuestionId(Integer value) {
		this.questionId = value;
	}
	/**获取问题 问题名 */
	

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getTitle() {
		return this.title;
	}
	/**设置问题 问题名 */

	public void setTitle(String value) {
		this.title = value;
	}
	/**获取总价  */
	

	@Column(name = "price", columnDefinition="double(11,3) default '0.00'", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Double getPrice() {
		return this.price;
	}
	/**设置总价  */

	public void setPrice(Double value) {
		this.price = value;
	}
	/**对象 获取微信支付H5对象  */
	@Transient
	

	public WaJsapiPaymentParam getPaywxh5() {
		return this.paywxh5;
	}
	/**设置微信支付H5对象  */

	public void setPaywxh5(WaJsapiPaymentParam value) {
		this.paywxh5 = value;
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
	/**支付时间String*/
	private String gmtPayString;
	/**获取支付时间String*/
	@Transient
	
	public String getGmtPayString() {
		if(gmtPayString==null && gmtPay!=null)
			gmtPayString=new SimpleDateFormat(DATE_FORMAT).format(gmtPay);
		return gmtPayString;
	}
	/**设置支付时间String*/
	public void setGmtPayString(String value) {
		this.gmtPayString=value;
	}
	/**支付状态String*/
	private String statusString;
	/**获取支付状态String*/
	@Transient
	
	public String getStatusString() {
		return statusString;
	}
	/**设置支付状态String*/
	public void setStatusString(String value) {
		this.statusString=value;
	}
	/**支付状态Map*/
	private Map<String, Object> statusMap;
	/**获取支付状态Map*/
	@Transient
	
	public Map<String, Object> getStatusMap() {
		return statusMap;
	}
	/**设置支付状态Map*/
	public void setStatusMap(Map<String, Object> value) {
		this.statusMap=value;
	}
	/**支付方式String*/
	private String itypePayString;
	/**获取支付方式String*/
	@Transient
	
	public String getItypePayString() {
		return itypePayString;
	}
	/**设置支付方式String*/
	public void setItypePayString(String value) {
		this.itypePayString=value;
	}
	/**支付方式Map*/
	private Map<String, Object> itypePayMap;
	/**获取支付方式Map*/
	@Transient
	
	public Map<String, Object> getItypePayMap() {
		return itypePayMap;
	}
	/**设置支付方式Map*/
	public void setItypePayMap(Map<String, Object> value) {
		this.itypePayMap=value;
	}
	/**会员String*/
	private String memberIdString;
	/**获取会员String*/
	@Transient
	
	public String getMemberIdString() {
		return memberIdString;
	}
	/**设置会员String*/
	public void setMemberIdString(String value) {
		this.memberIdString=value;
	}
	/**Member - memberIdMemberObj*/
	private Member memberIdMemberObj;
	/**获取 Member - memberIdMemberObj*/
	@Transient
	
	public Member getMemberIdMemberObj() {
		return memberIdMemberObj;
	}
	/**设置 Member - 会员Obj*/
	public void setMemberIdMemberObj(Member value) {
		this.memberIdMemberObj=value;
	}
	/**会员Stringid*/
	private String memberIdStringid;
	/**获取会员Stringid*/
	@Transient
	
	public String getMemberIdStringid() {
		return memberIdStringid;
	}
	/**设置会员Stringid*/
	public void setMemberIdStringid(String value) {
		this.memberIdStringid=value;
	}
	/**一对一问题IDString*/
	private String questionIdString;
	/**获取一对一问题IDString*/
	@Transient
	
	public String getQuestionIdString() {
		return questionIdString;
	}
	/**设置一对一问题IDString*/
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
	/**设置 Question - 一对一问题IDObj*/
	public void setQuestionIdQuestionObj(Question value) {
		this.questionIdQuestionObj=value;
	}
	/**一对一问题IDStringid*/
	private String questionIdStringid;
	/**获取一对一问题IDStringid*/
	@Transient
	
	public String getQuestionIdStringid() {
		return questionIdStringid;
	}
	/**设置一对一问题IDStringid*/
	public void setQuestionIdStringid(String value) {
		this.questionIdStringid=value;
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
		this.itypePayMap=null;
		this.memberIdMemberObj=null; //Member
		this.questionIdQuestionObj=null; //Question
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
			.append(getGmtPay())
			.append(getStatus())
			.append(getItypePay())
			.append(getMemberId())
			.append(getName())
			.append(getMobile())
			.append(getQuestionId())
			.append(getTitle())
			.append(getPrice())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof OrderrQuestionviewFinished == false) return false;
		if(this == obj) return true;
		OrderrQuestionviewFinished other = (OrderrQuestionviewFinished)obj;
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
			.append(",GmtPay:",getGmtPay())
			.append(",Status:",getStatus())
			.append(",ItypePay:",getItypePay())
			.append(",MemberId:",getMemberId())
			.append(",Name:",getName())
			.append(",Mobile:",getMobile())
			.append(",QuestionId:",getQuestionId())
			.append(",Title:",getTitle())
			.append(",Price:",getPrice())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","gmtPayString","statusString","statusMap","itypePayString","itypePayMap","memberIdString","memberIdMemberObj","memberIdStringid","questionIdString","questionIdQuestionObj","questionIdStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","gmtPayString","statusString","statusMap","itypePayString","itypePayMap","memberIdString","memberIdMemberObj","memberIdStringid","questionIdString","questionIdQuestionObj","questionIdStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 订单之一对一问题观看归档
OrderrQuestionviewFinished orderrQuestionviewFinished = new OrderrQuestionviewFinished(
	gmtPay , //Date 支付时间   
	status , //Integer 支付状态 default=0  {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
	itypePay , //Integer 支付方式 default=0  {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
	memberId , //Integer 会员   
	questionId , //Integer 一对一问题ID   
	null
);
*/
}
