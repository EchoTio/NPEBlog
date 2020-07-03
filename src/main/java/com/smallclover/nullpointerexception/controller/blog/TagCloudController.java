package com.smallclover.nullpointerexception.controller.blog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallclover.nullpointerexception.dto.TagDTO;
import com.smallclover.nullpointerexception.mapper.TagMapper;
import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.model.Tag;
import com.smallclover.nullpointerexception.service.article.ArticleService;
import com.smallclover.nullpointerexception.service.tag.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @Author: Amadeus
 * @Date: 2020/6/23 21:36
 */
@RequestMapping("/blog/tags-cloud")
@Controller
@AllArgsConstructor
public class TagCloudController {

    private TagService tagService;

    @RequestMapping(value = {"/",""})
    public String index()
    {
        return "/blog/tags_cloud";
    }

    @RequestMapping("/json")
    public ResponseEntity<List<TagDTO>> tagCloud(){

        List<Tag> tagList = tagService.getAllTags();

        var map = new HashMap<String, Integer>();


        for (Tag tag: tagList){
            map.put(tag.getTagName(), map.getOrDefault(tag.getTagName(), 0) + 1);
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
