package com.smallclover.nullpointerexception.mapper;

import com.smallclover.nullpointerexception.model.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Amadeus
 * @Date: 2020/7/2 22:20
 */
@Mapper
@Repository
public interface TagMapper {

    @Select("SELECT * FROM tag")
    @Results(id = "tag",value = {
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "articleId", column = "article_id"),
            @Result(property = "tagName", column = "tag_name"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag")
    })
    List<Tag> getAllTags();

    @Select("SELECT article_id FROM tag WHERE tag_name=#{tagName}")
    List<Long> getAllArticleIdByTagName(String tagName);

    @Select("SELECT * FROM tag WHERE article_id=#{articleId}")
    @ResultMap("tag")
    List<Tag> getAllTagByArticleId(long articleId);
}
