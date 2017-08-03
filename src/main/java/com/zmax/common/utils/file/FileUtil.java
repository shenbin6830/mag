package com.zmax.common.utils.file;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
	
	private static final String DEFAULT_ENCODING = "GB18030";
	
	public FileUtil() {
	}
	/**
	 * 拷文件夹
	 * @param sourceDirName
	 * @param destinationDirName
	 */
	public static void copyDirectory(String sourceDirName,
			String destinationDirName) {
		copyDirectory(new File(sourceDirName), new File(destinationDirName));
	}
	/**
	 * 拷文件夹
	 * @param source
	 * @param destination
	 */
	public static void copyDirectory(File source, File destination) {
		if (source.exists() && source.isDirectory()) {
			if (!destination.exists())
				destination.mkdirs();
			File fileArray[] = source.listFiles();
			for (int i = 0; i < fileArray.length; i++)
				if (fileArray[i].isDirectory())
					copyDirectory(fileArray[i], new File(destination.getPath()
							+ File.separator + fileArray[i].getName()));
				else
					copyFile(fileArray[i], new File(destination.getPath()
							+ File.separator + fileArray[i].getName()));

		}
	}
	/**
	 * 复制文件
	 * @param sourceFileName
	 * @param destinationFileName
	 */
	public static void copyFile(String sourceFileName,
			String destinationFileName) {
		copyFile(new File(sourceFileName), new File(destinationFileName));
	}
	/**
	 * 复制文件
	 * @param source
	 * @param destination
	 */
	public static void copyFile(File source, File destination) {
		if (!source.exists())
			return;
		if (destination.getParentFile() != null
				&& !destination.getParentFile().exists())
			destination.getParentFile().mkdirs();
		try {
			FileInputStream fis = new FileInputStream(source);
			FileOutputStream fos = new FileOutputStream(destination);
			byte buffer[] = new byte[4096];
			for (int n = 0; (n = fis.read(buffer)) != -1;)
				fos.write(buffer, 0, n);

			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除目录
	 * @param directory
	 */
	public static void deltree(String directory) {
		deltree(new File(directory));
	}
	/**
	 * 删除目录
	 * @param directory
	 */
	public static void deltree(File directory) {
		if (directory.exists() && directory.isDirectory()) {
			File fileArray[] = directory.listFiles();
			for (int i = 0; i < fileArray.length; i++)
				if (fileArray[i].isDirectory())
					deltree(fileArray[i]);
				else
					fileArray[i].delete();

			directory.delete();
		}
		if(directory.exists() && directory.isFile()){
			directory.delete();
			 if(directory.getParentFile().isDirectory()){
				 if(directory.getParentFile().listFiles().length==0)
					 directory.getParentFile().delete();
			 }
		}
	}
	/**
	 * 列出文件夹下所有文件，不包括文件夹
	 * @param list
	 * @param f
	 */
	public static void listTree(List<File> list,File f){
        if(f!=null){
            if(f.isDirectory()){
            	list.add(f);
                File[] fileArray=f.listFiles();
                if(fileArray!=null){
                    for (int i = 0; i < fileArray.length; i++) {
                        //递归调用
                    	listTree(list,fileArray[i]);
                    }
                }
            }
            else{
            	list.add(f);
                //System.out.println(f);
            }
        }
    }


	public static String getPath(String fullFileName) {
		int pos = fullFileName.lastIndexOf("/");
		if (pos == -1)
			pos = fullFileName.lastIndexOf("\\");
		String shortFileName = fullFileName.substring(0, pos);
		return shortFileName;
	}

	public static String getShortFileName(String fullFileName) {
		int pos = fullFileName.lastIndexOf("/");
		if (pos == -1)
			pos = fullFileName.lastIndexOf("\\");
		String shortFileName = fullFileName.substring(pos + 1, fullFileName
				.length());
		return shortFileName;
	}

	public static boolean exists(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}
	/**
	 * 文件夹中文件变成List<String>
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String[] listDirs(String fileName) throws IOException {
		return listDirs(new File(fileName));
	}
	/**
	 * 文件夹中文件变成List<String>
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String[] listDirs(File file) throws IOException {
		List dirs = new ArrayList();
		File fileArray[] = file.listFiles();
		for (int i = 0; i < fileArray.length; i++)
			if (fileArray[i].isDirectory())
				dirs.add(fileArray[i].getName());

		return (String[]) dirs.toArray(new String[0]);
	}
	/**
	 * 文件夹中文件变成List<String>
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String[] listFiles(String fileName) throws IOException {
		return listFiles(new File(fileName));
	}
	/**
	 * 文件夹中文件变成List<String>
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String[] listFiles(File file) throws IOException {
		List files = new ArrayList();
		File fileArray[] = file.listFiles();
		for (int i = 0; i < fileArray.length; i++)
			if (fileArray[i].isFile())
				files.add(fileArray[i].getName());

		return (String[]) files.toArray(new String[0]);
	}
	/**
	 * 建目录
	 * @param pathName
	 */
	public static void mkdirs(String pathName) {
		File file = new File(pathName);
		file.mkdirs();
	}
	/**
	 * 读文件为文本
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String read(String fileName) throws IOException {
		return read(new File(fileName));
	}
	/**
	 * 读文件为文本
	 * @param file
	 * @return String
	 * @throws IOException
	 */
	public static String read(File file) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), DEFAULT_ENCODING));
		StringBuffer sb = new StringBuffer();
		for (String line = null; (line = br.readLine()) != null;)
			sb.append(line).append("\r\n");

		br.close();
		return sb.toString().trim();
	}
	/**
	 * 读文件为文本
	 * @param file
	 * @param encoding
	 * @return String
	 * @throws IOException
	 */
	public static String read(File file, String encoding) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		StringBuffer sb = new StringBuffer();
		for (String line = null; (line = br.readLine()) != null;)
			sb.append(line).append("\r\n");

		br.close();
		return sb.toString().trim();
	}
	/**
	 * 读文件为文本
	 * @param file
	 * @param encoding
	 * @return String
	 * @throws IOException
	 */
	public static String read(String file, String encoding) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		StringBuffer sb = new StringBuffer();
		for (String line = null; (line = br.readLine()) != null;)
			sb.append(line).append("\r\n");

		br.close();
		return sb.toString().trim();
	}
	/**
	 * 文件变List<String>
	 * @param reader
	 * @return
	 */
	public static List<String> toList(Reader reader) {
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(reader);
			StringBuffer sb = new StringBuffer();
			for (String line = null; (line = br.readLine()) != null;)
				list.add(line);

			br.close();
		} catch (IOException ioexception) {
		}
		return list;
	}
	/**
	 * 文件变List<String>
	 * @param fileName 文件名绝对路径
	 * @return
	 */
	public static List<String> toList(String fileName) {
		try {
			return toList(((Reader) (new FileReader(fileName))));
		} catch (IOException ioe) {
			return new ArrayList<String>();
		}
	}
	/**
	 * 参数配置文件转Properties
	 * @param fis
	 * @return
	 */
	public static Properties toProperties(FileInputStream fis) {
		Properties props = new Properties();
		try {
			props.load(fis);
		} catch (IOException ioexception) {
		}
		return props;
	}
	/**
	 * 参数配置文件转Properties
	 * @param fileName
	 * @return Properties
	 */
	public static Properties toProperties(String fileName) {
		try {
			return toProperties(new FileInputStream(fileName));
		} catch (IOException ioe) {
			return new Properties();
		}
	}
	/**
	 * 写文本到文件
	 * @param file
	 * @param s
	 * @throws IOException
	 */
	public static void write(File file, String s) throws IOException {
		if (file.getParent() != null)
			mkdirs(file.getParent());
		FileOutputStream outputStream = new FileOutputStream(file);
		OutputStreamWriter bw = new OutputStreamWriter(outputStream, "gb2312");
		bw.flush();
		bw.write(s);
		bw.close();
		outputStream.close();
	}
	/**
	 * 写文本到文件
	 * @param file
	 * @param s
	 * @param encoding
	 * @throws IOException
	 */
	public static void write(File file, String s, String encoding)
			throws IOException {
		if (file.getParent() != null)
			mkdirs(file.getParent());
		OutputStreamWriter bw = new OutputStreamWriter(new FileOutputStream(file), encoding);
		bw.flush();
		bw.write(s);
		bw.close();
	}
	/**
	 * 写文本到文件
	 * @param fileName
	 * @param s
	 * @throws IOException
	 */
	public static void write(String fileName, String s) throws IOException {
		write(new File(fileName), s);
	}
	/**
	 * 写文本到文件
	 * @param fileName
	 * @param s
	 * @param encoding
	 * @throws IOException
	 */
	public static void write(String fileName, String s, String encoding)
			throws IOException {
		write(new File(fileName), s, encoding);
	}
	/**
	 * 获取一个当前时间的字符串2014-09-08
	 * @return
	 */
	public static String getDirectoryNameByDate() {
		Date nowDate = new Date();
		int year = nowDate.getYear();
		int month = nowDate.getMonth();
		int day = nowDate.getDay();
		return year + "-" + month + "-" + day;
	}
	
	/**
	 * 压缩文件
	 * @param inFile 文件或文件夹都可以
	 * @param zos
	 * @param dir 通常为空
	 * @throws IOException
	 */
	public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {
		if(dir==null)
			dir="";
		
		if (inFile.isDirectory()) {
			File[] files = inFile.listFiles();
			for (File file:files)
				zipFile(file, zos, dir + "/" + inFile.getName());
		} else {
			String entryName = null;
			if (!"".equals(dir))
				entryName = dir + "/" + inFile.getName();
			else
				entryName = inFile.getName();
			ZipEntry entry = new ZipEntry(entryName);
			zos.putNextEntry(entry);
			InputStream is = new FileInputStream(inFile);
			int len = 0;
			while ((len = is.read()) != -1)
				zos.write(len);
			is.close();
		}

	}
	
}
