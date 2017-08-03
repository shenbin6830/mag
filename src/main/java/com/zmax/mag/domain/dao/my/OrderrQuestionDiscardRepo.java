package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.OrderrQuestionDiscard;


/**
 * 订单之一对一问题提问放弃Repo
 * @author zmax
 *
 */
public interface OrderrQuestionDiscardRepo extends JpaRepository<OrderrQuestionDiscard, Integer> {}
