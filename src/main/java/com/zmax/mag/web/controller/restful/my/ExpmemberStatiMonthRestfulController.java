/*
 * zmax 
 * 
 */

package com.zmax.mag.web.controller.restful.my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmax.common.exception.BoException;
import com.zmax.common.exception.NeedLoginException;
import com.zmax.common.exception.RightException;
import com.zmax.common.utils.easyui.Grid;
import com.zmax.common.utils.easyui.PageHelper;
import com.zmax.common.utils.string.*;

import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.service.my.*;
import com.zmax.mag.web.controller.restful.RestSpecUtils;
import com.zmax.mag.web.controller.restful.RestUtils;
import com.zmax.mag.web.controller.restful.entity.ClientInfo;
import com.zmax.mag.web.controller.restful.entity.RestPage;


/**
 * RestFul控制 
 * 会员经验月统计
 * 
 *
 */
@RestController
@RequestMapping("/rest/ExpmemberStatiMonth")
public class ExpmemberStatiMonthRestfulController {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	ExpmemberStatiMonthService expmemberStatiMonthService;
	@Autowired
	RestUtils restUtils;
	@Autowired
	RestSpecUtils restSpecUtils;
	/**
	 * 查询列表
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @return List<ExpmemberStatiMonth>
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public List<ExpmemberStatiMonth> query(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage) throws BoException, NeedLoginException, RightException, Exception {
		restPage.fix();
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage,new ExpmemberStatiMonth(),"query");

		List<ExpmemberStatiMonth> list=new ArrayList<ExpmemberStatiMonth>();
		PageHelper ph=new PageHelper(restPage.getPageNo(), restPage.getPageSize());
		ph.setSort(restPage.getOrderstr());
		restSpecUtils.queryBefore(request, clientInfo, restPage, tokenUser, "ExpmemberStatiMonth", ph);
		Grid page = expmemberStatiMonthService.csgrid(tokenUser, null, ph, restPage.getWhere());
		list=(List<ExpmemberStatiMonth>)page.getRows();
		for (ExpmemberStatiMonth expmemberStatiMonth : list) {
			expmemberStatiMonthService.addObj(null, expmemberStatiMonth);
		}
		//如果是usa过来的查询，把总数量加上，放在备用整数1
		if(restPage!=null&&Conf.REST_CMD_USA.equals(restPage.getCmd())&&list!=null&&list.size()>0){
			((ExpmemberStatiMonth)list.get(0)).setObjint1(page.getTotal());
		}
		restSpecUtils.queryAfter(request, clientInfo, restPage, tokenUser, "ExpmemberStatiMonth", ph, list);
		return list;
	}

	/**
	 * 获取一个新对象，加上各种LIST让用户选择
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param expmemberStatiMonth 给的默认值，可以为空
	 * @return ExpmemberStatiMonth
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/getnew", method = RequestMethod.GET)
	public ExpmemberStatiMonth getnew(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,ExpmemberStatiMonth expmemberStatiMonth) throws BoException, NeedLoginException, RightException, Exception {
		if(expmemberStatiMonth==null)
			expmemberStatiMonth=new ExpmemberStatiMonth();
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, expmemberStatiMonth,"new");
		expmemberStatiMonth=expmemberStatiMonthService.csnewget(tokenUser, expmemberStatiMonth, null, null);
		restSpecUtils.newgetAfter(request, clientInfo, restPage, tokenUser, expmemberStatiMonth);
		return expmemberStatiMonth;
	}
	/**
	 * 新对象提交
	 * @param request
	 * @param clientInfo
	 * @param restPage 页面信息
	 * @param expmemberStatiMonth 页面提交的对象
	 * @return ExpmemberStatiMonth
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ExpmemberStatiMonth create(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,ExpmemberStatiMonth expmemberStatiMonth) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, expmemberStatiMonth,"create");
		restSpecUtils.createBefore(request, clientInfo, restPage, tokenUser, expmemberStatiMonth);
		expmemberStatiMonthService.saveCreate(tokenUser,new ExpmemberStatiMonth(),expmemberStatiMonth, null, null);
		restSpecUtils.createAfter(request, clientInfo, restPage, tokenUser, expmemberStatiMonth);
		return expmemberStatiMonth;
	}
	/**
	 * 获取对象
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param id
	 * @return ExpmemberStatiMonth
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ExpmemberStatiMonth get(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,@PathVariable Integer id) throws BoException, NeedLoginException, RightException, Exception {
		if(id==null || id.toString().equals("0")){
			return getnew(request, clientInfo, restPage, null);
		}
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new ExpmemberStatiMonth(),"get");
		restSpecUtils.ggetBefore(request, clientInfo, restPage, tokenUser, "ExpmemberStatiMonth", id);
		ExpmemberStatiMonth expmemberStatiMonth=expmemberStatiMonthService.csshow(tokenUser, id);
		restSpecUtils.ggetAfter(request, clientInfo, restPage, tokenUser, expmemberStatiMonth);
		return expmemberStatiMonth;
	}
	/**
	 * 获取对象Edit版
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param id
	 * @return ExpmemberStatiMonth
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/getedit/{id}", method = RequestMethod.GET)
	public ExpmemberStatiMonth getedit(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,@PathVariable Integer id) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new ExpmemberStatiMonth(),"get");
		restSpecUtils.ggeteditBefore(request, clientInfo, restPage, tokenUser, "ExpmemberStatiMonth", id);
		ExpmemberStatiMonth expmemberStatiMonth=expmemberStatiMonthService.csedit(tokenUser, id);
		restSpecUtils.ggeteditAfter(request, clientInfo, restPage, tokenUser, expmemberStatiMonth);
		return expmemberStatiMonth;
	}
	/**
	 * 提交更新
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param expmemberStatiMonth
	 * @return ExpmemberStatiMonth
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST) 
	public ExpmemberStatiMonth update(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,ExpmemberStatiMonth expmemberStatiMonth) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new ExpmemberStatiMonth(),"update");
		restSpecUtils.updateBefore(request, clientInfo, restPage, tokenUser, expmemberStatiMonth);
		expmemberStatiMonthService.updateUpdate(tokenUser, expmemberStatiMonth, expmemberStatiMonth.getId());
		expmemberStatiMonthService.addList(tokenUser,expmemberStatiMonth);
		expmemberStatiMonthService.addString(tokenUser,expmemberStatiMonth);	
		restSpecUtils.updateAfter(request, clientInfo, restPage, tokenUser, expmemberStatiMonth);
		return expmemberStatiMonth;
	}
	/**
	 * 删除对象
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param id
	 * @return Boolean
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public Boolean del(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,@PathVariable Integer id) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new ExpmemberStatiMonth(),"del");
		expmemberStatiMonthService.deleteById(tokenUser, id);
		return true;
	}
}
