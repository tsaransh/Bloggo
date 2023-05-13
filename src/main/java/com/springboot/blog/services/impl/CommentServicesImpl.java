package com.springboot.blog.services.impl;

import com.springboot.blog.Repository.CommentRepository;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServicesImpl implements CommentServices {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServicesImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDTO createComment(long id, CommentDTO commentDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        Comment comment = convertToComment(commentDTO);
        comment.setPost(post);
        return convertToCommentDTO(commentRepository.save(comment));
    }

    @Override
    public List<CommentDTO> getPostComments(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> convertToCommentDTO(comment)).collect(Collectors.toList());
    }

    private Comment convertToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setDate(commentDTO.getDate());
        comment.setCommentBody(commentDTO.getCommentBody());
        return comment;
    }
//    sdf

    private CommentDTO convertToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setDate(comment.getDate());
        commentDTO.setCommentBody(comment.getCommentBody());
        return commentDTO;
    }
}
