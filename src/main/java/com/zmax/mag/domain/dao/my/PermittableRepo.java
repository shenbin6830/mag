package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Permittable;


/**
 * 权限之表设定Repo
 * @author zmax
 *
 */
public interface PermittableRepo extends JpaRepository<Permittable, Integer> {}
