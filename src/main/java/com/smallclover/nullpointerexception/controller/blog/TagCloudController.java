package com.smallclover.nullpointerexception.controller.blog;

import com.smallclover.nullpointerexception.dto.TagDTO;
import com.smallclover.nullpointerexception.model.Tag;
import com.smallclover.nullpointerexception.service.article.ArticleService;
import com.smallclover.nullpointerexception.service.tag.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Amadeus
 * @Date: 2020/6/23 21:36
 */
@RequestMapping("/blog/tags-cloud")
@Controller
@AllArgsConstructor
public class TagCloudController {

    private TagService tagService;
    private ArticleService articleService;

    @RequestMapping(value = {"/",""})
    public String index()
    {
        return "/blog/tags_cloud";
    }

    /**
     * 获取标签云
     * @return
     */
    @RequestMapping("/json")
    public ResponseEntity<List<TagDTO>> tagCloud(){

        List<String> tagNames = tagService.getAllTags()
                .stream()
                .map(Tag::getTagName)
                .collect(Collectors.toList());
        List<Tag> tags = tagService.getTagsByTagNames(tagNames);
        var map = new HashMap<String, Integer>();


        // 标签名:权重
        for (Tag tag: tags){
            map.put(tag.getTagName(), map.getOrDefault(tag.getTagName(), 0) + 1);
        }

        var tagDTOs = new ArrayList<TagDTO>();
        for (Map.Entry<String, Integer> entry: map.entrySet()){
            var tagDTO = new TagDTO();
            tagDTO.setWord(entry.getKey());
            tagDTO.setCount(entry.getValue());
            tagDTOs.add(tagDTO);
        }

        return ResponseEntity.ok(tagDTOs);
    }

    @RequestMapping("/{tag_name}")
    public String getTagNameHoldArticles(@PathVariable("tag_name") String tagName, Model model){

        var articles = tagService.getArticlesByTagName(tagName);
        model.addAttribute("articles", articles);
        return "/blog/tag_hold_articles";
    }
}
