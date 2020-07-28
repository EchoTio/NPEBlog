package com.smallclover.nullpointerexception.service.tag;

import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.model.Tag;

import java.util.List;
import java.util.Map;

/**
 * @Author: Amadeus
 * @Date: 2020/7/2 22:00
 */
public interface TagService {

    List<Tag> getAllTags();
    List<Article> getArticlesByTagName(String tagName);
    List<Tag> getTagsByTagNames(List<String> tagNames);
    Map<Long, String> getTagsByArticleIds(List<Long> articleIds);
    Map<Long, String> getCategoryByArticleIds(List<Long> articleIds);
}
