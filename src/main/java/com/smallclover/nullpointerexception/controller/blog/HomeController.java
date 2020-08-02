package com.smallclover.nullpointerexception.controller.blog;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * @author Amadeus
 * @date 2019-12-16
 */
@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    /**
     * 跳转到首页
     * @return 首页视图
     */
    @RequestMapping(value = {"/", "/index", "/blog/index"})
    public String index(){
        return "/blog/index";
    }

}
