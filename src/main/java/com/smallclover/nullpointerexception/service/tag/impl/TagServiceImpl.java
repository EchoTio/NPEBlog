package com.smallclover.nullpointerexception.service.tag.impl;

import com.smallclover.nullpointerexception.dto.TagDto;
import com.smallclover.nullpointerexception.mapper.ArticleMapper;
import com.smallclover.nullpointerexception.mapper.TagArticleMapper;
import com.smallclover.nullpointerexception.mapper.TagMapper;
import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.model.ArticleTagCategory;
import com.smallclover.nullpointerexception.model.Tag;
import com.smallclover.nullpointerexception.service.tag.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Amadeus
 * @Date: 2020/7/2 22:00
 */
@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private TagMapper tagMapper;
    private TagArticleMapper tagArticleMapper;
    private ArticleMapper articleMapper;

    @Override
    public List<Tag> getAllTags() {
        return tagMapper.getAllTags();
    }

    @Override
    public List<Article> getArticlesByTagName(String tagName) {

        var tag = tagMapper.getTagIdByTagName(tagName);
        var articleIds = tagArticleMapper.getAllArticleIdByTagId(tag.getId());
        return articleMapper.getArticlesByIds(articleIds);
    }

    @Override
    public List<Tag> getTagsByTagNames(List<String> tagNames) {
        return tagMapper.getTagsByTagNames(tagNames);
    }

    @Override
    public Map<Long, String> getTagsByArticleIds(List<Long> articleIds) {

        List<ArticleTagCategory> articleTagCategories = tagMapper.getTagsByArticleIds(articleIds);

        var articleIdAndTagsMap = new HashMap<Long, String>();
        for (ArticleTagCategory atc: articleTagCategories){
            String tags = atc.getTagName() + "," + articleIdAndTagsMap.getOrDefault(atc.getArticleId(), "");
            articleIdAndTagsMap.put(atc.getArticleId(), tags);
        }


        return articleIdAndTagsMap;
    }

    @Override
    public Map<Long, String> getCategoryByArticleIds(List<Long> articleIds) {
        List<ArticleTagCategory> articleTagCategories = tagMapper.getCategoryByArticleIds(articleIds);
        var articleIdAndCategoryMap = new HashMap<Long, String>();
        for (ArticleTagCategory atc: articleTagCategories){
            articleIdAndCategoryMap.put(atc.getArticleId(), atc.getCategoryName());
        }
        return articleIdAndCategoryMap;
    }

    @Override
    public boolean insertTags(String tags) {
        String[] tagNames = StringUtils.split(tags, " ");
        var tagList = new ArrayList<Tag>();
        for (String tagName: tagNames){
            var tag = new Tag();
            tag.setTagName(tagName);
            tag.setCreateTime(LocalDateTime.now());
            tag.setUpdateTime(LocalDateTime.now());
            tag.setDeleteFlag(false);
            tagList.add(tag);
        }
        long count = tagMapper.insertTags(tagList);
        return count >= 0;
    }

}
