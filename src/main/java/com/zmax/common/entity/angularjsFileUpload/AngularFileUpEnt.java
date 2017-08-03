package com.zmax.common.entity.angularjsFileUpload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Angular file upload 上传的对象
 * formData.append('_chunkSize', config._chunkSize);
 * formData.append('_currentChunkSize', config._end - config._start);
 * formData.append('_chunkNumber', Math.floor(config._start / config._chunkSize));
 * formData.append('_totalSize', config._file.size);
 * @author Administrator
 *
 */
public class AngularFileUpEnt {
	/**上传进来的文件名*/
	String filename="";
	/**写到硬盘的文件名*/
	String writefilename="";
	/**目前总长度*/
	Date gmtCreate=new Date();
	/**目前总长度*/
	int size=0;
	/**每块长度*/
	int _chunkSize=100000;
	/**最近块长度*/
	int _currentChunkSize=0;
	/**目前总块数，页面从0开始的*/
	int _chunkNumber=0;
	/**文件总长度*/
	int _totalSize=0;
	/**二进制列表*/
	List<byte[]> list=new ArrayList<byte[]>();
	/**用户*/
	Integer userId;
	/**
	 * 是否上传结束
	 * @return
	 */
	public boolean isFinished(){
		//size=(_chunkNumber+1)*_chunkSize+_currentChunkSize;
		return _totalSize==size;
	}
	
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public int get_chunkSize() {
		return _chunkSize;
	}
	public void set_chunkSize(int _chunkSize) {
		this._chunkSize = _chunkSize;
	}
	public int get_currentChunkSize() {
		return _currentChunkSize;
	}
	public void set_currentChunkSize(int _currentChunkSize) {
		this._currentChunkSize = _currentChunkSize;
	}
	public int get_chunkNumber() {
		return _chunkNumber;
	}
	public void set_chunkNumber(int _chunkNumber) {
		this._chunkNumber = _chunkNumber;
	}
	public int get_totalSize() {
		return _totalSize;
	}
	public void set_totalSize(int _totalSize) {
		this._totalSize = _totalSize;
	}
	public List<byte[]> getList() {
		return list;
	}
	public void setList(List<byte[]> list) {
		this.list = list;
	}


	public Date getGmtCreate() {
		return gmtCreate;
	}


	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getWritefilename() {
		return writefilename;
	}


	public void setWritefilename(String writefilename) {
		this.writefilename = writefilename;
	}

	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "AngularFileUpEnt [filename=" + filename + ", writefilename="
				+ writefilename + ", gmtCreate=" + gmtCreate + ", size=" + size
				+ ", _chunkSize=" + _chunkSize + ", _currentChunkSize="
				+ _currentChunkSize + ", _chunkNumber=" + _chunkNumber
				+ ", _totalSize=" + _totalSize+ ", userId=" + userId + ", list=" + list + "]";
	}


	
}
