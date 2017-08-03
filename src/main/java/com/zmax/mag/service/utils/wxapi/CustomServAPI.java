package com.zmax.mag.service.utils.wxapi;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;

import com.zmax.common.utils.file.localhttp.LocalHttpClient;
import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wx.*;
import com.zmax.mag.domain.bean.wxa.*;



/**
 * 客服接口
 * 当用户主动发消息给公众号的时候（包括发送信息、点击自定义菜单、订阅事件、扫描二维码事件、支付成功事件、用户维权），
 * 微信将会把消息数据推送给开发者，开发者在一段时间内（目前修改为48小时）可以调用客服消息接口，
 * 通过POST一个JSON数据包来发送消息给普通用户，在48小时内不限制发送次数。
 * 此接口主要用于客服等有人工消息处理环节的功能，方便开发者为用户提供更加优质的服务。 
 * 为了帮助公众号使用不同的客服身份服务不同的用户群体，客服接口进行了升级，开发者可以管理客服账号，并设置客服账号的头像和昵称。
 * 该能力针对所有拥有客服接口权限的公众号开放。
 * @author zmax
 *
 */
public class CustomServAPI extends BaseAPI{
	/**日志实例*/
	private static final Logger logger = Logger.getLogger(CustomServAPI.class);
	/**
	 * 客服帐号管理
	 * 开发者在根据开发文档的要求完成开发后，使用6.0.2版及以上版本的微信用户在与公众号进行客服沟通，公众号使用不同的客服账号进行回复后，用户可以看到对应的客服头像和昵称。
	 * 请注意，必须先在公众平台官网为公众号设置微信号后才能使用该能力。
	 */
	/**
	 * 添加客服帐号
	 * 开发者可以通过本接口为公众号添加客服账号，每个公众号最多添加10个客服账号。该接口调用请求如下：
	 * http请求方式: POST
	 * https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN
	 * POST数据示例如下：{"kf_account" : "test1@test","nickname" : "客服1","password" : "pswmd5",}
	 * 返回说明（正确时的JSON返回结果）：{"errcode" : 0,"errmsg" : "ok",}
	 * @param access_token
	 * @return
	 */
	public static BaseResult kfaccountAdd(String access_token,WaCustomAudParam waCustomAudParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(jsonHeader)
				.setUri(BASE_URI+"/customservice/kfaccount/add")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(waCustomAudParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	/**
	 * 修改客服帐号
	 * 开发者可以通过本接口为公众号修改客服账号。该接口调用请求如下：
	 * http请求方式: POST
	 * https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN
	 * POST数据示例如下：{"kf_account" : "test1@test","nickname" : "客服1","password" : "pswmd5",}
	 * 返回说明（正确时的JSON返回结果）：{"errcode" : 0,"errmsg" : "ok",}
	 * @param access_token
	 * @return
	 */
	public static BaseResult kfaccountUpdate(String access_token,WaCustomAudParam waCustomAudParam){
//		if(logger.isDebugEnabled()){
//			logger.debug(JsonUtilAli.toJSONString(waCustomAudParam,new String[]{"kf_account","nickname","password"},new String[]{"id","myname","statusValidOrNot","userId","userIdString"}));
//		}
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/customservice/kfaccount/update")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(JsonUtilAli.toJSONString(waCustomAudParam,new String[]{"kf_account","nickname","password"},new String[]{"id","myname","statusValidOrNot","userId","userIdString"}), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	/**
	 * 删除客服帐号
	 * 开发者可以通过该接口为公众号删除客服帐号。该接口调用请求如下：
	 * http请求方式: GET
	 * https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN
	 * POST数据示例如下：{"kf_account" : "test1@test","nickname" : "客服1","password" : "pswmd5",}
	 * 返回说明（正确时的JSON返回结果）：{"errcode" : 0,"errmsg" : "ok",}
	 * @param access_token
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	
	
	//坑货腾讯    采用get提交方式，客服参数只要kf_account  原来采用post提交方式，客服参数只要需要一个对象，还返回参数错误
	//https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT
	public static BaseResult kfaccountDel(String access_token,WaCustomAudParam waCustomAudParam) throws UnsupportedEncodingException{
		//{"id":16,"kf_account":"222@meiqisczp","myname":"","nickname":"343r3","password":"e10adc3949ba59abbe56e057f20f883e","statusValidOrNot":1,"userId":166,"userIdString":"zkx "}
//		if(logger.isDebugEnabled()){
//			logger.debug(JsonUtilAli.toJSONString(waCustomAudParam,new String[]{"kf_account","nickname","password"},new String[]{"id","myname","statusValidOrNot","userId","userIdString"}));
//		}
		//主要由于kf_account中有一个@，按照原方法会自动编码成%40，腾讯返回的错误是无效的客服账号
		HttpUriRequest httpUriRequest2 =new HttpGet(BASE_URI+"/customservice/kfaccount/del?access_token="+access_token+"&kf_account="+waCustomAudParam.getKfAccount());
//		HttpUriRequest httpUriRequest3 =new HttpPost("");
//		HttpUriRequest httpUriRequest = RequestBuilder.get()
//				.setUri(BASE_URI + "/customservice/kfaccount/del")
//				.addParameter("access_token", access_token)
//				.addParameter("kf_account",waCustomAudParam.getKfAccount())
//				//.setEntity(new StringEntity(JsonUtilAli.toJSONString(waCustomAudParam,new String[]{"kf_account","nickname","password"},new String[]{"id","myname","statusValidOrNot","userId","userIdString"}), CHARSET))
//				.build();
//		if(logger.isDebugEnabled()){
//			logger.debug(URLDecoder.decode(httpUriRequest.toString(), "utf-8"));
//			//logger.debug(httpUriRequest.getURI());
//			//logger.debug("httpUriRequest=="+httpUriRequest2.toString());
//		}
		return LocalHttpClient.executeJsonResult(httpUriRequest2,BaseResult.class);
	}
	
	/**
	 * 设置客服帐号的头像
	 * 开发者可调用本接口来上传图片作为客服人员的头像，头像图片文件必须是jpg格式，推荐使用640*640大小的图片以达到最佳效果。该接口调用请求如下：
	 * http请求方式: POST/FORM
	 * http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT
	 * 调用示例：使用curl命令，用FORM表单方式上传一个多媒体文件，curl命令的具体用法请自行了解
	 * 返回说明（正确时的JSON返回结果）：{"errcode" : 0,"errmsg" : "ok",}
	 * @param access_token
	 * @return
	 */
	public static BaseResult kfaccountUploadheadimg(String access_token,String kfaccount){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/customservice/kfaccount/uploadheadimg")
				.addParameter("access_token", access_token)
				.addParameter("kf_account", kfaccount)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	/**
	 * 获取所有客服账号
	 * 开发者通过本接口，获取公众号中所设置的客服基本信息，包括客服工号、客服昵称、客服登录账号。
	 * http请求方式: GET
	 * https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN
	 * 返回说明（正确时的JSON返回结果）：{"kf_list": [{"kf_account": "test1@test", "kf_nick": "ntest1", "kf_id": "1001""kf_headimgurl": " http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0"}, {"kf_account": "test2@test", "kf_nick": "ntest2", "kf_id": "1002""kf_headimgurl": " http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw /0"}]}
	 * @param access_token
	 * @return
	 */
	public static WaSendcustommanageListRet getkflist(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/customservice/getkflist")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaSendcustommanageListRet.class);
	}

	/**
	 * 消息发送
	 * @param access_token
	 * @param messageJson
	 * @return
	 */
	public static BaseResult messageCustomSend(String access_token,String messageJson){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(jsonHeader)
				.setUri(BASE_URI+"/cgi-bin/message/custom/send")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(messageJson,Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	/**
	 * 这个方法可以用于发送任何消息类型，text,image,voice...
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static BaseResult sendMsg(String access_token,String msg){
		return messageCustomSend(access_token,msg);
	}
	
	
	/**
	 * 消息发送之文本
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static BaseResult sendText(String access_token,WaSendcustommsgTextParam msg){
		String str = msg.toJsonNotshow2();
		return messageCustomSend(access_token,str);
	}
	/**
	 * 消息发送之图片
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static BaseResult sendImage(String access_token,WaSendcustommsgImageParam msg){
		String str = msg.toJsonNotshow2();
		return messageCustomSend(access_token,str);
	}
	
	/**
	 * 消息发送之语音
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static BaseResult sendVoice(String access_token,WaSendcustommsgVoiceParam msg){
		String str = msg.toJsonNotshow2();
		return messageCustomSend(access_token,str);
	}
	
	
	/**
	 * 消息发送之视频
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static BaseResult sendVideo(String access_token,WaSendcustommsgVideoParam msg){
		String str = msg.toJsonNotshow2();
		return messageCustomSend(access_token,str);
	}
	
	/**
	 * 消息发送之音乐
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static BaseResult sendMusic(String access_token,WaSendcustommsgMusicParam msg){
		String str = msg.toJsonNotshow2();
		return messageCustomSend(access_token,str);
	}
	
	/**
	 * 消息发送之图文消息
	 * @param access_token
	 * @param msg
	 * @return
	 */
	public static BaseResult Articles(String access_token,WaSendcustommsgArticlesParam msg){
		String str = msg.toJsonNotshow2();
		return messageCustomSend(access_token,str);
	}

}
