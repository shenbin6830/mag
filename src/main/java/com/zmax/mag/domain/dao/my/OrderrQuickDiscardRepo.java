package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.OrderrQuickDiscard;


/**
 * 订单之抢答问题提问放弃Repo
 * @author zmax
 *
 */
public interface OrderrQuickDiscardRepo extends JpaRepository<OrderrQuickDiscard, Integer> {}
