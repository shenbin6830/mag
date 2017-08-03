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
 * 二维码场景及转换<br/>场景id或str转自定义参数，用于临时二维码关注等地方
<br>图在http://weix1.maykeys.com/weix/res/img/qrcode/wx_166.JPG(普通关注的图片地址)
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "wa_qrcodeticket_scene")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class WaQrcodeticketScene extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "二维码场景及转换";

	//date formats
	
	//columns START
	/**序号ID Integer  即sceneId */
	@Max(99999999999L)
	
	private Integer id;
	/**场景值ID字符串形式 String  字符串类型，长度限制为1到64，仅永久二维码支持此字段 */
	@Length(max=64)
	
	private String sceneStr;
	/**操作类型 String   */
	@Length(max=30)
	
	private String actType;
	/**操作参数分隔符 String default=, 默认逗号 */
	@Length(max=2)
	
	private String actDetailSplit;
	/**操作详情或参数 String  主要用于唯一性查询 */
	@Length(max=200)
	
	private String actDetail;
	/**是否为临时二维码 Integer default=0  map={'0':'是临时二维码','1':'是永久二维码'}*/
	@Max(999L)
	
	private Integer statusTemporary = 0;
	/**是否有效 Integer default=1 是否有效主要针对于永久二维码，临时二维码直接设置为有效即可 map={'0':'无效','1':'有效'}*/
	@Max(999L)
	
	private Integer statusValid = 1;
	/**图1*/
	private String img1;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaQrcodeticketScene _this){
		_this.actDetailSplit=",";
		_this.statusTemporary=0;
		_this.statusValid=1;
	}
	public WaQrcodeticketScene(){
		makedefault(this);
	}
	public WaQrcodeticketScene(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param sceneStr String 场景值ID字符串形式  字符串类型，长度限制为1到64，仅永久二维码支持此字段 
	 * @param actType String 操作类型   
	 * @param actDetailSplit String 操作参数分隔符 default=, 默认逗号 
	 * @param actDetail String 操作详情或参数  主要用于唯一性查询 
	 * @param statusTemporary Integer 是否为临时二维码 default=0  {'0':'是临时二维码','1':'是永久二维码'}
	 * @param statusValid Integer 是否有效 default=1 是否有效主要针对于永久二维码，临时二维码直接设置为有效即可 {'0':'无效','1':'有效'}
	* @param notuse String 没用
	 */
	public WaQrcodeticketScene(String sceneStr ,String actType ,String actDetailSplit ,String actDetail ,Integer statusTemporary ,Integer statusValid ,String notuse) {
		super();
		this.sceneStr = sceneStr;
		this.actType = actType;
		this.actDetailSplit = actDetailSplit;
		this.actDetail = actDetail;
		this.statusTemporary = statusTemporary;
		this.statusValid = statusValid;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID  即sceneId 
	 * @param sceneStr String 场景值ID字符串形式  字符串类型，长度限制为1到64，仅永久二维码支持此字段 
	 * @param actType String 操作类型   
	 * @param actDetailSplit String 操作参数分隔符 default=, 默认逗号 
	 * @param actDetail String 操作详情或参数  主要用于唯一性查询 
	 * @param statusTemporary Integer 是否为临时二维码 default=0  {'0':'是临时二维码','1':'是永久二维码'}
	 * @param statusValid Integer 是否有效 default=1 是否有效主要针对于永久二维码，临时二维码直接设置为有效即可 {'0':'无效','1':'有效'}
	* @param notuse String 没用
	 */
	public WaQrcodeticketScene(Integer id ,String sceneStr ,String actType ,String actDetailSplit ,String actDetail ,Integer statusTemporary ,Integer statusValid ,String notuse) {
		super();
		this.id = id;
		this.sceneStr = sceneStr;
		this.actType = actType;
		this.actDetailSplit = actDetailSplit;
		this.actDetail = actDetail;
		this.statusTemporary = statusTemporary;
		this.statusValid = statusValid;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID  即sceneId 
	 * @param sceneStr String 场景值ID字符串形式  字符串类型，长度限制为1到64，仅永久二维码支持此字段 
	 * @param actType String 操作类型   
	 * @param actDetailSplit String 操作参数分隔符 default=, 默认逗号 
	 * @param actDetail String 操作详情或参数  主要用于唯一性查询 
	 * @param statusTemporary Integer 是否为临时二维码 default=0  {'0':'是临时二维码','1':'是永久二维码'}
	 * @param statusValid Integer 是否有效 default=1 是否有效主要针对于永久二维码，临时二维码直接设置为有效即可 {'0':'无效','1':'有效'}
	 * @param img1 String 图1  160x160 
	* @param notuse String 没用
	 */
	public WaQrcodeticketScene(Integer id ,String sceneStr ,String actType ,String actDetailSplit ,String actDetail ,Integer statusTemporary ,Integer statusValid ,String img1 ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.sceneStr = sceneStr;
		this.actType = actType;
		this.actDetailSplit = actDetailSplit;
		this.actDetail = actDetail;
		this.statusTemporary = statusTemporary;
		this.statusValid = statusValid;
		this.img1 = img1;
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
	
@JSONField(name="scene_id")
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "identity")
	@Column(name = "id",  unique = false, nullable = false, insertable = true, updatable = true, length = 11)
	public Integer getId() {
		return this.id;
	}
	
	/**获取场景值ID字符串形式 字符串类型，长度限制为1到64，仅永久二维码支持此字段 */
	
@JSONField(name="scene_str")
	@Column(name = "scene_str", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getSceneStr() {
		return this.sceneStr;
	}
	/**设置场景值ID字符串形式 字符串类型，长度限制为1到64，仅永久二维码支持此字段 */
@JSONField(name="scene_str")
	public void setSceneStr(String value) {
		this.sceneStr = value;
	}
	/**获取操作类型  */
	

	@Column(name = "act_type", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getActType() {
		return this.actType;
	}
	/**设置操作类型  */

	public void setActType(String value) {
		this.actType = value;
	}
	/**获取操作参数分隔符 默认逗号 */
	

	@Column(name = "act_detail_split", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public String getActDetailSplit() {
		return this.actDetailSplit;
	}
	/**设置操作参数分隔符 默认逗号 */

	public void setActDetailSplit(String value) {
		this.actDetailSplit = value;
	}
	/**获取操作详情或参数 主要用于唯一性查询 */
	

	@Column(name = "act_detail", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getActDetail() {
		return this.actDetail;
	}
	/**设置操作详情或参数 主要用于唯一性查询 */

	public void setActDetail(String value) {
		this.actDetail = value;
	}
	/**获取是否为临时二维码  {'0':'是临时二维码','1':'是永久二维码'}*/
	

	@Column(name = "status_temporary", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatusTemporary() {
		return this.statusTemporary;
	}
	/**设置是否为临时二维码  {'0':'是临时二维码','1':'是永久二维码'}*/

	public void setStatusTemporary(Integer value) {
		this.statusTemporary = value;
	}
	/**获取是否有效 是否有效主要针对于永久二维码，临时二维码直接设置为有效即可 {'0':'无效','1':'有效'}*/
	

	@Column(name = "status_valid", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatusValid() {
		return this.statusValid;
	}
	/**设置是否有效 是否有效主要针对于永久二维码，临时二维码直接设置为有效即可 {'0':'无效','1':'有效'}*/

	public void setStatusValid(Integer value) {
		this.statusValid = value;
	}
	/**对象 获取图1 160x160 */
	@Transient
	

	public String getImg1() {
		return this.img1;
	}
	/**设置图1 160x160 */

	public void setImg1(String value) {
		this.img1 = value;
	}
	/**是否为临时二维码String*/
	private String statusTemporaryString;
	/**获取是否为临时二维码String*/
	@Transient
	
	public String getStatusTemporaryString() {
		return statusTemporaryString;
	}
	/**设置是否为临时二维码String*/
	public void setStatusTemporaryString(String value) {
		this.statusTemporaryString=value;
	}
	/**是否为临时二维码Map*/
	private Map<String, Object> statusTemporaryMap;
	/**获取是否为临时二维码Map*/
	@Transient
	
	public Map<String, Object> getStatusTemporaryMap() {
		return statusTemporaryMap;
	}
	/**设置是否为临时二维码Map*/
	public void setStatusTemporaryMap(Map<String, Object> value) {
		this.statusTemporaryMap=value;
	}
	/**是否有效String*/
	private String statusValidString;
	/**获取是否有效String*/
	@Transient
	
	public String getStatusValidString() {
		return statusValidString;
	}
	/**设置是否有效String*/
	public void setStatusValidString(String value) {
		this.statusValidString=value;
	}
	/**是否有效Map*/
	private Map<String, Object> statusValidMap;
	/**获取是否有效Map*/
	@Transient
	
	public Map<String, Object> getStatusValidMap() {
		return statusValidMap;
	}
	/**设置是否有效Map*/
	public void setStatusValidMap(Map<String, Object> value) {
		this.statusValidMap=value;
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
		this.statusTemporaryMap=null;
		this.statusValidMap=null;
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
			.append(getSceneStr())
			.append(getActType())
			.append(getActDetailSplit())
			.append(getActDetail())
			.append(getStatusTemporary())
			.append(getStatusValid())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof WaQrcodeticketScene == false) return false;
		if(this == obj) return true;
		WaQrcodeticketScene other = (WaQrcodeticketScene)obj;
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
			.append(",SceneStr:",getSceneStr())
			.append(",ActType:",getActType())
			.append(",ActDetailSplit:",getActDetailSplit())
			.append(",ActDetail:",getActDetail())
			.append(",StatusTemporary:",getStatusTemporary())
			.append(",StatusValid:",getStatusValid())
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
		String[] excludesProperties={"myname","mynameid","statusTemporaryString","statusTemporaryMap","statusValidString","statusValidMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"actType","actDetailSplit","actDetail","statusTemporary","statusValid","img1","myname","mynameid","statusTemporaryString","statusTemporaryMap","statusValidString","statusValidMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 二维码场景及转换
WaQrcodeticketScene waQrcodeticketScene = new WaQrcodeticketScene(
	sceneStr , //String 场景值ID字符串形式  字符串类型，长度限制为1到64，仅永久二维码支持此字段 
	actType , //String 操作类型   
	actDetailSplit , //String 操作参数分隔符 default=, 默认逗号 
	actDetail , //String 操作详情或参数  主要用于唯一性查询 
	statusTemporary , //Integer 是否为临时二维码 default=0  {'0':'是临时二维码','1':'是永久二维码'}
	statusValid , //Integer 是否有效 default=1 是否有效主要针对于永久二维码，临时二维码直接设置为有效即可 {'0':'无效','1':'有效'}
	null
);
*/
}
