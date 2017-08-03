package com.zmax.mag.service.spec;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.zmax.common.exception.BoException;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wx.*;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.service.my.*;
import com.zmax.mag.service.utils.MyUtils;
import com.zmax.mag.service.utils.wxapi.PaymentServAPI;
import com.zmax.mag.service.utils.wxapi.SignatureUtil;


/**
 * 微信服务之微信支付
 * <br>流程：
 * <br>后台：
 * <br>4 后台根据订单OrderrXxx生成WaUnifiedPaymentParam 微信统一下单请求对象 ，这个通常在业务系统中做的。
 * <br>5 后台调用统一下单api发给微信PaymentServAPI WaUnifiedPaymentRet payUnifiedorder(WaUnifiedPaymentParam waUnifiedPaymentParam)
 * <br>后台得到WaUnifiedPaymentRet,成功的话，最关键是其中的prepayId
 * <br>6 后台使用prepayId创建H5支付对象WaJsapiPaymentParam，创建签名
 * <br>后台将WaJsapiPaymentParam发给前台
 * <br>前台：
 * <br>7 调用JSAPI，发送WaJsapiPaymentParam到微信，8和9是用户与微信间的互动。结束后，回到前台？（回到哪个页面？）
 * <br>前台向后台发起查询
 * <br>10 11 后台查询一下订单的支付状态，如果没成功再去微信查一下：PaymentServAPI WaOrderQueryRet payOrderquery(WaOrderQueryParam waOrderQueryParam)
 * <br>异步：
 * <br>14 后台会收到微信的支付结果通知，后台收通知地址在WaUnifiedPaymentRet.notify_url中设置的
 * <br>说明：
 * <br>10和14是起了相同作用，注意并发。
 * @author zmax
 *
 */
@Service
public class SpecWxPayService {
	private static final Logger logger = Logger.getLogger(SpecWxPayService.class);
	@Autowired
	WaQrcodeticketSceneService waQrcodeticketSceneService;

	@Autowired
	SpecUserService specUserService;
	@Autowired
	WaGeneralNoticeRetService waGeneralNoticeRetService;
	@Autowired
	SpecMagPayService specMagPayService;
	/**
	 * 微信支付请求4-6步合并
	 * 输入WaUnifiedPaymentParam 微信统一下单请求对象(半成品)，传出H5支付对象
	 * 将微信统一下单请求对象(半成品)补全，向微信平台请求
	 * 将返回结果转换成H5订单对象，进行返回
	 * @param waUnifiedPaymentParam 微信统一下单请求对象(半成品)由订单生成的，有一半在值在业务函数中填写(比如价格，单号)，另一半在此函数中填写(如APPID，签名等)
	 * @param bizId
	 * @return WaJsapiPaymentParam H5需要的jsapi支付对象
	 * @throws BoException
	 * @throws Exception
	 */
	public WaJsapiPaymentParam payUnifiedorder(WaUnifiedPaymentParam waUnifiedPaymentParam,Integer bizId) throws BoException,Exception{
		if(waUnifiedPaymentParam==null)
			throw new BoException("参数为空");
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
		waUnifiedPaymentParam.setAppid(wxcfg.getAppid()); 
		waUnifiedPaymentParam.setMchId(wxcfg.getMchid());
		waUnifiedPaymentParam.setNonceStr(UUID.randomUUID().toString().replace("-", ""));	
		logger.debug("wxcfg.getPaykey()="+wxcfg.getPaykey());
		//sign是重点
		String sign=SignatureUtil.signWaUnifiedPaymentParam(waUnifiedPaymentParam, wxcfg.getPaykey());
		waUnifiedPaymentParam.setSign(sign);
		if (logger.isDebugEnabled())
			logger.debug("WX:"+waUnifiedPaymentParam.toString());

		if("APP".equals(waUnifiedPaymentParam.getTradeType())){
		/*	waUnifiedPaymentParam.setAppid(wxcfg.getAppAppid());
			waUnifiedPaymentParam.setMchId(wxcfg.getAppMchid()); 
			sign=SignatureUtil.signWaUnifiedPaymentParam(waUnifiedPaymentParam, wxcfg.getAppPaykey());
			waUnifiedPaymentParam.setSign(sign);*/
			if (logger.isDebugEnabled())
				logger.debug("APP:"+waUnifiedPaymentParam.toString());
		}		
		
		
		WaUnifiedPaymentRet waUnifiedPaymentRet=PaymentServAPI.payUnifiedorder(waUnifiedPaymentParam);
		if (logger.isDebugEnabled())
			logger.debug("waUnifiedPaymentRet="+waUnifiedPaymentRet.toString());
		if(waUnifiedPaymentRet!=null&&waUnifiedPaymentRet.getReturnCode().equalsIgnoreCase("FAIL")){
			logger.error("微信支付请求返回的错误:"+waUnifiedPaymentRet.getReturnMsg());
			throw new BoException("微信支付请求返回的错误:"+waUnifiedPaymentRet.getReturnMsg());
		}
		String package1="prepay_id="+waUnifiedPaymentRet.getPrepayId();

		//根据unifiedPayment的prepay_id得到jsapiPayment
		//精简构造 网页端调起支付API
		WaJsapiPaymentParam waJsapiPaymentParam = new WaJsapiPaymentParam(
				wxcfg.getAppid() , //String 公众号id  商户注册具有支付权限的公众号成功后即可获得 
				new Date().getTime()/1000 , //Long 时间戳  当前的时间 
				UUID.randomUUID().toString().replace("-", "") , //String 随机字符串  随机字符串，不长于32位。 
				package1 , //String 订单详情扩展字符串  统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=*** 
				"MD5" , //String 签名方式 default=MD5 签名算法，暂支持MD5 
				null , //String 签名  签名 
				waUnifiedPaymentRet.getPrepayId() , //String 预支付交易会话ID  H5对象不需要这个 
				null
				);	
		String sign1=SignatureUtil.signWaJsapiPaymentParam(waJsapiPaymentParam, wxcfg.getPaykey());
		waJsapiPaymentParam.setPaysign(sign1);
		if (logger.isDebugEnabled())
			logger.debug("waJsapiPaymentParam="+waJsapiPaymentParam.toString());
		//此处返回H5对象，如果是APP后面会继续处理
		return waJsapiPaymentParam;
	}
	/**
	 * 微信支付请求4-6步合并测试版,随机字符串  package1 是mobile
	 * @param waUnifiedPaymentParam
	 * @param bizId
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public WaJsapiPaymentParam payUnifiedorderTest(WaUnifiedPaymentParam waUnifiedPaymentParam,String mobile) throws BoException,Exception{
		if(waUnifiedPaymentParam==null)
			throw new BoException("参数为空");
		Wxcfg wxcfg=MyUtils.takeWxcfg(Conf.BIZID);
		waUnifiedPaymentParam.setAppid(wxcfg.getAppid());
		waUnifiedPaymentParam.setMchId(wxcfg.getMchid());
		waUnifiedPaymentParam.setNonceStr(UUID.randomUUID().toString().replace("-", ""));	
		//sign是重点
		String sign=SignatureUtil.signWaUnifiedPaymentParam(waUnifiedPaymentParam, wxcfg.getPaykey());
		waUnifiedPaymentParam.setSign(sign);


		String package1=mobile;

		//根据unifiedPayment的prepay_id得到jsapiPayment
		//精简构造 网页端调起支付API
		WaJsapiPaymentParam waJsapiPaymentParam = new WaJsapiPaymentParam(
				wxcfg.getAppid() , //String 公众号id  商户注册具有支付权限的公众号成功后即可获得 
				new Date().getTime()/1000 , //Long 时间戳  当前的时间 
				mobile , //String 随机字符串  随机字符串，不长于32位。 
				package1 , //String 订单详情扩展字符串  统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=*** 
				"MD5" , //String 签名方式 default=MD5 签名算法，暂支持MD5 
				null , //String 签名  签名 
				mobile , //String 预支付交易会话ID  H5对象不需要这个 
				null
				);
		String sign1=SignatureUtil.signWaJsapiPaymentParam(waJsapiPaymentParam, wxcfg.getPaykey());
		waJsapiPaymentParam.setPaysign(sign1);
		return waJsapiPaymentParam;
	}
	
	/**
	 * JsAPI转成AppAPI
	 * 微信支付H5对象转成微信APP的API对象
	 * 将返回结果转换成H5订单对象，进行返回
	 * @param waJsapiPaymentParam WaJsapiPaymentParam 微信支付H5对象
	 * @param bizId
	 * @return WaAppapiPaymentParam App的api支付对象
	 * @throws BoException
	 * @throws Exception
	 */
	public WaAppapiPaymentParam jsapiToAppapi(WaJsapiPaymentParam waJsapiPaymentParam,Integer bizId) throws BoException,Exception{
		if(waJsapiPaymentParam==null)
			throw new BoException("参数为空");
		if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
		Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
		WaAppapiPaymentParam waAppapiPaymentParam = new WaAppapiPaymentParam(
				wxcfg.getAppAppid() , //String 公众账号ID  微信分配的公众账号ID 
				wxcfg.getAppMchid() , //String 商户号  微信支付分配的商户号 
				waJsapiPaymentParam.getPrepayid() , //String 预支付交易会话ID  微信返回的支付交易会话ID 
				"Sign=WXPay" , //String 扩展字段  暂填写固定值Sign=WXPay 
				UUID.randomUUID().toString().replace("-", "") , //String 随机字符串  随机字符串，不长于32位。推荐随机数生成算法 
				""+new Date().getTime()/1000 , //String 时间戳  时间戳，请见接口规则-参数规定 
				null , //String 签名  签名，详见签名生成算法 
				null
			);
		String sign1=SignatureUtil.signWaAppapiPaymentParam(waAppapiPaymentParam, wxcfg.getAppPaykey());
		waAppapiPaymentParam.setSign(sign1);
		return waAppapiPaymentParam;
	}
	
	/**
	 * 微信订单查询
	 * @param transactionId 微信的订单号，优先使用
	 * @param outTradeNo 商户系统内部的订单号，当没提供transaction_id时需要传这个
	 * @param bizId
	 * @return
	 */
	public WaOrderQueryRet queryOrder(String transactionId,String outTradeNo,Integer bizId) throws BoException, Exception{
		try {
			if(StringUtils.isBlank(transactionId)&&StringUtils.isBlank(outTradeNo))
				throw new BoException("订单号与商家订单不能同时空");
			if(StringUtilz.integerNullOr0(bizId))bizId=Conf.BIZID;
			Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
			WaOrderQueryParam waOrderQueryParam=new WaOrderQueryParam();
			waOrderQueryParam.setTransactionId(transactionId);
			waOrderQueryParam.setOutTradeNo(outTradeNo);
			waOrderQueryParam.setAppid(wxcfg.getAppid());
			waOrderQueryParam.setMchId(wxcfg.getMchid());
			waOrderQueryParam.setNonceStr(UUID.randomUUID().toString().replace("-", ""));
			//貌似没进行签名也能通过接口返回一个waOrderQueryRet。要留意一下
			String sign=SignatureUtil.signWaOrderQueryParam(waOrderQueryParam, wxcfg.getPaykey());
			waOrderQueryParam.setSign(sign);

			WaOrderQueryRet waOrderQueryRet=PaymentServAPI.payOrderquery(waOrderQueryParam);
			return waOrderQueryRet;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);	
			return null;
		}
	}

	/**
	 * 支付结果回调
	 * @param waGeneralNoticeRet
	 * @throws Exception 
	 * @throws BoException 
	 */
	public void payNotify(WaGeneralNoticeRet waGeneralNoticeRet) throws BoException, Exception{
		logger.info("微信支付结果回调 payNotify "+waGeneralNoticeRet.getReturnCode()+" "+waGeneralNoticeRet.getResultCode()+" "+waGeneralNoticeRet.getOutTradeNo());
		waGeneralNoticeRetService.save(null,waGeneralNoticeRet);
		if("SUCCESS".equals(waGeneralNoticeRet.getReturnCode()) && "SUCCESS".equals(waGeneralNoticeRet.getResultCode())){
			//关键值是waGeneralNoticeRet.getOutTradeNo()
			String outTradeNo = waGeneralNoticeRet.getOutTradeNo();
			if (outTradeNo.startsWith(Conf.PAY_FORQUESTION_WX)) {
				specMagPayService.updateOrderrQuestionSucc(outTradeNo);				
			} else if (outTradeNo.startsWith(Conf.PAY_FORQUICK_WX)) {
				specMagPayService.updateOrderrQuickSucc(outTradeNo);
			} else if (outTradeNo.startsWith(Conf.PAY_FORQUESTIONMEMBER_WX)) {
				specMagPayService.updateOrderrQuestionviewSucc(outTradeNo);
			}else if (outTradeNo.startsWith(Conf.PAY_FORQUICKMEMBER_WX)) {
				specMagPayService.updateOrderrQuickviewSucc(outTradeNo);
			}
		}
	}
}
