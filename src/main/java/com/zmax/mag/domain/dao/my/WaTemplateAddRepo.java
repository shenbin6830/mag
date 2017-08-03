package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.WaTemplateAdd;


/**
 * 模板消息接口之获得模板IDRepo
 * @author zmax
 *
 */
public interface WaTemplateAddRepo extends JpaRepository<WaTemplateAdd, Integer> {}
