
package com.zmax.common.utils.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import com.zmax.common.utils.image.ImageInfo;
import com.zmax.common.utils.string.Num62;
import com.zmax.common.utils.string.StringUtilz;


/**
 * 文件名工具及文件工具
 * Static helper methods for files.
 * 
 * @version 
 * @author zmax
 */
public class FileNameUtils {
	/**不合法的URL标记，包括./,:,?,|,*, &quot;, &lt;, &gt; cntrl*/
	public static final Pattern ERRURL = Pattern.compile("\\.\\\\|\\.\\.|\\./|:|\\?|\\*|\"|<|>|\\p{Cntrl}");
	/**合法的文件名标记*/
	public static final Pattern ILLEGAL_CURRENT_FOLDER_PATTERN = Pattern
	.compile("^[^/]|[^/]$|/\\.{1,2}|\\\\|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}");
	/**
	 * 日期格式化对象
	 */
	public static final DateFormat MONTH_FORMAT = new SimpleDateFormat(
	"/yyyyMM/ddHHmmss");
	public static final DateFormat YYYY_MM_PATH = new SimpleDateFormat("/yyyyMM");
	public static final DateFormat ddHHmmss = new SimpleDateFormat("ddHHmmss");
	public static final DateFormat YYYYMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	/**排序之按文件名*/
	public static final int sortbyfilename=1;
	/**排序之按大小*/
	public static final int sortbysize=2;
	/**排序之按上次修改日期*/
	public static final int sortbylastModified=3;
	/**排序之按文件扩展名*/
	public static final int sortbyext=4;
	
	public static void mkdir(File file){
		if(file==null)return;
		if(file.exists())return;
		File parent=file.getParentFile();
		while (!parent.exists()){
			parent.mkdirs();
		}
		
	}
	/**
	 * 列目标目录中的目录，以文件夹名排序
	 * @param abpath 目标目录
	 * @param isdesc 是否倒序
	 * @return
	 * @throws Exception
	 */
	public static List<String> listDirectory(String abpath,boolean isdesc) throws Exception{
		File realDir=new File(abpath);
		if (!realDir.exists() || !realDir.isDirectory())
			throw new Exception("dir not exists");
		List<String> files = Arrays.asList(realDir.list(DirectoryFileFilter.DIRECTORY));

		Collections.sort(files, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});	

		if(isdesc)Collections.reverse(files);
		return files;
	}
	/**
	 * 列目标目录中的文件
	 * @param abpath 目标目录
	 * @param allowExt 允许列的文件扩展,null全列
	 * @param sortby 排序方式,见sortbyfilename...
	 * @param isdesc 是否倒序
	 * @return
	 * @throws Exception
	 */
	public static List<File> listFiles(String abpath,Set<String> allowExt,int sortby,boolean isdesc) throws Exception{
		return listDirsAndFiles(abpath, allowExt, sortby, isdesc, true);
	}
	/**
	 * 列目标目录中的文件和目录
	 * @param abpath 目标目录
	 * @param allowExt 允许列的文件扩展,null全列
	 * @param sortby 排序方式,见sortbyfilename...
	 * @param isdesc 是否倒序
	 * @param isonlyfiles 只有文件，不包括目录？
	 * @return
	 * @throws Exception
	 */
	public static List<File> listDirsAndFiles(String abpath,Set<String> allowExt,int sortby,boolean isdesc,boolean isonlyfiles) throws Exception{
		File realDir=new File(abpath);
		if (!realDir.exists() || !realDir.isDirectory())
			throw new Exception("dir not exists");
		File[] aFile = null;
		if(isonlyfiles)
			aFile=realDir.listFiles((FileFilter) FileFileFilter.FILE);
		else
			aFile=realDir.listFiles();
		List<File> files=null;
		if(allowExt==null){
			files = Arrays.asList(aFile);
		}else{
			files=new ArrayList<File>();
			for (File file : aFile) {
				if(!file.isDirectory()&&!allowExt.contains(FilenameUtils.getExtension(file.getName()).toLowerCase()))
					continue;
				files.add(file);
			}
		}

		switch (sortby) {
		case sortbysize:
		{
			Collections.sort(files, new Comparator<File>(){
				@Override
				public int compare(File o1, File o2) {
					if(o1.isDirectory() && o2.isFile())
						return -1;
					if(o1.isFile() && o2.isDirectory())
						return 1;
					return Long.valueOf(o1.length()).compareTo( Long.valueOf(o2.length()));
				}
			});
			break;
		}
		case sortbylastModified:
		{
			Collections.sort(files, new Comparator<File>(){
				@Override
				public int compare(File o1, File o2) {
					if(o1.isDirectory() && o2.isFile())
						return -1;
					if(o1.isFile() && o2.isDirectory())
						return 1;
					return Long.valueOf(o1.lastModified()).compareTo( Long.valueOf(o2.lastModified()));
				}
			});
			break;
		}
		case sortbyext:
		{
			Collections.sort(files, new Comparator<File>(){
				@Override
				public int compare(File o1, File o2) {
					if(o1.isDirectory() && o2.isFile())
						return -1;
					if(o1.isFile() && o2.isDirectory())
						return 1;
					return FilenameUtils.getExtension(o1.getName()).toLowerCase().compareTo(FilenameUtils.getExtension(o2.getName()).toLowerCase());
				}
			});			
			break;
		}
		default: //sortbyfilename
		{
			Collections.sort(files, new Comparator<File>(){
				@Override
				public int compare(File o1, File o2) {
					if(o1.isDirectory() && o2.isFile())
						return -1;
					if(o1.isFile() && o2.isDirectory())
						return 1;
					return (o1.getName().toLowerCase()).compareTo((o2.getName().toLowerCase()));
				}
			});			
			break;
		}
		}
		if(isdesc)Collections.reverse(files);
		return files;
	}

	/**
	 * 将输入文件名称转为一个符合标准的文件名称
	 * Sanitizes a filename from certain chars.<br />
	 * 
	 * This method enforces the <code>forceSingleExtension</code> property and
	 * then replaces all occurrences of \, /, |, :, ?, *, &quot;, &lt;, &gt;,
	 * control chars by _ (underscore).
	 * 
	 * @param filename
	 *            a potentially 'malicious' filename
	 * @return sanitized filename
	 */
	public static String sanitizeFileName(final String filename) {

		if (StringUtils.isBlank(filename))
			return filename;

		String name = forceSingleExtension(filename) ;

		// Remove \ / | : ? * " < > 'Control Chars' with _
		return name.replaceAll("\\\\|/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
	}

	/**
	 * 将输入文件夹名称转为一个符合标准的文件夹名称
	 * Sanitizes a folder name from certain chars.<br />
	 * 
	 * This method replaces all occurrences of \, /, |, :, ?, *, &quot;, &lt;,
	 * &gt;, control chars by _ (underscore).
	 * 
	 * @param folderName
	 *            a potentially 'malicious' folder name
	 * @return sanitized folder name
	 */
	public static String sanitizeFolderName(final String folderName) {

		if (StringUtils.isBlank(folderName))
			return folderName;

		// Remove . \ / | : ? * " < > 'Control Chars' with _
		return folderName.replaceAll(
				"\\.|\\\\|/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
	}

	/**
	 * 根据流判断是否为图流
	 * Checks if the underlying input stream contains an image.
	 * 
	 * @param in
	 *            input stream of an image
	 * @return <code>true</code> if the underlying input stream contains an
	 *         image, else <code>false</code>
	 */
	public static boolean isImage(final InputStream in) {
		ImageInfo ii = new ImageInfo();
		ii.setInput(in);
		return ii.check();
	}

	/**
	 * 检查路径中是否有非法字符如../.../之类的
	 * Checks whether a path complies with the FCKeditor File Browser <a href="http://docs.fckeditor.net/FCKeditor_2.x/Developers_Guide/Server_Side_Integration#File_Browser_Requests"
	 * target="_blank">rules</a>.
	 * 
	 * @param path
	 *            a potentially 'malicious' path
	 * @return <code>true</code> if path complies with the rules, else
	 *         <code>false</code>
	 */
	public static boolean isValidPath(final String path) {
		if (StringUtils.isBlank(path))
			return false;

		if (ILLEGAL_CURRENT_FOLDER_PATTERN.matcher(path).find())
			return false;

		return true;
	}

	/**
	 * 替换.?!之类的变成_
	 * Replaces all dots in a filename with underscores except the last one.
	 * 
	 * @param filename
	 *            filename to sanitize
	 * @return string with a single dot only
	 */
	public static String forceSingleExtension(final String filename) {
		return filename.replaceAll("\\.(?![^.]+$)", "_");
	}

	/**
	 * 文件名中是否有非法字符
	 * Checks if a filename contains more than one dot.
	 * 
	 * @param filename
	 *            filename to check
	 * @return <code>true</code> if filename contains severals dots, else
	 *         <code>false</code>
	 */
	public static boolean isSingleExtension(final String filename) {
		return filename.matches("[^\\.]+\\.[^\\.]+");
	}

	/**
	 * 如果目录不存在，创建
	 * Checks a directory for existence and creates it if non-existent.
	 * 
	 * @param dir
	 *            directory to check/create
	 */
	public static void checkDirAndCreate(File dir) {
		if (!dir.exists())
			dir.mkdirs();
	}

	/**
	 * 如果文件名已经存在，产生一个新文件名(1...n)
	 * Iterates over a base name and returns the first non-existent file.<br />
	 * This method extracts a file's base name, iterates over it until the first
	 * non-existent appearance with <code>basename(n).ext</code>. Where n is a
	 * positive integer starting from one.
	 * 
	 * @param file
	 *            base file
	 * @return first non-existent file
	 */
	public static File getUniqueFile(final File file) {
		if (!file.exists())
			return file;

		File tmpFile = new File(file.getAbsolutePath());
		File parentDir = tmpFile.getParentFile();
		int count = 1;
		String extension = FilenameUtils.getExtension(tmpFile.getName());
		String baseName = FilenameUtils.getBaseName(tmpFile.getName());
		do {
			tmpFile = new File(parentDir, baseName + "(" + count++ + ")."
					+ extension);
		} while (tmpFile.exists());
		return tmpFile;
	}

	/**
	 * 产生一个时间及随机数的文件名/yyyyMM/ddHHmmss，带路径
	 * @param path
	 * @param ext
	 * @return
	 */
	public static String generatePathFilename(String path, String ext) {
		return path + MONTH_FORMAT.format(new Date())
		+ RandomStringUtils.random(4, Num62.N36_CHARS) + "." + ext;
	}
	/**
	 * 获取现在的时间yyyyMM格式的路径，如：/201205
	 * @return
	 */
	public static String yyyyMMPath(){
		return YYYY_MM_PATH.format(new Date());
	}
	/**
	 * 产生一个随机文件名，格式是ddHHmmss+4位随机英文
	 * @param path
	 * @param ext
	 * @return
	 */
	public static String generateRandomFilename(String ext) {
		return ddHHmmss.format(new Date())+RandomStringUtils.random(4, Num62.N36_CHARS) + "." + ext;
	}
	/**
	 * 产生一个随机文件名，格式是ddHHmmss+4位随机英文
	 * @param path
	 * @return
	 */
	public static String generateRandomFilenameDHm() {
		return ddHHmmss.format(new Date())+RandomStringUtils.random(4, Num62.N36_CHARS);
	}
	/**
	 * 产生一个随机文件名，格式是YYYYMMddHHmmss+4位随机英文
	 * @param path
	 * @return ex.20141128163839
	 */
	public static String generateRandomFilenameYMdHms() {
		return YYYYMMddHHmmss.format(new Date())+RandomStringUtils.random(4, Num62.N36_CHARS);
	}	
	
	/**
	 * 根据名称产生文件YYYYMMddHHmmss4位英文的随机文件，扩展名和路径不变
	 * @param url 如/aa/bb/cc.jpg
	 * @return  "/aa/bb/20140916110258ed4n.jpg"
	 */
	public static String generateRandomUrlYMdHms(String url) {
		int extAt= url.lastIndexOf(".");
		//扩展名
		String ext = "";
		if(extAt>-1){
			ext=url.substring(extAt);
		}
		//文件名前面的url
		int bAt=url.lastIndexOf("/");
		String burl= "";
		if(bAt>-1){
			burl=url.substring(0,bAt+1);
		}
		
		//String sdest=StringUtilz.urlParentAddChild(burl, FileNameUtils.generateRandomFilenameYMdHms()+ext);
		String sdest=burl+FileNameUtils.generateRandomFilenameYMdHms()+ext;
		return sdest;
	}
	/**
	 * 是不是合法的URL，不能包括./,../,:,?,|,*, &quot;, &lt;, &gt; cntrl
	 * @param url
	 * @return
	 */
	public static boolean isVaildUrl(String url){

		return !ERRURL.matcher(url).find();
	}
	/**
	 * 把不合法的URL替换成合法的url
	 * <br/>isVaildUrl(/aa/bb/cc.html)=true,/aa/bb/cc.html
<br/>isVaildUrl(/cc)=true,/cc
<br/>isVaildUrl(c*c)=false,c_c
<br/>isVaildUrl(./cc)=false,_cc
<br/>isVaildUrl(.\cc)=false,_cc
<br/>isVaildUrl(..\cc)=false,_\cc
<br/>isVaildUrl(...//cc*bb.cc)=false,__/cc_bb.cc

	 * @param url
	 * @return
	 */
	public static String replaceUrl(String url){
		if(isVaildUrl(url))return url;
		return url.replaceAll(ERRURL.pattern(), "_");
	}
	/**
	 * 获取扩展名
	 * @param url
	 * @return
	 */
	public static String getExt(String url){
		return url.substring(url.lastIndexOf(".")+1,url.length());
	}
	/**
	 * file转Base64
	 * @param file
	 * @return
	 */
	public static String file2Base64(File file) throws Exception{
			FileInputStream inputFile = new FileInputStream(file);  
			byte[] buffer = new byte[(int)file.length()];  
			inputFile.read(buffer);  
			inputFile.close();  
			Base64 base64=new Base64();  
			buffer=base64.encode(buffer);  
			String s=new String(buffer);
			return s;
	}
	/**
	 * 如果是有中文的文件名，转成URL格式
	 * @param chinesefilename
	 * @return
	 */
	public static String filenameChineseToUrlEncoder(String chinesefilename){
		if(!StringUtilz.haveChineseCharacter(chinesefilename)){
			return chinesefilename;
		}
		try {
			//两次
			chinesefilename= URLEncoder.encode(chinesefilename, "UTF-8");
			chinesefilename=StringUtils.replace(chinesefilename, "+", "%20");
			//chinesefilename= URLEncoder.encode(chinesefilename, "UTF-8");
			//linux
			//if(System.getProperties().getProperty("file.separator").equals("/"))chinesefilename=StringUtils.replace(chinesefilename, "%", "");
			//chinesefilename=StringUtils.replaceChars(chinesefilename, '%', 'z');
			//chinesefilename.replaceAll("\\%", "\\%25");
			//chinesefilename= URLEncoder.encode(chinesefilename, "UTF-8");
			//chinesefilename= URLEncoder.encode(chinesefilename, "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		return chinesefilename;
	}
	/**
	 * 如果是有中文的文件名，转成URL格式,win版本
	 * @param chinesefilename
	 * @return
	 */
	public static String filenameChineseToUrlEncoderWin(String chinesefilename){
		if(!StringUtilz.haveChineseCharacter(chinesefilename)){
			return chinesefilename;
		}
		try {
			chinesefilename= URLEncoder.encode(chinesefilename, "UTF-8");
			//chinesefilename=StringUtils.replace(chinesefilename, "%", "%25");
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		return chinesefilename;
	}
	/**
	public static void main(String[] args) {
		System.out.println("filenameChineseToUrlEncoder()="+filenameChineseToUrlEncoder("中文.png"));
		try {
			List<File> listFile;
			List<String> list = listDirectory("d:/tmp2", false);
			for (String string : list) {
				System.out.println("string=" + string);
			}
			System.out.println("======================");
			listFile = listFiles("d:/tmp2", null, 3, false);
			for (File file : listFile) {
				System.out.println("listFile.string=" + file.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(e);
		}
		String url;
		url="/aa/bb/cc.html";
		System.out.println("<br/>isVaildUrl("+url+")=" + isVaildUrl(url)+","+replaceUrl(url));		
		url="/cc";
		System.out.println("<br/>isVaildUrl("+url+")=" + isVaildUrl(url)+","+replaceUrl(url));		
		url="c*c";
		System.out.println("<br/>isVaildUrl("+url+")=" + isVaildUrl(url)+","+replaceUrl(url));		
		url="./cc";
		System.out.println("<br/>isVaildUrl("+url+")=" + isVaildUrl(url)+","+replaceUrl(url));		
		url=".\\cc";
		System.out.println("<br/>isVaildUrl("+url+")=" + isVaildUrl(url)+","+replaceUrl(url));		
		url="..\\cc";
		System.out.println("<br/>isVaildUrl("+url+")=" + isVaildUrl(url)+","+replaceUrl(url));		
		url="...//cc*bb.cc";
		System.out.println("<br/>isVaildUrl("+url+")=" + isVaildUrl(url)+","+replaceUrl(url));	

	}
		 */

}