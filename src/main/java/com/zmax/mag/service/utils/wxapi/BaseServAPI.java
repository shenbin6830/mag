package com.zmax.mag.service.utils.wxapi;

import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import com.zmax.common.utils.file.localhttp.LocalHttpClient;
import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wx.*;


/**
 * 基础接口
 * 获取access_token
 * 获取微信服务器IP地址。
 * @author zmax
 *
 */
public class BaseServAPI extends BaseAPI{

	/**
	 * 获取access_token
	 * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token。
	 * 开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。
	 * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
	 * http请求方式: GET
	 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
	 * @param appid 第三方用户唯一凭证
	 * @param secret 第三方用户唯一凭证密钥，即appsecret
	 * @return 正常情况下，微信会返回下述JSON数据包给公众号： 
	 * {"access_token":"ACCESS_TOKEN","expires_in":7200}
	 * 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:
	 * {"errcode":40013,"errmsg":"invalid appid"}
	 * 返回null就是错误了
	 */
	public static AccessToken token(String appid,String secret){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/cgi-bin/token")
				.addParameter("grant_type","client_credential")
				.addParameter("appid", appid)
				.addParameter("secret", secret)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,AccessToken.class);
	}
	/**
	 * 获取微信服务器IP地址
	 * 如果公众号基于安全等考虑，需要获知微信服务器的IP地址列表，以便进行相关限制，可以通过该接口获得微信服务器IP地址列表。 
	 * http请求方式: GET
	 * https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN
	 * 正常情况下，微信会返回下述JSON数据包给公众号： {"ip_list":["127.0.0.1","127.0.0.1"]}
	 * 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）: 
	 * {"errcode":40013,"errmsg":"invalid appid"}
	 * @param access_token  公众号的access_token
	 * @return
	 */
	public static Getcallbackip getcallbackip(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/getcallbackip")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Getcallbackip.class);
	}

}
