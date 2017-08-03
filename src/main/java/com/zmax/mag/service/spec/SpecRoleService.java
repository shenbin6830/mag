package com.zmax.mag.service.spec;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmax.common.conf.AttrStatic;
import com.zmax.common.exception.BoException;
import com.zmax.common.utils.easyui.Json;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.service.my.*;

/**
 * 涉及到的角色服务
 * @author zmax
 *
 */
@Service
public class SpecRoleService {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	UserService userService;
	@Autowired
	AdminService adminService;
	@Autowired
	CadminService cadminService;
	@Autowired
	MemberService memberService;


	/**
	 * 给用户增加角色对象 user.setUserobj(admin);
	 * @param user User
	 */
	public void userAddRoleobj(User user){
		int usertype=user.getRoleId();
		switch (usertype) {
		case AttrStatic.ROLE_ADMIN:
		{
			user.setUserobj(adminService.get(null, user.getId()));
			break;
		}
		case AttrStatic.ROLE_CADMIN:
		{
			user.setUserobj(cadminService.get(null, user.getId()));
			break;
		}
		case AttrStatic.ROLE_MEMBER:
		{
			user.setUserobj(memberService.get(null, user.getId()));
			break;
		}	
		default:
			break;
		}
	}
	/**
	 * 设置通用版权限
	 * @param role
	 * @param pmtMap
	 */
	public void putFirstPmt(Map<String,String> pmtMap,int roleId){
		//级别权限
		switch (roleId) {
		case AttrStatic.ROLE_CADMIN:{
			putPermit(pmtMap,roleId, "0.ua", "9");
			putPermit(pmtMap,roleId, "0.uo", "9"); //是 oper 的  o
			putPermit(pmtMap,roleId, "0.u2", "9");
			putPermit(pmtMap,roleId, "0.u3", "9");
			putPermit(pmtMap,roleId, "0.u4", "9");
			putPermit(pmtMap,roleId, "0.u5", "9");
			putPermit(pmtMap,roleId, "0.u6", "9");
			putPermit(pmtMap,roleId, "0.p1", "9");
			putPermit(pmtMap,roleId, "0.p4", "9");
			putPermit(pmtMap,roleId, "0.p5", "9");
			putPermit(pmtMap,roleId, "0.p6", "9");

			break;
		}
		case AttrStatic.ROLE_MEMBER:{
			putPermit(pmtMap,roleId, "0.u5", "9");
			putPermit(pmtMap,roleId, "0.p5", "9");
			break;
		}
		default:
			break;
		}
	}	
	/**
	 * 权限数据放到Map中
	 * @param role
	 * @param pmtMap
	 * @param key
	 * @param cvalue
	 */
	private void putPermit(Map<String,String> pmtMap,int roleId,String key,String cvalue){
		//		if (logger.isDebugEnabled())
		//			logger.debug("权限初始化[" +roleId+"]\t"+ key+"\t"+cvalue+"");
		pmtMap.put(key, cvalue);
	}	

	/**
	 * 注册时根据User，自动创建一个相应的Obj
	 * @param user 被创建的User
	 * @param sessionUser 操作的User
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public Json createRegObjByUser(User user,User sessionUser) throws BoException,Exception  {
		if(user==null || user.getId()==null || user.getRoleId()==null)
			throw new BoException("用户信息为空");
		int roleId=user.getRoleId().intValue();
		switch (roleId) {
		case AttrStatic.ROLE_ADMIN:{
			Admin admin=adminService.get(null, user.getId());
			if(null==admin){
				admin = new Admin(
						user.getUsername() , //String 昵称   
						null
						);
				admin.setId(user.getId());
				adminService.save(null, admin);
			}
			user.setUserobj(admin);
			break;
		}
		case AttrStatic.ROLE_CADMIN:{
			Cadmin cadmin=cadminService.get(null, user.getId());
			if(null==cadmin){
				cadmin = new Cadmin(
						user.getUsername() , //String 昵称   
						null
						);
				cadmin.setId(user.getId());
				cadminService.save(null, cadmin);
			}			
			user.setUserobj(cadmin);
			break;
		}
		case AttrStatic.ROLE_MEMBER:{
			Member member=memberService.get(null, user.getId());
			if(null==member){
				//精简构造 个人会员
				member = new Member(
						0 , //Integer 用户类型 default=0 专家可以回答，会员不行 {'0':'会员','1':'专家'}
						0.0 , //Double 咨询费 default=0.0 别人问我问题需要付的钱 
						user.getNickname()==null?user.getUsername():user.getNickname() , //String 姓名或名称   
						null , //String 简介   
						null , //String 头像图  200x200 
						null , //String 二维码图  200x200,大师才有 
						0 , //Integer 类型之个人企业 default=0 用这个区分是企业还是个人 {'0':'个人','1':'企业'}
						null , //String 证件号码  企业是营业执照，个人是身份证 
						null , //String 地址   
						null , //String 邮编   
						user.getUsername() , //String 手机   
						null , //String 电子邮件   
						0 , //Integer 积分  积分余额，是通过流水和统计算出来的 
						0 , //Integer 余额  单位分，现金余额，是通过流水和统计算出来的 
						0 , //Integer 经验  单位分，是通过流水和统计算出来的 
						0 , //Integer 等级  通过经验计算出 
						null
					);
				member.setId(user.getId());
				memberService.save(null, member);
			}
			user.setUserobj(member);
			break;
		}
		default:{
			break;
		}

		}


		Map<String,Object> ret=new HashMap<String, Object>();
		ret.put("user",user);
		return new Json(true,"注册成功",ret);
	}


	/**
	 * 是否是超管
	 * @param tokenUser
	 * @return
	 */
	public static boolean isAdmin(User tokenUser){
		if(tokenUser==null || tokenUser.getRoleId()==null)
			return false;
		return tokenUser.getRoleId().intValue()==AttrStatic.ROLE_ADMIN;
	}


	/**
	 * 是否是普管
	 * @param tokenUser
	 * @return
	 */
	public static boolean isCadmin(User tokenUser){
		if(tokenUser==null || tokenUser.getRoleId()==null)
			return false;
		return tokenUser.getRoleId().intValue()==AttrStatic.ROLE_CADMIN;
	}



	/**
	 * 是否是个人会员
	 * @param tokenUser
	 * @return
	 */
	public static boolean isMember(User tokenUser){
		if(tokenUser==null || tokenUser.getRoleId()==null)
			return false;
		return tokenUser.getRoleId().intValue()==AttrStatic.ROLE_MEMBER;
	}



}