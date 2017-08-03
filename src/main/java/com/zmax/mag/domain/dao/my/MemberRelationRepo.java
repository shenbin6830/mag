package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.MemberRelation;


/**
 * 会员父子关系Repo
 * @author zmax
 *
 */
public interface MemberRelationRepo extends JpaRepository<MemberRelation, Integer> {}
