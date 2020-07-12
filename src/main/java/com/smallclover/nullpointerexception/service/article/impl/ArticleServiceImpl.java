package com.smallclover.nullpointerexception.service.article.impl;

import com.smallclover.nullpointerexception.dto.ArticleDTO;
import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.mapper.ArticleMapper;
import com.smallclover.nullpointerexception.service.article.ArticleService;
import com.smallclover.nullpointerexception.service.tag.TagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章操作服务层实现类
 * @author Amadeus
 * @date 2019-11-25
 */
@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    // 文章Mapper
    private ArticleMapper articleMapper;
    private TagService tagService;

    /**
     * 查询所有文章
     * @return 包含所有文章的列表
     */
//    @Cacheable(cacheNames = "articles")
    @Override
    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleMapper.getAllArticles();
        List<Long> articleIds = articles.stream().map(Article::getId).collect(Collectors.toList());
        var articleIdAndCategoryMap = tagService.getCategoryByArticleIds(articleIds);
        var articleIdAndTagsMap = tagService.getTagsByArticleIds(articleIds);

        var articleDTOs = new ArrayList<ArticleDTO>();
        for(Article article: articles){
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article, articleDTO);
            articleDTO.setTags(articleIdAndTagsMap.get(article.getId()));
            articleDTO.setCategory(articleIdAndCategoryMap.get(article.getId()));
            articleDTOs.add(articleDTO);
        }
        return articleDTOs;
    }

    @Override
    public List<Article> getAllArticleNoCategory() {
        return articleMapper.getAllArticles();
    }

    /**
     * 根据文章id来获取文章
     * @param id 文章id
     * @return 文章实体类
     */
//    @Cacheable(cacheNames = "article", key = "#id")
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

    @Caching(evict = {
            @CacheEvict(cacheNames = "article", key = "#articleId"),
            @CacheEvict(cacheNames = "articles", allEntries = true)
    })
    @Override
    public long deleteArticleById(long articleId) {
        return articleMapper.deleteArticleById(articleId,true );
    }

//    @CacheEvict(cacheNames = "articles", allEntries = true)
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

    @Override
    public List<Article> getArticlesByIds(List<Long> articleIds) {
        return articleMapper.getArticlesByIds(articleIds);
    }

    @Override
    public List<Article> getArticlesOrderByCreateTime() {
        return articleMapper.getAllArticlesOrderByCreateTime();
    }
}
