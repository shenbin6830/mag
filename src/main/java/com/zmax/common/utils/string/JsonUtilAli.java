package com.zmax.common.utils.string;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class JsonUtilAli {

	private JsonUtilAli(){}

	public static <T> T parseObject(String json,Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}

	public static String toJSONString(Object object){
		return JSON.toJSONString(object);
	}
	/**
	 * 对象转文本
	 * @param object
	 * @param includesProperties 包含字段
	 * @param excludesProperties 排除字段
	 * @return
	 */
	public static String toJSONString(Object object,String[] includesProperties, String[] excludesProperties){
		FastjsonFilter filter = new FastjsonFilter();// excludes优先于includes
		if (excludesProperties != null && excludesProperties.length > 0) {
			filter.getExcludes().addAll(Arrays.<String> asList(excludesProperties));
		}
		if (includesProperties != null && includesProperties.length > 0) {
			filter.getIncludes().addAll(Arrays.<String> asList(includesProperties));
		}
		return JSON.toJSONString(object,filter);
	}
	/** 
	 * 从json HASH表达式中获取一个map，该map支持嵌套功能 
	 * 形如：{"id" : "johncon", "name" : "小强"} 
	 * @param object 
	 * @return 
	 */ 
	public static Map<String,String> getMapFromJson(String jsonString) { 
		Map<String,String> map = (Map<String,String>)JSON.parse(jsonString); 
		return map; 
	} 
}
