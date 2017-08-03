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
 * 广告使用的关注二维码<br/>关键字=QRADV,转换场景中的act_detail就是wa_qradv.id,返回是wa_qradv指定的
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wa_qradv")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class WaQradv extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "广告使用的关注二维码";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**序号ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**标题 String   */
	@Length(max=100)
	
	private String title;
	/**二维码 String  600x600 */
	@Length(max=200)
	
	private String imgqr;
	/**关注后返回方式 Integer default=0  map={'0':'无返回','1':'返回一个纯文本','2':'返回一个图文'}*/
	@Max(999L)
	
	private Integer rettype = 0;
	/**关注数量 Integer default=0  */
	@Max(99999999999L)
	
	private Integer num = 0;
	/**返回的纯文本 String   */
	@Length(max=100)
	
	private String rettxt;
	/**返回的图文 Integer default=0  */
	@Max(99999999999L)
	
	private Integer waEntityArticleId = 0;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaQradv _this){
		_this.gmtCreate=new Date();
		_this.rettype=0;
		_this.num=0;
		_this.waEntityArticleId=0;
	}
	public WaQradv(){
		makedefault(this);
	}
	public WaQradv(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param title String 标题   
	 * @param imgqr String 二维码  600x600 
	 * @param rettype Integer 关注后返回方式 default=0  {'0':'无返回','1':'返回一个纯文本','2':'返回一个图文'}
	 * @param num Integer 关注数量 default=0  
	 * @param rettxt String 返回的纯文本   
	 * @param waEntityArticleId Integer 返回的图文 default=0  
	* @param notuse String 没用
	 */
	public WaQradv(String title ,String imgqr ,Integer rettype ,Integer num ,String rettxt ,Integer waEntityArticleId ,String notuse) {
		super();
		this.title = title;
		this.imgqr = imgqr;
		this.rettype = rettype;
		this.num = num;
		this.rettxt = rettxt;
		this.waEntityArticleId = waEntityArticleId;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param title String 标题   
	 * @param imgqr String 二维码  600x600 
	 * @param rettype Integer 关注后返回方式 default=0  {'0':'无返回','1':'返回一个纯文本','2':'返回一个图文'}
	 * @param num Integer 关注数量 default=0  
	 * @param rettxt String 返回的纯文本   
	 * @param waEntityArticleId Integer 返回的图文 default=0  
	* @param notuse String 没用
	 */
	public WaQradv(Integer id ,String title ,String imgqr ,Integer rettype ,Integer num ,String rettxt ,Integer waEntityArticleId ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.title = title;
		this.imgqr = imgqr;
		this.rettype = rettype;
		this.num = num;
		this.rettxt = rettxt;
		this.waEntityArticleId = waEntityArticleId;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param gmtCreate Date 创建时间   
	 * @param title String 标题   
	 * @param imgqr String 二维码  600x600 
	 * @param rettype Integer 关注后返回方式 default=0  {'0':'无返回','1':'返回一个纯文本','2':'返回一个图文'}
	 * @param num Integer 关注数量 default=0  
	 * @param rettxt String 返回的纯文本   
	 * @param waEntityArticleId Integer 返回的图文 default=0  
	* @param notuse String 没用
	 */
	public WaQradv(Integer id ,Date gmtCreate ,String title ,String imgqr ,Integer rettype ,Integer num ,String rettxt ,Integer waEntityArticleId ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.title = title;
		this.imgqr = imgqr;
		this.rettype = rettype;
		this.num = num;
		this.rettxt = rettxt;
		this.waEntityArticleId = waEntityArticleId;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+title+" "+rettxt+" ":myname;
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
		mynameid=(mynameid==null)?"["+id+"]"+title+" "+rettxt+" ":mynameid;
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
	/**获取标题  */
	

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getTitle() {
		return this.title;
	}
	/**设置标题  */

	public void setTitle(String value) {
		this.title = value;
	}
	/**获取二维码 600x600 */
	

	@Column(name = "imgqr", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getImgqr() {
		return this.imgqr;
	}
	/**设置二维码 600x600 */

	public void setImgqr(String value) {
		this.imgqr = value;
	}
	/**获取关注后返回方式  {'0':'无返回','1':'返回一个纯文本','2':'返回一个图文'}*/
	

	@Column(name = "rettype", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getRettype() {
		return this.rettype;
	}
	/**设置关注后返回方式  {'0':'无返回','1':'返回一个纯文本','2':'返回一个图文'}*/

	public void setRettype(Integer value) {
		this.rettype = value;
	}
	/**获取关注数量  */
	

	@Column(name = "num", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getNum() {
		return this.num;
	}
	/**设置关注数量  */

	public void setNum(Integer value) {
		this.num = value;
	}
	/**获取返回的纯文本  */
	

	@Column(name = "rettxt", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getRettxt() {
		return this.rettxt;
	}
	/**设置返回的纯文本  */

	public void setRettxt(String value) {
		this.rettxt = value;
	}
	/**获取返回的图文  */
	

	@Column(name = "wa_entity_article_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getWaEntityArticleId() {
		return this.waEntityArticleId;
	}
	/**设置返回的图文  */

	public void setWaEntityArticleId(Integer value) {
		this.waEntityArticleId = value;
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
	/**关注后返回方式String*/
	private String rettypeString;
	/**获取关注后返回方式String*/
	@Transient
	
	public String getRettypeString() {
		return rettypeString;
	}
	/**设置关注后返回方式String*/
	public void setRettypeString(String value) {
		this.rettypeString=value;
	}
	/**关注后返回方式Map*/
	private Map<String, Object> rettypeMap;
	/**获取关注后返回方式Map*/
	@Transient
	
	public Map<String, Object> getRettypeMap() {
		return rettypeMap;
	}
	/**设置关注后返回方式Map*/
	public void setRettypeMap(Map<String, Object> value) {
		this.rettypeMap=value;
	}
	/**返回的图文String*/
	private String waEntityArticleIdString;
	/**获取返回的图文String*/
	@Transient
	
	public String getWaEntityArticleIdString() {
		return waEntityArticleIdString;
	}
	/**设置返回的图文String*/
	public void setWaEntityArticleIdString(String value) {
		this.waEntityArticleIdString=value;
	}
	/**WaEntityArticle - waEntityArticleIdWaEntityArticleObj*/
	private WaEntityArticle waEntityArticleIdWaEntityArticleObj;
	/**获取 WaEntityArticle - waEntityArticleIdWaEntityArticleObj*/
	@Transient
	
	public WaEntityArticle getWaEntityArticleIdWaEntityArticleObj() {
		return waEntityArticleIdWaEntityArticleObj;
	}
	/**设置 WaEntityArticle - 返回的图文Obj*/
	public void setWaEntityArticleIdWaEntityArticleObj(WaEntityArticle value) {
		this.waEntityArticleIdWaEntityArticleObj=value;
	}
	/**返回的图文Stringid*/
	private String waEntityArticleIdStringid;
	/**获取返回的图文Stringid*/
	@Transient
	
	public String getWaEntityArticleIdStringid() {
		return waEntityArticleIdStringid;
	}
	/**设置返回的图文Stringid*/
	public void setWaEntityArticleIdStringid(String value) {
		this.waEntityArticleIdStringid=value;
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
		this.rettypeMap=null;
		this.waEntityArticleIdWaEntityArticleObj=null; //WaEntityArticle
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
			.append(getTitle())
			.append(getImgqr())
			.append(getRettype())
			.append(getNum())
			.append(getRettxt())
			.append(getWaEntityArticleId())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof WaQradv == false) return false;
		if(this == obj) return true;
		WaQradv other = (WaQradv)obj;
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
			.append(",Title:",getTitle())
			.append(",Imgqr:",getImgqr())
			.append(",Rettype:",getRettype())
			.append(",Num:",getNum())
			.append(",Rettxt:",getRettxt())
			.append(",WaEntityArticleId:",getWaEntityArticleId())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","rettypeString","rettypeMap","waEntityArticleIdString","waEntityArticleIdWaEntityArticleObj","waEntityArticleIdStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","rettypeString","rettypeMap","waEntityArticleIdString","waEntityArticleIdWaEntityArticleObj","waEntityArticleIdStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 广告使用的关注二维码
WaQradv waQradv = new WaQradv(
	title , //String 标题   
	imgqr , //String 二维码  600x600 
	rettype , //Integer 关注后返回方式 default=0  {'0':'无返回','1':'返回一个纯文本','2':'返回一个图文'}
	num , //Integer 关注数量 default=0  
	rettxt , //String 返回的纯文本   
	waEntityArticleId , //Integer 返回的图文 default=0  
	null
);
*/
}
