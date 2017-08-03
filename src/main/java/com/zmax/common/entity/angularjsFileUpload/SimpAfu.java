package com.zmax.common.entity.angularjsFileUpload;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Angular file upload 上传的对象简单版
 * @author Administrator
 *
 */
public class SimpAfu {
	/**上传的文件名*/
	String orgname="";
	/**写到硬盘的文件名*/
	String writefilename="";
	/**二进制列表*/
	byte[] data=null;
	/**用户*/
	Integer userId;
	
	/**写到硬盘的文件名完整带路径*/
	String filenamefulldisk="";
	/**url文件名*/
	String filenameurl="";
	
	/**备用字段1*/
	Object obj1=null;
	/**备用字段2*/
	Object obj2=null;
	
	public SimpAfu(){}
	
	
	/**
	 * 构造
	 * @param orgname 原文件名
	 * @param writefilename 存文件名
	 * @param data 数据
	 * @param userId 用户
	 */
	public SimpAfu(String orgname, String writefilename, byte[] data,
			Integer userId) {
		super();
		this.orgname = orgname;
		this.writefilename = writefilename;
		this.data = data;
		this.userId = userId;
	}



	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getWritefilename() {
		return writefilename;
	}
	public void setWritefilename(String writefilename) {
		this.writefilename = writefilename;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFilenamefulldisk() {
		return filenamefulldisk;
	}

	public void setFilenamefulldisk(String filenamefulldisk) {
		this.filenamefulldisk = filenamefulldisk;
	}

	public String getFilenameurl() {
		return filenameurl;
	}

	public void setFilenameurl(String filenameurl) {
		this.filenameurl = filenameurl;
	}

	
	public Object getObj1() {
		return obj1;
	}


	public void setObj1(Object obj1) {
		this.obj1 = obj1;
	}


	public Object getObj2() {
		return obj2;
	}


	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}


	@Override
	public String toString() {
		return "SimpAfu [writefilename=" + writefilename + ", data="
				+ ((data!=null)?data.length:0) + ", userId=" + userId
				+ ", filenamefulldisk=" + filenamefulldisk + ", orgname="
				+ orgname+ ", filenameurl="
						+ filenameurl + "]";
	}


	
	
	
}
