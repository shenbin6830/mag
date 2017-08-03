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
 * 抢答
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "quick")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Quick extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "抢答";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	public static final String FORMAT_GMT_MODIFIED = DATE_FORMAT;
	public static final String FORMAT_GMT_PAY = DATE_FORMAT;
	public static final String FORMAT_GMT_START = DATE_FORMAT;
	public static final String FORMAT_GMT_OVER = DATE_FORMAT;
	
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
	/**支付时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtPay;
	/**开始时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtStart;
	/**结束时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtOver;
	/**状态 Integer default=0  map={'0':'未支付','1':'已支付','2':'开始抢答','3':'抢答结束'}*/
	@Max(999L)
	
	private Integer status = 0;
	/**公开状态 Integer default=0  map={'0':'收费公开','1':'不公开'}*/
	@Max(999L)
	
	private Integer ptype = 0;
	/**排列顺序 Integer default=99 数字越小排名越前 */
	@Max(99999999999L)
	
	private Integer priority = 99;
	/**频道内序号 Integer   */
	@Max(99999999999L)
	
	private Integer articlechannelId;
	/**提问者 Integer default=0  */
	@Max(99999999999L)
	
	private Integer memberQu = 0;
	/**每个奖金 Double default=0.0  */
	
	
	private Double priceeach;
	/**奖金数量 Integer default=0  */
	@Max(99999999999L)
	
	private Integer pricenum = 0;
	/**总奖金 Double default=0.0  */
	
	
	private Double price;
	/**观看费 Double default=0.0  */
	
	
	private Double viewprice;
	/**回答人数 Integer default=0 计算产生 */
	@Max(99999999999L)
	
	private Integer answernum = 0;
	/**观看人数 Integer default=0 计算产生 */
	@Max(99999999999L)
	
	private Integer viewnum = 0;
	/**标题 String   */
	@Length(max=40)
	
	private String title;
	/**问题 String   */
	@Length(max=1000)
	
	private String question;
	/**能看不*/
	private boolean canread;
	/**答案列表*/
	private List<QuickTxt> listQuickTxt;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Quick _this){
		_this.gmtCreate=new Date();
		_this.status=0;
		_this.ptype=0;
		_this.priority=99;
		_this.memberQu=0;
		_this.priceeach=0.0;
		_this.pricenum=0;
		_this.price=0.0;
		_this.viewprice=0.0;
		_this.answernum=0;
		_this.viewnum=0;
	}
	public Quick(){
		makedefault(this);
	}
	public Quick(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param gmtPay Date 支付时间   
	 * @param gmtStart Date 开始时间   
	 * @param gmtOver Date 结束时间   
	 * @param status Integer 状态 default=0  {'0':'未支付','1':'已支付','2':'开始抢答','3':'抢答结束'}
	 * @param ptype Integer 公开状态 default=0  {'0':'收费公开','1':'不公开'}
	 * @param priority Integer 排列顺序 default=99 数字越小排名越前 
	 * @param articlechannelId Integer 频道内序号   
	 * @param memberQu Integer 提问者 default=0  
	 * @param priceeach Double 每个奖金 default=0.0  
	 * @param pricenum Integer 奖金数量 default=0  
	 * @param viewprice Double 观看费 default=0.0  
	 * @param answernum Integer 回答人数 default=0 计算产生 
	 * @param viewnum Integer 观看人数 default=0 计算产生 
	 * @param title String 标题   
	 * @param question String 问题   
	* @param notuse String 没用
	 */
	public Quick(Date gmtPay ,Date gmtStart ,Date gmtOver ,Integer status ,Integer ptype ,Integer priority ,Integer articlechannelId ,Integer memberQu ,Double priceeach ,Integer pricenum ,Double viewprice ,Integer answernum ,Integer viewnum ,String title ,String question ,String notuse) {
		super();
		this.gmtPay = gmtPay;
		this.gmtStart = gmtStart;
		this.gmtOver = gmtOver;
		this.status = status;
		this.ptype = ptype;
		this.priority = priority;
		this.articlechannelId = articlechannelId;
		this.memberQu = memberQu;
		this.priceeach = priceeach;
		this.pricenum = pricenum;
		this.viewprice = viewprice;
		this.answernum = answernum;
		this.viewnum = viewnum;
		this.title = title;
		this.question = question;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param gmtModified Date 修改时间   
	 * @param gmtPay Date 支付时间   
	 * @param gmtStart Date 开始时间   
	 * @param gmtOver Date 结束时间   
	 * @param status Integer 状态 default=0  {'0':'未支付','1':'已支付','2':'开始抢答','3':'抢答结束'}
	 * @param ptype Integer 公开状态 default=0  {'0':'收费公开','1':'不公开'}
	 * @param priority Integer 排列顺序 default=99 数字越小排名越前 
	 * @param articlechannelId Integer 频道内序号   
	 * @param memberQu Integer 提问者 default=0  
	 * @param priceeach Double 每个奖金 default=0.0  
	 * @param pricenum Integer 奖金数量 default=0  
	 * @param price Double 总奖金 default=0.0  
	 * @param viewprice Double 观看费 default=0.0  
	 * @param answernum Integer 回答人数 default=0 计算产生 
	 * @param viewnum Integer 观看人数 default=0 计算产生 
	 * @param title String 标题   
	 * @param question String 问题   
	* @param notuse String 没用
	 */
	public Quick(Integer id ,Date gmtModified ,Date gmtPay ,Date gmtStart ,Date gmtOver ,Integer status ,Integer ptype ,Integer priority ,Integer articlechannelId ,Integer memberQu ,Double priceeach ,Integer pricenum ,Double price ,Double viewprice ,Integer answernum ,Integer viewnum ,String title ,String question ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.gmtModified = gmtModified;
		this.gmtPay = gmtPay;
		this.gmtStart = gmtStart;
		this.gmtOver = gmtOver;
		this.status = status;
		this.ptype = ptype;
		this.priority = priority;
		this.articlechannelId = articlechannelId;
		this.memberQu = memberQu;
		this.priceeach = priceeach;
		this.pricenum = pricenum;
		this.price = price;
		this.viewprice = viewprice;
		this.answernum = answernum;
		this.viewnum = viewnum;
		this.title = title;
		this.question = question;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param gmtCreate Date 创建时间   
	 * @param gmtModified Date 修改时间   
	 * @param gmtPay Date 支付时间   
	 * @param gmtStart Date 开始时间   
	 * @param gmtOver Date 结束时间   
	 * @param status Integer 状态 default=0  {'0':'未支付','1':'已支付','2':'开始抢答','3':'抢答结束'}
	 * @param ptype Integer 公开状态 default=0  {'0':'收费公开','1':'不公开'}
	 * @param priority Integer 排列顺序 default=99 数字越小排名越前 
	 * @param articlechannelId Integer 频道内序号   
	 * @param memberQu Integer 提问者 default=0  
	 * @param priceeach Double 每个奖金 default=0.0  
	 * @param pricenum Integer 奖金数量 default=0  
	 * @param price Double 总奖金 default=0.0  
	 * @param viewprice Double 观看费 default=0.0  
	 * @param answernum Integer 回答人数 default=0 计算产生 
	 * @param viewnum Integer 观看人数 default=0 计算产生 
	 * @param title String 标题   
	 * @param question String 问题   
	 * @param canread Boolean 能看不   
	 * @param listQuickTxt String 答案列表   
	* @param notuse String 没用
	 */
	public Quick(Integer id ,Date gmtCreate ,Date gmtModified ,Date gmtPay ,Date gmtStart ,Date gmtOver ,Integer status ,Integer ptype ,Integer priority ,Integer articlechannelId ,Integer memberQu ,Double priceeach ,Integer pricenum ,Double price ,Double viewprice ,Integer answernum ,Integer viewnum ,String title ,String question ,boolean canread ,List<QuickTxt> listQuickTxt ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.gmtPay = gmtPay;
		this.gmtStart = gmtStart;
		this.gmtOver = gmtOver;
		this.status = status;
		this.ptype = ptype;
		this.priority = priority;
		this.articlechannelId = articlechannelId;
		this.memberQu = memberQu;
		this.priceeach = priceeach;
		this.pricenum = pricenum;
		this.price = price;
		this.viewprice = viewprice;
		this.answernum = answernum;
		this.viewnum = viewnum;
		this.title = title;
		this.question = question;
		this.canread = canread;
		this.listQuickTxt = listQuickTxt;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+title+" ":myname;
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
		mynameid=(mynameid==null)?"["+id+"]"+title+" ":mynameid;
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
	/**获取支付时间  */
	

	@Column(name = "gmt_pay", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtPay() {
		return this.gmtPay;
	}
	/**设置支付时间  */

	public void setGmtPay(Date value) {
		this.gmtPay = value;
	}
	/**获取开始时间  */
	

	@Column(name = "gmt_start", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtStart() {
		return this.gmtStart;
	}
	/**设置开始时间  */

	public void setGmtStart(Date value) {
		this.gmtStart = value;
	}
	/**获取结束时间  */
	

	@Column(name = "gmt_over", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtOver() {
		return this.gmtOver;
	}
	/**设置结束时间  */

	public void setGmtOver(Date value) {
		this.gmtOver = value;
	}
	/**获取状态  {'0':'未支付','1':'已支付','2':'开始抢答','3':'抢答结束'}*/
	

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	/**设置状态  {'0':'未支付','1':'已支付','2':'开始抢答','3':'抢答结束'}*/

	public void setStatus(Integer value) {
		this.status = value;
	}
	/**获取公开状态  {'0':'收费公开','1':'不公开'}*/
	

	@Column(name = "ptype", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getPtype() {
		return this.ptype;
	}
	/**设置公开状态  {'0':'收费公开','1':'不公开'}*/

	public void setPtype(Integer value) {
		this.ptype = value;
	}
	/**获取排列顺序 数字越小排名越前 */
	

	@Column(name = "priority", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getPriority() {
		return this.priority;
	}
	/**设置排列顺序 数字越小排名越前 */

	public void setPriority(Integer value) {
		this.priority = value;
	}
	/**获取频道内序号  */
	

	@Column(name = "articlechannel_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getArticlechannelId() {
		return this.articlechannelId;
	}
	/**设置频道内序号  */

	public void setArticlechannelId(Integer value) {
		this.articlechannelId = value;
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
	/**获取每个奖金  */
	

	@Column(name = "priceeach", columnDefinition="double(11,3) default '0.00'", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Double getPriceeach() {
		return this.priceeach;
	}
	/**设置每个奖金  */

	public void setPriceeach(Double value) {
		this.priceeach = value;
	}
	/**获取奖金数量  */
	

	@Column(name = "pricenum", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getPricenum() {
		return this.pricenum;
	}
	/**设置奖金数量  */

	public void setPricenum(Integer value) {
		this.pricenum = value;
	}
	/**获取总奖金  */
	

	@Column(name = "price", columnDefinition="double(11,3) default '0.00'", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Double getPrice() {
		return this.price;
	}
	/**设置总奖金  */

	public void setPrice(Double value) {
		this.price = value;
	}
	/**获取观看费  */
	

	@Column(name = "viewprice", columnDefinition="double(11,3) default '0.00'", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Double getViewprice() {
		return this.viewprice;
	}
	/**设置观看费  */

	public void setViewprice(Double value) {
		this.viewprice = value;
	}
	/**获取回答人数 计算产生 */
	

	@Column(name = "answernum", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getAnswernum() {
		return this.answernum;
	}
	/**设置回答人数 计算产生 */

	public void setAnswernum(Integer value) {
		this.answernum = value;
	}
	/**获取观看人数 计算产生 */
	

	@Column(name = "viewnum", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getViewnum() {
		return this.viewnum;
	}
	/**设置观看人数 计算产生 */

	public void setViewnum(Integer value) {
		this.viewnum = value;
	}
	/**获取标题  */
	

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	public String getTitle() {
		return this.title;
	}
	/**设置标题  */

	public void setTitle(String value) {
		this.title = value;
	}
	/**获取问题  */
	

	@Column(name = "question", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getQuestion() {
		return this.question;
	}
	/**设置问题  */

	public void setQuestion(String value) {
		this.question = value;
	}
	/**对象 获取能看不  */
	@Transient
	

	public boolean getCanread() {
		return this.canread;
	}
	/**设置能看不  */

	public void setCanread(boolean value) {
		this.canread = value;
	}
	/**对象 获取答案列表  */
	@Transient
	

	public List<QuickTxt> getListQuickTxt() {
		return this.listQuickTxt;
	}
	/**设置答案列表  */

	public void setListQuickTxt(List<QuickTxt> value) {
		this.listQuickTxt = value;
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
	/**开始时间String*/
	private String gmtStartString;
	/**获取开始时间String*/
	@Transient
	
	public String getGmtStartString() {
		if(gmtStartString==null && gmtStart!=null)
			gmtStartString=new SimpleDateFormat(DATE_FORMAT).format(gmtStart);
		return gmtStartString;
	}
	/**设置开始时间String*/
	public void setGmtStartString(String value) {
		this.gmtStartString=value;
	}
	/**结束时间String*/
	private String gmtOverString;
	/**获取结束时间String*/
	@Transient
	
	public String getGmtOverString() {
		if(gmtOverString==null && gmtOver!=null)
			gmtOverString=new SimpleDateFormat(DATE_FORMAT).format(gmtOver);
		return gmtOverString;
	}
	/**设置结束时间String*/
	public void setGmtOverString(String value) {
		this.gmtOverString=value;
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
	/**公开状态String*/
	private String ptypeString;
	/**获取公开状态String*/
	@Transient
	
	public String getPtypeString() {
		return ptypeString;
	}
	/**设置公开状态String*/
	public void setPtypeString(String value) {
		this.ptypeString=value;
	}
	/**公开状态Map*/
	private Map<String, Object> ptypeMap;
	/**获取公开状态Map*/
	@Transient
	
	public Map<String, Object> getPtypeMap() {
		return ptypeMap;
	}
	/**设置公开状态Map*/
	public void setPtypeMap(Map<String, Object> value) {
		this.ptypeMap=value;
	}
	/**频道内序号String*/
	private String articlechannelIdString;
	/**获取频道内序号String*/
	@Transient
	
	public String getArticlechannelIdString() {
		return articlechannelIdString;
	}
	/**设置频道内序号String*/
	public void setArticlechannelIdString(String value) {
		this.articlechannelIdString=value;
	}
	/**Articlechannel - articlechannelIdArticlechannelObj*/
	private Articlechannel articlechannelIdArticlechannelObj;
	/**获取 Articlechannel - articlechannelIdArticlechannelObj*/
	@Transient
	
	public Articlechannel getArticlechannelIdArticlechannelObj() {
		return articlechannelIdArticlechannelObj;
	}
	/**设置 Articlechannel - 频道内序号Obj*/
	public void setArticlechannelIdArticlechannelObj(Articlechannel value) {
		this.articlechannelIdArticlechannelObj=value;
	}
	/**频道内序号Stringid*/
	private String articlechannelIdStringid;
	/**获取频道内序号Stringid*/
	@Transient
	
	public String getArticlechannelIdStringid() {
		return articlechannelIdStringid;
	}
	/**设置频道内序号Stringid*/
	public void setArticlechannelIdStringid(String value) {
		this.articlechannelIdStringid=value;
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
		this.ptypeMap=null;
		this.articlechannelIdArticlechannelObj=null; //Articlechannel
		this.memberQuMemberObj=null; //Member
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
			.append(getGmtPay())
			.append(getGmtStart())
			.append(getGmtOver())
			.append(getStatus())
			.append(getPtype())
			.append(getPriority())
			.append(getArticlechannelId())
			.append(getMemberQu())
			.append(getPriceeach())
			.append(getPricenum())
			.append(getPrice())
			.append(getViewprice())
			.append(getAnswernum())
			.append(getViewnum())
			.append(getTitle())
			.append(getQuestion())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Quick == false) return false;
		if(this == obj) return true;
		Quick other = (Quick)obj;
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
			.append(",GmtPay:",getGmtPay())
			.append(",GmtStart:",getGmtStart())
			.append(",GmtOver:",getGmtOver())
			.append(",Status:",getStatus())
			.append(",Ptype:",getPtype())
			.append(",Priority:",getPriority())
			.append(",ArticlechannelId:",getArticlechannelId())
			.append(",MemberQu:",getMemberQu())
			.append(",Priceeach:",getPriceeach())
			.append(",Pricenum:",getPricenum())
			.append(",Price:",getPrice())
			.append(",Viewprice:",getViewprice())
			.append(",Answernum:",getAnswernum())
			.append(",Viewnum:",getViewnum())
			.append(",Title:",getTitle())
			.append(",Question:",getQuestion())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","gmtModifiedString","gmtPayString","gmtStartString","gmtOverString","statusString","statusMap","ptypeString","ptypeMap","articlechannelIdString","articlechannelIdArticlechannelObj","articlechannelIdStringid","memberQuString","memberQuMemberObj","memberQuStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","gmtModifiedString","gmtPayString","gmtStartString","gmtOverString","statusString","statusMap","ptypeString","ptypeMap","articlechannelIdString","articlechannelIdArticlechannelObj","articlechannelIdStringid","memberQuString","memberQuMemberObj","memberQuStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 抢答
Quick quick = new Quick(
	gmtPay , //Date 支付时间   
	gmtStart , //Date 开始时间   
	gmtOver , //Date 结束时间   
	status , //Integer 状态 default=0  {'0':'未支付','1':'已支付','2':'开始抢答','3':'抢答结束'}
	ptype , //Integer 公开状态 default=0  {'0':'收费公开','1':'不公开'}
	priority , //Integer 排列顺序 default=99 数字越小排名越前 
	articlechannelId , //Integer 频道内序号   
	memberQu , //Integer 提问者 default=0  
	priceeach , //Double 每个奖金 default=0.0  
	pricenum , //Integer 奖金数量 default=0  
	viewprice , //Double 观看费 default=0.0  
	answernum , //Integer 回答人数 default=0 计算产生 
	viewnum , //Integer 观看人数 default=0 计算产生 
	title , //String 标题   
	question , //String 问题   
	null
);
*/
}
