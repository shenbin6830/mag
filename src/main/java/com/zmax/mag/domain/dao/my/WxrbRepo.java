package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Wxrb;


/**
 * 微信用户非首推关系Repo
 * @author zmax
 *
 */
public interface WxrbRepo extends JpaRepository<Wxrb, Integer> {}
