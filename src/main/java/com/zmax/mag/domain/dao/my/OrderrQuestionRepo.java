package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.OrderrQuestion;


/**
 * 订单之一对一问题提问Repo
 * @author zmax
 *
 */
public interface OrderrQuestionRepo extends JpaRepository<OrderrQuestion, Integer> {}
