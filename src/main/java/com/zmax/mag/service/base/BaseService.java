package com.zmax.mag.service.base;

/**
 * zmax 2013
 */
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zmax.common.utils.easyui.Grid;
import com.zmax.common.utils.string.SqlUtils;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.dao.base.BaseRepo;
import com.zmax.mag.service.utils.PermitCheckUtils;




/**
 * 基础Service，其它都从此扩展
 * @author zmax
 *
 */

public class BaseService<T> {
	private static final Logger logger = Logger.getLogger(BaseService.class);


	@Autowired
	public BaseRepo<T, Serializable> baseRepo;
	@Autowired
	public PermitCheckUtils permitCheckUtils;

	/**
	 * 返回第一个查询结果
	 * @param sessionUser
	 * @param Clazz 类名表名 ex.Admin
	 * @param hqlb where 前
	 * @param where ex. a=1
	 * @param order ex. id desc
	 * @param params
	 * @return 返回相应的持久化PO实例
	 */
	public T getFirst(User sessionUser,String where,String order,Object[] params){
		Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		String Clazz=c.getSimpleName();
		where=SqlUtils.whereAdd(where,permitCheckUtils.addWhere(sessionUser,Clazz));		
		return baseRepo.getFirst(Clazz, where,null, params);
	}
	/**
	 * 获取数据数量
	 * @param sessionUser
	 * @param where
	 * @param order
	 * @param params
	 * @return
	 */
	public long getTableCount(User sessionUser,String where,String order,Object[] params){
		Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		String Clazz=c.getSimpleName();
		where=SqlUtils.whereAdd(where,permitCheckUtils.addWhere(sessionUser,Clazz));		
		return baseRepo.getCount(Clazz, where, params);
	}
	/**
	 * 返回所有数据
	 * @param sessionUser
	 * @param Clazz 类名表名 ex.Admin
	 * @return
	 */
	public List<T> listAll(User sessionUser){
		Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		String Clazz=c.getSimpleName();

		String where=permitCheckUtils.addWhere(sessionUser,Clazz);
		return baseRepo.listByWhereOrder(Clazz, where, null, null);
	}
	/**
	 * 根据where,order查找，返回所有符合条件的列表
	 * @param sessionUser
	 * @param Clazz 类名表名 ex.Admin
	 * @param where ex. a=1
	 * @param order ex. id desc
	 * @param params
	 * @return List
	 */
	public List<T> listfind(User sessionUser,String where,String order,Object[] params) {
		Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		String Clazz=c.getSimpleName();

		where=SqlUtils.whereAdd(where,permitCheckUtils.addWhere(sessionUser,Clazz));
		return baseRepo.listByWhereOrder(Clazz, where, order, params);
	}
	/**
	 * 返回EasyUI表格
	 * @param sessionUser
	 * @param Clazz
	 * @param where
	 * @param order
	 * @param pageable new PageRequest(0,20) or new PageRequest(pageNo-1, pageSize)
	 * @param params
	 * @return Grid
	 * @throws ClassNotFoundException
	 */
	public Grid listGrid(User sessionUser,String where,String order,Pageable pageable,Object[] params) throws ClassNotFoundException {
		Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		String Clazz=c.getSimpleName();

		where=SqlUtils.whereAdd(where,permitCheckUtils.addWhere(sessionUser,Clazz));
		Page<T> page=(Page<T>) baseRepo.listPageByWhereOrder(Clazz, where, order, pageable, params);
		Grid grid=new Grid();
		grid.setTotal((int)page.getTotalElements());
		grid.setRows(page.getContent());
		return grid;
	}



}
