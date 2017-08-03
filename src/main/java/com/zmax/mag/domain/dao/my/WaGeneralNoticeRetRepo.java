package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.WaGeneralNoticeRet;


/**
 * 微信支付回调通用结果Repo
 * @author zmax
 *
 */
public interface WaGeneralNoticeRetRepo extends JpaRepository<WaGeneralNoticeRet, Integer> {}
