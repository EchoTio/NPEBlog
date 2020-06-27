package com.smallclover.nullpointerexception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 站点统计
 * @Author: Amadeus
 * @Date: 2020/6/3 21:26
 */
@Data
@AllArgsConstructor
public class SiteVisitDTO {
    // 站点访问统计
    private VisitDTO siteDTO;
    // 页面访问统计
    private VisitDTO uriDTO;
}
