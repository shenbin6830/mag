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
 * 对象之用于设定图文消息的接收者
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaAdvmassFilter extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "对象之用于设定图文消息的接收者";

	//date formats
	
	//columns START
	/**用于设定是否向全部用户发送， String  用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据group_id发送给指定群组的用户 */
	
	
	private String isToAll;
	/**群发到的分组的group_id String  群发到的分组的group_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写group_id */
	
	
	private String groupId;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaAdvmassFilter _this){
	}
	public WaAdvmassFilter(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param isToAll String 用于设定是否向全部用户发送，  用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据group_id发送给指定群组的用户 
	 * @param groupId String 群发到的分组的group_id  群发到的分组的group_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写group_id 
	* @param notuse String 没用
	 */
	public WaAdvmassFilter(String isToAll ,String groupId ,String notuse) {
		super();
		this.isToAll = isToAll;
		this.groupId = groupId;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param isToAll String 用于设定是否向全部用户发送，  用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据group_id发送给指定群组的用户 
	 * @param groupId String 群发到的分组的group_id  群发到的分组的group_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写group_id 
	* @param notuse String 没用
	 */
	public WaAdvmassFilter(String isToAll ,String groupId ,String notuse,Object notuse2) {
		super();
		this.isToAll = isToAll;
		this.groupId = groupId;
	}

	
	/**获取用于设定是否向全部用户发送， 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据group_id发送给指定群组的用户 */
	
@JSONField(name="is_to_all")
	
	public String getIsToAll() {
		return this.isToAll;
	}
	/**设置用于设定是否向全部用户发送， 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据group_id发送给指定群组的用户 */
@JSONField(name="is_to_all")
	public void setIsToAll(String value) {
		this.isToAll = value;
	}
	/**获取群发到的分组的group_id 群发到的分组的group_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写group_id */
	
@JSONField(name="group_id")
	
	public String getGroupId() {
		return this.groupId;
	}
	/**设置群发到的分组的group_id 群发到的分组的group_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写group_id */
@JSONField(name="group_id")
	public void setGroupId(String value) {
		this.groupId = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",IsToAll:",getIsToAll())
			.append(",GroupId:",getGroupId())
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
		String[] excludesProperties={"null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 对象之用于设定图文消息的接收者
WaAdvmassFilter waAdvmassFilter = new WaAdvmassFilter(
	isToAll , //String 用于设定是否向全部用户发送，  用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据group_id发送给指定群组的用户 
	groupId , //String 群发到的分组的group_id  群发到的分组的group_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写group_id 
	null
);
*/
}
