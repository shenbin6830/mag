package com.zmax.mag.service.spec;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.taobao.api.ApiException;
//import com.taobao.api.DefaultTaobaoClient;
//import com.taobao.api.TaobaoClient;
//import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
//import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.zmax.common.exception.BoException;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.domain.conf.PropMe;
import com.zmax.mag.service.my.*;
/**
 * 阿里大于短信service
 * @author Zhf
 *
 */
@Service
public class SpecAliSmsService {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	/**阿里大于短信接口*/
	public static final String Ali_SMS_SERVER_URI = "http://gw.api.taobao.com/router/rest";
	/**阿里大于语音验证码接口*/
	public static final String Ali_VOICE_SERVER_URI = "http://yunpian.com/v1/voice/send.json";
	/**阿里大于短信APIKEY*/
	public static final String Ali_SMS_SERVER_APIKEY = "23782428";
	/**阿里大于短信SECRET*/
	public static final String Ali_SMS_SERVER_SECRET = "fbb10b4290c29b4e2744ca8565622aff";

	/**手机号:验证码键值对 <mobile,code> 线程安全map*/
	public static final ConcurrentHashMap<String, String> codeMap = new ConcurrentHashMap<>();
	
	@Autowired
	PropMe propMe;
	@Autowired
	MemberService memberService;
	@Autowired
	UserService userService;
	
	/**
	 * 短信验证码发送
	 * @param mobile
	 * @throws BoException
	 * @throws Exception
	 */
	public void requestCode(String mobile) throws BoException,Exception{
		//以后做下有效检查，目前使用对方服务器的检查
		if (StringUtils.isBlank(mobile))
			throw new BoException("手机号为空");
		if(mobile.indexOf(Conf.TESTUSER)==0){
			//测试手机，code是8888
			codeMap.put(mobile, "8888");
			return;
		}	
		String code = RandomStringUtils.randomNumeric(4);
		if (logger.isDebugEnabled())
			logger.debug("验证码: " + code);
		codeMap.put(mobile, code);
		//sendSms(mobile,code);
	}
	/**
	 * 通过阿里大于发送短信验证码
	 * @param mobile
	 * @param code
	 * @throws ApiException
	 */
	private void sendSms(String mobile,String code) {
		//TaobaoClient client = new DefaultTaobaoClient(Ali_SMS_SERVER_URI,Ali_SMS_SERVER_APIKEY,Ali_SMS_SERVER_SECRET);
		//AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setExtend("A"+mobile); //UserId
//		req.setSmsType("normal");//短信类型，传入值请填写normal
//		req.setSmsFreeSignName("点今文化");//短信签名 需要通过审核 短信效果示例：【阿里大于】
//		req.setSmsParamString("{\"code\":\""+code+"\",\"product\":\"会员\"}");
//		req.setRecNum(mobile);
//		req.setSmsTemplateCode("SMS_63765647");//短信接入模板
//		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		if (logger.isDebugEnabled())
			logger.debug("阿里大于接口返回："+"rsp.getBody()");
		
	}
	/**
	 * 通过阿里大于发送短信通知患者
	 * 两种情况
	 * 1 ${doctor}医生给您开了药笺,请打开${app}查看,可关注公众号或在各应用市场下载  SMS_53925302
	 * 2  您的订单（订单号：${sn}）交易已成功，欢迎使用${myapp} SMS_54905002
	 * @param mobile
	 * @param code
	 * @throws ApiException
	 */
	private void sendSmsForOrderr(String mobile,String templateCode,String paramString)  {
//		TaobaoClient client = new DefaultTaobaoClient(Ali_SMS_SERVER_URI,Ali_SMS_SERVER_APIKEY,Ali_SMS_SERVER_SECRET);
//		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setExtend("O"+mobile); //Orderr
//		req.setSmsType("normal");//短信类型，传入值请填写normal
//		req.setSmsFreeSignName(propMe.getAliSmsFreeSignName());//短信签名 需要通过审核 短信效果示例：【阿里大于】
//		req.setSmsParamString(paramString);
//		req.setRecNum(mobile);//收到短信的手机号码
//		req.setSmsTemplateCode(templateCode);//短信接入模板
//		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		if (logger.isDebugEnabled())
			logger.debug("阿里大于接口返回："+"rsp.getBody()");
	}

	/**
	 * 检查码
	 * @param mobile 手机号
	 * @param code 验证码
	 */
	public boolean checkCode(String mobile,String code) throws BoException,Exception{
		if (StringUtils.isBlank(mobile))
			throw new BoException("手机号为空");
		if(!codeMap.containsKey(mobile))
			throw new BoException("手机验证码已经失效");
		if (!codeMap.remove(mobile, code))
			throw new BoException("验证码错误");
		return true;
	}
}
