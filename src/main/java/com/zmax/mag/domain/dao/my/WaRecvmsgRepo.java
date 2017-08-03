package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.WaRecvmsg;


/**
 * 接收到的消息Repo
 * @author zmax
 *
 */
public interface WaRecvmsgRepo extends JpaRepository<WaRecvmsg, Integer> {}
