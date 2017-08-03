/*
 * zmax 
 * 
 */


package com.zmax.mag.service.my;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.zmax.common.entity.*;
import com.zmax.common.exception.*;
import com.zmax.common.utils.clazz.*;
import com.zmax.common.utils.easyui.*;
import com.zmax.common.utils.string.SqlUtils;
import com.zmax.common.utils.string.StringUtilz;
import com.zmax.common.conf.AttrStatic;
import com.zmax.mag.service.base.BaseService;
import com.zmax.mag.service.utils.*;
import com.zmax.mag.service.spec.*;
import com.zmax.mag.domain.bean.*;
import com.zmax.mag.domain.dao.base.BaseRepo;
import com.zmax.mag.domain.dao.my.MemberRepo;


/**
 * 会员服务
 * 用到我的有：MemberRelation^MemberId^会员父子关系_父亲,MemberRelation^MemberCh^会员父子关系_孩子,Signin^MemberId^签到_签到者,Article^MemberId^文章_作者内序号,ArticleComment^MemberAu^文章的评论_作者内序号,ArticleComment^MemberCo^文章的评论_评论者内序,Message^MemberFr^短消息_发送者,Message^MemberTo^短消息_接收者,CashHis^MemberId^现金流水_会员,CashmemberStatiDay^MemberId^会员现金日统计_会员,CashmemberStatiMonth^MemberId^会员现金月统计_会员,ScoreHis^MemberId^积分流水_会员,ScorememberStatiDay^MemberId^会员积分日统计_会员,ScorememberStatiMonth^MemberId^会员积分月统计_会员,ExpHis^MemberId^经验流水_会员,ExpmemberStatiDay^MemberId^会员经验日统计_会员,ExpmemberStatiMonth^MemberId^会员经验月统计_会员,OrderrQuestion^MemberId^订单之一对一问题提问_会员,OrderrQuestionFinished^MemberId^订单之一对一问题提问归档_会员,OrderrQuestionDiscard^MemberId^订单之一对一问题提问放弃_会员,OrderrQuick^MemberId^订单之抢答问题提问_会员,OrderrQuickFinished^MemberId^订单之抢答问题提问归档_会员,OrderrQuickDiscard^MemberId^订单之抢答问题提问放弃_会员,OrderrQuestionview^MemberId^订单之一对一问题观看_会员,OrderrQuestionviewFinished^MemberId^订单之一对一问题观看归档_会员,OrderrQuestionviewDiscard^MemberId^订单之一对一问题观看放弃_会员,OrderrQuickview^MemberId^订单之抢答问题观看_会员,OrderrQuickviewFinished^MemberId^订单之抢答问题观看归档_会员,OrderrQuickviewDiscard^MemberId^订单之抢答问题观看放弃_会员,Question^MemberQu^一对一问题_提问者,Question^MemberAn^一对一问题_回答者,QuestionAdd^MemberQu^一对一问题之追加_提问者,QuestionAdd^MemberAn^一对一问题之追加_回答者,QuestionLinkMemberView^MemberQu^观看问题的会员_提问者,QuestionLinkMemberView^MemberAn^观看问题的会员_回答者,QuestionLinkMemberView^MemberVi^观看问题的会员_观看者,Quick^MemberQu^抢答_提问者,QuickTxt^MemberQu^抢答回答_提问者,QuickTxt^MemberAn^抢答回答_回答者,QuickAdd^MemberQu^抢答之追加_提问者,QuickAdd^MemberAn^抢答之追加_回答者,QuickLinkMemberView^MemberQu^观看抢答的会员_提问者,QuickLinkMemberView^MemberVi^观看抢答的会员_观看者,QuickTxtComment^MemberAn^抢答的评价_回答者,QuickTxtComment^MemberVi^抢答的评价_观看者,
 * @author zmax
 */
@Service
public class MemberService extends BaseService<Member> {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	PermitCheckUtils permitCheckUtils;
	/**标准版*/
	@Autowired
	MemberRepo memberRepo;
	/**扩展版*/
	@Autowired
	BaseRepo<Member,Integer> baseRepo;
	@Autowired
	CommonzSvrUitlsSpec commonzSvrUitlsSpec;
	@Autowired
	SpecService specService;


	///////基础版
	/**
	 * 根据ID获取实例
	 * @param sessionUser
	 * @param id
	 * @return 返回相应的持久化PO实例
	 */
	public Member get(User sessionUser,Integer id) {
		if(id==null)
			return null;
		if(id.intValue()==0)
			return null;
		Member obj= memberRepo.findOne(id);
		if(obj==null)
			return null;
		if(!permitCheckUtils.checkObj(sessionUser, obj,"Member")) 
			return null;
		return obj;
	}

	/**
	 * 根据where key='value' 查找第一个对象，如果没有就保存一个，再返回。
	 * @param sessionUser
	 * @param entity 需要保存的对象
	 * @param keyfield 查找的关键字
	 * @param value 关键字对应的值
	 * @param order 排序条件等
	 * @param params 其它参数
	 */
	public Member getByKvIfNotExistsSaveKv(User sessionUser,Member entity,String keyfield,Object value,String order, Object[] params) {
		if(entity==null)
			return null;
		if(!permitCheckUtils.checkObj(sessionUser, entity,"Member")) return entity;
		Member obj=getFirst(sessionUser, keyfield+"='"+value.toString()+"'", order, params);
		if(obj==null){
			save(sessionUser,entity);
			return entity;
		}
		return obj;
	}

	/**
	 * id in (1,2,3) 方式返回列表
	 * @param sessionUser
	 * @param ids
	 * @param morecondition 更多条件 不空则and上 ,ex. bbb=1
	 * @return List
	 */
	public List<Member> listfindByIn(User sessionUser,Integer[] ids,String morecondition) {
		String where=SqlUtils.whereFromIds(ids);
		where=SqlUtils.whereAdd(where,permitCheckUtils.addWhere(sessionUser,"Member"));
		if(StringUtils.isNotBlank(morecondition))
			where=SqlUtils.whereAdd(where, morecondition);
		List<Integer> list=new ArrayList<Integer>();
		return memberRepo.findAll(list);
	}
	

	///////更多LIST在父亲那里

	/**
	 * 保存，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param member
	 */
	public void save(User sessionUser,Member member){
		memberRepo.save(member);
	}
	/**
	 * 更新
	 * @param sessionUser
	 * @param member
	 */
	public void update(User sessionUser,Member member){
		memberRepo.save(member);
	}
	/**
	 * 保存，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param member
	 */
	public void saveOrUpdate(User sessionUser,Member member){
		save(sessionUser,member);
	}
	/**
	 * 保存列表，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param entities
	 */
	public void saveOrUpdateAll(User sessionUser,Collection<Member> entities) {
		if(entities==null)
			return;
		for (Member member : entities) {
			save(sessionUser,member);
		}
	}
	/**
	 * 删除
	 * @param sessionUser
	 * @param id
	 */
	public void deleteById(User sessionUser,Integer id){
		memberRepo.delete(id);
	}
	
	/**
	 * 执行
	 * @param sessionUser
	 * @param jpql
	 * @param params 参数
	 */
	public void execute(User sessionUser,String jpql,Object[] params){
		baseRepo.execute(jpql,params);
	}

	/**
	 * 删除列表
	 * @param sessionUser
	 * @param list
	 * @throws BoException
	 * @throws Exception
	 */
	public void deleteAll(User sessionUser,List<Member> list) throws BoException,Exception{
		for (Member obj : list) {
			deleteOneById(sessionUser, obj.getId());
		}
	}
	
	////////升级版
	/**
	 * 首页
	 * @param sessionUser 
	 * @param obj 在index时会传一个新对象进来，用于添加搜索条件用的列表
	 * @param act 更多的操作 "nullCreate"=如果是空列表创建一个新对象
	 */
	public void csindex(User sessionUser,Member obj,String act) throws BoException,Exception{
		if(StringUtils.equals(act, "nullCreate")){
			if(obj==null)
				throw new Exception("无法创建空对象");
			Member dbobj=get(sessionUser, obj.getId());
			if(dbobj==null){
				//创建_写表前操作之自定义，目的是修改obj
				commonzSvrUitlsSpec.createAndUpdateBefore("create",new Member(), obj,sessionUser);
				save(sessionUser,obj);
			}
		}
		indexAfter(sessionUser,obj); //给搜索用
	}
	
	/**
	 * 表格
	 * @param sessionUser 
	 * @param search
	 * @param ph
	 * @param queryhq
	 * @return
	 */
	public Grid csgrid(User sessionUser,Member search, PageHelper ph,String queryhq) throws BoException,Exception{
		String order=(ph.realSort()+" "+ph.getOrder()).trim();
		List<Object> params = new ArrayList<Object>();
		gridBefore(sessionUser,search);
		String searchwhere=commonzSvrUitlsSpec.makeWhere(sessionUser,search, queryhq, params);
		String where=SqlUtils.whereAdd(searchwhere, queryhq);
		Grid grid = listGrid(sessionUser, where, order, new PageRequest(ph.getPage()-1, ph.getRows()==0?20:ph.getRows()), params.toArray());
		grid.setObj1(queryhq);
		gridAfter(sessionUser,grid.getRows());			
		return grid;
	}

	/**
	 * 创建时的保存
	 * @param sessionUser 
	 * @param oldobj 旧对象，创建时通常是个空对象
	 * @param obj 新对象，也就是需要保存的对象
	 * @param copyfrom 从哪个表复制对象
	 * @param copyfromwhere 复制的条件
	 * @throws Exception
	 */
	public void saveCreate(User sessionUser,Member oldobj,Member obj,String copyfrom,String copyfromwhere) throws BoException,Exception{
		//用于做复制新增的检查
		if(StringUtils.isNotBlank(copyfrom)&&StringUtils.isNotBlank(copyfromwhere)){
			Object objsrc=specService.getFirst(sessionUser,copyfrom, copyfromwhere,null,null);
			commonzSvrUitlsSpec.obj2objcheck(sessionUser,objsrc, obj ,copyfrom ,"Member");
		}		

		//创建_写表前操作之通用
		commonzSvrUitlsSpec.commonCreateBefore(oldobj,obj,"Member",sessionUser);
		//创建_写表前操作之本地
		createAndUpdateBefore(sessionUser,"create",oldobj, obj);
		//创建_写表前操作之自定义，目的是修改obj
		commonzSvrUitlsSpec.createAndUpdateBefore("create",oldobj, obj,sessionUser);

		save(sessionUser,obj);
		//TODO:lang
		//创建_写表后操作之本地
		createAndUpdateAfter(sessionUser,"create",obj,new Member());
		//创建_写表后操作之自定义
		commonzSvrUitlsSpec.createAndUpdateAfter("create",obj,new Member(),sessionUser);
	}
	/**
	 * 更新
	 * 流程：从DB取出DB对象，对提交对象进行修正，将修正对象复制到DB对象，保存
	 * @param sessionUser 
	 * @param obj
	 * @param id 这个id通常是从session中获取的
	 * @throws Exception
	 */
	public void updateUpdate(User sessionUser,Member obj,Integer id) throws BoException,Exception{
		//数据库表里取出的对象，要把页面对象obj里的内容，放进来进行保存
		Member dbobj=get(sessionUser, id);
		if(dbobj==null)
			throw new BoException("数据库中数据找不到");

		//更新_写表前操作之通用，目的是修改obj
		commonzSvrUitlsSpec.commonUpdateBefore(dbobj,obj,"Member",sessionUser);
		//更新_写表前操作之本地，目的是修改obj
		createAndUpdateBefore(sessionUser,"update",dbobj, obj);
		//更新_写表前操作之自定义，目的是修改obj
		commonzSvrUitlsSpec.createAndUpdateBefore("update", dbobj, obj, sessionUser);

		//复制一下老数据库对象，给createAndUpdateAfter用
		Member olddbobj=new Member();
		ClassUtils.beanDeepCopy(dbobj, olddbobj);
		//把页面传进来的对象obj复制到数据库里产生的对象dbobj,之后保存数据库产生的对象dbobj
		ClassUtils.beanDeepCopy(obj, dbobj);
		update(sessionUser,dbobj);

		//更新_写表后操作之本地
		createAndUpdateAfter(sessionUser,"update",dbobj,olddbobj);
		//更新_写表后操作之自定义
		commonzSvrUitlsSpec.createAndUpdateAfter("update",dbobj,olddbobj,sessionUser);

	}
	/**
	 * 新建或更新之完全操作版
	 * @param sessionUser
	 * @param oldobj new Obj() 
	 * @param obj
	 * @throws BoException
	 * @throws Exception
	 */
	public void saveCreateUpdateUpdate(User sessionUser,Member oldobj,Member obj) throws BoException,Exception{
		if(null==obj.getId()){
			saveCreate(sessionUser, oldobj, obj, null, null);
		}else{
			updateUpdate(sessionUser, obj, obj.getId());
		}
	}
	/**
	 * 根据IDS批量更新某些数据
	 * @param sessionUser 
	 * @param ids
	 * @param setfield
	 * @throws Exception
	 */
	public void updateBatchByIds(User sessionUser,String[] ids,String setfield) throws BoException,Exception{
		String hql= "update Member set "+setfield +" where "+StringUtilz.whereIn(ids);
		execute(sessionUser, hql, null);
		commonzSvrUitlsSpec.batchupdateAfter(sessionUser,"Member", setfield, ids);
	}
	/**
	 * 根据ID删除一个
	 * @param sessionUser 
	 * @param id
	 * @throws Exception
	 */
	public void deleteOneById(User sessionUser,Integer id) throws BoException,Exception{
		commonzSvrUitlsSpec.deleteByIdBefore(sessionUser,"Member", id);
		deleteById(sessionUser,id);
		commonzSvrUitlsSpec.deleteByIdAfter(sessionUser,"Member", id);
		//TODO:lang
	}
	/**
	 * 根据IDs删除批量
	 * @param sessionUser 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteBatch(User sessionUser,String[] ids) throws BoException,Exception{
		
		for (String string : ids) {
			Integer id=Integer.valueOf(string);
			commonzSvrUitlsSpec.deleteByIdBefore(sessionUser,"Member", id);
			deleteById(sessionUser,id);
			commonzSvrUitlsSpec.deleteByIdAfter(sessionUser,"Member", id);
		}
	}
	/**
	 * 详情
	 * @param sessionUser 
	 * @param id
	 * @return
	 */
	public Member csshow(User sessionUser,Integer id) throws BoException,Exception{
		Member member=get(sessionUser, id);
		showAfter(sessionUser,member);
		return (member==null)?new Member():member;
	}
	/**
	 * 新建GET
	 * @param sessionUser 
	 * @param member 初始用户
	 * @param copyfrom 从其它表复制的表名
	 * @param copyfromwhere 从其它表复制的条件
	 * @return
	 */
	public Member csnewget(User sessionUser,Member member,String copyfrom,String copyfromwhere) throws BoException,Exception{
		if(member==null)
			member=new Member();
		//如果是从其它表复制过来的
		if(StringUtils.isNotBlank(copyfrom)&&StringUtils.isNotBlank(copyfromwhere)){
			Object objsrc=specService.getFirst(sessionUser,copyfrom, copyfromwhere, null,null);
			commonzSvrUitlsSpec.obj2obj(sessionUser,objsrc, member,copyfrom ,"Member");
			member.setId(null);
		}
		commonzSvrUitlsSpec.newgetBefore(sessionUser,member);
		newgetAfter(sessionUser,member);
		return member;
	}
	/**
	 * 根据id从数据库取，没有的话创建一个新的对象，并且保存完整版到数据库中
	 * @param sessionUser
	 * @param id
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public Member getOrNew(User sessionUser,Integer id) throws BoException,Exception{
		Member obj=get(sessionUser, id);
		if(obj==null){
			obj = new Member(
				0 , //Integer 用户类型 default=0 专家/大师可以回答，会员不行 {'0':'会员','1':'大师'}
				0.0 , //Double 咨询费 default=0.0 别人问我问题需要付的钱 
				null , //String 姓名或名称   
				null , //String 简介   
				null , //String 头像图  200x200 
				null , //String 二维码图  200x200,大师才有 
				0 , //Integer 类型之个人企业 default=0 用这个区分是企业还是个人 {'0':'个人','1':'企业'}
				null , //String 证件号码  企业是营业执照，个人是身份证 
				null , //String 地址   
				null , //String 邮编   
				null , //String 手机   
				null , //String 电子邮件   
				0 , //Integer 积分 default=0 积分余额，是通过流水和统计算出来的 
				0 , //Integer 余额 default=0 单位分，现金余额，是通过流水和统计算出来的 
				0 , //Integer 经验 default=0 单位分，是通过流水和统计算出来的 
				0 , //Integer 等级 default=0 通过经验计算出 
				null
			);
			obj.setId(id);
			save(sessionUser,obj);
		}
		return obj;
	}
	/**
	 * 修改时的请求
	 * @param sessionUser 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Member csedit(User sessionUser,Integer id) throws BoException,Exception{
		
		Member member=get(sessionUser, id);
		if(member==null){
			throw new Exception("数据找不到");
		}
			
		commonzSvrUitlsSpec.editBefore(sessionUser,member);
		editAfter(sessionUser,member);
		return member;
	}
	////////下面是系列补充与检查

	/**
	 * 创建更新，写表前，如计算总价之类，通常都是一样的操作
	 * @param sessionUser
	 * @param simb
	 * @param dbobj update更新时是数据库里取出的老对象，create创建时是个new Object()
	 * @param obj 主要目的是修改这个对象
	 * @throws BoException
	 * @throws Exception
	 */
	public void createAndUpdateBefore(User sessionUser,String simb,Member dbobj,Member obj) throws BoException,Exception{
		if(obj==null)return;
		//create update 共用
		//create 独有
		if("create".equals(simb)){
		}
		//update 独有
		if("update".equals(simb)){
			
		}
	}	
	/**
	 * 创建更新，有可能要改变其它表，或本表的其它数据，通常都是一样的操作
	 * @param sessionUser
	 * @param simb
	 * @param obj 已经db保存过的对象
	 * @param olddbobj update更新时是复制过的数据库老对象，create创建时是个new Object()
	 * @throws BoException
	 * @throws Exception
	 */
	public void createAndUpdateAfter(User sessionUser,String simb,Member obj,Member olddbobj) throws BoException,Exception{
		if(obj==null)return;
		//create 独有
		if("create".equals(simb)){
		}
		//update 独有
		if("update".equals(simb)){
			updateRedundancyToChildren(sessionUser, obj);
		}
	}	


	/**
	 * 获取index，创建新对象后，返回页面之前
	 * @param obj
	 */
	public void indexAfter(User sessionUser,Member obj) throws BoException,Exception{
		addList(sessionUser,obj); //给搜索用
		commonzSvrUitlsSpec.indexAfter(sessionUser,obj);
	}	
	/**
	 * 列表，读库前
	 * @param obj
	 */
	public void gridBefore(User sessionUser,Member obj) throws BoException,Exception{
		if(obj==null)return;
		commonzSvrUitlsSpec.gridBefore(sessionUser,obj);

	}	
	/**
	 * 列表，读库后
	 * @param list
	 */
	public void gridAfter(User sessionUser,List<Member> list) throws BoException,Exception{
		if(list==null||list.size()==0) return;
		for (int i = 0; i < list.size(); i++) {
			gridOneAfter(sessionUser,list.get(i));	
		}
		commonzSvrUitlsSpec.gridAfter(sessionUser,list);
	}
	/**
	 * 列表中的单项，读库后
	 * @param obj
	 */
	public void gridOneAfter(User sessionUser,Member obj) throws BoException,Exception{
		if(obj==null)return;
		addString(sessionUser,obj);	
		
	}	
	/**
	 * show，读库后
	 * @param obj
	 */
	public void showAfter(User sessionUser,Member obj) throws BoException,Exception{
		if(obj==null)return;
		addObj(sessionUser,obj);
		addString(sessionUser,obj);
		addMore(sessionUser,obj);
		commonzSvrUitlsSpec.show(sessionUser,obj);
	}
	/**
	 * newGet，对象产生之后，返回页面之前，可能还会在对象中放一些允许用户修改的，从其它表复制过来的数据，目的是为了方便用户
	 * @param obj
	 * @throws Exception
	 */
	public void newgetAfter(User sessionUser,Member obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		addList(sessionUser,obj);
		addString(sessionUser,obj);
		commonzSvrUitlsSpec.newgetAfter(sessionUser,obj);
	}	
	/**
	 * Edit Get，获取对象之后，返回页面之前
	 * @param obj
	 */
	public void editAfter(User sessionUser,Member obj) throws BoException,Exception{
		if(obj==null)return;
		addList(sessionUser,obj);
		addString(sessionUser,obj);	
		commonzSvrUitlsSpec.editAfter(sessionUser,obj);
	}
	
	////////下面是各类补充
	/**
	 * 加上各类对象
	 * @param sessionUser
	 * @param obj
	 * @throws BoException
	 * @throws Exception
	 */
	public void addObj(User sessionUser,Member obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		commonzSvrUitlsSpec.addObj(sessionUser,obj);		
	}

	/**
	 * 给对象加上list,map，用于radio选择，combox搜索
	 * @param sessionUser
	 * @param obj
	 * @throws BoException
	 * @throws Exception
	 */
	public void addList(User sessionUser,Member obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		//这个以后要去掉的
		obj.setMtypeMap((Map)MapDb.getInstance().getMap("Member.Mtype"));
		//这个以后要去掉的
		obj.setIdtypeMap((Map)MapDb.getInstance().getMap("Member.Idtype"));
		commonzSvrUitlsSpec.addList(sessionUser,obj);
	}
	/**
	 * 加更多
	 * @param sessionUser
	 * @param obj Member
	 * @throws BoException
	 * @throws Exception
	 */
	public void addMore(User sessionUser,Member obj) throws BoException,Exception{
		if(obj==null)return;
		commonzSvrUitlsSpec.addMore(sessionUser,obj);
	}
	/**
	 * 加文本
	 * @param sessionUser
	 * @param obj
	 * @throws BoException
	 * @throws Exception
	 */
	public void addString(User sessionUser,Member obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		String value="";
		value=(String)MapDb.getInstance().getMapString("Member.Mtype", ""+obj.getMtype());
		if(StringUtils.isEmpty(value))
			value=""+obj.getMtype();
		obj.setMtypeString(value);
		value=(String)MapDb.getInstance().getMapString("Member.Idtype", ""+obj.getIdtype());
		if(StringUtils.isEmpty(value))
			value=""+obj.getIdtype();
		obj.setIdtypeString(value);
		commonzSvrUitlsSpec.addString(sessionUser,obj);
	}
	/**
	 * 我更新后，冗余我的孩子们也更新一下
	 * @param sessionUser
	 * @param member Member 父亲
	 */
	public void updateRedundancyToChildren(User sessionUser,Member member){
		//OrderrQuestion
		orderrQuestionService.updateRedundancyFromMember(sessionUser, member);
		//OrderrQuestionFinished
		orderrQuestionFinishedService.updateRedundancyFromMember(sessionUser, member);
		//OrderrQuestionDiscard
		orderrQuestionDiscardService.updateRedundancyFromMember(sessionUser, member);
		//OrderrQuick
		orderrQuickService.updateRedundancyFromMember(sessionUser, member);
		//OrderrQuickFinished
		orderrQuickFinishedService.updateRedundancyFromMember(sessionUser, member);
		//OrderrQuickDiscard
		orderrQuickDiscardService.updateRedundancyFromMember(sessionUser, member);
		//OrderrQuestionview
		orderrQuestionviewService.updateRedundancyFromMember(sessionUser, member);
		//OrderrQuestionviewFinished
		orderrQuestionviewFinishedService.updateRedundancyFromMember(sessionUser, member);
		//OrderrQuestionviewDiscard
		orderrQuestionviewDiscardService.updateRedundancyFromMember(sessionUser, member);
		//OrderrQuickview
		orderrQuickviewService.updateRedundancyFromMember(sessionUser, member);
		//OrderrQuickviewFinished
		orderrQuickviewFinishedService.updateRedundancyFromMember(sessionUser, member);
		//OrderrQuickviewDiscard
		orderrQuickviewDiscardService.updateRedundancyFromMember(sessionUser, member);
		//Question
		questionService.updateRedundancyFromMember(sessionUser, member);
		commonzSvrUitlsSpec.updateRedundancyToChildren(sessionUser, member);
	}

	@Autowired
	MemberRelationService memberRelationService;
	@Autowired
	SigninService signinService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ArticleCommentService articleCommentService;
	@Autowired
	MessageService messageService;
	@Autowired
	CashHisService cashHisService;
	@Autowired
	CashmemberStatiDayService cashmemberStatiDayService;
	@Autowired
	CashmemberStatiMonthService cashmemberStatiMonthService;
	@Autowired
	ScoreHisService scoreHisService;
	@Autowired
	ScorememberStatiDayService scorememberStatiDayService;
	@Autowired
	ScorememberStatiMonthService scorememberStatiMonthService;
	@Autowired
	ExpHisService expHisService;
	@Autowired
	ExpmemberStatiDayService expmemberStatiDayService;
	@Autowired
	ExpmemberStatiMonthService expmemberStatiMonthService;
	@Autowired
	OrderrQuestionService orderrQuestionService;
	@Autowired
	OrderrQuestionFinishedService orderrQuestionFinishedService;
	@Autowired
	OrderrQuestionDiscardService orderrQuestionDiscardService;
	@Autowired
	OrderrQuickService orderrQuickService;
	@Autowired
	OrderrQuickFinishedService orderrQuickFinishedService;
	@Autowired
	OrderrQuickDiscardService orderrQuickDiscardService;
	@Autowired
	OrderrQuestionviewService orderrQuestionviewService;
	@Autowired
	OrderrQuestionviewFinishedService orderrQuestionviewFinishedService;
	@Autowired
	OrderrQuestionviewDiscardService orderrQuestionviewDiscardService;
	@Autowired
	OrderrQuickviewService orderrQuickviewService;
	@Autowired
	OrderrQuickviewFinishedService orderrQuickviewFinishedService;
	@Autowired
	OrderrQuickviewDiscardService orderrQuickviewDiscardService;
	@Autowired
	QuestionService questionService;
	@Autowired
	QuestionAddService questionAddService;
	@Autowired
	QuestionLinkMemberViewService questionLinkMemberViewService;
	@Autowired
	QuickService quickService;
	@Autowired
	QuickTxtService quickTxtService;
	@Autowired
	QuickAddService quickAddService;
	@Autowired
	QuickLinkMemberViewService quickLinkMemberViewService;
	@Autowired
	QuickTxtCommentService quickTxtCommentService;

}
