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
import com.zmax.mag.domain.dao.my.OrderrQuestionRepo;

import org.apache.poi.hssf.usermodel.*;

/**
 * 订单之一对一问题提问服务
 * 用到我的有：
 * @author zmax
 */
@Service
public class OrderrQuestionService extends BaseService<OrderrQuestion> {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	PermitCheckUtils permitCheckUtils;
	/**标准版*/
	@Autowired
	OrderrQuestionRepo orderrQuestionRepo;
	/**扩展版*/
	@Autowired
	BaseRepo<OrderrQuestion,Integer> baseRepo;
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
	public OrderrQuestion get(User sessionUser,Integer id) {
		if(id==null)
			return null;
		if(id.intValue()==0)
			return null;
		OrderrQuestion obj= orderrQuestionRepo.findOne(id);
		if(obj==null)
			return null;
		if(!permitCheckUtils.checkObj(sessionUser, obj,"OrderrQuestion")) 
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
	public OrderrQuestion getByKvIfNotExistsSaveKv(User sessionUser,OrderrQuestion entity,String keyfield,Object value,String order, Object[] params) {
		if(entity==null)
			return null;
		if(!permitCheckUtils.checkObj(sessionUser, entity,"OrderrQuestion")) return entity;
		OrderrQuestion obj=getFirst(sessionUser, keyfield+"='"+value.toString()+"'", order, params);
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
	public List<OrderrQuestion> listfindByIn(User sessionUser,Integer[] ids,String morecondition) {
		String where=SqlUtils.whereFromIds(ids);
		where=SqlUtils.whereAdd(where,permitCheckUtils.addWhere(sessionUser,"OrderrQuestion"));
		if(StringUtils.isNotBlank(morecondition))
			where=SqlUtils.whereAdd(where, morecondition);
		List<Integer> list=new ArrayList<Integer>();
		return orderrQuestionRepo.findAll(list);
	}
	

	///////更多LIST在父亲那里

	/**
	 * 保存，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param orderrQuestion
	 */
	public void save(User sessionUser,OrderrQuestion orderrQuestion){
		orderrQuestionRepo.save(orderrQuestion);
	}
	/**
	 * 更新
	 * @param sessionUser
	 * @param orderrQuestion
	 */
	public void update(User sessionUser,OrderrQuestion orderrQuestion){
		orderrQuestionRepo.save(orderrQuestion);
	}
	/**
	 * 保存，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param orderrQuestion
	 */
	public void saveOrUpdate(User sessionUser,OrderrQuestion orderrQuestion){
		save(sessionUser,orderrQuestion);
	}
	/**
	 * 保存列表，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param entities
	 */
	public void saveOrUpdateAll(User sessionUser,Collection<OrderrQuestion> entities) {
		if(entities==null)
			return;
		for (OrderrQuestion orderrQuestion : entities) {
			save(sessionUser,orderrQuestion);
		}
	}
	/**
	 * 删除
	 * @param sessionUser
	 * @param id
	 */
	public void deleteById(User sessionUser,Integer id){
		orderrQuestionRepo.delete(id);
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
	public void deleteAll(User sessionUser,List<OrderrQuestion> list) throws BoException,Exception{
		for (OrderrQuestion obj : list) {
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
	public void csindex(User sessionUser,OrderrQuestion obj,String act) throws BoException,Exception{
		if(StringUtils.equals(act, "nullCreate")){
			if(obj==null)
				throw new Exception("无法创建空对象");
			OrderrQuestion dbobj=get(sessionUser, obj.getId());
			if(dbobj==null){
				//创建_写表前操作之自定义，目的是修改obj
				commonzSvrUitlsSpec.createAndUpdateBefore("create",new OrderrQuestion(), obj,sessionUser);
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
	public Grid csgrid(User sessionUser,OrderrQuestion search, PageHelper ph,String queryhq) throws BoException,Exception{
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
	public void saveCreate(User sessionUser,OrderrQuestion oldobj,OrderrQuestion obj,String copyfrom,String copyfromwhere) throws BoException,Exception{
		//用于做复制新增的检查
		if(StringUtils.isNotBlank(copyfrom)&&StringUtils.isNotBlank(copyfromwhere)){
			Object objsrc=specService.getFirst(sessionUser,copyfrom, copyfromwhere,null,null);
			commonzSvrUitlsSpec.obj2objcheck(sessionUser,objsrc, obj ,copyfrom ,"OrderrQuestion");
		}		

		//创建_写表前操作之通用
		commonzSvrUitlsSpec.commonCreateBefore(oldobj,obj,"OrderrQuestion",sessionUser);
		//创建_写表前操作之本地
		createAndUpdateBefore(sessionUser,"create",oldobj, obj);
		//创建_写表前操作之自定义，目的是修改obj
		commonzSvrUitlsSpec.createAndUpdateBefore("create",oldobj, obj,sessionUser);

		save(sessionUser,obj);
		//TODO:lang
		//创建_写表后操作之本地
		createAndUpdateAfter(sessionUser,"create",obj,new OrderrQuestion());
		//创建_写表后操作之自定义
		commonzSvrUitlsSpec.createAndUpdateAfter("create",obj,new OrderrQuestion(),sessionUser);
	}
	/**
	 * 更新
	 * 流程：从DB取出DB对象，对提交对象进行修正，将修正对象复制到DB对象，保存
	 * @param sessionUser 
	 * @param obj
	 * @param id 这个id通常是从session中获取的
	 * @throws Exception
	 */
	public void updateUpdate(User sessionUser,OrderrQuestion obj,Integer id) throws BoException,Exception{
		//数据库表里取出的对象，要把页面对象obj里的内容，放进来进行保存
		OrderrQuestion dbobj=get(sessionUser, id);
		if(dbobj==null)
			throw new BoException("数据库中数据找不到");

		//更新_写表前操作之通用，目的是修改obj
		commonzSvrUitlsSpec.commonUpdateBefore(dbobj,obj,"OrderrQuestion",sessionUser);
		//更新_写表前操作之本地，目的是修改obj
		createAndUpdateBefore(sessionUser,"update",dbobj, obj);
		//更新_写表前操作之自定义，目的是修改obj
		commonzSvrUitlsSpec.createAndUpdateBefore("update", dbobj, obj, sessionUser);

		//复制一下老数据库对象，给createAndUpdateAfter用
		OrderrQuestion olddbobj=new OrderrQuestion();
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
	public void saveCreateUpdateUpdate(User sessionUser,OrderrQuestion oldobj,OrderrQuestion obj) throws BoException,Exception{
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
		String hql= "update OrderrQuestion set "+setfield +" where "+StringUtilz.whereIn(ids);
		execute(sessionUser, hql, null);
		commonzSvrUitlsSpec.batchupdateAfter(sessionUser,"OrderrQuestion", setfield, ids);
	}
	/**
	 * 根据ID删除一个
	 * @param sessionUser 
	 * @param id
	 * @throws Exception
	 */
	public void deleteOneById(User sessionUser,Integer id) throws BoException,Exception{
		commonzSvrUitlsSpec.deleteByIdBefore(sessionUser,"OrderrQuestion", id);
		deleteById(sessionUser,id);
		commonzSvrUitlsSpec.deleteByIdAfter(sessionUser,"OrderrQuestion", id);
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
			commonzSvrUitlsSpec.deleteByIdBefore(sessionUser,"OrderrQuestion", id);
			deleteById(sessionUser,id);
			commonzSvrUitlsSpec.deleteByIdAfter(sessionUser,"OrderrQuestion", id);
		}
	}
	/**
	 * excel表格
	 * @param sessionUser 
	 * @param search
	 * @param ph
	 * @param queryhq
	 * @param act
	 * @return
	 */
	public HSSFWorkbook csexcelexport(User sessionUser,OrderrQuestion search, PageHelper ph,String queryhq,String act) throws BoException,Exception{
		String order=(ph.realSort()+" "+ph.getOrder()).trim();
		List<Object> params = new ArrayList<Object>();
		gridBefore(sessionUser,search);
		String excelwhere=commonzSvrUitlsSpec.makeExportExcelQuery(sessionUser,"OrderrQuestion",queryhq, act, params);
		String where=StringUtilz.whereAdd(excelwhere, queryhq);
		int pageNo=1;
		int pageSize=100;
		
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("导出1");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式    
        HSSFCell cell=null;
        int nowrow=0;
         //excel表格第一行，插入的是字段英文名。
        HSSFRow row = sheet.createRow(nowrow++);
        String[] fielde={"Id","GmtCreate","GmtCreateString","GmtPay","GmtPayString","Status","StatusString","ItypePay","ItypePayString","MemberId","MemberIdString","Name","Mobile","QuestionId","QuestionIdString","Title","Price","Paywxh5",""};
        for (int i = 0; i < fielde.length; i++) {
        	cell=row.createCell(i);
	        cell.setCellStyle(style);
	        cell.setCellValue(fielde[i]);  
		}
        //excel表格第一行，插入的是字段英文名。
        row = sheet.createRow(nowrow++);
        String[] fieldc={"序号ID","创建时间","创建时间","支付时间","支付时间","支付状态","支付状态","支付方式","支付方式","会员","会员","姓名","手机","一对一问题ID","一对一问题ID","问题","总价","微信支付H5对象",""};
        for (int i = 0; i < fieldc.length; i++) {
        	cell=row.createCell(i);
	        cell.setCellStyle(style);
	        cell.setCellValue(fieldc[i]);  
		}	  
        
        //开始插入数据
		while(true){
			Grid page = listGrid(sessionUser, where, order, new PageRequest(pageNo-1, pageSize), params.toArray());
			List<OrderrQuestion> list=page.getRows();
			
			for (OrderrQuestion orderrQuestion : list) {
				addString(sessionUser, orderrQuestion);
		        row = sheet.createRow(nowrow++);
		        int nowcol=0;
		        //放序号ID
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				if(orderrQuestion.getId()!=null)
					cell.setCellValue(orderrQuestion.getId());  
		        //放创建时间
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				if(orderrQuestion.getGmtCreate()!=null)
					cell.setCellValue(orderrQuestion.getGmtCreate());  
		        //放创建时间String
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				cell.setCellValue(orderrQuestion.getGmtCreateString());  
		        //放支付时间
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				if(orderrQuestion.getGmtPay()!=null)
					cell.setCellValue(orderrQuestion.getGmtPay());  
		        //放支付时间String
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				cell.setCellValue(orderrQuestion.getGmtPayString());  
		        //放支付状态
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				if(orderrQuestion.getStatus()!=null)
					cell.setCellValue(orderrQuestion.getStatus());  
		        //放支付状态String
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				cell.setCellValue(orderrQuestion.getStatusString());  
		        //放支付方式
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				if(orderrQuestion.getItypePay()!=null)
					cell.setCellValue(orderrQuestion.getItypePay());  
		        //放支付方式String
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				cell.setCellValue(orderrQuestion.getItypePayString());  
		        //放会员
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				if(orderrQuestion.getMemberId()!=null)
					cell.setCellValue(orderrQuestion.getMemberId());  
		        //放会员String
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				cell.setCellValue(orderrQuestion.getMemberIdString());  
		        //放姓名
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				if(orderrQuestion.getName()!=null)
					cell.setCellValue(orderrQuestion.getName());  
		        //放手机
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				if(orderrQuestion.getMobile()!=null)
					cell.setCellValue(orderrQuestion.getMobile());  
		        //放一对一问题ID
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				if(orderrQuestion.getQuestionId()!=null)
					cell.setCellValue(orderrQuestion.getQuestionId());  
		        //放一对一问题IDString
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				cell.setCellValue(orderrQuestion.getQuestionIdString());  
		        //放问题
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				if(orderrQuestion.getTitle()!=null)
					cell.setCellValue(orderrQuestion.getTitle());  
		        //放总价
	        	cell=row.createCell(nowcol++);
		        cell.setCellStyle(style);
				if(orderrQuestion.getPrice()!=null)
					cell.setCellValue(orderrQuestion.getPrice());  
			}
			
			
			pageNo++;
			if(page.getRows().size()<pageSize)
				break;
		}
		return wb;
	}
	/**
	 * 详情
	 * @param sessionUser 
	 * @param id
	 * @return
	 */
	public OrderrQuestion csshow(User sessionUser,Integer id) throws BoException,Exception{
		OrderrQuestion orderrQuestion=get(sessionUser, id);
		showAfter(sessionUser,orderrQuestion);
		return (orderrQuestion==null)?new OrderrQuestion():orderrQuestion;
	}
	/**
	 * 新建GET
	 * @param sessionUser 
	 * @param orderrQuestion 初始用户
	 * @param copyfrom 从其它表复制的表名
	 * @param copyfromwhere 从其它表复制的条件
	 * @return
	 */
	public OrderrQuestion csnewget(User sessionUser,OrderrQuestion orderrQuestion,String copyfrom,String copyfromwhere) throws BoException,Exception{
		if(orderrQuestion==null)
			orderrQuestion=new OrderrQuestion();
		//如果是从其它表复制过来的
		if(StringUtils.isNotBlank(copyfrom)&&StringUtils.isNotBlank(copyfromwhere)){
			Object objsrc=specService.getFirst(sessionUser,copyfrom, copyfromwhere, null,null);
			commonzSvrUitlsSpec.obj2obj(sessionUser,objsrc, orderrQuestion,copyfrom ,"OrderrQuestion");
			orderrQuestion.setId(null);
		}
		commonzSvrUitlsSpec.newgetBefore(sessionUser,orderrQuestion);
		newgetAfter(sessionUser,orderrQuestion);
		return orderrQuestion;
	}
	/**
	 * 根据id从数据库取，没有的话创建一个新的对象，并且保存完整版到数据库中
	 * @param sessionUser
	 * @param id
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public OrderrQuestion getOrNew(User sessionUser,Integer id) throws BoException,Exception{
		OrderrQuestion obj=get(sessionUser, id);
		if(obj==null){
			obj = new OrderrQuestion(
				null , //Date 支付时间   
				0 , //Integer 支付状态 default=0  {'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}
				0 , //Integer 支付方式 default=0  {'0':'余额支付','2':'微信支付','3':'支付宝支付'}
				null , //Integer 会员   
				null , //Integer 一对一问题ID   
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
	public OrderrQuestion csedit(User sessionUser,Integer id) throws BoException,Exception{
		
		OrderrQuestion orderrQuestion=get(sessionUser, id);
		if(orderrQuestion==null){
			throw new Exception("数据找不到");
		}
			
		commonzSvrUitlsSpec.editBefore(sessionUser,orderrQuestion);
		editAfter(sessionUser,orderrQuestion);
		return orderrQuestion;
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
	public void createAndUpdateBefore(User sessionUser,String simb,OrderrQuestion dbobj,OrderrQuestion obj) throws BoException,Exception{
		if(obj==null)return;
		//create update 共用
		//冗余
		Member obj3=memberService.get(sessionUser, obj.getMemberId());
		if(obj3!=null){
			obj.setMemberIdMemberObj(obj3);
			obj.setName(obj3.getName());
		}
		//冗余
		Member obj4=memberService.get(sessionUser, obj.getMemberId());
		if(obj4!=null){
			obj.setMemberIdMemberObj(obj4);
			obj.setMobile(obj4.getMobile());
		}
		//冗余
		Question obj5=questionService.get(sessionUser, obj.getQuestionId());
		if(obj5!=null){
			obj.setQuestionIdQuestionObj(obj5);
			obj.setTitle(obj5.getTitle());
		}
		//冗余
		Question obj6=questionService.get(sessionUser, obj.getQuestionId());
		if(obj6!=null){
			obj.setQuestionIdQuestionObj(obj6);
			obj.setPrice(obj6.getPrice());
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
	public void createAndUpdateAfter(User sessionUser,String simb,OrderrQuestion obj,OrderrQuestion olddbobj) throws BoException,Exception{
		if(obj==null)return;
		//create 独有
		if("create".equals(simb)){
		}
		//update 独有
		if("update".equals(simb)){
		}
	}	

	@Autowired
	OrderrQuestionFinishedService orderrQuestionFinishedService;
	/**
	 * 完成
	 * @param request
	 * @param response
	 * @param model
	 * @param finishWhere 完成的hql表达式
	 */
	public void createFinished(User sessionUser,String finishWhere) throws BoException,Exception{
		finishWhere=(StringUtils.isBlank(finishWhere))?commonzSvrUitlsSpec.defaultFinishHql("OrderrQuestion"):finishWhere;
		List<OrderrQuestion> list=listfind(sessionUser, finishWhere, null, null);
		if(list==null || list.size()<1){
			return;
		}
		List<OrderrQuestionFinished> listFinished=new ArrayList<OrderrQuestionFinished>();
		for (OrderrQuestion orderrQuestion : list) {
			OrderrQuestionFinished obj=new OrderrQuestionFinished();
			ClassUtils.beanDeepCopy(orderrQuestion, obj);
			listFinished.add(obj);
			commonzSvrUitlsSpec.finishBefore(sessionUser, obj ,finishWhere);
		}
		orderrQuestionFinishedService.saveOrUpdateAll(sessionUser,listFinished);
		deleteAll(sessionUser, list);
	}
	/**
	 * 带Finish的列表
	 * @param sessionUser
	 * @param hqlb
	 * @param where
	 * @param hqle
	 * @param params
	 * @return
	 */
	public List<OrderrQuestion> listfindWithFinished(User sessionUser,String hqlb,String where,String order,Object[] params){
		List<OrderrQuestion> list=listfind(sessionUser, where, order, params);
		List<OrderrQuestionFinished> listFinished=orderrQuestionFinishedService.listfind(sessionUser, where, order, params);
		for (OrderrQuestionFinished objFinished : listFinished) {
			OrderrQuestion obj=new OrderrQuestion();
			ClassUtils.beanDeepCopy(objFinished, obj);
			list.add(obj);
		}
		return list;
	}
	@Autowired
	OrderrQuestionDiscardService orderrQuestionDiscardService;
	/**
	 * 放弃
	 * @param request
	 * @param response
	 * @param model
	 * @param discardWhere 完成的hql表达式
	 */
	public void createDiscard(User sessionUser,String discardWhere) throws BoException,Exception{
		discardWhere=(StringUtils.isBlank(discardWhere))?commonzSvrUitlsSpec.defaultDiscardHql("OrderrQuestion"):discardWhere;
		List<OrderrQuestion> list=listfind(sessionUser, discardWhere, null, null);
		if(list==null || list.size()<1){
			return;
		}
		List<OrderrQuestionDiscard> listDiscard=new ArrayList<OrderrQuestionDiscard>();
		for (OrderrQuestion orderrQuestion : list) {
			OrderrQuestionDiscard obj=new OrderrQuestionDiscard();
			ClassUtils.beanDeepCopy(orderrQuestion, obj);
			listDiscard.add(obj);
			commonzSvrUitlsSpec.discardBefore(sessionUser, obj ,discardWhere);
		}
		orderrQuestionDiscardService.saveOrUpdateAll(sessionUser,listDiscard);
		deleteAll(sessionUser, list);
	}

	/**
	 * 获取index，创建新对象后，返回页面之前
	 * @param obj
	 */
	public void indexAfter(User sessionUser,OrderrQuestion obj) throws BoException,Exception{
		addList(sessionUser,obj); //给搜索用
		commonzSvrUitlsSpec.indexAfter(sessionUser,obj);
	}	
	/**
	 * 列表，读库前
	 * @param obj
	 */
	public void gridBefore(User sessionUser,OrderrQuestion obj) throws BoException,Exception{
		if(obj==null)return;
		commonzSvrUitlsSpec.gridBefore(sessionUser,obj);

	}	
	/**
	 * 列表，读库后
	 * @param list
	 */
	public void gridAfter(User sessionUser,List<OrderrQuestion> list) throws BoException,Exception{
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
	public void gridOneAfter(User sessionUser,OrderrQuestion obj) throws BoException,Exception{
		if(obj==null)return;
		addString(sessionUser,obj);	
		
	}	
	/**
	 * show，读库后
	 * @param obj
	 */
	public void showAfter(User sessionUser,OrderrQuestion obj) throws BoException,Exception{
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
	public void newgetAfter(User sessionUser,OrderrQuestion obj) throws BoException,Exception{
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
	public void editAfter(User sessionUser,OrderrQuestion obj) throws BoException,Exception{
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
	public void addObj(User sessionUser,OrderrQuestion obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		obj.setMemberIdMemberObj(memberService.get(sessionUser,obj.getMemberId()));
		obj.setQuestionIdQuestionObj(questionService.get(sessionUser,obj.getQuestionId()));
		commonzSvrUitlsSpec.addObj(sessionUser,obj);		
	}

	/**
	 * 给对象加上list,map，用于radio选择，combox搜索
	 * @param sessionUser
	 * @param obj
	 * @throws BoException
	 * @throws Exception
	 */
	public void addList(User sessionUser,OrderrQuestion obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		//这个以后要去掉的
		obj.setStatusMap((Map)MapDb.getInstance().getMap("OrderrQuestion.Status"));
		//这个以后要去掉的
		obj.setItypePayMap((Map)MapDb.getInstance().getMap("OrderrQuestion.ItypePay"));
		commonzSvrUitlsSpec.addList(sessionUser,obj);
	}
	/**
	 * 加更多
	 * @param sessionUser
	 * @param obj OrderrQuestion
	 * @throws BoException
	 * @throws Exception
	 */
	public void addMore(User sessionUser,OrderrQuestion obj) throws BoException,Exception{
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
	public void addString(User sessionUser,OrderrQuestion obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		String value="";
		value=(String)MapDb.getInstance().getMapString("OrderrQuestion.Status", ""+obj.getStatus());
		if(StringUtils.isEmpty(value))
			value=""+obj.getStatus();
		obj.setStatusString(value);
		value=(String)MapDb.getInstance().getMapString("OrderrQuestion.ItypePay", ""+obj.getItypePay());
		if(StringUtils.isEmpty(value))
			value=""+obj.getItypePay();
		obj.setItypePayString(value);
		Member obj7=(Member)(memberService.get(sessionUser,obj.getMemberId()));
		if(obj7!=null){
			obj.setMemberIdString(obj7.getMyname());
			obj.setMemberIdStringid(obj7.getMynameid());
		}

		Question obj8=(Question)(questionService.get(sessionUser,obj.getQuestionId()));
		if(obj8!=null){
			obj.setQuestionIdString(obj8.getMyname());
			obj.setQuestionIdStringid(obj8.getMynameid());
		}

		commonzSvrUitlsSpec.addString(sessionUser,obj);
	}
	/**
	 * 从父亲Member那里，批量冗余我的数据
	 * @param sessionUser
	 * @param member Member 
	 */
	public void updateRedundancyFromMember(User sessionUser,Member member){
		execute(sessionUser, "update OrderrQuestion set  name=?0 , mobile=?1 where memberId=?2", new Object[]{member.getName(),member.getMobile(), member.getId()});
		commonzSvrUitlsSpec.updateRedundancyFromFather(sessionUser, "OrderrQuestion", member);
	}
	/**
	 * 从父亲Question那里，批量冗余我的数据
	 * @param sessionUser
	 * @param question Question 
	 */
	public void updateRedundancyFromQuestion(User sessionUser,Question question){
		execute(sessionUser, "update OrderrQuestion set  title=?0 , price=?1 where questionId=?2", new Object[]{question.getTitle(),question.getPrice(), question.getId()});
		commonzSvrUitlsSpec.updateRedundancyFromFather(sessionUser, "OrderrQuestion", question);
	}

	@Autowired
	MemberService memberService;
	@Autowired
	QuestionService questionService;

}
