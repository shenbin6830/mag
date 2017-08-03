package com.zmax.mag.domain.dao.spec;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.zmax.mag.domain.bean.Admin;



/**
 * 特别Impl
 * 完全自定义的Dao
 * @author zmax
 *
 */
@Component
public class SpecAdminImpl {
	@PersistenceContext
	private EntityManager em;
	/**
	 * 返回第一个管理员
	 * @return
	 */
	public Admin getFirstAdmin() {
		String jpql = "select t from Admin t where 1=1 order by id";
		Query query = em.createQuery(jpql);	
		query.setFirstResult(0).setMaxResults(1);
		Admin admin=(Admin)query.getSingleResult();
		return admin;
	}
}