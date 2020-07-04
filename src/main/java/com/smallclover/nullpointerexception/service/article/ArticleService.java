package com.smallclover.nullpointerexception.service.article;

import com.smallclover.nullpointerexception.model.Article;

import java.util.List;

/**
 * 文章操作 服务层
 * @author Amadeus
 * @date 2019-11-25
 */
public interface ArticleService {
    /**
     * 取得所有文章
     * @return 文章实体类列表
     */
    List<Article> getAllArticles();

    /**
     * 通过文章id来获取文章
     * @return 文章实体类
     */
    Article getArticleById(long id);

    /**
     * 获取文章数
     * @return 文章总数s
     */
    long getArticleCount();

    /**
     * 根据入力Id删除文章
     * @return
     */
    long deleteArticleById(long articleId);

    boolean insertArticle(Article article);

    boolean publishArticle(long articleId);

    List<Article> getArticlesByIds(List<Long> articleIds);
}
