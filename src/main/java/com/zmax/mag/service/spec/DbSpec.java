package com.zmax.mag.service.spec;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmax.common.utils.string.Md5PwdEncoder;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.conf.PropMy;
import com.zmax.mag.domain.conf.PropSys;
import com.zmax.mag.service.my.UserService;

@Service
public class DbSpec {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	PropMy propMy;
	@Autowired
	CommonzSvrUitlsSpec commonzSvrUitlsSpec;
	@Autowired
	UserService userService;
	
	
	public  void init(){
		userInit();
	}
	/**
	 * 用户
	 */
	private void userInit(){
		String pwd=StringUtils.isBlank(propMy.getAdminpwd())?"admin123":propMy.getAdminpwd();
		//管理员
		User user=(User)userService.getFirst(null, " username= ?0 ", null, new Object[]{"admin"});
		if(user==null){
			user=new User();
			user.setUsername("admin");
			user.setPassword(pwd);
			user.setNickname("管理员");
			user.setRoleId(0);
			user.setStatus(1);
			try {
				commonzSvrUitlsSpec.commonCreateBefore(new User(), user, "User",null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userService.save(null,user);
		}else{
			user.setPassword(Md5PwdEncoder.encodePassword(pwd));
			userService.update(null, user);
		}
	}
}
