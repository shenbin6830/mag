package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Wxmsgtemplate;


/**
 * 微信自定义模板Repo
 * @author zmax
 *
 */
public interface WxmsgtemplateRepo extends JpaRepository<Wxmsgtemplate, Integer> {}
