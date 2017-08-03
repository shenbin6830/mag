package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.CashmemberStatiDay;


/**
 * 会员现金日统计Repo
 * @author zmax
 *
 */
public interface CashmemberStatiDayRepo extends JpaRepository<CashmemberStatiDay, Integer> {}
