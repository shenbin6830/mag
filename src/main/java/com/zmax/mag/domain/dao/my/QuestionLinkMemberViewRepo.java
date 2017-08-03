package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.QuestionLinkMemberView;


/**
 * 观看问题的会员Repo
 * @author zmax
 *
 */
public interface QuestionLinkMemberViewRepo extends JpaRepository<QuestionLinkMemberView, Integer> {}
