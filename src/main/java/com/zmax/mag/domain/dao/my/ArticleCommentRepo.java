package com.zmax.mag.domain.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmax.mag.domain.bean.ArticleComment;


/**
 * 文章的评论Repo
 * @author zmax
 *
 */
public interface ArticleCommentRepo extends JpaRepository<ArticleComment, Integer> {}
