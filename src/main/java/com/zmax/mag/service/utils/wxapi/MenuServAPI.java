package com.zmax.mag.service.utils.wxapi;

import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import com.zmax.common.utils.file.localhttp.LocalHttpClient;
import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wx.*;

public class MenuServAPI extends BaseAPI {

	// 参数 是否必须 说明
	// button 是 一级菜单数组，个数应为1~3个
	// sub_button 否 二级菜单数组，个数应为1~5个
	// type 是 菜单的响应动作类型
	// name 是 菜单标题，不超过16个字节，子菜单不超过40个字节
	// key click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节
	// url view类型必须 网页链接，用户点击菜单可打开链接，不超过256字节
	// media_id media_id类型和view_limited类型必须 调用新增永久素材接口返回的合法media_id

	// http请求方式：POST（请使用https协议）
	// https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
	/**
	 * 创建自定义菜单
	 * @param waMenu  微信自定义菜单对象
	 * @param access_token 接口凭证，大部分接口会用到
	 * @return
	 */
	public static BaseResult menuCreate(WaMenu waMenu, String access_token) {
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/menu/create")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(waMenu.toJsonNotshow2(), CHARSET)).build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}

	// 使用接口创建自定义菜单后，开发者还可使用接口查询自定义菜单的结构。
	// 请求说明
	// http请求方式：GET
	// https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN
	// 返回说明
	// 对应创建接口，正确的Json返回结果:
	// {"menu":{"button":[{"type":"click","name":"今日歌曲","key":"V1001_TODAY_MUSIC","sub_button":[]},{"type":"click","name":"歌手简介","key":"V1001_TODAY_SINGER","sub_button":[]},{"name":"菜单","sub_button":[{"type":"view","name":"搜索","url":"http://www.soso.com/","sub_button":[]},{"type":"view","name":"视频","url":"http://v.qq.com/","sub_button":[]},{"type":"click","name":"赞一下我们","key":"V1001_GOOD","sub_button":[]}]}]}}
	// 统一返回码说明
	// 使用网页调试工具调试该接口
	/**
	 * 查询自定义菜单的结构
	 * @param access_token  接口凭证，大部分接口会用到
	 * @return
	 */
	public static WaMenuRet menuGet(String access_token) {
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/cgi-bin/menu/get")
				.addParameter("access_token", access_token).build();
		return LocalHttpClient.executeJsonResult(httpUriRequest, WaMenuRet.class);
	}

	// 使用接口创建自定义菜单后，开发者还可使用接口删除当前使用的自定义菜单。
	// 请求说明
	// http请求方式：GET
	// https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN
	// 返回说明
	// 对应创建接口，正确的Json返回结果:
	// {"errcode":0,"errmsg":"ok"}
	// 统一返回码说明
	// 使用网页调试工具调试该接口
	/**
	 * 删除当前使用的自定义菜单
	 * @param access_token 接口凭证，大部分接口会用到
	 * @return
	 */
	public static BaseResult menuDelete(String access_token) {
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/cgi-bin/menu/delete")
				.addParameter("access_token", access_token).build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}

}
