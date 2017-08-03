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
 * 模板消息接口之发送模板消息
 * @author zmax
 * @version 1.0
 * @since 
 */

public class WaTemplateSendParam extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "模板消息接口之发送模板消息";

	//date formats
	
	//columns START
	/**openid String   */
	
	
	private String touser;
	/**模板id String   */
	
	
	private String templateId;
	/**链接 String   */
	
	
	private String url;
	/**上部颜色 String   */
	
	
	private String topcolor;
	/**data*/
	private Map<String,WaTemplateVc> data;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaTemplateSendParam _this){
	}
	public WaTemplateSendParam(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	 * @param touser String openid   
	 * @param templateId String 模板id   
	 * @param url String 链接   
	 * @param topcolor String 上部颜色   
	* @param notuse String 没用
	 */
	public WaTemplateSendParam(String touser ,String templateId ,String url ,String topcolor ,String notuse) {
		super();
		this.touser = touser;
		this.templateId = templateId;
		this.url = url;
		this.topcolor = topcolor;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param touser String openid   
	 * @param templateId String 模板id   
	 * @param url String 链接   
	 * @param topcolor String 上部颜色   
	 * @param data String data   
	* @param notuse String 没用
	 */
	public WaTemplateSendParam(String touser ,String templateId ,String url ,String topcolor ,Map<String,WaTemplateVc> data ,String notuse,Object notuse2) {
		super();
		this.touser = touser;
		this.templateId = templateId;
		this.url = url;
		this.topcolor = topcolor;
		this.data = data;
	}

	
	/**获取openid  */
	
@JSONField(name="touser")
	
	public String getTouser() {
		return this.touser;
	}
	/**设置openid  */
@JSONField(name="touser")
	public void setTouser(String value) {
		this.touser = value;
	}
	/**获取模板id  */
	
@JSONField(name="template_id")
	
	public String getTemplateId() {
		return this.templateId;
	}
	/**设置模板id  */
@JSONField(name="template_id")
	public void setTemplateId(String value) {
		this.templateId = value;
	}
	/**获取链接  */
	
@JSONField(name="url")
	
	public String getUrl() {
		return this.url;
	}
	/**设置链接  */
@JSONField(name="url")
	public void setUrl(String value) {
		this.url = value;
	}
	/**获取上部颜色  */
	
@JSONField(name="topcolor")
	
	public String getTopcolor() {
		return this.topcolor;
	}
	/**设置上部颜色  */
@JSONField(name="topcolor")
	public void setTopcolor(String value) {
		this.topcolor = value;
	}
	/**对象 获取data  */
	
	
@JSONField(name="data")
	public Map<String,WaTemplateVc> getData() {
		return this.data;
	}
	/**设置data  */
@JSONField(name="data")
	public void setData(Map<String,WaTemplateVc> value) {
		this.data = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Touser:",getTouser())
			.append(",TemplateId:",getTemplateId())
			.append(",Url:",getUrl())
			.append(",Topcolor:",getTopcolor())
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
//精简构造 模板消息接口之发送模板消息
WaTemplateSendParam waTemplateSendParam = new WaTemplateSendParam(
	touser , //String openid   
	templateId , //String 模板id   
	url , //String 链接   
	topcolor , //String 上部颜色   
	null
);
*/
}
