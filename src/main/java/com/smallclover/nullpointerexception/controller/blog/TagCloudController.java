package com.smallclover.nullpointerexception.controller.blog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallclover.nullpointerexception.dto.TagDTO;
import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.service.article.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Amadeus
 * @Date: 2020/6/23 21:36
 */
@RequestMapping("/blog/tags_cloud")
@Controller
@AllArgsConstructor
public class TagCloudController {

    private ArticleService articleService;

    @RequestMapping(value = {"/",""})
    public String index()
    {
        return "/blog/tags_cloud";
    }

    @RequestMapping("/json")
    public ResponseEntity tagCloud(){

        var articles = articleService.getAllArticles();
        var map = new HashMap<String, Integer>();
        for (Article article: articles){
            // 单个标签之间是以逗号分隔
            String[] tags = article.getTags().split(",");
            for (String tag: tags){
                map.put(tag, map.getOrDefault(tag, 0) + 1);
            }
        }

        var tags = new ArrayList<TagDTO>();
        for (Map.Entry<String, Integer> entry: map.entrySet()){
            var tag = new TagDTO();
            tag.setWord(entry.getKey());
            tag.setCount(entry.getValue());
            tags.add(tag);
        }

        return ResponseEntity.ok(tags);
    }
}
