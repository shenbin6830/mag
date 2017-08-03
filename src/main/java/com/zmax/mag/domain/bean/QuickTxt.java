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
 * 抢答回答
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "quick_txt")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class QuickTxt extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "抢答回答";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**序号 Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**问题内序号 Integer   */
	@Max(99999999999L)
	
	private Integer quickId;
	/**中奖状态 Integer default=0  map={'0':'没中奖','1':'中奖'}*/
	@Max(999L)
	
	private Integer statusWin = 0;
	/**提问者 Integer default=0  */
	@Max(99999999999L)
	
	private Integer memberQu = 0;
	/**回答者 Integer default=0  */
	@Max(99999999999L)
	
	private Integer memberAn = 0;
	/**好评人数 Integer default=0 计算产生 */
	@Max(99999999999L)
	
	private Integer goodnum = 0;
	/**差评人数 Integer default=0 计算产生 */
	@Max(99999999999L)
	
	private Integer badnum = 0;
	/**提问者评价 Integer   map={'0':'以后再评','1':'好评','-1':'差评'}*/
	@Max(999L)
	
	private Integer ctype;
	/**回答 String  限2000汉字 */
	@Length(max=4000)
	
	private String answer;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(QuickTxt _this){
		_this.gmtCreate=new Date();
		_this.statusWin=0;
		_this.memberQu=0;
		_this.memberAn=0;
		_this.goodnum=0;
		_this.badnum=0;
	}
	public QuickTxt(){
		makedefault(this);
	}
	public QuickTxt(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param quickId Integer 问题内序号   
	 * @param statusWin Integer 中奖状态 default=0  {'0':'没中奖','1':'中奖'}
	 * @param memberAn Integer 回答者 default=0  
	 * @param goodnum Integer 好评人数 default=0 计算产生 
	 * @param badnum Integer 差评人数 default=0 计算产生 
	 * @param ctype Integer 提问者评价   {'0':'以后再评','1':'好评','-1':'差评'}
	 * @param answer String 回答  限2000汉字 
	* @param notuse String 没用
	 */
	public QuickTxt(Integer quickId ,Integer statusWin ,Integer memberAn ,Integer goodnum ,Integer badnum ,Integer ctype ,String answer ,String notuse) {
		super();
		this.quickId = quickId;
		this.statusWin = statusWin;
		this.memberAn = memberAn;
		this.goodnum = goodnum;
		this.badnum = badnum;
		this.ctype = ctype;
		this.answer = answer;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param quickId Integer 问题内序号   
	 * @param statusWin Integer 中奖状态 default=0  {'0':'没中奖','1':'中奖'}
	 * @param memberQu Integer 提问者 default=0  
	 * @param memberAn Integer 回答者 default=0  
	 * @param goodnum Integer 好评人数 default=0 计算产生 
	 * @param badnum Integer 差评人数 default=0 计算产生 
	 * @param ctype Integer 提问者评价   {'0':'以后再评','1':'好评','-1':'差评'}
	 * @param answer String 回答  限2000汉字 
	* @param notuse String 没用
	 */
	public QuickTxt(Integer id ,Integer quickId ,Integer statusWin ,Integer memberQu ,Integer memberAn ,Integer goodnum ,Integer badnum ,Integer ctype ,String answer ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.quickId = quickId;
		this.statusWin = statusWin;
		this.memberQu = memberQu;
		this.memberAn = memberAn;
		this.goodnum = goodnum;
		this.badnum = badnum;
		this.ctype = ctype;
		this.answer = answer;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param gmtCreate Date 创建时间   
	 * @param quickId Integer 问题内序号   
	 * @param statusWin Integer 中奖状态 default=0  {'0':'没中奖','1':'中奖'}
	 * @param memberQu Integer 提问者 default=0  
	 * @param memberAn Integer 回答者 default=0  
	 * @param goodnum Integer 好评人数 default=0 计算产生 
	 * @param badnum Integer 差评人数 default=0 计算产生 
	 * @param ctype Integer 提问者评价   {'0':'以后再评','1':'好评','-1':'差评'}
	 * @param answer String 回答  限2000汉字 
	* @param notuse String 没用
	 */
	public QuickTxt(Integer id ,Date gmtCreate ,Integer quickId ,Integer statusWin ,Integer memberQu ,Integer memberAn ,Integer goodnum ,Integer badnum ,Integer ctype ,String answer ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.quickId = quickId;
		this.statusWin = statusWin;
		this.memberQu = memberQu;
		this.memberAn = memberAn;
		this.goodnum = goodnum;
		this.badnum = badnum;
		this.ctype = ctype;
		this.answer = answer;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+answer+" ":myname;
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
		mynameid=(mynameid==null)?"["+id+"]"+answer+" ":mynameid;
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
	/**获取问题内序号  */
	

	@Column(name = "quick_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getQuickId() {
		return this.quickId;
	}
	/**设置问题内序号  */

	public void setQuickId(Integer value) {
		this.quickId = value;
	}
	/**获取中奖状态  {'0':'没中奖','1':'中奖'}*/
	

	@Column(name = "status_win", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatusWin() {
		return this.statusWin;
	}
	/**设置中奖状态  {'0':'没中奖','1':'中奖'}*/

	public void setStatusWin(Integer value) {
		this.statusWin = value;
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
	/**获取好评人数 计算产生 */
	

	@Column(name = "goodnum", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getGoodnum() {
		return this.goodnum;
	}
	/**设置好评人数 计算产生 */

	public void setGoodnum(Integer value) {
		this.goodnum = value;
	}
	/**获取差评人数 计算产生 */
	

	@Column(name = "badnum", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getBadnum() {
		return this.badnum;
	}
	/**设置差评人数 计算产生 */

	public void setBadnum(Integer value) {
		this.badnum = value;
	}
	/**获取提问者评价  {'0':'以后再评','1':'好评','-1':'差评'}*/
	

	@Column(name = "ctype", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getCtype() {
		return this.ctype;
	}
	/**设置提问者评价  {'0':'以后再评','1':'好评','-1':'差评'}*/

	public void setCtype(Integer value) {
		this.ctype = value;
	}
	/**获取回答 限2000汉字 */
	

	@Column(name = "answer", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getAnswer() {
		return this.answer;
	}
	/**设置回答 限2000汉字 */

	public void setAnswer(String value) {
		this.answer = value;
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
	/**问题内序号String*/
	private String quickIdString;
	/**获取问题内序号String*/
	@Transient
	
	public String getQuickIdString() {
		return quickIdString;
	}
	/**设置问题内序号String*/
	public void setQuickIdString(String value) {
		this.quickIdString=value;
	}
	/**Quick - quickIdQuickObj*/
	private Quick quickIdQuickObj;
	/**获取 Quick - quickIdQuickObj*/
	@Transient
	
	public Quick getQuickIdQuickObj() {
		return quickIdQuickObj;
	}
	/**设置 Quick - 问题内序号Obj*/
	public void setQuickIdQuickObj(Quick value) {
		this.quickIdQuickObj=value;
	}
	/**问题内序号Stringid*/
	private String quickIdStringid;
	/**获取问题内序号Stringid*/
	@Transient
	
	public String getQuickIdStringid() {
		return quickIdStringid;
	}
	/**设置问题内序号Stringid*/
	public void setQuickIdStringid(String value) {
		this.quickIdStringid=value;
	}
	/**中奖状态String*/
	private String statusWinString;
	/**获取中奖状态String*/
	@Transient
	
	public String getStatusWinString() {
		return statusWinString;
	}
	/**设置中奖状态String*/
	public void setStatusWinString(String value) {
		this.statusWinString=value;
	}
	/**中奖状态Map*/
	private Map<String, Object> statusWinMap;
	/**获取中奖状态Map*/
	@Transient
	
	public Map<String, Object> getStatusWinMap() {
		return statusWinMap;
	}
	/**设置中奖状态Map*/
	public void setStatusWinMap(Map<String, Object> value) {
		this.statusWinMap=value;
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
	/**提问者评价String*/
	private String ctypeString;
	/**获取提问者评价String*/
	@Transient
	
	public String getCtypeString() {
		return ctypeString;
	}
	/**设置提问者评价String*/
	public void setCtypeString(String value) {
		this.ctypeString=value;
	}
	/**提问者评价Map*/
	private Map<String, Object> ctypeMap;
	/**获取提问者评价Map*/
	@Transient
	
	public Map<String, Object> getCtypeMap() {
		return ctypeMap;
	}
	/**设置提问者评价Map*/
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
		this.quickIdQuickObj=null; //Quick
		this.statusWinMap=null;
		this.memberQuMemberObj=null; //Member
		this.memberAnMemberObj=null; //Member
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
			.append(getQuickId())
			.append(getStatusWin())
			.append(getMemberQu())
			.append(getMemberAn())
			.append(getGoodnum())
			.append(getBadnum())
			.append(getCtype())
			.append(getAnswer())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof QuickTxt == false) return false;
		if(this == obj) return true;
		QuickTxt other = (QuickTxt)obj;
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
			.append(",QuickId:",getQuickId())
			.append(",StatusWin:",getStatusWin())
			.append(",MemberQu:",getMemberQu())
			.append(",MemberAn:",getMemberAn())
			.append(",Goodnum:",getGoodnum())
			.append(",Badnum:",getBadnum())
			.append(",Ctype:",getCtype())
			.append(",Answer:",getAnswer())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","quickIdString","quickIdQuickObj","quickIdStringid","statusWinString","statusWinMap","memberQuString","memberQuMemberObj","memberQuStringid","memberAnString","memberAnMemberObj","memberAnStringid","ctypeString","ctypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","quickIdString","quickIdQuickObj","quickIdStringid","statusWinString","statusWinMap","memberQuString","memberQuMemberObj","memberQuStringid","memberAnString","memberAnMemberObj","memberAnStringid","ctypeString","ctypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 抢答回答
QuickTxt quickTxt = new QuickTxt(
	quickId , //Integer 问题内序号   
	statusWin , //Integer 中奖状态 default=0  {'0':'没中奖','1':'中奖'}
	memberAn , //Integer 回答者 default=0  
	goodnum , //Integer 好评人数 default=0 计算产生 
	badnum , //Integer 差评人数 default=0 计算产生 
	ctype , //Integer 提问者评价   {'0':'以后再评','1':'好评','-1':'差评'}
	answer , //String 回答  限2000汉字 
	null
);
*/
}
