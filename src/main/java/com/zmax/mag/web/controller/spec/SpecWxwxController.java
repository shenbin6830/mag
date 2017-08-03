package com.zmax.mag.web.controller.spec;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zmax.common.exception.BoException;
import com.zmax.common.exception.OpenWeixinQQException;
import com.zmax.common.utils.string.XMLConverUtil;
import com.zmax.common.web.utils.RequestUtils;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.domain.conf.Rt;
import com.zmax.mag.service.spec.SpecWxPayService;
import com.zmax.mag.service.spec.SpecWxService;
import com.zmax.common.entity.ExpireSet;
import com.zmax.mag.service.utils.MyUtils;
import com.zmax.mag.service.utils.wxapi.SignatureUtil;
import com.zmax.mag.web.controller.page.base.BaseController;
/**
 * 给微信平台用的Controller，比如后台消息，通知，支付通知等
 * <br>
 * <b>类描述:</b>
 * <pre>
 * 包括授权GET，用户向微信发消息POST
 * </pre>
 * 
 * @see
 * @since
 */
@Controller
@RequestMapping("/")
public class SpecWxwxController extends BaseController{
	/**日志实例*/
	private static final Logger logger = Logger.getLogger(SpecWxwxController.class);
	/**重复通知过滤  时效60秒*/ 
	private static ExpireSet<String> expireSet = new ExpireSet<String>(60);
	
	/**平台id，用于单用户，多微信用户的路径是/pub/wx/{bizId}/xxxx，同时参数带@PathVariable Integer bizId*/
	private Integer bizId=Conf.BIZID;
	
	@Autowired
	SpecWxService specWxService;
	@Autowired
	SpecWxPayService specWxPayService;

	/**
	 * 数据流输出
	 * @param outputStream
	 * @param text
	 * @return
	 */
	private boolean outputStreamWrite(HttpServletResponse response,String text){
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(text.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 接受消息 get是用于腾讯检查，POST是用于接受用户消息
	 * 
	 * 登录检查 这个地址是需要在公众后台注册过的。
	 * 多微信用户的路径是/pub/wx/{bizId}/wxmsg
	 * @param request
	 * @param response
	 * @param model
	 * @param signature  微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。  
	 * @param timestamp  时间戳  
	 * @param nonce  随机数  
	 * @param echostr  随机字符串  
	 * @param agentId 商家的user.id
	 */
	@RequestMapping(value="/pub/wx/wxmsg",method=RequestMethod.GET)
	public void verify(HttpServletRequest request,HttpServletResponse response,ModelMap model,String signature,String timestamp,String nonce,String echostr){
		
		try {
			Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
			if (logger.isDebugEnabled())
				logger.debug("收到 msg agentId="+bizId+",signature="+signature+",timestamp="+timestamp+",nonce="+nonce+",echostr="+echostr);
			ServletInputStream inputStream = request.getInputStream();
			logger.debug("inputStream"+inputStream);
			//首次请求申请验证,返回echostr
			if(echostr!=null){
				logger.info("首次验证，返回"+echostr);
				outputStreamWrite(response,echostr);
				//return;
			}
			if(signature==null || timestamp==null || nonce==null){
				logger.error("验签信息空");
				return;
			}
			//验证请求签名
			if(!signature.equals(SignatureUtil.generateEventMessageSignature(wxcfg.getToken(),timestamp,nonce))){
				logger.error("验签失败,如果您刚刚修改【微信配置】中的token，需要参数重载才能生效");
				return;
			}
			logger.info("验签成功");
		} catch (Exception e) {
			logger.error(e);
		}

	}
	/**
	 * 接受消息 get是用于腾讯检查，POST是用于接受用户消息
	 * 接收用过发来的消息，消息转成WaRecvmsg后类型与事件包括：
	 * msgtype 消息类型 String   {'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'}
	 * event 事件类型 String   {'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'}
	 * 多微信用户的路径是/pub/wx/{bizId}/wxmsg
	 * @param request
	 * @param response
	 * @param model
	 * @param signature  微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。  
	 * @param timestamp  时间戳  
	 * @param nonce  随机数  
	 * @param echostr  随机字符串  
	 * @param agentId
	 */
	@RequestMapping(value="/pub/wx/wxmsg",method=RequestMethod.POST)
	public void msg(HttpServletRequest request,HttpServletResponse response,ModelMap model,String signature,String timestamp,String nonce){
		
		try {
			//获取WX配置信息
			Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
			if (logger.isDebugEnabled())
				logger.debug("收到 msg agentId="+bizId+",signature="+signature+",timestamp="+timestamp+",nonce="+nonce);
			ServletInputStream inputStream = request.getInputStream();
			logger.debug("inputStream="+inputStream);
			if(signature==null || timestamp==null || nonce==null){
				logger.error("验签信息空");
				return ;//"redirect:"+wxcfg.getWwwindex()+agentId;
			}
			//验证请求签名
			if(!signature.equals(SignatureUtil.generateEventMessageSignature(wxcfg.getToken(),timestamp,nonce))){
				if (logger.isDebugEnabled())
					logger.debug(""+wxcfg.getToken()+","+timestamp+","+nonce+"!="+signature);
				logger.error("验签失败,如果您刚刚修改【微信配置】中的token，需要参数重载才能生效");
				return ;//"redirect:"+wxcfg.getWwwindex()+agentId;
			}

			if(inputStream!=null){
				//转换XML
				WaRecvmsg waRecvmsg = XMLConverUtil.convertToObject(WaRecvmsg.class,inputStream);
				if(waRecvmsg==null){
					return;
				}
				String expireKey = waRecvmsg.toString();
				if(expireSet.contains(expireKey)){
					//重复通知不作处理
					return ;//"redirect:"+wxcfg.getWwwindex()+agentId;
				}else{
					expireSet.add(expireKey);
				}
				logger.info("收到:"+RequestUtils.getIpAddr(request)+","+waRecvmsg.toString());
				String xml=specWxService.saveSgAndRespAuto(waRecvmsg,bizId);		
				if (logger.isDebugEnabled()){
					logger.debug("msg 回复="+xml);
				}
				if(StringUtils.isNotBlank(xml))
					outPrint(response, xml);
				return;
			}
			outputStreamWrite(response,"");
		} catch (BoException e) {
			logger.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return ;
	}
	/**
	 * 支付回调,wxcfg中有配置，支付完后，微信会执行这个函数
	 * 用于处理各类订单的 orderr和orderrdetail的状态修改
	 * 多微信用户的路径是/pub/wx/{bizId}/wxpaynotify
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value="/pub/wx/wxpaynotify")
	public void wxpaynotify(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		if (logger.isDebugEnabled())
			logger.debug("wxpaynotify");
		try {
			//获取请求数据
			WaGeneralNoticeRet waGeneralNoticeRet = XMLConverUtil.convertToObject(WaGeneralNoticeRet.class, request.getInputStream());
			if (logger.isDebugEnabled()){
				logger.debug("wxnotify waGeneralNoticeRet.toString()="+ waGeneralNoticeRet.toString());
				//logger.debug("wxnotify generalNotice.getOut_trade_no()="+ generalNotice.getOut_trade_no());
				logger.debug("wxnotify return sign="+ waGeneralNoticeRet.getSign());
			}
			Wxcfg wxcfg=MyUtils.takeWxcfg(bizId);
			//签名验证
			String signCalcu=SignatureUtil.signWaGeneralNoticeRet(waGeneralNoticeRet, wxcfg.getPaykey());
			if(!signCalcu.equals(waGeneralNoticeRet.getSign())){
				logger.debug("wxnotify validateAppSignature=error");
				outPrint(response, "error");
				return;
			}else{
				logger.debug("wxnotify validateAppSignature=success");
				outPrint(response, "success");
			}
			specWxPayService.payNotify(waGeneralNoticeRet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			outPrint(response, "error");
			e.printStackTrace();
		}

	}
	/**
	 * 已关注用户从微信首页点击菜单或链接进入到此_会员首页
	 * 测试：http://ddd.xxx.com/xxx/pub/wx/index.html?code=10123456789
	 * @param request
	 * @param response
	 * @param model
	 * @param code QQ传来的code,可以用于获取openid，如果是test101234nnn则是测试号传过来的测试
	 * @param act home:#/tab/home ;
	 * @param tb 是trunk 还是 branch
	 * @param token 如果不空，则进行userId绑定
	 */
	@RequestMapping(value="/pub/wx/index")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model,String code,String act,String tb,String token){	
		try {
			if (logger.isDebugEnabled())
				logger.debug("index:获取openid关注版_收到bizId="+bizId+",code="+code);
			Wxouser wxouser=wxouserByCode(code);
			//由于tokenUser和微信用户登录的角色不一定匹配，不再进行userId绑定(在登录和注册时进行绑定)
			/*	if(StringUtils.isNotBlank(token)){
				try {
					User tokenUser = AESUtil.getInstance().decryptUser(token);
					specWxService.updateOpenidUserIdBind(tokenUser.getId(),wxouser.getId(), tokenUser.getRoleId());
				} catch (Exception e) {

				}
				}*/
			model.addAttribute("wxouser", wxouser);
			//      #/tab/OrgList
			String tabact="";
			if(StringUtils.isNotBlank(act)){
				tabact="#/tab/"+act;
			}
			model.addAttribute("act", tabact);
			model.addAttribute("tb", tb);

			return "/pages/pub/wx/index";
		} catch (OpenWeixinQQException e) {
			e.printStackTrace();
			return errm(model,e.getMessage());
		} catch (BoException e) {
			e.printStackTrace();
			return errm(model,e.getMessage());
		}catch (Exception e) {
			return errm(model,"服务器正忙");
		}
	}	
	/**
	 * 通过code获取Wxouser
	 * @param code
	 * @return Wxouser 有可能是null
	 * @throws OpenWeixinQQException
	 * @throws BoException
	 * @throws Exception
	 */
	private Wxouser wxouserByCode(String code) throws OpenWeixinQQException, BoException, Exception{
		Wxouser wxouser=Rt.mapCw.get(code);
		if(wxouser==null){
			wxouser= specWxService.saveWxouserFullAttation(code, bizId);
			if(wxouser!=null){
				wxouser.setObj1(new Date());
				synchronized(Rt.mapCw){
					Rt.mapCw.put(code, wxouser);
				}				
				if (logger.isDebugEnabled())
					logger.debug("index:获取openid关注版_收到bizId="+bizId+",code="+code+",wxouser="+wxouser.getId()+","+wxouser.getOpenid());
			}else{
				if (logger.isDebugEnabled())
					logger.debug("wxouser==null");
			}
		}else{
			if (logger.isDebugEnabled())
				logger.debug("wxouser in mapCw");
		}
		return wxouser;
	} 
}
