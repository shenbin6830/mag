package com.zmax.mag.service.spec;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zmax.common.conf.AttrStatic.EnumFieldType;
import com.zmax.common.conf.AttrStatic.EnumFromControllerType;
import com.zmax.common.conf.AttrStatic.StatusApplyPassRefuse;
import com.zmax.common.conf.AttrStatic.StatusValidOrNot;

public class MapSpec {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	public  static void init(Map<String,Map<String,String>> map){
		//通用
		map.put("all."+"EnumFieldType", EnumFieldType.getMap());
		map.put("all."+"EnumFromControllerType", EnumFromControllerType.getMap());
		map.put("all."+"StatusValidOrNot", StatusValidOrNot.getMap());	
		map.put("all."+"StatusApplyPassRefuse", StatusApplyPassRefuse.getMap());	
	}
}
