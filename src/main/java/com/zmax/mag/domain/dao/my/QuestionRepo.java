package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Question;


/**
 * 一对一问题Repo
 * @author zmax
 *
 */
public interface QuestionRepo extends JpaRepository<Question, Integer> {}
