package com.zmax.mag.service.spec;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmax.common.utils.string.SqlUtils;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.dao.base.SpecRepo;
import com.zmax.mag.service.utils.PermitCheckUtils;

@Service
public class SpecService {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	SpecRepo specRepo;
	@Autowired
	PermitCheckUtils permitCheckUtils;
	
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
	public Object getFirst(User sessionUser,String Clazz,String where,String order,Object[] params){
		where=SqlUtils.whereAdd(where,permitCheckUtils.addWhere(sessionUser,Clazz));		
		return specRepo.getFirst(Clazz, where, order, params);
	}
	/**
	 * 根据sql返回对象
	 * @param sql  原生sql语句 ex. select sum(id) from admin where id < ?0
	 * @param params
	 * @return
	 */
	public Object getFirstBySql(String sql,Object[] params){
		return specRepo.getFirstByNativeQuery(sql, params);
	}
	/**
	 * 自定义查询HQL，返回List[Object[]]
	 * @param sessionUser
	 * @param clazz
	 * @param hql ex:select t.obj_id,t.template_id,s.ckey from channel_prop_link t,channel_prop_template s where t.obj_id="+channelId+" and t.template_id=s.id
	 * @param params
	 * @return
	 */
	public List<Object[]> listObjectSql(User sessionUser,String sql, Object[] params){
		Map<String,String> map=SqlUtils.sql6MapFromSql(sql);
		String clazz=map.get("clazz");
		String where=map.get("where");
		where=SqlUtils.whereAdd(where,permitCheckUtils.addWhere(sessionUser,clazz));
		map.put("where", where);
		sql=SqlUtils.sql6Map2Sql(map);
		return specRepo.listObjectSql(sql, params);
	}
}
