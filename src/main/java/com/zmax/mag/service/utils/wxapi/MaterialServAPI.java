package com.zmax.mag.service.utils.wxapi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.zmax.common.utils.file.localhttp.LocalHttpClient;
import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wx.*;



/**
 * @author Administrator
 *
 */
public class MaterialServAPI extends BaseAPI{
	/**临时素材的上传api地址*/
	static String URL_media_upload=BASE_URI+"/cgi-bin/media/upload";
	/**永久素材的上传api地址*/
	static String URL_material_add_material=BASE_URI+"/cgi-bin/material/add_material";
	/**永久素材的下载api地址*/
	static String URL_material_get_material=BASE_URI+"/cgi-bin/material/get_material";
	/**永久素材的删除api地址*/
	static String URL_material_del_material=BASE_URI+"/cgi-bin/material/del_material";
	/**图文永久素材的修改api地址*/
	static String URL_material_update_material_article=BASE_URI+"/cgi-bin/material/update_news";	

	/**
	 * 新增素材 文件名版
	 * 临时：https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
	 * 永久： https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN
	 * @param access_token
	 * @param mediaType
	 * @param filename
	 * @param isTmp 是否为临时素材
	 * @param WaMaterialAddnewsvideoParam 视频素材的说明对象，如果不为null表示素材为视频video
	 * @return
	 */
	private static HttpPost materialAdd(String access_token,MediaType mediaType,String filename,boolean isTmp,WaMaterialAddnewsvideoParam waMaterialAddnewsvideoParam){
		String upurl=(isTmp)?URL_media_upload:URL_material_add_material;
		HttpPost httpPost = new HttpPost(upurl);
		FileBody bin = new FileBody(new File(filename));
		MultipartEntityBuilder multipartEntityBuilder= MultipartEntityBuilder.create()
				.addPart("media", bin)
				.addTextBody("access_token", access_token)
				.addTextBody("type",mediaType.uploadType());
		if(waMaterialAddnewsvideoParam!=null)
			multipartEntityBuilder.addTextBody("description", waMaterialAddnewsvideoParam.toJsonNotshow2());
		HttpEntity reqEntity = multipartEntityBuilder.build();
		httpPost.setEntity(reqEntity);
		return httpPost;
	}
	/**
	 * 新增素材 File版
	 * 临时：https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
	 * 永久： https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN
	 * @param access_token
	 * @param mediaType
	 * @param media File
	 * @param isTmp 是否为临时素材
	 * @param WaMaterialAddnewsvideoParam 视频素材的说明对象，如果不为null表示素材为视频video
	 * @return
	 */
	private static HttpPost materialAdd(String access_token,MediaType mediaType,File media,boolean isTmp,WaMaterialAddnewsvideoParam waMaterialAddnewsvideoParam){
		String upurl=(isTmp)?URL_media_upload:URL_material_add_material;
		HttpPost httpPost = new HttpPost(upurl);
		FileBody bin = new FileBody(media);
		MultipartEntityBuilder multipartEntityBuilder= MultipartEntityBuilder.create()
				.addPart("media", bin)
				.addTextBody("access_token", access_token)
				.addTextBody("type",mediaType.uploadType());
		if(waMaterialAddnewsvideoParam!=null)
			multipartEntityBuilder.addTextBody("description", waMaterialAddnewsvideoParam.toJsonNotshow2());
		HttpEntity reqEntity = multipartEntityBuilder.build();
		httpPost.setEntity(reqEntity);
		return httpPost;
	}	
	/**
	 * 新增素材 InputStream版
	 * 临时：https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
	 * 永久： https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN
	 * @param access_token
	 * @param mediaType
	 * @param inputStream InputStream
	 * @param isTmp 是否为临时素材
	 * @param WaMaterialAddnewsvideoParam 视频素材的说明对象，如果不为null表示素材为视频video
	 * @return
	 */
	private static HttpPost materialAdd(String access_token,MediaType mediaType,InputStream inputStream,boolean isTmp,WaMaterialAddnewsvideoParam waMaterialAddnewsvideoParam){
		String upurl=(isTmp)?URL_media_upload:URL_material_add_material;
		HttpPost httpPost = new HttpPost(upurl);
		InputStreamBody inputStreamBody = new InputStreamBody(inputStream, mediaType.mimeType(),"temp."+mediaType.fileSuffix());
		MultipartEntityBuilder multipartEntityBuilder= MultipartEntityBuilder.create()
				.addPart("media", inputStreamBody)
				.addTextBody("access_token", access_token)
				.addTextBody("type",mediaType.uploadType());
		if(waMaterialAddnewsvideoParam!=null)
			multipartEntityBuilder.addTextBody("description", waMaterialAddnewsvideoParam.toJsonNotshow2());
		HttpEntity reqEntity = multipartEntityBuilder.build();
		httpPost.setEntity(reqEntity);
		return httpPost;
	}
	/**
	 * 新增素材 URI版
	 * 临时：https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
	 * 永久： https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN
	 * @param access_token
	 * @param mediaType
	 * @param uri URI
	 * @param isTmp 是否为临时素材
	 * @param WaMaterialAddnewsvideoParam 视频素材的说明对象，如果不为null表示素材为视频video
	 * @return
	 */
	private static HttpPost materialAdd(String access_token,MediaType mediaType,URI uri,boolean isTmp,WaMaterialAddnewsvideoParam waMaterialAddnewsvideoParam){
		String upurl=(isTmp)?URL_media_upload:URL_material_add_material;
		HttpPost httpPost = new HttpPost(upurl);
		CloseableHttpClient tempHttpClient = HttpClients.createDefault();
		try {
			HttpEntity remoteEntity = tempHttpClient.execute(RequestBuilder.get().setUri(uri).build()).getEntity();
			MultipartEntityBuilder multipartEntityBuilder= MultipartEntityBuilder.create()
					.addBinaryBody("media",EntityUtils.toByteArray(remoteEntity),ContentType.get(remoteEntity),"temp."+mediaType.fileSuffix())
					.addTextBody("access_token", access_token)
					.addTextBody("type",mediaType.uploadType());
			if(waMaterialAddnewsvideoParam!=null)
				multipartEntityBuilder.addTextBody("description", waMaterialAddnewsvideoParam.toJsonNotshow2());
			HttpEntity reqEntity = multipartEntityBuilder.build();
			httpPost.setEntity(reqEntity);
		} catch (UnsupportedCharsetException | ParseException | IOException e) {
			e.printStackTrace();
			//logger.error(e);
		}
		return httpPost;
	}
	/**
	 * 新增临时素材 文件名版
	 * 1、对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除
	 * 2、media_id是可复用的。
	 * 3、素材的格式大小等要求与公众平台官网一致。
	 * 具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，
	 * 语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式 
	 * https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
	 * @param access_token
	 * @param mediaType MaterialServAPI.MediaType
	 * @param filename String
	 * @return
	 */
	public static WaMaterialAddtmpRet materialAddTmp(String access_token,MediaType mediaType,String filename){
		HttpPost httpPost = materialAdd(access_token, mediaType, filename, true,null);
		return LocalHttpClient.executeJsonResult(httpPost,WaMaterialAddtmpRet.class);
	}
	/**
	 * 新增临时素材 文件版
	 * 1、对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除
	 * 2、media_id是可复用的。
	 * 3、素材的格式大小等要求与公众平台官网一致。
	 * 具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，
	 * 语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式 
	 * @param access_token
	 * @param mediaType MaterialServAPI.MediaType
	 * @param media File
	 * @return
	 */
	public static WaMaterialAddtmpRet materialAddTmp(String access_token,MediaType mediaType,File media){
		HttpPost httpPost = materialAdd(access_token, mediaType, media, true,null);
		return LocalHttpClient.executeJsonResult(httpPost,WaMaterialAddtmpRet.class);
	}
	/**
	 * 新增临时素材 数据流版
	 * 1、对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除
	 * 2、media_id是可复用的。
	 * 3、素材的格式大小等要求与公众平台官网一致。
	 * 具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，
	 * 语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式 
	 * @param access_token
	 * @param mediaType MaterialServAPI.MediaType
	 * @param media File
	 * @return
	 */
	public static WaMaterialAddtmpRet materialAddTmp(String access_token,MediaType mediaType,InputStream inputStream){
		HttpPost httpPost = materialAdd(access_token, mediaType, inputStream, true,null);
		return LocalHttpClient.executeJsonResult(httpPost,WaMaterialAddtmpRet.class);
	}
	/**
	 * 新增临时素材 远程URL版
	 * 1、对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除
	 * 2、media_id是可复用的。
	 * 3、素材的格式大小等要求与公众平台官网一致。
	 * 具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，
	 * 语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式 
	 * @param access_token
	 * @param mediaType MaterialServAPI.MediaType
	 * @param media File
	 * @return
	 */
	public static WaMaterialAddtmpRet materialAddTmp(String access_token,MediaType mediaType,URI uri){
		HttpPost httpPost =materialAdd(access_token, mediaType, uri, true,null);
		return LocalHttpClient.executeJsonResult(httpPost,WaMaterialAddtmpRet.class);
	}

	/**
	 * 获取临时素材
	 * 视频文件不支持https下载，调用该接口需http协议。
	 * http请求方式: GET,https调用
	 * https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
	 * @param access_token
	 * @param mediaId
	 * @return byte[]
	 */
	public static byte[] materialGetTmp(String access_token,String mediaId){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI+"/cgi-bin/media/get")
				.addParameter("access_token", access_token)
				.addParameter("media_id", mediaId)
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
	 * 新增永久图文素材
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN
	 * @param access_token
	 * @param waMaterialAddnewsParam
	 * @return
	 */
	public static WaMaterialAddnewsRet materialAddNewArticle(String access_token,WaMaterialAddnewsParam waMaterialAddnewsParam ){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(jsonHeader)
				.setUri(BASE_URI+"/cgi-bin/material/add_news")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(waMaterialAddnewsParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaMaterialAddnewsRet.class);
	}

	/**
	 * 新增其它永久素材 文件名版
	 * 包括：图片（image）、语音（voice）、缩略图（thumb） 视频（video）
	 * @param access_token
	 * @param mediaType MaterialServAPI.MediaType
	 * @param filename String
	 * @param WaMaterialAddnewsvideoParam 视频素材的说明对象，如果不为null表示素材为视频video
	 * @return
	 */
	public static WaMaterialAddnewsRet materialAddNew(String access_token,MediaType mediaType,String filename,WaMaterialAddnewsvideoParam waMaterialAddnewsvideoParam){
		HttpPost httpPost = materialAdd(access_token, mediaType, filename, false,waMaterialAddnewsvideoParam);
		return LocalHttpClient.executeJsonResult(httpPost,WaMaterialAddnewsRet.class);
	}
	/**
	 * 新增其它永久素材 文件版
	 * 包括：图片（image）、语音（voice）、缩略图（thumb） 视频（video）
	 * @param access_token
	 * @param mediaType MaterialServAPI.MediaType
	 * @param media File
	 * @param WaMaterialAddnewsvideoParam 视频素材的说明对象，如果不为null表示素材为视频video
	 * @return
	 */
	public static WaMaterialAddnewsRet materialAddNew(String access_token,MediaType mediaType,File media,WaMaterialAddnewsvideoParam waMaterialAddnewsvideoParam){
		HttpPost httpPost = materialAdd(access_token, mediaType, media, false,waMaterialAddnewsvideoParam);
		return LocalHttpClient.executeJsonResult(httpPost,WaMaterialAddnewsRet.class);
	}
	/**
	 * 新增其它永久素材 数据流版
	 * 包括：图片（image）、语音（voice）、缩略图（thumb） 视频（video）
	 * @param access_token
	 * @param mediaType MaterialServAPI.MediaType
	 * @param media File
	 * @param WaMaterialAddnewsvideoParam 视频素材的说明对象，如果不为null表示素材为视频video
	 * @return
	 */
	public static WaMaterialAddnewsRet materialAddNew(String access_token,MediaType mediaType,InputStream inputStream,WaMaterialAddnewsvideoParam waMaterialAddnewsvideoParam){
		HttpPost httpPost = materialAdd(access_token, mediaType, inputStream, false,waMaterialAddnewsvideoParam);
		return LocalHttpClient.executeJsonResult(httpPost,WaMaterialAddnewsRet.class);
	}
	/**
	 * 新增其它永久素材 远程URL版
	 * 包括：图片（image）、语音（voice）、缩略图（thumb） 视频（video）
	 * @param access_token
	 * @param mediaType MaterialServAPI.MediaType
	 * @param media File
	 * @param WaMaterialAddnewsvideoParam 视频素材的说明对象，如果不为null表示素材为视频video
	 * @return
	 */
	public static WaMaterialAddnewsRet materialAddNew(String access_token,MediaType mediaType,URI uri,WaMaterialAddnewsvideoParam waMaterialAddnewsvideoParam){
		HttpPost httpPost =materialAdd(access_token, mediaType, uri, false,waMaterialAddnewsvideoParam);
		return LocalHttpClient.executeJsonResult(httpPost,WaMaterialAddnewsRet.class);
	}
	
	
	/**
	 * 获取永久素材 非图文，非视频，返回本体
	 * http请求方式: POST,https调用
	 * https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN
	 * POST参数 {"media_id":MEDIA_ID}
	 * @param access_token
	 * @param mediaId
	 * @return byte[]
	 */
	public static byte[] materialGetNew(String access_token,String mediaId){
		WaMaterialGetdelParam waMaterialGetdelParam=new WaMaterialGetdelParam(mediaId, null);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(URL_material_get_material)
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(waMaterialGetdelParam.toJsonNotshow2(), CHARSET))
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
	 * 获取永久素材 图文
	 * http请求方式: POST,https调用
	 * https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN
	 * POST参数 {"media_id":MEDIA_ID}
	 * @param access_token
	 * @param mediaId
	 * @return WaMaterialGetarticalRet
	 */
	public static WaMaterialGetarticalRet materialGetNewArticle(String access_token,String mediaId){
		WaMaterialGetdelParam waMaterialGetdelParam=new WaMaterialGetdelParam(mediaId, null);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(URL_material_get_material)
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(waMaterialGetdelParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaMaterialGetarticalRet.class);
	}
	/**
	 * 获取视频
	 * http请求方式: POST,https调用
	 * https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN
	 * POST参数 {"media_id":MEDIA_ID}
	 * @param access_token
	 * @param mediaId
	 * @return WaMaterialGetvideoRet
	 */
	public static WaMaterialGetvideoRet materialGetNewVideo(String access_token,String mediaId){
		WaMaterialGetdelParam waMaterialGetdelParam=new WaMaterialGetdelParam(mediaId, null);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(URL_material_get_material)
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(waMaterialGetdelParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaMaterialGetvideoRet.class);
	}	

	/**
	 * 删除永久素材
	 * http请求方式: POST,https调用
	 * https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN
	 * POST参数 {"media_id":MEDIA_ID}
	 * @param access_token
	 * @param mediaId
	 * @return WaMaterialGetvideoRet
	 */
	public static BaseResult materialDelNew(String access_token,String mediaId){
		WaMaterialGetdelParam waMaterialGetdelParam=new WaMaterialGetdelParam(mediaId, null);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(URL_material_del_material)
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(waMaterialGetdelParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}	
	
	/**
	 * 修改图文永久素材
	 * http请求方式: POST,https调用
	 * https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN
	 * POST参数 {"media_id":MEDIA_ID}
	 * @param access_token
	 * @param waMaterialUpdatenewsParam WaMaterialUpdatenewsParam
	 * @return WaMaterialGetvideoRet
	 */
	public static BaseResult materialUpdateNewArticle(String access_token,WaMaterialUpdatenewsParam waMaterialUpdatenewsParam){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(URL_material_update_material_article)
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(waMaterialUpdatenewsParam.toJsonNotshow2(), CHARSET))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	/**
	 * 获取素材总数
	 * 永久素材的总数，也会计算公众平台官网素材管理中的素材
	 * 图片和图文消息素材（包括单图文和多图文）的总数上限为5000，其他素材的总数上限为1000
	 * http请求方式: GET
	 * https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN
	 * @param access_token
	 * @return WaMaterialGetvideoRet
	 */
	public static WaMaterialGetcountRet  takeMaterialcount(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI+"/cgi-bin/material/get_materialcount")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaMaterialGetcountRet.class);
	}
	/**
	 * 获取素材列表
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN
	 * @param access_token
	 * @param waMaterialBatchgetParam waMaterialBatchgetParam.type:素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	 * @return
	 */
	public static WaMaterialBatchgetRet takeMaterialList(String access_token, WaMaterialBatchgetParam waMaterialBatchgetParam){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI+"/cgi-bin/material/batchget_material")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,WaMaterialBatchgetRet.class);
	}
	/**
	 * 媒体素材类型枚举
	 * @author zmax
	 *
	 */
	public enum MediaType{
		image {
			@Override
			String mimeType() {
				return "image/jpeg";
			}

			@Override
			String fileSuffix() {
				return "jpg";
			}

			@Override
			String uploadType() {
				return "image";
			}
		},
		voice_mp3 {
			@Override
			String mimeType() {
				return "audio/mpeg";
			}

			@Override
			String fileSuffix() {
				return "mp3";
			}

			@Override
			String uploadType() {
				return "voice";
			}
		},
		voice_arm {
			@Override
			String mimeType() {
				return "audio/amr";
			}

			@Override
			String fileSuffix() {
				return "amr";
			}

			@Override
			String uploadType() {
				return "voice";
			}
		},video {
			@Override
			String mimeType() {
				return "video/mp4";
			}

			@Override
			String fileSuffix() {
				return "mp4";
			}

			@Override
			String uploadType() {
				return "video";
			}
		},thumb {
			@Override
			String mimeType() {
				return "image/jpeg";
			}

			@Override
			String fileSuffix() {
				return "jpg";
			}

			@Override
			String uploadType() {
				return "thumb";
			}
		};

		abstract String mimeType();

		abstract String fileSuffix();

		/**
		 * 上传类型
		 * @return
		 */
		abstract String uploadType();



	}
}
