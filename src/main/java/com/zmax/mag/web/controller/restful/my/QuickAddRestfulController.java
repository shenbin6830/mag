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
 * 抢答之追加
 * 
 *
 */
@RestController
@RequestMapping("/rest/QuickAdd")
public class QuickAddRestfulController {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	QuickAddService quickAddService;
	@Autowired
	RestUtils restUtils;
	@Autowired
	RestSpecUtils restSpecUtils;
	/**
	 * 查询列表
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @return List<QuickAdd>
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public List<QuickAdd> query(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage) throws BoException, NeedLoginException, RightException, Exception {
		restPage.fix();
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage,new QuickAdd(),"query");

		List<QuickAdd> list=new ArrayList<QuickAdd>();
		PageHelper ph=new PageHelper(restPage.getPageNo(), restPage.getPageSize());
		ph.setSort(restPage.getOrderstr());
		restSpecUtils.queryBefore(request, clientInfo, restPage, tokenUser, "QuickAdd", ph);
		Grid page = quickAddService.csgrid(tokenUser, null, ph, restPage.getWhere());
		list=(List<QuickAdd>)page.getRows();
		for (QuickAdd quickAdd : list) {
			quickAddService.addObj(null, quickAdd);
		}
		//如果是usa过来的查询，把总数量加上，放在备用整数1
		if(restPage!=null&&Conf.REST_CMD_USA.equals(restPage.getCmd())&&list!=null&&list.size()>0){
			((QuickAdd)list.get(0)).setObjint1(page.getTotal());
		}
		restSpecUtils.queryAfter(request, clientInfo, restPage, tokenUser, "QuickAdd", ph, list);
		return list;
	}

	/**
	 * 获取一个新对象，加上各种LIST让用户选择
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param quickAdd 给的默认值，可以为空
	 * @return QuickAdd
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/getnew", method = RequestMethod.GET)
	public QuickAdd getnew(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,QuickAdd quickAdd) throws BoException, NeedLoginException, RightException, Exception {
		if(quickAdd==null)
			quickAdd=new QuickAdd();
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, quickAdd,"new");
		quickAdd=quickAddService.csnewget(tokenUser, quickAdd, null, null);
		restSpecUtils.newgetAfter(request, clientInfo, restPage, tokenUser, quickAdd);
		return quickAdd;
	}
	/**
	 * 新对象提交
	 * @param request
	 * @param clientInfo
	 * @param restPage 页面信息
	 * @param quickAdd 页面提交的对象
	 * @return QuickAdd
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public QuickAdd create(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,QuickAdd quickAdd) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, quickAdd,"create");
		restSpecUtils.createBefore(request, clientInfo, restPage, tokenUser, quickAdd);
		quickAddService.saveCreate(tokenUser,new QuickAdd(),quickAdd, null, null);
		restSpecUtils.createAfter(request, clientInfo, restPage, tokenUser, quickAdd);
		return quickAdd;
	}
	/**
	 * 获取对象
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param id
	 * @return QuickAdd
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public QuickAdd get(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,@PathVariable Integer id) throws BoException, NeedLoginException, RightException, Exception {
		if(id==null || id.toString().equals("0")){
			return getnew(request, clientInfo, restPage, null);
		}
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new QuickAdd(),"get");
		restSpecUtils.ggetBefore(request, clientInfo, restPage, tokenUser, "QuickAdd", id);
		QuickAdd quickAdd=quickAddService.csshow(tokenUser, id);
		restSpecUtils.ggetAfter(request, clientInfo, restPage, tokenUser, quickAdd);
		return quickAdd;
	}
	/**
	 * 获取对象Edit版
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param id
	 * @return QuickAdd
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/getedit/{id}", method = RequestMethod.GET)
	public QuickAdd getedit(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,@PathVariable Integer id) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new QuickAdd(),"get");
		restSpecUtils.ggeteditBefore(request, clientInfo, restPage, tokenUser, "QuickAdd", id);
		QuickAdd quickAdd=quickAddService.csedit(tokenUser, id);
		restSpecUtils.ggeteditAfter(request, clientInfo, restPage, tokenUser, quickAdd);
		return quickAdd;
	}
	/**
	 * 提交更新
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param quickAdd
	 * @return QuickAdd
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST) 
	public QuickAdd update(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,QuickAdd quickAdd) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new QuickAdd(),"update");
		restSpecUtils.updateBefore(request, clientInfo, restPage, tokenUser, quickAdd);
		quickAddService.updateUpdate(tokenUser, quickAdd, quickAdd.getId());
		quickAddService.addList(tokenUser,quickAdd);
		quickAddService.addString(tokenUser,quickAdd);	
		restSpecUtils.updateAfter(request, clientInfo, restPage, tokenUser, quickAdd);
		return quickAdd;
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
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new QuickAdd(),"del");
		quickAddService.deleteById(tokenUser, id);
		return true;
	}
}
