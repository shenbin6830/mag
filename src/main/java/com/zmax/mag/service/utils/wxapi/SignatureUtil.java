package com.zmax.mag.service.utils.wxapi;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.zmax.common.utils.string.MapUtil;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.mag.domain.bean.WaGeneralNoticeRet;
import com.zmax.mag.domain.bean.wx.*;
import com.zmax.mag.domain.bean.wxa.*;



/**
 * 微信签名工具
 * @author zmax
 *
 */
public class SignatureUtil {
	private static final Logger logger = Logger.getLogger(SignatureUtil.class);
	/**
	 * 生成 package 字符串
	 * @param map
	 * @param paternerKey
	 * @return
	 */
	public static String generatePackage(Map<String, String> map,String paternerKey){
		String sign = generateSign(map,paternerKey);
		Map<String,String> tmap = MapUtil.order(map);
		String s2 = MapUtil.mapJoin(tmap,false,true);
		return s2+"&sign="+sign;
	}
	/**
	 * 生成sign SHA1 加密 toUpperCase
	 * @param map
	 * @return
	 */
	public static String generateSignSHA1(Map<String, String> map){
		Map<String, String> tmap = MapUtil.order(map);
		if(tmap.containsKey("sign")){
			tmap.remove("sign");
		}
		String str = MapUtil.mapJoin(tmap, false, false);
		if (logger.isDebugEnabled())
			logger.debug("SignatureUtil.str=" + str);
		return DigestUtils.shaHex(str).toUpperCase();
	}
	/**
	 * 生成sign MD5 加密 toUpperCase
	 * @param map
	 * @return
	 */
	public static String generateSign(Map<String, String> map){
		Map<String, String> tmap = MapUtil.order(map);
		if(tmap.containsKey("sign")){
			tmap.remove("sign");
		}
		if(tmap.containsKey("TABLE_ALIAS")){
			tmap.remove("TABLE_ALIAS");
		}
		String str = MapUtil.mapJoin(tmap, false, false);
		if (logger.isDebugEnabled())
			logger.debug("SignatureUtil.str=" + str);
		return DigestUtils.md5Hex(str).toUpperCase();
	}
	/**
	 * 生成sign MD5 加密 toUpperCase
	 * @param map
	 * @param paternerKey 结尾加&key=paternerKey 后再md5
	 * @return
	 */
	public static String generateSign(Map<String, String> map,String paternerKey){
		Map<String, String> tmap = MapUtil.order(map);
		if(tmap.containsKey("sign")){
			tmap.remove("sign");
		}
		if(tmap.containsKey("TABLE_ALIAS")){
			tmap.remove("TABLE_ALIAS");
		}
		String str = MapUtil.mapJoin(tmap, false, false);
		
		String str1=str+"&key="+paternerKey;
		String md5=DigestUtils.md5Hex(str1).toUpperCase();
		if (logger.isDebugEnabled()){
			logger.debug("SignatureUtil.str1=" + str1);
			logger.debug("system generate sign="+md5);
		}
		return md5;
	}

	/**
	 * 生成 paySign
	 * @param map
	 * @param paternerKey
	 * @return
	 */
	public static String generatePaySign(Map<String, String> map,String paySignKey){
		if(paySignKey!=null){
			map.put("appkey",paySignKey);
		}
		Map<String, String> tmap = MapUtil.order(map);
		String str = MapUtil.mapJoin(tmap,true,false);
		return DigestUtils.shaHex(str);
	}




	/**
	 * 生成事件消息接收签名
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static String generateEventMessageSignature(String token, String timestamp,String nonce) {
		String[] array = new String[]{token,timestamp,nonce};
		Arrays.sort(array);
		String s = StringUtilz.arrayToDelimitedString(array, "");
		return DigestUtils.shaHex(s);
	}

	/**
	 * 返回WxConfig签名
	 * @param WxConfig
	 * @return String sign
	 */
	public static String signWxConfig(WxConfig wxConfig){
		Map<String,String> map=MapUtil.objectToMapWith(wxConfig, new String[]{"noncestr","jsapi_ticket","timestamp","url"});
		String sign=SignatureUtil.generateSignSHA1(map);
		return sign;
	}
	/**
	 * 返回WaUnifiedPaymentParam签名
	 * @param waUnifiedPaymentParam
	 * @param paykey 支付密钥，微信商户平台设置，签名几乎必定用到
	 * @return String sign  根据字典顺序排好，加上key，再MD5转化为大写得到的
	 */
	public static String signWaUnifiedPaymentParam(WaUnifiedPaymentParam waUnifiedPaymentParam,String paykey){
		//手写
		AHandWaUnifiedPaymentParamSigned waUnifiedPaymentParamSigned=new AHandWaUnifiedPaymentParamSigned(
				waUnifiedPaymentParam.getAppid(), waUnifiedPaymentParam.getAttach(), waUnifiedPaymentParam.getBody(), 
				waUnifiedPaymentParam.getDetail(), waUnifiedPaymentParam.getDeviceInfo(), waUnifiedPaymentParam.getFeeType(), 
				waUnifiedPaymentParam.getGoodsTag(), waUnifiedPaymentParam.getMchId(), waUnifiedPaymentParam.getNonceStr(),
				waUnifiedPaymentParam.getNotifyUrl(), waUnifiedPaymentParam.getOpenid(), waUnifiedPaymentParam.getOutTradeNo(), 
				waUnifiedPaymentParam.getProductId(), waUnifiedPaymentParam.getSign(), waUnifiedPaymentParam.getSpbillCreateIp(), 
				waUnifiedPaymentParam.getTimeExpire(), waUnifiedPaymentParam.getTimeStart(), 
				waUnifiedPaymentParam.getTotalFee(), waUnifiedPaymentParam.getTradeType());
		
		Map<String,String> map=MapUtil.objectToMap(waUnifiedPaymentParamSigned);
		String sign=generateSign(map, paykey);
		return sign;
	}	
	/**
	 * 返回WaJsapiPaymentParam签名
	 * @param WaJsapiPaymentParam
	 * @param paykey 支付密钥，微信商户平台设置，签名几乎必定用到
	 * @return String sign  根据字典顺序排好，加上key，再MD5转化为大写得到的
	 */
	public static String signWaJsapiPaymentParam(WaJsapiPaymentParam waJsapiPaymentParam,String paykey){
		//即最后参与签名的参数有appId,timeStamp,nonceStr,package(prepay_id),signType
		String str = "appId="+waJsapiPaymentParam.getAppid()+"&nonceStr="+waJsapiPaymentParam.getNoncestr()+
				"&package="+waJsapiPaymentParam.getPackage1()+"&signType="+waJsapiPaymentParam.getSigntype()+
				"&timeStamp="+waJsapiPaymentParam.getTimestamp1()+"&key="+paykey;
		String sign=DigestUtils.md5Hex(str).toUpperCase();
		if (logger.isDebugEnabled()){
			logger.debug("str="+str);
			logger.debug("sign="+sign);
		}
		return sign;
	}	
	
	/**
	 * 返回WaAppapiPaymentParam签名
	 * @param WaAppapiPaymentParam
	 * @param paykey 支付密钥，微信商户平台设置，签名几乎必定用到
	 * @return String sign  根据字典顺序排好，加上key，再MD5转化为大写得到的
	 */
	public static String signWaAppapiPaymentParam(WaAppapiPaymentParam waAppapiPaymentParam,String paykey){
		//即最后参与签名的参数有appId,timeStamp,nonceStr,package(prepay_id),signType
		String str = "appid="+waAppapiPaymentParam.getAppid()
				+"&noncestr="+waAppapiPaymentParam.getNoncestr()
				+"&package="+waAppapiPaymentParam.getPackage1()
				+"&partnerid="+waAppapiPaymentParam.getPartnerid()
				+"&prepayid="+waAppapiPaymentParam.getPrepayid()
				+"&timestamp="+waAppapiPaymentParam.getTimestamp1()
				+"&key="+paykey;
		String sign=DigestUtils.md5Hex(str).toUpperCase();
		if (logger.isDebugEnabled()){
			logger.debug("app.str="+str);
			logger.debug("app.sign="+sign);
		}
/*

	appid , //String 公众账号ID  微信分配的公众账号ID 
	noncestr , //String 随机字符串  随机字符串，不长于32位。推荐随机数生成算法 
	package1 , //String 扩展字段  暂填写固定值Sign=WXPay 
	partnerid , //String 商户号  微信支付分配的商户号 
	prepayid , //String 预支付交易会话ID  微信返回的支付交易会话ID 
	timestamp1 , //String 时间戳  时间戳，请见接口规则-参数规定 
 */
		return sign;
	}
	
	/**
	 * 返回waGeneralNoticeRet签名
	 * @param waGeneralNoticeRet  QQ支付回调通知的返回值
	 * @param paykey  支付密钥，微信商户平台设置，签名几乎必定用到
	 * @return String sign  根据字典顺序排好，加上key，再MD5转化为大写得到的
	 */
	public static String signWaGeneralNoticeRet(WaGeneralNoticeRet waGeneralNoticeRet,String paykey){
		//手写
		AHandWaGeneralNoticeRetSigned waGeneralNoticeRetSigned = new AHandWaGeneralNoticeRetSigned(
				waGeneralNoticeRet.getReturnCode(), waGeneralNoticeRet.getReturnMsg(), waGeneralNoticeRet.getAppid(),
				waGeneralNoticeRet.getMchId(), waGeneralNoticeRet.getDeviceInfo(), waGeneralNoticeRet.getNonceStr(), 
				waGeneralNoticeRet.getSign(), waGeneralNoticeRet.getResultCode(), waGeneralNoticeRet.getErrCode(), 
				waGeneralNoticeRet.getErrCodeDes(),waGeneralNoticeRet.getOpenid(), waGeneralNoticeRet.getIsSubscribe(), 
				waGeneralNoticeRet.getTradeType(),waGeneralNoticeRet.getBankType(), waGeneralNoticeRet.getTotalFee(), 
				waGeneralNoticeRet.getFeeType(), waGeneralNoticeRet.getCashFee(), waGeneralNoticeRet.getCashFeeType(), 
				waGeneralNoticeRet.getCouponFee(), waGeneralNoticeRet.getCouponCount(), waGeneralNoticeRet.getTransactionId(), 
				waGeneralNoticeRet.getOutTradeNo(), waGeneralNoticeRet.getAttach(), waGeneralNoticeRet.getTimeEnd());
		Map<String,String> map=MapUtil.objectToMap(waGeneralNoticeRetSigned);
		String sign=generateSign(map, paykey);
		return sign;
	}	
	
	/**
	 * 返回waOrderQueryParam签名
	 * @param waOrderQueryParam  订单查询提交的对象
	 * @param paykey  支付密钥，微信商户平台设置，签名几乎必定用到
	 * @return String sign  根据字典顺序排好，加上key，再MD5转化为大写得到的
	 */
	public static String signWaOrderQueryParam(WaOrderQueryParam waOrderQueryParam,String paykey){
		//手写
		AHandWaOrderQueryParamSigned waOrderQueryParamSigned = new AHandWaOrderQueryParamSigned(
				waOrderQueryParam.getAppid(), waOrderQueryParam.getMchId(), waOrderQueryParam.getTransactionId(), 
				waOrderQueryParam.getOutTradeNo(), waOrderQueryParam.getNonceStr(), waOrderQueryParam.getSign());
		
		Map<String,String> map=MapUtil.objectToMap(waOrderQueryParamSigned);
		String sign=generateSign(map, paykey);
		return sign;
	}	
}
