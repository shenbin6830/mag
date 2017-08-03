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

import com.alibaba.fastjson.annotation.*;




/**
 * 用户分组
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wx_usergroup")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class WxUsergroup extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "用户分组";

	//date formats
	
	//columns START
	/**分组id Long  分组id，由微信分配 */
	@Max(999999999999999999L)
	
	private Long id;
	/**所属卖家 Integer   */
	@Max(99999999999L)
	
	private Integer sellerId;
	/**分组名字 String  分组名字，UTF8编码 */
	@Length(max=50)
	
	private String name;
	/**分组内用户数量 Integer   */
	@Max(99999999999L)
	
	private Integer count;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WxUsergroup _this){
	}
	public WxUsergroup(){
		makedefault(this);
	}
	public WxUsergroup(Long _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param sellerId Integer 所属卖家   
	 * @param name String 分组名字  分组名字，UTF8编码 
	 * @param count Integer 分组内用户数量   
	* @param notuse String 没用
	 */
	public WxUsergroup(Integer sellerId ,String name ,Integer count ,String notuse) {
		super();
		this.sellerId = sellerId;
		this.name = name;
		this.count = count;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Long 分组id  分组id，由微信分配 
	 * @param sellerId Integer 所属卖家   
	 * @param name String 分组名字  分组名字，UTF8编码 
	 * @param count Integer 分组内用户数量   
	* @param notuse String 没用
	 */
	public WxUsergroup(Long id ,Integer sellerId ,String name ,Integer count ,String notuse) {
		super();
		this.id = id;
		this.sellerId = sellerId;
		this.name = name;
		this.count = count;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Long 分组id  分组id，由微信分配 
	 * @param sellerId Integer 所属卖家   
	 * @param name String 分组名字  分组名字，UTF8编码 
	 * @param count Integer 分组内用户数量   
	* @param notuse String 没用
	 */
	public WxUsergroup(Long id ,Integer sellerId ,String name ,Integer count ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.sellerId = sellerId;
		this.name = name;
		this.count = count;
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
	public void setId(Long value) {
		this.id = value;
	}
	/**获取主键*/
	
@JSONField(name="id")
	@Id 
	@Column(name = "id",  unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public Long getId() {
		return this.id;
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
	/**获取分组名字 分组名字，UTF8编码 */
	
@JSONField(name="name")
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getName() {
		return this.name;
	}
	/**设置分组名字 分组名字，UTF8编码 */
@JSONField(name="name")
	public void setName(String value) {
		this.name = value;
	}
	/**获取分组内用户数量  */
	
@JSONField(name="count")
	@Column(name = "count", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getCount() {
		return this.count;
	}
	/**设置分组内用户数量  */
@JSONField(name="count")
	public void setCount(Integer value) {
		this.count = value;
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
			.append(getSellerId())
			.append(getName())
			.append(getCount())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof WxUsergroup == false) return false;
		if(this == obj) return true;
		WxUsergroup other = (WxUsergroup)obj;
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
			.append(",SellerId:",getSellerId())
			.append(",Name:",getName())
			.append(",Count:",getCount())
			.toString();

	}
	/**
	 * 返回JSON之不显示第0级，对于一些不能传递的对象，不进行json编码，比如password明文，用得较少。
	 * @return
	 */
	public String toJsonNotshow(){
		String[] excludesProperties={"sellerId","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第1级，nowshow+notshow1('notshow1'+xxxObj+xxxString+xxxList)
	 * <br>在内部服务通讯时，防止get页面参数过大，通常设置为notshow1，返回时可以是notshow或notshow1
	 * @return
	 */
	public String toJsonNotshow1(){
		String[] excludesProperties={"sellerId","myname","mynameid","sellerIdString","sellerIdUserObj","sellerIdStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"sellerId","myname","mynameid","sellerIdString","sellerIdUserObj","sellerIdStringid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 用户分组
WxUsergroup wxUsergroup = new WxUsergroup(
	sellerId , //Integer 所属卖家   
	name , //String 分组名字  分组名字，UTF8编码 
	count , //Integer 分组内用户数量   
	null
);
*/
}
