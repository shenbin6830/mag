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





/**
 * 文章内容
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "article_ext_txt")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class ArticleExtTxt extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "文章内容";

	//date formats
	
	//columns START
	/**商品ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**详细介绍 String  如果要在这里放图的话，如果不是全屏，请点源码，图片的style属性从style=‘width: 111px; height: 222px;’改为：style=‘width: 100%;’ */
	@Length(max=2147483647)
	
	private String txt;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(ArticleExtTxt _this){
	}
	public ArticleExtTxt(){
		makedefault(this);
	}
	public ArticleExtTxt(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param txt String 详细介绍  如果要在这里放图的话，如果不是全屏，请点源码，图片的style属性从style=‘width: 111px; height: 222px;’改为：style=‘width: 100%;’ 
	* @param notuse String 没用
	 */
	public ArticleExtTxt(String txt ,String notuse) {
		super();
		this.txt = txt;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 商品ID   
	 * @param txt String 详细介绍  如果要在这里放图的话，如果不是全屏，请点源码，图片的style属性从style=‘width: 111px; height: 222px;’改为：style=‘width: 100%;’ 
	* @param notuse String 没用
	 */
	public ArticleExtTxt(Integer id ,String txt ,String notuse) {
		super();
		this.id = id;
		this.txt = txt;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 商品ID   
	 * @param txt String 详细介绍  如果要在这里放图的话，如果不是全屏，请点源码，图片的style属性从style=‘width: 111px; height: 222px;’改为：style=‘width: 100%;’ 
	* @param notuse String 没用
	 */
	public ArticleExtTxt(Integer id ,String txt ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.txt = txt;
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
	

	@Id 
	@Column(name = "id",  unique = false, nullable = false, insertable = true, updatable = true, length = 11)
	public Integer getId() {
		return this.id;
	}
	
	/**获取详细介绍 如果要在这里放图的话，如果不是全屏，请点源码，图片的style属性从style=‘width: 111px; height: 222px;’改为：style=‘width: 100%;’ */
	

	@Column(name = "txt", unique = false, nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getTxt() {
		return this.txt;
	}
	/**设置详细介绍 如果要在这里放图的话，如果不是全屏，请点源码，图片的style属性从style=‘width: 111px; height: 222px;’改为：style=‘width: 100%;’ */

	public void setTxt(String value) {
		this.txt = value;
	}
	/**商品IDString*/
	private String idString;
	/**获取商品IDString*/
	@Transient
	
	public String getIdString() {
		return idString;
	}
	/**设置商品IDString*/
	public void setIdString(String value) {
		this.idString=value;
	}
	/**Article - idArticleObj*/
	private Article idArticleObj;
	/**获取 Article - idArticleObj*/
	@Transient
	
	public Article getIdArticleObj() {
		return idArticleObj;
	}
	/**设置 Article - 商品IDObj*/
	public void setIdArticleObj(Article value) {
		this.idArticleObj=value;
	}
	/**商品IDStringid*/
	private String idStringid;
	/**获取商品IDStringid*/
	@Transient
	
	public String getIdStringid() {
		return idStringid;
	}
	/**设置商品IDStringid*/
	public void setIdStringid(String value) {
		this.idStringid=value;
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
		this.idArticleObj=null; //Article
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
			.append(getTxt())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof ArticleExtTxt == false) return false;
		if(this == obj) return true;
		ArticleExtTxt other = (ArticleExtTxt)obj;
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
			.append(",Txt:",getTxt())
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
		String[] excludesProperties={"myname","mynameid","idString","idArticleObj","idStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","idString","idArticleObj","idStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 文章内容
ArticleExtTxt articleExtTxt = new ArticleExtTxt(
	txt , //String 详细介绍  如果要在这里放图的话，如果不是全屏，请点源码，图片的style属性从style=‘width: 111px; height: 222px;’改为：style=‘width: 100%;’ 
	null
);
*/
}
