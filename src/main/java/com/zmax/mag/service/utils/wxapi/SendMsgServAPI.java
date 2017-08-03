package com.zmax.mag.service.utils.wxapi;

import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import com.alibaba.fastjson.JSONArray;
import com.zmax.common.utils.file.localhttp.LocalHttpClient;
import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wx.*;



public class SendMsgServAPI extends BaseAPI{

//	
//	/**
//	 * 客服帐号管理
//	 * 开发者在根据开发文档的要求完成开发后，使用6.0.2版及以上版本的微信用户在与公众号进行客服沟通，公众号使用不同的客服账号进行回复后，用户可以看到对应的客服头像和昵称。
//	 * 请注意，必须先在公众平台官网为公众号设置微信号后才能使用该能力。
//	 */
//	/**
//	 * 添加客服帐号
//	 * 开发者可以通过本接口为公众号添加客服账号，每个公众号最多添加10个客服账号。该接口调用请求如下：
//	 * http请求方式: POST
//	 * https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN
//	 * POST数据示例如下：{"kf_account" : "test1@test","nickname" : "客服1","password" : "pswmd5",}
//	 * 返回说明（正确时的JSON返回结果）：{"errcode" : 0,"errmsg" : "ok",}
//	 * @param access_token
//	 * @return
//	 */
//	public static Getcallbackip kfaccountAdd(String access_token){
//		HttpUriRequest httpUriRequest = RequestBuilder.post()
//				.setUri(BASE_URI + "/customservice/kfaccount/add")
//				.addParameter("access_token", access_token)
//				.build();
//		return LocalHttpClient.executeJsonResult(httpUriRequest,Getcallbackip.class);
//	}
//	
//	/**
//	 * 修改客服帐号
//	 * 开发者可以通过本接口为公众号修改客服账号。该接口调用请求如下：
//	 * http请求方式: POST
//	 * https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN
//	 * POST数据示例如下：{"kf_account" : "test1@test","nickname" : "客服1","password" : "pswmd5",}
//	 * 返回说明（正确时的JSON返回结果）：{"errcode" : 0,"errmsg" : "ok",}
//	 * @param access_token
//	 * @return
//	 */
//	public static Getcallbackip kfaccountUpdate(String access_token){
//		HttpUriRequest httpUriRequest = RequestBuilder.post()
//				.setUri(BASE_URI + "/customservice/kfaccount/update")
//				.addParameter("access_token", access_token)
//				.build();
//		return LocalHttpClient.executeJsonResult(httpUriRequest,Getcallbackip.class);
//	}
//	
//	/**
//	 * 删除客服帐号
//	 * 开发者可以通过该接口为公众号删除客服帐号。该接口调用请求如下：
//	 * http请求方式: GET
//	 * https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN
//	 * POST数据示例如下：{"kf_account" : "test1@test","nickname" : "客服1","password" : "pswmd5",}
//	 * 返回说明（正确时的JSON返回结果）：{"errcode" : 0,"errmsg" : "ok",}
//	 * @param access_token
//	 * @return
//	 */
//	public static Getcallbackip kfaccountDel(String access_token){
//		HttpUriRequest httpUriRequest = RequestBuilder.post()
//				.setUri(BASE_URI + "/customservice/kfaccount/del")
//				.addParameter("access_token", access_token)
//				.build();
//		return LocalHttpClient.executeJsonResult(httpUriRequest,Getcallbackip.class);
//	}
//	
//	/**
//	 * 设置客服帐号的头像
//	 * 开发者可调用本接口来上传图片作为客服人员的头像，头像图片文件必须是jpg格式，推荐使用640*640大小的图片以达到最佳效果。该接口调用请求如下：
//	 * http请求方式: POST/FORM
//	 * http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT
//	 * 调用示例：使用curl命令，用FORM表单方式上传一个多媒体文件，curl命令的具体用法请自行了解
//	 * 返回说明（正确时的JSON返回结果）：{"errcode" : 0,"errmsg" : "ok",}
//	 * @param access_token
//	 * @return
//	 */
//	public static Getcallbackip kfaccountUploadheadimg(String access_token,String KFACCOUNT){
//		HttpUriRequest httpUriRequest = RequestBuilder.post()
//				.setUri(BASE_URI + "/customservice/kfaccount/uploadheadimg")
//				.addParameter("access_token", access_token)
//				.addParameter("kf_account", KFACCOUNT)
//				.build();
//		return LocalHttpClient.executeJsonResult(httpUriRequest,Getcallbackip.class);
//	}
//	
//	/**
//	 * 获取所有客服账号
//	 * 开发者通过本接口，获取公众号中所设置的客服基本信息，包括客服工号、客服昵称、客服登录账号。
//	 * http请求方式: GET
//	 * https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN
//	 * 返回说明（正确时的JSON返回结果）：{"kf_list": [{"kf_account": "test1@test", "kf_nick": "ntest1", "kf_id": "1001""kf_headimgurl": " http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0"}, {"kf_account": "test2@test", "kf_nick": "ntest2", "kf_id": "1002""kf_headimgurl": " http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw /0"}]}
//	 * @param access_token
//	 * @return
//	 */
//	public static Getcallbackip getkflist(String access_token){
//		HttpUriRequest httpUriRequest = RequestBuilder.post()
//				.setUri(BASE_URI + "/customservice/getkflist")
//				.addParameter("access_token", access_token)
//				.build();
//		return LocalHttpClient.executeJsonResult(httpUriRequest,Getcallbackip.class);
//	}
	
//	/**
//	 * 客服接口-发消息
//	 * 接口调用请求说明
//	 * http请求方式: POST
//	 * https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
//	 * 各消息类型所需的JSON数据包如下：
//	 * 发送文本消息{"touser":"OPENID","msgtype":"text","text":{"content":"Hello World"}}
//	 * 发送图片消息{"touser":"OPENID","msgtype":"image","image":{"media_id":"MEDIA_ID"}}
//	 * 发送语音消息{"touser":"OPENID","msgtype":"voice","voice":{"media_id":"MEDIA_ID"}}
//	 * 发送视频消息{"touser":"OPENID","msgtype":"video","video":{"media_id":"MEDIA_ID","thumb_media_id":"MEDIA_ID","title":"TITLE","description":"DESCRIPTION"}}
//	 * 发送音乐消息{"touser":"OPENID","msgtype":"music","music":{"title":"MUSIC_TITLE","description":"MUSIC_DESCRIPTION","musicurl":"MUSIC_URL","hqmusicurl":"HQ_MUSIC_URL","thumb_media_id":"THUMB_MEDIA_ID" }}
//	 * 发送图文消息 图文消息条数限制在10条以内，注意，如果图文数超过10，则将会无响应。
//	 * {"touser":"OPENID","msgtype":"news","news":{"articles": [{"title":"Happy Day","description":"Is Really A Happy Day","url":"URL","picurl":"PIC_URL"},{"title":"Happy Day","description":"Is Really A Happy Day","url":"URL","picurl":"PIC_URL"}]}}
//	 * 请注意，如果需要以某个客服帐号来发消息（在微信6.0.2及以上版本中显示自定义头像），则需在JSON数据包的后半部分加入customservice参数，例如发送文本消息则改为：
//	 * {"touser":"OPENID","msgtype":"text","text":{"content":"Hello World"},"customservice":{"kf_account": "test1@kftest"}}
//	 * @param access_token
//	 * @return
//	 */
//	public static Getcallbackip messageCustomSend(String access_token){
//		HttpUriRequest httpUriRequest = RequestBuilder.post()
//				.setUri(BASE_URI + "/cgi-bin/message/custom/send")
//				.addParameter("access_token", access_token)
//				.build();
//		return LocalHttpClient.executeJsonResult(httpUriRequest,Getcallbackip.class);
//	}
	
	/**
	 * 在公众平台网站上，为订阅号提供了每天一条的群发权限，为服务号提供每月（自然月）4条的群发权限。而对于某些具备开发能力的公众号运营者，可以通过高级群发接口，实现更灵活的群发能力。
	 * 请注意：
	 * 1、对于认证订阅号，群发接口每天可成功调用1次，此次群发可选择发送给全部用户或某个分组；
	 * 2、对于认证服务号虽然开发者使用高级群发接口的每日调用限制为100次，但是用户每月只能接收4条，无论在公众平台网站上，还是使用接口群发，用户每月只能接收4条群发消息，多于4条的群发将对该用户发送失败；
	 * 3、具备微信支付权限的公众号，在使用群发接口上传、群发图文消息类型时，可使用<a>标签加入外链；
	 * 4、开发者可以使用预览接口校对消息样式和排版，通过预览接口可发送编辑好的消息给指定用户校验效果。
	 * 关于群发时使用is_to_all为true使其进入公众号在微信客户端的历史消息列表：
	 * 1、使用is_to_all为true且成功群发，会使得此次群发进入历史消息列表。
	 * 2、为防止异常，认证订阅号在一天内，只能使用is_to_all为true进行群发一次，或者在公众平台官网群发（不管本次群发是对全体还是对某个分组）一次。以避免一天内有2条群发进入历史消息列表。
	 * 3、类似地，服务号在一个月内，使用is_to_all为true群发的次数，加上公众平台官网群发（不管本次群发是对全体还是对某个分组）的次数，最多只能是4次。
	 * 4、设置is_to_all为false时是可以多次群发的，但每个用户只会收到最多4条，且这些群发不会进入历史消息列表。
	 * 另外，请开发者注意，本接口中所有使用到media_id的地方，现在都可以使用素材管理中的永久素材media_id了。
	 */
	/**
	 * 上传图文消息素材【订阅号与服务号认证后均可用】
	 * 接口调用请求说明
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN
	 * POST数据说明
	 * POST数据示例如下：{"articles": [{"thumb_media_id":"qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p","author":"xxx","title":"Happy Day","content_source_url":"www.qq.com","content":"content","digest":"digest","show_cover_pic":"1"},{"thumb_media_id":"qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p","author":"xxx","title":"Happy Day","content_source_url":"www.qq.com","content":"content","digest":"digest","show_cover_pic":"0"}]}
	 * 返回说明
	 * 返回数据示例（正确时的JSON返回结果）：{"type":"news","media_id":"CsEf3ldqkAYJAU6EJeIkStVDSvffUJ54vqbThMgplD-VJXXof6ctX5fI6-aYyUiQ","created_at":1391857799}
	 * @param access_token
	 * @return
	 */
	public static WaAdvmassArticleRet MediaUploadnews(String access_token,WaAdvmassArticleParam waAdvmassArticleParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/media/uploadnews")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaAdvmassArticleRet.class);
	}
	
	
	/**
	 * 根据分组进行群发【订阅号与服务号认证后均可用】接口调用请求说明
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
	 * POST数据说明
	 * POST数据示例如下：
	 * 图文消息（注意图文消息的media_id需要通过上述方法来得到）：{"filter":{"is_to_all":false"group_id":"2"},"mpnews":{"media_id":"123dsdajkasd231jhksad"},"msgtype":"mpnews"}
	 * 文本：{"filter":{"is_to_all":false"group_id":"2"},"text":{"content":"CONTENT"},"msgtype":"text"}
	 * 语音（注意此处media_id需通过基础支持中的上传下载多媒体文件来得到）：{"filter":{"is_to_all":false"group_id":"2"},"voice":{"media_id":"123dsdajkasd231jhksad"},"msgtype":"voice"}
	 * 图片（注意此处media_id需通过基础支持中的上传下载多媒体文件来得到）：{"filter":{"is_to_all":false"group_id":"2"}, "image":{"media_id":"123dsdajkasd231jhksad"}, "msgtype":"image"}
	 * 视频
	 * 请注意，此处视频的media_id需通过POST请求到下述接口特别地得到： https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN 
	 * POST数据如下（此处media_id需通过基础支持中的上传下载多媒体文件来得到）：
	 * {"media_id": "rF4UdIMfYK3efUfyoddYRMU50zMiRmmt_l0kszupYh_SzrcW5Gaheq05p_lHuOTQ","title": "TITLE","description": "Description"}
	 * 返回将为{"type":"video","media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc","created_at":1398848981}
	 * 然后，POST下述数据（将media_id改为上一步中得到的media_id），
	 * 即可进行发送{"filter":{"is_to_all":false"group_id":"2"},"mpvideo":{"media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc",},"msgtype":"mpvideo"}
	 * 返回说明
	 * 返回数据示例（正确时的JSON返回结果）：{"errcode":0,"errmsg":"send job submission success","msg_id":34182}
	 * @param access_token
	 * @return
	 */
	public static WaAdvmassCommonRet messageMassSendall(String access_token,String messageJson){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(jsonHeader)
				.setUri(BASE_URI + "/cgi-bin/message/mass/sendall")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(messageJson,Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaAdvmassCommonRet.class);
	}
	
	/**根据分组进行群发
	 * 发送图文消息
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendMpnewsByGroup(String access_token,WaAdvmassGroupmpnewsParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassSendall(access_token,str);
	}
	
	/**根据分组进行群发
	 * 发送文本
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendTextByGroup(String access_token,WaAdvmassGrouptextParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassSendall(access_token,str);
	}
	
	/**根据分组进行群发
	 * 发送语音
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendVoiceByGroup(String access_token,WaAdvmassGroupvoiceParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassSendall(access_token,str);
	}
	
	/**根据分组进行群发
	 * 发送图片
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendImageByGroup(String access_token,WaAdvmassGroupimageParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassSendall(access_token,str);
	}
	
	//TOOD：视频
	/**根据分组进行群发
	 * 视频第一步
	 * @param access_token
	 * @param waAdvmassGroupmpvideofParam
	 * @return
	 */
	public static WaAdvmassArticleRet mediaUploadvideo(String access_token,WaAdvmassGroupmpvideofParam waAdvmassGroupmpvideofParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(MEDIA_URI + "/cgi-bin/media/uploadvideo")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaAdvmassArticleRet.class);
	}
	
	/**根据分组进行群发
	 * 视频第二步
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendVideoByGroup(String access_token,WaAdvmassGroupmpvideosParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassSendall(access_token,str);
	}
	
	
	/**
	 * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】
	 * 接口调用请求说明
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN
	 * @param access_token
	 * @param waAdvmassDeletegetParam
	 * @return
	 */
	public static WaAdvmassCommonRet messageMassSend(String access_token,String messageJson){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/message/mass/send")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaAdvmassCommonRet.class);
	}
	
	/**根据OpenID列表群发
	 * 消息发送之图文消息
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendMpnewsByOpenid(String access_token,WaAdvmassOpenidmpnewsParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassSend(access_token,str);
	}
	
	/**根据OpenID列表群发
	 * 消息发送之文本
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendTextByOpenid(String access_token,WaAdvmassOpenidtextParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassSend(access_token,str);
	}
	
	/**根据OpenID列表群发
	 * 消息发送之语音
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendVoiceByOpenid(String access_token,WaAdvmassOpenidvoiceParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassSend(access_token,str);
	}
	
	/**根据OpenID列表群发
	 * 消息发送之语音
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendImageByOpenid(String access_token,WaAdvmassOpenidimageParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassSend(access_token,str);
	}
	
	
	//TOOD:视频特别，请参考api  第一步上面已有方法
	/**根据OpenID列表群发
	 * 消息发送之视频  第二步
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendVideoByOpenid(String access_token,WaAdvmassOpenidvideosParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassSend(access_token,str);
	}
	
	
	
	/**
	 * 删除群发【订阅号与服务号认证后均可用】
	 * 接口调用请求说明
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN
	 * POST数据说明
	 * POST数据示例如下：{"msg_id":30124}
	 * 返回说明
	 * 返回数据示例（正确时的JSON返回结果）：{"errcode":0,"errmsg":"ok"}
	 * @param access_token
	 * @return
	 */
	public static BaseResult messageMassDelete(String access_token,WaAdvmassDeletegetParam waAdvmassDeletegetParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/message/mass/delete")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	/**
	 * 预览接口【订阅号与服务号认证后均可用】
	 * 开发者可通过该接口发送消息给指定用户，在手机端查看消息的样式和排版。
	 * 接口调用请求说明
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN
	 * POST数据说明
	 * POST数据示例如下：
	 * 图文消息（其中media_id与根据分组群发中的media_id相同）：{"touser":"OPENID","mpnews":{"media_id":"123dsdajkasd231jhksad"},"msgtype":"mpnews" }
	 * 文本：{"touser":"OPENID","text":{"content":"CONTENT"},"msgtype":"text"}
	 * 语音（其中media_id与根据分组群发中的media_id相同）：{"touser":"OPENID","voice":{"media_id":"123dsdajkasd231jhksad"},"msgtype":"voice" }
	 * 图片（其中media_id与根据分组群发中的media_id相同）：{"touser":"OPENID","image":{"media_id":"123dsdajkasd231jhksad"},"msgtype":"image" }
	 * 视频（其中media_id与根据分组群发中的media_id相同）：{"touser":"OPENID","mpvideo":{  "media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc",   },"msgtype":"mpvideo" }
	 * 返回说明
	 * 返回数据示例（正确时的JSON返回结果）：{"errcode":0,"errmsg":"send job submission success","msg_id":34182}
	 * @param access_token
	 * @return
	 */
	public static WaAdvmassCommonRet messageMassPreview(String access_token,String messageJson){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(jsonHeader)
				.setUri(BASE_URI + "/cgi-bin/message/mass/preview")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(messageJson,Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaAdvmassCommonRet.class);
	}
	
	/**预览接口
	 * 消息发送之图文消息
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendMpnewsPreview(String access_token,WaAdvmassPregrompnewsParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassPreview(access_token,str);
	} 
	
	/**预览接口
	 * 消息发送之文本
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendTextPreview(String access_token,WaAdvmassPregrotextParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassPreview(access_token,str);
	} 
	
	/**预览接口
	 * 消息发送之语音
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendVoicePreview(String access_token,WaAdvmassPregrovoiceParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassPreview(access_token,str);
	} 
	
	
	/**预览接口
	 * 消息发送之图片
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendImagePreview(String access_token,WaAdvmassPregroimageParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassPreview(access_token,str);
	} 
	
	//TOOD： 视频特别，请参考api  第一步的方法前面已出现过
	/**预览接口
	 * 消息发送之视频  第二步
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static WaAdvmassCommonRet sendVideosPreview(String access_token,WaAdvmassPregrovideosParam msg){
		String str = msg.toJsonNotshow2();
		return messageMassPreview(access_token,str);
	} 
	
	
	/**
	 * 查询群发消息发送状态【订阅号与服务号认证后均可用】
	 * 接口调用请求说明
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=ACCESS_TOKEN
	 * POST数据说明
	 * POST数据示例如下：{"msg_id": "201053012"}
	 * 返回说明
	 * 返回数据示例（正确时的JSON返回结果）：{"msg_id":201053012,"msg_status":"SEND_SUCCESS"}
	 * @param access_token
	 * @return
	 */
	public static WaAdvmassGetRet messageMassGet(String access_token, WaAdvmassDeletegetParam waAdvmassDeletegetParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/message/mass/get")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaAdvmassGetRet.class);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 模板消息仅用于公众号向用户发送重要的服务通知，只能用于符合其要求的服务场景中，如信用卡刷卡通知，商品购买成功通知等。不支持广告等营销类消息以及其它所有可能对用户造成骚扰的消息。
	 * 关于使用规则，请注意：
	 * 1、所有服务号都可以在功能->添加功能插件处看到申请模板消息功能的入口，但只有认证后的服务号才可以申请模板消息的使用权限并获得该权限；
	 * 2、需要选择公众账号服务所处的2个行业，每月可更改1次所选行业；
	 * 3、在所选择行业的模板库中选用已有的模板进行调用；
	 * 4、每个账号可以同时使用15个模板。
	 * 5、当前每个模板的日调用上限为10万次【2014年11月18日将接口调用频率从默认的日1万次提升为日10万次，可在MP登录后的开发者中心查看】。
	 * 关于接口文档，请注意：
	 * 1、模板消息调用时主要需要模板ID和模板中各参数的赋值内容；
	 * 2、模板中参数内容必须以".DATA"结尾，否则视为保留字；
	 * 3、模板保留符号"{{ }}"。
	 */
	/*** 设置所属行业
	 * 设置行业可在MP中完成，每月可修改行业1次，账号仅可使用所属行业中相关的模板，为方便第三方开发者，提供通过接口调用的方式来修改账号所属行业，具体如下：
	 * 接口调用请求说明
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN
	 * POST数据说明
	 * POST数据示例如下：{"industry_id1":"1","industry_id2":"4"}
	 * @param access_token
	 * @return
	 */
	public static Getcallbackip templateApi_set_industry(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/template/api_set_industry")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Getcallbackip.class);
	}
	
	/**
	 * 获得模板ID
	 * 从行业模板库选择模板到账号后台，获得模板ID的过程可在MP中完成。为方便第三方开发者，提供通过接口调用的方式来修改账号所属行业，具体如下：
	 * 接口调用请求说明
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN
	 * POST数据说明
	 * POST数据示例如下：{"template_id_short":"TM00015"}
	 * 返回码说明在调用模板消息接口后，会返回JSON数据包。
	 * 正常时的返回JSON数据包示例：{"errcode":0,"errmsg":"ok","template_id":"Doclyl5uP7Aciu-qZ7mJNPtWkbkYnWBWVja26EGbNyk"}
	 * @param access_token
	 * @return
	 */
	public static WaTemplateAdd templateApi_add_template(String access_token,WaTemplateAdd waTemplateAdd){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/template/api_add_template")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(JSONArray.toJSONString(waTemplateAdd),Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaTemplateAdd.class);
	}
	
	

	
	/**
	 * 发送模板消息
	 * 接口调用请求说明
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
	 * POST数据说明
	 * POST数据示例如下：{"touser":"OPENID","template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY","url":"http://weixin.qq.com/download","topcolor":"#FF0000","data":{"first": {"value":"恭喜你购买成功！","color":"#173177"},"keynote1":{"value":"巧克力","color":"#173177"},"keynote2": {"value":"39.8元","color":"#173177"},"keynote3": {"value":"2014年9月16日","color":"#173177"},"remark":{"value":"欢迎再次购买！","color":"#173177"}}}
	 * 返回码说明
	 * 在调用模板消息接口后，会返回JSON数据包。正常时的返回JSON数据包示例：{"errcode":0,"errmsg":"ok","msgid":200228332}
	 * @param access_token
	 * @return
	 */
	public static WaTemplateSendRet messageTemplateSend(String access_token,String str){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/message/template/send")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(str,Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaTemplateSendRet.class);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 开发者可以通过该接口，获取公众号当前使用的自动回复规则，包括关注后自动回复、消息自动回复（60分钟内触发一次）、关键词自动回复。
	 * 请注意：
	 * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自动回复配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
	 * 2、本接口仅能获取公众号在公众平台官网的自动回复功能中设置的自动回复规则，若公众号自行开发实现自动回复，或通过第三方平台开发者来实现，则无法获取。
	 * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。
	 * 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
	 * 5、本接口中返回的mediaID均为临时素材（通过素材管理-获取临时素材接口来获取这些素材），每次接口调用返回的mediaID都是临时的、不同的，在每次接口调用后3天有效，若需永久使用该素材，需使用素材管理接口中的永久素材。
	 * 接口调用请求说明
	 * http请求方式: GET（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=ACCESS_TOKEN
	 * 返回结果说明
	 * 返回的JSON格式样例：（注意，格式化前的json使用‘\’作为转义符）,这里参数过多，请参考开发文档
	 * @param access_token
	 * @return
	 */
	public static Getcallbackip get_current_autoreply_info(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/get_current_autoreply_info")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Getcallbackip.class);
	}
}
