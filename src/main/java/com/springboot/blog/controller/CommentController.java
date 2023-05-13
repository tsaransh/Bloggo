package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentServices commentServices;

    @Autowired
    public CommentController(CommentServices commentServices) {
        this.commentServices = commentServices;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value="postId") long id, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentServices.createComment(id,commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getPostComments(@PathVariable(value="postId") long postId) {
        return commentServices.getPostComments(postId);
    }

}
