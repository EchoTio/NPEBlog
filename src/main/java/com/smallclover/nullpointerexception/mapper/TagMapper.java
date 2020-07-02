package com.smallclover.nullpointerexception.mapper;

import com.smallclover.nullpointerexception.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
}
