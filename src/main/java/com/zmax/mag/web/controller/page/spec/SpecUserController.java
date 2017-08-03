package com.zmax.mag.web.controller.page.spec;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zmax.mag.domain.bean.*;
import com.zmax.mag.service.my.*;
import com.zmax.mag.service.spec.SpecRoleService;
import com.zmax.mag.service.spec.SpecWxQrService;
import com.zmax.mag.web.controller.page.base.BaseController;
import com.zmax.mag.web.controller.page.my.*;
import com.zmax.mag.domain.bean.Wxr;
import com.zmax.common.conf.AttrStatic;
import com.zmax.common.exception.BoException;
import com.zmax.common.exception.NeedLoginException;
import com.zmax.common.utils.easyui.Json;


@Controller
@RequestMapping("/user")
public class SpecUserController extends BaseController {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	UserService userService;

	@Autowired
	SpecRoleService specRoleService;
	@Autowired
	AdminController adminController;
	@Autowired
	CadminController cadminController;
	@Autowired
	MemberController memberController;
	@Autowired
	MemberService memberService;
	@Autowired
	SpecWxQrService specWxQrService;
	@Autowired
	WxrService wxrService;
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return wm(request,null, "/user/index",null,null);
	}
	/**
	 * 头
	 * @return
	 */
	@RequestMapping("/top")
	public String top(HttpServletRequest request) {
		return wm(request,null, "/user/top",null,null);
	}
	/**
	 * 左
	 * @return
	 */
	@RequestMapping("/left")
	public String left(HttpServletRequest request) {
		return wm(request,null, "/user/left",null,null);
	}
	/**
	 * 脚
	 * @return
	 */
	@RequestMapping("/foot")
	public String foot(HttpServletRequest request) {
		return wm(request,null, "/user/foot",null,null);
	}
	/**
	 * 首右
	 * @return
	 */
	@RequestMapping("/indexright")
	public String indexright(HttpServletRequest request) {
		return wm(request,null, "/user/indexright",null,null);
	}	
	/**
	 * 执行/root/中脚本
	 * 有一个要求，脚本中的路径都要是绝对路径，比如 cd /root/xxx，不能是 cd xxx
	 * @param request
	 * @param response
	 * @param model
	 * @param sh ex. wAaa
	 */
	@RequestMapping(value="/shsh",method=RequestMethod.GET)
	public String shsh(HttpServletRequest request,HttpServletResponse response,ModelMap model,String sh) {
		String msg="命令已经发送["+sh+"]";
		try {
			String cmd="/bin/sh /root/"+sh+".sh";
			if (logger.isDebugEnabled())
				logger.debug("cmd="+cmd);
			//Runtime.getRuntime().exec(cmd).waitFor(); 
			shr(cmd);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e);
			msg=e.getMessage();
		}
		model.addAttribute("errorMsg", msg);
		return wm(request,null, "/error/msg",null,null);
	}
	/**
	 * 执行命令带返回
	 * @param commands
	 * @return
	 */
	private String shr(String commands){
		String ret="";
		try  {          
			Process process = Runtime.getRuntime().exec (commands);
			// for showing the info on screen
			InputStreamReader ir=new InputStreamReader(process.getInputStream());
			BufferedReader input = new BufferedReader (ir);
			String line;
			while ((line = input.readLine ()) != null){
				if (logger.isDebugEnabled())
					logger.debug(line);
				ret+=line+"\r\n";

			}
		}
		catch (java.io.IOException e){
			System.err.println ("IOException " + e.getMessage());
		}
		return ret;
	}
	/**
	 * 从用户页进入用户Admin/Member/Company的列表，不存在的话，创建
	 * @param request 
	 * 		<br>ex. 'user/User/userObj/index.html?queryhq=id=1&newobj=id=1;
	 * @param response
	 * @param model
	 * @param obj
	 * @param queryhq
	 * @param newobj
	 * @return
	 */
	@RequestMapping(value="/User/userObj/index",method=RequestMethod.GET)
	public String userIndexToUserObj(HttpServletRequest request,HttpServletResponse response,ModelMap model,String queryhq,String newobj) {
		//queryqh的第二项是id
		Integer id=Integer.parseInt(queryhq.split("=")[1].replace("'", ""));
		User user=userService.get(null, id);
		return userToUserobj(request, response, model, queryhq, newobj, user);
	}
	/**
	 * 后台页面上点击，查询基本信息，根据User的类型返回跳转Member/Company/Oper等页面,没有的话，会创建一个
	 * 如果不存在，创建
	 * @param request
	 * @param response
	 * @param model
	 * @param queryhq
	 * @param newobj
	 * @param user
	 * @return
	 */
	private String userToUserobj(HttpServletRequest request,HttpServletResponse response,ModelMap model,String queryhq,String newobj,User user){
		if(user==null)
			return ERRPAGE;
		try {
			specRoleService.createRegObjByUser(user, null);
		} catch (BoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERRPAGE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERRPAGE;
		}
		if(user==null || user.getUserobj()==null)
			return ERRPAGE;
		int usertype=user.getRoleId();
		switch (usertype) {
		case AttrStatic.ROLE_ADMIN:
		{
			return adminController.index(request, response, model, (Admin)user.getUserobj(), queryhq, newobj,null);
		}
		case AttrStatic.ROLE_CADMIN:
		{
			return cadminController.index(request, response, model, (Cadmin)user.getUserobj(), queryhq, newobj,null);
		}
		default:
			break;
		}
		return ERRPAGE;		
	}
	/**
	 * 医生的关注二维码，更新一下二维码
	 * 根据doctor.id找到wxr，根据wxr生成图 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 */
	@RequestMapping(value="/Member/qr")
	public void memberQr(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer id) {
		try {
			Wxr wxr=null;
			Member member=memberService.get(null, id);
			if(member==null){
				writeJson(request, response, new Json(false,"操作失败！改会员不存在"));
				return;
			}
			if(member.getMtype().intValue()==0){
				writeJson(request, response, new Json(false,"只有大师才能产生二维码！"));
				return;
			}
			wxr=(Wxr)wxrService.getFirst(null, "userId=?0", null, new Object[]{member.getId()});
			if(wxr==null){
				writeJson(request, response, new Json(false,"操作失败！微信关系wxr不存在平台绑定用户"));
				return;
			}
			specWxQrService.createOrUpdateWxrQr(request, wxr);
			if(StringUtils.isNotBlank(wxr.getImgqr())){
				member.setImgqr(wxr.getImgqr());
				memberService.update(null, member);
			}
		} catch (BoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			writeJson(request, response, new Json(false,"操作失败！"+e.getMessage()));
		} catch (NeedLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			writeJson(request, response, new Json(false,"操作失败！"+e.getMessage()));
		} catch(ConstraintViolationException e){
			e.printStackTrace();
			logger.error(e.getConstraintViolations().toArray()[0]);
			writeJson(request, response, new Json(false,"操作失败！"+e.getMessage()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			writeJson(request, response, new Json(false,"操作失败！"+e.getMessage()));
		}
		writeJson(request, response, new Json(true,"成功，刷新一下数据"));

	}
}
