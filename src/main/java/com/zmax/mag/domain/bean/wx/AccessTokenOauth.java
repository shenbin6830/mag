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
 * 网页授权版access_token<br/>网页授权access_token，不同于access_token，是通过OAuth2.0机制实现的，在用户授权给公众号后，公众号可以获取到一个网页授权特有的接口调用凭证（网页授权access_token），通过网页授权access_token可以进行授权后接口调用，如获取用户基本信息；
 * @author zmax
 * @version 1.0
 * @since 
 */

public class AccessTokenOauth extends BaseResult{
	
	//alias
	public static final String TABLE_ALIAS = "网页授权版access_token";

	//date formats
	
	//columns START
	/**网页授权接口调用凭证网页授权接口调用凭证 String   */
	
	
	private String accessToken;
	/**access_token接口调用凭证超时时间，单位（秒） Integer   */
	
	
	private Integer expiresIn;
	/**用户刷新access_token String   */
	
	
	private String refreshToken;
	/**用户唯一标识 String  请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID */
	
	
	private String openid;
	/**用户授权的作用域 String  使用逗号（,）分隔 */
	
	
	private String scope;
	/**unionid String  只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段 */
	
	
	private String unionid;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(AccessTokenOauth _this){
	}
	public AccessTokenOauth(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param accessToken String 网页授权接口调用凭证网页授权接口调用凭证   
	 * @param expiresIn Integer access_token接口调用凭证超时时间，单位（秒）   
	 * @param refreshToken String 用户刷新access_token   
	 * @param openid String 用户唯一标识  请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID 
	 * @param scope String 用户授权的作用域  使用逗号（,）分隔 
	 * @param unionid String unionid  只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段 
	* @param notuse String 没用
	 */
	public AccessTokenOauth(String accessToken ,Integer expiresIn ,String refreshToken ,String openid ,String scope ,String unionid ,String notuse) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.refreshToken = refreshToken;
		this.openid = openid;
		this.scope = scope;
		this.unionid = unionid;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param accessToken String 网页授权接口调用凭证网页授权接口调用凭证   
	 * @param expiresIn Integer access_token接口调用凭证超时时间，单位（秒）   
	 * @param refreshToken String 用户刷新access_token   
	 * @param openid String 用户唯一标识  请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID 
	 * @param scope String 用户授权的作用域  使用逗号（,）分隔 
	 * @param unionid String unionid  只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段 
	* @param notuse String 没用
	 */
	public AccessTokenOauth(String accessToken ,Integer expiresIn ,String refreshToken ,String openid ,String scope ,String unionid ,String notuse,Object notuse2) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.refreshToken = refreshToken;
		this.openid = openid;
		this.scope = scope;
		this.unionid = unionid;
	}

	
	/**获取网页授权接口调用凭证网页授权接口调用凭证  */
	
@JSONField(name="access_token")
	
	public String getAccessToken() {
		return this.accessToken;
	}
	/**设置网页授权接口调用凭证网页授权接口调用凭证  */
@JSONField(name="access_token")
	public void setAccessToken(String value) {
		this.accessToken = value;
	}
	/**获取access_token接口调用凭证超时时间，单位（秒）  */
	
@JSONField(name="expires_in")
	
	public Integer getExpiresIn() {
		return this.expiresIn;
	}
	/**设置access_token接口调用凭证超时时间，单位（秒）  */
@JSONField(name="expires_in")
	public void setExpiresIn(Integer value) {
		this.expiresIn = value;
	}
	/**获取用户刷新access_token  */
	
@JSONField(name="refresh_token")
	
	public String getRefreshToken() {
		return this.refreshToken;
	}
	/**设置用户刷新access_token  */
@JSONField(name="refresh_token")
	public void setRefreshToken(String value) {
		this.refreshToken = value;
	}
	/**获取用户唯一标识 请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID */
	
@JSONField(name="openid")
	
	public String getOpenid() {
		return this.openid;
	}
	/**设置用户唯一标识 请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID */
@JSONField(name="openid")
	public void setOpenid(String value) {
		this.openid = value;
	}
	/**获取用户授权的作用域 使用逗号（,）分隔 */
	
@JSONField(name="scope")
	
	public String getScope() {
		return this.scope;
	}
	/**设置用户授权的作用域 使用逗号（,）分隔 */
@JSONField(name="scope")
	public void setScope(String value) {
		this.scope = value;
	}
	/**获取unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段 */
	
@JSONField(name="unionid")
	
	public String getUnionid() {
		return this.unionid;
	}
	/**设置unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段 */
@JSONField(name="unionid")
	public void setUnionid(String value) {
		this.unionid = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",AccessToken:",getAccessToken())
			.append(",ExpiresIn:",getExpiresIn())
			.append(",RefreshToken:",getRefreshToken())
			.append(",Openid:",getOpenid())
			.append(",Scope:",getScope())
			.append(",Unionid:",getUnionid())
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
//精简构造 网页授权版access_token
AccessTokenOauth accessTokenOauth = new AccessTokenOauth(
	accessToken , //String 网页授权接口调用凭证网页授权接口调用凭证   
	expiresIn , //Integer access_token接口调用凭证超时时间，单位（秒）   
	refreshToken , //String 用户刷新access_token   
	openid , //String 用户唯一标识  请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID 
	scope , //String 用户授权的作用域  使用逗号（,）分隔 
	unionid , //String unionid  只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段 
	null
);
*/
}
