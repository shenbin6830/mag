/*
 * CKFinder
 * ========
 * http://ckfinder.com
 * Copyright (C) 2007-2012, CKSource - Frederico Knabben. All rights reserved.
 *
 * The software, this file and its contents are subject to the CKFinder
 * License. Please read the license.txt file before using, installing, copying,
 * modifying or distribute this file or part of its contents. The contents of
 * this file is part of the Source Code of CKFinder.
 */
package com.ckfinder.connector.configuration;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ckfinder.connector.data.ResourceType;
import com.ckfinder.connector.utils.PathUtils;
import com.zmax.common.conf.SessionName;




/**
 * Path builder that creates default values of baseDir and baseURL.
 */
public class ZresPathUtil {

	
	
	/**
	 * 检查资源，如果第一次使用，创建目录
	 * @param request
	 * @param conf
	 * @throws Exception
	 */
	public static void init(final HttpServletRequest request) throws Exception {
		ZresConfiguration conf=ZresConfiguration.getInstace();
		File file = new File(Constants.BASE_DIR);
		if (!file.exists() && request.getParameter("command").equals("Init")) {
			file.mkdirs();
		}
		for (ResourceType item : conf.getTypes().values()) {
			file = new File(item.getPath(request));
			if (!file.exists() && request.getParameter("command").equals("Init")) {
				file.mkdirs();
			}
		}

	}
	/**
	 * 根据Session获取相对路径，
	 * @param request
	 * @return 管理员"/",用户 "/users/${userId}/",出错 null
	 */
	public static String getBaseUrlAndPath(final HttpServletRequest request) {
		HttpSession session=request.getSession();
		//zmax修改
        Integer userid=0;
		String usertype=(String)session.getAttribute(SessionName.ZRES_SESSIONUSERTYPE);
		if(usertype==null)
			return null;
		if("admin".equals(usertype)){
			return "/";
		}else{
			userid=(Integer)session.getAttribute(SessionName.ZRES_SESSIONUSERID);
			if(userid==null || userid<1)
				return null;
			return "/users/"+userid+"/";
		}
	}
	
	
	
	
	/**
	 * Gets configuration value of baseUrl.
	 * When config value is not set, then return default value.
	 * @param request request
	 * @return default baseDir value
	 */
	public String getBaseUrl(final HttpServletRequest request) {
		ZresConfiguration conf=ZresConfiguration.getInstace();

		String baseURL = null;
		try {
			baseURL = conf.getBaseURL(); //   /userfiles
			//request.getSession().setAttribute(Zres.SESSIONUSERTYPE, "admin"); 
			//zmax add
			//baseURL=baseUrlBySessionZuc(baseURL, request);
			baseURL =baseUrlBySession(baseURL, request);  // /userfiles/0/ or /userfiles/1/

		} catch (Exception e) {
			e.printStackTrace();
			baseURL = null;
		}
		if (baseURL == null || baseURL.equals("")) {
//			baseURL = super.getBaseUrl(request);
			return null;
		}

		return PathUtils.addSlashToBeginning(PathUtils.addSlashToEnd(baseURL));
	}
	/**
	 * zmax add 
	 * 根据session返回访问根目录
	 * 格式：
	 * 管理员: /userfiles/0/
	 * 普通用户：/userfiles/${userId}/
	 * @param request
	 * @return
	 */
	private String baseUrlBySessionZuc(String baseURL,final HttpServletRequest request){
		HttpSession session =request .getSession();   
		if("admin".equals(session.getAttribute(SessionName.ZRES_SESSIONUSERTYPE)))
			return baseURL+"/0/";
		if(session.getAttribute(SessionName.ZRES_SESSIONUSERID)!=null)
			return baseURL+"/"+(Integer)session.getAttribute(SessionName.ZRES_SESSIONUSERID)+"/";
        ServletContext Context = session.getServletContext();   
        ServletContext Context1= Context.getContext("应用来源ex:zru"); 
        System.out.println(Context1); 
        HttpSession session2 =(HttpSession)Context1.getAttribute("session"); 
        if(session2==null)
        	return null;
        //System.out.println("base传过来的user为:"+session2.getAttribute("name")); 


		//zmax修改
		//用户类型
        Integer userid=0;
		String usertype=(String)session2.getAttribute(SessionName.ZRES_SESSIONUSERTYPE);
		if(usertype==null)
			return null;
		if("admin".equals(usertype)){
			session.setAttribute(SessionName.ZRES_SESSIONUSERTYPE, "admin"); 
			session.setAttribute(SessionName.ZRES_SESSIONUSERID, userid); 
		}else{
			userid=Integer.valueOf((String)session2.getAttribute(SessionName.ZRES_SESSIONUSERID));
			session.setAttribute(SessionName.ZRES_SESSIONUSERTYPE, "user"); 
			session.setAttribute(SessionName.ZRES_SESSIONUSERID, userid); 
		}
		return baseURL+"/"+userid+"/";
	}
	/**
	 * zmax add 
	 * 根据session返回访问根目录
	 * 路径结构
	 * @param request
	 * @return
	 */
	private String baseUrlBySession(String baseURL,final HttpServletRequest request){
		HttpSession session=request.getSession();
		//zmax修改
        Integer userid=0;
		String usertype=(String)session.getAttribute(SessionName.ZRES_SESSIONUSERTYPE);
		if(usertype==null)
			return null;
		if("admin".equals(usertype)){
			return baseURL+"/";
		}else{
			userid=Integer.valueOf((String)session.getAttribute(SessionName.ZRES_SESSIONUSERID));
			if(userid==null)
				return null;
		}
		return baseURL+"/users/"+userid+"/";
	}
	/**
	 * Gets configuration value of baseDir.
	 * When config value is not set, then return default value.
	 * @param request request
	 * @return default baseDir value
	 */
	public String getBaseDir(final HttpServletRequest request) {
		ZresConfiguration conf=ZresConfiguration.getInstace();
		String baseDir = null;
		try {
			baseDir = conf.getBaseDir();
		} catch (Exception e) {
			baseDir = null;
		}
		return baseDir;
	}

}
