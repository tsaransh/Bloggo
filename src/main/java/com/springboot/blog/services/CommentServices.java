package com.springboot.blog.services;

import com.springboot.blog.payload.CommentDTO;

import java.util.List;

public interface CommentServices {

    CommentDTO createComment(long id, CommentDTO comment);

    List<CommentDTO> getPostComments(long postId);

    CommentDTO getCommentById(long postId,long commentId);

    CommentDTO updateComment(long id, long commentId, CommentDTO newCommentDTO);

    void deleteComment(long postId, long commentId);
}
