package com.smallclover.nullpointerexception.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.service.article.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章管理
 * @author Amadeus
 * @date 2020-01-23
 */
@RequestMapping("/admin/article/manager")
@Controller
@AllArgsConstructor
public class ArticleManagerController {

    private final ArticleService articleService;

    /**
     * 文章管理首页
     * @param model
     * @return
     */
    @GetMapping("")
    public String index(@RequestParam(required = true, defaultValue = "1") Integer page,
                        @RequestParam(required = true, defaultValue = "10") Integer pageSize,
                        Model model){
            PageHelper.startPage(page, pageSize);
        List<Article> articleList = articleService.getAllArticles();
        var pageInfo = new PageInfo<>(articleList);
        model.addAttribute("articles", articleList);
        model.addAttribute("pageInfo", pageInfo);

        return "/admin/article_manager";
    }

    /**
     * 更新指定文章
     * @param articleId
     * @param model
     * @return
     */
    @GetMapping("edit/{articleId}")
    public String updateArticle(@PathVariable long articleId, Model model){
        var article = articleService.getArticleById(articleId);
        model.addAttribute("updateArticle", article);
        return "/admin/article_publish";
    }

    /**
     * 删除指定文章
     * @param articleId
     * @return
     */
    @PostMapping("delete")
    public @ResponseBody ResponseEntity deleteArticle(@RequestParam long articleId){
        var count = articleService.deleteArticleById(articleId);
        if (count > 0){
            return ResponseEntity.ok().body("success");
        }else{
            return ResponseEntity.badRequest().body("error");
        }
    }

    @PostMapping("publish")
    public @ResponseBody ResponseEntity publishArticle(@RequestParam long articleId){
        boolean result = articleService.publishArticle(articleId);
        if (result){
            return ResponseEntity.ok().body("success");
        }else{
            return ResponseEntity.badRequest().body("error");
        }
    }
}
