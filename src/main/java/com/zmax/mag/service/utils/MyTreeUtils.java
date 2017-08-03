/**
 * 
 */
package com.zmax.mag.service.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.zmax.common.utils.easyui.Treei;
import com.zmax.common.utils.file.FileUtil;
import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.mag.domain.conf.PropMy;
import com.zmax.mag.domain.conf.Rt;


/**
 * 我的树工具
 * 对于一些字段中的二级或三级选择树的工具，该选择树设计完成后就基本上定了，所以配置成文本文件而不是数据库
 * 流程
 * 1.先手工填写excel，只要填写中文，一级，\t二级,\t\t三级 ex.areaid，复制粘贴成treetxt_Clazz_field.txt或者treetxt_all_field.txt
 * 2.TreeTest根据txt生成js,easyUI要求是数组，java 要求是对象，angularjs要示？
 * ex. tree_all_areaid.js tree:var tree_all_areaid=[{...}]; 
 * ex. tree_Product_catalog.js :var tree_Product_catalog=[{...}];
 * 3.前端后台都引用此js，后台读文本将JSON文本转成java对象，后台主要是使用takeName
 * @author zmax
 *
 */
public class MyTreeUtils {
	/**日志实例*/
	private static final Log logger = LogFactory.getLog(MyTreeUtils.class);
	/**我的二级以及上选择项Tree*/
	public static Map<String,Map<Integer,String>> myTreeAll=new HashMap<String, Map<Integer,String>>();
	/**需要启动器注入进来,资源根目录，如：/opt/hos/static */
	public static String webinfclasses=Rt.runtime_harddisk_rootpath;
	/**分隔符*/
	static final String SEPT="-";
	/**顶级显示*/
	static final String TOP="全部";
	
	/**
	 * 第一步
	 * 这个是在软件设计时期给test用的，线上不会用到。
	 * 准备工作：先手工填写excel，只要填写中文，一级，\t二级,\t\t三级   
	 * 复制贴成文本文件tree_xxx.txt中,改成utf-8编码，然后运行step1
	 * 此过程把txt转成js
	 * 结果：打印js,ID自动产生，复制粘贴到tree_xxx.js中
	 * @param field
	 */
	public static String step1(String clazz,String field){
		String file=webinfclasses+"/res/js/mytree"+"/treetxt_"+clazz+"_"+field+".txt";
		//开发模式在d:/xxx/xxx/xxx/static目录下，和线上比，需要增加个static
		if("zmaxdEFAULT".equals(Rt.runtime_MODE)){
			file=StringUtilz.urlRootAddChild(webinfclasses, "/static/res/js/mytree"+"/treetxt_"+clazz+"_"+field+".txt");
		}
		System.out.println("file=" + file);
		//带id的txt
		String idtxt="";
		List<String> listTxt=FileUtil.toList(file);
		Treei treei=new Treei();
		//第一项
		treei.setId(0);
		treei.setText(TOP);		
		Treei kid1=null,kid2=null,kid3=null;
		int id1=0,id2=0,id3=0;
		for (String string : listTxt) {
			String[] as=string.split("\t");
			if(as.length==1){
				id1++;
				kid1=new Treei();
				String text=as[0];
				kid1.setText(text);
				kid1.setId(id1*10000);
				treei.getChildren().add(kid1);
				id2=0;
				id3=0;
				idtxt+=""+kid1.getId()+"\t"+string+"\r\n";
			}
			if(as.length==2){
				id2++;
				kid2=new Treei();
				String text=as[1];
				kid2.setText(text);
				kid2.setId(id1*10000+id2*100);
				kid1.getChildren().add(kid2);
				id3=0;
				idtxt+=""+kid2.getId()+"\t"+string+"\r\n";
			}
			if(as.length==3){
				id3++;
				kid3=new Treei();
				String text=as[2];
				kid3.setText(text);
				kid3.setId(id1*10000+id2*100+id3);
				kid2.getChildren().add(kid3);
				idtxt+=""+kid3.getId()+"\t"+string+"\r\n";
			}
		}
		String str=JsonUtilAli.toJSONString(treei, new String[]{"id","text","children"}, null);
		System.out.println("下面的贴到tree_"+field+".js中,贴的时候记得改成utf-8编码，说明：js要求是列表list<Treei>，而java要求是Treei对象");
		System.out.println("var tree_"+clazz+"_"+field+"=[" + str+"];");
		return str;
//		System.out.println("=======带id的txt在下面==========");
//		System.out.println(idtxt);
//		System.out.println("=======带id的txt在上面==========");
	}

	/**
	 * 将tree_field.js,转成treei对象 ,同时创建id,text的Map，把map放到总
	 * @param field
	 */
	public static void js2Treei(String clazz,String field){		
		String file=StringUtilz.urlRootAddChild(webinfclasses, "/res/js/mytree"+"/tree_"+clazz+"_"+field+".js");
		if("zmaxdEFAULT".equals(Rt.runtime_MODE)){
			file=StringUtilz.urlRootAddChild(webinfclasses, "/static/res/js/mytree"+"/tree_"+clazz+"_"+field+".js");
		}
		if (logger.isDebugEnabled())
			logger.debug("[3]初始化Tree("+clazz+"_"+field+")=" + file);

		List<String> listTxt=FileUtil.toList(file);
		String firstrow=listTxt.get(0);
		//System.out.println("firstrow :" + firstrow);
		//
		int firstcut="var tree_=[".length()+1+clazz.length()+field.length();
		//去掉开始=[前的
		firstrow=firstrow.substring(firstcut);
		//去掉后面的];
		firstrow=firstrow.substring(0, firstrow.length()-2);
		//System.out.println("firstrow :" + firstrow);
		Treei treei=JsonUtilAli.parseObject(firstrow, Treei.class);
		//打印一下看看对不对
		//String str=JsonUtilAli.toJSONString(treei, new String[]{"id","text","children"}, null);
		//System.out.println("var tree_"+field+"=" + str);	
		Map<Integer, String> map=new HashMap<Integer, String>();
		treei2Map(treei, map);
		//System.out.println("map=" + map);
		myTreeAll.put(clazz+"_"+field, map);
	}
	/**
	 * treei转Map[id,text]
	 * @param treei
	 * @param map
	 */
	public static void treei2Map(Treei treei,Map<Integer,String> map){
		map.put(treei.getId(), treei.getText());
		for (Treei child : treei.getChildren()) {
			treei2Map(child, map);
		}
	}
	/**
	 * 根据id获取名称
	 * @param clazz 表名 ex.User，如果是all表示全局
	 * @param field 字段名 ex.areaid
	 * @param key 值 ex.310000
	 * @return 浙江省-杭州市-下城区
	 */
	public static String takeName(String clazz,String field,Integer key){
		if(key==null||key==0)
			return "";
		Map<Integer, String> map=myTreeAll.get(clazz+"_"+field);
		
		if(map==null){
			js2Treei(clazz,field);
		}
		
		map=myTreeAll.get(clazz+"_"+field);
		if(map==null){
			return "";
		}

		String code=""+key;
		for (int i = code.length(); i < 6; i++) {
			code="0"+code;
		}
		if(code.substring(2,6).equals("0000")){//省 110000 500000
			return map.get(key);
		}else if(code.substring(4,6).equals("00")){ //市110100
			int p=Integer.parseInt(code.substring(0,2)+"0000");
			return map.get(p)+SEPT+map.get(key);
		}else{ //县110101
			int p=Integer.parseInt(code.substring(0,2)+"0000");
			int c=Integer.parseInt(code.substring(0,4)+"00");
			return map.get(p)+SEPT+map.get(c)+SEPT+map.get(key);
		}
	}


}
