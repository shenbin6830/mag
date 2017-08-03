package com.zmax.common.utils.file.localhttp;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
/**
 * LocalHttpClient连接工厂
 * @author zmax
 *
 */
public class LocalHttpClientFactory {
	/**
	 * 创建连接
	 * @return
	 */
	public static HttpClient createHttpClient() {
		return HttpClientBuilder.create().build();
	}
	/**
	 * 创建连接
	 * @param maxTotal 最大连接总数
	 * @param maxPerRoute 同一地址最大连接数
	 * @return
	 */
	public static HttpClient createHttpClient(int maxTotal,int maxPerRoute) {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
		return HttpClientBuilder.create()
				.setConnectionManager(poolingHttpClientConnectionManager)
				.build();

	}
	/**
	 * 创建https连接
	 * @return
	 */
	public static HttpClient createHttpsClient() {
		try {
			SSLContext sslContext = SSLContexts.custom().useSSL().build();
			SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			return HttpClientBuilder.create().setSSLSocketFactory(sf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 创建https连接
	 * @param maxTotal 最大连接总数
	 * @param maxPerRoute 同一地址最大连接数
	 * @return
	 */
	public static HttpClient createHttpsClient(int maxTotal,int maxPerRoute) {
		try {
			SSLContext sslContext = SSLContexts.custom().useSSL().build();
			SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
			poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
			poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
			return HttpClientBuilder.create()
					.setConnectionManager(poolingHttpClientConnectionManager)
					.setSSLSocketFactory(sf)
					.build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Key store 类型HttpClient
	 * @param keystore
	 * @param keyPassword
	 * @param supportedProtocols
	 * @param maxTotal
	 * @param maxPerRoute
	 * @return
	 */
	public static HttpClient createKeyMaterialHttpClient(KeyStore keystore,String keyPassword,String[] supportedProtocols,int maxTotal,int maxPerRoute) {
		try {

			SSLContext sslContext = SSLContexts.custom().useSSL().loadKeyMaterial(keystore, keyPassword.toCharArray()).build();
			SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext,supportedProtocols,
					null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
			poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
			poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
			return HttpClientBuilder.create()
					.setConnectionManager(poolingHttpClientConnectionManager)
					.setSSLSocketFactory(sf)
					.build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return null;
	}

}
