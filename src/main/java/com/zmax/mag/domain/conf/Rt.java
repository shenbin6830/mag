package com.zmax.mag.domain.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.bean.Wxcfg;
import com.zmax.mag.domain.bean.Wxouser;

/**
 * RunTime运行时参数放在这里
 * 
 * @author zmax
 *
 */
public class Rt {
//	/** 运行模式：开发/win线上/linux线上 */
//	public static String runtime_MODE = "zmaxdev";
//	/** 和conf中prop.my.hddir一致的路径，由配置文件决定，启动时注入，通常是 /opt/xxx */
//	public static String runtime_hddir = "/";
//	/** 运行时的硬盘根目录，需要启动器注入 */
//	public static String runtime_harddisk_rootpath = "/";
//	/** 运行时的用户上传硬盘根目录，依赖runtime_harddisk_rootpath */
//	public static String runtime_harddisk_rootpath_userupload = "/";
//	/** 微信配置列表 */
//	public static Map<Integer, Wxcfg> wxcfgMap = new HashMap<Integer, Wxcfg>();
//	/** 正在进行医患聊天的用户列表 userId,User */
//	public static Map<String, User> dmmsgMap = new HashMap<String, User>();
//
//	/** ios需要被推送的列表 会员版 */
//	public static List<String> iosListPush = new ArrayList<String>();
//	/** ios需要被推送的列表 医生版 */
//	public static List<String> iosListPushB = new ArrayList<String>();
//	/** B2C网站限制登录用户列表 多次登录失败后限制登录 */
//	public static Map<Integer, User> loginB2CMap = new HashMap<Integer, User>();
//
//	/** 腾讯Code和录用户Wxouser的Map，根据腾讯Code到腾讯去取Wxouser,每过5分钟清除一次，时间在Wxouser的obj1中 */
//	public static Map<String, Wxouser> mapCw = new HashMap<String, Wxouser>();
	/**运行模式：开发/win线上/linux线上 */
	public static String runtime_MODE="zmaxdev";
	/**和conf中prop.my.hddir一致的路径，由配置文件决定，启动时注入，通常是 /opt/xxx */
	public static String runtime_hddir="/";
	/**运行时的硬盘根目录，需要启动器注入*/
	public static String runtime_harddisk_rootpath="/";
	/**运行时的用户上传硬盘根目录，依赖runtime_harddisk_rootpath*/
	public static String runtime_harddisk_rootpath_userupload="/";
	/**微信配置列表*/
	public static Map<Integer,Wxcfg> wxcfgMap=new HashMap<Integer, Wxcfg>();
	/**正在进行医患聊天的用户列表 userId,User*/
	public static Map<String,User> dmmsgMap=new HashMap<String, User>();
	/**ios需要被推送的列表 会员版*/
	public static List<String> iosListPush=new ArrayList<String>();
	/**ios需要被推送的列表 医生版*/
	public static List<String> iosListPushB=new ArrayList<String>();
	/**B2C网站限制登录用户列表  多次登录失败后限制登录*/
	public static Map<Integer,User> loginB2CMap=new HashMap<Integer, User>();
	
	/**腾讯Code和录用户Wxouser的Map，根据腾讯Code到腾讯去取Wxouser,每过5分钟清除一次，时间在Wxouser的obj1中*/ 
	public static Map<String,Wxouser> mapCw = new HashMap<String,Wxouser>();
}
