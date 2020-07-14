package com.smallclover.nullpointerexception.controller.blog;

import com.smallclover.nullpointerexception.model.Journey;
import com.smallclover.nullpointerexception.service.journey.JourneyService;
import com.smallclover.nullpointerexception.util.TimeUtils;
import com.smallclover.nullpointerexception.dto.DevelopLogDTO;
import com.smallclover.nullpointerexception.service.developLog.DevelopLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Amadeus
 * @date 2019-12-16
 * 前端页面控制器
 */
@Controller("homeController")
@RequestMapping("/")
@AllArgsConstructor
public class IndexController {

    private DevelopLogService siteLogService;


    /**
     * 跳转到首页
     * @return 首页视图
     */
    @RequestMapping(value = {"/", "/index", "/blog/index"})
    public String index(){
        return "/blog/index";
    }

    /**
     * 跳转到关于页面
     * @return 关于视图
     */
    @GetMapping("/blog/about")
    public String about(){
        return "/blog/about";
    }

    /**
     * 跳转到博客页面
     * @return 博客视图
     */
    @GetMapping("/blog")
    public String article(){
        // 跳转到ArticleController来处理视图
        return "forward:/api/article/list";
    }

    /**
     * 跳转到开发日志页面
     * @return 开发日志视图
     */
    @GetMapping("/blog/develop-log")
    public String timeline(Model model){

        var developLogDTOS = siteLogService.getAllDevelopLogs().stream().map(siteLog -> {
            String timeAgo = TimeUtils.getFewTimeAgo(siteLog.getCreateTime());
            var developLogDTO = new DevelopLogDTO();
            developLogDTO.setAuthor(siteLog.getAuthor());
            developLogDTO.setCreateTime(siteLog.getCreateTime());
            developLogDTO.setTimeAgo(timeAgo);
            List<String> content =Arrays.asList(siteLog.getContent().split(","));
            developLogDTO.setContent(content);
            return developLogDTO;
        }).collect(Collectors.toList());
        model.addAttribute("developLogs", developLogDTOS);
        return "/blog/develop_log";
    }
}
