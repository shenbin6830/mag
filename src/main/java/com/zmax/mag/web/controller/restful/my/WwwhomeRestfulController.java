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
 * 手机页首页配置
 * 
 *
 */
@RestController
@RequestMapping("/rest/Wwwhome")
public class WwwhomeRestfulController {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	WwwhomeService wwwhomeService;
	@Autowired
	RestUtils restUtils;
	@Autowired
	RestSpecUtils restSpecUtils;
	/**
	 * 查询列表
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @return List<Wwwhome>
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public List<Wwwhome> query(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage) throws BoException, NeedLoginException, RightException, Exception {
		restPage.fix();
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage,new Wwwhome(),"query");

		List<Wwwhome> list=new ArrayList<Wwwhome>();
		PageHelper ph=new PageHelper(restPage.getPageNo(), restPage.getPageSize());
		ph.setSort(restPage.getOrderstr());
		restSpecUtils.queryBefore(request, clientInfo, restPage, tokenUser, "Wwwhome", ph);
		Grid page = wwwhomeService.csgrid(tokenUser, null, ph, restPage.getWhere());
		list=(List<Wwwhome>)page.getRows();
		for (Wwwhome wwwhome : list) {
			wwwhomeService.addObj(null, wwwhome);
		}
		//如果是usa过来的查询，把总数量加上，放在备用整数1
		if(restPage!=null&&Conf.REST_CMD_USA.equals(restPage.getCmd())&&list!=null&&list.size()>0){
			((Wwwhome)list.get(0)).setObjint1(page.getTotal());
		}
		restSpecUtils.queryAfter(request, clientInfo, restPage, tokenUser, "Wwwhome", ph, list);
		return list;
	}

	/**
	 * 获取一个新对象，加上各种LIST让用户选择
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param wwwhome 给的默认值，可以为空
	 * @return Wwwhome
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/getnew", method = RequestMethod.GET)
	public Wwwhome getnew(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,Wwwhome wwwhome) throws BoException, NeedLoginException, RightException, Exception {
		if(wwwhome==null)
			wwwhome=new Wwwhome();
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, wwwhome,"new");
		wwwhome=wwwhomeService.csnewget(tokenUser, wwwhome, null, null);
		restSpecUtils.newgetAfter(request, clientInfo, restPage, tokenUser, wwwhome);
		return wwwhome;
	}
	/**
	 * 新对象提交
	 * @param request
	 * @param clientInfo
	 * @param restPage 页面信息
	 * @param wwwhome 页面提交的对象
	 * @return Wwwhome
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Wwwhome create(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,Wwwhome wwwhome) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, wwwhome,"create");
		restSpecUtils.createBefore(request, clientInfo, restPage, tokenUser, wwwhome);
		wwwhomeService.saveCreate(tokenUser,new Wwwhome(),wwwhome, null, null);
		restSpecUtils.createAfter(request, clientInfo, restPage, tokenUser, wwwhome);
		return wwwhome;
	}
	/**
	 * 获取对象
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param id
	 * @return Wwwhome
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Wwwhome get(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,@PathVariable Integer id) throws BoException, NeedLoginException, RightException, Exception {
		if(id==null || id.toString().equals("0")){
			return getnew(request, clientInfo, restPage, null);
		}
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new Wwwhome(),"get");
		restSpecUtils.ggetBefore(request, clientInfo, restPage, tokenUser, "Wwwhome", id);
		Wwwhome wwwhome=wwwhomeService.csshow(tokenUser, id);
		restSpecUtils.ggetAfter(request, clientInfo, restPage, tokenUser, wwwhome);
		return wwwhome;
	}
	/**
	 * 获取对象Edit版
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param id
	 * @return Wwwhome
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/getedit/{id}", method = RequestMethod.GET)
	public Wwwhome getedit(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,@PathVariable Integer id) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new Wwwhome(),"get");
		restSpecUtils.ggeteditBefore(request, clientInfo, restPage, tokenUser, "Wwwhome", id);
		Wwwhome wwwhome=wwwhomeService.csedit(tokenUser, id);
		restSpecUtils.ggeteditAfter(request, clientInfo, restPage, tokenUser, wwwhome);
		return wwwhome;
	}
	/**
	 * 提交更新
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param wwwhome
	 * @return Wwwhome
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST) 
	public Wwwhome update(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,Wwwhome wwwhome) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new Wwwhome(),"update");
		restSpecUtils.updateBefore(request, clientInfo, restPage, tokenUser, wwwhome);
		wwwhomeService.updateUpdate(tokenUser, wwwhome, wwwhome.getId());
		wwwhomeService.addList(tokenUser,wwwhome);
		wwwhomeService.addString(tokenUser,wwwhome);	
		restSpecUtils.updateAfter(request, clientInfo, restPage, tokenUser, wwwhome);
		return wwwhome;
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
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new Wwwhome(),"del");
		wwwhomeService.deleteById(tokenUser, id);
		return true;
	}
}
