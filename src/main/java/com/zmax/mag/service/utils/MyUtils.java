package com.zmax.mag.service.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.zmax.common.entity.Sele;
import com.zmax.common.exception.BoException;
import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.mag.domain.bean.Wxcfg;
import com.zmax.mag.domain.conf.Rt;





public class MyUtils {
	private static final Logger logger = Logger.getLogger(MyUtils.class);
	/**
	 * 属性，从JSON转成HTML的select格式
	 * 属性变成<option></option><option></option>...<option></option>
	 * @param pname 属性名
	 * @param prop ex.[{"id":"红","iindex":"1"},{"id":"黄","iindex":"3"},{"id":"兰","iindex":"2"},{"id":"绿","iindex":"4"},{"id":"白","iindex":"5"}]
	 * @return <option value='属性名_红'>红</option>
	 */
	public static String propToOptions(String pname,String prop){
		String ret="";
		List<Sele> listSele=JsonUtilAli.parseObject(prop, List.class);
				//JsonUtil.getDTOList(prop, Sele.class);
		for (Sele sele : listSele) {
			//ret+="<option value='"+pname+"_"+sele.getId()+"'>"+sele.getId()+"</option>";
			ret+="<option value='"+pname+"_"+sele.getValue()+"'>"+sele.getId()+"</option>";
		}
		return ret;
	}
	/**
	 * 从运行环境中找到微信配置
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static Wxcfg takeWxcfg(Integer id) throws BoException{
		if(StringUtilz.integerNullOr0(id))
			throw new BoException("微信配置ID为空");
		Wxcfg wxcfg=Rt.wxcfgMap.get(id);
		//logger.debug("RtCfg.wxcfgMap="+RtCfg.wxcfgMap);
		if(wxcfg==null)
			throw new BoException("id为"+id+"微信配置文件找不到");
		return wxcfg;
	}
	/**
	 * 微信URL，
	 * 如果有参数，就是http://aaa.bbb.com/ddd?qqq
	 * 如果没参数，就是http://aaa.bbb.com/ddd
	 * @param request
	 * @return
	 */
	public static String wxUrl(HttpServletRequest request){
		return (null==request.getQueryString())?request.getRequestURL().toString():request.getRequestURL().toString()+ "?" + (request.getQueryString()); 
	}

}
