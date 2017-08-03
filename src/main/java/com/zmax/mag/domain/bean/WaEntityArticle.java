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

import javax.xml.bind.annotation.*;
import com.alibaba.fastjson.annotation.*;


 
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 共用对象之图文
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wa_entity_article")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
@XmlRootElement(name="xml")
public class WaEntityArticle extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "共用对象之图文";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**序号ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**状态 Integer default=1  map={'0':'无效','1':'上线'}*/
	@Max(999L)
	
	private Integer status = 1;
	/**关键字 String   */
	@Length(max=20)
	
	private String keyw;
	/**图文消息标题 String   */
	@Length(max=100)
	
	private String title;
	/**图文消息描述 String  如果是纯文本消息，取此项值 */
	@Length(max=200)
	
	private String description;
	/**图片链接 String  大图360*200,小图200*200 */
	@Length(max=200)
	
	private String pic;
	/**图片完整链接 String  腾讯规定的完整路径，http://a.b.c/加上面的图片链接 */
	@Length(max=200)
	
	private String picurl;
	/**点击图文消息跳转链接 String   */
	@Length(max=500)
	
	private String url;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaEntityArticle _this){
		_this.gmtCreate=new Date();
		_this.status=1;
	}
	public WaEntityArticle(){
		makedefault(this);
	}
	public WaEntityArticle(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param status Integer 状态 default=1  {'0':'无效','1':'上线'}
	 * @param keyw String 关键字   
	 * @param title String 图文消息标题   
	 * @param description String 图文消息描述  如果是纯文本消息，取此项值 
	 * @param pic String 图片链接  大图360*200,小图200*200 
	 * @param picurl String 图片完整链接  腾讯规定的完整路径，http://a.b.c/加上面的图片链接 
	 * @param url String 点击图文消息跳转链接   
	* @param notuse String 没用
	 */
	public WaEntityArticle(Integer status ,String keyw ,String title ,String description ,String pic ,String picurl ,String url ,String notuse) {
		super();
		this.status = status;
		this.keyw = keyw;
		this.title = title;
		this.description = description;
		this.pic = pic;
		this.picurl = picurl;
		this.url = url;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param status Integer 状态 default=1  {'0':'无效','1':'上线'}
	 * @param keyw String 关键字   
	 * @param title String 图文消息标题   
	 * @param description String 图文消息描述  如果是纯文本消息，取此项值 
	 * @param pic String 图片链接  大图360*200,小图200*200 
	 * @param picurl String 图片完整链接  腾讯规定的完整路径，http://a.b.c/加上面的图片链接 
	 * @param url String 点击图文消息跳转链接   
	* @param notuse String 没用
	 */
	public WaEntityArticle(Integer id ,Integer status ,String keyw ,String title ,String description ,String pic ,String picurl ,String url ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.status = status;
		this.keyw = keyw;
		this.title = title;
		this.description = description;
		this.pic = pic;
		this.picurl = picurl;
		this.url = url;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param gmtCreate Date 创建时间   
	 * @param status Integer 状态 default=1  {'0':'无效','1':'上线'}
	 * @param keyw String 关键字   
	 * @param title String 图文消息标题   
	 * @param description String 图文消息描述  如果是纯文本消息，取此项值 
	 * @param pic String 图片链接  大图360*200,小图200*200 
	 * @param picurl String 图片完整链接  腾讯规定的完整路径，http://a.b.c/加上面的图片链接 
	 * @param url String 点击图文消息跳转链接   
	* @param notuse String 没用
	 */
	public WaEntityArticle(Integer id ,Date gmtCreate ,Integer status ,String keyw ,String title ,String description ,String pic ,String picurl ,String url ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.status = status;
		this.keyw = keyw;
		this.title = title;
		this.description = description;
		this.pic = pic;
		this.picurl = picurl;
		this.url = url;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	@XmlTransient
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
	@XmlTransient
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
	@XmlTransient

	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "identity")
	@Column(name = "id",  unique = false, nullable = false, insertable = true, updatable = true, length = 11)
	public Integer getId() {
		return this.id;
	}
	
	/**获取创建时间  */
	@XmlTransient

	@Column(name = "gmt_create", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtCreate() {
		return this.gmtCreate;
	}
	/**设置创建时间  */

	public void setGmtCreate(Date value) {
		this.gmtCreate = value;
	}
	/**获取状态  {'0':'无效','1':'上线'}*/
	@XmlTransient

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	/**设置状态  {'0':'无效','1':'上线'}*/

	public void setStatus(Integer value) {
		this.status = value;
	}
	/**获取关键字  */
	@XmlTransient

	@Column(name = "keyw", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getKeyw() {
		return this.keyw;
	}
	/**设置关键字  */

	public void setKeyw(String value) {
		this.keyw = value;
	}
	/**获取图文消息标题  */
	@XmlElement(name="Title")

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getTitle() {
		return this.title;
	}
	/**设置图文消息标题  */

	public void setTitle(String value) {
		this.title = value;
	}
	/**获取图文消息描述 如果是纯文本消息，取此项值 */
	@XmlElement(name="Description")

	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getDescription() {
		return this.description;
	}
	/**设置图文消息描述 如果是纯文本消息，取此项值 */

	public void setDescription(String value) {
		this.description = value;
	}
	/**获取图片链接 大图360*200,小图200*200 */
	@XmlTransient

	@Column(name = "pic", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getPic() {
		return this.pic;
	}
	/**设置图片链接 大图360*200,小图200*200 */

	public void setPic(String value) {
		this.pic = value;
	}
	/**获取图片完整链接 腾讯规定的完整路径，http://a.b.c/加上面的图片链接 */
	@XmlElement(name="PicUrl")

	@Column(name = "picurl", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getPicurl() {
		return this.picurl;
	}
	/**设置图片完整链接 腾讯规定的完整路径，http://a.b.c/加上面的图片链接 */

	public void setPicurl(String value) {
		this.picurl = value;
	}
	/**获取点击图文消息跳转链接  */
	@XmlElement(name="Url")

	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public String getUrl() {
		return this.url;
	}
	/**设置点击图文消息跳转链接  */

	public void setUrl(String value) {
		this.url = value;
	}
	/**创建时间String*/
	private String gmtCreateString;
	/**获取创建时间String*/
	@Transient
	@XmlTransient
	public String getGmtCreateString() {
		if(gmtCreateString==null && gmtCreate!=null)
			gmtCreateString=new SimpleDateFormat(DATE_FORMAT).format(gmtCreate);
		return gmtCreateString;
	}
	/**设置创建时间String*/
	public void setGmtCreateString(String value) {
		this.gmtCreateString=value;
	}
	/**状态String*/
	private String statusString;
	/**获取状态String*/
	@Transient
	@XmlTransient
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
	@XmlTransient
	public Map<String, Object> getStatusMap() {
		return statusMap;
	}
	/**设置状态Map*/
	public void setStatusMap(Map<String, Object> value) {
		this.statusMap=value;
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
	}
	/**备用对象1*/
	private Object obj1;
	/**
	 * 获取备用对象1
	 * @return Object
	 */
	@Transient
	@XmlTransient
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
	@XmlTransient
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
	@XmlTransient
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
	@XmlTransient
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
	@XmlTransient
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
	@XmlTransient
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
	@XmlTransient
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
	@XmlTransient
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
	@XmlTransient
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
	@XmlTransient
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
	@XmlTransient
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
			.append(getStatus())
			.append(getKeyw())
			.append(getTitle())
			.append(getDescription())
			.append(getPic())
			.append(getPicurl())
			.append(getUrl())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof WaEntityArticle == false) return false;
		if(this == obj) return true;
		WaEntityArticle other = (WaEntityArticle)obj;
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
			.append(",Status:",getStatus())
			.append(",Keyw:",getKeyw())
			.append(",Title:",getTitle())
			.append(",Description:",getDescription())
			.append(",Pic:",getPic())
			.append(",Picurl:",getPicurl())
			.append(",Url:",getUrl())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","statusString","statusMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"id","gmtCreate","status","pic","myname","mynameid","gmtCreateString","statusString","statusMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 共用对象之图文
WaEntityArticle waEntityArticle = new WaEntityArticle(
	status , //Integer 状态 default=1  {'0':'无效','1':'上线'}
	keyw , //String 关键字   
	title , //String 图文消息标题   
	description , //String 图文消息描述  如果是纯文本消息，取此项值 
	pic , //String 图片链接  大图360*200,小图200*200 
	picurl , //String 图片完整链接  腾讯规定的完整路径，http://a.b.c/加上面的图片链接 
	url , //String 点击图文消息跳转链接   
	null
);
*/
}
