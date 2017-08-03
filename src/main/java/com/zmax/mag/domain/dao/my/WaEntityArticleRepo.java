package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.WaEntityArticle;


/**
 * 共用对象之图文Repo
 * @author zmax
 *
 */
public interface WaEntityArticleRepo extends JpaRepository<WaEntityArticle, Integer> {}
