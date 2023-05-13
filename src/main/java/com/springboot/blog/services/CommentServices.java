package com.springboot.blog.services;

import com.springboot.blog.payload.CommentDTO;

import java.util.List;

public interface CommentServices {

    CommentDTO createComment(long id, CommentDTO comment);

    List<CommentDTO> getPostComments(long postId);

}
