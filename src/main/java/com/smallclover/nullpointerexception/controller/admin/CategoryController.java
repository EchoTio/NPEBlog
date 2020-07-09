package com.smallclover.nullpointerexception.controller.admin;

import com.smallclover.nullpointerexception.model.Category;
import com.smallclover.nullpointerexception.model.Tag;
import com.smallclover.nullpointerexception.service.category.CategoryService;
import com.smallclover.nullpointerexception.service.tag.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Author: Amadeus
 * @Date: 2020/7/8 21:10
 */
@Controller
@RequestMapping("/admin/category")
@AllArgsConstructor
public class CategoryController {

    private TagService tagService;
    private CategoryService categoryService;


    private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    @RequestMapping("/manager")
    public String index(Model model){
        var tags = tagService.getAllTags()
                .stream()
                .map(Tag::getTagName)
                .distinct()
                .collect(Collectors.toList());
        var categories = categoryService.selectAllCategories()
                .stream()
                .map(Category::getCategoryName)
                .distinct()
                .collect(Collectors.toList());
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
