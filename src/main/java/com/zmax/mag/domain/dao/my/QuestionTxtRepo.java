package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.QuestionTxt;


/**
 * 一对一问题回答Repo
 * @author zmax
 *
 */
public interface QuestionTxtRepo extends JpaRepository<QuestionTxt, Integer> {}
