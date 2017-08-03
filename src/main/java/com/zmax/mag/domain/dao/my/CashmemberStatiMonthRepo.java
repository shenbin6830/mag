package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.CashmemberStatiMonth;


/**
 * 会员现金月统计Repo
 * @author zmax
 *
 */
public interface CashmemberStatiMonthRepo extends JpaRepository<CashmemberStatiMonth, Integer> {}
