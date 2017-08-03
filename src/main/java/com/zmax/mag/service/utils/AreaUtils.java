/**
 * 
 */
package com.zmax.mag.service.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zmax.common.utils.file.FileUtil;
import com.zmax.mag.domain.conf.Rt;

/**
 * 省市县工具
 * @author zmax
 *
 */
public class AreaUtils {
	/**地区map:id,name*/
	public static Map<Integer,String> mapArea=null;
	/**
	 * 这里是程序员自已增加map的地方
	 */
	public static void init(){
		mapArea=new HashMap<Integer, String>();
		String pcc=Rt.runtime_hddir+"/config/pcc.txt";
		List<String> listTxt=FileUtil.toList(pcc);
		for (String string : listTxt) {
			String[] anc=string.split("\t");
			String name=anc[1];
			int code=Integer.parseInt(anc[0]);
			mapArea.put(code, name);
		}
		System.out.println("[AreaUtils.init]AreaUtils.size()=" + listTxt.size());
	}
	/**
	 * 根据id获取名称
	 * @param key
	 * @return
	 */
	public static String takeName(Integer key){
		if(key==null||key==0)
			return "";
		if(mapArea==null){
			init();
		}
		if(mapArea==null){
			return "";
		}
		String code=""+key;
		if(code.length()<6)
			return "";
		if(code.substring(2,6).equals("0000")){//省 110000 500000
			return mapArea.get(key);
		}else if(code.substring(4,6).equals("00")){ //市110100
			int p=Integer.parseInt(code.substring(0,2)+"0000");
			return mapArea.get(p)+mapArea.get(key);
		}else{ //县110101
			int p=Integer.parseInt(code.substring(0,2)+"0000");
			int c=Integer.parseInt(code.substring(0,4)+"00");
			return mapArea.get(p)+mapArea.get(c)+mapArea.get(key);
		}
	}


}
