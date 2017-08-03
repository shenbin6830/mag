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
import com.zmax.mag.domain.dao.my.ArticlechannelRepo;


/**
 * 频道服务
 * 用到我的有：Article^ArticlechannelId^文章_文章频道内序号,Question^ArticlechannelId^一对一问题_频道内序号,Quick^ArticlechannelId^抢答_频道内序号,
 * @author zmax
 */
@Service
public class ArticlechannelService extends BaseService<Articlechannel> {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	PermitCheckUtils permitCheckUtils;
	/**标准版*/
	@Autowired
	ArticlechannelRepo articlechannelRepo;
	/**扩展版*/
	@Autowired
	BaseRepo<Articlechannel,Integer> baseRepo;
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
	public Articlechannel get(User sessionUser,Integer id) {
		if(id==null)
			return null;
		if(id.intValue()==0)
			return null;
		Articlechannel obj= articlechannelRepo.findOne(id);
		if(obj==null)
			return null;
		if(!permitCheckUtils.checkObj(sessionUser, obj,"Articlechannel")) 
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
	public Articlechannel getByKvIfNotExistsSaveKv(User sessionUser,Articlechannel entity,String keyfield,Object value,String order, Object[] params) {
		if(entity==null)
			return null;
		if(!permitCheckUtils.checkObj(sessionUser, entity,"Articlechannel")) return entity;
		Articlechannel obj=getFirst(sessionUser, keyfield+"='"+value.toString()+"'", order, params);
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
	public List<Articlechannel> listfindByIn(User sessionUser,Integer[] ids,String morecondition) {
		String where=SqlUtils.whereFromIds(ids);
		where=SqlUtils.whereAdd(where,permitCheckUtils.addWhere(sessionUser,"Articlechannel"));
		if(StringUtils.isNotBlank(morecondition))
			where=SqlUtils.whereAdd(where, morecondition);
		List<Integer> list=new ArrayList<Integer>();
		return articlechannelRepo.findAll(list);
	}
	

	///////更多LIST在父亲那里

	/**
	 * 保存，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param articlechannel
	 */
	public void save(User sessionUser,Articlechannel articlechannel){
		articlechannelRepo.save(articlechannel);
	}
	/**
	 * 更新
	 * @param sessionUser
	 * @param articlechannel
	 */
	public void update(User sessionUser,Articlechannel articlechannel){
		articlechannelRepo.save(articlechannel);
	}
	/**
	 * 保存，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param articlechannel
	 */
	public void saveOrUpdate(User sessionUser,Articlechannel articlechannel){
		save(sessionUser,articlechannel);
	}
	/**
	 * 保存列表，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param entities
	 */
	public void saveOrUpdateAll(User sessionUser,Collection<Articlechannel> entities) {
		if(entities==null)
			return;
		for (Articlechannel articlechannel : entities) {
			save(sessionUser,articlechannel);
		}
	}
	/**
	 * 删除
	 * @param sessionUser
	 * @param id
	 */
	public void deleteById(User sessionUser,Integer id){
		articlechannelRepo.delete(id);
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
	public void deleteAll(User sessionUser,List<Articlechannel> list) throws BoException,Exception{
		for (Articlechannel obj : list) {
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
	public void csindex(User sessionUser,Articlechannel obj,String act) throws BoException,Exception{
		if(StringUtils.equals(act, "nullCreate")){
			if(obj==null)
				throw new Exception("无法创建空对象");
			Articlechannel dbobj=get(sessionUser, obj.getId());
			if(dbobj==null){
				//创建_写表前操作之自定义，目的是修改obj
				commonzSvrUitlsSpec.createAndUpdateBefore("create",new Articlechannel(), obj,sessionUser);
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
	public Grid csgrid(User sessionUser,Articlechannel search, PageHelper ph,String queryhq) throws BoException,Exception{
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
	public void saveCreate(User sessionUser,Articlechannel oldobj,Articlechannel obj,String copyfrom,String copyfromwhere) throws BoException,Exception{
		//用于做复制新增的检查
		if(StringUtils.isNotBlank(copyfrom)&&StringUtils.isNotBlank(copyfromwhere)){
			Object objsrc=specService.getFirst(sessionUser,copyfrom, copyfromwhere,null,null);
			commonzSvrUitlsSpec.obj2objcheck(sessionUser,objsrc, obj ,copyfrom ,"Articlechannel");
		}		

		//创建_写表前操作之通用
		commonzSvrUitlsSpec.commonCreateBefore(oldobj,obj,"Articlechannel",sessionUser);
		//创建_写表前操作之本地
		createAndUpdateBefore(sessionUser,"create",oldobj, obj);
		//创建_写表前操作之自定义，目的是修改obj
		commonzSvrUitlsSpec.createAndUpdateBefore("create",oldobj, obj,sessionUser);

		save(sessionUser,obj);
		//TODO:lang
		//创建_写表后操作之本地
		createAndUpdateAfter(sessionUser,"create",obj,new Articlechannel());
		//创建_写表后操作之自定义
		commonzSvrUitlsSpec.createAndUpdateAfter("create",obj,new Articlechannel(),sessionUser);
	}
	/**
	 * 更新
	 * 流程：从DB取出DB对象，对提交对象进行修正，将修正对象复制到DB对象，保存
	 * @param sessionUser 
	 * @param obj
	 * @param id 这个id通常是从session中获取的
	 * @throws Exception
	 */
	public void updateUpdate(User sessionUser,Articlechannel obj,Integer id) throws BoException,Exception{
		//数据库表里取出的对象，要把页面对象obj里的内容，放进来进行保存
		Articlechannel dbobj=get(sessionUser, id);
		if(dbobj==null)
			throw new BoException("数据库中数据找不到");

		//更新_写表前操作之通用，目的是修改obj
		commonzSvrUitlsSpec.commonUpdateBefore(dbobj,obj,"Articlechannel",sessionUser);
		//更新_写表前操作之本地，目的是修改obj
		createAndUpdateBefore(sessionUser,"update",dbobj, obj);
		//更新_写表前操作之自定义，目的是修改obj
		commonzSvrUitlsSpec.createAndUpdateBefore("update", dbobj, obj, sessionUser);

		//父id是否相同
		boolean isSameParentid=(dbobj.getParentid().equals(obj.getParentid()));
		//复制一下老数据库对象，给createAndUpdateAfter用
		Articlechannel olddbobj=new Articlechannel();
		ClassUtils.beanDeepCopy(dbobj, olddbobj);
		//把页面传进来的对象obj复制到数据库里产生的对象dbobj,之后保存数据库产生的对象dbobj
		ClassUtils.beanDeepCopy(obj, dbobj);
		update(sessionUser,dbobj);

		//更新_写表后操作之本地
		createAndUpdateAfter(sessionUser,"update",dbobj,olddbobj);
		//更新_写表后操作之自定义
		commonzSvrUitlsSpec.createAndUpdateAfter("update",dbobj,olddbobj,sessionUser);

		if(!isSameParentid)
			upateMyfamilyChild(dbobj);
	}
	/**
	 * 新建或更新之完全操作版
	 * @param sessionUser
	 * @param oldobj new Obj() 
	 * @param obj
	 * @throws BoException
	 * @throws Exception
	 */
	public void saveCreateUpdateUpdate(User sessionUser,Articlechannel oldobj,Articlechannel obj) throws BoException,Exception{
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
		String hql= "update Articlechannel set "+setfield +" where "+StringUtilz.whereIn(ids);
		execute(sessionUser, hql, null);
		commonzSvrUitlsSpec.batchupdateAfter(sessionUser,"Articlechannel", setfield, ids);
	}
	/**
	 * 根据ID删除一个
	 * @param sessionUser 
	 * @param id
	 * @throws Exception
	 */
	public void deleteOneById(User sessionUser,Integer id) throws BoException,Exception{
		commonzSvrUitlsSpec.deleteByIdBefore(sessionUser,"Articlechannel", id);
		deleteById(sessionUser,id);
		commonzSvrUitlsSpec.deleteByIdAfter(sessionUser,"Articlechannel", id);
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
			commonzSvrUitlsSpec.deleteByIdBefore(sessionUser,"Articlechannel", id);
			deleteById(sessionUser,id);
			commonzSvrUitlsSpec.deleteByIdAfter(sessionUser,"Articlechannel", id);
		}
	}
	/**
	 * 根据ckey查询对象
	 * @param sessionUser 
	 * @param ckey
	 * @return
	 */
	public Articlechannel getckey(User sessionUser,String ckey) throws BoException,Exception{
		Articlechannel articlechannel=getFirst(sessionUser, "ckey=?0 ", null, new Object[]{ckey});
		showAfter(sessionUser,articlechannel);
		return (articlechannel==null)?new Articlechannel():articlechannel;
	}
	/**
	 * 详情
	 * @param sessionUser 
	 * @param id
	 * @return
	 */
	public Articlechannel csshow(User sessionUser,Integer id) throws BoException,Exception{
		Articlechannel articlechannel=get(sessionUser, id);
		showAfter(sessionUser,articlechannel);
		return (articlechannel==null)?new Articlechannel():articlechannel;
	}
	/**
	 * 新建GET
	 * @param sessionUser 
	 * @param articlechannel 初始用户
	 * @param copyfrom 从其它表复制的表名
	 * @param copyfromwhere 从其它表复制的条件
	 * @return
	 */
	public Articlechannel csnewget(User sessionUser,Articlechannel articlechannel,String copyfrom,String copyfromwhere) throws BoException,Exception{
		if(articlechannel==null)
			articlechannel=new Articlechannel();
		//如果是从其它表复制过来的
		if(StringUtils.isNotBlank(copyfrom)&&StringUtils.isNotBlank(copyfromwhere)){
			Object objsrc=specService.getFirst(sessionUser,copyfrom, copyfromwhere, null,null);
			commonzSvrUitlsSpec.obj2obj(sessionUser,objsrc, articlechannel,copyfrom ,"Articlechannel");
			articlechannel.setId(null);
		}
		commonzSvrUitlsSpec.newgetBefore(sessionUser,articlechannel);
		newgetAfter(sessionUser,articlechannel);
		return articlechannel;
	}
	/**
	 * 根据id从数据库取，没有的话创建一个新的对象，并且保存完整版到数据库中
	 * @param sessionUser
	 * @param id
	 * @return
	 * @throws BoException
	 * @throws Exception
	 */
	public Articlechannel getOrNew(User sessionUser,Integer id) throws BoException,Exception{
		Articlechannel obj=get(sessionUser, id);
		if(obj==null){
			obj = new Articlechannel(
				null , //String 关键字  ID的补充 
				0 , //Integer 父ID default=0 0表示顶级 
				99 , //Integer 排列顺序 default=99 数字越小排名越前 
				0 , //Integer 孩子数量 default=0 0表示最底 
				null , //String 祖先  逗号分开 
				null , //String 名称   
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
	public Articlechannel csedit(User sessionUser,Integer id) throws BoException,Exception{
		
		Articlechannel articlechannel=get(sessionUser, id);
		if(articlechannel==null){
			throw new Exception("数据找不到");
		}
			
		commonzSvrUitlsSpec.editBefore(sessionUser,articlechannel);
		editAfter(sessionUser,articlechannel);
		return articlechannel;
	}
	/**
	 * 获取数据表格树状TREE版
	 * @param sessionUser 
	 * @param search
	 * @param ph
	 * @param queryhq
	 * @return
	 * @throws Exception
	 */
	public List<Tree> cstree(User sessionUser,Articlechannel search, PageHelper ph,String queryhq) throws BoException,Exception{
		
		List<Object> params = new ArrayList<Object>();
		gridBefore(sessionUser,search);
		String searchwhere=commonzSvrUitlsSpec.makeWhere(sessionUser,search, queryhq, params);
		String where=StringUtilz.whereAdd(searchwhere, queryhq);
		List<Tree> listTreeOra=new ArrayList<Tree>();
		Integer id=0;
		if(search!=null && search.getId()!=null)
			id=search.getId();
		if(id.intValue()==0)
			listTreeOra.add(new Tree("0", AttrStatic.treerootname, null, false, null, null, "", "0"));	
		where=StringUtilz.whereAdd(where,"parentid="+id);
		List<Articlechannel> listArticlechannel=listfind(sessionUser, where, null, params.toArray());
		gridAfter(sessionUser,listArticlechannel);
		for (Articlechannel articlechannel : listArticlechannel) {
			Tree tree=new Tree(""+articlechannel.getId(), articlechannel.getMyname(), "closed", false, null, null, "", ""+articlechannel.getParentid());
			listTreeOra.add(tree);
		}
		List<Tree> listTree=EasyUiUtils.treeSort(listTreeOra);
		return listTree;

	}
	
	/**
	 * 获取数据表格treegrid
	 * @param sessionUser 
	 * @param search
	 * @param ph
	 * @param queryhq
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object cstreegrid(User sessionUser,Articlechannel search, PageHelper ph,String queryhq,Integer id) throws BoException,Exception{
		if(id==null)
			id=0;
		String order=(ph.getSort()+" "+ph.getOrder()).trim();
		List<Object> params = new ArrayList<Object>();
		gridBefore(sessionUser,search);
		String searchwhere=commonzSvrUitlsSpec.makeWhere(sessionUser,search, queryhq, params);
		String where=StringUtilz.whereAdd(searchwhere, queryhq);
		where=StringUtilz.whereAdd(where,"parentid="+id);
		if(id.equals(0)){
			Grid page = listGrid(sessionUser, where, order, new PageRequest(ph.getPage()-1, ph.getRows()), params.toArray());
			gridAfter(sessionUser,page.getRows());
			return page;
		}else{
			List list=listfind(sessionUser, where, order, params.toArray());
			gridAfter(sessionUser,list);
			return list;
		}
	}
	/**
	 * 计算我的family
	 * @param me
	 * @throws BoException
	 * @throws Exception
	 */
	public void familyMe(Articlechannel me) throws BoException,Exception{
		Articlechannel parent=get(null, me.getParentid());
		if(StringUtilz.integerNullOr0(me.getParentid()) || parent==null){ //顶级的
			me.setParentid(0);
			me.setFamily("");
		}else{
			if(StringUtils.isBlank(parent.getFamily()))
				me.setFamily(""+parent.getId());
			else
				me.setFamily(parent.getFamily()+","+parent.getId());
		}	
	}
	/**
	 * 计算我的孩子的family
	 * @param me
	 * @param list 最终返回的，需要保存的集合
	 * @throws BoException
	 * @throws Exception
	 */
	public void familyChild(Articlechannel me,List<Articlechannel> list) throws BoException,Exception{
		if(list==null)
			throw new Exception("传入的list不能为空");
		familyMe(me);
		list.add(me);
		//孩子
		List<Articlechannel> listchild=listfind(null, "parentId=?0 ", null, new Object[]{me.getId()});
		for (Articlechannel child : listchild) {
			familyChild(child,list);
		}
	}
	/**
	 * 更新我所有的孩子的family
	 * @param me
	 * @throws BoException
	 * @throws Exception
	 */
	public void upateMyfamilyChild(Articlechannel me) throws BoException,Exception{
		List<Articlechannel> list=new ArrayList<Articlechannel>();
		familyChild(me, list);
		if(list.size()>0)
			saveOrUpdateAll(null,list);
	}
	/**
	 * 整表重算family
	 * @throws BoException
	 * @throws Exception
	 */
	public void updateFamilyAll() throws BoException,Exception{
		List<Articlechannel> list=listfind(null, "parentId=0", null, null);
		for (Articlechannel child : list) {
			upateMyfamilyChild(child);
		}
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
	public void createAndUpdateBefore(User sessionUser,String simb,Articlechannel dbobj,Articlechannel obj) throws BoException,Exception{
		if(obj==null)return;
		//create update 共用
		familyMe(obj);
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
	public void createAndUpdateAfter(User sessionUser,String simb,Articlechannel obj,Articlechannel olddbobj) throws BoException,Exception{
		if(obj==null)return;
		//树形表更新父亲孩子数量
		int num=0;
		if(null!=obj.getParentid() && obj.getParentid().intValue()>0){
			Articlechannel newparent=get(sessionUser,obj.getParentid());
			num=(int)getTableCount(sessionUser,  "parentid=?0 ", null, new Object[]{obj.getParentid()});
			newparent.setChildrennum(num);
			update(sessionUser, newparent);
		}
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
	public void indexAfter(User sessionUser,Articlechannel obj) throws BoException,Exception{
		addList(sessionUser,obj); //给搜索用
		commonzSvrUitlsSpec.indexAfter(sessionUser,obj);
	}	
	/**
	 * 列表，读库前
	 * @param obj
	 */
	public void gridBefore(User sessionUser,Articlechannel obj) throws BoException,Exception{
		if(obj==null)return;
		commonzSvrUitlsSpec.gridBefore(sessionUser,obj);

	}	
	/**
	 * 列表，读库后
	 * @param list
	 */
	public void gridAfter(User sessionUser,List<Articlechannel> list) throws BoException,Exception{
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
	public void gridOneAfter(User sessionUser,Articlechannel obj) throws BoException,Exception{
		if(obj==null)return;
		addString(sessionUser,obj);	
		addTreeStatus(obj);
	}	
	/**
	 * show，读库后
	 * @param obj
	 */
	public void showAfter(User sessionUser,Articlechannel obj) throws BoException,Exception{
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
	public void newgetAfter(User sessionUser,Articlechannel obj) throws BoException,Exception{
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
	public void editAfter(User sessionUser,Articlechannel obj) throws BoException,Exception{
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
	public void addObj(User sessionUser,Articlechannel obj) throws BoException,Exception{
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
	public void addList(User sessionUser,Articlechannel obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		commonzSvrUitlsSpec.addList(sessionUser,obj);
	}
	/**
	 * 加更多
	 * @param sessionUser
	 * @param obj Articlechannel
	 * @throws BoException
	 * @throws Exception
	 */
	public void addMore(User sessionUser,Articlechannel obj) throws BoException,Exception{
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
	public void addString(User sessionUser,Articlechannel obj) throws BoException,Exception{
		if(obj==null)return;
		 //这里不要查session
		String value="";
		if(obj.getParentid()==null || obj.getParentid()==0)
			obj.setParentidString("根目录");
		else{
			Articlechannel obj3=get(sessionUser,obj.getParentid());
			if(obj3!=null){
				obj.setParentidString(obj3.getMyname());
				obj.setParentidStringid(obj3.getMynameid());
			}
		}
		commonzSvrUitlsSpec.addString(sessionUser,obj);
	}
	/**
	 * 增加树状态
	 * @param obj
	 */
	public void addTreeStatus(Articlechannel obj) throws BoException,Exception{
		if(StringUtilz.integerNullOr0(obj.getChildrennum())){
			obj.setChildren(null);
			obj.setState(null);
		}
	}
	/**
	 * 返回所有项目的树形列表，注意这个会读全表，如果数据量大不要使用
	 * @param addprent boolean  是否加父，在json中，加了父就会死循环
	 * @return
	 */
	public List<Articlechannel> listWithTree(boolean addprent) throws BoException,Exception{
		QueryHelper queryHelper=new QueryHelper(); 
		queryHelper.setOrder("priority");
		commonzSvrUitlsSpec.makeWhereForListWithTree(null, "Articlechannel", queryHelper);
		List<Articlechannel> listAll=listfind(null, queryHelper.getWhere(), queryHelper.getOrder(), null);
		gridAfter(null, listAll);
		List<Articlechannel> listTree=new ArrayList<Articlechannel>();
		//找到顶级
		for (Articlechannel obj : listAll) {
			if(StringUtilz.integerNullOr0(obj.getParentid())){
				listTree.add(obj);
			}
		}
		//给所有对象加孩子列表
		for (Articlechannel obj : listTree) {
			putChildren(obj, listAll,addprent);
		}
		return listTree;
	}
	/**
	 * 返回指定parentid下的所有项目的树形列表，注意这个可能会读全表，如果数据量大不要使用
	 * @param pid 父母id
	 * @param addprent boolean  是否加父，在json中，加了父就会死循环
	 * @return
	 */
	public List<Articlechannel> listWithTreeByPid(Integer pid,boolean addprent) throws BoException,Exception{
		if(pid==null)
			return new ArrayList<Articlechannel>();
		QueryHelper queryHelper=new QueryHelper(); 
		queryHelper.setOrder("priority");
		commonzSvrUitlsSpec.makeWhereForListWithTree(null, "Articlechannel", queryHelper);
		List<Articlechannel> listAll=listfind(null, queryHelper.getWhere(), queryHelper.getOrder(), null);
		gridAfter(null, listAll);
		List<Articlechannel> listTree=new ArrayList<Articlechannel>();
		//找到顶级
		for (Articlechannel obj : listAll) {
			if(pid.equals(obj.getParentid())){
				listTree.add(obj);
			}
		}
		//给所有对象加孩子列表
		for (Articlechannel obj : listTree) {
			putChildren(obj, listAll,addprent);
		}
		return listTree;
	}

	/**
	 * 给对象加孩子列表
	 * @param obj
	 * @param listAll
	 * @param addprent boolean
	 */
	private void putChildren(Articlechannel obj,List<Articlechannel> listAll,boolean addprent) throws BoException,Exception{
		obj.setChildren(new ArrayList<Articlechannel>());
		for (Articlechannel objc : listAll) {
			if(objc.getParentid()!=null && objc.getParentid().equals(obj.getId())){
				if(addprent)objc.setParent(obj);
				obj.getChildren().add(objc);
				putChildren(objc, listAll,addprent);
			}
		}
		obj.setChildrennum(obj.getChildren().size());
	}
	/**
	 * 从列表以及孩子中找到对象
	 * @param list
	 * @param id
	 * @return
	 */
	public Articlechannel takeObjFromListAll(List<Articlechannel> list,Integer id) throws BoException,Exception{
		Articlechannel ret=null;
		if(list==null)
			return ret;
		for (Articlechannel obj : list) {
			if(obj.getId()!=null && obj.getId().equals(id)){
				return obj;
			}
			ret=takeObjFromListAll(obj.getChildren(), id);
			if(ret!=null)
				return ret;
		}
		return null;
	}

	@Autowired
	ArticleService articleService;
	@Autowired
	QuestionService questionService;
	@Autowired
	QuickService quickService;

}
