package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.QuickTxtComment;


/**
 * 抢答的评价Repo
 * @author zmax
 *
 */
public interface QuickTxtCommentRepo extends JpaRepository<QuickTxtComment, Integer> {}
