package com.smallclover.nullpointerexception.controller.blog;

import com.smallclover.nullpointerexception.dto.DevelopLogDTO;
import com.smallclover.nullpointerexception.service.developLog.DevelopLogService;
import com.smallclover.nullpointerexception.util.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 开发日志
 * @Author: Amadeus
 * @Date: 2020/7/15 21:17
 */
@RequestMapping("/blog/develop-log")
@Controller("blog-develop-log")
@AllArgsConstructor
public class DevelopLogController {

    private DevelopLogService developLogService;

    /**
     * 跳转到开发日志页面
     * @return 开发日志视图
     */
    @GetMapping(value = {"", "/"})
    public String timeline(Model model){

        var developLogDTOS = developLogService.getAllDevelopLogs().stream().map(siteLog -> {
            String timeAgo = TimeUtils.getFewTimeAgo(siteLog.getCreateTime());
            var developLogDTO = new DevelopLogDTO();
            developLogDTO.setAuthor(siteLog.getAuthor());
            developLogDTO.setCreateTime(siteLog.getCreateTime());
            developLogDTO.setTimeAgo(timeAgo);
            List<String> content = Arrays.asList(siteLog.getContent().split(","));
            developLogDTO.setContent(content);
            return developLogDTO;
        }).collect(Collectors.toList());
        model.addAttribute("developLogs", developLogDTOS);
        return "/blog/develop_log";
    }
}
