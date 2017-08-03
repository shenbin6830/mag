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

import com.zmax.common.utils.easyui.Tree;

/**
 * RestFul控制 
 * 频道
 * 
 *
 */
@RestController
@RequestMapping("/rest/Articlechannel")
public class ArticlechannelRestfulController {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	ArticlechannelService articlechannelService;
	@Autowired
	RestUtils restUtils;
	@Autowired
	RestSpecUtils restSpecUtils;
	/**
	 * 查询列表
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @return List<Articlechannel>
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public List<Articlechannel> query(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage) throws BoException, NeedLoginException, RightException, Exception {
		restPage.fix();
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage,new Articlechannel(),"query");

		List<Articlechannel> list=new ArrayList<Articlechannel>();
		PageHelper ph=new PageHelper(restPage.getPageNo(), restPage.getPageSize());
		ph.setSort(restPage.getOrderstr());
		restSpecUtils.queryBefore(request, clientInfo, restPage, tokenUser, "Articlechannel", ph);
		Grid page = articlechannelService.csgrid(tokenUser, null, ph, restPage.getWhere());
		list=(List<Articlechannel>)page.getRows();
		for (Articlechannel articlechannel : list) {
			articlechannelService.addObj(null, articlechannel);
		}
		//如果是usa过来的查询，把总数量加上，放在备用整数1
		if(restPage!=null&&Conf.REST_CMD_USA.equals(restPage.getCmd())&&list!=null&&list.size()>0){
			((Articlechannel)list.get(0)).setObjint1(page.getTotal());
		}
		restSpecUtils.queryAfter(request, clientInfo, restPage, tokenUser, "Articlechannel", ph, list);
		return list;
	}

	/**
	 * 获取一个新对象，加上各种LIST让用户选择
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param articlechannel 给的默认值，可以为空
	 * @return Articlechannel
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/getnew", method = RequestMethod.GET)
	public Articlechannel getnew(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,Articlechannel articlechannel) throws BoException, NeedLoginException, RightException, Exception {
		if(articlechannel==null)
			articlechannel=new Articlechannel();
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, articlechannel,"new");
		articlechannel=articlechannelService.csnewget(tokenUser, articlechannel, null, null);
		restSpecUtils.newgetAfter(request, clientInfo, restPage, tokenUser, articlechannel);
		return articlechannel;
	}
	/**
	 * 获取ckey对象
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param ckey
	 * @return Articlechannel
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/getckey/{ckey}", method = RequestMethod.GET)
	public Articlechannel getckey(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,@PathVariable String ckey) throws BoException, NeedLoginException, RightException, Exception {
		if(StringUtils.isBlank(ckey)){
			return getnew(request, clientInfo, restPage, null);
		}
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new Articlechannel(),"get");
		restSpecUtils.ggetBefore(request, clientInfo, restPage, tokenUser, "Articlechannel", 0);
		Articlechannel articlechannel=articlechannelService.getckey(tokenUser, ckey);
		restSpecUtils.ggetAfter(request, clientInfo, restPage, tokenUser, articlechannel);
		return articlechannel;
	}
	/**
	 * 新对象提交
	 * @param request
	 * @param clientInfo
	 * @param restPage 页面信息
	 * @param articlechannel 页面提交的对象
	 * @return Articlechannel
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Articlechannel create(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,Articlechannel articlechannel) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, articlechannel,"create");
		restSpecUtils.createBefore(request, clientInfo, restPage, tokenUser, articlechannel);
		articlechannelService.saveCreate(tokenUser,new Articlechannel(),articlechannel, null, null);
		restSpecUtils.createAfter(request, clientInfo, restPage, tokenUser, articlechannel);
		return articlechannel;
	}
	/**
	 * 获取对象
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param id
	 * @return Articlechannel
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Articlechannel get(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,@PathVariable Integer id) throws BoException, NeedLoginException, RightException, Exception {
		if(id==null || id.toString().equals("0")){
			return getnew(request, clientInfo, restPage, null);
		}
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new Articlechannel(),"get");
		restSpecUtils.ggetBefore(request, clientInfo, restPage, tokenUser, "Articlechannel", id);
		Articlechannel articlechannel=articlechannelService.csshow(tokenUser, id);
		restSpecUtils.ggetAfter(request, clientInfo, restPage, tokenUser, articlechannel);
		return articlechannel;
	}
	/**
	 * 获取对象Edit版
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param id
	 * @return Articlechannel
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/getedit/{id}", method = RequestMethod.GET)
	public Articlechannel getedit(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,@PathVariable Integer id) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new Articlechannel(),"get");
		restSpecUtils.ggeteditBefore(request, clientInfo, restPage, tokenUser, "Articlechannel", id);
		Articlechannel articlechannel=articlechannelService.csedit(tokenUser, id);
		restSpecUtils.ggeteditAfter(request, clientInfo, restPage, tokenUser, articlechannel);
		return articlechannel;
	}
	/**
	 * 提交更新
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param articlechannel
	 * @return Articlechannel
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST) 
	public Articlechannel update(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,Articlechannel articlechannel) throws BoException, NeedLoginException, RightException, Exception {
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new Articlechannel(),"update");
		restSpecUtils.updateBefore(request, clientInfo, restPage, tokenUser, articlechannel);
		articlechannelService.updateUpdate(tokenUser, articlechannel, articlechannel.getId());
		articlechannelService.addList(tokenUser,articlechannel);
		articlechannelService.addString(tokenUser,articlechannel);	
		restSpecUtils.updateAfter(request, clientInfo, restPage, tokenUser, articlechannel);
		return articlechannel;
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
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage, new Articlechannel(),"del");
		articlechannelService.deleteById(tokenUser, id);
		return true;
	}
	/**
	 * 查询获取列表树
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @return List[Articlechannel]
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public List<Articlechannel> tree(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage) throws BoException, NeedLoginException, RightException, Exception {
		restPage.fix();
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage,new Articlechannel(),"query");
		PageHelper ph=new PageHelper(restPage.getPageNo(), restPage.getPageSize());
		ph.setSort(restPage.getOrderstr());
		restSpecUtils.queryBefore(request, clientInfo, restPage, tokenUser, "Articlechannel", ph);
		List<Articlechannel> list=articlechannelService.listWithTree(false);
		return list;
	}
	/**
	 * 查询获取pid名下的列表树
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param pid 父母id
	 * @return List[Articlechannel]
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/treebypid", method = RequestMethod.GET)
	public List<Articlechannel> treebypid(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,Integer pid) throws BoException, NeedLoginException, RightException, Exception {
		restPage.fix();
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage,new Articlechannel(),"query");
		PageHelper ph=new PageHelper(restPage.getPageNo(), restPage.getPageSize());
		ph.setSort(restPage.getOrderstr());
		restSpecUtils.queryBefore(request, clientInfo, restPage, tokenUser, "Articlechannel", ph);
		List<Articlechannel> list=articlechannelService.listWithTreeByPid(pid,false);
		return list;
	}
	/**
	 * 查询获取ckey名下的列表树
	 * @param request
	 * @param clientInfo 客户端信息
	 * @param restPage 页面信息
	 * @param ckey 父母ckey
	 * @return List[Articlechannel]
	 * @throws BoException 可以显示给客户的错
	 * @throws NeedLoginException 没登录
	 * @throws Exception 内部错
	 */
	@RequestMapping(value = "/treebyckey", method = RequestMethod.GET)
	public List<Articlechannel> treebyckey(HttpServletRequest request,ClientInfo clientInfo,RestPage restPage,String ckey) throws BoException, NeedLoginException, RightException, Exception {
		restPage.fix();
		User tokenUser=restUtils.checkAccessToken(clientInfo, restPage,new Articlechannel(),"query");
		PageHelper ph=new PageHelper(restPage.getPageNo(), restPage.getPageSize());
		ph.setSort(restPage.getOrderstr());
		restSpecUtils.queryBefore(request, clientInfo, restPage, tokenUser, "Articlechannel", ph);
		Articlechannel articlechannel=articlechannelService.getckey(tokenUser, ckey);
		Integer pid=(articlechannel==null)?null:articlechannel.getId();
		List<Articlechannel> list=articlechannelService.listWithTreeByPid(pid,false);
		return list;
	}
}
