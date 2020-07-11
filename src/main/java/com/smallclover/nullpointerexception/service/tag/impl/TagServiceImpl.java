package com.smallclover.nullpointerexception.service.tag.impl;

import com.smallclover.nullpointerexception.mapper.ArticleMapper;
import com.smallclover.nullpointerexception.mapper.TagArticleMapper;
import com.smallclover.nullpointerexception.mapper.TagMapper;
import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.model.Tag;
import com.smallclover.nullpointerexception.model.TagArticle;
import com.smallclover.nullpointerexception.service.tag.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
