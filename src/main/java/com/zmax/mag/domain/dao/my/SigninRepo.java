package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Signin;


/**
 * 签到Repo
 * @author zmax
 *
 */
public interface SigninRepo extends JpaRepository<Signin, Integer> {}
