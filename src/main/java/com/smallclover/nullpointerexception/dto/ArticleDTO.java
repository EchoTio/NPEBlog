package com.smallclover.nullpointerexception.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 文章DTO
 * @author Amadeus
 * @date 2020-05-04
 */
@Data
public class ArticleDTO {
    // 标题
    @NotBlank(message = "标题不能为空")
    private String title;
    // 描述
    @NotBlank(message = "描述不能为空")
    private String desc;
    // html内容
    @NotBlank(message = "内容不能为空")
    private String htmlContent;
    // markdown 内容
    @NotBlank(message = "内容不能为空")
    private String mdContent;
    // 分类
    @NotBlank(message = "分类不能为空")
    private String category;
    // 标签
    @NotBlank(message = "标签不能为空")
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