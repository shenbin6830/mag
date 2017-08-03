package com.zmax.common.utils.file.localhttp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;

public class LocalHttpClient {
	/**httpclient*/
	protected static HttpClient httpClient = LocalHttpClientFactory.createHttpClient(100,10);
	/**证书库*/
	private static Map<String,HttpClient> httpClient_mchKeyStore = new HashMap<String, HttpClient>();
	/**
	 * 初始化一个最大连接数的httpclient
	 * @param maxTotal 最大连接总数
	 * @param maxPerRoute 同一地址最大连接数
	 */
	public static void init(int maxTotal,int maxPerRoute){
		httpClient = LocalHttpClientFactory.createHttpClient(maxTotal,maxPerRoute);
	}

	/**
	 * 初始化   MCH HttpClient KeyStore
	 * @param keyStoreName  keyStore 名称
	 * @param keyStoreFilePath 私钥文件路径
	 * @param mch_id
	 * @param maxTotal
	 * @param maxPerRoute
	 */
	public static void initMchKeyStore(String keyStoreName,String keyStoreFilePath,String mch_id,int maxTotal,int maxPerRoute){
		try {
			KeyStore keyStore = KeyStore.getInstance(keyStoreName);
			 FileInputStream instream = new FileInputStream(new File(keyStoreFilePath));
			 keyStore.load(instream,mch_id.toCharArray());
			 instream.close();
			 HttpClient httpClient = LocalHttpClientFactory.createKeyMaterialHttpClient(keyStore, mch_id, new String[]{"TLSv1"}, maxTotal, maxPerRoute);
			 httpClient_mchKeyStore.put(mch_id, httpClient);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送数据，不管返回
	 * @param request
	 * @return
	 */
	public static HttpResponse execute(HttpUriRequest request){
		try {
			return httpClient.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 发送数据，返回文本
	 * @param request
	 * @return
	 */
	public static String executestr(HttpUriRequest request){
        try {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();  
			//执行请求并获取结果  
			String responseBody = httpClient.execute(request, responseHandler); 
			return responseBody;
		} catch (IOException e) {
			e.printStackTrace();
			//logger.error(e);
		}
        return null;
	}
	/**
	 * 发送数据，并获取返回 ResponseHandler
	 * @param request
	 * @param responseHandler
	 * @return 
	 */
	public static <T> T execute(HttpUriRequest request,ResponseHandler<T> responseHandler){
		try {
			return httpClient.execute(request, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送数据，并获取返回，返回值为JSON，解析成对象
	 * @param request HttpUriRequest 包含了URL和参数
	 * @param clazz 要解析成为的对象
	 * @return
	 */
	public static <T> T executeJsonResult(HttpUriRequest request,Class<T> clazz){
		return execute(request,LocalJsonResponseHandler.createResponseHandler(clazz));
	}
	/**
	 * Utf8 发送数据，并获取返回，返回值为JSON，解析成对象
	 * @param request HttpUriRequest 包含了URL和参数
	 * @param clazz 要解析成为的对象
	 * @return
	 */
	public static <T> T executeJsonResultUtf8(HttpUriRequest request,Class<T> clazz){
		return execute(request,LocalJsonResponseHandler.createResponseHandlerUtf8(clazz));
	}
	/**
	 * 发送数据，并获取返回，返回值为XML，解析成对象
	 * @param request HttpUriRequest 包含了URL和参数
	 * @param clazz 要解析成为的对象
	 * @return
	 */
	public static <T> T executeXmlResult(HttpUriRequest request,Class<T> clazz){
		return execute(request,LocalXmlResponseHandler.createResponseHandler(clazz));
	}
	/**
	 * Utf8 发送数据，并获取返回，返回值为XML，解析成对象
	 * @param request HttpUriRequest 包含了URL和参数
	 * @param clazz 要解析成为的对象
	 * @return
	 */
	public static <T> T executeXmlResultUtf8(HttpUriRequest request,Class<T> clazz){
		return execute(request,LocalXmlResponseHandler.createResponseHandlerUtf8(clazz));
	}
	/**
	 * MCH keyStore 请求 数据返回自动XML对象解析
	 * @param mch_id
	 * @param request
	 * @param clazz
	 * @return
	 */
	public static <T> T keyStoreExecuteXmlResult(String mch_id,HttpUriRequest request,Class<T> clazz){
		try {
			return httpClient_mchKeyStore.get(mch_id).execute(request,LocalXmlResponseHandler.createResponseHandler(clazz));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
