package com.zmax.mag.web.controller.restful;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zmax.common.conf.AttrStatic;
import com.zmax.common.exception.BoException;
import com.zmax.common.exception.NeedLoginException;
import com.zmax.common.exception.RightException;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.utils.AESUtil;
import com.zmax.mag.web.controller.restful.entity.ClientInfo;
import com.zmax.mag.web.controller.restful.entity.RestPage;


/**
 * RestFul控制 
 * 企业
 * 
 *
 */
@Component
public class RestUtils {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	RestClazzUtils restClazzUtils;



	/**
	 * Token检查，根据Token创建Token用户
	 * @param clientInfo
	 * @param restPage
	 * @param obj
	 * @param funcName
	 * @return User
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public User checkAccessToken(ClientInfo clientInfo,RestPage restPage,Object obj,String funcName) throws BoException, NeedLoginException, RightException, Exception{
		//if(funcName.equals("del")) throw new Exception("not allowed");
		if(restClazzUtils.isNeedToken(obj,funcName)){
			if(StringUtils.isBlank(clientInfo.getToken()))
				throw new NeedLoginException(); 
			User tokenUser=null;
			try {
				tokenUser = AESUtil.getInstance().decryptUser(clientInfo.getToken());
			} catch (Exception e) {
				throw new NeedLoginException();
			}
			if(tokenUser==null)
				throw new NeedLoginException();
			if(StringUtils.isNotBlank(clientInfo.getOpenid()))
				tokenUser.setOpenid(clientInfo.getOpenid());
			return tokenUser;
		}else{
			User tokenUser=AESUtil.getInstance().decryptUser(clientInfo.getToken());
			if(tokenUser==null){
				//不需要TOKEN的话，就相当于管理员了。
				//精简构造 账号信息修改
				User admin = new User(
						1 , //Integer 状态 default=0  {'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'}
						0 , //Integer 角色 default=0 这里如果改变，要变的包括：权限表，wxr {'0':'超管','1':'一般管理员','5':'会员'}
						null , //String 账号  唯一值 
						null , //String 密码   
						null , //String 昵称   
						null , //Integer 隶属于   
						null , //String 微信openid  以微信用户表为主，此字段是冗余字段 
						null
					);
				return admin;
			}else{
				return tokenUser;
			}
		}
	}
	/**
	 * Token检查，根据Token检查是否拥有用户
	 * @param clientInfo
	 * @param restPage
	 * @param obj
	 * @param funcName
	 * @return User
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public User checkAccessTokenNoLogin(ClientInfo clientInfo,RestPage restPage,Object obj,String funcName) throws BoException, NeedLoginException, RightException, Exception{
		User tokenUser=null;
		try {
			logger.debug("clientInfo.getToken()="+clientInfo.getToken()+",ip="+clientInfo.getIp());
			tokenUser = AESUtil.getInstance().decryptUser(clientInfo.getToken());
		} catch (Exception e) {
			logger.debug("tokenUser is null,ip="+clientInfo.getIp());
			return tokenUser;
		}
		return tokenUser;
	}
	/**
	 * Token检查，根据Token创建Token用户，不抛错版
	 * @param clientInfo
	 * @param restPage
	 * @param obj
	 * @param funcName
	 * @return
	 */
	public User checkAccessTokenNoException(ClientInfo clientInfo,RestPage restPage,Object obj,String funcName) {
		if(funcName.equals("del"))
			return null;
		if(restClazzUtils.isNeedToken(obj,funcName)){
			if(StringUtils.isBlank(clientInfo.getToken()))
				return null; 
			User tokenUser=null;
			try {
				tokenUser = AESUtil.getInstance().decryptUser(clientInfo.getToken());
			} catch (Exception e) {
				return null; 
			}
			if(tokenUser==null)
				return null; 
			if(StringUtils.isNotBlank(clientInfo.getOpenid()))
				tokenUser.setOpenid(clientInfo.getOpenid());
			return tokenUser;
		}
		return null; 
	}
	
}
