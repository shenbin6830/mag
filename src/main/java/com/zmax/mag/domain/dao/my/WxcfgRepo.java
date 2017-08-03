package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Wxcfg;


/**
 * 微信配置Repo
 * @author zmax
 *
 */
public interface WxcfgRepo extends JpaRepository<Wxcfg, Integer> {}
