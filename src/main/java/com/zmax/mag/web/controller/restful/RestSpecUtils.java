package com.zmax.mag.web.controller.restful;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.db.dialect.DBUtil;

import com.zmax.common.exception.BoException;
import com.zmax.common.exception.NeedLoginException;
import com.zmax.common.exception.RightException;
import com.zmax.common.utils.easyui.PageHelper;
import com.zmax.common.utils.string.DateUtils;
import com.zmax.mag.domain.bean.Article;
import com.zmax.mag.domain.bean.Member;
import com.zmax.mag.domain.bean.Question;
import com.zmax.mag.domain.bean.QuestionLinkMemberView;
import com.zmax.mag.domain.bean.QuestionTxt;
import com.zmax.mag.domain.bean.Quick;
import com.zmax.mag.domain.bean.QuickLinkMemberView;
import com.zmax.mag.domain.bean.QuickTxt;
import com.zmax.mag.domain.bean.QuickTxtComment;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.conf.PropMy;
import com.zmax.mag.domain.conf.PropSys;
import com.zmax.mag.service.my.MemberService;
import com.zmax.mag.service.my.QuestionLinkMemberViewService;
import com.zmax.mag.service.my.QuestionService;
import com.zmax.mag.service.my.QuestionTxtService;
import com.zmax.mag.service.my.QuickLinkMemberViewService;
import com.zmax.mag.service.my.QuickService;
import com.zmax.mag.service.my.QuickTxtCommentService;
import com.zmax.mag.service.my.QuickTxtService;
import com.zmax.mag.service.my.UserService;
import com.zmax.mag.service.spec.SpecRoleService;
import com.zmax.mag.web.controller.restful.entity.ClientInfo;
import com.zmax.mag.web.controller.restful.entity.RestPage;

/**
 * RestFul控制
 * 
 * 
 *
 */
@Component
public class RestSpecUtils {

	/** 日志实例 */
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	SpecRoleService specRoleService;
	@Autowired
	RestUtils restUtils;
	@Autowired
	PropMy propMy;
	@Autowired
	PropSys propSys;
	@Autowired
	MemberService memberService;
	@Autowired
	UserService userService;
	@Autowired
	QuestionService questionService;
	@Autowired
	QuestionTxtService questionTxtService;
	@Autowired
	QuestionLinkMemberViewService questionLinkMemberViewService;
	@Autowired
	QuickService quickService;
	@Autowired
	QuickTxtService quickTxtService;
	@Autowired
	QuickLinkMemberViewService quickLinkMemberViewService;
	@Autowired
	QuickTxtCommentService quickTxtCommentService;

	/**
	 * 查询前做的事
	 * 
	 * @param request
	 * @param clientInfo
	 * @param tokenUser
	 * @param clazzName
	 * @param ph
	 * @param restPage
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void queryBefore(HttpServletRequest request, ClientInfo clientInfo,
			RestPage restPage, User tokenUser, String clazzName, PageHelper ph)
			throws BoException, NeedLoginException, RightException, Exception {
		if ("Orderr".equals(clazzName)) {
		}
		if ("Question".equals(clazzName)) {

		}

	}

	/**
	 * 查询后做的事
	 * 
	 * @param request
	 * @param clientInfo
	 * @param tokenUser
	 * @param clazzName
	 * @param ph
	 * @param restPage
	 * @param list
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void queryAfter(HttpServletRequest request, ClientInfo clientInfo,
			RestPage restPage, User tokenUser, String clazzName, PageHelper ph,
			List list) throws BoException, NeedLoginException, RightException,
			Exception {
		if (list == null || list.size() == 0) {
			return;
		}
		// 用户
		if (list.get(0) instanceof User) {
				System.out.println(tokenUser);
		}
		// 抢答回答
		if (list.get(0) instanceof QuickTxt) {
			for (int i = 0; i < list.size(); i++) {
				QuickTxt quickTxt = (QuickTxt) list.get(i);
				QuickTxtComment quickTxtComment = quickTxtCommentService
						.getFirst(null, "quickTxtId=?0", null,
								new Object[] { quickTxt.getId() });
				if (quickTxtComment == null) {
					quickTxtComment = new QuickTxtComment(quickTxt.getId(), // Integer
																			// 抢答回答ID
							tokenUser.getId(), // Integer 观看者 也就是评论者
							0, // Integer 评论情况 {'0':'以后再评','1':'好评','-1':'差评'}
							null);
					quickTxtCommentService.saveCreate(null,
							new QuickTxtComment(), quickTxtComment, "", "");
				}

				quickTxt.setObj1(quickTxtComment);
			}
		}
		if(list.get(0) instanceof Quick){
			//在获取列表时，canread就要被赋上值，如果canrad=true答案也一起被发过来了。
			List<QuickLinkMemberView> listQuickLinkMemberViews=quickLinkMemberViewService.listfind(null, "memberVi=?0", null, new Object[]{tokenUser.getId()});
			for (int i = 0; i < list.size(); i++) {
				Quick quick = (Quick) list.get(i);
				for (int j = 0; j < listQuickLinkMemberViews.size(); j++) {
					if(quick.getId().intValue()==listQuickLinkMemberViews.get(j).getQuickId().intValue()||
							quick.getMemberQu().intValue()==tokenUser.getId().intValue()||quick.getViewprice().equals(new Double(0))){
						quick.setCanread(true);
						List<QuickTxt> listQuickTxtList=quickTxtService.listfind(null, "quickId=?0", null, new Object[]{quick.getId()});
						quick.setListQuickTxt(listQuickTxtList);
						continue;
					}
				}
			}			
		}
		//crz  自己发布的悬赏不需要支付就可以观看
		/*if(list.get(0) instanceof Quick){
			int mid=tokenUser.getId();
			for (int i = 0; i < list.size(); i++) {
				Quick quick = (Quick)list.get(i);
				if(quick.getMemberQu().intValue()==mid){
					quick.setCanread(true);
					List<QuickTxt> listQuickTxtList=quickTxtService.listfind(null, "quickId=?0", null, new Object[]{quick.getId()});
					quick.setListQuickTxt(listQuickTxtList);
				}
			}
		}*/
		if(list.get(0) instanceof Question){
			//在获取列表时，canread就要被赋上值，如果canrad=true答案也一起被发过来了。
			List<QuestionLinkMemberView> listQuestionLinkMemberViewList=questionLinkMemberViewService.listfind(null, "memberVi=?0", null, new Object[]{tokenUser.getId()});
			for (int i = 0; i < list.size(); i++) {
				Question question = (Question) list.get(i);
				for (int j = 0; j < listQuestionLinkMemberViewList.size(); j++) {
					if(question.getId().intValue()==listQuestionLinkMemberViewList.get(j).getQuestionId().intValue()||
							question.getMemberQu().intValue()==tokenUser.getId().intValue()||question.getMemberAn().intValue()==tokenUser.getId().intValue() ||question.getViewprice().equals(new Double(0))){
						question.setCanread(true);
						QuestionTxt questionTxt=questionTxtService.get(null, question.getId());
						question.setQuestionTxt(questionTxt);
						continue;
					}
				}
			}
		}
		//crz  自己发布的问答不需要支付就可以观看
		/*if(list.get(0) instanceof Question){
			int mid=tokenUser.getId();
			for (int i = 0; i < list.size(); i++) {
				Question question = (Question)list.get(i);
				if(question.getMemberQu().intValue()==mid){
					question.setCanread(true);
					QuestionTxt questionTxt=questionTxtService.get(null, question.getId());
					question.setQuestionTxt(questionTxt);
				}
			}
		}*/

	}

	/**
	 * Get前做的事
	 * 
	 * @param request
	 * @param clientInfo
	 * @param tokenUser
	 * @param clazzName
	 * @param id
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void ggetBefore(HttpServletRequest request, ClientInfo clientInfo,
			RestPage restPage, User tokenUser, String clazzName, Serializable id)
			throws BoException, NeedLoginException, RightException, Exception {

	}

	/**
	 * Get后做的事
	 * 
	 * @param request
	 * @param clientInfo
	 * @param tokenUser
	 * @param obj
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void ggetAfter(HttpServletRequest request, ClientInfo clientInfo,
			RestPage restPage, User tokenUser, Object obj) throws BoException,
			NeedLoginException, RightException, Exception {

		// 会员
		if (obj instanceof Member) {
			Member member = (Member) obj;
			memberService.addList(tokenUser, member);

		}
	}

	/**
	 * GetEdit前做的事
	 * 
	 * @param request
	 * @param clientInfo
	 * @param tokenUser
	 * @param clazzName
	 * @param id
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void ggeteditBefore(HttpServletRequest request,
			ClientInfo clientInfo, RestPage restPage, User tokenUser,
			String clazzName, Serializable id) throws BoException,
			NeedLoginException, RightException, Exception {

	}

	/**
	 * GetEdit后做的事
	 * 
	 * @param request
	 * @param clientInfo
	 * @param tokenUser
	 * @param obj
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void ggeteditAfter(HttpServletRequest request,
			ClientInfo clientInfo, RestPage restPage, User tokenUser, Object obj)
			throws BoException, NeedLoginException, RightException, Exception {

	}

	/**
	 * new Get 之后做的事
	 * 
	 * @param request
	 * @param clientInfo
	 * @param restPage
	 * @param tokenUser
	 * @param obj
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws RightException
	 * @throws Exception
	 */
	public void newgetAfter(HttpServletRequest request, ClientInfo clientInfo,
			RestPage restPage, User tokenUser, Object obj) throws BoException,
			NeedLoginException, RightException, Exception {

	}

	/**
	 * REST创建前要做的事
	 * 
	 * @param request
	 * @param clientInfo
	 * @param tokenUser
	 * @param obj
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void createBefore(HttpServletRequest request, ClientInfo clientInfo,
			RestPage restPage, User tokenUser, Object obj) throws BoException,
			NeedLoginException, RightException, Exception {
		// 会员
		if (obj instanceof Member) {
		}
		//悬赏 @author sjia
		if(obj instanceof Quick)
		{
			Quick quick=(Quick)obj;
			//悬赏时间 1今天 2明天 3后天 
			int quickDate=Integer.parseInt((String)quick.getObj1());
			//悬赏天数 1天 2天 3天
			int quickDays=Integer.parseInt((String)quick.getObj2());
			//今天早上0点
			Date today0=DateUtils.thatdayMorning000(new Date());
			//悬赏开始时间处理
			switch(quickDate){
			//1 今天  将quick的gmtStart 设置为今天早上0:00
			case 1:
				quick.setGmtStart(today0);
				break;
			//2  明天  将quick的gmtStart 设置为明天早上0:00
			case 2:
				quick.setGmtStart(DateUtils.dateAddDay(today0, 1));
				break;
			//3 后天 将quick的gmtStart 设置为后天早上0:00
			case 3:
				quick.setGmtStart(DateUtils.dateAddDay(today0, 2));
				break;
			//默认设置为今天早上0:00
			default:
				quick.setGmtStart(today0);
				break;
			}
			
			//悬赏结束时间处理
			if(quickDays!=0){
				quick.setGmtOver(DateUtils.dateAddDay(quick.getGmtStart(), quickDays));
			}else{
				//遇到特殊情况 默认设置悬赏天数是一天
				quick.setGmtOver(DateUtils.dateAddDay(quick.getGmtStart(), 1));
			}
			quickService.update(null, quick);
		}
	}

	/**
	 * REST创建后要做的事
	 * 
	 * @param request
	 * @param clientInfo
	 * @param tokenUser
	 * @param obj
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void createAfter(HttpServletRequest request, ClientInfo clientInfo,
			RestPage restPage, User tokenUser, Object obj) throws BoException,
			NeedLoginException, RightException, Exception {
		// 开单
		if (obj instanceof User) {
		}
		if (obj instanceof QuestionLinkMemberView) {// 观看问题会员创建一条，该问题被查看加1.question_link_member_view
			QuestionLinkMemberView questionLinkMemberView = (QuestionLinkMemberView) obj;
			Question questionDb = questionService.get(null,
					questionLinkMemberView.getQuestionId());
			questionDb.setViewnum(questionDb.getViewnum() + 1);
			questionService.update(null, questionDb);
		}
		if (obj instanceof QuickLinkMemberView) {// 观看抢答的会员创建一条，抢答中的观看人数加1
			QuickLinkMemberView quickLinkMemberView = (QuickLinkMemberView) obj;
			Quick quickDb = quickService.get(null,
					quickLinkMemberView.getQuickId());
			quickDb.setViewnum(quickDb.getViewnum() + 1);
			quickService.update(null, quickDb);
		}
		if (obj instanceof QuickTxt) {// 抢答回答创建一个，抢答的回答人数加1
			QuickTxt quickTxt = (QuickTxt) obj;
			Quick quickDb = quickService.get(null, quickTxt.getQuickId());
			quickDb.setAnswernum(quickDb.getAnswernum() + 1);
			quickService.update(null, quickDb);
		}
		if (obj instanceof QuestionTxt) {// 如果有人回答了问题，就把问题变成已回答状态！
			QuestionTxt questionTxt = (QuestionTxt) obj;
			Question question = questionService.get(null, questionTxt.getId());
			question.setStatus(2);
			questionService.update(null, question);
		}
	}

	/**
	 * REST更新前要做的事
	 * 
	 * @param request
	 * @param clientInfo
	 * @param tokenUser
	 * @param obj
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void updateBefore(HttpServletRequest request, ClientInfo clientInfo,
			RestPage restPage, User tokenUser, Object obj) throws BoException,
			NeedLoginException, RightException, Exception {
		if (obj instanceof User) {
			User user = (User) obj;
			User userDB = userService.get(null, tokenUser.getId());
			if (user.getUsername() != null
					&& !userDB.getUsername().equals(user.getUsername())) {
				throw new BoException("账号不一致！");
			}
		}
		// crz 设置中奖状态
		if (obj instanceof Quick) {
			Quick quick = (Quick) obj;

			String objString = (String) quick.getObj1();
			if(objString != null){
			String winId = objString.substring(1, objString.length() - 1);
			String[] winIds = winId.split(",");
			for (String string : winIds) {
				QuickTxt qt = quickTxtService.get(tokenUser,
						Integer.valueOf(string));
				qt.setStatusWin(1);
			}
			quick.setStatus(3);
			}
		}
	}

	/**
	 * REST更新后要做的事
	 * 
	 * @param request
	 * @param clientInfo
	 * @param tokenUser
	 * @param obj
	 * @throws BoException
	 * @throws NeedLoginException
	 * @throws Exception
	 */
	public void updateAfter(HttpServletRequest request, ClientInfo clientInfo,
			RestPage restPage, User tokenUser, Object obj) throws BoException,
			NeedLoginException, RightException, Exception {
		if (obj instanceof User) {
			// /////////会员
			if (specRoleService.isMember(tokenUser)) {
				// 会员取消订单后 发个消息
				// specYjCartOrderrService.createDmmsgAfterMemberAbandonedOrderr(tokenUser,
				// (Orderr)obj);
			}
		}
		if (obj instanceof QuestionTxt) {// 如果是对“一对一问题回答”的修改。那么把问题长度放进去
			QuestionTxt questionTxt = (QuestionTxt) obj;
			Question questionDb = questionService
					.get(null, questionTxt.getId());
			questionDb.setAnswerwords(questionTxt.getAnswer().toString()
					.length());
			questionService.update(null, questionDb);
		}/*
		 * if(obj instanceof QuickLinkMemberView){//如果是对“观看问题的会员”的修改。
		 * QuickLinkMemberView quickLinkMemberView=(QuickLinkMemberView)obj;
		 * QuickLinkMemberView
		 * quickLinkMemberViewDB=quickLinkMemberViewService.get(null,
		 * quickLinkMemberView.getId()); Quick quickDB=quickService.get(null,
		 * quickLinkMemberView.getQuickId());
		 * if(quickLinkMemberView.getCtype().intValue
		 * ()>quickLinkMemberViewDB.getCtype
		 * ().intValue()||quickLinkMemberView.getCtype
		 * ().intValue()<quickLinkMemberViewDB.getCtype().intValue()){
		 * if(quickLinkMemberViewDB.getCtype().intValue()==0){//原来没有评价
		 * if(quickLinkMemberView.getCtype().intValue()==1){//现在是好评，就好评加1
		 * quickDB.setGoodnum(quickDB.getGoodnum()+1); }else{//现在是差评，差评加1
		 * quickDB.setBadnum(quickDB.getBadnum()+1); } }else{//原来有评价
		 * if(quickLinkMemberView
		 * .getCtype().intValue()==1){//如果现在是好评，那说明之前是差评。那好评加1，差评减1
		 * quickDB.setGoodnum(quickDB.getGoodnum()+1);
		 * quickDB.setBadnum(quickDB.getBadnum()-1);
		 * }else{//现在是差评，那说明之前是好评。那现在差评加1，好评减1
		 * quickDB.setGoodnum(quickDB.getGoodnum()-1);
		 * quickDB.setBadnum(quickDB.getBadnum()+1); } } }
		 * quickService.update(null, quickDB); } if(obj instanceof
		 * QuickTxtComment){//如果是对“抢答的评价”的修改。 QuickTxtComment
		 * quickTxtComment=(QuickTxtComment)obj; QuickTxtComment
		 * quickTxtCommentDB=quickTxtCommentService.get(null,
		 * quickTxtComment.getId()); QuickTxt
		 * quickTxtDB=quickTxtService.get(null,
		 * quickTxtComment.getQuickTxtId());
		 * if(quickTxtComment.getCtype().intValue
		 * ()>quickTxtCommentDB.getCtype().
		 * intValue()||quickTxtComment.getCtype()
		 * .intValue()<quickTxtCommentDB.getCtype().intValue()){
		 * if(quickTxtCommentDB.getCtype().intValue()==0){//原来没有评价
		 * if(quickTxtComment.getCtype().intValue()==1){//现在是好评，就好评加1
		 * quickTxtDB.setGoodnum(quickTxtDB.getGoodnum()+1); }else{//现在是差评，差评加1
		 * quickTxtDB.setBadnum(quickTxtDB.getBadnum()+1); } }else{//原来有评价
		 * if(quickTxtComment
		 * .getCtype().intValue()==1){//如果现在是好评，那说明之前是差评。那好评加1，差评减1
		 * quickTxtDB.setGoodnum(quickTxtDB.getGoodnum()+1);
		 * quickTxtDB.setBadnum(quickTxtDB.getBadnum()-1);
		 * }else{//现在是差评，那说明之前是好评。那现在差评加1，好评减1
		 * quickTxtDB.setGoodnum(quickTxtDB.getGoodnum()-1);
		 * quickTxtDB.setBadnum(quickTxtDB.getBadnum()+1); } } }
		 * quickTxtService.update(null, quickTxtDB); }
		 */
	}

	/**
	 * @author crz
	 * @param i
	 *            几天后开始悬赏 1==now 2==明天零点 3==后天零点
	 * @return
	 */
	public void formatTime(Quick quick) {
		Date date = new Date();
		Calendar right = Calendar.getInstance();
		right.setTime(date);
		Date d1 = null;
		// 1表示即可发布 ,!=1表示以后的0点发布
		String temp = (String) quick.getObj1();
		if (temp.equals(1)) {
			// 1表示现在发送
			d1 = right.getTime();
			quick.setGmtStart(d1);
			right.add(
					Calendar.DAY_OF_YEAR,
					Integer.parseInt((String) quick.getObj1()) - 1
							+ Integer.parseInt((String) quick.getObj2()));
			d1 = right.getTime();
			quick.setGmtOver(d1);

		} else {

			right.add(Calendar.DAY_OF_YEAR,
					Integer.parseInt((String) quick.getObj1()) - 1);
			d1 = right.getTime();
			d1.setSeconds(0);
			d1.setHours(0);
			d1.setMinutes(0);
			quick.setGmtStart(d1);
			System.out.println(d1);
			right.add(Calendar.DAY_OF_YEAR,
					Integer.parseInt((String) quick.getObj2()));
			d1 = right.getTime();
			d1.setSeconds(0);
			d1.setHours(0);
			d1.setMinutes(0);
			quick.setGmtOver(d1);

		}

	}
	/**
	 * 
	 * @param exp 经验
	 * @return 等级
	 */
	public int expTranLev(int exp){
		int lev=0;
		int need=100;
		while(lev<=99){
			if(exp>=need){
				if(lev==99){
					return 99;
				}
				lev+=1;
				exp-=need;
				if((lev+1)%10==1){
					need*=2;
				}
			}else{
				return lev;
			}
		}
		return lev;
	}
}
