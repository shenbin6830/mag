package com.zmax.common.utils.file.localhttp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import com.zmax.common.utils.string.JsonUtilAli;


/**
 * http返回JSON解析工具
 * @author zmax
 *
 */
public class LocalJsonResponseHandler{
	/**存放各种类的map*/
	private static Map<String, ResponseHandler<?>> map = new HashMap<String, ResponseHandler<?>>();
	/**
	 * 返回解析JSON工具，传输使用的编码是iso-8859-1
	 * @param clazz 类
	 * @return ResponseHandler<T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> ResponseHandler<T> createResponseHandler(final Class<T> clazz){

		if(map.containsKey(clazz.getName())){
			return (ResponseHandler<T>)map.get(clazz.getName());
		}else{
			ResponseHandler<T> responseHandler = new ResponseHandler<T>() {
				@Override
				public T handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
	                if (status >= 200 && status < 300) {
	                    HttpEntity entity = response.getEntity();
	                    String str = EntityUtils.toString(entity);
	                    return JsonUtilAli.parseObject(new String(str.getBytes("iso-8859-1"),"utf-8"), clazz); 
	                } else {
	                    throw new ClientProtocolException("Unexpected response status: " + status);
	                }
				}
			};
			map.put(clazz.getName(), responseHandler);
			return responseHandler;
		}
	}
	/**
	 * 返回解析JSON工具 传输使用的编码utf-8
	 * @param clazz 类
	 * @return ResponseHandler<T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> ResponseHandler<T> createResponseHandlerUtf8(final Class<T> clazz){

		if(map.containsKey(clazz.getName())){
			return (ResponseHandler<T>)map.get(clazz.getName());
		}else{
			ResponseHandler<T> responseHandler = new ResponseHandler<T>() {
				@Override
				public T handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
	                if (status >= 200 && status < 300) {
	                    HttpEntity entity = response.getEntity();
	                    String str = EntityUtils.toString(entity);
	                    return JsonUtilAli.parseObject(str, clazz); //都是用utf-8传输的，不用转了，否则使用new String(str.getBytes("iso-8859-1"),"utf-8")
	                } else {
	                    throw new ClientProtocolException("Unexpected response status: " + status);
	                }
				}
			};
			map.put(clazz.getName(), responseHandler);
			return responseHandler;
		}
	}
}
