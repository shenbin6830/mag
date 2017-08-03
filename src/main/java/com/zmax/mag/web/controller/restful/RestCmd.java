package com.zmax.mag.web.controller.restful;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.zmax.mag.domain.bean.*;

/**
 * 静态指令集 
 * 
 * 
 *
 */
public class RestCmd {
	/** 测试 */
	public static final String test1="test1";
	/**订单收货 参数中只需orderr.id即可*/
	public static final String cmd_ORDERR_REC_GOODS="restOrderrRecGoods";
	/**接单*/
	public static final String cmd_ORDERR_CONFIRM="restOrderrConfirm";
	/**收货*/
	public static final String cmd_ORDERR_SEND="restOrderrSend";
	/**订单取消 参数中只需orderr.id即可*/
	public static final String cmd_ORDERR_CANCEL="restOrderrCancel";
	/**订单微信支付*/
	public static final String cmd_ORDERR_PAY_WX="restOrderrPayWx";
	/**医生取消药方*/
	public static final String cmd_PRESCRIPT_CANCEL="restPrescriptCancel";
	/**医生取消药方产品*/
	public static final String cmd_PRESCRIPTPRODUCT_CANCEL="restPrescriptProductCancel";

}
