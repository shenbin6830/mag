package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.CashHis;


/**
 * 现金流水Repo
 * @author zmax
 *
 */
public interface CashHisRepo extends JpaRepository<CashHis, Integer> {}
