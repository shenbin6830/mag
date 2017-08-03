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
 * 会员<br/>主要包括设计师，组织等
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "member")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Member extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "会员";

	//date formats
	
	//columns START
	/**用户ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**用户类型 Integer default=0 专家/大师可以回答，会员不行 map={'0':'会员','1':'大师'}*/
	@Max(999L)
	
	private Integer mtype = 0;
	/**咨询费 Double default=0.0 别人问我问题需要付的钱 */
	
	
	private Double price;
	/**姓名或名称 String   */
	@Length(max=50)
	
	private String name;
	/**简介 String   */
	@Length(max=1000)
	
	private String intro;
	/**头像图 String  200x200 */
	@Length(max=200)
	
	private String img1;
	/**二维码图 String  200x200,大师才有 */
	@Length(max=200)
	
	private String imgqr;
	/**类型之个人企业 Integer default=0 用这个区分是企业还是个人 map={'0':'个人','1':'企业'}*/
	@Max(999L)
	
	private Integer idtype = 0;
	/**证件号码 String  企业是营业执照，个人是身份证 */
	@Length(max=30)
	
	private String idnum;
	/**地址 String   */
	@Length(max=200)
	
	private String addr;
	/**邮编 String   */
	@Length(max=6)
	
	private String zip;
	/**手机 String   */
	@Length(max=20)
	
	private String mobile;
	/**电子邮件 String   */
	@Length(max=50)
	
	private String email;
	/**积分 Integer default=0 积分余额，是通过流水和统计算出来的 */
	@Max(99999999999L)
	
	private Integer score = 0;
	/**余额 Integer default=0 单位分，现金余额，是通过流水和统计算出来的 */
	@Max(99999999999L)
	
	private Integer cash = 0;
	/**经验 Integer default=0 单位分，是通过流水和统计算出来的 */
	@Max(99999999999L)
	
	private Integer exp = 0;
	/**等级 Integer default=0 通过经验计算出 */
	@Max(99999999999L)
	
	private Integer mlevel = 0;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Member _this){
		_this.mtype=0;
		_this.price=0.0;
		_this.idtype=0;
		_this.score=0;
		_this.cash=0;
		_this.exp=0;
		_this.mlevel=0;
	}
	public Member(){
		makedefault(this);
	}
	public Member(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param mtype Integer 用户类型 default=0 专家/大师可以回答，会员不行 {'0':'会员','1':'大师'}
	 * @param price Double 咨询费 default=0.0 别人问我问题需要付的钱 
	 * @param name String 姓名或名称   
	 * @param intro String 简介   
	 * @param img1 String 头像图  200x200 
	 * @param imgqr String 二维码图  200x200,大师才有 
	 * @param idtype Integer 类型之个人企业 default=0 用这个区分是企业还是个人 {'0':'个人','1':'企业'}
	 * @param idnum String 证件号码  企业是营业执照，个人是身份证 
	 * @param addr String 地址   
	 * @param zip String 邮编   
	 * @param mobile String 手机   
	 * @param email String 电子邮件   
	 * @param score Integer 积分 default=0 积分余额，是通过流水和统计算出来的 
	 * @param cash Integer 余额 default=0 单位分，现金余额，是通过流水和统计算出来的 
	 * @param exp Integer 经验 default=0 单位分，是通过流水和统计算出来的 
	 * @param mlevel Integer 等级 default=0 通过经验计算出 
	* @param notuse String 没用
	 */
	public Member(Integer mtype ,Double price ,String name ,String intro ,String img1 ,String imgqr ,Integer idtype ,String idnum ,String addr ,String zip ,String mobile ,String email ,Integer score ,Integer cash ,Integer exp ,Integer mlevel ,String notuse) {
		super();
		this.mtype = mtype;
		this.price = price;
		this.name = name;
		this.intro = intro;
		this.img1 = img1;
		this.imgqr = imgqr;
		this.idtype = idtype;
		this.idnum = idnum;
		this.addr = addr;
		this.zip = zip;
		this.mobile = mobile;
		this.email = email;
		this.score = score;
		this.cash = cash;
		this.exp = exp;
		this.mlevel = mlevel;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 用户ID   
	 * @param mtype Integer 用户类型 default=0 专家/大师可以回答，会员不行 {'0':'会员','1':'大师'}
	 * @param price Double 咨询费 default=0.0 别人问我问题需要付的钱 
	 * @param name String 姓名或名称   
	 * @param intro String 简介   
	 * @param img1 String 头像图  200x200 
	 * @param imgqr String 二维码图  200x200,大师才有 
	 * @param idtype Integer 类型之个人企业 default=0 用这个区分是企业还是个人 {'0':'个人','1':'企业'}
	 * @param idnum String 证件号码  企业是营业执照，个人是身份证 
	 * @param addr String 地址   
	 * @param zip String 邮编   
	 * @param mobile String 手机   
	 * @param email String 电子邮件   
	 * @param score Integer 积分 default=0 积分余额，是通过流水和统计算出来的 
	 * @param cash Integer 余额 default=0 单位分，现金余额，是通过流水和统计算出来的 
	 * @param exp Integer 经验 default=0 单位分，是通过流水和统计算出来的 
	 * @param mlevel Integer 等级 default=0 通过经验计算出 
	* @param notuse String 没用
	 */
	public Member(Integer id ,Integer mtype ,Double price ,String name ,String intro ,String img1 ,String imgqr ,Integer idtype ,String idnum ,String addr ,String zip ,String mobile ,String email ,Integer score ,Integer cash ,Integer exp ,Integer mlevel ,String notuse) {
		super();
		this.id = id;
		this.mtype = mtype;
		this.price = price;
		this.name = name;
		this.intro = intro;
		this.img1 = img1;
		this.imgqr = imgqr;
		this.idtype = idtype;
		this.idnum = idnum;
		this.addr = addr;
		this.zip = zip;
		this.mobile = mobile;
		this.email = email;
		this.score = score;
		this.cash = cash;
		this.exp = exp;
		this.mlevel = mlevel;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 用户ID   
	 * @param mtype Integer 用户类型 default=0 专家/大师可以回答，会员不行 {'0':'会员','1':'大师'}
	 * @param price Double 咨询费 default=0.0 别人问我问题需要付的钱 
	 * @param name String 姓名或名称   
	 * @param intro String 简介   
	 * @param img1 String 头像图  200x200 
	 * @param imgqr String 二维码图  200x200,大师才有 
	 * @param idtype Integer 类型之个人企业 default=0 用这个区分是企业还是个人 {'0':'个人','1':'企业'}
	 * @param idnum String 证件号码  企业是营业执照，个人是身份证 
	 * @param addr String 地址   
	 * @param zip String 邮编   
	 * @param mobile String 手机   
	 * @param email String 电子邮件   
	 * @param score Integer 积分 default=0 积分余额，是通过流水和统计算出来的 
	 * @param cash Integer 余额 default=0 单位分，现金余额，是通过流水和统计算出来的 
	 * @param exp Integer 经验 default=0 单位分，是通过流水和统计算出来的 
	 * @param mlevel Integer 等级 default=0 通过经验计算出 
	* @param notuse String 没用
	 */
	public Member(Integer id ,Integer mtype ,Double price ,String name ,String intro ,String img1 ,String imgqr ,Integer idtype ,String idnum ,String addr ,String zip ,String mobile ,String email ,Integer score ,Integer cash ,Integer exp ,Integer mlevel ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.mtype = mtype;
		this.price = price;
		this.name = name;
		this.intro = intro;
		this.img1 = img1;
		this.imgqr = imgqr;
		this.idtype = idtype;
		this.idnum = idnum;
		this.addr = addr;
		this.zip = zip;
		this.mobile = mobile;
		this.email = email;
		this.score = score;
		this.cash = cash;
		this.exp = exp;
		this.mlevel = mlevel;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+name+" ":myname;
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
		mynameid=(mynameid==null)?"["+id+"]"+name+" ":mynameid;
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
	
	/**获取用户类型 专家/大师可以回答，会员不行 {'0':'会员','1':'大师'}*/
	

	@Column(name = "mtype", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getMtype() {
		return this.mtype;
	}
	/**设置用户类型 专家/大师可以回答，会员不行 {'0':'会员','1':'大师'}*/

	public void setMtype(Integer value) {
		this.mtype = value;
	}
	/**获取咨询费 别人问我问题需要付的钱 */
	

	@Column(name = "price", columnDefinition="double(11,3) default '0.00'", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Double getPrice() {
		return this.price;
	}
	/**设置咨询费 别人问我问题需要付的钱 */

	public void setPrice(Double value) {
		this.price = value;
	}
	/**获取姓名或名称  */
	

	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getName() {
		return this.name;
	}
	/**设置姓名或名称  */

	public void setName(String value) {
		this.name = value;
	}
	/**获取简介  */
	

	@Column(name = "intro", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getIntro() {
		return this.intro;
	}
	/**设置简介  */

	public void setIntro(String value) {
		this.intro = value;
	}
	/**获取头像图 200x200 */
	

	@Column(name = "img1", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getImg1() {
		return this.img1;
	}
	/**设置头像图 200x200 */

	public void setImg1(String value) {
		this.img1 = value;
	}
	/**获取二维码图 200x200,大师才有 */
	

	@Column(name = "imgqr", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getImgqr() {
		return this.imgqr;
	}
	/**设置二维码图 200x200,大师才有 */

	public void setImgqr(String value) {
		this.imgqr = value;
	}
	/**获取类型之个人企业 用这个区分是企业还是个人 {'0':'个人','1':'企业'}*/
	

	@Column(name = "idtype", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIdtype() {
		return this.idtype;
	}
	/**设置类型之个人企业 用这个区分是企业还是个人 {'0':'个人','1':'企业'}*/

	public void setIdtype(Integer value) {
		this.idtype = value;
	}
	/**获取证件号码 企业是营业执照，个人是身份证 */
	

	@Column(name = "idnum", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getIdnum() {
		return this.idnum;
	}
	/**设置证件号码 企业是营业执照，个人是身份证 */

	public void setIdnum(String value) {
		this.idnum = value;
	}
	/**获取地址  */
	

	@Column(name = "addr", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getAddr() {
		return this.addr;
	}
	/**设置地址  */

	public void setAddr(String value) {
		this.addr = value;
	}
	/**获取邮编  */
	

	@Column(name = "zip", unique = false, nullable = true, insertable = true, updatable = true, length = 6)
	public String getZip() {
		return this.zip;
	}
	/**设置邮编  */

	public void setZip(String value) {
		this.zip = value;
	}
	/**获取手机  */
	

	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getMobile() {
		return this.mobile;
	}
	/**设置手机  */

	public void setMobile(String value) {
		this.mobile = value;
	}
	/**获取电子邮件  */
	

	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getEmail() {
		return this.email;
	}
	/**设置电子邮件  */

	public void setEmail(String value) {
		this.email = value;
	}
	/**获取积分 积分余额，是通过流水和统计算出来的 */
	

	@Column(name = "score", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getScore() {
		return this.score;
	}
	/**设置积分 积分余额，是通过流水和统计算出来的 */

	public void setScore(Integer value) {
		this.score = value;
	}
	/**获取余额 单位分，现金余额，是通过流水和统计算出来的 */
	

	@Column(name = "cash", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getCash() {
		return this.cash;
	}
	/**设置余额 单位分，现金余额，是通过流水和统计算出来的 */

	public void setCash(Integer value) {
		this.cash = value;
	}
	/**获取经验 单位分，是通过流水和统计算出来的 */
	

	@Column(name = "exp", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getExp() {
		return this.exp;
	}
	/**设置经验 单位分，是通过流水和统计算出来的 */

	public void setExp(Integer value) {
		this.exp = value;
	}
	/**获取等级 通过经验计算出 */
	

	@Column(name = "mlevel", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getMlevel() {
		return this.mlevel;
	}
	/**设置等级 通过经验计算出 */

	public void setMlevel(Integer value) {
		this.mlevel = value;
	}
	/**用户类型String*/
	private String mtypeString;
	/**获取用户类型String*/
	@Transient
	
	public String getMtypeString() {
		return mtypeString;
	}
	/**设置用户类型String*/
	public void setMtypeString(String value) {
		this.mtypeString=value;
	}
	/**用户类型Map*/
	private Map<String, Object> mtypeMap;
	/**获取用户类型Map*/
	@Transient
	
	public Map<String, Object> getMtypeMap() {
		return mtypeMap;
	}
	/**设置用户类型Map*/
	public void setMtypeMap(Map<String, Object> value) {
		this.mtypeMap=value;
	}
	/**类型之个人企业String*/
	private String idtypeString;
	/**获取类型之个人企业String*/
	@Transient
	
	public String getIdtypeString() {
		return idtypeString;
	}
	/**设置类型之个人企业String*/
	public void setIdtypeString(String value) {
		this.idtypeString=value;
	}
	/**类型之个人企业Map*/
	private Map<String, Object> idtypeMap;
	/**获取类型之个人企业Map*/
	@Transient
	
	public Map<String, Object> getIdtypeMap() {
		return idtypeMap;
	}
	/**设置类型之个人企业Map*/
	public void setIdtypeMap(Map<String, Object> value) {
		this.idtypeMap=value;
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
		this.mtypeMap=null;
		this.idtypeMap=null;
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
			.append(getMtype())
			.append(getPrice())
			.append(getName())
			.append(getIntro())
			.append(getImg1())
			.append(getImgqr())
			.append(getIdtype())
			.append(getIdnum())
			.append(getAddr())
			.append(getZip())
			.append(getMobile())
			.append(getEmail())
			.append(getScore())
			.append(getCash())
			.append(getExp())
			.append(getMlevel())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Member == false) return false;
		if(this == obj) return true;
		Member other = (Member)obj;
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
			.append(",Mtype:",getMtype())
			.append(",Price:",getPrice())
			.append(",Name:",getName())
			.append(",Intro:",getIntro())
			.append(",Img1:",getImg1())
			.append(",Imgqr:",getImgqr())
			.append(",Idtype:",getIdtype())
			.append(",Idnum:",getIdnum())
			.append(",Addr:",getAddr())
			.append(",Zip:",getZip())
			.append(",Mobile:",getMobile())
			.append(",Email:",getEmail())
			.append(",Score:",getScore())
			.append(",Cash:",getCash())
			.append(",Exp:",getExp())
			.append(",Mlevel:",getMlevel())
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
		String[] excludesProperties={"myname","mynameid","mtypeString","mtypeMap","idtypeString","idtypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","mtypeString","mtypeMap","idtypeString","idtypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 会员
Member member = new Member(
	mtype , //Integer 用户类型 default=0 专家/大师可以回答，会员不行 {'0':'会员','1':'大师'}
	price , //Double 咨询费 default=0.0 别人问我问题需要付的钱 
	name , //String 姓名或名称   
	intro , //String 简介   
	img1 , //String 头像图  200x200 
	imgqr , //String 二维码图  200x200,大师才有 
	idtype , //Integer 类型之个人企业 default=0 用这个区分是企业还是个人 {'0':'个人','1':'企业'}
	idnum , //String 证件号码  企业是营业执照，个人是身份证 
	addr , //String 地址   
	zip , //String 邮编   
	mobile , //String 手机   
	email , //String 电子邮件   
	score , //Integer 积分 default=0 积分余额，是通过流水和统计算出来的 
	cash , //Integer 余额 default=0 单位分，现金余额，是通过流水和统计算出来的 
	exp , //Integer 经验 default=0 单位分，是通过流水和统计算出来的 
	mlevel , //Integer 等级 default=0 通过经验计算出 
	null
);
*/
}
