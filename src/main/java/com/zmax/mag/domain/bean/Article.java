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
 * 文章
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "article")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Article extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "文章";

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
	/**排列顺序 Integer default=99 数字越小排名越前 */
	@Max(99999999999L)
	
	private Integer priority = 99;
	/**文章频道内序号 Integer   */
	@Max(99999999999L)
	
	private Integer articlechannelId;
	/**作者内序号 Integer default=0  */
	@Max(99999999999L)
	
	private Integer memberId = 0;
	/**名称 String   */
	@Length(max=100)
	
	private String title;
	/**简介 String   */
	@Length(max=300)
	
	private String intro;
	/**作者 String   */
	@Length(max=50)
	
	private String author;
	/**直接链接到 String  记得前面加http://或https://,如果这个填写了，txt就不需要了 */
	@Length(max=200)
	
	private String linkto;
	/**图1 String   */
	@Length(max=200)
	
	private String img1;
	/**点击次数 Integer default=0  */
	@Max(99999999999L)
	
	private Integer hittimes = 0;
	/**生成相对地址 String   */
	@Length(max=200)
	
	private String genurl;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Article _this){
		_this.gmtCreate=new Date();
		_this.priority=99;
		_this.memberId=0;
		_this.hittimes=0;
	}
	public Article(){
		makedefault(this);
	}
	public Article(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param priority Integer 排列顺序 default=99 数字越小排名越前 
	 * @param articlechannelId Integer 文章频道内序号   
	 * @param memberId Integer 作者内序号 default=0  
	 * @param title String 名称   
	 * @param intro String 简介   
	 * @param author String 作者   
	 * @param linkto String 直接链接到  记得前面加http://或https://,如果这个填写了，txt就不需要了 
	 * @param img1 String 图1   
	 * @param hittimes Integer 点击次数 default=0  
	 * @param genurl String 生成相对地址   
	* @param notuse String 没用
	 */
	public Article(Integer priority ,Integer articlechannelId ,Integer memberId ,String title ,String intro ,String author ,String linkto ,String img1 ,Integer hittimes ,String genurl ,String notuse) {
		super();
		this.priority = priority;
		this.articlechannelId = articlechannelId;
		this.memberId = memberId;
		this.title = title;
		this.intro = intro;
		this.author = author;
		this.linkto = linkto;
		this.img1 = img1;
		this.hittimes = hittimes;
		this.genurl = genurl;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param gmtModified Date 修改时间   
	 * @param priority Integer 排列顺序 default=99 数字越小排名越前 
	 * @param articlechannelId Integer 文章频道内序号   
	 * @param memberId Integer 作者内序号 default=0  
	 * @param title String 名称   
	 * @param intro String 简介   
	 * @param author String 作者   
	 * @param linkto String 直接链接到  记得前面加http://或https://,如果这个填写了，txt就不需要了 
	 * @param img1 String 图1   
	 * @param hittimes Integer 点击次数 default=0  
	 * @param genurl String 生成相对地址   
	* @param notuse String 没用
	 */
	public Article(Integer id ,Date gmtModified ,Integer priority ,Integer articlechannelId ,Integer memberId ,String title ,String intro ,String author ,String linkto ,String img1 ,Integer hittimes ,String genurl ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.gmtModified = gmtModified;
		this.priority = priority;
		this.articlechannelId = articlechannelId;
		this.memberId = memberId;
		this.title = title;
		this.intro = intro;
		this.author = author;
		this.linkto = linkto;
		this.img1 = img1;
		this.hittimes = hittimes;
		this.genurl = genurl;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param gmtCreate Date 创建时间   
	 * @param gmtModified Date 修改时间   
	 * @param priority Integer 排列顺序 default=99 数字越小排名越前 
	 * @param articlechannelId Integer 文章频道内序号   
	 * @param memberId Integer 作者内序号 default=0  
	 * @param title String 名称   
	 * @param intro String 简介   
	 * @param author String 作者   
	 * @param linkto String 直接链接到  记得前面加http://或https://,如果这个填写了，txt就不需要了 
	 * @param img1 String 图1   
	 * @param hittimes Integer 点击次数 default=0  
	 * @param genurl String 生成相对地址   
	* @param notuse String 没用
	 */
	public Article(Integer id ,Date gmtCreate ,Date gmtModified ,Integer priority ,Integer articlechannelId ,Integer memberId ,String title ,String intro ,String author ,String linkto ,String img1 ,Integer hittimes ,String genurl ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.priority = priority;
		this.articlechannelId = articlechannelId;
		this.memberId = memberId;
		this.title = title;
		this.intro = intro;
		this.author = author;
		this.linkto = linkto;
		this.img1 = img1;
		this.hittimes = hittimes;
		this.genurl = genurl;
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
	/**获取排列顺序 数字越小排名越前 */
	

	@Column(name = "priority", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getPriority() {
		return this.priority;
	}
	/**设置排列顺序 数字越小排名越前 */

	public void setPriority(Integer value) {
		this.priority = value;
	}
	/**获取文章频道内序号  */
	

	@Column(name = "articlechannel_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getArticlechannelId() {
		return this.articlechannelId;
	}
	/**设置文章频道内序号  */

	public void setArticlechannelId(Integer value) {
		this.articlechannelId = value;
	}
	/**获取作者内序号  */
	

	@Column(name = "member_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getMemberId() {
		return this.memberId;
	}
	/**设置作者内序号  */

	public void setMemberId(Integer value) {
		this.memberId = value;
	}
	/**获取名称  */
	

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getTitle() {
		return this.title;
	}
	/**设置名称  */

	public void setTitle(String value) {
		this.title = value;
	}
	/**获取简介  */
	

	@Column(name = "intro", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public String getIntro() {
		return this.intro;
	}
	/**设置简介  */

	public void setIntro(String value) {
		this.intro = value;
	}
	/**获取作者  */
	

	@Column(name = "author", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getAuthor() {
		return this.author;
	}
	/**设置作者  */

	public void setAuthor(String value) {
		this.author = value;
	}
	/**获取直接链接到 记得前面加http://或https://,如果这个填写了，txt就不需要了 */
	

	@Column(name = "linkto", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getLinkto() {
		return this.linkto;
	}
	/**设置直接链接到 记得前面加http://或https://,如果这个填写了，txt就不需要了 */

	public void setLinkto(String value) {
		this.linkto = value;
	}
	/**获取图1  */
	

	@Column(name = "img1", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getImg1() {
		return this.img1;
	}
	/**设置图1  */

	public void setImg1(String value) {
		this.img1 = value;
	}
	/**获取点击次数  */
	

	@Column(name = "hittimes", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getHittimes() {
		return this.hittimes;
	}
	/**设置点击次数  */

	public void setHittimes(Integer value) {
		this.hittimes = value;
	}
	/**获取生成相对地址  */
	

	@Column(name = "genurl", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getGenurl() {
		return this.genurl;
	}
	/**设置生成相对地址  */

	public void setGenurl(String value) {
		this.genurl = value;
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
	/**文章频道内序号String*/
	private String articlechannelIdString;
	/**获取文章频道内序号String*/
	@Transient
	
	public String getArticlechannelIdString() {
		return articlechannelIdString;
	}
	/**设置文章频道内序号String*/
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
	/**设置 Articlechannel - 文章频道内序号Obj*/
	public void setArticlechannelIdArticlechannelObj(Articlechannel value) {
		this.articlechannelIdArticlechannelObj=value;
	}
	/**文章频道内序号Stringid*/
	private String articlechannelIdStringid;
	/**获取文章频道内序号Stringid*/
	@Transient
	
	public String getArticlechannelIdStringid() {
		return articlechannelIdStringid;
	}
	/**设置文章频道内序号Stringid*/
	public void setArticlechannelIdStringid(String value) {
		this.articlechannelIdStringid=value;
	}
	/**作者内序号String*/
	private String memberIdString;
	/**获取作者内序号String*/
	@Transient
	
	public String getMemberIdString() {
		return memberIdString;
	}
	/**设置作者内序号String*/
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
	/**设置 Member - 作者内序号Obj*/
	public void setMemberIdMemberObj(Member value) {
		this.memberIdMemberObj=value;
	}
	/**作者内序号Stringid*/
	private String memberIdStringid;
	/**获取作者内序号Stringid*/
	@Transient
	
	public String getMemberIdStringid() {
		return memberIdStringid;
	}
	/**设置作者内序号Stringid*/
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
		this.articlechannelIdArticlechannelObj=null; //Articlechannel
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
	/**扩展对象ArticleExtTxt*/
	private ArticleExtTxt articleExtTxt;
	/**扩展对象ArticleExtTxt*/
	@Transient
	
	public ArticleExtTxt getArticleExtTxt() {
		return  articleExtTxt;
	}
	/**
	 * 扩展对象ArticleExtTxt
	 * @return  articleExtTxt
	 */
	public void setArticleExtTxt(ArticleExtTxt articleExtTxt) {
		this. articleExtTxt =  articleExtTxt;
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
			.append(getPriority())
			.append(getArticlechannelId())
			.append(getMemberId())
			.append(getTitle())
			.append(getIntro())
			.append(getAuthor())
			.append(getLinkto())
			.append(getImg1())
			.append(getHittimes())
			.append(getGenurl())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Article == false) return false;
		if(this == obj) return true;
		Article other = (Article)obj;
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
			.append(",Priority:",getPriority())
			.append(",ArticlechannelId:",getArticlechannelId())
			.append(",MemberId:",getMemberId())
			.append(",Title:",getTitle())
			.append(",Intro:",getIntro())
			.append(",Author:",getAuthor())
			.append(",Linkto:",getLinkto())
			.append(",Img1:",getImg1())
			.append(",Hittimes:",getHittimes())
			.append(",Genurl:",getGenurl())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","gmtModifiedString","articlechannelIdString","articlechannelIdArticlechannelObj","articlechannelIdStringid","memberIdString","memberIdMemberObj","memberIdStringid","articleExtTxt","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","gmtModifiedString","articlechannelIdString","articlechannelIdArticlechannelObj","articlechannelIdStringid","memberIdString","memberIdMemberObj","memberIdStringid","articleExtTxt","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 文章
Article article = new Article(
	priority , //Integer 排列顺序 default=99 数字越小排名越前 
	articlechannelId , //Integer 文章频道内序号   
	memberId , //Integer 作者内序号 default=0  
	title , //String 名称   
	intro , //String 简介   
	author , //String 作者   
	linkto , //String 直接链接到  记得前面加http://或https://,如果这个填写了，txt就不需要了 
	img1 , //String 图1   
	hittimes , //Integer 点击次数 default=0  
	genurl , //String 生成相对地址   
	null
);
*/
}
