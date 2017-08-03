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
 * 自定义菜单属性对象
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaButton extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "自定义菜单属性对象";

	//date formats
	
	//columns START
	/**序号ID Integer   */
	
	
	private Integer id;
	/**响应动作类型 String  菜单的响应动作类型 map={'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}*/
	
	
	private String type;
	/**菜单标题 String  菜单标题，不超过16个字节，子菜单不超过40个字节 */
	
	
	private String name;
	/**菜单KEY值 String  click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 */
	
	
	private String key1;
	/**网页链接 String  view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 */
	
	
	private String url;
	/**合法media_id String  调用新增永久素材接口返回的合法media_id */
	
	
	private String mediaId;
	/**二级菜单数组*/
	private List<WaSubButton> subButton;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaButton _this){
	}
	public WaButton(){
		makedefault(this);
	}
	public WaButton(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param type String 响应动作类型  菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}
	 * @param name String 菜单标题  菜单标题，不超过16个字节，子菜单不超过40个字节 
	 * @param key1 String 菜单KEY值  click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 
	 * @param url String 网页链接  view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 
	 * @param mediaId String 合法media_id  调用新增永久素材接口返回的合法media_id 
	* @param notuse String 没用
	 */
	public WaButton(String type ,String name ,String key1 ,String url ,String mediaId ,String notuse) {
		super();
		this.type = type;
		this.name = name;
		this.key1 = key1;
		this.url = url;
		this.mediaId = mediaId;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号ID   
	 * @param type String 响应动作类型  菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}
	 * @param name String 菜单标题  菜单标题，不超过16个字节，子菜单不超过40个字节 
	 * @param key1 String 菜单KEY值  click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 
	 * @param url String 网页链接  view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 
	 * @param mediaId String 合法media_id  调用新增永久素材接口返回的合法media_id 
	* @param notuse String 没用
	 */
	public WaButton(Integer id ,String type ,String name ,String key1 ,String url ,String mediaId ,String notuse) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.key1 = key1;
		this.url = url;
		this.mediaId = mediaId;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号ID   
	 * @param type String 响应动作类型  菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}
	 * @param name String 菜单标题  菜单标题，不超过16个字节，子菜单不超过40个字节 
	 * @param key1 String 菜单KEY值  click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 
	 * @param url String 网页链接  view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 
	 * @param mediaId String 合法media_id  调用新增永久素材接口返回的合法media_id 
	 * @param subButton String 二级菜单数组  二级菜单数组，个数应为1~5个 
	* @param notuse String 没用
	 */
	public WaButton(Integer id ,String type ,String name ,String key1 ,String url ,String mediaId ,List<WaSubButton> subButton ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.key1 = key1;
		this.url = url;
		this.mediaId = mediaId;
		this.subButton = subButton;
	}
	/**
	 * 构造之不能为空的参数
	 * @param type String 响应动作类型 
	* @param notuse String 没用
	 */
	public WaButton(Integer _id,String _type,String notuse){
		this.type=_type;
		makedefault(this);
	}

	/**设置主键*/
	public void setId(Integer value) {
		this.id = value;
	}
	/**获取主键*/
	

	public Integer getId() {
		return this.id;
	}
	
	/**获取响应动作类型 菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}*/
	
@JSONField(name="type")
	
	public String getType() {
		return this.type;
	}
	/**设置响应动作类型 菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}*/
@JSONField(name="type")
	public void setType(String value) {
		this.type = value;
	}
	/**获取菜单标题 菜单标题，不超过16个字节，子菜单不超过40个字节 */
	
@JSONField(name="name")
	
	public String getName() {
		return this.name;
	}
	/**设置菜单标题 菜单标题，不超过16个字节，子菜单不超过40个字节 */
@JSONField(name="name")
	public void setName(String value) {
		this.name = value;
	}
	/**获取菜单KEY值 click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 */
	
@JSONField(name="key")
	
	public String getKey1() {
		return this.key1;
	}
	/**设置菜单KEY值 click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 */
@JSONField(name="key")
	public void setKey1(String value) {
		this.key1 = value;
	}
	/**获取网页链接 view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 */
	
@JSONField(name="url")
	
	public String getUrl() {
		return this.url;
	}
	/**设置网页链接 view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 */
@JSONField(name="url")
	public void setUrl(String value) {
		this.url = value;
	}
	/**获取合法media_id 调用新增永久素材接口返回的合法media_id */
	
@JSONField(name="media_id")
	
	public String getMediaId() {
		return this.mediaId;
	}
	/**设置合法media_id 调用新增永久素材接口返回的合法media_id */
@JSONField(name="media_id")
	public void setMediaId(String value) {
		this.mediaId = value;
	}
	/**对象 获取二级菜单数组 二级菜单数组，个数应为1~5个 */
	
	
@JSONField(name="sub_button")
	public List<WaSubButton> getSubButton() {
		return this.subButton;
	}
	/**设置二级菜单数组 二级菜单数组，个数应为1~5个 */
@JSONField(name="sub_button")
	public void setSubButton(List<WaSubButton> value) {
		this.subButton = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Id:",getId())
			.append(",Type:",getType())
			.append(",Name:",getName())
			.append(",Key1:",getKey1())
			.append(",Url:",getUrl())
			.append(",MediaId:",getMediaId())
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
		String[] excludesProperties={"id","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}

/*
//精简构造 自定义菜单属性对象
WaButton waButton = new WaButton(
	type , //String 响应动作类型  菜单的响应动作类型 {'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}
	name , //String 菜单标题  菜单标题，不超过16个字节，子菜单不超过40个字节 
	key1 , //String 菜单KEY值  click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节 
	url , //String 网页链接  view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节 
	mediaId , //String 合法media_id  调用新增永久素材接口返回的合法media_id 
	null
);
*/
}
