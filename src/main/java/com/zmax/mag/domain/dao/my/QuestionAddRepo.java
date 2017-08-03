package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.QuestionAdd;


/**
 * 一对一问题之追加Repo
 * @author zmax
 *
 */
public interface QuestionAddRepo extends JpaRepository<QuestionAdd, Integer> {}
