package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.QuickLinkMemberView;


/**
 * 观看抢答的会员Repo
 * @author zmax
 *
 */
public interface QuickLinkMemberViewRepo extends JpaRepository<QuickLinkMemberView, Integer> {}
