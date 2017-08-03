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
 * 微信用户关注历史记录
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wxousersubscribehis")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Wxousersubscribehis extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "微信用户关注历史记录";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**序号ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**所属卖家 Integer   */
	@Max(99999999999L)
	
	private Integer sellerId;
	/**微信openid String   */
	@Length(max=50)
	
	private String wxouserId;
	/**操作 Integer default=0  map={'0':'取消关注','1':'关注'}*/
	@Max(999L)
	
	private Integer act = 0;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Wxousersubscribehis _this){
		_this.gmtCreate=new Date();
		_this.act=0;
	}
	public Wxousersubscribehis(){
		makedefault(this);
	}
	public Wxousersubscribehis(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param sellerId Integer 所属卖家   
	 * @param wxouserId String 微信openid   
	 * @param act Integer 操作 default=0  {'0':'取消关注','1':'关注'}
	* @param notuse String 没用
	 */
	public Wxousersubscribehis(Integer sellerId ,String wxouserId ,Integer act ,String notuse) {
		super();
		this.sellerId = sellerId;
		this.wxouserId = wxouserId;
		this.act = act;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param sellerId Integer 所属卖家   
	 * @param wxouserId String 微信openid   
	 * @param act Integer 操作 default=0  {'0':'取消关注','1':'关注'}
	* @param notuse String 没用
	 */
	public Wxousersubscribehis(Integer id ,Integer sellerId ,String wxouserId ,Integer act ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.sellerId = sellerId;
		this.wxouserId = wxouserId;
		this.act = act;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param gmtCreate Date 时间   
	 * @param sellerId Integer 所属卖家   
	 * @param wxouserId String 微信openid   
	 * @param act Integer 操作 default=0  {'0':'取消关注','1':'关注'}
	* @param notuse String 没用
	 */
	public Wxousersubscribehis(Integer id ,Date gmtCreate ,Integer sellerId ,String wxouserId ,Integer act ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.sellerId = sellerId;
		this.wxouserId = wxouserId;
		this.act = act;
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
	
	/**获取时间  */
	

	@Column(name = "gmt_create", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtCreate() {
		return this.gmtCreate;
	}
	/**设置时间  */

	public void setGmtCreate(Date value) {
		this.gmtCreate = value;
	}
	/**获取所属卖家  */
	

	@Column(name = "seller_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getSellerId() {
		return this.sellerId;
	}
	/**设置所属卖家  */

	public void setSellerId(Integer value) {
		this.sellerId = value;
	}
	/**获取微信openid  */
	

	@Column(name = "wxouser_id", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getWxouserId() {
		return this.wxouserId;
	}
	/**设置微信openid  */

	public void setWxouserId(String value) {
		this.wxouserId = value;
	}
	/**获取操作  {'0':'取消关注','1':'关注'}*/
	

	@Column(name = "act", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getAct() {
		return this.act;
	}
	/**设置操作  {'0':'取消关注','1':'关注'}*/

	public void setAct(Integer value) {
		this.act = value;
	}
	/**时间String*/
	private String gmtCreateString;
	/**获取时间String*/
	@Transient
	
	public String getGmtCreateString() {
		if(gmtCreateString==null && gmtCreate!=null)
			gmtCreateString=new SimpleDateFormat(DATE_FORMAT).format(gmtCreate);
		return gmtCreateString;
	}
	/**设置时间String*/
	public void setGmtCreateString(String value) {
		this.gmtCreateString=value;
	}
	/**所属卖家String*/
	private String sellerIdString;
	/**获取所属卖家String*/
	@Transient
	
	public String getSellerIdString() {
		return sellerIdString;
	}
	/**设置所属卖家String*/
	public void setSellerIdString(String value) {
		this.sellerIdString=value;
	}
	/**User - sellerIdUserObj*/
	private User sellerIdUserObj;
	/**获取 User - sellerIdUserObj*/
	@Transient
	
	public User getSellerIdUserObj() {
		return sellerIdUserObj;
	}
	/**设置 User - 所属卖家Obj*/
	public void setSellerIdUserObj(User value) {
		this.sellerIdUserObj=value;
	}
	/**所属卖家Stringid*/
	private String sellerIdStringid;
	/**获取所属卖家Stringid*/
	@Transient
	
	public String getSellerIdStringid() {
		return sellerIdStringid;
	}
	/**设置所属卖家Stringid*/
	public void setSellerIdStringid(String value) {
		this.sellerIdStringid=value;
	}
	/**微信openidString*/
	private String wxouserIdString;
	/**获取微信openidString*/
	@Transient
	
	public String getWxouserIdString() {
		return wxouserIdString;
	}
	/**设置微信openidString*/
	public void setWxouserIdString(String value) {
		this.wxouserIdString=value;
	}
	/**Wxouser - wxouserIdWxouserObj*/
	private Wxouser wxouserIdWxouserObj;
	/**获取 Wxouser - wxouserIdWxouserObj*/
	@Transient
	
	public Wxouser getWxouserIdWxouserObj() {
		return wxouserIdWxouserObj;
	}
	/**设置 Wxouser - 微信openidObj*/
	public void setWxouserIdWxouserObj(Wxouser value) {
		this.wxouserIdWxouserObj=value;
	}
	/**微信openidStringid*/
	private String wxouserIdStringid;
	/**获取微信openidStringid*/
	@Transient
	
	public String getWxouserIdStringid() {
		return wxouserIdStringid;
	}
	/**设置微信openidStringid*/
	public void setWxouserIdStringid(String value) {
		this.wxouserIdStringid=value;
	}
	/**操作String*/
	private String actString;
	/**获取操作String*/
	@Transient
	
	public String getActString() {
		return actString;
	}
	/**设置操作String*/
	public void setActString(String value) {
		this.actString=value;
	}
	/**操作Map*/
	private Map<String, Object> actMap;
	/**获取操作Map*/
	@Transient
	
	public Map<String, Object> getActMap() {
		return actMap;
	}
	/**设置操作Map*/
	public void setActMap(Map<String, Object> value) {
		this.actMap=value;
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
		this.sellerIdUserObj=null; //User
		this.wxouserIdWxouserObj=null; //Wxouser
		this.actMap=null;
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
			.append(getSellerId())
			.append(getWxouserId())
			.append(getAct())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Wxousersubscribehis == false) return false;
		if(this == obj) return true;
		Wxousersubscribehis other = (Wxousersubscribehis)obj;
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
			.append(",SellerId:",getSellerId())
			.append(",WxouserId:",getWxouserId())
			.append(",Act:",getAct())
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
		String[] excludesProperties={"myname","mynameid","gmtCreateString","sellerIdString","sellerIdUserObj","sellerIdStringid","wxouserIdString","wxouserIdWxouserObj","wxouserIdStringid","actString","actMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","sellerIdString","sellerIdUserObj","sellerIdStringid","wxouserIdString","wxouserIdWxouserObj","wxouserIdStringid","actString","actMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 微信用户关注历史记录
Wxousersubscribehis wxousersubscribehis = new Wxousersubscribehis(
	sellerId , //Integer 所属卖家   
	wxouserId , //String 微信openid   
	act , //Integer 操作 default=0  {'0':'取消关注','1':'关注'}
	null
);
*/
}
