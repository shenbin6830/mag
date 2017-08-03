package com.zmax.mag.domain.dao.base;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.zmax.common.utils.string.SqlUtils;

/**
 * 泛形SpecDao，稍微扩展一下xxxRepo
 * @author Administrator
 *
 */
@Repository
public class SpecRepo{

	static final String BEANPACKAGE="com.zmax.mag.domain.bean";

	@PersistenceContext
	private EntityManager em;
	/**
	 * 获取一个值，不一定是表对象，有可能是个字段之类
	 * @param sql 原生sql语句 ex. select sum(id) from admin where id < ?0
	 * @param params 查询的参数 ex.new Object[]{"aa",1}
	 * @return
	 */
	public Object getFirstByNativeQuery(String sql,Object[] params) {
		Query query = createQueryWithSql(sql, params);

		query.setFirstResult(0).setMaxResults(1);
		return query.getSingleResult();			
	}
	/**
	 * 返回符合条件的第一个对象
	 * @param Clazz 对象名 ex.Admin
	 * @param where ex.aaa=1 或者 aaa = ?0
	 * @param order ex.id desc
	 * @param params 查询的参数 ex.new Object[]{"aa",1}
	 * @return
	 */
	public Object getFirst(String Clazz,String where,String order,Object[] params) {
		String jpql = SqlUtils.jpqlFromClazzWhereOrder(Clazz, where, order);
		Query query = createQueryWithJp(jpql, params);
		query.setFirstResult(0).setMaxResults(1);
		return query.getSingleResult();			
	}
	/**
	 * 自定义查询SQL，返回List[Object[]]
	 * @param sql ex:select t.obj_id,t.template_id,s.ckey from channel_prop_link t,channel_prop_template s where t.obj_id="+channelId+" and t.template_id=s.id
	 * @return
	 */
	public List<Object[]> listObjectSql(String sql, Object... params){
		Query query = createQueryWithSql(sql, params);
		return query.getResultList();
	}	
	/**
	 * 通过jpql,params生成query
	 * @param jpql
	 * @param params
	 * @return
	 */
	private Query createQueryWithJp(String jpql,Object[] params){
		Query query = em.createQuery(jpql);
		queryAddParam(query, params);
		return query;
	}
	/**
	 * 通过原生sql,params生成query
	 * @param sql
	 * @param params
	 * @return
	 */
	private Query createQueryWithSql(String sql,Object[] params){
		Query query = em.createNativeQuery(sql);
		queryAddParam(query, params);
		return query;
	}
	/**
	 * Query加参数
	 * @param query
	 * @param params
	 */
	private void queryAddParam(Query query,Object[] params){
		if(params!=null && params.length > 0){
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
	}
}
