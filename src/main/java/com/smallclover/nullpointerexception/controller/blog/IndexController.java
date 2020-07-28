package com.smallclover.nullpointerexception.controller.blog;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
