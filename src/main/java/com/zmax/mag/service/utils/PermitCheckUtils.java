package com.zmax.mag.service.utils;
/**
 * 权限检查工具
 */
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zmax.common.utils.clazz.ClassUtils;
import com.zmax.mag.domain.bean.Permitfield;
import com.zmax.mag.domain.bean.Permittable;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.service.my.PermitfieldService;
import com.zmax.mag.service.my.PermittableService;
import com.zmax.mag.service.spec.SpecRoleService;





/**
 * 权限工具，主要用在xxxService的各项操作之前
 * kv例:
 * 1.user.new(del或edit或list..其它自定义)=null;
 * 2./user/xxx;
 * 3.user=userId=sessonobj.id;
 * 4.user=return dbobj.userId==sessionobj.id

 * @author zmax
 *
 */

@Component
public class PermitCheckUtils {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	/**全局权限列表，key=role.id,value=[权限key,权限value]*/
	public static Map<Integer,Map<String,String>> pMap=new HashMap<Integer, Map<String,String>>();

	//private String tname;
	private GroovyShell shell;

	/**权限EXCEL的路径*/
	//public static String permitExcelPath="";
	/**权限excel的最大宽度*/
	static int CELLMAXNUM=19;
	/**
	 *在从excel中导出时，是否要转大小写:
	 *<br>0默认,全部转成小写(未来对象下划线变大写也用这个),ex.aBbCc->abbcc or a_Bb_Cc -> a_bb_cc 
	 *<br>1保持原样
	 *<br>2大写变下划线 ex.aBbCc -> a_bb_cc
	 */
	static int WORDCHANGE=1;	
	
	@Autowired
	PermittableService permittableService;
	@Autowired
	PermitfieldService permitfieldService;
	@Autowired
	SpecRoleService specRoleService;
	
	
	/**
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PermitCheckUtils() {
		shell = new GroovyShell();
	}

	/**
	 * 初始化权限列表
	 * 某目录下，permit*.xls
	 */
	public void doInit(){
		pMap.clear();

		//超管
		int role=0;
		Map<String,String> pmtMap=takePmtMap(role);
		putPermit(role, pmtMap, "0.u"+role, "9");
		putPermit(role, pmtMap, "0.u", "9");
		putPermit(role, pmtMap, "0.ua", "9");
		putPermit(role, pmtMap, "0.uo", "9");
		pMap.put(role, pmtMap);
		if (logger.isDebugEnabled())
			logger.debug("权限分配:role=" + role+",pmtMap.size()="+pmtMap.size());

		//其它权限
		//通过
		//permitByExcel();
		permitByDb();
	}
	/**
	 * 根据role获取pmtMap，没有的话，建一下
	 * @param role
	 * @return
	 */
	public Map<String,String>  takePmtMap(int role){
		Map<String,String> pmtMap=pMap.get(role);
		if(pmtMap==null){
			pmtMap=new HashMap<String, String>();
			pMap.put(role, pmtMap);
		}

		return pmtMap;
	}

	List<Permitfield> listFieldDb=new ArrayList<Permitfield>();
	/**
	 * 权限之Db版
	 */
	public void permitByDb(){
		List<Permittable> listTbDb=permittableService.listAll(null);
		listFieldDb=permitfieldService.listAll(null);
		//角色循环，放置每个角色的权限通用版
		Map<String,String> roleMap=MapDb.getInstance().getMymap().get("User.RoleId");
		Iterator<Entry<String,String>> iter = roleMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String,String> entry = iter.next(); 
			String key = entry.getKey();
			if(!"0".equals(key)){ //非管理员
				int roleId=Integer.valueOf(key);
				Map<String,String>  pmtMap=takePmtMap(roleId);
				specRoleService.putFirstPmt(pmtMap,roleId);
			}
				
		}		
		for (Permittable permittable : listTbDb) {
			calcuTbByDb(permittable);
		}
	}	

	/**
	 * 是否是打开的状态
	 * @param value
	 * @return
	 */
	boolean isOn(Integer value){
		if(value==null)
			return false;
		return value.intValue()>5;
	}
	/**
	 * 是否是打开的状态
	 * @param value
	 * @return
	 */
	boolean isOn(String value){
		if(value==null)
			return false;
		if("".equals(value))
			return false;
		if("null".equals(value))
			return false;
		String value1=value.substring(0,1);
		if(!StringUtils.isNumeric(value1)){
			return false;
		}
		int ivalue=0;
		try {
			ivalue = Integer.valueOf(value1);
		} catch (NumberFormatException e) {
			return false;
		}
		return ivalue>5;
	}
	/**
	 * 是否是关闭的状态
	 * @param value
	 * @return
	 */
	boolean isOff(Integer value){
		return !isOn(value);
	}
	/**
	 * 是否是关闭的状态
	 * @param value
	 * @return
	 */
	boolean isOff(String value){
		return !isOn(value);
	}

	/**
	 * 计算表之通过Table Permittable
	 * @param sheet
	 * @param index
	 * @param tbname
	 * @param role
	 * @param pmtMap
	 */
	private void calcuTbByDb(Permittable permittable){
		//6	7	8	9	10	11
		//增按钮	删按钮	改按钮	查按钮	2表查+hql	3表改删检查gvy
		String key="";
		String cvalue="";
		Integer ivalue=0;
		//总开关
		cvalue=""+permittable.getPall();
		if(isOff(cvalue)){
			return;
		}
		key="0."+permittable.getTbname();
		putPermit(permittable.getRoleId(), key, cvalue);
		//增按钮	删按钮	改按钮	查按钮
		cvalue=""+permittable.getBtnnew();
		if(isOn(cvalue)){
			key="0."+permittable.getTbname()+".new";
			putPermit(permittable.getRoleId(), key, cvalue);
		}
		cvalue=""+permittable.getBtndel();
		if(isOn(cvalue)){
			key="0."+permittable.getTbname()+".del";
			putPermit(permittable.getRoleId(), key, cvalue);
		}
		cvalue=""+permittable.getBtnedit();
		if(isOn(cvalue)){
			key="0."+permittable.getTbname()+".edit";
			putPermit(permittable.getRoleId(), key, cvalue);
		}
		cvalue=""+permittable.getBtnshow();
		if(isOn(cvalue)){
			key="0."+permittable.getTbname()+".show";
			putPermit(permittable.getRoleId(), key, cvalue);
		}
		//2表查+hql	
		cvalue=permittable.getQueryaddhql();
		if(StringUtils.isNotBlank(cvalue)){
			key="2."+permittable.getTbname();
			cvalue=fast2(cvalue);
			putPermit(permittable.getRoleId(), key, cvalue);
		}
		//3表改删检查gvy
		cvalue=permittable.getModigvy();
		if(StringUtils.isNotBlank(cvalue)){
			key="3."+permittable.getTbname();
			cvalue=fast3(cvalue);
			putPermit(permittable.getRoleId(), key, cvalue);
		}
		//字段
		List<Permitfield> listfd=fieldFromDblist(permittable);
		for (Permitfield permitfield : listfd) {
			calcuFieldByDb(permitfield);
		}

	}
	/**
	 * 从数据库列表中，根据roleId和tablename找到Permitfield
	 * @param permittable
	 * @return List<Permitfield>
	 */
	private List<Permitfield> fieldFromDblist(Permittable permittable){
		List<Permitfield> ret = new ArrayList<Permitfield>();
		for (Permitfield fdb : listFieldDb) {
			if(fdb.getRoleId().equals(permittable.getRoleId())
					&&fdb.getTbname().equals(permittable.getTbname())
					){
				ret.add(fdb);
			}
		}
		return ret;
	}
	/**
	 * 2表查+hql	的快捷转换
	 * @param value
	 * @return
	 */
	private String fast2(String value){
		if(StringUtils.isBlank(value))
			return value;
		if("id".equals(value)){
			return "\"id=\"+sessionobj.id";
		}
		if("userId".equals(value)){
			return "\"userId=\"+sessionobj.userId";
		}
		return value;

	}
	/**
	 * 3表改删检查gvy的快捷转换
	 * @param value
	 * @return
	 */
	private String fast3(String value){
		if(StringUtils.isBlank(value))
			return value;
		if("id".equals(value)){
			return "dbobj.id==sessionobj.id";
		}
		if("userId".equals(value)){
			return "dbobj.userId==sessionobj.userId";
		}
		return value;

	}

	/**
	 * 计算字段之通过数据库
	 * @param sheet
	 * @param index
	 * @param tbname
	 * @param fieldname
	 * @param role
	 * @param pmtMap
	 */
	private void calcuFieldByDb(Permitfield permitfield){
		//12	13	14
		//41增可写字段	42改可写字段	51查可看字段	52列表显示字段
		String key="";
		String cvalue="";
		//41增可写字段
		cvalue=permitfield.getPf41();
		if(isOn(cvalue)||cvalue.indexOf("~")>-1){ //41有可能会放默认值
			key="41."+permitfield.getTbname()+"."+permitfield.getFieldname();
			putPermit(permitfield.getRoleId(), key, cvalue);
		}
		//42改可写字段
		cvalue=permitfield.getPf42();
		if(isOn(cvalue)){
			key="42."+permitfield.getTbname()+"."+permitfield.getFieldname();
			putPermit(permitfield.getRoleId(), key, cvalue);
		}
		//51查可看字段
		cvalue=permitfield.getPf51();
		if(isOn(cvalue)){
			key="51."+permitfield.getTbname()+"."+permitfield.getFieldname();
			putPermit(permitfield.getRoleId(), key, cvalue);
		}	
		//52列表显示字段
		cvalue=permitfield.getPf52();
		if(isOn(cvalue)){
			key="52."+permitfield.getTbname()+"."+permitfield.getFieldname();
			putPermit(permitfield.getRoleId(), key, cvalue);
		}
	}
	/**
	 * 权限数据放到Map中
	 * @param role
	 * @param pmtMap
	 * @param key
	 * @param cvalue
	 */
	private void putPermit(int role,Map<String,String> pmtMap,String key,String cvalue){
		logger.debug("权限初始化[" +role+"]\t"+ key+"\t"+cvalue+"");
		pmtMap.put(key, cvalue);
	}
	/**
	 * 权限数据放到Map中
	 * @param role
	 * @param pmtMap
	 * @param key
	 * @param cvalue
	 */
	private void putPermit(int roleId,String key,String cvalue){
		logger.debug("权限初始化[" +roleId+"]\t"+ key+"\t"+cvalue+"");
		Map<String,String> pmtMap=takeMapByRoleId(roleId);
		pmtMap.put(key, cvalue);
	}


	/**
	 * 根据roleId返回权限列表
	 * @param roleId
	 * @return
	 */
	public Map<String,String> takeMapByRoleId(Integer roleId){
		if(roleId==null)
			return new HashMap<String, String>();
		Map<String,String> ret=pMap.get(roleId);
		if(ret==null){
			ret=new HashMap<String, String>();
			pMap.put(roleId, ret);
		}		
		return ret;
	}
	
	/**
	 * 检查对象是否是用户的，用于get,edit和delete 
	 * @param sessionUser
	 * @param obj 取出的对象
	 * @param tname 表名
	 * @return
	 */
	public boolean checkObj(User sessionUser,Object obj,String tname){
		if(sessionUser==null)return true;
		//超级管理员有所有权限
		if(SpecRoleService.isAdmin(sessionUser))
			return true;
		String key="3."+tname;
		return checkObjInPermitMap(key,sessionUser,obj);
	}
	
	/**
	 * 加where，用于list	表查增
	 * @param sessionUser
	 * @param tname 表名
	 * @return
	 */
	public String addWhere(User sessionUser,String tname){
		if(sessionUser==null)
			return "";
		//超级管理员有所有权限
		if(SpecRoleService.isAdmin(sessionUser))
			return "";

		String key="2."+tname;
		return addhqlPermitMap(key, sessionUser);
	}

	/**
	 * 41.新增时，给禁填数据们放个默认值
	 * @param permtMap
	 * @param className
	 * @param sessionobj session给的对象，通常是sessionUser
	 * @param dbobj 数据库给的对象 
	 * @param dest 返回的结果对象
	 */
	public void createWithDefaultValue(String className,User sessionUser,Object dbobj,Object dest){
		Map<String,String> permtMap=pMap.get(sessionUser.getRoleId());
		if(permtMap==null || permtMap.size()==0)
			return ;
		String field="";
		for (String key : permtMap.keySet()) {
			if(key.indexOf("41."+className+".")==0){
				//字段
				field=key.replaceAll("41."+className+".", "");
				if(StringUtils.isBlank(field))
					continue;
				Object value=calcuResultBySessionobj(key, sessionUser, dbobj);
				if(value==null)
					continue;
				ClassUtils.setFieldValue(dest, field, value);
			}
		}
		return ;
	}
	
	/**
	 * 42.更改时，给禁填数据们放个默认值  value=1~公式
	 * @param permtMap
	 * @param className
	 * @param sessionobj session给的对象，通常是sessionUser
	 * @param dbobj 数据库给的对象 
	 * @param dest 返回的结果对象
	 */
	public void updateWithDefaultValue(String className,User sessionUser,Object dbobj,Object dest){
		Map<String,String> permtMap=pMap.get(sessionUser.getRoleId());
		if(permtMap==null || permtMap.size()==0)
			return ;
		String field="";
		for (String key : permtMap.keySet()) {
			if(key.indexOf("42."+className+".")==0){
				//字段
				field=key.replaceAll("42."+className+".", "");
				if(StringUtils.isBlank(field))
					continue;
				Object value=calcuResultBySessionobj(key, sessionUser, dbobj);
				if(value==null)
					continue;
				ClassUtils.setFieldValue(dest, field, value);
			}
		}
		return ;
	}	
	/**
	 * 42.更改保存前返回允许用户修改的字段集合
	 * 先看0.Xxx.edit(permittable相应的权限)，0完全无权，91完全有权，9部分有权
	 * 如果是9部分有权，则查permitfield之value=9，这些都可以改。其它不行。
	 * @param className
	 * @return Set<String> 可以改的字段集合，不能改的字段要从老DB中复制 ,0完全无权返回空，91完全有权返回{"91all"}，9部分有权{"aa","bb"...}
	 */
	public Set<String> keepSessionByPermit(String className,User sessionUser){
		Map<String,String> permtMap=pMap.get(sessionUser.getRoleId());
		Set<String> set=new HashSet<String>();
		if(permtMap==null || permtMap.size()==0)
			return set;
		String item=""; //也就是字段名
		String key="0."+className+".edit"; 
		String pvalue=permtMap.get(key);
		if("0".equals(pvalue)){
			//完全没权，返回空set
			return set;
		}else if("91".equals(pvalue)){
			//完全有权，返回所有字段的set
			set.add("91all");
			return set;
		}else{
			//部分有权，返回permitfield之value=9
			for (String str : permtMap.keySet()) {
				if(str.indexOf("42."+className+".")==0){
					String value=permtMap.get(str);
					item=str.replaceAll("42."+className+".", "");
					if(StringUtils.isNotBlank(item)&&"9".equals(value)){
						set.add(item);
					}
				}
			}
		}
		return set;
	}
	/**
	 * 通过sessionobj计算产生一个结果，
	 * 用于41/42，给禁填数据放个默认值	 
	 * @param map
	 * @param key
	 * @param sessionobj session给的对象
	 * @param dbobj 数据库给的对象
	 * @return
	 */
	public Object calcuResultBySessionobj(String key,User sessionUser,Object dbobj){
		Map<String,String> permtMap=pMap.get(sessionUser.getRoleId());
		String value=(permtMap==null)?null:permtMap.get(key);
		if(StringUtils.isBlank(value)) //不禁止
			return null; 
		if(value.indexOf("~")==-1)
			return null;
		String vscript=value.split("~")[1];
		if(sessionUser!=null)
			shell.setVariable("sessionobj",	sessionUser);
		if(dbobj!=null)
			shell.setVariable("dbobj",	dbobj);
		Script script = shell.parse("return "+vscript);
		return script.run();
	}

	/**
	 * 表改删查检查判断
	 * 3.user:return dbobj.userId==sessionobj.id
	 * @param map
	 * @param key
	 * @param sessionobj
	 * @return
	 */
	private boolean checkObjInPermitMap(String key,User sessionUser,Object dbobj){
		Map<String,String> map=pMap.get(sessionUser.getRoleId());
		String value=(map==null)?null:map.get(key);
		if(StringUtils.isBlank(value)) //不禁止
			return true; 
		if(sessionUser==null||dbobj==null)
			return false;
		shell.setVariable("sessionobj",	sessionUser);
		shell.setVariable("dbobj",	dbobj);
		Script script = shell.parse("return "+value);
		return (Boolean)script.run();
	}
	/**
	 * 表查增
	 * 2.user:"userId="+sessonobj+".id"
	 * @param map
	 * @param key
	 * @param sessionobj
	 * @return
	 */
	private String addhqlPermitMap(String key,User sessionUser){
		Map<String,String> map=pMap.get(sessionUser.getRoleId());
		String value=(map==null)?null:map.get(key);
		if(StringUtils.isBlank(value)) //不禁止
			return ""; 
		shell.setVariable("sessionobj",	sessionUser);
		Script script = shell.parse("return "+value); //return "id=3"
		String ret= ""+script.run();
		ret=(ret==null)?"":ret;
		ret=(ret.equals("null"))?"":ret;	
		return ret;
	}
}
