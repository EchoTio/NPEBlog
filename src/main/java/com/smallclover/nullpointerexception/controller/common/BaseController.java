package com.smallclover.nullpointerexception.controller.common;

import com.smallclover.nullpointerexception.service.visit.VisitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

/**
 * @author Amadeus
 * @date 2019-12-07
 * 共通控制器用于处理一些共通业务
 */
@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class BaseController {

    private VisitService visitService;

//    // 获取网站信息
//    private WebSiteService webSiteService;
//
//    public BaseController(WebSiteService webSiteService) {
//        this.webSiteService = webSiteService;
//    }

    @ModelAttribute
    public void webSiteConfig(Model model){
        visitService.build("blog");
        Map<Object, Object> articleViewMap = visitService.getArticleAccessRecord();
        model.addAttribute("articleMap", articleViewMap);
//        // 网站的配置信息
//        var config = webSiteService.getWebSiteConfig();
//        model.addAttribute("config", config);
    }
}
