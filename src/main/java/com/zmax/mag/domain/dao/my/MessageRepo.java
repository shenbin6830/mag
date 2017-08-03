package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Message;


/**
 * 短消息Repo
 * @author zmax
 *
 */
public interface MessageRepo extends JpaRepository<Message, Integer> {}
