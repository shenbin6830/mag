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
import com.zmax.mag.domain.dao.my.QuestionLinkMemberViewRepo;


/**
 * 观看问题的会员服务
 * 用到我的有：
 * @author zmax
 */
@Service
public class QuestionLinkMemberViewService extends BaseService<QuestionLinkMemberView> {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	PermitCheckUtils permitCheckUtils;
	/**标准版*/
	@Autowired
	QuestionLinkMemberViewRepo questionLinkMemberViewRepo;
	/**扩展版*/
	@Autowired
	BaseRepo<QuestionLinkMemberView,Integer> baseRepo;
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
	public QuestionLinkMemberView get(User sessionUser,Integer id) {
		if(id==null)
			return null;
		if(id.intValue()==0)
			return null;
		QuestionLinkMemberView obj= questionLinkMemberViewRepo.findOne(id);
		if(obj==null)
			return null;
		if(!permitCheckUtils.checkObj(sessionUser, obj,"QuestionLinkMemberView")) 
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
	public QuestionLinkMemberView getByKvIfNotExistsSaveKv(User sessionUser,QuestionLinkMemberView entity,String keyfield,Object value,String order, Object[] params) {
		if(entity==null)
			return null;
		if(!permitCheckUtils.checkObj(sessionUser, entity,"QuestionLinkMemberView")) return entity;
		QuestionLinkMemberView obj=getFirst(sessionUser, keyfield+"='"+value.toString()+"'", order, params);
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
	public List<QuestionLinkMemberView> listfindByIn(User sessionUser,Integer[] ids,String morecondition) {
		String where=SqlUtils.whereFromIds(ids);
		where=SqlUtils.whereAdd(where,permitCheckUtils.addWhere(sessionUser,"QuestionLinkMemberView"));
		if(StringUtils.isNotBlank(morecondition))
			where=SqlUtils.whereAdd(where, morecondition);
		List<Integer> list=new ArrayList<Integer>();
		return questionLinkMemberViewRepo.findAll(list);
	}
	

	///////更多LIST在父亲那里

	/**
	 * 保存，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param questionLinkMemberView
	 */
	public void save(User sessionUser,QuestionLinkMemberView questionLinkMemberView){
		questionLinkMemberViewRepo.save(questionLinkMemberView);
	}
	/**
	 * 更新
	 * @param sessionUser
	 * @param questionLinkMemberView
	 */
	public void update(User sessionUser,QuestionLinkMemberView questionLinkMemberView){
		questionLinkMemberViewRepo.save(questionLinkMemberView);
	}
	/**
	 * 保存，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param questionLinkMemberView
	 */
	public void saveOrUpdate(User sessionUser,QuestionLinkMemberView questionLinkMemberView){
		save(sessionUser,questionLinkMemberView);
	}
	/**
	 * 保存列表，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param entities
	 */
	public void saveOrUpdateAll(User sessionUser,Collection<QuestionLinkMemberView> entities) {
		if(entities==null)
			return;
		for (QuestionLinkMemberView questionLinkMemberView : entities) {
			save(sessionUser,questionLinkMemberView);
		}
	}
	/**
	 * 删除
	 * @param sessionUser
	 * @param id
	 */
	public void deleteById(User sessionUser,Integer id){
		questionLinkMemberViewRepo.delete(id);
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
	public void deleteAll(User sessionUser,List<QuestionLinkMemberView> list) throws BoException,Exception{
		for (QuestionLinkMemberView obj : list) {
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
	public void csindex(User sessionUser,QuestionLinkMemberView obj,String act) throws BoException,Exception{
		if(StringUtils.equals(act, "nullCreate")){
			if(obj==null)
				throw new Exception("无法创建空对象");
			QuestionLinkMemberView dbobj=get(sessionUser, obj.getId());
			if(dbobj==null){
				//创建_写表前操作之自定义，目的是修改obj
				commonzSvrUitlsSpec.createAndUpdateBefore("create",new QuestionLinkMemberView(), obj,sessionUser);
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
	public Grid csgrid(User sessionUser,QuestionLinkMemberView search, PageHelper ph,String queryhq) throws BoException,Exception{
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
	public void saveCreate(User sessionUser,QuestionLinkMemberView oldobj,QuestionLinkMemberView obj,String copyfrom,String copyfromwhere) throws BoException,Exception{
		//用于做复制新增的检查
		if(StringUtils.isNotBlank(copyfrom)&&StringUtils.isNotBlank(copyfromwhere)){
			Object objsrc=specService.getFirst(sessionUser,copyfrom, copyfromwhere,null,null);
			commonzSvrUitlsSpec.obj2objcheck(sessionUser,objsrc, obj ,copyfrom ,"QuestionLinkMemberView");
		}		

		//创建_写表前操作之通用
		commonzSvrUitlsSpec.commonCreateBefore(oldobj,obj,"QuestionLinkMemberView",sessionUser);
		//创建_写表前操作之本地
		createAndUpdateBefore(sessionUser,"create",oldobj, obj);
		//创建_写表前操作之自定义，目的是修改obj
		commonzSvrUitlsSpec.createAndUpdateBefore("create",oldobj, obj,sessionUser);

		save(sessionUser,obj);
		//TODO:lang
		//创建_写表后操作之本地
		createAndUpdateAfter(sessionUser,"create",obj,new QuestionLinkMemberView());
		//创建_写表后操作之自定义
		commonzSvrUitlsSpec.createAndUpdateAfter("create",obj,new QuestionLinkMemberView(),sessionUser);
	}
	/**
	 * 更新
	 * 流程：从DB取出DB对象，对提交对象进行修正，将修正对象复制到DB对象，保存
	 * @param sessionUser 
	 * @param obj
	 * @param id 这个id通常是从session中获取的
	 * @throws Exception
	 */
	public void updateUpdate(User sessionUser,QuestionLinkMemberView obj,Integer id) throws BoException,Exception{
		//数据库表里取出的对象，要把页面对象obj里的内容，放进来进行保存
		QuestionLinkMemberView dbobj=get(sessionUser, id);
		if(dbobj==null)
			throw new BoException("数据库中数据找不到");

		//更新_写表前操作之通用，目的是修改obj
		commonzSvrUitlsSpec.commonUpdateBefore(dbobj,obj,"QuestionLinkMemberView",sessionUser);
		//更新_写表前操作之本地，目的是修改obj
		createAndUpdateBefore(sessionUser,"update",dbobj, obj);
		//更新_写表前操作之自定义，目的是修改obj
		commonzSvrUitlsSpec.createAndUpdateBefore("update", dbobj, obj, sessionUser);

		//复制一下老数据库对象，给createAndUpdateAfter用
		QuestionLinkMemberView olddbobj=new QuestionLinkMemberView();
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
	public void saveCreateUpdateUpdate(User sessionUser,QuestionLinkMemberView oldobj,QuestionLinkMemberView obj) throws BoException,Exception{
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
		String hql= "update QuestionLinkMemberView set "+setfield +" where "+StringUtilz.whereIn(ids);
		execute(sessionUser, hql, null);
		commonzSvrUitlsSpec.batchupdateAfter(sessionUser,"QuestionLinkMemberView", setfield, ids);
	}
	/**
	 * 根据ID删除一个
	 * @param sessionUser 
	 * @param id
	 * @throws Exception
	 */
	public void deleteOneById(User sessionUser,Integer id) throws BoException,Exception{
		commonzSvrUitlsSpec.deleteByIdBefore(sessionUser,"QuestionLinkMemberView", id);
		deleteById(sessionUser,id);
		commonzSvrUitlsSpec.deleteByIdAfter(sessionUser,"QuestionLinkMemberView", id);
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
			commonzSvrUitlsSpec.deleteByIdBefore(sessionUser,"QuestionLinkMemberView", id);
			deleteById(sessionUser,id);
			commonzSvrUitlsSpec.deleteByIdAfter(sessionUser,"QuestionLinkMemberView", id);
		}
	}
	/**
	 * 详情
	 * @param sessionUser 
	 * @param id
	 * @return
	 */
	public QuestionLinkMemberView csshow(User sessionUser,Integer id) throws BoException,Exception{
		QuestionLinkMemberView questionLinkMemberView=get(sessionUser, id);
		showAfter(sessionUser,questionLinkMemberView);
		return (questionLinkMemberView==null)?new QuestionLinkMemberView():questionLinkMemberView;
	}
	/**
	 * 新建GET
	 * @param sessionUser 
	 * @param questionLinkMemberView 初始用户
	 * @param copyfrom 从其它表复制的表名
	 * @param copyfromwhere 从其它表复制的条件
	 * @return
	 */
	public QuestionLinkMemberView csnewget(User sessionUser,QuestionLinkMemberView questionLinkMemberView,String copyfrom,String copyfromwhere) throws BoException,Exception{
		if(questionLinkMemberView==null)
			questionLinkMemberView=new QuestionLinkMemberView();
		//如果是从其它表复制过来的
		if(StringUtils.isNotBlank(copyfrom)&&StringUtils.isNotBlank(copyfromwhere)){
			Object objsrc=specService.getFirst(sessionUser,copyfrom, copyfromwhere, null,null);
			commonzSvrUitlsSpec.obj2obj(sessionUser,objsrc, questionLinkMemberView,copyfrom ,"QuestionLinkMemberView");
			questionLinkMemberView.setId(null);
		}
		commonzSvrUitlsSpec.newgetBefore(sessionUser,questionLinkMemberView);
		newgetAfter(sessionUser,questionLinkMemberView);
		return questionLinkMemberView;
	}
	/**
	 * 根据id从数据库取，没有的话创建一个新的对象，并且保存完整版到数据库中
	 * @param sessionUser
	 * @param id
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public QuestionLinkMemberView getOrNew(User sessionUser,Integer id) throws BoException,Exception{
		QuestionLinkMemberView obj=get(sessionUser, id);
		if(obj==null){
			obj = new QuestionLinkMemberView(
				null , //Integer 问题内序号   
				null , //Integer 观看者   
				null , //Integer 评论情况   {'0':'以后再评','1':'好评','-1':'差评'}
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
	public QuestionLinkMemberView csedit(User sessionUser,Integer id) throws BoException,Exception{
		
		QuestionLinkMemberView questionLinkMemberView=get(sessionUser, id);
		if(questionLinkMemberView==null){
			throw new Exception("数据找不到");
		}
			
		commonzSvrUitlsSpec.editBefore(sessionUser,questionLinkMemberView);
		editAfter(sessionUser,questionLinkMemberView);
		return questionLinkMemberView;
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
	public void createAndUpdateBefore(User sessionUser,String simb,QuestionLinkMemberView dbobj,QuestionLinkMemberView obj) throws BoException,Exception{
		if(obj==null)return;
		//create update 共用
		//冗余
		Question obj3=questionService.get(sessionUser, obj.getQuestionId());
		if(obj3!=null){
			obj.setQuestionIdQuestionObj(obj3);
			obj.setMemberQu(obj3.getMemberQu());
		}
		//冗余
		Question obj4=questionService.get(sessionUser, obj.getQuestionId());
		if(obj4!=null){
			obj.setQuestionIdQuestionObj(obj4);
			obj.setMemberAn(obj4.getMemberAn());
		}
		//冗余
		Question obj5=questionService.get(sessionUser, obj.getQuestionId());
		if(obj5!=null){
			obj.setQuestionIdQuestionObj(obj5);
			obj.setViewprice(obj5.getViewprice());
		}
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
	public void createAndUpdateAfter(User sessionUser,String simb,QuestionLinkMemberView obj,QuestionLinkMemberView olddbobj) throws BoException,Exception{
		if(obj==null)return;
		//create 独有
		if("create".equals(simb)){
		}
		//update 独有
		if("update".equals(simb)){
		}
	}	


	/**
	 * 获取index，创建新对象后，返回页面之前
	 * @param obj
	 */
	public void indexAfter(User sessionUser,QuestionLinkMemberView obj) throws BoException,Exception{
		addList(sessionUser,obj); //给搜索用
		commonzSvrUitlsSpec.indexAfter(sessionUser,obj);
	}	
	/**
	 * 列表，读库前
	 * @param obj
	 */
	public void gridBefore(User sessionUser,QuestionLinkMemberView obj) throws BoException,Exception{
		if(obj==null)return;
		commonzSvrUitlsSpec.gridBefore(sessionUser,obj);

	}	
	/**
	 * 列表，读库后
	 * @param list
	 */
	public void gridAfter(User sessionUser,List<QuestionLinkMemberView> list) throws BoException,Exception{
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
	public void gridOneAfter(User sessionUser,QuestionLinkMemberView obj) throws BoException,Exception{
		if(obj==null)return;
		addString(sessionUser,obj);	
		
	}	
	/**
	 * show，读库后
	 * @param obj
	 */
	public void showAfter(User sessionUser,QuestionLinkMemberView obj) throws BoException,Exception{
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
	public void newgetAfter(User sessionUser,QuestionLinkMemberView obj) throws BoException,Exception{
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
	public void editAfter(User sessionUser,QuestionLinkMemberView obj) throws BoException,Exception{
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
	public void addObj(User sessionUser,QuestionLinkMemberView obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		obj.setQuestionIdQuestionObj(questionService.get(sessionUser,obj.getQuestionId()));
		obj.setMemberQuMemberObj(memberService.get(sessionUser,obj.getMemberQu()));
		obj.setMemberAnMemberObj(memberService.get(sessionUser,obj.getMemberAn()));
		obj.setMemberViMemberObj(memberService.get(sessionUser,obj.getMemberVi()));
		commonzSvrUitlsSpec.addObj(sessionUser,obj);		
	}

	/**
	 * 给对象加上list,map，用于radio选择，combox搜索
	 * @param sessionUser
	 * @param obj
	 * @throws BoException
	 * @throws Exception
	 */
	public void addList(User sessionUser,QuestionLinkMemberView obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		//这个以后要去掉的
		obj.setCtypeMap((Map)MapDb.getInstance().getMap("QuestionLinkMemberView.Ctype"));
		commonzSvrUitlsSpec.addList(sessionUser,obj);
	}
	/**
	 * 加更多
	 * @param sessionUser
	 * @param obj QuestionLinkMemberView
	 * @throws BoException
	 * @throws Exception
	 */
	public void addMore(User sessionUser,QuestionLinkMemberView obj) throws BoException,Exception{
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
	public void addString(User sessionUser,QuestionLinkMemberView obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		String value="";
		Question obj6=(Question)(questionService.get(sessionUser,obj.getQuestionId()));
		if(obj6!=null){
			obj.setQuestionIdString(obj6.getMyname());
			obj.setQuestionIdStringid(obj6.getMynameid());
		}

		Member obj7=memberService.get(sessionUser,obj.getMemberQu());
		if(obj7!=null){
			obj.setMemberQuString(obj7.getMyname());
			obj.setMemberQuStringid(obj7.getMynameid());
		}
		Member obj8=memberService.get(sessionUser,obj.getMemberAn());
		if(obj8!=null){
			obj.setMemberAnString(obj8.getMyname());
			obj.setMemberAnStringid(obj8.getMynameid());
		}
		Member obj9=(Member)(memberService.get(sessionUser,obj.getMemberVi()));
		if(obj9!=null){
			obj.setMemberViString(obj9.getMyname());
			obj.setMemberViStringid(obj9.getMynameid());
		}

		value=(String)MapDb.getInstance().getMapString("QuestionLinkMemberView.Ctype", ""+obj.getCtype());
		if(StringUtils.isEmpty(value))
			value=""+obj.getCtype();
		obj.setCtypeString(value);
		commonzSvrUitlsSpec.addString(sessionUser,obj);
	}
	/**
	 * 从父亲Question那里，批量冗余我的数据
	 * @param sessionUser
	 * @param question Question 
	 */
	public void updateRedundancyFromQuestion(User sessionUser,Question question){
		execute(sessionUser, "update QuestionLinkMemberView set  memberQu=?0 , memberAn=?1 , viewprice=?2 where questionId=?3", new Object[]{question.getMemberQu(),question.getMemberAn(),question.getViewprice(), question.getId()});
		commonzSvrUitlsSpec.updateRedundancyFromFather(sessionUser, "QuestionLinkMemberView", question);
	}

	@Autowired
	QuestionService questionService;
	@Autowired
	MemberService memberService;

}
