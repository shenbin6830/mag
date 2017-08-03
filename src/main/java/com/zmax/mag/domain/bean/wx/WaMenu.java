/*
 * zmax 
 * 
 */


//  
package com.zmax.mag.domain.bean.wx;



import java.text.*;
import java.util.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.mag.domain.bean.base.BaseEntity;

import com.alibaba.fastjson.annotation.*;


import com.zmax.mag.domain.bean.*;


/**
 * 对象之自定义菜单
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaMenu extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "对象之自定义菜单";

	//date formats
	
	//columns START
	/**序号ID Integer   */
	
	
	private Integer id;
	/**卖家 Integer   */
	
	
	private Integer userId;
	/**一级菜单数组*/
	private List<WaButton> button;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaMenu _this){
	}
	public WaMenu(){
		makedefault(this);
	}
	public WaMenu(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param userId Integer 卖家   
	* @param notuse String 没用
	 */
	public WaMenu(Integer userId ,String notuse) {
		super();
		this.userId = userId;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param userId Integer 卖家   
	* @param notuse String 没用
	 */
	public WaMenu(Integer id ,Integer userId ,String notuse) {
		super();
		this.id = id;
		this.userId = userId;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param userId Integer 卖家   
	 * @param button String 一级菜单数组  一级菜单数组，个数应为1~3个 
	* @param notuse String 没用
	 */
	public WaMenu(Integer id ,Integer userId ,List<WaButton> button ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.userId = userId;
		this.button = button;
	}

	/**设置主键*/
	public void setId(Integer value) {
		this.id = value;
	}
	/**获取主键*/
	

	public Integer getId() {
		return this.id;
	}
	
	/**获取卖家  */
	

	
	public Integer getUserId() {
		return this.userId;
	}
	/**设置卖家  */

	public void setUserId(Integer value) {
		this.userId = value;
	}
	/**对象 获取一级菜单数组 一级菜单数组，个数应为1~3个 */
	
	
@JSONField(name="button")
	public List<WaButton> getButton() {
		return this.button;
	}
	/**设置一级菜单数组 一级菜单数组，个数应为1~3个 */
@JSONField(name="button")
	public void setButton(List<WaButton> value) {
		this.button = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Id:",getId())
			.append(",UserId:",getUserId())
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
		String[] excludesProperties={"null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	/**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 */
	public String toJsonNotshow2(){
		String[] excludesProperties={"id","userId","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 对象之自定义菜单
WaMenu waMenu = new WaMenu(
	userId , //Integer 卖家   
	null
);
*/
}
