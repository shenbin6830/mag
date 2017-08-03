package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Cadmin;


/**
 * 一般管理员Repo
 * @author zmax
 *
 */
public interface CadminRepo extends JpaRepository<Cadmin, Integer> {}
