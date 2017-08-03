package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Wxr;


/**
 * 微信用户关系Repo
 * @author zmax
 *
 */
public interface WxrRepo extends JpaRepository<Wxr, String> {}
