package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentServices commentServices;

    @Autowired
    public CommentController(CommentServices commentServices) {
        this.commentServices = commentServices;
    }

    @PostMapping("/posts/{postId}/comment")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value="postId") long id, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentServices.createComment(id,commentDTO), HttpStatus.CREATED);
    }

}
