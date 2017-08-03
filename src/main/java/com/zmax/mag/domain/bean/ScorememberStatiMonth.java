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
 * 会员积分月统计<br/>日统计完成后，如果是1号，做月统计
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "scoremember_stati_month")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class ScorememberStatiMonth extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "会员积分月统计";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	public static final String FORMAT_GMT_STATI = DATE_FORMAT;
	
	//columns START
	/**序号ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date  计算的时间 */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**被统计月 Date  >=每月1日0:0，<下月1日0:0 */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtStati;
	/**会员 Integer   */
	@Max(99999999999L)
	
	private Integer memberId;
	/**本月增减数量 Integer default=0 正负，正是增加，负是减少，零也创建记录 */
	@Max(99999999999L)
	
	private Integer num = 0;
	/**月初数 Integer default=0 1日0:0时的分数，月初数+本月增减分数=月末数 */
	@Max(99999999999L)
	
	private Integer nstart = 0;
	/**月末数 Integer default=0 下月1日0:0时的分数 */
	@Max(99999999999L)
	
	private Integer nend = 0;
	/**备注说明 String   */
	@Length(max=200)
	
	private String cmemo;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(ScorememberStatiMonth _this){
		_this.gmtCreate=new Date();
		_this.num=0;
		_this.nstart=0;
		_this.nend=0;
	}
	public ScorememberStatiMonth(){
		makedefault(this);
	}
	public ScorememberStatiMonth(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param gmtStati Date 被统计月  >=每月1日0:0，<下月1日0:0 
	 * @param memberId Integer 会员   
	 * @param num Integer 本月增减数量 default=0 正负，正是增加，负是减少，零也创建记录 
	 * @param nstart Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
	 * @param nend Integer 月末数 default=0 下月1日0:0时的分数 
	 * @param cmemo String 备注说明   
	* @param notuse String 没用
	 */
	public ScorememberStatiMonth(Date gmtStati ,Integer memberId ,Integer num ,Integer nstart ,Integer nend ,String cmemo ,String notuse) {
		super();
		this.gmtStati = gmtStati;
		this.memberId = memberId;
		this.num = num;
		this.nstart = nstart;
		this.nend = nend;
		this.cmemo = cmemo;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param gmtStati Date 被统计月  >=每月1日0:0，<下月1日0:0 
	 * @param memberId Integer 会员   
	 * @param num Integer 本月增减数量 default=0 正负，正是增加，负是减少，零也创建记录 
	 * @param nstart Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
	 * @param nend Integer 月末数 default=0 下月1日0:0时的分数 
	 * @param cmemo String 备注说明   
	* @param notuse String 没用
	 */
	public ScorememberStatiMonth(Integer id ,Date gmtStati ,Integer memberId ,Integer num ,Integer nstart ,Integer nend ,String cmemo ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.gmtStati = gmtStati;
		this.memberId = memberId;
		this.num = num;
		this.nstart = nstart;
		this.nend = nend;
		this.cmemo = cmemo;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param gmtCreate Date 创建时间  计算的时间 
	 * @param gmtStati Date 被统计月  >=每月1日0:0，<下月1日0:0 
	 * @param memberId Integer 会员   
	 * @param num Integer 本月增减数量 default=0 正负，正是增加，负是减少，零也创建记录 
	 * @param nstart Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
	 * @param nend Integer 月末数 default=0 下月1日0:0时的分数 
	 * @param cmemo String 备注说明   
	* @param notuse String 没用
	 */
	public ScorememberStatiMonth(Integer id ,Date gmtCreate ,Date gmtStati ,Integer memberId ,Integer num ,Integer nstart ,Integer nend ,String cmemo ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.gmtStati = gmtStati;
		this.memberId = memberId;
		this.num = num;
		this.nstart = nstart;
		this.nend = nend;
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
	
	/**获取创建时间 计算的时间 */
	

	@Column(name = "gmt_create", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtCreate() {
		return this.gmtCreate;
	}
	/**设置创建时间 计算的时间 */

	public void setGmtCreate(Date value) {
		this.gmtCreate = value;
	}
	/**获取被统计月 >=每月1日0:0，<下月1日0:0 */
	

	@Column(name = "gmt_stati", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtStati() {
		return this.gmtStati;
	}
	/**设置被统计月 >=每月1日0:0，<下月1日0:0 */

	public void setGmtStati(Date value) {
		this.gmtStati = value;
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
	/**获取本月增减数量 正负，正是增加，负是减少，零也创建记录 */
	

	@Column(name = "num", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getNum() {
		return this.num;
	}
	/**设置本月增减数量 正负，正是增加，负是减少，零也创建记录 */

	public void setNum(Integer value) {
		this.num = value;
	}
	/**获取月初数 1日0:0时的分数，月初数+本月增减分数=月末数 */
	

	@Column(name = "nstart", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getNstart() {
		return this.nstart;
	}
	/**设置月初数 1日0:0时的分数，月初数+本月增减分数=月末数 */

	public void setNstart(Integer value) {
		this.nstart = value;
	}
	/**获取月末数 下月1日0:0时的分数 */
	

	@Column(name = "nend", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getNend() {
		return this.nend;
	}
	/**设置月末数 下月1日0:0时的分数 */

	public void setNend(Integer value) {
		this.nend = value;
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
	/**被统计月String*/
	private String gmtStatiString;
	/**获取被统计月String*/
	@Transient
	
	public String getGmtStatiString() {
		if(gmtStatiString==null && gmtStati!=null)
			gmtStatiString=new SimpleDateFormat(DATE_FORMAT).format(gmtStati);
		return gmtStatiString;
	}
	/**设置被统计月String*/
	public void setGmtStatiString(String value) {
		this.gmtStatiString=value;
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
			.append(getGmtStati())
			.append(getMemberId())
			.append(getNum())
			.append(getNstart())
			.append(getNend())
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
		if(obj instanceof ScorememberStatiMonth == false) return false;
		if(this == obj) return true;
		ScorememberStatiMonth other = (ScorememberStatiMonth)obj;
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
			.append(",GmtStati:",getGmtStati())
			.append(",MemberId:",getMemberId())
			.append(",Num:",getNum())
			.append(",Nstart:",getNstart())
			.append(",Nend:",getNend())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","gmtStatiString","memberIdString","memberIdMemberObj","memberIdStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","gmtStatiString","memberIdString","memberIdMemberObj","memberIdStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 会员积分月统计
ScorememberStatiMonth scorememberStatiMonth = new ScorememberStatiMonth(
	gmtStati , //Date 被统计月  >=每月1日0:0，<下月1日0:0 
	memberId , //Integer 会员   
	num , //Integer 本月增减数量 default=0 正负，正是增加，负是减少，零也创建记录 
	nstart , //Integer 月初数 default=0 1日0:0时的分数，月初数+本月增减分数=月末数 
	nend , //Integer 月末数 default=0 下月1日0:0时的分数 
	cmemo , //String 备注说明   
	null
);
*/
}
