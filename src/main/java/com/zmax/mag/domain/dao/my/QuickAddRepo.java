package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.QuickAdd;


/**
 * 抢答之追加Repo
 * @author zmax
 *
 */
public interface QuickAddRepo extends JpaRepository<QuickAdd, Integer> {}
