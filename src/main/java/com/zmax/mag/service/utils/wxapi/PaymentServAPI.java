package com.zmax.mag.service.utils.wxapi;

import java.nio.charset.Charset;
import java.util.Map;


import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;
import org.aspectj.weaver.SignatureUtils;



import com.zmax.common.utils.file.localhttp.LocalHttpClient;
import com.zmax.common.utils.string.JsonUtilAli;
import com.zmax.common.utils.string.XMLConverUtil;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wx.*;




/**
 * 基础接口
 * 获取access_token
 * 获取微信服务器IP地址。
 * @author zmax
 *
 */
public class PaymentServAPI extends BaseAPI{
	private static final Logger logger = Logger.getLogger(PaymentServAPI.class);
	/**
	 * 统一支付接口
	 * @param unifiedorder
	 * @return
	 */
	public static WaUnifiedPaymentRet payUnifiedorder(WaUnifiedPaymentParam waUnifiedPaymentParam){
		String waUnifiedPaymentParamXML = XMLConverUtil.convertToXML(waUnifiedPaymentParam);
		if(logger.isDebugEnabled()){
			logger.debug("waUnifiedPaymentParamSignedXML="+waUnifiedPaymentParamXML);
		}
		HttpUriRequest httpUriRequest = RequestBuilder.post()
										.setHeader(xmlHeader)
										.setUri(MCH_URI + "/pay/unifiedorder")
										.setEntity(new StringEntity(waUnifiedPaymentParamXML,Charset.forName("utf-8")))
										.build();
		return LocalHttpClient.executeXmlResult(httpUriRequest,WaUnifiedPaymentRet.class);
	}

	/**
	 * 应用场景
	 * 以下情况需要调用关单接口：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
	 * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
	 * 查询订单
	 * @param qrderQuery
	 * @param key
	 * @return
	 */
	public static WaOrderQueryRet payOrderquery(WaOrderQueryParam waOrderQueryParam){
		String waOrderQueryParamXML = XMLConverUtil.convertToXML(waOrderQueryParam);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(xmlHeader)
				.setUri(MCH_URI + "/pay/orderquery")
				.setEntity(new StringEntity(waOrderQueryParamXML,Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.executeXmlResult(httpUriRequest,WaOrderQueryRet.class);
	}
}
