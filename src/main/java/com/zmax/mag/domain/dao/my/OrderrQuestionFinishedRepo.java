package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.OrderrQuestionFinished;


/**
 * 订单之一对一问题提问归档Repo
 * @author zmax
 *
 */
public interface OrderrQuestionFinishedRepo extends JpaRepository<OrderrQuestionFinished, Integer> {}
