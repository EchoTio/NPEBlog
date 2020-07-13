package com.smallclover.nullpointerexception.service.comment;

import com.smallclover.nullpointerexception.dto.CommentDTO;
import com.smallclover.nullpointerexception.model.Comment;

import java.util.List;

/**
 * @Author: Amadeus
 * @Date: 2020/3/28 10:08
 */
 public interface CommentService {

     List<Comment> getTopComment(long articleId);

     List<Comment> getTopChildComment(long articleId, String commentParentId);

     List<Comment> getAllComments();

     long getCommentCount();

     boolean updateAuditStatus(long commentId);

     boolean insertComment(Comment comment);
    
     Comment getCommentByCommentId(long commentId);

    List<CommentDTO> getArticleCommentsByArticleId(long articleId);
}
