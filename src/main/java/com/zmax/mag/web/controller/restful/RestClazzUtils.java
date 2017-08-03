package com.zmax.mag.web.controller.restful;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.zmax.mag.domain.bean.*;

/**
 * RestFul对象检查工具 
 * 
 * 
 *
 */
@Component
public class RestClazzUtils {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	/**
	 * 是否需要TOKEN，默认都需要检查，不需要的话，自己列一下
	 * @param obj
	 * @param funcName 函数名 增删改查
	 * @return boolean
	 */
	public boolean isNeedToken(Object obj,String funcName){
		if(obj instanceof Wwwhome)return false;
		if(obj instanceof Question)return false;
		if(obj instanceof Quick)return false;
		if(obj instanceof Member)return false;
		if(obj instanceof Articlechannel)return false;
		return true;
		//return false;
	} 
}
