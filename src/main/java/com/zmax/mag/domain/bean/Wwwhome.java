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
 * 手机页首页配置
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wwwhome")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Wwwhome extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "手机页首页配置";

	//date formats
	
	//columns START
	/**所属用户 Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**图标 String  160x160 */
	@Length(max=200)
	
	private String logo;
	/**分享转发说明 String   */
	@Length(max=100)
	
	private String sharetxt;
	/**图1 String  640x380 */
	@Length(max=200)
	
	private String headimg1;
	/**图说明1 String   */
	@Length(max=50)
	
	private String headimgtxt1;
	/**图链接1 String  #开头的表示本地地址 */
	@Length(max=100)
	
	private String headimglink1;
	/**图2 String  640x380 */
	@Length(max=200)
	
	private String headimg2;
	/**图说明2 String   */
	@Length(max=50)
	
	private String headimgtxt2;
	/**图链接2 String   */
	@Length(max=100)
	
	private String headimglink2;
	/**图3 String  640x380 */
	@Length(max=200)
	
	private String headimg3;
	/**图说明3 String   */
	@Length(max=50)
	
	private String headimgtxt3;
	/**图链接3 String   */
	@Length(max=100)
	
	private String headimglink3;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Wwwhome _this){
	}
	public Wwwhome(){
		makedefault(this);
	}
	public Wwwhome(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param logo String 图标  160x160 
	 * @param sharetxt String 分享转发说明   
	 * @param headimg1 String 图1  640x380 
	 * @param headimgtxt1 String 图说明1   
	 * @param headimglink1 String 图链接1  #开头的表示本地地址 
	 * @param headimg2 String 图2  640x380 
	 * @param headimgtxt2 String 图说明2   
	 * @param headimglink2 String 图链接2   
	 * @param headimg3 String 图3  640x380 
	 * @param headimgtxt3 String 图说明3   
	 * @param headimglink3 String 图链接3   
	* @param notuse String 没用
	 */
	public Wwwhome(String logo ,String sharetxt ,String headimg1 ,String headimgtxt1 ,String headimglink1 ,String headimg2 ,String headimgtxt2 ,String headimglink2 ,String headimg3 ,String headimgtxt3 ,String headimglink3 ,String notuse) {
		super();
		this.logo = logo;
		this.sharetxt = sharetxt;
		this.headimg1 = headimg1;
		this.headimgtxt1 = headimgtxt1;
		this.headimglink1 = headimglink1;
		this.headimg2 = headimg2;
		this.headimgtxt2 = headimgtxt2;
		this.headimglink2 = headimglink2;
		this.headimg3 = headimg3;
		this.headimgtxt3 = headimgtxt3;
		this.headimglink3 = headimglink3;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 所属用户   
	 * @param logo String 图标  160x160 
	 * @param sharetxt String 分享转发说明   
	 * @param headimg1 String 图1  640x380 
	 * @param headimgtxt1 String 图说明1   
	 * @param headimglink1 String 图链接1  #开头的表示本地地址 
	 * @param headimg2 String 图2  640x380 
	 * @param headimgtxt2 String 图说明2   
	 * @param headimglink2 String 图链接2   
	 * @param headimg3 String 图3  640x380 
	 * @param headimgtxt3 String 图说明3   
	 * @param headimglink3 String 图链接3   
	* @param notuse String 没用
	 */
	public Wwwhome(Integer id ,String logo ,String sharetxt ,String headimg1 ,String headimgtxt1 ,String headimglink1 ,String headimg2 ,String headimgtxt2 ,String headimglink2 ,String headimg3 ,String headimgtxt3 ,String headimglink3 ,String notuse) {
		super();
		this.id = id;
		this.logo = logo;
		this.sharetxt = sharetxt;
		this.headimg1 = headimg1;
		this.headimgtxt1 = headimgtxt1;
		this.headimglink1 = headimglink1;
		this.headimg2 = headimg2;
		this.headimgtxt2 = headimgtxt2;
		this.headimglink2 = headimglink2;
		this.headimg3 = headimg3;
		this.headimgtxt3 = headimgtxt3;
		this.headimglink3 = headimglink3;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 所属用户   
	 * @param logo String 图标  160x160 
	 * @param sharetxt String 分享转发说明   
	 * @param headimg1 String 图1  640x380 
	 * @param headimgtxt1 String 图说明1   
	 * @param headimglink1 String 图链接1  #开头的表示本地地址 
	 * @param headimg2 String 图2  640x380 
	 * @param headimgtxt2 String 图说明2   
	 * @param headimglink2 String 图链接2   
	 * @param headimg3 String 图3  640x380 
	 * @param headimgtxt3 String 图说明3   
	 * @param headimglink3 String 图链接3   
	* @param notuse String 没用
	 */
	public Wwwhome(Integer id ,String logo ,String sharetxt ,String headimg1 ,String headimgtxt1 ,String headimglink1 ,String headimg2 ,String headimgtxt2 ,String headimglink2 ,String headimg3 ,String headimgtxt3 ,String headimglink3 ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.logo = logo;
		this.sharetxt = sharetxt;
		this.headimg1 = headimg1;
		this.headimgtxt1 = headimgtxt1;
		this.headimglink1 = headimglink1;
		this.headimg2 = headimg2;
		this.headimgtxt2 = headimgtxt2;
		this.headimglink2 = headimglink2;
		this.headimg3 = headimg3;
		this.headimgtxt3 = headimgtxt3;
		this.headimglink3 = headimglink3;
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
	
	/**获取图标 160x160 */
	

	@Column(name = "logo", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getLogo() {
		return this.logo;
	}
	/**设置图标 160x160 */

	public void setLogo(String value) {
		this.logo = value;
	}
	/**获取分享转发说明  */
	

	@Column(name = "sharetxt", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getSharetxt() {
		return this.sharetxt;
	}
	/**设置分享转发说明  */

	public void setSharetxt(String value) {
		this.sharetxt = value;
	}
	/**获取图1 640x380 */
	

	@Column(name = "headimg1", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getHeadimg1() {
		return this.headimg1;
	}
	/**设置图1 640x380 */

	public void setHeadimg1(String value) {
		this.headimg1 = value;
	}
	/**获取图说明1  */
	

	@Column(name = "headimgtxt1", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getHeadimgtxt1() {
		return this.headimgtxt1;
	}
	/**设置图说明1  */

	public void setHeadimgtxt1(String value) {
		this.headimgtxt1 = value;
	}
	/**获取图链接1 #开头的表示本地地址 */
	

	@Column(name = "headimglink1", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getHeadimglink1() {
		return this.headimglink1;
	}
	/**设置图链接1 #开头的表示本地地址 */

	public void setHeadimglink1(String value) {
		this.headimglink1 = value;
	}
	/**获取图2 640x380 */
	

	@Column(name = "headimg2", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getHeadimg2() {
		return this.headimg2;
	}
	/**设置图2 640x380 */

	public void setHeadimg2(String value) {
		this.headimg2 = value;
	}
	/**获取图说明2  */
	

	@Column(name = "headimgtxt2", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getHeadimgtxt2() {
		return this.headimgtxt2;
	}
	/**设置图说明2  */

	public void setHeadimgtxt2(String value) {
		this.headimgtxt2 = value;
	}
	/**获取图链接2  */
	

	@Column(name = "headimglink2", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getHeadimglink2() {
		return this.headimglink2;
	}
	/**设置图链接2  */

	public void setHeadimglink2(String value) {
		this.headimglink2 = value;
	}
	/**获取图3 640x380 */
	

	@Column(name = "headimg3", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getHeadimg3() {
		return this.headimg3;
	}
	/**设置图3 640x380 */

	public void setHeadimg3(String value) {
		this.headimg3 = value;
	}
	/**获取图说明3  */
	

	@Column(name = "headimgtxt3", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getHeadimgtxt3() {
		return this.headimgtxt3;
	}
	/**设置图说明3  */

	public void setHeadimgtxt3(String value) {
		this.headimgtxt3 = value;
	}
	/**获取图链接3  */
	

	@Column(name = "headimglink3", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getHeadimglink3() {
		return this.headimglink3;
	}
	/**设置图链接3  */

	public void setHeadimglink3(String value) {
		this.headimglink3 = value;
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
			.append(getLogo())
			.append(getSharetxt())
			.append(getHeadimg1())
			.append(getHeadimgtxt1())
			.append(getHeadimglink1())
			.append(getHeadimg2())
			.append(getHeadimgtxt2())
			.append(getHeadimglink2())
			.append(getHeadimg3())
			.append(getHeadimgtxt3())
			.append(getHeadimglink3())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Wwwhome == false) return false;
		if(this == obj) return true;
		Wwwhome other = (Wwwhome)obj;
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
			.append(",Logo:",getLogo())
			.append(",Sharetxt:",getSharetxt())
			.append(",Headimg1:",getHeadimg1())
			.append(",Headimgtxt1:",getHeadimgtxt1())
			.append(",Headimglink1:",getHeadimglink1())
			.append(",Headimg2:",getHeadimg2())
			.append(",Headimgtxt2:",getHeadimgtxt2())
			.append(",Headimglink2:",getHeadimglink2())
			.append(",Headimg3:",getHeadimg3())
			.append(",Headimgtxt3:",getHeadimgtxt3())
			.append(",Headimglink3:",getHeadimglink3())
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
		String[] excludesProperties={"myname","mynameid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 手机页首页配置
Wwwhome wwwhome = new Wwwhome(
	logo , //String 图标  160x160 
	sharetxt , //String 分享转发说明   
	headimg1 , //String 图1  640x380 
	headimgtxt1 , //String 图说明1   
	headimglink1 , //String 图链接1  #开头的表示本地地址 
	headimg2 , //String 图2  640x380 
	headimgtxt2 , //String 图说明2   
	headimglink2 , //String 图链接2   
	headimg3 , //String 图3  640x380 
	headimgtxt3 , //String 图说明3   
	headimglink3 , //String 图链接3   
	null
);
*/
}
