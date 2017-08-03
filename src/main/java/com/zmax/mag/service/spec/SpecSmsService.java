package com.zmax.mag.service.spec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zmax.common.exception.BoException;
import com.zmax.mag.domain.conf.Conf;


/**
 * 云片短信验证service
 * 
 * @author zmax
 *
 */
@Service
public class SpecSmsService {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());	
	/**手机号:验证码键值对 <mobile,code>*/
	public static final ConcurrentHashMap<String, String> codeMap = new ConcurrentHashMap<>();

	/**云片短信APIKEY*/
	public static final String SMS_SERVER_APIKEY = "a0e2c28fff98237fb242605784a5e463";
	
	/**云片短信接口*/
	public static final String SMS_SERVER_URI = "http://yunpian.com/v1/sms/send.json";
	/**云片语音验证码接口*/
	public static final String VOICE_SERVER_URI = "http://yunpian.com/v1/voice/send.json";
	
	/**
	 * 发验证码
	 * @param mobile
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
		logger.debug("验证码: " + code);
		codeMap.put(mobile, code);
		sendSms(mobile, "【易健医疗】验证码" + code + "，此码用于用户登录，15分钟内有效。");
	}
	/**
	 * 请求语音验证码
	 * @param mobile
	 * @throws BoException
	 * @throws Exception
	 */
	public void requestVoice(String mobile) throws BoException, Exception {
		if (StringUtils.isBlank(mobile))
			throw new BoException("手机号为空");
		if(mobile.indexOf(Conf.TESTUSER)==0){
			//测试手机，code是8888
			codeMap.put(mobile, "8888");
			return;
		}
		String code = codeMap.get(mobile);
		if (code == null) {
			code = RandomStringUtils.randomNumeric(4);
			codeMap.put(mobile, code);
		}
		sendVoice(mobile, code);
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
	/**
	 * 通过云片发送验证码
	 * @param mobile 手机号码
	 * @param text 短信内容
	 * @throws BoException
	 */
	private void sendSms(String mobile, String text) throws BoException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("apikey", SMS_SERVER_APIKEY);
		params.put("text", text);
		params.put("mobile", mobile);
		
		JSONObject response;
		try {
			response = JSON.parseObject(post(SMS_SERVER_URI, params));
		} catch (Exception e) {
			throw new BoException("验证码发送失败，请稍后再试");
		}
		logger.debug("短信接口返回: " + response.toJSONString());
	}
	/**
	 * 通过云片发送语音验证码
	 * @param mobile
	 * @param code
	 * @return
	 * @throws IOException
	 */
	 private void sendVoice(String mobile, String code) throws BoException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", SMS_SERVER_APIKEY);
        params.put("mobile", mobile);
        params.put("code", code);
		
		JSONObject response;
		try {
			response = JSON.parseObject(post(VOICE_SERVER_URI, params));
		} catch (Exception e) {
			throw new BoException("语音验证码发送失败，请稍后再试");
		}
		logger.debug("语音接口返回: " + response.toJSONString());
		int retCode = response.getInteger("code");
		if (retCode != 0) {
			throw new BoException("验证码发送中，请稍后再试");
		}
    }

	/**
	 * 发送http post请求
	 * @param url
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	private String post(String url, Map<String, String> paramsMap) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		HttpPost method = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			if (paramsMap != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> param : paramsMap.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity);
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return responseText;
	}
}
