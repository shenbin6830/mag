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
 * 客服回复消息之音乐
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaSendcustommsgMusicParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "客服回复消息之音乐";

	//date formats
	
	//columns START
	/**接受者openid String   */
	
	
	private String tousername;
	/**消息类型 String default=music  */
	
	
	private String msgtype;
	/**音乐对象*/
	private WaEntityMusic music;
	/**customservice*/
	private WaSendcustommsgCustomservice customservice;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaSendcustommsgMusicParam _this){
		_this.msgtype="music";
	}
	public WaSendcustommsgMusicParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param tousername String 接受者openid   
	 * @param msgtype String 消息类型 default=music  
	* @param notuse String 没用
	 */
	public WaSendcustommsgMusicParam(String tousername ,String msgtype ,String notuse) {
		super();
		this.tousername = tousername;
		this.msgtype = msgtype;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param tousername String 接受者openid   
	 * @param msgtype String 消息类型 default=music  
	 * @param music String 音乐对象   
	 * @param customservice String customservice   
	* @param notuse String 没用
	 */
	public WaSendcustommsgMusicParam(String tousername ,String msgtype ,WaEntityMusic music ,WaSendcustommsgCustomservice customservice ,String notuse,Object notuse2) {
		super();
		this.tousername = tousername;
		this.msgtype = msgtype;
		this.music = music;
		this.customservice = customservice;
	}

	
	/**获取接受者openid  */
	
@JSONField(name="touser")
	
	public String getTousername() {
		return this.tousername;
	}
	/**设置接受者openid  */
@JSONField(name="touser")
	public void setTousername(String value) {
		this.tousername = value;
	}
	/**获取消息类型  */
	

	
	public String getMsgtype() {
		return this.msgtype;
	}
	/**设置消息类型  */

	public void setMsgtype(String value) {
		this.msgtype = value;
	}
	/**对象 获取音乐对象  */
	
	

	public WaEntityMusic getMusic() {
		return this.music;
	}
	/**设置音乐对象  */

	public void setMusic(WaEntityMusic value) {
		this.music = value;
	}
	/**对象 获取customservice  */
	
	

	public WaSendcustommsgCustomservice getCustomservice() {
		return this.customservice;
	}
	/**设置customservice  */

	public void setCustomservice(WaSendcustommsgCustomservice value) {
		this.customservice = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Tousername:",getTousername())
			.append(",Msgtype:",getMsgtype())
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
//精简构造 客服回复消息之音乐
WaSendcustommsgMusicParam waSendcustommsgMusicParam = new WaSendcustommsgMusicParam(
	tousername , //String 接受者openid   
	msgtype , //String 消息类型 default=music  
	null
);
*/
}
