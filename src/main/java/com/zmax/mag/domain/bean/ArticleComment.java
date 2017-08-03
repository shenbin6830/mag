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
 * 文章的评论
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "article_comment")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class ArticleComment extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "文章的评论";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**序号 Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**文章内序号 Integer   */
	@Max(99999999999L)
	
	private Integer articleId;
	/**作者内序号 Integer default=0  */
	@Max(99999999999L)
	
	private Integer memberAu = 0;
	/**评论者内序 Integer default=0  */
	@Max(99999999999L)
	
	private Integer memberCo = 0;
	/**评论 String   */
	@Length(max=200)
	
	private String msg;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(ArticleComment _this){
		_this.gmtCreate=new Date();
		_this.memberAu=0;
		_this.memberCo=0;
	}
	public ArticleComment(){
		makedefault(this);
	}
	public ArticleComment(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param articleId Integer 文章内序号   
	 * @param memberCo Integer 评论者内序 default=0  
	 * @param msg String 评论   
	* @param notuse String 没用
	 */
	public ArticleComment(Integer articleId ,Integer memberCo ,String msg ,String notuse) {
		super();
		this.articleId = articleId;
		this.memberCo = memberCo;
		this.msg = msg;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param articleId Integer 文章内序号   
	 * @param memberAu Integer 作者内序号 default=0  
	 * @param memberCo Integer 评论者内序 default=0  
	 * @param msg String 评论   
	* @param notuse String 没用
	 */
	public ArticleComment(Integer id ,Integer articleId ,Integer memberAu ,Integer memberCo ,String msg ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.articleId = articleId;
		this.memberAu = memberAu;
		this.memberCo = memberCo;
		this.msg = msg;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param gmtCreate Date 创建时间   
	 * @param articleId Integer 文章内序号   
	 * @param memberAu Integer 作者内序号 default=0  
	 * @param memberCo Integer 评论者内序 default=0  
	 * @param msg String 评论   
	* @param notuse String 没用
	 */
	public ArticleComment(Integer id ,Date gmtCreate ,Integer articleId ,Integer memberAu ,Integer memberCo ,String msg ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.articleId = articleId;
		this.memberAu = memberAu;
		this.memberCo = memberCo;
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
	/**获取文章内序号  */
	

	@Column(name = "article_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getArticleId() {
		return this.articleId;
	}
	/**设置文章内序号  */

	public void setArticleId(Integer value) {
		this.articleId = value;
	}
	/**获取作者内序号  */
	

	@Column(name = "member_au", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getMemberAu() {
		return this.memberAu;
	}
	/**设置作者内序号  */

	public void setMemberAu(Integer value) {
		this.memberAu = value;
	}
	/**获取评论者内序  */
	

	@Column(name = "member_co", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getMemberCo() {
		return this.memberCo;
	}
	/**设置评论者内序  */

	public void setMemberCo(Integer value) {
		this.memberCo = value;
	}
	/**获取评论  */
	

	@Column(name = "msg", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getMsg() {
		return this.msg;
	}
	/**设置评论  */

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
	/**文章内序号String*/
	private String articleIdString;
	/**获取文章内序号String*/
	@Transient
	
	public String getArticleIdString() {
		return articleIdString;
	}
	/**设置文章内序号String*/
	public void setArticleIdString(String value) {
		this.articleIdString=value;
	}
	/**Article - articleIdArticleObj*/
	private Article articleIdArticleObj;
	/**获取 Article - articleIdArticleObj*/
	@Transient
	
	public Article getArticleIdArticleObj() {
		return articleIdArticleObj;
	}
	/**设置 Article - 文章内序号Obj*/
	public void setArticleIdArticleObj(Article value) {
		this.articleIdArticleObj=value;
	}
	/**文章内序号Stringid*/
	private String articleIdStringid;
	/**获取文章内序号Stringid*/
	@Transient
	
	public String getArticleIdStringid() {
		return articleIdStringid;
	}
	/**设置文章内序号Stringid*/
	public void setArticleIdStringid(String value) {
		this.articleIdStringid=value;
	}
	/**作者内序号String*/
	private String memberAuString;
	/**获取作者内序号String*/
	@Transient
	
	public String getMemberAuString() {
		return memberAuString;
	}
	/**设置作者内序号String*/
	public void setMemberAuString(String value) {
		this.memberAuString=value;
	}
	/**Member - memberAuMemberObj*/
	private Member memberAuMemberObj;
	/**获取 Member - memberAuMemberObj*/
	@Transient
	
	public Member getMemberAuMemberObj() {
		return memberAuMemberObj;
	}
	/**设置 Member - 作者内序号Obj*/
	public void setMemberAuMemberObj(Member value) {
		this.memberAuMemberObj=value;
	}
	/**作者内序号Stringid*/
	private String memberAuStringid;
	/**获取作者内序号Stringid*/
	@Transient
	
	public String getMemberAuStringid() {
		return memberAuStringid;
	}
	/**设置作者内序号Stringid*/
	public void setMemberAuStringid(String value) {
		this.memberAuStringid=value;
	}
	/**评论者内序String*/
	private String memberCoString;
	/**获取评论者内序String*/
	@Transient
	
	public String getMemberCoString() {
		return memberCoString;
	}
	/**设置评论者内序String*/
	public void setMemberCoString(String value) {
		this.memberCoString=value;
	}
	/**Member - memberCoMemberObj*/
	private Member memberCoMemberObj;
	/**获取 Member - memberCoMemberObj*/
	@Transient
	
	public Member getMemberCoMemberObj() {
		return memberCoMemberObj;
	}
	/**设置 Member - 评论者内序Obj*/
	public void setMemberCoMemberObj(Member value) {
		this.memberCoMemberObj=value;
	}
	/**评论者内序Stringid*/
	private String memberCoStringid;
	/**获取评论者内序Stringid*/
	@Transient
	
	public String getMemberCoStringid() {
		return memberCoStringid;
	}
	/**设置评论者内序Stringid*/
	public void setMemberCoStringid(String value) {
		this.memberCoStringid=value;
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
		this.articleIdArticleObj=null; //Article
		this.memberAuMemberObj=null; //Member
		this.memberCoMemberObj=null; //Member
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
			.append(getArticleId())
			.append(getMemberAu())
			.append(getMemberCo())
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
		if(obj instanceof ArticleComment == false) return false;
		if(this == obj) return true;
		ArticleComment other = (ArticleComment)obj;
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
			.append(",ArticleId:",getArticleId())
			.append(",MemberAu:",getMemberAu())
			.append(",MemberCo:",getMemberCo())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","articleIdString","articleIdArticleObj","articleIdStringid","memberAuString","memberAuMemberObj","memberAuStringid","memberCoString","memberCoMemberObj","memberCoStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","articleIdString","articleIdArticleObj","articleIdStringid","memberAuString","memberAuMemberObj","memberAuStringid","memberCoString","memberCoMemberObj","memberCoStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 文章的评论
ArticleComment articleComment = new ArticleComment(
	articleId , //Integer 文章内序号   
	memberCo , //Integer 评论者内序 default=0  
	msg , //String 评论   
	null
);
*/
}
