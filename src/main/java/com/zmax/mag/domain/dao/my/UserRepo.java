package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.User;


/**
 * 账号信息修改Repo
 * @author zmax
 *
 */
public interface UserRepo extends JpaRepository<User, Integer> {}
