package com.zmax.mag.service.utils;
/**
 * 权限检查工具
 */
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zmax.common.conf.AttrStatic;
import com.zmax.common.conf.SessionName;
import com.zmax.common.exception.NeedLoginException;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.service.my.AdminService;



/**
 * Session和Db关于User读写
 * @author zmax
 *
 */

@Component
public class SessionUserUtils {
	/**日志实例*/
	private static final Logger logger = Logger.getLogger(SessionUserUtils.class);
	/**关键字之给其它系统用的用户登录，传参版，内容是&userType=1&userId=2&hm=a12b34*/
	public static String keyUUHM="uuhm";
	
	@Autowired
	AdminService userService;

	
	/**
	 * 从Request.Session中获取User并且检查用户类型，如果不存在，抛出没有登录的错
	 * @param request
	 * @param clazz ex.Member.class
	 * @return
	 * @throws NeedLoginException 抛出没有登录的错
	 */
	public User userFromRequestWithException(HttpServletRequest request,Class clazz) throws NeedLoginException{
		HttpSession session=(request==null)?null:request.getSession();
		if(session==null)
			throw new NeedLoginException();
		if(session.getAttribute(SessionName.user)==null)
			throw new NeedLoginException();
		User user=(User)session.getAttribute(SessionName.user);
		if(user.getUserobj()==null)
			return null;
		if(user.getUserobj().getClass().equals(clazz))
			return user;
		
		return null;
	}
	/**
	 * 从Request.Session中获取User 如果不存在，抛出没有登录的错
	 * @param request
	 * @return
	 */
	public User userFromRequestWithException(HttpServletRequest request) throws NeedLoginException{
		HttpSession session=(request==null)?null:request.getSession();
		if(session==null)
			throw new NeedLoginException();
		if(session.getAttribute(SessionName.user)==null)
			throw new NeedLoginException();
		return (User)session.getAttribute(SessionName.user);
	}
	/**
	 * 从Request.Session中获取User 如果不存在，不抛错，返回空
	 * @param request
	 * @return
	 */
	public User userFromRequest(HttpServletRequest request){
		HttpSession session=(request==null)?null:request.getSession();
		if(session==null)
			return null;
		if(session.getAttribute(SessionName.user)==null)
			return null;
		return (User)session.getAttribute(SessionName.user);
	}
	/**
	 * 从Request.Session中获取User.userObj
	 * @param request
	 * @return
	 */
	public Object userObjFromRequest(HttpServletRequest request){
		HttpSession session=(request==null)?null:request.getSession();
		if(session==null)
			return null;
		if(session.getAttribute(SessionName.user)==null)
			return null;
		User user=(User)session.getAttribute(SessionName.user);
		return user.getUserobj();
	}

	/**
	 * 把User保存到数据库中，同时更新session
	 * @param request
	 * @param me
	 */
	public  void saveUserMe(HttpServletRequest request,User me){
		User sessionUser=userFromRequest(request);
		//zmax1031 User dbme=userService.get(sessionUser, me.getId());
		//zmax 1031 ClassUtils.beanDeepCopySkipEmpty(me, dbme);
		//zmax 1031 userService.update(sessionUser, dbme);
		//zmax 1031 sessionUser.setUserobj(dbme);
	}
	


	/**Session中的userObjMap的key的分隔符*/
	static String sessionMapKeySplit="^";
	/**
	 * 根据关键字从session中获取相应的用户所拥有的对象
	 * 如果user不存在，可能是session过期了，或者还没登录，抛错
	 * 如果不存在，则到表里去取一下
	 * 如果还不存在，则返回null
	 * @param request
	 * @param key 关键字 ex.bomember
	 * @param bizId 所属商家id
	 * @return
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public Object takeUserObjmap(HttpServletRequest request,String key,Integer bizId) throws NeedLoginException,Exception{
		User sessionUser=userFromRequest(request);
		if(sessionUser==null) 
			throw new NeedLoginException("用户不存在，可能是session过期了");
		if(sessionUser.getObjmap()==null)
			sessionUser.setObjmap(new HashMap<String, Object>());
		String realkey=key+sessionMapKeySplit+bizId;
		Object obj=sessionUser.getObjmap().get(realkey);
		if(obj!=null)
			return obj;
		return null;
	}

	/**
	 * 从表中获取数据更新session
	 * 这个是用于并发安全，配合xxxService xxxDao executeHql(update xxx)之后的工作
	 * @param request
	 * @param key 关键字 ex.bomember
	 * @param bizId 所属商家id
	 * @return
	 * @throws NeedLoginException 需要登录
	 * @throws Exception 数据不存在
	 */
	public Object refreshUserObjmapFromTb(HttpServletRequest request,String key,Integer bizId) throws NeedLoginException,Exception{
		User sessionUser=userFromRequest(request);
		if(sessionUser==null) 
			throw new NeedLoginException("用户不存在，可能是session过期了");
		if(sessionUser.getObjmap()==null)
			sessionUser.setObjmap(new HashMap<String, Object>());
		String realkey=key+sessionMapKeySplit+bizId;
		return null;
	}
	/**
	 * 更新session的objmap,同时保存到表中，如果涉及到金额的时候，不要用这个函数，此函数为非并发安全函数
	 * @param request
	 * @param key 关键字 ex.bomember
	 * @param bizId 所属商家id
	 * @param obj 需要保存的对象
	 * @param isSaveDb boolean 是不是要保存到数据库
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void saveUserObjmap(HttpServletRequest request,String key,Integer bizId,Object obj,Boolean isSaveDb) throws NeedLoginException,Exception{
		if(StringUtils.isBlank(key)||obj==null) 
			throw new Exception("传入对象为空");
		User sessionUser=userFromRequest(request);
		if(sessionUser==null) 
			throw new NeedLoginException("用户不存在，可能是session过期了");
		if(sessionUser.getObjmap()==null)
			sessionUser.setObjmap(new HashMap<String, Object>());
		String realkey=key+sessionMapKeySplit+bizId;

		//保存到session
		sessionUser.getObjmap().put(realkey, obj);
		isSaveDb=(isSaveDb==null)?false:isSaveDb;
		if(!isSaveDb)
			return;
		//存到表
	}
	/**
	 * 是否是超级管理员
	 * @param request
	 * @return
	 */
	public boolean isAdmin(HttpServletRequest request){
		User user=userFromRequest(request);
		if(user==null)
			return false;
		return isAdmin(user);
	}
	/**
	 * 是否是超级管理员
	 * @param user
	 * @return
	 */
	public static boolean isAdmin(User user){
		if(user==null)
			return false;
		return (Integer.valueOf(AttrStatic.ROLE_ADMIN).equals(user.getRoleId()));
	}
	/**
	 * 是否是一般管理员
	 * @param request
	 * @return
	 */
	public boolean isCadmin(HttpServletRequest request){
		User user=userFromRequest(request);
		if(user==null)
			return false;
		return isCadmin(user);
	}
	/**
	 * 是否是一般管理员
	 * @param user
	 * @return
	 */
	public static boolean isCadmin(User user){
		if(user==null)
			return false;
		return (Integer.valueOf(AttrStatic.ROLE_CADMIN).equals(user.getRoleId()));
	}
	/**
	 * 是否是个人会员
	 * @param request
	 * @return
	 */
	public boolean isMember(HttpServletRequest request){
		User user=userFromRequest(request);
		if(user==null)
			return false;
		return isMember(user);
	}
	/**
	 * 是否是操作员
	 * @param request
	 * @return
	 */
	public static boolean isMember(User user){
		if(user==null)
			return false;
		return (Integer.valueOf(AttrStatic.ROLE_MEMBER).equals(user.getRoleId()));
	}
}
