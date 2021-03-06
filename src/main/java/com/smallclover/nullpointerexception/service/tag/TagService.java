package com.smallclover.nullpointerexception.service.tag;

import com.smallclover.nullpointerexception.dto.TagDto;
import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.model.Tag;

import java.util.List;
import java.util.Map;

/**
 * @Author: Amadeus
 * @Date: 2020/7/2 22:00
 */
public interface TagService {
    /**
     * 从标签主表取得所有标签
     * @return
     */
    List<Tag> getAllTags();

    /**
     * 根据标签名取得该标签所拥有的所有文章
     * @param tagName
     * @return
     */
    List<Article> getArticlesByTagName(String tagName);

    /**
     * 根据标签名取得该标签名所代表的标签实体
     * @param tagNames
     * @return
     */
    List<Tag> getTagsByTagNames(List<String> tagNames);

    /**
     * 根据文章id获取该文章拥有的标签
     * @param articleIds
     * @return
     */
    Map<Long, String> getTagsByArticleIds(List<Long> articleIds);
    boolean insertTags(String tags);
    List<TagDto> getTagCloud();
}
