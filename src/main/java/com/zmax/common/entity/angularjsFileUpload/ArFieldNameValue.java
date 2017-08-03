package com.zmax.common.entity.angularjsFileUpload;
/**
 * {"fieldName":"_chunkSize","value":"100000"}
 * @author Administrator
 *
 */
public class ArFieldNameValue {
	String fieldName;
	String value;
	String name;
	String size;
	public ArFieldNameValue(){};
	public ArFieldNameValue(String fieldName, String value) {
		super();
		this.fieldName = fieldName;
		this.value = value;
	}
	public ArFieldNameValue(String fieldName, String name, String size) {
		super();
		this.fieldName = fieldName;
		this.name = name;
		this.size = size;
	}
	public ArFieldNameValue(String size) {
		super();
		this.size = size;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
}
