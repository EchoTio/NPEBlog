package com.smallclover.nullpointerexception.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author: Amadeus
 * @Date: 2020/7/8 21:10
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {


    private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    @RequestMapping("/manager")
    public String index(Model model){
        var tags = Arrays.asList("java", "HelloWorld", "test");
        var categories = Arrays.asList("技术", "旅游");
        model.addAttribute("randColor", rand_color());
        model.addAttribute("tags", tags);
        model.addAttribute("categories", categories);
        return "admin/category";
    }

    private String rand_color() {
        Random random = new Random();
        int r =  random.nextInt(COLORS.length - 1) % (COLORS.length) ;
        return COLORS[r];
    }

}
