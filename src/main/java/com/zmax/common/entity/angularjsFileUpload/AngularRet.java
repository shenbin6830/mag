package com.zmax.common.entity.angularjsFileUpload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * {"result": [{"fieldName":"_chunkSize","value":"100000"},{"fieldName":"_currentChunkSize","value":"100000"
 * },{"fieldName":"_chunkNumber","value":"0"},{"fieldName":"_totalSize","value":"127523"},{"fieldName":"file"
 * ,"name":"56f0dee26fea0.gif","size":"100000"}]}
 * 
 * Object { fieldName="_totalSize",  value="127523"}
 * Object { fieldName="file",  name="56f0dee26fea0.gif",  size="27523"}
 * 
 * @author Administrator
 *
 */
public class AngularRet {
	List<ArFieldNameValue> result=new ArrayList<>();
	
	
	public List<ArFieldNameValue> getResult() {
		return result;
	}


	public void setResult(List<ArFieldNameValue> result) {
		this.result = result;
	}


//	public static void main(String[] args) {
//		AngularRet ar=new AngularRet();
//		ar.getResult().add(new ArFieldNameValue("_chunkSize", "100000"));
//		ar.getResult().add(new ArFieldNameValue("_currentChunkSize", "100000"));
//		ar.getResult().add(new ArFieldNameValue("file", "56f0dee26fea0.gif","100000"));
//		
//		System.out.println("post:" + JSON.toJSONString(ar));
//		//
//		ar=new AngularRet();
//		ar.getResult().add(new ArFieldNameValue("0"));
//		System.out.println("option:" + JSON.toJSONString(ar));
//		//
//		System.out.println("get:" + JSON.toJSONString(new ArFieldNameValue("0")));
//	}
	
}
