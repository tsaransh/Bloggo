package com.springboot.blog.services;

import com.springboot.blog.payload.CommentDTO;

public interface CommentServices {

    CommentDTO createComment(long id, CommentDTO comment);

}
