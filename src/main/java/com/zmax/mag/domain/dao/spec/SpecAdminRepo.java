package com.zmax.mag.domain.dao.spec;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Admin;

/**
 * 特别Repo
 * Spring Data 格式要求的自定义Dao
 * @author zmax
 *
 */
public interface SpecAdminRepo extends JpaRepository<Admin, Integer> {
	Page<Admin> findByNickname(String nickname,Pageable pageable); 
}