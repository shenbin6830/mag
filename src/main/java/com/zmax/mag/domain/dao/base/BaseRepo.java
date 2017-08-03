package com.zmax.mag.domain.dao.base;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.zmax.common.utils.string.SqlUtils;

/**
 * 泛形dao，稍微扩展一下xxxRepo
 * @author Administrator
 *
 */
@Repository
public class BaseRepo<T, ID extends Serializable> {

	static final String BEANPACKAGE="com.zmax.mag.domain.bean";
	
	@PersistenceContext
	private EntityManager em;

	/**
	 * 返回符合条件的第一个对象
	 * @param Clazz 对象名 ex.Admin
	 * @param where ex.aaa=1 或者 aaa = ?0
	 * @param order ex.id desc
	 * @param params 查询的参数 ex.new Object[]{"aa",1}
	 * @return
	 */
	public T getFirst(String Clazz,String where,String order,Object[] params) {
		String jpql = SqlUtils.jpqlFromClazzWhereOrder(Clazz, where, order);
		Query query = createQueryWithJp(jpql, params);
		query.setFirstResult(0).setMaxResults(1);
		try {
			return (T)query.getSingleResult();
		} catch (Exception e) {
			return null;
		}		
	}
	/**
	 * 
	 * @param Clazz
	 * @param where
	 * @param params
	 * @return
	 */
	public long getCount(String Clazz,String where,Object[] params){
		String jpql = SqlUtils.jpqlCountFromClazzWhere(Clazz, where);
		Query query = createQueryWithJp(jpql, params);
		long count=(Long)query.getSingleResult();
		return count;
	}

	/**
	 * 通过where order 返回所有符合条件的数据
	 * @param Clazz 对象名 ex.Admin
	 * @param where ex.aaa=1
	 * @param order ex.id desc
	 * @param params
	 * @return
	 */
	public List<T> listByWhereOrder(String Clazz,String where,String order,Object[] params) {
		String jpql = SqlUtils.jpqlFromClazzWhereOrder(Clazz, where, order);
		Query query = createQueryWithJp(jpql, params);
		return query.getResultList();			
	}
	/**
	 * 根据where order 返回 page
	 * @param Clazz 对象名 ex.Admin
	 * @param where ex.aaa=1
	 * @param order ex.id desc
	 * @param pageable
	 * @param params
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public Page<T> listPageByWhereOrder(String Clazz,String where,String order,Pageable pageable,Object[] params) throws ClassNotFoundException {
		Class<T> domainClass=(Class<T>)Class.forName(BEANPACKAGE+"."+Clazz);
		String jpql = SqlUtils.jpqlFromClazzWhereOrder(Clazz, where, order);
		Query query = createQueryWithJp(jpql, params);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());

		Long total = getCount(Clazz,where,params);
		List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T> emptyList();
		
		return new PageImpl<T>(content, pageable, total);
	}

	/**
	 * 执行jpql
	 * @param jpql ex.update Admin set nickname=nickname+'0' where id=1
	 * @return
	 */
	@Modifying
	@Transactional
	public int execute(String jpql,Object[] params){
		Query query=em.createQuery(jpql);
		queryAddParam(query, params);
		return query.executeUpdate();
	}
	/**
	 * 执行sql
	 * @param sql ex.update admin set nickname=nickname+'0' where id=1
	 * @return
	 */
	@Modifying
	@Transactional
	public int executeSql(String sql){
		return em.createNativeQuery(sql).executeUpdate();
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
