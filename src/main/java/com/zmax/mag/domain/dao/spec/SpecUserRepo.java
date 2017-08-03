package com.zmax.mag.domain.dao.spec;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.User;


/**
 * 用户特别Repo
 * @author Administrator
 *
 */
public interface SpecUserRepo extends JpaRepository<User, Integer> {
	Page<User> findByUsername(String username,Pageable pageable); 
}