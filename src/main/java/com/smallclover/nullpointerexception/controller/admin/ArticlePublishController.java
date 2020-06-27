package com.smallclover.nullpointerexception.controller.admin;

import com.smallclover.nullpointerexception.dto.ArticlePublishDTO;
import com.smallclover.nullpointerexception.mapper.ArticleMapper;
import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.property.NPEResourcesProperties;
import com.smallclover.nullpointerexception.service.article.ArticleService;
import com.smallclover.nullpointerexception.service.article.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amadeus
 * @date 2020-04-04
 */
@RequestMapping("/admin/article")
@RestController
@Slf4j
@AllArgsConstructor
public class ArticlePublishController {

    private final ArticleService articleService;
    private final FileService fileService;

    public static final String ARTICLE_IMG_URL = "/upload/article/img/";


    @GetMapping("/publish")
    public ModelAndView articlePublish(){
        var mv = new ModelAndView();
        mv.setViewName("/admin/article_publish");
        return mv;
    }

    /**
     * 上传文章
     * @param
     * @return
     */
    @PostMapping("/add/content")
    public Map<String, Object> addArticle(@RequestBody ArticlePublishDTO articlePublishDTO){
        var article = new Article();
        BeanUtils.copyProperties(articlePublishDTO, article);
        articleService.insertArticle(article);
        var map = new HashMap<String, Object>();
        map.put("url", "list");
        return map;
    }


    /**
     * 上传文章里插入的图片
     * @param pic 图片
     * @return 上传结果
     */
    @PostMapping("/add/content/image")
    public Map<String, Object> addArticleImage(
            @RequestParam(value = "editormd-image-file", required = false) MultipartFile pic){
        var map = new HashMap<String, Object>();
        fileService.storeFile(pic);
        map.put("success", 1);
        map.put("message", "test");
        map.put("url", ARTICLE_IMG_URL + pic.getOriginalFilename());

        return map;
    }
}
