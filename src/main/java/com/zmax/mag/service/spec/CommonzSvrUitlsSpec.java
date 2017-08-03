package com.zmax.mag.service.spec;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.zmax.common.entity.QueryHelper;
import com.zmax.common.exception.BoException;
import com.zmax.common.utils.clazz.ClassUtils;
import com.zmax.common.utils.easyui.Grid;
import com.zmax.common.utils.string.Md5PwdEncoder;
import com.zmax.common.conf.AttrStatic;
import com.zmax.mag.domain.bean.Question;
import com.zmax.mag.domain.bean.QuestionTxt;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.service.my.QuestionLinkMemberViewService;
import com.zmax.mag.service.my.QuestionService;
import com.zmax.mag.service.my.QuestionTxtService;
import com.zmax.mag.service.my.QuickLinkMemberViewService;
import com.zmax.mag.service.my.QuickService;
import com.zmax.mag.service.my.QuickTxtCommentService;
import com.zmax.mag.service.my.QuickTxtService;
import com.zmax.mag.service.utils.PermitCheckUtils;






/**
 * 在Controller中GET/POST时
 * 额外的Bean补全增加之通用版
 * @author zmax
 *
 */
@Component
public class CommonzSvrUitlsSpec {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	static int LISTMAX=1000;

	public CommonzSvrUitlsSpec(){
	}

	@Autowired
	PermitCheckUtils permitCheckUtils;
	@Autowired
	SpecRoleService specRoleService;
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
	 * 通用版new Post写表前
	 * @param oldobj 老对象，session中或db中的对象
	 * @param obj 目标及返回对象
	 * @param className
	 * @param sessionUser
	 * @throws Exception
	 */
	public void commonCreateBefore(Object oldobj,Object obj,String className,User sessionUser) throws Exception{
		if(obj==null)return;
		String key=null;
		Object newdata=null;
		key="gmtCreate";
		ClassUtils.setFieldValue(obj, key, new Date());
		ClassUtils.setFieldValue(oldobj, key, new Date());
		key="gmtModified";
		ClassUtils.setFieldValue(obj, key, new Date());
		ClassUtils.setFieldValue(oldobj, key, new Date());
	
		key="password";
		newdata=ClassUtils.getFieldValue(obj, key);
		if(newdata!=null){
			ClassUtils.setFieldValue(obj, key, Md5PwdEncoder.encodePassword(newdata.toString()));
			ClassUtils.setFieldValue(oldobj, key, Md5PwdEncoder.encodePassword(newdata.toString()));
		}

		if(sessionUser==null)
			return;
		//计算41.
		permitCheckUtils.createWithDefaultValue(className, sessionUser,oldobj, obj);
	}
	/**
	 * Edit Post，写库前
	 * @param oldobj 老对象从内存中取出的id，从表中取出，未来保存到表中的也是这个
	 * @param newobj 新对象由用户提交
	 * @param request
	 * @param response
	 */
	public void commonUpdateBefore(Object oldobj,Object obj,String className,User sessionUser) throws Exception{
		if(obj==null)return;

		Object olddata=null;
		Object newdata=null;
		String key=null;

		key="id";
		olddata=ClassUtils.getFieldValue(oldobj, key);
		ClassUtils.setFieldValue(obj, key, olddata);

		key="gmtCreate";
		olddata=ClassUtils.getFieldValue(oldobj, key);
		ClassUtils.setFieldValue(obj, key, olddata);

		key="gmtModified";
		ClassUtils.setFieldValue(obj, key, new Date());	
		
		key="password";
		newdata=ClassUtils.getFieldValue(obj, key);
		olddata=ClassUtils.getFieldValue(oldobj, key);
		if(newdata!=null&&!newdata.equals(olddata)){
			ClassUtils.setFieldValue(obj, key, Md5PwdEncoder.encodePassword(newdata.toString()));
		}

		if(sessionUser==null)
			return;
		//计算42.1
		permitCheckUtils.updateWithDefaultValue(className, sessionUser, oldobj, obj);
		//计算42.0 42.9
		if(isAdmin(sessionUser)){
			
		}else{
			//计算42.9
			Set<String> skp=permitCheckUtils.keepSessionByPermit(className, sessionUser);
			if(skp.size()==0){
				//完全无权
				obj=oldobj;
				return;
			}else if(skp.contains("91all")){
				//完全有权，把页面提交的数据全部复制到老对象中
				//ClassUtils.beanDeepCopy(obj, oldobj);
			}else{
				//所有字段
				String[] newfields=ClassUtils.getAllClassFields(oldobj.getClass());
				for (String newfield : newfields) {
					if(!skp.contains(newfield)){ //如果不能改，从老数据中复制
						olddata=ClassUtils.getFieldValue(oldobj, newfield);
						ClassUtils.setFieldValue(obj, newfield, olddata);			
					}
				}
			}
		}
	}
	public static boolean isAdmin(User sessionUser){
		if(sessionUser==null)
			return false;
		return (Integer.valueOf(AttrStatic.ROLE_ADMIN).equals(sessionUser));
	}
	
	/**
	 * 返回导出excel需要的Hql
	 * @param sessionUser
	 * @param classname
	 * @param queryhq
	 * @param params
	 * @return hql
	 */
	public String makeExportExcelQuery(User sessionUser,String classname, String queryhq, String act ,List<Object> params){
		return "";
	}	
	/**
	 * 根据搜索对象产生where
	 * @param request
	 * @param response
	 * @param model
	 * @param search
	 * @param queryhq
	 * @param params
	 */
	public String makeWhere(User sessionUser,Object search, String queryhq ,List<Object> params)throws BoException,Exception{
		if(search==null)
			return "";
		return "";
	}
	/**
	 * 为ListWithTree创建QueryHelper,包含where order
	 * @param sessionUser
	 * @param classname
	 * @param queryHelper
	 * @return
	 */
	public void makeWhereForListWithTree(User sessionUser,String classname,QueryHelper queryHelper){

	}
	/**
	 * index的补全
	 * @param request
	 * @param response
	 * @param model
	 * @param obj
	 */
	public void indexAfter(User sessionUser,Object obj){

	}
	/**
	 * 列表前的补全
	 * @param obj
	 */
	public void gridBefore(User sessionUser,Object obj){
	}	
	/**
	 * index时补全bean，只需要显示父母或属性对象的文本信息即可
	 * @param obj
	 */
	public void gridAfter(User sessionUser,List list){
		if (list == null || list.size() == 0){
			return;
		}
	}

	/**
	 * index时的单条补全bean，只需要显示父母或属性对象的文本信息即可
	 * @param obj
	 */
	public void gridOneAfter(User sessionUser,Object obj){
	}
	/**
	 * 给表格加脚 
	 * grid.setFooter(new ArrayList());
	 * grid.getFooter().add(统计obj);
	 * @param sessionUser
	 * @param grid
	 * @param obj
	 * @param where
	 */
	public void csgridAfterFooter(User sessionUser,Grid grid,Object obj,String where){
	}
	/**
	 * show时补全bean，显示父母或属性对象+扩展
	 * @param obj
	 */
	public void show(User sessionUser,Object obj){

	}
	/**
	 * new get前进行判断
	 * @param obj
	 */
	public void newgetBefore(User sessionUser,Object obj) throws Exception{

	}
	/**
	 * new get时补全bean:加上各类的LIST
	 * @param obj
	 */
	public void newgetAfter(User sessionUser,Object obj) throws Exception{

	}



	/**
	 * 批量更新之前
	 * @param className 类名
	 * @param setfield 设置的字段
	 * @param ids id们
	 * @param request
	 * @param response
	 */
	public void batchupdateBefore(User sessionUser,String className,String setfield,Object[] ids) throws Exception{

	}
	/**
	 * 批量更新之后
	 * @param className 类名
	 * @param setfield 设置的字段
	 * @param ids id们
	 * @param request
	 * @param response
	 */
	public void batchupdateAfter(User sessionUser,String className,String setfield,Object[] ids) throws Exception{
	}	
	/**
	 * 删除ById,写库前
	 * @param className 类名
	 * @param id
	 * @param request
	 * @param response
	 */
	public void deleteByIdBefore(User sessionUser,String className,Object id){
	}
	/**
	 * 删除ById，写库后
	 * @param className 类名
	 * @param id
	 * @param request
	 * @param response
	 */
	public void deleteByIdAfter(User sessionUser,String className,Object id){
	}	
	/**
	 * 生成静态
	 * @param sessionUser
	 * @param obj
	 * @param pagable
	 */
	public void genFirst(User sessionUser,Object obj,Pageable pagable){
	}
	/**
	 * 生成静态
	 * @param sessionUser
	 * @param obj
	 * @param pagable
	 */
	public void genChannel(User sessionUser,Object obj,Pageable pagable){
	}
	/**
	 * 生成静态
	 * @param sessionUser
	 * @param obj
	 * @param pagable
	 */
	public void genContent(User sessionUser,Object obj,Pageable pagable){
	}	
	/**
	 * Edit Get时做的判断
	 * @param obj
	 */
	public void editBefore (User sessionUser,Object obj) throws Exception{

	}



	/**
	 * Edit Get时补全bean，加父母对象用于显示，加父母LIST用于select
	 * @param obj
	 */
	public void editAfter(User sessionUser,Object obj){

	}


	/**
	 * create update 共用
	 * 创建更新，写表前，如计算总价之类，通常都是一样的操作
	 * @param simb 标记 create or update
	 * @param oldobj 新增时是个新对象，修改时是用户提交对象
	 * @param obj 新增时这个是用户提交的obj，修改时这个是从db取出并且被放了新值的数据，数据库保存这个
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void createAndUpdateBefore(String simb,Object oldobj,Object obj,User sessionUser) throws Exception{
		if(obj==null)return;

	}	
	/**
	 * create update 共用
	 * 创建更新，写表后，有可能要改变其它表，或本表的其它数据，通常都是一样的操作
	 * @param simb 标记 create or update
	 * @param obj 这个是用户提交的obj已经存到db的数据
	 * @param olddbobj update更新时是复制过的数据库老对象，create创建时是个new Object()
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void createAndUpdateAfter(String simb,Object obj,Object olddbobj,User sessionUser) throws BoException, Exception{
		if(obj==null)return;
		if(obj instanceof User){
			//创建用户的时候，创建附属对象
			specRoleService.createRegObjByUser((User)obj, sessionUser);
		}
		if(obj instanceof QuestionTxt){//提交问题答案的时候，修改question回答的长度。本来是从手机端做的，但是后台也经常提交答案或者管理员修改答案，因此在这里也加一条。
			QuestionTxt questionTxt=(QuestionTxt)obj;
			Question questionDb=questionService.get(null, questionTxt.getId());
			questionDb.setAnswerwords(questionTxt.getAnswer().length());
			questionService.update(null, questionDb);
		}
	}	
	/**
	 * 加上对象，主要用于显示，如果关联表了，hibernate会自动做
	 * @param obj
	 * @param request
	 * @param response
	 * @param model
	 * @param map
	 */
	public void addObj(User sessionUser,Object obj) throws BoException,Exception{
		if(obj instanceof User){
			specRoleService.userAddRoleobj((User)obj);
		}

	}
	/**
	 * 加上多语言对象
	 * @param obj
	 * @param request
	 * @param response
	 * @param model
	 */
	public void addLangObj(User sessionUser,Object obj){
	}	
	/**
	 * 加上文本，用于显示
	 * @param obj
	 */
	public void addString(User sessionUser,Object obj){

	}


	/**
	 * 加上父母或引用的名称LIST，用于select选择，放在对象中。
	 * <br/>list名的规范是:字段名_list
	 * @param obj
	 */
	public void addList(User sessionUser,Object obj){

	}
	/**
	 * 加更多 
	 * @param obj
	 */
	public void addMore(User sessionUser,Object obj){

	}
	/**
	 * 加上扩展
	 * @param obj
	 * @param request
	 * @param response
	 * @param model
	 */
	public void addExt(User sessionUser,Object obj){


	}
	/**
	 * 加上属性
	 * @param obj
	 * @param request
	 * @param response
	 * @param model
	 */
	public void addProp(User sessionUser,Object obj){

	}
	/**
	 * 对象复制
	 * @param request
	 * @param response
	 * @param model
	 * @param src
	 * @param dest
	 */
	public void obj2obj(User sessionUser,Object src,Object dest,String from,String to)throws Exception{
		ClassUtils.beanDeepCopySkipEmpty(src, dest);
	}	
	/**
	 * 对象复制后，保存前的检查
	 * @param request
	 * @param response
	 * @param model
	 * @param src
	 * @param dest
	 * @return
	 */
	public void obj2objcheck(User sessionUser,Object src,Object dest,String from,String to)throws Exception{

	}	
	/**
	 * 完成前，对象集复制成完成对象集后，需要做的事。
	 * 流程:对象集复制成完成对象集，完成表添加，原表删除。 
	 * @param sessionUser
	 * @param obj 已经是xxxFinished对象了
	 * @param finishWhere 条件
	 */
	public void finishBefore(User sessionUser,Object obj,String finishWhere){


	}
	/**
	 * 放弃前，对象集复制成完成对象集后，需要做的事。
	 * 流程:对象集复制成完成对象集，完成表添加，原表删除。 
	 * @param sessionUser
	 * @param obj 已经是xxxFinished对象了
	 * @param discardWhere 条件
	 */
	public void discardBefore(User sessionUser,Object obj,String discardWhere){


	}
	/**
	 * 默认的finish的hql
	 * @param classname
	 * @return
	 */
	public String defaultFinishHql(String classname){
		return "1=0";
	}
	/**
	 * 默认的discard的hql
	 * @param classname
	 * @return
	 */
	public String defaultDiscardHql(String classname){
		return "1=0";
	}
	/**
	 * 返回自定义html
	 * @param request 
	 * @param sessionUser User
	 * @param ret 标准返回 ex. /user/User/index
	 * @param clazz 类名 ex.User
	 * @param act 操作 ex.index/show/new/edit/editGetEmpty
	 * @return
	 */
	public String changeHtml(User sessionUser,String ret,String clazz,String act){
		return ret;
	}
	/**
	 * 父亲更新后，冗余父亲的孩子们也需要更新一下。
	 * @param sessionUser
	 * @param father 父亲对象
	 */
	public void updateRedundancyToChildren(User sessionUser,Object father){
		
	}
	/**
	 * 孩子批量冗余更新自己数据后，继续要做的事
	 * @param sessionUser
	 * @param child 孩子的类名
	 * @param father 父亲对象
	 */
	public void updateRedundancyFromFather(User sessionUser,String child,Object father){
		
	}
}