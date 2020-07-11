package com.smallclover.nullpointerexception.controller.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smallclover.nullpointerexception.constant.ResponseStatusCode;
import com.smallclover.nullpointerexception.dto.ApiResponse;
import com.smallclover.nullpointerexception.dto.CommentDTO;
import com.smallclover.nullpointerexception.model.Article;
import com.smallclover.nullpointerexception.model.Comment;
import com.smallclover.nullpointerexception.service.article.ArticleService;
import com.smallclover.nullpointerexception.service.comment.CommentService;
import com.smallclover.nullpointerexception.service.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文章内容编辑
 * 文章管理
 */
@Slf4j//引入lombok日志注解，默认的的日志对象名是log
@RestController
@RequestMapping("/blog/article")
@AllArgsConstructor
public class ArticleController{

    // 文章相关服务层
    private ArticleService articleService;
    // 评论相关服务层
    private CommentService commentService;
    // 发送邮件通知
    private MailService mailService;


    //TODO 缓存的引入
    //TODO 标签数据结构修改


    /**
     * 显示指定Id文章
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public ModelAndView articleDetail(@PathVariable("id") long id){
        var mv = new ModelAndView();
        Article article = articleService.getArticleById(id);

        if (!article.isPublish()){
            mv.setViewName("/blog/common/article_forbid_access");
            return mv;
        }

        mv.addObject("article",article);

        List<CommentDTO> commentDTOList = new ArrayList<>();
        List<Comment> comments = commentService.getTopComment(id);

        for (Comment comment: comments){
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            var childCommentList = commentService.getTopChildComment(id, comment.getUserId());

            commentDTO.setChildComments(childCommentList);
            commentDTOList.add(commentDTO);
        }

        mv.addObject("comments", commentDTOList);
        mv.setViewName("/blog/article_detail");
        return mv;
    }

    /**
     * 显示文章列表
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping(value = {"/list", "/", ""})
    public ModelAndView articleList(
            @RequestParam(required = true, defaultValue = "1") Integer page,
            @RequestParam(required = true, defaultValue = "10") Integer pageSize){
        PageHelper.startPage(page, pageSize);
        var articles = articleService.getAllArticles();
        var pageInfo = new PageInfo<>(articles);
        var mv = new ModelAndView();
        mv.addObject("articles", articles);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("/blog/article_list");
        return mv;
    }

    @PostMapping("/comment")
    public ApiResponse commentPost(@Valid @RequestBody CommentDTO commentDTO){

            var comment = new Comment();
            BeanUtils.copyProperties(commentDTO, comment);
            comment.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            comment.setDeleteFlag(false);
            comment.setPassAudit(false);

            //生成唯一id 替换uuid中的"-"
            String userId= UUID.randomUUID().toString().replace("-", "");
            comment.setUserId(userId);

            boolean result = commentService.insertComment(comment);

            if (result){
                return ApiResponse.ok();
            }
            return ApiResponse.fail(ResponseStatusCode.INTERNAL_SERVER_ERROR.getCode(),
                    ResponseStatusCode.INTERNAL_SERVER_ERROR.getDesc());
    }

}
