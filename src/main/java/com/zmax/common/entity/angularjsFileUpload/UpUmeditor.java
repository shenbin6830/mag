package com.zmax.common.entity.angularjsFileUpload;
/**
 * Umeditor Angularjs版图片上传的返回对象，返回的是List<UpUmeditor>
 * @author zmax
 *
 */
public class UpUmeditor {
	//原文件名
    String originalName;
    //现在文件名
    String name;
    //下载URL地址
    String url;
    //大小
    String size;
    //文件扩展名
    String type;
    //状态
    String state;
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
    
}
