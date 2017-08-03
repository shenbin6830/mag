package com.zmax.mag.service.utils.wxapi;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.zmax.common.utils.file.localhttp.LocalHttpClient;
import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wx.*;


/**
 * 帐号管理
 * 生成带参数的二维码
 * 目前有2种类型的二维码： 
 * 1、临时二维码，是有过期时间的，最长可以设置为在二维码生成后的7天（即604800秒）后过期，但能够生成较多数量。
 * 临时二维码主要用于帐号绑定等不要求二维码永久保存的业务场景
 * 2、永久二维码，是无过期时间的，但数量较少（目前为最多10万个）。永久二维码主要用于适用于帐号绑定、用户来源统计等场景。
 * 
 * ==>临时与永久的参数区别是 "action_name": QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
 * 
 * 用户扫描带场景值二维码时，可能推送以下两种事件： 
 * 1. 如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。 
 * 2. 如果用户已经关注公众号，在用户扫描后会自动进入会话，微信也会将带场景值扫描事件推送给开发者。 
 * 获取带参数的二维码的过程包括两步，首先创建二维码ticket，然后凭借ticket到指定URL换取二维码。 
 * 
 * 长链接转短链接接口
 * @author zmax
 *
 */
public class QrcodeServAPI extends BaseAPI{
	/**
	 * 每次创建二维码ticket需要提供一个开发者自行设定的参数（scene_id）
	 * http请求方式: POST
	 * URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
	 * @param access_token
	 * @param waQrcodeticketParam waQrcodeticketParam.actionname QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
	 * @return
	 */
	public static WaQrcodeticketRet qrcodeticket(String access_token,WaQrcodeticketParam waQrcodeticketParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/qrcode/create")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(waQrcodeticketParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaQrcodeticketRet.class);
	}
	
	/**
	 * 根据ticket获取二维码图片
	 * HTTP GET请求（请使用https协议）
	 * https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
	 *
	 *
	 * 提醒：TICKET记得进行UrlEncode
	 * 
	 * 
	 * @param ticket
	 * @return byte[] 图片本体
	 */
	public static byte[] qrcodePic(String ticket){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(MP_URI + "/cgi-bin/showqrcode")
				.addParameter("ticket", ticket)
				.build();
		HttpResponse httpResponse = LocalHttpClient.execute(httpUriRequest);
		try {
			return EntityUtils.toByteArray(httpResponse.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 将一条长链接转成短链接。
	 * 主要使用场景： 开发者用于生成二维码的原链接（商品、支付二维码等）太长导致扫码速度和成功率下降，
	 * 将原长链接通过此接口转成短链接再生成二维码将大大提升扫码速度和成功率。 
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN
	 * @param access_token
	 * @param WaQrcodechangeParam
	 * @return
	 */
	public static WaQrcodechangeRet shorturl(String access_token,WaQrcodechangeParam waQrcodechangeParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(MP_URI + "/cgi-bin/shorturl")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaQrcodechangeRet.class);
	}
}
