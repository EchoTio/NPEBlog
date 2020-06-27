package com.smallclover.nullpointerexception.dto;

import lombok.Data;

import java.util.List;

/**
 *
 * 网站更新日志传输类
 * @author Amadeus
 * @date 2020-01-19
 */
@Data
public class DevelopLogDTO {

    // 修复内容
    private List<String> content;
    // 问题修复时间
    private String createTime;
    // 修复者
    private String author;
    // 多少时间前
    private String timeAgo;
}
