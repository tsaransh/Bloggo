package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.services.CommentServices;
import org.hibernate.boot.model.source.spi.AttributePath;
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

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getPostCommentsById(@PathVariable(value="postId") long postId,
                                                          @PathVariable(value="commentId") long commentId) {
        return new ResponseEntity<>(commentServices.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(value="postId") long postId,
                                    @PathVariable(value="commentId") long commentId,
                                    @RequestBody CommentDTO newCommentDTO) {
        return new ResponseEntity<>(commentServices.updateComment(postId,commentId,newCommentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long postId,
                              @PathVariable long commentId) {
        commentServices.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment Successfully deleted", HttpStatus.OK);
    }

}
