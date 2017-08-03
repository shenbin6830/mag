package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Oplog;


/**
 * 操作日志Repo
 * @author zmax
 *
 */
public interface OplogRepo extends JpaRepository<Oplog, Integer> {}
