package com.zmax.mag.service.spec;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmax.common.conf.AttrStatic;
import com.zmax.common.exception.BoException;
import com.zmax.common.utils.easyui.Json;
import com.zmax.common.utils.string.Md5PwdEncoder;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.mag.domain.bean.Member;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.bean.Wxouser;
import com.zmax.mag.domain.bean.Wxr;
import com.zmax.mag.domain.conf.PropMy;
import com.zmax.mag.service.my.MemberService;
import com.zmax.mag.service.my.UserService;
import com.zmax.mag.service.my.WxouserService;
import com.zmax.mag.service.my.WxrService;
import com.zmax.mag.service.spec.SpecRoleService;
import com.zmax.mag.service.utils.PermitCheckUtils;
import com.zmax.mag.web.controller.restful.entity.ClientInfo;

@Service
public class SpecUserService {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	PropMy propMy;
	@Autowired
	CommonzSvrUitlsSpec commonzSvrUitlsSpec;
	@Autowired
	PermitCheckUtils permitCheckUtils;
	@Autowired
	UserService userService;
	@Autowired
	MemberService memberService;
	@Autowired
	WxrService wxrService;
	@Autowired
	WxouserService wxouserService;
	/**
	 * 登录检查 账密
	 * @param user 页面提交的User
	 * @param isNeedPm 是否要加权限列表
	 * @return new Json(success, msg, {"user",user})
	 * @throws BoException 
	 */
	public Json loginCheck(User user,boolean isNeedPm) throws BoException,Exception {
		String pwd="";
		if(StringUtils.isNotBlank(user.getPassword())&&user.getPassword().length()==32){
			//32位是密文
			pwd=user.getPassword();
		}else{
			//非32位是明文
			pwd=Md5PwdEncoder.encodePassword(user.getPassword());
		}

		User dbuser=userService.getFirst(null, "username = ?0 and password= ?1",null,new Object[]{user.getUsername(),pwd});
		if(dbuser==null){
			
			throw new BoException("账号密码错误！");
		}
		//有些参数是通过objx传递的
		dbuser.setObj1(user.getObj1());
		dbuser.setObj2(user.getObj2());
		dbuser.setObj3(user.getObj3());

		return calcuUserAfterLogin(dbuser,isNeedPm,true);
	}
	/**
	 * 登录检查，之不存在就创建
	 * @param user
	 * @param isNeedPm boolean 是否需要权限列表 
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public Json createLoginCheckCreateOne(User user) throws BoException,Exception {

		User dbuser=userService.getFirst(null, "username = ?0 ",null,new Object[]{user.getUsername()});
		if(dbuser!=null){
			return calcuUserAfterLogin(dbuser,false,true);
		}		
		//不存在，创建
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword("111111");
		}

		createReg(user);
		createRegMemberByUser(user);
		return calcuUserAfterLogin(user,false,true);
	}
	/**
	 * 登录检查，之不存在就创建 存在就抛错
	 * @param user
	 * @param isMd5 0/null明文,1密文
	 * @param isNeedPm boolean 是否需要权限列表 
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public Json createLoginCheckCreateOneOrThrowException(User user) throws BoException,Exception {

		User dbuser=userService.getFirst(null, "username = ?0 ",null,new Object[]{user.getUsername()});
		if(dbuser!=null){
			throw new BoException("用户已经存在");
		}		
		//不存在，创建
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword("111111");
		}

		createReg(user);
		createRegMemberByUser(user);
		return calcuUserAfterLogin(user,false,false);
	}
	/**
	 * 登录时，数据库检查后，对USER进行处理，比如放入权限列表之类
	 * 
	 * @param dbuser 
	 * @param isNeedPm 用户是否加权限列表
	 * @param isNeedRoot 用户是否审核通过判断 如果是注册就不需要判断
	 * 
	 * @return
	 */
	public Json calcuUserAfterLogin(User dbuser,boolean isNeedPm,boolean isNeedRoot) throws BoException,Exception {
		if(isNeedRoot){			
			//会员登录需要判断
			if(dbuser.getRoleId().intValue()==AttrStatic.ROLE_MEMBER){
				if(dbuser.getStatus().intValue()!=1){
					throw new BoException("资格审核中，请稍候！");
				}
			}
		}
		//在spec中，把会员、管理员等用户对象放了进去
		try {
			userService.addObj(null,dbuser);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1);
		}
		if(isNeedPm){
			//权限列表
			dbuser.setPmtmap(permitCheckUtils.takeMapByRoleId(dbuser.getRoleId()));
			if (logger.isDebugEnabled()){
				for(Entry<String, String> entry:dbuser.getPmtmap().entrySet()){ 
					logger.debug(entry.getKey()+"--->"+entry.getValue()); 
				}
			} 

		}
		//specUserService.updateAfterLogin(dbuser);

		Map<String,Object> ret=new HashMap<String, Object>();
		ret.put("user",dbuser);
		return new Json(true,"登录成功！",ret);
	}	

	/**
	 * 注册用户_检查创建
	 * @param user
	 * @param errors
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	public Json createReg(User user) throws BoException,Exception  {
		if(null!=userService.getFirst(null, "username=?0", null, new Object[]{user.getUsername()})){
			throw new BoException("用户名已经存在");
		}

		if(user.getRoleId()==null){
			//放个默认值
			user.setRoleId(AttrStatic.ROLE_MEMBER);
		}
		//下面的角色，不允许通过注册页注册
		if(user.getRoleId().intValue()==AttrStatic.ROLE_ADMIN 
				|| user.getRoleId().intValue()==AttrStatic.ROLE_CADMIN){
			throw new BoException("系统权限限止此项操作，请联系管理员");
		}		

		try {
			userService.saveCreate(null, new User(), user, null, null);
			//specUserService.createDbAfterUser0(user);
			//如果需要审核，把下面注释掉
			//specUserService.createDbAfterUser1(user);	
		} catch (Exception e) {
			logger.error(e);
			return new Json(false,"注册失败！");
		}
		Map<String,Object> ret=new HashMap<String, Object>();
		ret.put("user",user);
		return new Json(true,"注册成功",ret);
	}
	/**
	 * 根据User，自动创建一个Member
	 * @param user
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public Json createRegMemberByUser(User user) throws BoException,Exception  {
		if(user==null || user.getId()==null)
			throw new BoException("用户信息为空");
		Member member=memberService.get(null, user.getId());
		if(null==member){
			//精简构造 个人会员
			member = new Member(
					0 , //Integer 用户类型 default=0 专家可以回答，会员不行 {'0':'会员','1':'专家'}
					0.0 , //Double 咨询费 default=0.0 别人问我问题需要付的钱 
					user.getNickname()==null?user.getUsername():user.getNickname()  , //String 姓名或名称   
					null , //String 简介   
					null , //String 头像图  200x200 
					null,//String 二维码图  200x200,大师才有
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
		Map<String,Object> ret=new HashMap<String, Object>();
		ret.put("user",user);
		return new Json(true,"注册成功",ret);
	}
	/**
	 * wxouser跟wxr与user的关系
	 * @param user
	 * @param clientInfo
	 * @throws BoException
	 * @throws Exception
	 */
	public void wxouserAndWxr(User user,ClientInfo clientInfo) throws BoException, Exception{
		logger.debug("进入wxr关系"+"clientInfo.getOpenid="+clientInfo.getOpenid()+"user.getOpenid="+user.getOpenid());
		String openid="";
		if((clientInfo.getOpenid()==null||("").equals(clientInfo.getOpenid()))&&((user.getOpenid()==null||("").equals(user.getOpenid())))){
			logger.debug("2处openid都为空");
			return;
		}
		if(clientInfo.getOpenid()!=null){
			logger.debug("clientInfo.getOpenid"+clientInfo.getOpenid());
			openid=clientInfo.getOpenid();
		}else{
			logger.debug("user.getOpenid="+user.getOpenid());
			openid=user.getOpenid();
		}
		if(("").equals(openid)){
			logger.debug("还是为空！");
			return;
		}
		if(user.getOpenid()==null||("").equals(user.getOpenid())){//openId不为空
			logger.debug("user的openid为空。user的id="+user.getId());
			user.setOpenid(openid);
			userService.update(null, user);
		}
		Wxr wxr=wxrService.get(null, user.getOpenid());
		if(wxr==null){
			logger.debug("wxr==null");
			wxr=new Wxr(
					null , //String 父openid   
					0 , //Integer 孩子数量 default=0  
					null , //String 二维码  600x600 
					null
				);
			wxr.setUserId(user.getId());
			wxr.setRoleId(5);
			wxr.setId(openid);
			wxrService.save(null, wxr);
		}
		logger.debug("wxouser0");
		Wxouser wxouser=wxouserService.get(null, openid);
		if(wxouser!=null){//为空就不做。因为登录过微信就有wxouser
			logger.debug("wxouser不为空！");
			if(wxouser.getUserId()==null||("").equals(wxouser.getUserId())){
				logger.debug("wxouser设置userId="+user.getId());
				wxouser.setUserId(user.getId());
				wxouserService.update(null, wxouser);
			}
		}else{
			logger.debug("wxouser为空！");
		}
	}
}
