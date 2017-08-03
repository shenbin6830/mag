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
 * 开发者可以使用接口，对公众平台的分组进行查询、创建、修改、删除等操作，也可以使用接口在需要时移动用户到某个分组。
 * @author Administrator
 *
 */
public class UserManageServAPI extends BaseAPI{

	/**创建分组
	 * 一个公众账号，最多支持创建100个分组。
	 * 接口调用请求说明
	 * http请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN
	 * POST数据格式：json
	 * POST数据例子：{"group":{"name":"test"}}
	 * 返回说明 正常时的返回JSON数据包示例：{"group": {"id": 107, "name": "test"}}
	 * 错误时的JSON数据包示例（该示例为AppID无效错误）：{"errcode":40013,"errmsg":"invalid appid"}
	 * @param access_token  公众号的access_token
	 * @return
	 */
	public static BaseResult groupsCreate(String access_token,WxUsergroupsAddeditdelParam wxUsergroupsAddeditdelParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/groups/create")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(JsonUtilAli.toJSONString(wxUsergroupsAddeditdelParam,new String[]{"group","name"},null), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	
	/**查询所有分组
	 * 接口调用请求说明
	 * http请求方式: GET（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN
	 * 返回说明 正常时的返回JSON数据包示例：{"groups": [{"id": 0, "name": "未分组", "count": 72596}, {"id": 1,"name": "黑名单", "count": 36}]}
	 * 错误时的JSON数据包示例（该示例为AppID无效错误）：{"errcode":40013,"errmsg":"invalid appid"}
	 * @param access_token  公众号的access_token
	 * @return
	 */
	public static WxUsergroupsRet groupsGet(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/cgi-bin/groups/get")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WxUsergroupsRet.class);
	}
	
	/**
	 * 查询用户所在分组
	 * 通过用户的OpenID查询其所在的GroupID。
	 *  接口调用请求说明
	 * http请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN
	 * POST数据格式：json
	 * POST数据例子：{"openid":"od8XIjsmk6QdVTETa9jLtGWA6KBc"}
	 * 返回说明 正常时的返回JSON数据包示例：{"groupid": 102}
	 * 错误时的JSON数据包示例（该示例为OpenID无效错误）：{"errcode":40003,"errmsg":"invalid openid"}
	 * @param access_token  公众号的access_token
	 * @return
	 */
	public static WxUsergroupsQueryRet groupsGetid(String access_token,WxUsergroupsQueryParam wxUsergroupsQueryParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/groups/getid")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(JsonUtilAli.toJSONString(wxUsergroupsQueryParam,new String[]{"group","name"},null), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WxUsergroupsQueryRet.class);
	}
	
	
	/**
	 * 修改分组名
	 * 接口调用请求说明
	 * http请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN
	 * POST数据格式：json
	 * POST数据例子：{"group":{"id":108,"name":"test2_modify2"}}
	 * 返回说明 正常时的返回JSON数据包示例：{"errcode": 0, "errmsg": "ok"}
	 * 错误时的JSON数据包示例（该示例为AppID无效错误）：{"errcode":40013,"errmsg":"invalid appid"}
	 * @param access_token  公众号的access_token
	 * @return
	 */
	public static BaseResult groupsUpdate(String access_token,WxUsergroupsAddeditdelParam wxUsergroupsAddeditdelParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/groups/update")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(wxUsergroupsAddeditdelParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	/**
	 * 移动用户分组
	 * 接口调用请求说明
	 * http请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN
	 * POST数据格式：json
	 * POST数据例子：{"openid":"oDF3iYx0ro3_7jD4HFRDfrjdCM58","to_groupid":108}
	 * 返回说明 正常时的返回JSON数据包示例：{"errcode": 0, "errmsg": "ok"}
	 * 错误时的JSON数据包示例（该示例为AppID无效错误）：{"errcode":40013,"errmsg":"invalid appid"}
	 * @param access_token  公众号的access_token
	 * @return
	 */
	public static BaseResult groupsMembersUpdate(String access_token,WxUsergroupsMoveParam wxUsergroupsMoveParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/groups/members/update")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(wxUsergroupsMoveParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	/**
	 * 批量移动用户分组
	 * 接口调用请求说明
	 * http请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN
	 * POST数据格式：json
	 * POST数据例子：{"openid_list":["oDF3iYx0ro3_7jD4HFRDfrjdCM58","oDF3iY9FGSSRHom3B-0w5j4jlEyY"],"to_groupid":108}
	 * 返回说明 正常时的返回JSON数据包示例：{"errcode": 0, "errmsg": "ok"}
	 * 错误时的JSON数据包示例（该示例为AppID无效错误）：{"errcode":40013,"errmsg":"invalid appid"}
	 * @param access_token
	 * @return
	 */
	public static BaseResult groupsMembersBatchupdate(String access_token,WxUsergroupsMovebatParam wxUsergroupsMovebatParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/groups/members/batchupdate")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(wxUsergroupsMovebatParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	/**
	 * 删除分组
	 * 注意本接口是删除一个用户分组，删除分组后，所有该分组内的用户自动进入默认分组。 接口调用请求说明
	 * http请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN
	 * POST数据格式：json
	 * POST数据例子：{"group":{"id":108}}
	 * 返回说明 正常时的返回JSON数据包示例：{"errcode": 0, "errmsg": "ok"}
	 * 错误时的JSON数据包示例（该示例为AppID无效错误）：{"errcode":40013,"errmsg":"invalid appid"}
	 * @param access_token  公众号的access_token
	 * @return
	 */
	public static BaseResult groupsDelete(String access_token,WxUsergroupsAddeditdelParam wxUsergroupsAddeditdelParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/groups/delete")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(wxUsergroupsAddeditdelParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 设置备注名
	 * 开发者可以通过该接口对指定用户设置备注名，该接口暂时开放给微信认证的服务号。 接口调用请求说明
	 * http请求方式: POST（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN
	 * POST数据格式：JSON
	 * POST数据例子：{"openid":"oDF3iY9ffA-hqb2vVvbr7qxf6A0Q","remark":"pangzi"}
	 * 返回说明 正常时的返回JSON数据包示例：{"errcode":0,"errmsg":"ok" }
	 * 错误时的JSON数据包示例（该示例为AppID无效错误）：{"errcode":40013,"errmsg":"invalid appid"}
	 * @param access_token
	 * @return
	 */
	public static Getcallbackip UserInfoUpdateremark(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/user/info/updateremark")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Getcallbackip.class);
	}
	
	
	
	
	
	
	
	
	/**
	 * 获取关注者后的用户基本信息（包括UnionID机制）
	 * 开发者可通过OpenID来获取用户基本信息。请使用https协议。
	 * 接口调用请求说明
	 * http请求方式: GET
	 * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
	 * 返回说明
	 * 正常情况下，微信会返回下述JSON数据包给公众号：{"subscribe": 1, "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", "nickname": "Band", "sex": 1, "language": "zh_CN", "city": "广州", "province": "广东", "country": "中国", "headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0", "subscribe_time": 1382694957,"unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL""remark": "","groupid": 0}
	 * 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:{"errcode":40013,"errmsg":"invalid appid"}
	 * @param access_token
	 * @return
	 */
	public static Wxouser cgiBinUserInfo(String access_token,String openid,String lang){
		if(lang==null) lang="zh_CN";
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/cgi-bin/user/info")
				.addParameter("access_token", access_token)
				.addParameter("openid", openid)
				.addParameter("lang", lang)
				.build();
		Wxouser wxouser= LocalHttpClient.executeJsonResult(httpUriRequest,Wxouser.class);
		if(wxouser==null)
			return null;
//		if(!Integer.valueOf(0).equals(wxouser.getErrcode())){
//			return null;
//		}
		wxouser.setId(wxouser.getOpenid());
		return wxouser;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求。
	 * 接口调用请求说明
	 * http请求方式: GET（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
	 * 正确时返回JSON数据包：{"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}
	 * 错误时返回JSON数据包（示例为无效AppID错误）：{"errcode":40013,"errmsg":"invalid appid"}
	 * 
	 * 
	 * 附：关注者数量超过10000时,当公众号关注者数量超过10000时，可通过填写next_openid的值，从而多次拉取列表的方式来满足需求。
	 * 具体而言，就是在调用接口时，将上一次调用得到的返回中的next_openid值，作为下一次调用中的next_openid值。
	 * 示例如下：
	 * 公众账号A拥有23000个关注的人，想通过拉取关注接口获取所有关注的人，那么分别请求url如下：
	 * https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN 
	 * 返回结果:{"total":23000,"count":10000,"data":{"openid":["OPENID1","OPENID2",...,"OPENID10000"]},"next_openid":"NEXT_OPENID1"}
	 * https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID1
	 * 返回结果:{"total":23000,"count":10000,"data":{"openid":["OPENID10001","OPENID10002",...,"OPENID20000"]},"next_openid":"NEXT_OPENID2"}
	 * https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID2
	 * 返回结果（关注者列表已返回完时，返回next_openid为空）:{"total":23000,"count":3000,"data":{""openid":["OPENID20001","OPENID20002",...,"OPENID23000"]},"next_openid":" "}
	 * @param access_token
	 * @return
	 */
	public static Getcallbackip UserGet(String access_token,String NEXT_OPENID){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/cgi-bin/user/get")
				.addParameter("access_token", access_token)
				.addParameter("next_openid", NEXT_OPENID)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Getcallbackip.class);
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 *  1 第一步：用户同意授权，获取code
		2 第二步：通过code换取网页授权access_token
		3 第三步：刷新access_token（如果需要）
		4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
		5 附：检验授权凭证（access_token）是否有效
	 */
	
	/**
	 * 第一步：用户同意授权，获取code
	 * 
	 * 在确保微信公众账号拥有授权作用域（scope参数）的权限的前提下（服务号获得高级接口后，默认拥有scope参数中的snsapi_base和snsapi_userinfo），引导关注者打开如下页面：
		https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
		若提示“该链接无法访问”，请检查参数是否填写错误，是否拥有scope参数对应的授权作用域权限。
	 */
	
	
	/**网页授权获取用户基本信息
	 * 第二步：通过code换取网页授权access_token和用户openid
	 * @param appid 公众号的唯一标识
	 * @param secret  公众号的appsecret
	 * @param code 填写第一步获取的code参数
	 * @return
	 */
	public static AccessTokenOauth SnsOauth2AccessToken(String appid,String secret,String code){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/sns/oauth2/access_token")
				.addParameter("appid", appid)
				.addParameter("secret", secret)
				.addParameter("code", code)
				.addParameter("grant_type", "authorization_code")
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,AccessTokenOauth.class);
	}
	
	
	/**网页授权获取用户基本信息
	 * 第三步：刷新access_token（如果需要）
	 * @param appid 公众号的唯一标识
	 * @param refresh_token 填写通过access_token获取到的refresh_token参数
	 * @return
	 */
	public static AccessTokenOauth SnsOauth2RefreshToken(String appid,String refresh_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/sns/oauth2/refresh_token")
				.addParameter("appid", appid)
				.addParameter("grant_type", "refresh_token")
				.addParameter("refresh_token", refresh_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,AccessTokenOauth.class);
	}
	
	
	
	
	/**网页授权获取用户基本信息
	 * 第四步：拉取用户信息(需scope为 snsapi_userinfo)
	 * @param access_token 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * @param openid 用户的唯一标识
	 * @param lang 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @return
	 */
	public static Wxouser snsUserInfo(String access_token,String openid,String lang){
		if(lang==null) lang="zh_CN";
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/sns/userinfo")
				.addParameter("access_token", access_token)
				.addParameter("openid", openid)
				.addParameter("lang", lang)
				.build();
		Wxouser wxouser= LocalHttpClient.executeJsonResult(httpUriRequest,Wxouser.class);
		wxouser.setId(wxouser.getOpenid());
		return wxouser;
	}
	
	
	
	
	
	
	/**网页授权获取用户基本信息
	 * 附：检验授权凭证（access_token）是否有效
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public static BaseResult SnsAuth(String access_token,String openid){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/sns/AUTH")
				.addParameter("access_token", access_token)
				.addParameter("openid", openid)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
}
