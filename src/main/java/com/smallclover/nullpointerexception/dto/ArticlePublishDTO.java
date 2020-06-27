package com.smallclover.nullpointerexception.dto;

import lombok.Data;

/**
 * articleDTO
 * @author Amadeus
 * @date 2020-05-04
 */
@Data
public class ArticlePublishDTO {
    // 标题
    private String title;
    // 描述
    private String desc;
    // html内容
    private String htmlContent;
    // markdown 内容
    private String mdContent;
    // 分类
    private String category;
    // 关键词
    private String tags;
    // 是否开启评论
    private boolean comment;
    // 是否是草稿
    private boolean status;
    // 是否发布
    private boolean publish;
    // 删除标志
    private boolean deleteFlag;
}
