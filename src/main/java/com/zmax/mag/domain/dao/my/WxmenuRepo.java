package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Wxmenu;


/**
 * 微信自定义菜单Repo
 * @author zmax
 *
 */
public interface WxmenuRepo extends JpaRepository<Wxmenu, Integer> {}
