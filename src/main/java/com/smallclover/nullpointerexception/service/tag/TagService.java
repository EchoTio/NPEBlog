package com.smallclover.nullpointerexception.service.tag;

import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.model.Tag;

import java.util.List;

/**
 * @Author: Amadeus
 * @Date: 2020/7/2 22:00
 */
public interface TagService {

    List<Tag> getAllTags();
    List<Article> getArticlesByTagName(String tagName);
    List<Tag> getTagsByTagNames(List<String> tagNames);
}
