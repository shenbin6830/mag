package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.Article;


/**
 * 文章Repo
 * @author zmax
 *
 */
public interface ArticleRepo extends JpaRepository<Article, Integer> {}
