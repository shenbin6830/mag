/*
 * OrderrQuickviewDiscardController Powered By zmax
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



import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.net.URLEncoder;
import java.io.OutputStream;
/**
 * 最基本的增删改查
 * @author 张闽昕
 * @version 1.0
 * @since - 2012
 */
@Controller
@RequestMapping("/user/OrderrQuickviewDiscard")
public class OrderrQuickviewDiscardController extends BaseController {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	public String LIST_ACTION = "redirect:/user/OrderrQuickviewDiscard/index.html";
	public String NOW_PAGE="/pages/user";
	@Autowired 
	CommonzSvrUitlsSpec commonzSvrUitlsSpec;
	@Autowired 
	SpecService specService;	
	@Autowired 
	OrderrQuickviewDiscardService orderrQuickviewDiscardService;
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
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model,OrderrQuickviewDiscard obj,String queryhq,String newobj,String other) {
		if(obj==null)
			obj=new OrderrQuickviewDiscard();
		try {
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			orderrQuickviewDiscardService.csindex(sessionUser,obj,other);
			model.addAttribute("orderrQuickviewDiscard", obj);
			model.addAttribute("queryhq", queryhq);
			model.addAttribute("newobj", newobj);
			model.addAttribute("other", other);
			return wm(request,null, commonzSvrUitlsSpec.changeHtml(sessionUser, NOW_PAGE+"/OrderrQuickviewDiscard/index","OrderrQuickviewDiscard","index"),null,null);
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
	 * 获取数据表格Excel版，这个会把所有的符合条件的进行返回
	 * @param request
	 * @param response
	 * @param model
	 * @param search 对象，里面放着需要被搜索的值
	 * @param ph
	 * @param queryhq
	 * @param act 更多的操作指令
	 */
	@RequestMapping("/excelexport")
	public void excelexport(HttpServletRequest request,HttpServletResponse response,ModelMap model,OrderrQuickviewDiscard search, PageHelper ph,String queryhq,String act) {
		try{
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			HSSFWorkbook excel = orderrQuickviewDiscardService.csexcelexport(sessionUser, search, ph, queryhq, act);
			OutputStream out = response.getOutputStream();	   
			response.reset();// 清空输出流 
			String filename="OrderrQuickviewDiscard.xls";
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			response.setContentType("application/msexcel;charset=UTF-8");  
			excel.write(out);	
			out.close();  
		} 
		catch (NeedLoginException e) {
			
			//writeJson(request, response, new Json(false,"操作失败！"+e.getMessage()));
		} 
		catch (BoException e) {
			//writeJson(request, response, new Json(false,"操作失败！"+e.getMessage()));
		}
		catch (Exception e) {
			e.printStackTrace();
			//writeJson(request, response, new Json(false,"操作失败！"));
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
			OrderrQuickviewDiscard orderrQuickviewDiscard=orderrQuickviewDiscardService.csshow(sessionUser, id);
			model.addAttribute("orderrQuickviewDiscard",orderrQuickviewDiscard);
			return  wm(request,null,commonzSvrUitlsSpec.changeHtml(sessionUser, NOW_PAGE+"/OrderrQuickviewDiscard/show","OrderrQuickviewDiscard","show"),null,null);
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
	 * @param orderrQuickviewDiscard
	 * @param copyfrom 新建时有可能会从另外一个对象复制产生
	 * @param copyfromwhere 条件 ex. id=1
	 * @return
	 */
	@RequestMapping(value="/new")
	public String newget(HttpServletRequest request,HttpServletResponse response,ModelMap model,OrderrQuickviewDiscard orderrQuickviewDiscard,String copyfrom,String copyfromwhere){
		try {
			User sessionUser=sessionUserUtils.userFromRequestWithException(request);
			orderrQuickviewDiscard=orderrQuickviewDiscardService.csnewget(sessionUser, orderrQuickviewDiscard, copyfrom, copyfromwhere);
			model.addAttribute("orderrQuickviewDiscard",orderrQuickviewDiscard);
			return commonzSvrUitlsSpec.changeHtml(sessionUser, NOW_PAGE+"/OrderrQuickviewDiscard/new","OrderrQuickviewDiscard","new");
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
			OrderrQuickviewDiscard orderrQuickviewDiscard=orderrQuickviewDiscardService.csedit(sessionUser, id);
			model.addAttribute("orderrQuickviewDiscard",orderrQuickviewDiscard);
			request.getSession().setAttribute("orderrQuickviewDiscard_in_edit"+orderrQuickviewDiscard.getId(), orderrQuickviewDiscard.getId());
			putToken(request);
			return commonzSvrUitlsSpec.changeHtml(sessionUser, NOW_PAGE+"/OrderrQuickviewDiscard/edit","OrderrQuickviewDiscard","edit");
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