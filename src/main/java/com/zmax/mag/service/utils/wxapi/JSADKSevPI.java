package com.zmax.mag.service.utils.wxapi;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.zmax.common.utils.file.localhttp.LocalHttpClient;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.bean.wx.*;



public class JSADKSevPI extends BaseAPI{
	
	public static JsapiTicket ticketGetticket(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/ticket/getticket")
				.addParameter("access_token",access_token)
				.addParameter("type", "jsapi")
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,JsapiTicket.class);
	}
}
