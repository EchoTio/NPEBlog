package com.smallclover.nullpointerexception.service.article.impl;

import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.mapper.ArticleMapper;
import com.smallclover.nullpointerexception.service.article.ArticleService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * 文章操作服务层实现类
 * @author Amadeus
 * @date 2019-11-25
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    // 文章Mapper
    private ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * 查询所有文章
     * @return 包含所有文章的列表
     */
    @Cacheable(cacheNames = "articles")
    @Override
    public List<Article> getAllArticles() {
        return articleMapper.getAllArticles();
    }

    /**
     * 根据文章id来获取文章
     * @param id 文章id
     * @return 文章实体类
     */
    @Cacheable(cacheNames = "article")
    @Override
    public Article getArticleById(long id) {
        return articleMapper.getArticleById(id);
    }

    /**
     * 获取文章总数
     * @return 文章总数
     */
    @Override
    public long getArticleCount() {
        return articleMapper.getArticleCount();
    }

    @CacheEvict(cacheNames = "articles")
    @Override
    public long deleteArticleById(long articleId) {
        return articleMapper.deleteArticleById(articleId,true );
    }

    @CachePut(cacheNames = "articles")
    @Override
    public boolean insertArticle(Article article) {
        // 浏览量
        article.setContentView(0);
        // 文章创建时间
        article.setCreateTime(new Timestamp(System.currentTimeMillis()));

        long count = articleMapper.insertArticle(article);

        return count != 0 ;
    }

    @Override
    public boolean publishArticle(long articleId) {
        long count = articleMapper.updateArticleById(articleId, true, false);
        return count >= 0;
    }
}
