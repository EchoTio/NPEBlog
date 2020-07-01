package com.smallclover.nullpointerexception.controller.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Amadeus
 * @Date: 2020/7/1 21:11
 */
@Controller
@RequestMapping("/blog/archive")
public class ArchiveController {

    @RequestMapping("")
    public String index(){
        return "/blog/archive";
    }
}
