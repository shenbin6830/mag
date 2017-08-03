package com.zmax.mag.service.utils.wxapi;


import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import com.zmax.common.utils.file.localhttp.LocalHttpClient;
import com.zmax.mag.domain.bean.wx.*;


/**
 * 高级群发接口
 * <br>1 上传图文消息素材【订阅号与服务号认证后均可用】
 * <br>2 根据分组进行群发【订阅号与服务号认证后均可用】
 * <br>3 根据OpenID列表群发【订阅号不可用，服务号认证后可用】
 * <br>4 删除群发【订阅号与服务号认证后均可用】
 * <br>5 预览接口【订阅号与服务号认证后均可用】
 * <br>6 查询群发消息发送状态【订阅号与服务号认证后均可用】
 * <br>7 事件推送群发结果
 * @author zmax
 *
 */
public class AdvMassServAPI extends BaseAPI{

	
	/**
	 * 上传图文消息素材【订阅号与服务号认证后均可用】 
	 * 接口调用请求说明
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN
	 * @param access_token
	 * @param waAdvmassArticleParam
	 * @return
	 */
	public static WaAdvmassArticleRet mediaUploadnews(String access_token,WaAdvmassArticleParam waAdvmassArticleParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/media/uploadnews")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(waAdvmassArticleParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaAdvmassArticleRet.class);
	}

}
