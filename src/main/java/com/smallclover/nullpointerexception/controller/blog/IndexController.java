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

    /**
     * 跳转到首页
     * @return 首页视图
     */
    @RequestMapping(value = {"/", "/index", "/blog/index"})
    public String index(){
        return "/blog/index";
    }

}
