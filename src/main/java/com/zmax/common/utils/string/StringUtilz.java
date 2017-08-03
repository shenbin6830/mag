/**
 * 
 */
package com.zmax.common.utils.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;


/**
 * @author zmax
 *
 */
public class StringUtilz {

	/**
	 * 获取字符串的长度，如果有中文，则每个中文字符计为2位
	 * 
	 * @param value
	 *            指定的字符串
	 * @return 字符串的长度
	 */
	public static int lengthWithGb(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
			String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}
	/**
	 * 占位符替换{n}版
	 * ex:fillStringByArgs("abc{0}de{1}",new String[]{"1","2"})=abc1de2
	 * @param str String
	 * @param arr String[]
	 * @return
	 */
	public static String fillStringByArgs(String str,String[] arr){
		Matcher m=Pattern.compile("\\{(\\d)\\}").matcher(str);
		while(m.find()){
			str=str.replace(m.group(),arr[Integer.parseInt(m.group(1))]);
		}
		return str;
	}
	/**
	 * 占位符替换问号版
	 * fillQuesStringByArgs("abc=? and de=?",new Object[]{"1",2}) = abc="1" and de=2
	 * @param str String
	 * @param arr Object[]
	 * @return
	 */
	public static String fillQuesStringByArgs(String str,Object[] arr){
		Matcher m=Pattern.compile("\\?").matcher(str);
		int index=0;
		StringBuffer sb = new StringBuffer(); 
		while(m.find()){
			Object obj=arr[index];
			m.appendReplacement(sb, (obj instanceof String)?"\""+obj+"\"":obj.toString()); 
			index++;
		}
		m.appendTail(sb); 
		return sb.toString();
	}
	/**
	 * 获取JavaClass的简写 ex:java.lang.Integer -> Integer
	 * @param clazz
	 * @return
	 */
	public static String getJavaClassSimpleName(String clazz) {
		if(clazz == null) return null;
		if(clazz.lastIndexOf(".") >= 0) {
			return clazz.substring(clazz.lastIndexOf(".")+1);
		}
		return clazz;
	}
	/**
	 * 从STR文本中截取start到end之间的字符
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String cutFromStringStarttoStringEnd(String str,String start,String end){
		String ret=null;
		int lenStart=-1;
		if((lenStart=str.indexOf(start))>=0){
			int lenEnd=str.indexOf(end,lenStart);
			if(lenEnd>-1)
				ret=(str.substring(lenStart+start.length(),lenEnd));
		}
		return ret;
	}
	/**
	 * 文本版路径的父目录,
	 * /aaa/bbb/123.htm -> /aaa/bbb
	 * /aaa-> ''
	 * @param clazz
	 * @return
	 */
	public static String fileNameParent(String path) {
		if(path == null) return null;
		if(path.lastIndexOf("/") >= 0) {
			return path.substring(0,path.lastIndexOf("/"));
		}
		return path;
	}	
	/**
	 * 过滤文件名中的非法字符
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public   static   String FileNameFilter(String str)   throws   PatternSyntaxException   {      
		// 只允许字母和数字        
		// String   regEx  =  "[^a-zA-Z0-9]";                      
		// 清除掉所有特殊字符   
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";   
		Pattern   p   =   Pattern.compile(regEx);      
		Matcher   m   =   p.matcher(str);      
		return   m.replaceAll("").trim();      
	}
	/**
	 * 把一个完整的带路径的文件名，返回单独文件名
	 * @param fullpathFilename ex. /aaa/bbb/ccc.jpg
	 * @return ccc.jpg
	 */
	public static String FileNameByRemoveDir(String fullpathFilename){
		int atg=fullpathFilename.lastIndexOf("/");
		if(atg==-1) return fullpathFilename;
		return fullpathFilename.substring(atg+1, fullpathFilename.length());
	}


	/**
	 * 中文转Unicode同时转换字符  ，0-255参见ASCII 对照表
	 * @param str
	 * @return
	 */
	public static String toUnicode(String str){
		char[] arChar=str.toCharArray();
		int iValue=0;
		String uStr="";
		for(int i=0;i<arChar.length;i++){
			iValue=(int)str.charAt(i);           
			if(iValue<=256){
				// uStr+="& "+Integer.toHexString(iValue)+";";
				uStr+="\\"+Integer.toHexString(iValue);
			}else{
				// uStr+="&#x"+Integer.toHexString(iValue)+";";
				uStr+="\\u"+Integer.toHexString(iValue);
			}
		}
		return uStr;
	}


	/**
	 * 中文转Unicode 只转换中文
	 * @param s
	 * @return
	 */
	public   static   String   unicodeToGB(String   s)   {      
		StringBuffer   sb   =   new   StringBuffer();      
		StringTokenizer   st   =   new   StringTokenizer(s,   "\\u");      
		while   (st.hasMoreTokens())   {      
			sb.append(   (char)   Integer.parseInt(st.nextToken(),   16));      
		}      
		return   sb.toString();      
	}  
	/**
	 * Unicode转中文
	 * @param s
	 * @return
	 */
	public static String toUnicodeString(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			}
			else {
				sb.append("\\u"+Integer.toHexString(c));
			}
		}
		return sb.toString();
	}
	/**
	 * 判断字符串中是否有中文
	 * @param chineseStr
	 * @return
	 */
	public static final boolean haveChineseCharacter(String chineseStr) {
		char[] charArray = chineseStr.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 给URL加上参数，如果之前没参数加?xxx=a否则加&xxx=a
	 * @param url
	 * @param param
	 * @return
	 */
	public static String urlAddParam(String url,String param){
		if(StringUtils.isBlank(param))
			return url;
		if(StringUtils.indexOf(url, "?")==-1){
			url+="?"+param;
		}else{
			url+="&"+param;
		}

		return url;
	}
	/**
	 * 子路径相加
	 * @param parent 父	ex:/ or /aaa or /aaa/bbb
	 * @param child	子	ex:ccc
	 * @return "/"+"/aaa"="/aaa";""+"/aaa"="/aaa";"/bbb"+"/aaa"="/bbb/aaa"
	 */
	public static String urlParentAddChild(String parent,String child){
		if(StringUtils.isBlank(parent) ||"/".equals(parent))
			return "/"+child;
		return parent+"/"+child;
	}
	/**
	 * url根目录加子目录
	 * @param httpurl ex. "http://aaa.com"(推荐) or "http://aaa.com/" 
	 * @param child ex. "" or "/" or "bbb" or "/bbb"(推荐) 
	 * @return "http://aaa.com/bbb"
	 */
	public static String urlRootAddChild(String httpurl,String child){
		httpurl=(httpurl==null)?"":httpurl;
		child=(child==null)?"":child;
		String ret=httpurl+"/"+child;
		//把//换成/，但是保留http://和https://
		ret=urlFormat(ret);
		//如果最后一位是/，去掉
		if(ret.charAt(ret.length()-1)=='/')
			ret=ret.substring(0, ret.length()-1);
		return ret;
	}	
	/**
	 * URL格式化，替换\\\ to / , // to / 
	 * @param url
	 * @return
	 */
	public static String urlFormat(String url){
		if (StringUtils.isBlank(url)) {
			return url;
		}
		//前缀
		String prefix = "";
		if ( url.indexOf("://") != -1 ) {
			prefix = url.substring(0, url.indexOf("://") + 3);
			url = url.replaceFirst(prefix, "");
		}
		url = url.replace("\\\\\\", "/");
		url = url.replace("\\\\", "/");
		url = url.replace("///", "/");
		url = url.replace("//", "/");
		// preserve // at the beginning for UNC paths
		if ( url.startsWith("//") ) {                    
			url = "/" + url.replace("/+", "/");
		} else {
			url = url.replace("/+", "/");
		}
		return prefix.concat(url);
	}
	/**
	 * 路径拆成父子数组
	 * @param url	/aaa or /aaa/bbb or /aaa/bbb/ccc
	 * @return String[0] parent 父	ex:/ or /aaa or /aaa/bbb;String[1] child	子	ex:ccc
	 */
	public static String[] urlSplitToParentAndChild(String url){
		if(url.charAt(0)!='/')
			return new String[]{"/",""};
		if(StringUtils.isBlank(url) ||"/".equals(url))
			return new String[]{"/",""};
		int atg=url.lastIndexOf("/");
		if(atg==-1)
			return new String[]{"/",url};
		String parent=url.substring(0, atg);
		if(StringUtils.isBlank(parent))
			parent="/";
		String child=url.substring(atg+1);
		return new String[]{parent,child};
	}
	/**
	 * 从url中获取域名
	 * http://test2.aaa.com/bo/pub/wx/support/1.html --> http://test2.maykeys.com
	 * @param url
	 * @return http://test2.aaa.com
	 */
	public static String domainFromUrl(String url){
		String ret=url;
		if(StringUtils.countMatches(url, "/")<3)
			return url;
		int atg=0;
		if(url.indexOf("http://")==0)
			atg=url.indexOf("/", "http://".length());
		if(url.indexOf("https://")==0)
			atg=url.indexOf("/", "https://".length());
		if(atg!=0)
			ret=url.substring(0,atg);
		return ret;
	}

	/**
	 * 从ip的字符串形式得到字节数组形式
	 * 
	 * @param ip
	 *            字符串形式的ip
	 * @return 字节数组形式的ip
	 */
	public static byte[] getIpByteArrayFromString(String ip) {
		byte[] ret = new byte[4];
		StringTokenizer st = new StringTokenizer(ip, ".");
		try {
			ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
		} catch (Exception e) {
			//			LogFactory.log("从ip的字符串形式得到字节数组形式报错", Level.ERROR, e);
		}
		return ret;
	}

	/**
	 * @param ip
	 *            ip的字节数组形式
	 * @return 字符串形式的ip
	 */
	public static String getIpStringFromBytes(byte[] ip) {
		StringBuilder sb = new StringBuilder();
		sb.delete(0, sb.length());
		sb.append(ip[0] & 0xFF);
		sb.append('.');
		sb.append(ip[1] & 0xFF);
		sb.append('.');
		sb.append(ip[2] & 0xFF);
		sb.append('.');
		sb.append(ip[3] & 0xFF);
		return sb.toString();
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * 
	 * @param b
	 *            字节数组
	 * @param offset
	 *            要转换的起始位置
	 * @param len
	 *            要转换的长度
	 * @param encoding
	 *            编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, int offset, int len,
			String encoding) {
		try {
			return new String(b, offset, len, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b, offset, len);
		}
	}
	/**
	 * byte[] 相加
	 * @param first
	 * @param second
	 * @param firstLen 长度，如果为0则取.length
	 * @param secondLen 长度，如果为0则取.length
	 * @return
	 */
	public static byte[] byteAdd(byte[] first, byte[] second, int firstLen, int secondLen) {
		firstLen=(firstLen==0)?first.length:firstLen;
		secondLen=(secondLen==0)?second.length:secondLen;
		byte[] ret = new  byte[firstLen+secondLen];
		System.arraycopy(first,0,ret,0,firstLen);
		System.arraycopy(second,0,ret,firstLen,secondLen);
		return ret;
	}

	/**
	 * 两个where相加,where1和2的格式是 " where xxx" 或 "xxx"
	 * @param where1
	 * @param where2
	 * @return where where1 and where2 
	 */
	public static String whereAdd(String where1,String where2){
		String retwhere="";
		if(!StringUtils.isBlank(where1)){
			where1=where1.trim();
			if(where1.indexOf("where")==0)
				where1=where1.substring(5);
		}
		if(!StringUtils.isBlank(where2)){
			where2=where2.trim();
			if(where2.indexOf("where")==0)
				where2=where2.substring(5);
		}

		if(StringUtils.isBlank(where1)){
			if(StringUtils.isBlank(where2)){
				retwhere= "";
			}else{
				retwhere= where2;
			}
		}else{
			if(StringUtils.isBlank(where2)){
				retwhere= where1;
			}else{
				retwhere= where1 +" and "+where2;
			}
		}
		retwhere=retwhere.trim();
		if(StringUtils.isBlank(retwhere))
			return "";
		if(retwhere.equals("where"))
			return "";
		if(retwhere.indexOf("where")!=0)
			retwhere=" where "+retwhere;
		return retwhere;
	}
	
	/**
	 * 两个where相加，前面不放where,where1和2的格式是 " where xxx" 或 "xxx"
	 * @param where1
	 * @param where2
	 * @return where1 and where2 
	 */
	public static String queryAdd(String where1,String where2){
		String retwhere="";
		if(!StringUtils.isBlank(where1)){
			where1=where1.trim();
			if(where1.indexOf("where")==0)
				where1=where1.substring(5);
		}
		if(!StringUtils.isBlank(where2)){
			where2=where2.trim();
			if(where2.indexOf("where")==0)
				where2=where2.substring(5);
		}

		if(StringUtils.isBlank(where1)){
			if(StringUtils.isBlank(where2)){
				retwhere= "";
			}else{
				retwhere= where2;
			}
		}else{
			if(StringUtils.isBlank(where2)){
				retwhere= where1;
			}else{
				retwhere= where1 +" and "+where2;
			}
		}
		retwhere=retwhere.trim();
		if(StringUtils.isBlank(retwhere))
			return "";
		if(retwhere.equals("where"))
			return "";
		return retwhere;
	}

	/**
	 * 最后一步的where 加 "where "
	 * @param retwhere
	 * @return
	 */
	public static String whereLast(String where){
		if(StringUtils.isBlank(where))
			return "";
		where=where.trim();
		if(StringUtils.isBlank(where))
			return "";
		if(where.equals("where")) 
			return "";
		if(where.indexOf("where")!=0)
			where=" where "+where+" ";
		return " "+where+" ";
	}
	/**
	 * 数组转  id in (1,2,3)文本
	 * @param ids
	 * @return
	 */
	public static String whereIn(Object[] ids){
		String where=" id in ("+ids[0];
		for (int i = 1; i < ids.length; i++) {
			where+=","+ids[i];
		}
		where+=")";
		return where;
	}
	/**
	 * 数组转  (1,2,3)文本
	 * @param ids
	 * @return (1,2,3)
	 */
	public static String whereKhIn(Object[] ids){
		String where="("+ids[0];
		for (int i = 1; i < ids.length; i++) {
			where+=","+ids[i];
		}
		where+=")";
		return where;
	}
	/**
	 * 如果hql 不是以from Table 开头，加上from Table
	 * @param hql
	 * @return
	 */
	public static String fixHql(String hql){
		hql=hql.trim();
		if(hql.indexOf("from ")==0)
			return hql;
		return "from "+hql;
	}
	/**
	 * 修复orderby
	 * @param order ex.id or id desc
	 * @return ' order by id desc' or ' order by id asc' 
	 */
	public static String fixOrderBy(String order){
		if(StringUtils.isBlank(order))
			return "";

		order=order.trim();
		if(order.indexOf("order by")==0)
			return " "+order;
		return " order by "+order;
	}



	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	public static String removeOrders(String hql) {
		if(hql==null)return "";
		if(hql.equals(""))return "";
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 大写转下划线，对象名转表名
	 * @param param
	 * @return ex. aBbCc -> a_bb_cc
	 */
	public static String cap2Undline(String param){
		Pattern  p=Pattern.compile("[A-Z]");
		if(param==null ||param.equals("")){
			return "";
		}
		StringBuilder builder=new StringBuilder(param);
		Matcher mc=p.matcher(param);
		int i=0;
		while(mc.find()){
			builder.replace(mc.start()+i, mc.end()+i, "_"+mc.group().toLowerCase());
			i++;
		}

		if('_' == builder.charAt(0)){
			builder.deleteCharAt(0);
		}
		return builder.toString();
	}
	/**
	 * 下划线转大写，把a_bb_cc变成aBbCc
	 * @param sqlName
	 * @return
	 */
	public static String undline2Cap(String sqlName) {
		String[] strs = sqlName.toLowerCase().split("_");
		String result = "";
		String preStr = "";
		for(int i = 0; i < strs.length; i++) {
			if(preStr.length() == 1) {
				result += strs[i];
			}else {
				result += capitalize(strs[i]);
			}
			preStr = strs[i];
		}
		return result;
	}
	/**
	 * 首字变大写
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}
	/**
	 * 首字变大写或小写
	 * @param str
	 * @param capitalize true大写;false小写
	 * @return
	 */
	public static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		}
		else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}
	/**
	 * Integer是null返回0
	 * @param param
	 * @return
	 */
	public static int integerNull20(Integer param){
		return (param==null)?0:param.intValue();
	}
	/**
	 * Double是null返回0
	 * @param param
	 * @return
	 */
	public static double doubleNull20(Double param){
		return (param==null)?0.0:param.doubleValue();
	}
	/**
	 * Integer是null或0
	 * @param param
	 * @return
	 */
	public static boolean integerNullOr0(Integer param){
		if(param==null)
			return true;
		if(param.intValue()==0)
			return true;
		return false;
	}
	/**
	 * 文本转整数
	 * @param src
	 * @param defaultvalue 默认值
	 * @return
	 */
	public static int parseInt(String src,int defaultvalue){
		if(StringUtils.isBlank(src))
			return defaultvalue;
		try {
			return Integer.parseInt(src);
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			return defaultvalue;
		}
	}
	/**
	 * List是否为空
	 * @param list
	 * @return
	 */
	public static boolean isblank(List list){
		return (list==null || list.size()==0);
	}
	/**
	 * 查找 1 是否在  1,2,3,4,5中
	 * @param ids 1,2,3,4,5
	 * @param id 1
	 * @param regex 分隔符号 ,默认逗号
	 * @return
	 */
	public static boolean indexofsa(String ids,String id,String regex){
		regex=(regex==null)?",":regex;
		String[] atmp=ids.split(regex);
		for (String string : atmp) {
			if(id.equals(string))
				return true;
		}
		return false;
	}
	/**
	 * 把中文转成Unicode码
	 * @param str
	 * @return
	 */
	public static String chinaToUnicode(String str){
		if(StringUtils.isBlank(str))return "";
		String result="";
		for (int i = 0; i < str.length(); i++){
			int chr1 = (char) str.charAt(i);
			if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)
				result+="\\u" + Integer.toHexString(chr1);
			}else{
				result+=str.charAt(i);
			}
		}
		return result;
	}

	/**
	 * 判断是否为中文字符
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
	/**
	 * 中文转unicode方法2
	 * @param s
	 * @return
	 */
	public static String string2Unicode(String s) {
		try {
			StringBuffer out = new StringBuffer("");
			byte[] bytes = s.getBytes("unicode");
			for (int i = 2; i < bytes.length - 1; i += 2) {
				out.append("u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				String str1 = Integer.toHexString(bytes[i] & 0xff);

				out.append(str);
				out.append(str1);
				out.append(" ");
			}
			return out.toString().toUpperCase();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	} 


	/**
	 * unicode转中文
	 * @param unicodeStr
	 * @return
	 */
	public static String unicode2String(String unicodeStr){
		StringBuffer sb = new StringBuffer();
		String str[] = unicodeStr.toUpperCase().split("U");
		for(int i=0;i<str.length;i++){
			if(str[i].equals("")) continue;
			char c = (char)Integer.parseInt(str[i].trim(),16);//NumberFormatException
			sb.append(c);
		}
		return sb.toString();
	}
	
	/**
	 * 数据转分隔符相加的文本
	 * {"1","2"}=>"1,2"
	 * @param arr Object[]
	 * @param delim 分隔符 String 如果是null则使用逗号
	 * @return
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (arr == null || arr.length == 0) {
			return "";
		}
		delim=(delim==null)?",":delim;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}	

	/**
	 * {1,2,3} => "1,2,3"
	 * @param arr
	 * @param split
	 * @return
	 */
	public static String arrayToString(Object[] arr,String split){
		if(arr==null)return null;
		if(arr.length==0)return "";
		String ret=""+arr[0];
		split=(split==null)?",":split;
		for (int i = 1; i < arr.length; i++) {
			ret+=split+arr[i];
		}
		return ret;
	}
	/**
	 * map转str {k1=v1,k2=v2}转"k1=v1&k2=v2"
	 * @param params Map<String, String>
	 * @return
	 */
	public static String map2str(Map<String, String> params){
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }
        return content.toString();
	}
	/**
	 * str转map "k1=v1&k2=v2" 转  {k1=v1,k2=v2}
	 * @param str String
	 * @return
	 */
	public static  Map<String,String> str2Map(String str){
		Map<String,String> params=new HashMap<String, String>();
		str=StringEscapeUtils.unescapeHtml(str);
		String[] s1=str.split("&");
		for (String string : s1) {
			if(string.indexOf("sign=")==0){
				String sign=string.substring(5);
				sign=URLDecoder.decode(sign);
				System.out.println("sign=" + sign);
				params.put("sign", sign);
			}else{
				String[] s2=string.split("=");
				if(s2.length>1){
					String v=URLDecoder.decode(s2[1]);
					
					System.out.println("name="+s2[0]+",value="+v);
					params.put(s2[0], v);
				}
			}
		}
		return params;
	}
	/**
	 * UTF-8的编码
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source){
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 返回 "1,2,3" + 4 = "1,2,3,4"
	 * @param fatherfamily
	 * @param fatherid
	 * @return
	 */
	public static String addFamily(String fatherfamily,Integer fatherid){
		if(StringUtils.isBlank(fatherfamily)){
			return ""+StringUtilz.integerNull20(fatherid);
		}else{
			return fatherfamily+","+StringUtilz.integerNull20(fatherid);
		}
			
	}
	
	
	/**
	 * 去除text中的utf8Mb4字符
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	static public String removeUtf8Mb4(String text) throws UnsupportedEncodingException {
		byte[] bytes = text.getBytes("utf-8");
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		int i = 0;
		while (i < bytes.length) {
			short b = bytes[i];
			if (b > 0) {
				buffer.put(bytes[i++]);
				continue;
			}
			b += 256;
			if ((b ^ 0xC0) >> 4 == 0) {
				buffer.put(bytes, i, 2);
				i += 2;
			}
			else if ((b ^ 0xE0) >> 4 == 0) {
				buffer.put(bytes, i, 3);
				i += 3;
			}
			else if ((b ^ 0xF0) >> 4 == 0) {
				i += 4;
			}
		}
		buffer.flip();
		return new String(buffer.array(), "utf-8");
	}
	/**
	 * 去除特殊字符，基本上只保留中英文数字
	 * @param s
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	static public String removeSpecLetter(String s) throws UnsupportedEncodingException {
	    char[] chars = s.toCharArray();  
	    String ss="";
	    for(int i = 0; i < chars.length; i ++) {  
	        if((chars[i] >= 19968 && chars[i] <= 40869) || (chars[i] >= 97 && chars[i] <= 122) || (chars[i] >= 65 && chars[i] <= 90)) {  
	        	ss+=chars[i];
	        	//System.out.print(chars[i]);  
	        }  
	    }
		return ss;  
	}	
	
	/**
	 * 为判断，用于url参数传递时，如果为"null"，也算空
	 * @param str
	 * @return
	 */
	public static boolean isBlankIncludeStrNull(String str){
		if(StringUtils.isBlank(str))
			return true;
		if(StringUtils.endsWithIgnoreCase(str, "null"))
			return true;
		return false;
	}
	/**
	 * 如果str=='null' 返回null
	 * @param str
	 * @return
	 */
	public static String nullStrToNull(String str){
		if(null==str)
			return null;
		if(StringUtils.endsWithIgnoreCase(str, "null"))
			return null;
		return str;

	}
	/**
	 * 数组最小值
	 * @param aint
	 * @return
	 */
	public static Integer minArr(Integer[] aint){
		return Collections.min(Arrays.asList(aint));		
	}
	/**
	 * 数组最大值
	 * @param aint
	 * @return
	 */
	public static Integer maxArr(Integer[] aint){
		return Collections.max(Arrays.asList(aint));		
	}
	/**
	 * a`{$=,&b - a\`\{\$\)b
	 * u\=1601714428\,1728073346\&fm\=21\&gp\=0.jpg 
	 * @param str
	 * @return
	 */
	public static String linuxFilename(String str){
		str=str.replaceAll("\\`", "\\\\`");
		str=str.replaceAll("\\{", "\\\\{");
		str=str.replaceAll("\\}", "\\\\}");
		str=str.replaceAll("\\$", "\\\\\\$");
		str=str.replaceAll("\\(", "\\\\(");
		str=str.replaceAll("\\)", "\\\\)");
		str=str.replaceAll("=", "\\\\=");
		str=str.replaceAll(",", "\\\\,");
		str=str.replaceAll("&", "\\\\&");
		return str;
	}
	/**
	 * 替换转义符为指定符，默认为_，主要用于文件名，所以点不换
	 * ex.
	 * @param str ex.~`!@#$%^&*()+=|\\{}[]:;\"',.<>/?
	 * @param rep
	 * @return
	 */
	public static String repMassChar(String str,String rep){
		if(rep==null)
			rep="_";
		str=str.replaceAll("`", "_");
		str=str.replaceAll("~", "_");

		str=str.replaceAll("!", "_");
		str=str.replaceAll("@", "_");
		str=str.replaceAll("#", "_");
		str=str.replaceAll("\\$", "_");
		str=str.replaceAll("\\%", "_");
		
		str=str.replaceAll("\\^", "_");
		str=str.replaceAll("&", "_");
		str=str.replaceAll("\\*", "_");
		str=str.replaceAll("\\(", "_");
		str=str.replaceAll("\\)", "_");

		str=str.replaceAll("=", "_");
		str=str.replaceAll("\\+", "_");
		str=str.replaceAll("\\\\", "_");
		str=str.replaceAll("\\|", "_");

		str=str.replaceAll("\\[", "_");
		str=str.replaceAll("\\{", "_");
		str=str.replaceAll("\\]", "_");
		str=str.replaceAll("\\}", "_");
		
		str=str.replaceAll(":", "_");
		str=str.replaceAll(";", "_");
		str=str.replaceAll("\"", "_");
		str=str.replaceAll("'", "_");

		str=str.replaceAll(",", "_");
		//str=str.replaceAll("\\.", "_");//点不能换
		str=str.replaceAll("<", "_");
		str=str.replaceAll(">", "_");
		str=str.replaceAll("\\/", "_");
		str=str.replaceAll("\\?", "_");
		
		str=str.replaceAll(" ", "_");
		return str;
	}
}
