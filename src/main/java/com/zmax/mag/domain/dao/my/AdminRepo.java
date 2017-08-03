package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Admin;


/**
 * 超级管理员Repo
 * @author zmax
 *
 */
public interface AdminRepo extends JpaRepository<Admin, Integer> {}
