package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.ScorememberStatiMonth;


/**
 * 会员积分月统计Repo
 * @author zmax
 *
 */
public interface ScorememberStatiMonthRepo extends JpaRepository<ScorememberStatiMonth, Integer> {}
