package com.zmax.mag.service.spec;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.alipay.api.domain.Data;
import com.zmax.mag.web.controller.restful.entity.ClientInfo;
import com.zmax.common.exception.BoException;
import com.zmax.common.utils.easyui.Json;
import com.zmax.common.utils.string.SqlUtils;
import com.zmax.common.web.utils.RequestUtils;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.domain.conf.PropSys;
import com.zmax.mag.domain.dao.base.SpecRepo;
import com.zmax.mag.service.my.*;
import com.zmax.mag.service.utils.PermitCheckUtils;
import com.zmax.mag.domain.bean.wx.*;

@Service
public class SpecMagPayService {

	/** 日志实例 */
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	SpecRepo specRepo;
	@Autowired
	PermitCheckUtils permitCheckUtils;
	@Autowired
	PropSys propSys;
	@Autowired
	OrderrQuestionService orderrQuestionService;
	@Autowired
	WxouserService wxouserService;
	@Autowired
	SpecWxPayService specWxPayService;
	@Autowired
	OrderrQuickService orderrQuickService;
	@Autowired
	OrderrQuestionviewService orderrQuestionviewService;
	@Autowired
	OrderrQuickviewService orderrQuickviewService;
	@Autowired
	QuestionLinkMemberViewService questionLinkMemberViewService;
	@Autowired
	QuickLinkMemberViewService quickLinkMemberViewService;
	@Autowired
	MemberService memberService;
	@Autowired
	CashHisService cashHisService;
	@Autowired
	SpecMagPayService specMagPayService;
	@Autowired
	QuestionService questionService;
	@Autowired
	QuickService quickService;
	/**
	 * 观看问题的会员用积分支付成功。
	 * 
	 * @param member
	 * @param question
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public Json updateMemberCashPay(Member member, Question question)
			throws BoException, Exception {
		/*
		 * 1创建一个一对一提问订单，这个订单是余额支付 2.会员的积分相应减少 3.保存一条现金记录 4.支付承购后干的事 5.返回信息
		 */
			OrderrQuestionview orderrQuestionview = new OrderrQuestionview(
				null, // Date gmtPay 支付时间
				0, // Integer status 支付状态 default=0  {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
				0, // Integer itypePay 支付方式 default=0 {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
				member.getId(), // Integer memberId 会员
				question.getId(), // Integer 一对一问题ID
				null);
		orderrQuestionviewService.saveCreate(null, new OrderrQuestionview(),
				orderrQuestionview, null, null);
		// 会员积分减少
		member.setScore(member.getScore()
				-  question.getViewprice().intValue());
		memberService.update(null, member);
		CashHis cashHis = new CashHis(
				member.getId(), // Integer 会员id
				-2, // Integer 类型  defalut=0对会员来说正是得先进，负是失现金{'-2':'购买积分','-1':'提现','1':'现金充值','2':‘积分变现','3':'支付宝充值','4':'微信充值'}
				-(int) (question.getViewprice() * 10), // Integer 数量  default=0单位分，有正负，正是得，负是失
				orderrQuestionview.getId(), // Integer 订单的id default=0 根据类型，指向不同的订单表
				"orderrQuestionview", // String 备注说明
				null);
		cashHisService.saveCreate(null, new CashHis(), cashHis, null, null);
		specMagPayService
				.updateOrderrQuestionviewSucc(Conf.PAY_FORQUESTIONMEMBER_WX
						+ orderrQuestionview.getId());// 观看问题支付成功后
		
		QuestionLinkMemberView questionLinkMemberView=questionLinkMemberViewService.getFirst(new User(), "memberVi=?0 and questionId=?1", null, new Object[]{member.getId(),question.getId()});
		//提问者
		Member Qu=memberService.get(null, questionLinkMemberView.getMemberQu());
		//回答者
		Member An=memberService.get(null, questionLinkMemberView.getMemberAn());
		
		Qu.setScore(Qu.getScore()+new Double(question.getViewprice().doubleValue()*0.5).intValue());
		An.setScore(An.getScore()+new Double(question.getViewprice().doubleValue()*0.5).intValue());
		memberService.update(null, Qu);
		memberService.update(null, An);
		Integer Exp=question.getViewprice().intValue();	//偷看获得经验 1元=1经验 
		if(member.getExp()==null){
			member.setExp(Exp);
		}else{
			member.setExp(member.getExp()+Exp);
		}		
		//偷看所扣积分，平分给提问者和大师
		
		memberService.update(null, member);
		return new Json(true, "scorePaySuccess");
	}

	/**
	 * 提问题的会员用积分支付成功
	 * 
	 * @param member
	 * @param question
	 * @return
	 * @throws Exception
	 * @throws BoException
	 */
	public Json updateMemberQuestionCashPay(Member member, Question question)
			throws BoException, Exception {
		OrderrQuestion orderrQuestion = new OrderrQuestion(
				null,// Date gmtPay 支付时间
				0,// Integer status 支付状态 default=0  {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
				0,// Integer itypePay 支付方式 default=0 {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
				member.getId(), // Integer memberId 会员
				question.getId(),// Integer 一对一问题ID
				null);
		orderrQuestionService.saveCreate(null, new OrderrQuestion(),
				orderrQuestion, null, null);
		member.setScore(member.getScore() - question.getPrice().intValue());//question.getPrice().intValue() 今币
		memberService.update(null, member);
		CashHis cashHis = new CashHis(
				member.getId(),// Integer 会员id
				-2,// Integer 类型  defalut=0对会员来说正是得先进，负是失现金{'-2':'购买积分','-1':'提现','1':'现金充值','2':‘积分变现','3':'支付宝充值','4':'微信充值'}
				-(int) (question.getPrice() * 10),// Integer 数量  default=0单位分，有正负，正是得，负是失
				orderrQuestion.getId(),// Integer 订单的id default=0 根据类型，指向不同的订单表
				"orderrQuestion",// String 备注说明
				null);
		cashHisService.saveCreate(null, new CashHis(), cashHis, null, null);
		specMagPayService.updateOrderrQuestionSucc(Conf.PAY_FORQUESTION_WX
				+ orderrQuestion.getId());//问题支付成功后
		//支付成功后Question状态改为已支付
		question.setStatus(1);
		question.setGmtPay(new Date());
		questionService.update(null, question);
		Integer Exp=question.getPrice().intValue();//咨询费不应该设置为有小数的 ,如果有小数，那么舍去小数部分再换算成经验   经验和咨询费关系是 1元等于1经验
		//会员增加经验
		member.setExp(member.getExp()+Exp);
		memberService.update(null, member);
		return new Json(true, "scorePaySuccess");
	}

	/**
	 * 观看抢答的会员用积分支付成功
	 * 
	 * @param member
	 * @param quick
	 * @return
	 * @throws Exception
	 * @throws BoException
	 */
	public Json updateMemberCashPay(Member member, Quick quick)
			throws BoException, Exception {
		OrderrQuickview orderrQuickview = new OrderrQuickview(
				null,// Date gmtPay 支付时间
				0,// Integer status 支付状态 default=0  {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
				0,// Integer itypePay 支付方式 default=0 {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
				member.getId(), // Integer 会员
				quick.getId(), // Integer 悬赏ID
				null);
		orderrQuickviewService.saveCreate(null, new OrderrQuickview(),
				orderrQuickview, null, null);
		member.setScore(member.getScore() - (int) (quick.getViewprice() * 10));
		memberService.update(null, member);
		CashHis cashHis = new CashHis(
				member.getId(),// Integer 会员id
				-2,// Integer 类型  defalut=0对会员来说正是得先进，负是失现金{'-2':'购买积分','-1':'提现','1':'现金充值','2':‘积分变现','3':'支付宝充值','4':'微信充值'}
				-(int) (quick.getViewprice() * 10),// Integer 数量  default=0单位分，有正负，正是得，负是失
				orderrQuickview.getId(),// Integer 订单的id default=0 根据类型，指向不同的订单表
				"orderrQuickview",// String 备注说明
				null);
		cashHisService.saveCreate(null, new CashHis(), cashHis, null, null);
		specMagPayService.updateOrderrQuickviewSucc(Conf.PAY_FORQUICKMEMBER_WX
				+ orderrQuickview.getId());
		Integer Exp=quick.getViewprice().intValue();//1元=1经验
		member.setExp(member.getExp()+Exp);
		memberService.update(null, member);
		return new Json(true, "scorePaySuccess");
	}

	/**
	 * 发布悬赏的会员积分支付成功
	 * 
	 * @param member
	 * @param quick
	 * @return
	 * @throws Exception 
	 * @throws BoException 
	 */
	public Json updateMemberQuickCashPay(Member member, Quick quick) throws BoException, Exception {
		OrderrQuick orderrQuick = new OrderrQuick(
				null,// Date gmtPay 支付时间
				0,// Integer status 支付状态 default=0  {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
				0,// Integer itypePay 支付方式 default=0 {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
				member.getId(), // Integer memberId 会员
				quick.getId(), // Integer 悬赏ID
				null);
		orderrQuickService.saveCreate(null, new OrderrQuick(), orderrQuick, null, null);
		//会员积分减少
		member.setScore(member.getScore()-(int)(quick.getPrice()*10));
		memberService.update(null,member);
		CashHis cashHis = new CashHis(
				member.getId(),// Integer 会员id
				-2,// Integer 类型  defalut=0对会员来说正是得先进，负是失现金{'-2':'购买积分','-1':'提现','1':'现金充值','2':‘积分变现','3':'支付宝充值','4':'微信充值'}
				-(int) (quick.getPrice() * 10),// Integer 数量  default=0单位分，有正负，正是得，负是失
				orderrQuick.getId(),// Integer 订单的id default=0 根据类型，指向不同的订单表
				"orderrQuickview",// String 备注说明
				null);
		cashHisService.saveCreate(null, new CashHis(), cashHis, null, null);
		specMagPayService.updateOrderrQuickSucc(Conf.PAY_FORQUICK_WX
				+ orderrQuick.getId());
		//支付成功后，设置支付时间
		quick.setGmtPay(new Date());
		// 支付成功后，更改状态为已支付
		quick.setStatus(1);
		//开始时间小于当前时间说明，悬赏日期是今天 ，所以支付完成后将quick状态由 已支付(1)改为 开始抢答(2)
		//如果开始时间大于当前时间的，会在每天凌晨执行函数判断时候开始抢答
		if (quick.getGmtStart().getTime() < new Date().getTime()) {
			quick.setStatus(2);
		} 
		quickService.update(null, quick);
		//发布悬赏增加经验
		Integer Exp=quick.getPrice().intValue();
		member.setExp(member.getExp()+Exp);
		memberService.update(null, member);
		return new Json(true,"scorePaySuccess");
	}

	/**
	 * 问题支付
	 * 
	 * @param request
	 * @param tokenUser
	 * @param clientInfo
	 * @param question
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public Json saveOrderrQuestionPay(HttpServletRequest request,
			User tokenUser, ClientInfo clientInfo, Question question)
			throws BoException, Exception {
		OrderrQuestion orderrQuestion = new OrderrQuestion(null, // Date 支付时间
				0, // Integer 支付状态 default=0
					// {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
				2, // Integer 支付方式 default=0 {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
				tokenUser.getId(), // Integer 会员
				question.getId(), // Integer 一对一问题ID
				null);
		orderrQuestionService.saveCreate(null, new OrderrQuestion(),
				orderrQuestion, null, null);

		// JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
		String[] ot = versionCheck(tokenUser, clientInfo);
		String openid = ot[0];
		String tradeType = ot[1];
		WaUnifiedPaymentParam waUnifiedPaymentParam = orderrToWaUnifiedPaymentParam(
				orderrQuestion, clientInfo.getIp(), openid, tradeType);
		// 如果是测试用户
		WaJsapiPaymentParam waJsapiPaymentParam = specWxPayService
				.payUnifiedorder(waUnifiedPaymentParam, Conf.BIZID);
		Map<String, Object> ret = new HashMap<String, Object>();
		if (clientInfo.getCli() == null || clientInfo.getCli().intValue() == 0
				|| clientInfo.getCli().intValue() == 3) {
			ret.put("WaJsapiPaymentParam", waJsapiPaymentParam);
		} else if (clientInfo.getCli().intValue() == 1
				|| clientInfo.getCli().intValue() == 2) {
			WaAppapiPaymentParam waAppapiPaymentParam = specWxPayService
					.jsapiToAppapi(waJsapiPaymentParam, Conf.BIZID);
			ret.put("WaAppapiPaymentParam", waAppapiPaymentParam);
		}
		return new Json(true, "wxpay", ret);
	}

	/**
	 * 抢答支付
	 * 
	 * @param request
	 * @param tokenUser
	 * @param clientInfo
	 * @param question
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public Json saveOrderrQuickPay(HttpServletRequest request, User tokenUser,
			ClientInfo clientInfo, Quick quick) throws BoException, Exception {
		OrderrQuick orderrQuick = new OrderrQuick(null, // Date 支付时间
				0, // Integer 支付状态 default=0
					// {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
				2, // Integer 支付方式 default=0 {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
				tokenUser.getId(), // Integer 会员
				quick.getId(), // Integer 抢答问题ID
				null);
		orderrQuickService.saveCreate(null, new OrderrQuick(), orderrQuick,
				null, null);
		// JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
		String[] ot = versionCheck(tokenUser, clientInfo);
		String openid = ot[0];
		String tradeType = ot[1];
		WaUnifiedPaymentParam waUnifiedPaymentParam = orderrToWaUnifiedPaymentParam(
				orderrQuick, clientInfo.getIp(), openid, tradeType);
		// 如果是测试用户
		WaJsapiPaymentParam waJsapiPaymentParam = specWxPayService
				.payUnifiedorder(waUnifiedPaymentParam, Conf.BIZID);
		Map<String, Object> ret = new HashMap<String, Object>();
		if (clientInfo.getCli() == null || clientInfo.getCli().intValue() == 0
				|| clientInfo.getCli().intValue() == 3) {
			ret.put("WaJsapiPaymentParam", waJsapiPaymentParam);
		} else if (clientInfo.getCli().intValue() == 1
				|| clientInfo.getCli().intValue() == 2) {
			WaAppapiPaymentParam waAppapiPaymentParam = specWxPayService
					.jsapiToAppapi(waJsapiPaymentParam, Conf.BIZID);
			ret.put("WaAppapiPaymentParam", waAppapiPaymentParam);
		}
		return new Json(true, "wxpay", ret);
	}

	/**
	 * 观看问题会员支付
	 * 
	 * @param request
	 * @param tokenUser
	 * @param clientInfo
	 * @param question
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public Json saveOrderrQuestionviewPay(HttpServletRequest request,
			User tokenUser, ClientInfo clientInfo, Question question)
			throws BoException, Exception {
		if(clientInfo.getCli().intValue()!=3){
			throw new BoException("微信支付，请从微信进入！");
		}
		OrderrQuestionview orderrQuestionview = new OrderrQuestionview(null, // Date
																				// 支付时间
				0, // Integer 支付状态 default=0
					// {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
				2, // Integer 支付方式 default=0 {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
				tokenUser.getId(), // Integer 会员
				question.getId(), // Integer 一对一问题ID
				null);
		orderrQuestionviewService.saveCreate(null, new OrderrQuestionview(),
				orderrQuestionview, null, null);

		// JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
		String[] ot = versionCheck(tokenUser, clientInfo);
		String openid = ot[0];
		String tradeType = ot[1];
		WaUnifiedPaymentParam waUnifiedPaymentParam = orderrToWaUnifiedPaymentParam(
				orderrQuestionview, RequestUtils.getIpAddr(request), openid,
				tradeType);
		WaJsapiPaymentParam waJsapiPaymentParam = specWxPayService
				.payUnifiedorder(waUnifiedPaymentParam, Conf.BIZID);
		Map<String, Object> ret = new HashMap<String, Object>();
		if (clientInfo.getCli() == null || clientInfo.getCli().intValue() == 0
				|| clientInfo.getCli().intValue() == 3) {
			ret.put("WaJsapiPaymentParam", waJsapiPaymentParam);
		} else if (clientInfo.getCli().intValue() == 1
				|| clientInfo.getCli().intValue() == 2) {
			WaAppapiPaymentParam waAppapiPaymentParam = specWxPayService
					.jsapiToAppapi(waJsapiPaymentParam, Conf.BIZID);
			ret.put("WaAppapiPaymentParam", waAppapiPaymentParam);
		}
		return new Json(true, "wxpay", ret);
	}

	/**
	 * 观看抢答会员支付
	 * 
	 * @param request
	 * @param tokenUser
	 * @param clientInfo
	 * @param question
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public Json saveOrderrQuickviewPay(HttpServletRequest request,
			User tokenUser, ClientInfo clientInfo, Quick quick)
			throws BoException, Exception {
		OrderrQuickview orderrQuickview = new OrderrQuickview(null, // Date 支付时间
				0, // Integer 支付状态 default=0
					// {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
				2, // Integer 支付方式 default=0 {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
				tokenUser.getId(), // Integer 会员
				quick.getId(), // Integer 抢答问题ID
				null);
		orderrQuickviewService.saveCreate(null, new OrderrQuickview(),
				orderrQuickview, null, null);
		// JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
		String[] ot = versionCheck(tokenUser, clientInfo);
		String openid = ot[0];
		String tradeType = ot[1];
		WaUnifiedPaymentParam waUnifiedPaymentParam = orderrToWaUnifiedPaymentParam(
				orderrQuickview, clientInfo.getIp(), openid, tradeType);
		// 如果是测试用户
		WaJsapiPaymentParam waJsapiPaymentParam = specWxPayService
				.payUnifiedorder(waUnifiedPaymentParam, Conf.BIZID);
		Map<String, Object> ret = new HashMap<String, Object>();
		if (clientInfo.getCli() == null || clientInfo.getCli().intValue() == 0
				|| clientInfo.getCli().intValue() == 3) {
			ret.put("WaJsapiPaymentParam", waJsapiPaymentParam);
		} else if (clientInfo.getCli().intValue() == 1
				|| clientInfo.getCli().intValue() == 2) {
			WaAppapiPaymentParam waAppapiPaymentParam = specWxPayService
					.jsapiToAppapi(waJsapiPaymentParam, Conf.BIZID);
			ret.put("WaAppapiPaymentParam", waAppapiPaymentParam);
		}
		return new Json(true, "wxpay", ret);
	}

	/**
	 * 商品订单转微信参数 4 后台根据订单OrderrXxx生成WaUnifiedPaymentParam 微信统一下单请求对象
	 * 订单转微信统一下单请求对象
	 * 
	 * @param orderr
	 * @param spbillCreateIp
	 *            客户端ip
	 * @param openid
	 * @param tradeType
	 *            交易类型：JSAPI，NATIVE，APP，WAP,默认 JSAPI
	 * @return WaUnifiedPaymentParam 微信统一下单请求对象
	 * @throws BoException
	 * @throws Exception
	 */
	private WaUnifiedPaymentParam orderrToWaUnifiedPaymentParam(Object obj,
			String spbillCreateIp, String openid, String tradeType)
			throws BoException, Exception {
		tradeType = (StringUtils.isBlank(tradeType)) ? "JSAPI" : tradeType;
		String title = "";
		String body = "";// 商品或者支付单简要说明
		String detail = "";
		String outTradeNo = "";
		Integer totalFee = 1;// 默认1分
		if (obj instanceof OrderrQuestion) {
			OrderrQuestion orderrQuestion = (OrderrQuestion) obj;
			title = (orderrQuestion.getTitle() == null) ? "" : orderrQuestion
					.getTitle();
			body = "实付" + orderrQuestion.getPrice() + "元";
			outTradeNo = Conf.PAY_FORQUESTION_WX
					+ String.valueOf(orderrQuestion.getId());
			totalFee = (int) (orderrQuestion.getPrice() * 10);
		}
		if (obj instanceof OrderrQuick) {
			OrderrQuick orderrQuick = (OrderrQuick) obj;
			title = (orderrQuick.getTitle() == null) ? "" : orderrQuick
					.getTitle();
			body = "实付" + orderrQuick.getPrice() + "元";
			outTradeNo = Conf.PAY_FORQUICK_WX
					+ String.valueOf(orderrQuick.getId());
			totalFee = (int) (orderrQuick.getPrice() * 10);
		}
		if (obj instanceof OrderrQuestionview) {
			OrderrQuestionview orderrQuestionview = (OrderrQuestionview) obj;
			title = (orderrQuestionview.getTitle() == null) ? ""
					: orderrQuestionview.getTitle();
			body = "实付" + orderrQuestionview.getPrice() + "元";
			outTradeNo = Conf.PAY_FORQUESTIONMEMBER_WX
					+ String.valueOf(orderrQuestionview.getId());
			totalFee = (int) (orderrQuestionview.getPrice() * 10);
		}
		if (obj instanceof OrderrQuickview) {
			OrderrQuickview orderrQuickview = (OrderrQuickview) obj;
			title = (orderrQuickview.getTitle() == null) ? "" : orderrQuickview
					.getTitle();
			body = "实付" + orderrQuickview.getPrice() + "元";
			outTradeNo = Conf.PAY_FORQUICKMEMBER_WX
					+ String.valueOf(orderrQuickview.getId());
			totalFee = (int) (orderrQuickview.getPrice() * 10);
		}
		// 代金券的接收微信支付异步通知回调地址http://lytb.laiyuanw.com/lytb/pub/wx/wxpaynotify
		String notifyUrl = propSys.getDomain() + "/mag/pub/wx/wxpaynotify";
		logger.debug("notifyUrl=" + notifyUrl);
		WaUnifiedPaymentParam waUnifiedPaymentParam = new WaUnifiedPaymentParam(
				null, // String 公众账号ID 微信分配的公众账号ID
				null, // String 商户号 微信支付分配的商户号
				null, // String 设备号 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
				null, // String 随机字符串 随机字符串，不长于32位
				null, // String 签名 签名
				body, // String 商品描述 商品或支付单简要描述
				detail, // String 商品详情 商品名称明细列表
				null, // String 附加数据 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
				outTradeNo, // String 商户订单号 商户系统内部的订单号,32个字符内、可包含字母,
				null, // String 货币类型 default=CNY 符合ISO 4217标准的三位字母代码，默认人民币：CNY，
				totalFee, // Integer 总金额 订单总金额，只能为整数
				spbillCreateIp, // String 终端IP
								// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
				null, // String 交易起始时间
						// 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。
				null, // String 交易结束时间
						// 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。
				null, // String 商品标记 商品标记，代金券或立减优惠功能的参数，
				notifyUrl, // String 通知地址 接收微信支付异步通知回调地址
				tradeType, // String 交易类型 取值如下：JSAPI，NATIVE，APP，WAP,
				null, // String 商品ID
				openid, // String 用户标识
				null);
		return waUnifiedPaymentParam;
	}

	/**
	 * app/h5版本检查
	 * 
	 * @param tokenUser
	 * @param clientInfo
	 * @return new String[]{openid,tradeType}
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	private String[] versionCheck(User tokenUser, ClientInfo clientInfo)
			throws BoException, Exception {
		// app支付的时候，不需要openid
		// StringUtils.isBlank(clientInfo.getOpenid())
		if (tokenUser == null) {
			throw new BoException("不是平台用户");
		}
		String openid = null;
		Wxouser wxouser = (Wxouser) wxouserService.get(null,
				clientInfo.getOpenid());
		if (clientInfo.getCli() != null
				&& (clientInfo.getCli().intValue() == 0 || clientInfo.getCli()
						.intValue() == 3) && wxouser == null) {
			throw new BoException("微信用户找不到");
		}
		if (wxouser != null)
			openid = wxouser.getId();

		// JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
		String tradeType = "JSAPI";
		if (clientInfo.getCli() != null
				&& (clientInfo.getCli().intValue() == 1 || clientInfo.getCli()
						.intValue() == 2))
			tradeType = "APP";
		return new String[] { openid, tradeType };
	}

	/**
	 * 问题支付成功后
	 * 
	 * @param outTradeNo
	 * @throws BoException
	 * @throws Exception
	 */
	public void updateOrderrQuestionSucc(String outTradeNo) throws BoException,
			Exception {
		if (StringUtils.isBlank(outTradeNo) || outTradeNo.length() < 5)
			throw new BoException("问题订单号错误");
		String sid = outTradeNo.substring(4, outTradeNo.length());
		Integer orderrQuestionId = Integer.parseInt(sid);
		OrderrQuestion orderrQuestion = orderrQuestionService.get(null,
				orderrQuestionId);
		if (orderrQuestion == null)
			throw new BoException("问题订单(" + outTradeNo + "," + orderrQuestionId
					+ ")丢失，请与管理员系统");
		orderrQuestion.setStatus(2);
		orderrQuestion.setGmtPay(new Date());
		orderrQuestionService.update(null, orderrQuestion);
	}

	/**
	 * 抢答支付成功后
	 * 
	 * @param outTradeNo
	 * @throws BoException
	 * @throws Exception
	 */
	public void updateOrderrQuickSucc(String outTradeNo) throws BoException,
			Exception {
		if (StringUtils.isBlank(outTradeNo) || outTradeNo.length() < 5)
			throw new BoException("抢答订单号错误");
		String sid = outTradeNo.substring(4, outTradeNo.length());
		Integer orderrQuickId = Integer.parseInt(sid);
		OrderrQuick orderrQuick = orderrQuickService.get(null, orderrQuickId);
		if (orderrQuick == null)
			throw new BoException("抢答订单(" + outTradeNo + "," + orderrQuickId
					+ ")丢失，请与管理员系统");
		orderrQuick.setStatus(2);
		orderrQuick.setGmtPay(new Date());
		orderrQuickService.update(null, orderrQuick);
	}

	/**
	 * 问题观看会员支付成功后
	 * 
	 * @param outTradeNo
	 * @throws BoException
	 * @throws Exception
	 */
	public void updateOrderrQuestionviewSucc(String outTradeNo)
			throws BoException, Exception {
		if (StringUtils.isBlank(outTradeNo) || outTradeNo.length() < 5)
			throw new BoException("问题会员订单号错误");
		String sid = outTradeNo.substring(4, outTradeNo.length());
		Integer orderrQuestionviewId = Integer.parseInt(sid);
		OrderrQuestionview orderrQuestionview = orderrQuestionviewService.get(
				null, orderrQuestionviewId);
		if (orderrQuestionview == null)
			throw new BoException("问题会员订单(" + outTradeNo + ","
					+ orderrQuestionviewId + ")丢失，请与管理员系统");
		orderrQuestionview.setStatus(2);
		orderrQuestionview.setGmtPay(new Date());
		orderrQuestionviewService.update(null, orderrQuestionview);
		QuestionLinkMemberView questionLinkMemberView = new QuestionLinkMemberView(
				orderrQuestionview.getQuestionId(), // Integer 问题内序号
				orderrQuestionview.getMemberId(), // Integer 观看者
				0, // Integer 评论情况 {'0':'以后再评','1':'好评','-1':'差评'}
				null);
		questionLinkMemberViewService.saveCreate(null,
				new QuestionLinkMemberView(), questionLinkMemberView, null,
				null);
	}

	/**
	 * 问题观看会员支付成功后
	 * 
	 * @param outTradeNo
	 * @throws BoException
	 * @throws Exception
	 */
	public void updateOrderrQuickviewSucc(String outTradeNo)
			throws BoException, Exception {
		if (StringUtils.isBlank(outTradeNo) || outTradeNo.length() < 5)
			throw new BoException("抢答会员订单号错误");
		String sid = outTradeNo.substring(4, outTradeNo.length());
		Integer orderrQuickviewId = Integer.parseInt(sid);
		OrderrQuickview orderrQuickview = orderrQuickviewService.get(null,
				orderrQuickviewId);
		if (orderrQuickview == null)
			throw new BoException("抢答会员订单(" + outTradeNo + ","
					+ orderrQuickviewId + ")丢失，请与管理员系统");
		orderrQuickview.setStatus(2);
		orderrQuickview.setGmtPay(new Date());
		orderrQuickviewService.update(null, orderrQuickview);
		QuickLinkMemberView quickLinkMemberView = new QuickLinkMemberView(
				orderrQuickview.getQuickId(), // Integer 抢答ID
				orderrQuickview.getMemberId(), // Integer 观看者
				null);
		quickLinkMemberViewService.saveCreate(null, new QuickLinkMemberView(),
				quickLinkMemberView, null, null);
	}
}
