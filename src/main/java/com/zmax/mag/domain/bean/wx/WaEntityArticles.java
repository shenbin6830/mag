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

import javax.xml.bind.annotation.*;
import com.alibaba.fastjson.annotation.*;


import com.zmax.mag.domain.bean.*;


/**
 * 共用对象之图文列表
 * @author zmax
 * @version 1.0
 * @since 
 */

@XmlRootElement(name="xml")
public class WaEntityArticles extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "共用对象之图文列表";

	//date formats
	
	//columns START
	/**图文对象列表*/
	private List<WaEntityArticle> news;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(WaEntityArticles _this){
	}
	public WaEntityArticles(){
		makedefault(this);
	}
	/**
	 * 不包括自动变量的构造
	* @param notuse String 没用
	 */
	public WaEntityArticles(String notuse) {
		super();
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param news String 图文对象列表   
	* @param notuse String 没用
	 */
	public WaEntityArticles(List<WaEntityArticle> news ,String notuse,Object notuse2) {
		super();
		this.news = news;
	}

	
	/**对象 获取图文对象列表  */
	
	@XmlElement(name="item")
@JSONField(name="articles")
	public List<WaEntityArticle> getNews() {
		return this.news;
	}
	/**设置图文对象列表  */
@JSONField(name="articles")
	public void setNews(List<WaEntityArticle> value) {
		this.news = value;
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
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
//精简构造 共用对象之图文列表
WaEntityArticles waEntityArticles = new WaEntityArticles(
	null
);
*/
}
