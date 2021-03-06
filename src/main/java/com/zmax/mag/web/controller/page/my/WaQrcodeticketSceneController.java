/*
 * WaQrcodeticketSceneController Powered By zmax
 * 
 * 
 */

package com.zmax.mag.web.controller.page.my;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmax.common.exception.*;
import com.zmax.common.utils.easyui.*;

import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.service.my.*;
import com.zmax.mag.service.spec.CommonzSvrUitlsSpec;
import com.zmax.mag.service.spec.SpecService;
import com.zmax.mag.service.utils.PermitCheckUtils;
import com.zmax.mag.service.utils.SessionUserUtils;
import com.zmax.mag.web.controller.page.base.BaseController;



/**
 * 最基本的增删改查
 * @author 张闽昕
 * @version 1.0
 * @since - 2012
 */
@Controller
@RequestMapping("/user/WaQrcodeticketScene")
public class WaQrcodeticketSceneController extends BaseController {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	public String LIST_ACTION = "redirect:/user/WaQrcodeticketScene/index.html";
	public String NOW_PAGE="/pages/user";
	@Autowired 
	CommonzSvrUitlsSpec commonzSvrUitlsSpec;
	@Autowired 
	SpecService specService;	
	@Autowired 
	WaQrcodeticketSceneService waQrcodeticketSceneService;
	@Autowired
	PermitCheckUtils PermitCheckUtils;	
	@Autowired
	SessionUserUtils sessionUserUtils;	
	/**
	 * 首页
	 * @param request
	 * @param response
	 * @param model
	 * @param obj 默认的对象
	 * @param queryhq 查询hql
	 * @param newobj 在此新增对象的默认值
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model,WaQrcodeticketScene obj,String queryhq,String newobj,String other) {
		if(obj==null)
			obj=new WaQrcodeticketScene();
		try {
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			waQrcodeticketSceneService.csindex(sessionUser,obj,other);
			model.addAttribute("waQrcodeticketScene", obj);
			model.addAttribute("queryhq", queryhq);
			model.addAttribute("newobj", newobj);
			model.addAttribute("other", other);
			return wm(request,null, commonzSvrUitlsSpec.changeHtml(sessionUser, NOW_PAGE+"/WaQrcodeticketScene/index","WaQrcodeticketScene","index"),null,null);
		} 
		catch (NeedLoginException e) {
			return err(e.getMessage());
		}
		catch (BoException e) {
			return err("操作失败！"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return err("操作失败!");
		}
	}


	/**
	 * 显示内容
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/show")
	public String show(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer id) {
		try {
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			WaQrcodeticketScene waQrcodeticketScene=waQrcodeticketSceneService.csshow(sessionUser, id);
			model.addAttribute("waQrcodeticketScene",waQrcodeticketScene);
			return  wm(request,null,commonzSvrUitlsSpec.changeHtml(sessionUser, NOW_PAGE+"/WaQrcodeticketScene/show","WaQrcodeticketScene","show"),null,null);
		}
		catch (NeedLoginException e) {
			return err(e.getMessage());
		}
		catch (BoException e) {
			return err("操作失败！"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return err("操作失败!");
		}
	}

	/**
	 * 新增请求
	 * @param request
	 * @param response
	 * @param model
	 * @param waQrcodeticketScene
	 * @param copyfrom 新建时有可能会从另外一个对象复制产生
	 * @param copyfromwhere 条件 ex. id=1
	 * @return
	 */
	@RequestMapping(value="/new")
	public String newget(HttpServletRequest request,HttpServletResponse response,ModelMap model,WaQrcodeticketScene waQrcodeticketScene,String copyfrom,String copyfromwhere){
		try {
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			waQrcodeticketScene=waQrcodeticketSceneService.csnewget(sessionUser, waQrcodeticketScene, copyfrom, copyfromwhere);
			model.addAttribute("waQrcodeticketScene",waQrcodeticketScene);
			return commonzSvrUitlsSpec.changeHtml(sessionUser, NOW_PAGE+"/WaQrcodeticketScene/new","WaQrcodeticketScene","new");
		}
		catch (NeedLoginException e) {
			return err(e.getMessage());
		}
		catch (BoException e) {
			return err("操作失败！"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return err("操作失败!");
		}
	}


	/**
	 * 编辑请求
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit")
	public String edit(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer id)  {
		try {
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			WaQrcodeticketScene waQrcodeticketScene=waQrcodeticketSceneService.csedit(sessionUser, id);
			model.addAttribute("waQrcodeticketScene",waQrcodeticketScene);
			request.getSession().setAttribute("waQrcodeticketScene_in_edit"+waQrcodeticketScene.getId(), waQrcodeticketScene.getId());
			putToken(request);
			return commonzSvrUitlsSpec.changeHtml(sessionUser, NOW_PAGE+"/WaQrcodeticketScene/edit","WaQrcodeticketScene","edit");
		} 
		catch (NeedLoginException e) {
			return err(e.getMessage());
		}
		catch (BoException e) {
			return err("操作失败！"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return err("操作失败!");
		}
	}

}