package com.smallclover.nullpointerexception.service.tag.impl;

import com.smallclover.nullpointerexception.mapper.TagMapper;
import com.smallclover.nullpointerexception.model.Tag;
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

    @Override
    public List<Tag> getAllTags() {
        return tagMapper.getAllTags();
    }

    @Override
    public List<Long> getAllArticleIdByTagName(String tagName) {
        return tagMapper.getAllArticleIdByTagName(tagName);
    }

    @Override
    public List<Tag> getAllTagByArticleId(long articleId) {
        return tagMapper.getAllTagByArticleId(articleId);
    }

}
