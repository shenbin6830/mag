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
 * 积分流水
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "score_his")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class ScoreHis extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "积分流水";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**序号ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**会员 Integer   */
	@Max(99999999999L)
	
	private Integer memberId;
	/**类型 Integer default=0 对会员来说正是得积分，负是失积分 map={'-5':'抢答看','-4':'一对一看','-3':'抢答提问','-2':'一对一提问','-1':'提现','1':'充值','2':'回答一对一','3':'回答抢答'}*/
	@Max(99999999999L)
	
	private Integer otype = 0;
	/**数量 Integer default=0 1积分=0.1元，有正负，正是得，负是失 */
	@Max(99999999999L)
	
	private Integer num = 0;
	/**订单的id Integer default=0 根据类型，指向不同的订单表 */
	@Max(99999999999L)
	
	private Integer oid = 0;
	/**备注说明 String   */
	@Length(max=200)
	
	private String cmemo;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(ScoreHis _this){
		_this.gmtCreate=new Date();
		_this.otype=0;
		_this.num=0;
		_this.oid=0;
	}
	public ScoreHis(){
		makedefault(this);
	}
	public ScoreHis(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param memberId Integer 会员   
	 * @param otype Integer 类型 default=0 对会员来说正是得积分，负是失积分 {'-5':'抢答看','-4':'一对一看','-3':'抢答提问','-2':'一对一提问','-1':'提现','1':'充值','2':'回答一对一','3':'回答抢答'}
	 * @param num Integer 数量 default=0 1积分=0.1元，有正负，正是得，负是失 
	 * @param oid Integer 订单的id default=0 根据类型，指向不同的订单表 
	 * @param cmemo String 备注说明   
	* @param notuse String 没用
	 */
	public ScoreHis(Integer memberId ,Integer otype ,Integer num ,Integer oid ,String cmemo ,String notuse) {
		super();
		this.memberId = memberId;
		this.otype = otype;
		this.num = num;
		this.oid = oid;
		this.cmemo = cmemo;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param memberId Integer 会员   
	 * @param otype Integer 类型 default=0 对会员来说正是得积分，负是失积分 {'-5':'抢答看','-4':'一对一看','-3':'抢答提问','-2':'一对一提问','-1':'提现','1':'充值','2':'回答一对一','3':'回答抢答'}
	 * @param num Integer 数量 default=0 1积分=0.1元，有正负，正是得，负是失 
	 * @param oid Integer 订单的id default=0 根据类型，指向不同的订单表 
	 * @param cmemo String 备注说明   
	* @param notuse String 没用
	 */
	public ScoreHis(Integer id ,Integer memberId ,Integer otype ,Integer num ,Integer oid ,String cmemo ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.memberId = memberId;
		this.otype = otype;
		this.num = num;
		this.oid = oid;
		this.cmemo = cmemo;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param gmtCreate Date 创建时间   
	 * @param memberId Integer 会员   
	 * @param otype Integer 类型 default=0 对会员来说正是得积分，负是失积分 {'-5':'抢答看','-4':'一对一看','-3':'抢答提问','-2':'一对一提问','-1':'提现','1':'充值','2':'回答一对一','3':'回答抢答'}
	 * @param num Integer 数量 default=0 1积分=0.1元，有正负，正是得，负是失 
	 * @param oid Integer 订单的id default=0 根据类型，指向不同的订单表 
	 * @param cmemo String 备注说明   
	* @param notuse String 没用
	 */
	public ScoreHis(Integer id ,Date gmtCreate ,Integer memberId ,Integer otype ,Integer num ,Integer oid ,String cmemo ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.memberId = memberId;
		this.otype = otype;
		this.num = num;
		this.oid = oid;
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
	/**获取会员  */
	

	@Column(name = "member_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getMemberId() {
		return this.memberId;
	}
	/**设置会员  */

	public void setMemberId(Integer value) {
		this.memberId = value;
	}
	/**获取类型 对会员来说正是得积分，负是失积分 {'-5':'抢答看','-4':'一对一看','-3':'抢答提问','-2':'一对一提问','-1':'提现','1':'充值','2':'回答一对一','3':'回答抢答'}*/
	

	@Column(name = "otype", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getOtype() {
		return this.otype;
	}
	/**设置类型 对会员来说正是得积分，负是失积分 {'-5':'抢答看','-4':'一对一看','-3':'抢答提问','-2':'一对一提问','-1':'提现','1':'充值','2':'回答一对一','3':'回答抢答'}*/

	public void setOtype(Integer value) {
		this.otype = value;
	}
	/**获取数量 1积分=0.1元，有正负，正是得，负是失 */
	

	@Column(name = "num", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getNum() {
		return this.num;
	}
	/**设置数量 1积分=0.1元，有正负，正是得，负是失 */

	public void setNum(Integer value) {
		this.num = value;
	}
	/**获取订单的id 根据类型，指向不同的订单表 */
	

	@Column(name = "oid", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getOid() {
		return this.oid;
	}
	/**设置订单的id 根据类型，指向不同的订单表 */

	public void setOid(Integer value) {
		this.oid = value;
	}
	/**获取备注说明  */
	

	@Column(name = "cmemo", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getCmemo() {
		return this.cmemo;
	}
	/**设置备注说明  */

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
	/**类型String*/
	private String otypeString;
	/**获取类型String*/
	@Transient
	
	public String getOtypeString() {
		return otypeString;
	}
	/**设置类型String*/
	public void setOtypeString(String value) {
		this.otypeString=value;
	}
	/**类型Map*/
	private Map<String, Object> otypeMap;
	/**获取类型Map*/
	@Transient
	
	public Map<String, Object> getOtypeMap() {
		return otypeMap;
	}
	/**设置类型Map*/
	public void setOtypeMap(Map<String, Object> value) {
		this.otypeMap=value;
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
		this.memberIdMemberObj=null; //Member
		this.otypeMap=null;
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
			.append(getMemberId())
			.append(getOtype())
			.append(getNum())
			.append(getOid())
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
		if(obj instanceof ScoreHis == false) return false;
		if(this == obj) return true;
		ScoreHis other = (ScoreHis)obj;
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
			.append(",MemberId:",getMemberId())
			.append(",Otype:",getOtype())
			.append(",Num:",getNum())
			.append(",Oid:",getOid())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","memberIdString","memberIdMemberObj","memberIdStringid","otypeString","otypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","memberIdString","memberIdMemberObj","memberIdStringid","otypeString","otypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 积分流水
ScoreHis scoreHis = new ScoreHis(
	memberId , //Integer 会员   
	otype , //Integer 类型 default=0 对会员来说正是得积分，负是失积分 {'-5':'抢答看','-4':'一对一看','-3':'抢答提问','-2':'一对一提问','-1':'提现','1':'充值','2':'回答一对一','3':'回答抢答'}
	num , //Integer 数量 default=0 1积分=0.1元，有正负，正是得，负是失 
	oid , //Integer 订单的id default=0 根据类型，指向不同的订单表 
	cmemo , //String 备注说明   
	null
);
*/
}
