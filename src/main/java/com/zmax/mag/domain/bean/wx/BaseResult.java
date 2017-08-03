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
 * 基础返回<br/>返回码 说明 
<br>-1  系统繁忙，此时请开发者稍候再试  
<br>0  请求成功  
<br>40001  获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口  
<br>40002  不合法的凭证类型  
<br>40003  不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID  
<br>40004  不合法的媒体文件类型  
<br>40005  不合法的文件类型  
<br>40006  不合法的文件大小  
<br>40007  不合法的媒体文件id  
<br>40008  不合法的消息类型  
<br>40009  不合法的图片文件大小  
<br>40010  不合法的语音文件大小  
<br>40011  不合法的视频文件大小  
<br>40012  不合法的缩略图文件大小  
<br>40013  不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写  
<br>40014  不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口  
<br>40015  不合法的菜单类型  
<br>40016  不合法的按钮个数  
<br>40017  不合法的按钮个数  
<br>40018  不合法的按钮名字长度  
<br>40019  不合法的按钮KEY长度  
<br>40020  不合法的按钮URL长度  
<br>40021  不合法的菜单版本号  
<br>40022  不合法的子菜单级数  
<br>40023  不合法的子菜单按钮个数  
<br>40024  不合法的子菜单按钮类型  
<br>40025  不合法的子菜单按钮名字长度  
<br>40026  不合法的子菜单按钮KEY长度  
<br>40027  不合法的子菜单按钮URL长度  
<br>40028  不合法的自定义菜单使用用户  
<br>40029  不合法的oauth_code  
<br>40030  不合法的refresh_token  
<br>40031  不合法的openid列表  
<br>40032  不合法的openid列表长度  
<br>40033  不合法的请求字符，不能包含\\uxxxx格式的字符  
<br>40035  不合法的参数  
<br>40038  不合法的请求格式  
<br>40039  不合法的URL长度  
<br>40050  不合法的分组id  
<br>40051  分组名字不合法  
<br>41001  缺少access_token参数  
<br>41002  缺少appid参数  
<br>41003  缺少refresh_token参数  
<br>41004  缺少secret参数  
<br>41005  缺少多媒体文件数据  
<br>41006  缺少media_id参数  
<br>41007  缺少子菜单数据  
<br>41008  缺少oauth code  
<br>41009  缺少openid  
<br>42001  access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明  
<br>42002  refresh_token超时  
<br>42003  oauth_code超时  
<br>43001  需要GET请求  
<br>43002  需要POST请求  
<br>43003  需要HTTPS请求  
<br>43004  需要接收者关注  
<br>43005  需要好友关系  
<br>44001  多媒体文件为空  
<br>44002  POST的数据包为空  
<br>44003  图文消息内容为空  
<br>44004  文本消息内容为空  
<br>45001  多媒体文件大小超过限制  
<br>45002  消息内容超过限制  
<br>45003  标题字段超过限制  
<br>45004  描述字段超过限制  
<br>45005  链接字段超过限制  
<br>45006  图片链接字段超过限制  
<br>45007  语音播放时间超过限制  
<br>45008  图文消息超过限制  
<br>45009  接口调用超过限制  
<br>45010  创建菜单个数超过限制  
<br>45015  回复时间超过限制  
<br>45016  系统分组，不允许修改  
<br>45017  分组名字过长  
<br>45018  分组数量超过上限  
<br>46001  不存在媒体数据  
<br>46002  不存在的菜单版本  
<br>46003  不存在的菜单数据  
<br>46004  不存在的用户  
<br>47001  解析JSON/XML内容错误  
<br>48001  api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限  
<br>50001  用户未授权该api  
<br>61451  参数错误(invalid parameter)  
<br>61452  无效客服账号(invalid kf_account)  
<br>61453  客服帐号已存在(kf_account exsited)  
<br>61454  客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalid kf_acount length)  
<br>61455  客服帐号名包含非法字符(仅允许英文+数字)(illegal character in kf_account)  
<br>61456  客服帐号个数超过限制(10个客服账号)(kf_account count exceeded)  
<br>61457  无效头像文件类型(invalid file type)  
<br>61450  系统错误(system error)  
<br>61500  日期格式错误  
<br>61501  日期范围错误 
 * @author zmax
 * @version 1.0
 * @since 
 */

public class BaseResult extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "基础返回";

	//date formats
	
	//columns START
	/**错误码 Integer   */
	
	
	private Integer errcode;
	/**错误说明 String   */
	
	
	private String errmsg;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(BaseResult _this){
	}
	public BaseResult(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param errcode Integer 错误码   
	 * @param errmsg String 错误说明   
	* @param notuse String 没用
	 */
	public BaseResult(Integer errcode ,String errmsg ,String notuse) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param errcode Integer 错误码   
	 * @param errmsg String 错误说明   
	* @param notuse String 没用
	 */
	public BaseResult(Integer errcode ,String errmsg ,String notuse,Object notuse2) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	
	/**获取错误码  */
	

	
	public Integer getErrcode() {
		return this.errcode;
	}
	/**设置错误码  */

	public void setErrcode(Integer value) {
		this.errcode = value;
	}
	/**获取错误说明  */
	

	
	public String getErrmsg() {
		return this.errmsg;
	}
	/**设置错误说明  */

	public void setErrmsg(String value) {
		this.errmsg = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Errcode:",getErrcode())
			.append(",Errmsg:",getErrmsg())
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
//精简构造 基础返回
BaseResult baseResult = new BaseResult(
	errcode , //Integer 错误码   
	errmsg , //String 错误说明   
	null
);
*/
}
