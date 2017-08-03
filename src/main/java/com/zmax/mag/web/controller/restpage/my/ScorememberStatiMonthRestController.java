/*
 * ScorememberStatiMonthController Powered By zmax
 * 
 * 
 */

package com.zmax.mag.web.controller.restpage.my;

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
import org.springframework.web.bind.annotation.RestController;

import com.zmax.common.exception.*;
import com.zmax.common.utils.easyui.*;

import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.service.my.*;
import com.zmax.mag.service.spec.CommonzSvrUitlsSpec;
import com.zmax.mag.service.spec.SpecService;
import com.zmax.mag.service.utils.PermitCheckUtils;
import com.zmax.mag.service.utils.SessionUserUtils;



/**
 * 最基本的RestFul
 * @author 张闽昕
 * @version 1.0
 * @since - 2012
 */
@RestController
@RequestMapping("/restpage/user/ScorememberStatiMonth")
public class ScorememberStatiMonthRestController {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired 
	CommonzSvrUitlsSpec commonzSvrUitlsSpec;
	@Autowired 
	SpecService specService;	
	@Autowired 
	ScorememberStatiMonthService scorememberStatiMonthService;
	@Autowired
	PermitCheckUtils PermitCheckUtils;	
	@Autowired
	SessionUserUtils sessionUserUtils;	

	/**
	 * 获取数据表格
	 * @param request
	 * @param response
	 * @param model
	 * @param search 对象，里面放着需要被搜索的值
	 * @param ph
	 * @param queryhq
	 */
	@RequestMapping("/grid")
	public Grid grid(HttpServletRequest request,HttpServletResponse response,ModelMap model,ScorememberStatiMonth search, PageHelper ph,String queryhq) {
		try{
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			Grid page = scorememberStatiMonthService.csgrid(sessionUser, search, ph, queryhq);
			return page;
		} 
		catch (NeedLoginException e) {
			
		} 
		catch (BoException e) {
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增提交
	 * @param scorememberStatiMonth
	 * @param errors
	 * @param model
	 * @param request
	 * @param response
	 * @param copyfrom 新建时有可能会从另外一个对象复制产生
	 * @param copyfromwhere 条件 ex. id=1
	 * @return
	 */
	@RequestMapping(value="/create")
	public Json create(@Valid ScorememberStatiMonth scorememberStatiMonth,BindingResult errors,ModelMap model,HttpServletRequest request,HttpServletResponse response,String copyfrom,String copyfromwhere)  {
		if(errors.hasErrors()) {
			FieldError err0=(FieldError)errors.getAllErrors().get(0);
			return new Json(false,"添加失败！"+err0.getField()+":"+err0.getDefaultMessage());
		}
		try {
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			scorememberStatiMonthService.saveCreate(sessionUser,new ScorememberStatiMonth(),scorememberStatiMonth, copyfrom, copyfromwhere);
			return new Json(true,"添加成功！");
		}
		catch (NeedLoginException e) {
			return new Json(false,"操作失败！"+e.getMessage());
		} 
		catch (BoException e) {
			return new Json(false,"操作失败！"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Json(false,"操作失败！");
		}
	}

	/**
	 * 编辑提交
	 * @param scorememberStatiMonth
	 * @param errors
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/update")
	public Json update(@Valid ScorememberStatiMonth scorememberStatiMonth,BindingResult errors,ModelMap model,HttpServletRequest request,HttpServletResponse response)  {
		if(errors.hasErrors()) {
			FieldError err0=(FieldError)errors.getAllErrors().get(0);
			return new Json(false,"添加失败！"+err0.getField()+":"+err0.getDefaultMessage());
		}
		//先从session找回不能改的值
		Integer id=(Integer)request.getSession().getAttribute("scorememberStatiMonth_in_edit"+scorememberStatiMonth.getId());
		if(id==null){
			return new Json(false,"长时间未使用，数据丢失");
		}		
		try {
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			scorememberStatiMonthService.updateUpdate(sessionUser, scorememberStatiMonth, id);
			return new Json(true,"修改成功！");
		} 
		catch (NeedLoginException e) {
			return new Json(false,"操作失败！"+e.getMessage());
		} 
		catch (BoException e) {
			return new Json(false,"操作失败！"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Json(false,"操作失败！"+e.getMessage());
		}
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Json delete(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer id) {
		try {
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			scorememberStatiMonthService.deleteOneById(sessionUser, id);
			return new Json(true,"删除成功！");
		} 
		catch (NeedLoginException e) {
			return new Json(false,"needlogin");
		} 
		catch (BoException e) {
			return new Json(false,"操作失败！"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Json(false,"系统错误！"+e.getMessage());
		}
	}


	/**
	 * 批量删除
	 * @param request
	 * @param response
	 * @param model
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delbatch",method=RequestMethod.POST)
	public Json delbatch(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		String[] ids=request.getParameterValues("ids[]");
		if(ids==null || ids.length==0){
			return  new Json(true,"没有勾选数据！");
		}
		try {
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			scorememberStatiMonthService.deleteBatch(sessionUser, ids);
			return new Json(true,"删除成功！");
		} 
		catch (NeedLoginException e) {
			return new Json(false,"needlogin");
		} 
		catch (BoException e) {
			return new Json(false,"操作失败！"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Json(false,"系统错误！"+e.getMessage());
		}
	}

	/**
	 * 批量更新某一数据
	 * @param request
	 * @param response
	 * @param model
	 * @param setfield
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/updatebatch",method=RequestMethod.POST)
	public Json updatebatch(HttpServletRequest request,HttpServletResponse response,ModelMap model,String setfield) {
		String[] ids=request.getParameterValues("ids[]");
		if(ids==null || ids.length==0){
			return  new Json(true,"没有勾选数据！");
		}
		try {
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			scorememberStatiMonthService.updateBatchByIds(sessionUser, ids, setfield);
			return new Json(true,"操作完成！");
		} 
		catch (NeedLoginException e) {
			return new Json(false,"needlogin");
		} 
		catch (BoException e) {
			return new Json(false,"操作失败！"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Json(false,"系统错误！"+e.getMessage());
		}
	}		
}