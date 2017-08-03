package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Wxouser;


/**
 * 微信用户Repo
 * @author zmax
 *
 */
public interface WxouserRepo extends JpaRepository<Wxouser, String> {}
