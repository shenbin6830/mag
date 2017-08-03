package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.ScorememberStatiDay;


/**
 * 会员积分日统计Repo
 * @author zmax
 *
 */
public interface ScorememberStatiDayRepo extends JpaRepository<ScorememberStatiDay, Integer> {}
