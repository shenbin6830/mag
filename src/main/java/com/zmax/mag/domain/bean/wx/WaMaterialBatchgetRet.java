/*
 * zmax 
 * 
 */


// BaseResult 
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
 * 回复消息之获取素材列表
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaMaterialBatchgetRet extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "回复消息之获取素材列表";

	//date formats
	
	//columns START
	/**素材总数 Integer  该类型的素材的总数 */
	
	
	private Integer totalCount;
	/**本次调用的数量 Integer  本次调用获取的素材的数量 */
	
	
	private Integer itemCount;
	/**item*/
	private List<WaMaterialItem> item;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaMaterialBatchgetRet _this){
	}
	public WaMaterialBatchgetRet(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param totalCount Integer 素材总数  该类型的素材的总数 
	 * @param itemCount Integer 本次调用的数量  本次调用获取的素材的数量 
	* @param notuse String 没用
	 */
	public WaMaterialBatchgetRet(Integer totalCount ,Integer itemCount ,String notuse) {
		super();
		this.totalCount = totalCount;
		this.itemCount = itemCount;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param totalCount Integer 素材总数  该类型的素材的总数 
	 * @param itemCount Integer 本次调用的数量  本次调用获取的素材的数量 
	 * @param item String item   
	* @param notuse String 没用
	 */
	public WaMaterialBatchgetRet(Integer totalCount ,Integer itemCount ,List<WaMaterialItem> item ,String notuse,Object notuse2) {
		super();
		this.totalCount = totalCount;
		this.itemCount = itemCount;
		this.item = item;
	}

	
	/**获取素材总数 该类型的素材的总数 */
	
@JSONField(name="total_count")
	
	public Integer getTotalCount() {
		return this.totalCount;
	}
	/**设置素材总数 该类型的素材的总数 */
@JSONField(name="total_count")
	public void setTotalCount(Integer value) {
		this.totalCount = value;
	}
	/**获取本次调用的数量 本次调用获取的素材的数量 */
	
@JSONField(name="item_count")
	
	public Integer getItemCount() {
		return this.itemCount;
	}
	/**设置本次调用的数量 本次调用获取的素材的数量 */
@JSONField(name="item_count")
	public void setItemCount(Integer value) {
		this.itemCount = value;
	}
	/**对象 获取item  */
	
	
@JSONField(name="item")
	public List<WaMaterialItem> getItem() {
		return this.item;
	}
	/**设置item  */
@JSONField(name="item")
	public void setItem(List<WaMaterialItem> value) {
		this.item = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",TotalCount:",getTotalCount())
			.append(",ItemCount:",getItemCount())
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
//精简构造 回复消息之获取素材列表
WaMaterialBatchgetRet waMaterialBatchgetRet = new WaMaterialBatchgetRet(
	totalCount , //Integer 素材总数  该类型的素材的总数 
	itemCount , //Integer 本次调用的数量  本次调用获取的素材的数量 
	null
);
*/
}
