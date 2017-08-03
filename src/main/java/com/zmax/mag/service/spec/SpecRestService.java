package com.zmax.mag.service.spec;

import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmax.common.exception.BoException;
import com.zmax.common.utils.string.DateUtils;
import com.zmax.common.utils.string.SqlUtils;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.dao.base.SpecRepo;
import com.zmax.mag.service.my.*;
import com.zmax.mag.service.utils.PermitCheckUtils;

@Service
public class SpecRestService {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	SpecRepo specRepo;
	@Autowired
	PermitCheckUtils permitCheckUtils;
	@Autowired
	QuestionLinkMemberViewService questionLinkMemberViewService;
	@Autowired
	QuickLinkMemberViewService quickLinkMemberViewService;
	@Autowired
	QuickService quickService;
	@Autowired
	QuestionService questionService;
	@Autowired
	SigninService signinService;
	@Autowired
	MemberService memberService;
	/**
	 * 会员点击付费看问题。
	 * @param tokenUser
	 * @param question
	 * @throws BoException
	 * @throws Exception
	 */
	public void saveQuestionLinkMemberView(User tokenUser,Question question) throws BoException, Exception{
		QuestionLinkMemberView questionLinkMemberView = new QuestionLinkMemberView(
				question.getId() , //Integer 问题内序号   
				tokenUser.getId() , //Integer 观看者   
				0 , //Integer 评论情况   {'0':'以后再评','1':'好评','-1':'差评'}
				null
			);
		questionLinkMemberViewService.saveCreate(null, new QuestionLinkMemberView(), questionLinkMemberView, null, null);
		//观看人数加1
		question.setViewnum(question.getViewnum()+1);
		questionService.update(null, question);
	}
	/**
	 * 会员点击抢答看答案。
	 * @param tokenUser
	 * @param question
	 * @throws BoException
	 * @throws Exception
	 */
	public void saveQuickLinkMemberView(User tokenUser,Quick quick) throws BoException, Exception{
		QuickLinkMemberView quickLinkMemberView = new QuickLinkMemberView(
				quick.getId() , //Integer 抢答ID   
				tokenUser.getId(), //Integer 观看者   
				null
			);
		quickLinkMemberViewService.saveCreate(null, new QuickLinkMemberView(), quickLinkMemberView, null, null);
		//观看人数加1
		quick.setViewnum(quick.getViewnum()+1);
		quickService.update(null, quick);
	}
	/**
	 * 会员签到
	 * @param toUser
	 * @throws BoException
	 * @throws Exception
	 */
	public void saveMemberSign(Member member)throws BoException, Exception{
		Date date=new Date();
		Date nowMorning=DateUtils.thatdayMorning000(date);
		Date nowNight=DateUtils.thatdayNight000(date);
		Signin signinToday=signinService.getFirst(null, "gmtCreate>=?0 and gmtCreate<?1 and memberId=?2", null, new Object[]{nowMorning,nowNight,member.getId()});
		if(signinToday!=null){
			throw new BoException("您今天已经签到过了！");
		}
		//今天没有签过到，所以肯定要new一个了
		Signin signin = new Signin(
			member.getId() , //Integer 父亲 default=0  
			1 , //Integer 顺序号 default=1 1到7，循环 
			null
		);
		Date yestoday=yesterdayThisTime();
		Date yestodayMorning=DateUtils.thatdayMorning000(yestoday);
		Date yestodayNight=DateUtils.thatdayNight000(yestoday);
		Signin signinYestoday=signinService.getFirst(null, "gmtCreate>=?0 and gmtCreate<?1 and memberId=?2", null, new Object[]{yestodayMorning,yestodayNight,member.getId()});
		if(signinYestoday!=null){//昨天没有签到，直接为1就不动了。有签到就去判断下
			Integer daynum=signinYestoday.getSindex();
			if(daynum<7){//小于7，加1
				signin.setSindex(daynum+1);
			}else {//不小于7，就是大于等于7
				signin.setSindex(1);
			}
		}
		signinService.saveCreate(null, new Signin(), signin, null, null);
		if(member.getScore()==null){
			member.setScore(signin.getSindex());
		}else{
			member.setScore(member.getScore()+signin.getSindex());
		}		
		memberService.update(null, member);
	}

	/**
	 * 昨天的这个时候
	 * @return
	 */
	private Date yesterdayThisTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}
}
