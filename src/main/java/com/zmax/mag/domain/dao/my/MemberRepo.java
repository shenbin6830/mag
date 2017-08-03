package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Member;


/**
 * 会员Repo
 * @author zmax
 *
 */
public interface MemberRepo extends JpaRepository<Member, Integer> {}
