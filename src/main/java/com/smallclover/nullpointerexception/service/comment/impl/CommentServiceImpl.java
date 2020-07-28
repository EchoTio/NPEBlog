package com.smallclover.nullpointerexception.service.comment.impl;

import com.smallclover.nullpointerexception.dto.CommentDto;
import com.smallclover.nullpointerexception.mapper.CommentMapper;
import com.smallclover.nullpointerexception.model.Comment;
import com.smallclover.nullpointerexception.service.comment.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论管理服务层
 * @Author: Amadeus
 * @Date: 2020/3/28 10:36
 */
@Service
public class CommentServiceImpl implements CommentService {

    private CommentMapper commentMapper;


    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> getTopComment(long articleId) {
        return commentMapper.getArticleTopComment(articleId);
    }

    @Override
    public List<Comment> getTopChildComment(long articleId, String commentParentId) {
        return commentMapper.getArticleTopChildComment(articleId, commentParentId);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentMapper.getAllComments();
    }

    @Override
    public long getCommentCount() {
        return commentMapper.getCommentCount();
    }

    @Override
    public boolean updateAuditStatus(long commentId) {
        long count = commentMapper.updateCommentAuditByCommentId(commentId, true);
        return count == 1;
    }

    @Override
    public boolean insertComment(Comment comment) {
        long count = commentMapper.insertComment(comment);
        return count == 1 ;
    }

    @Override
    public Comment getCommentByCommentId(long commentId) {
        return commentMapper.getCommentByCommentId(commentId);
    }

    @Override
    public List<CommentDto> getArticleCommentsByArticleId(long articleId) {
        List<CommentDto> commentDTOList = new ArrayList<>();
        List<Comment> comments = getTopComment(articleId);

        for (Comment comment: comments){
            CommentDto commentDTO = new CommentDto();
            BeanUtils.copyProperties(comment, commentDTO);
            var childCommentList = getTopChildComment(articleId, comment.getUserId());

            commentDTO.setChildComments(childCommentList);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

}
