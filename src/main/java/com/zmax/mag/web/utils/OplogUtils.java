package com.zmax.mag.web.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import com.zmax.common.conf.SessionName;
import com.zmax.common.web.utils.RequestUtils;
import com.zmax.mag.domain.bean.Oplog;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.service.my.OplogService;

/**
 * 日志记录工具
 * @author zmax
 *
 */
@Component
public class OplogUtils {
	@Autowired
	OplogService oplogService;
	/**
	 * 从其它过来，比如管理员登录成功
	 * @param request
	 * @param response
	 * @param itype 类型 {'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}
	 * @param tbname 表名
	 * @param tbid 表ID
	 * @param cmemo 备注
	 */
	public void createOne(HttpServletRequest request, HttpServletResponse response,Integer itype ,String tbname,String tbid,String cmemo){
		
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request); //    /prj/restpage/user/Admin/index.do

		Integer userId=null;
		User user = (User) request.getSession().getAttribute(SessionName.user);
		if(user!=null)
			userId=user.getId();


		//记个日志
		Oplog oplog = new Oplog(
				RequestUtils.getIpAddr(request) , //String ip   
				userId , //Integer 操作者   
				itype , //Integer 操作类型 default=0  {'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}
				tbname , //String 操作表   
				tbid , //String 操作表id   
				uri , //String 原始操作   
				cmemo , //String 备注   
				null
			);
		oplog.setGmtCreate(new Date());
		oplogService.save(null, oplog);
	}
	/**
	 * 从resgpage过来的
	 * @param request
	 * @param response
	 */
	public void createFormRestPage(HttpServletRequest request, HttpServletResponse response){
		Integer userId=null;
		User user = (User) request.getSession().getAttribute(SessionName.user);
		if(user!=null)
			userId=user.getId();
		String[] ani=URI2Arr(request);
		String uri=ani[2];
		String tbname=ani[0];
		String stype=ani[1];
		String tbid="";
		String cmemo="";
		int itype=0;
		if("create".equals(stype)||"create.html".equals(stype)){
			itype=1;
		}else if("update".equals(stype)||"update.html".equals(stype)){
			itype=3;
			tbid=request.getParameter("id");
		}else if("del".equals(stype)||"del.html".equals(stype)){
			itype=2;
			tbid=request.getParameter("id");
		}else if("delbatch".equals(stype)||"delbatch.html".equals(stype)){
			itype=2;
			String[] ids=request.getParameterValues("ids[]");
			if(null!=ids){
				for (String id : ids) {
					cmemo+=","+id;
				}
			}
			if(cmemo.length()>1000)cmemo=cmemo.substring(0,999);
		}else if("updatebatch".equals(stype)||"updatebatch.html".equals(stype)){
			itype=8;
			String[] ids=request.getParameterValues("ids[]");
			if(null!=ids){
				for (String id : ids) {
					cmemo+=","+id;
				}
			}
			if(cmemo.length()>1000)cmemo=cmemo.substring(0,999);
		}
		//记个日志
		Oplog oplog = new Oplog(
				RequestUtils.getIpAddr(request) , //String ip   
				userId , //Integer 操作者   
				itype , //Integer 操作类型 default=0  {'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}
				tbname , //String 操作表   
				tbid , //String 操作表id   
				uri , //String 原始操作   
				cmemo , //String 备注   
				null
			);
		oplog.setGmtCreate(new Date());
		oplogService.save(null, oplog);
	}
	/**
	 * 获得路径的最后两项关键字
	 * prj不为空
	 * /prj/restpage/user/Admin/index.do=>{admin,index,uri}
	 * prj为空
	 * /restpage/user/Admin/index.do=>{admin,index,uri}
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 */
	private static String[] URI2Arr(HttpServletRequest request)
			throws IllegalStateException {
		String[] ret=new String[]{"","",""};
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request); //    /prj/restpage/user/Admin/index.do
		ret[2]=uri;
		String ctxPath = helper.getOriginatingContextPath(request);  //   /prj
		
		String[] a1=uri.split("/");
		if (StringUtils.isBlank(ctxPath)) {
			//  /restpage/user/Admin/index.do
			if(a1.length>4){
				ret[0]=a1[3];
				ret[1]=a1[4];
			}
				
		}else{
			//  /prj/restpage/user/Admin/index.do
			if(a1.length>5){
				ret[0]=a1[4];
				ret[1]=a1[5];
			}			
		}
		return ret;
	}
}
