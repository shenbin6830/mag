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
 * 频道
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "articlechannel")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Articlechannel extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "频道";

	//date formats
	
	//columns START
	/**序号ID Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**关键字 String  ID的补充 */
	@Length(max=20)
	
	private String ckey;
	/**父ID Integer default=0 0表示顶级 */
	@Max(99999999999L)
	
	private Integer parentid = 0;
	/**排列顺序 Integer default=99 数字越小排名越前 */
	@Max(99999999999L)
	
	private Integer priority = 99;
	/**孩子数量 Integer default=0 0表示最底 */
	@Max(99999999999L)
	
	private Integer childrennum = 0;
	/**祖先 String  逗号分开 */
	@Length(max=100)
	
	private String family;
	/**名称 String   */
	@Length(max=100)
	
	private String title;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Articlechannel _this){
		_this.parentid=0;
		_this.priority=99;
		_this.childrennum=0;
	}
	public Articlechannel(){
		makedefault(this);
	}
	public Articlechannel(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param ckey String 关键字  ID的补充 
	 * @param parentid Integer 父ID default=0 0表示顶级 
	 * @param priority Integer 排列顺序 default=99 数字越小排名越前 
	 * @param childrennum Integer 孩子数量 default=0 0表示最底 
	 * @param family String 祖先  逗号分开 
	 * @param title String 名称   
	* @param notuse String 没用
	 */
	public Articlechannel(String ckey ,Integer parentid ,Integer priority ,Integer childrennum ,String family ,String title ,String notuse) {
		super();
		this.ckey = ckey;
		this.parentid = parentid;
		this.priority = priority;
		this.childrennum = childrennum;
		this.family = family;
		this.title = title;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param ckey String 关键字  ID的补充 
	 * @param parentid Integer 父ID default=0 0表示顶级 
	 * @param priority Integer 排列顺序 default=99 数字越小排名越前 
	 * @param childrennum Integer 孩子数量 default=0 0表示最底 
	 * @param family String 祖先  逗号分开 
	 * @param title String 名称   
	* @param notuse String 没用
	 */
	public Articlechannel(Integer id ,String ckey ,Integer parentid ,Integer priority ,Integer childrennum ,String family ,String title ,String notuse) {
		super();
		this.id = id;
		this.ckey = ckey;
		this.parentid = parentid;
		this.priority = priority;
		this.childrennum = childrennum;
		this.family = family;
		this.title = title;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param ckey String 关键字  ID的补充 
	 * @param parentid Integer 父ID default=0 0表示顶级 
	 * @param priority Integer 排列顺序 default=99 数字越小排名越前 
	 * @param childrennum Integer 孩子数量 default=0 0表示最底 
	 * @param family String 祖先  逗号分开 
	 * @param title String 名称   
	* @param notuse String 没用
	 */
	public Articlechannel(Integer id ,String ckey ,Integer parentid ,Integer priority ,Integer childrennum ,String family ,String title ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.ckey = ckey;
		this.parentid = parentid;
		this.priority = priority;
		this.childrennum = childrennum;
		this.family = family;
		this.title = title;
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
	
	/**获取关键字 ID的补充 */
	

	@Column(name = "ckey", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getCkey() {
		return this.ckey;
	}
	/**设置关键字 ID的补充 */

	public void setCkey(String value) {
		this.ckey = value;
	}
	/**获取父ID 0表示顶级 */
	

	@Column(name = "parentid", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getParentid() {
		return this.parentid;
	}
	/**设置父ID 0表示顶级 */

	public void setParentid(Integer value) {
		this.parentid = value;
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
	/**获取孩子数量 0表示最底 */
	

	@Column(name = "childrennum", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getChildrennum() {
		return this.childrennum;
	}
	/**设置孩子数量 0表示最底 */

	public void setChildrennum(Integer value) {
		this.childrennum = value;
	}
	/**获取祖先 逗号分开 */
	

	@Column(name = "family", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getFamily() {
		return this.family;
	}
	/**设置祖先 逗号分开 */

	public void setFamily(String value) {
		this.family = value;
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
	/**父IDString*/
	private String parentidString;
	/**获取父IDString*/
	@Transient
	
	public String getParentidString() {
		return parentidString;
	}
	/**设置父IDString*/
	public void setParentidString(String value) {
		this.parentidString=value;
	}
	/**父IDStringid*/
	private String parentidStringid;
	/**获取父IDString*/
	@Transient
	
	public String getParentidStringid() {
		return parentidStringid;
	}
	/**设置父IDString*/
	public void setParentidStringid(String value) {
		this.parentidStringid=value;
	}
	/**孩子List*/
	private List<Articlechannel> children=new ArrayList<Articlechannel>();
	/**获取孩子List*/
	@Transient
	
	public List<Articlechannel> getChildren() {
		return children;
	}
	/**设置孩子List*/
	public void setChildren(List<Articlechannel> children) {
		this.children = children;
	}
	/**父母*/
	private Articlechannel parent=null;
	/**获取父母对象Articlechannel*/
	@Transient
	
	public Articlechannel getParent() {
		return parent;
	}
	/**设置父母对象Articlechannel*/
	public void setParent(Articlechannel parent) {
		this.parent = parent;
	}
	/**父母List*/
	private List<Articlechannel> parentList;
	/**获取父母List*/
	@Transient
	
	public List<Articlechannel> getParentList() {
		return parentList;
	}
	/**设置父母List*/
	public void setParentList(List<Articlechannel> parentList) {
		this.parentList = parentList;
	}
	/**TreeGrid用，形状open,close,null 关闭=close*/
	private String state="closed";
	/**TreeGrid用，形状open,close,null 关闭=close*/
	@Transient
	
	public String getState() {
		return state;
	}
	/**TreeGrid用，形状open,close,null 关闭=close*/
	public void setState(String state) {
		this.state = state;
	}
	/**TreeGrid用，已经被勾选上*/
	private boolean checked=false;
	/**TreeGrid用，已经被勾选上*/
	@Transient
	
	public boolean getChecked() {
		return checked;
	}
	/**TreeGrid用，已经被勾选上*/
	public void setChecked(boolean checked) {
		this.checked = checked;
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
		this.children=null;
		this.parent=null;
		this.parentList=null;
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
			.append(getCkey())
			.append(getParentid())
			.append(getPriority())
			.append(getChildrennum())
			.append(getFamily())
			.append(getTitle())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Articlechannel == false) return false;
		if(this == obj) return true;
		Articlechannel other = (Articlechannel)obj;
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
			.append(",Ckey:",getCkey())
			.append(",Parentid:",getParentid())
			.append(",Priority:",getPriority())
			.append(",Childrennum:",getChildrennum())
			.append(",Family:",getFamily())
			.append(",Title:",getTitle())
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
		String[] excludesProperties={"myname","mynameid","parentidString","parentidStringid","children","parent","parentList","state","checked","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","parentidString","parentidStringid","children","parent","parentList","state","checked","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 频道
Articlechannel articlechannel = new Articlechannel(
	ckey , //String 关键字  ID的补充 
	parentid , //Integer 父ID default=0 0表示顶级 
	priority , //Integer 排列顺序 default=99 数字越小排名越前 
	childrennum , //Integer 孩子数量 default=0 0表示最底 
	family , //String 祖先  逗号分开 
	title , //String 名称   
	null
);
*/
}
