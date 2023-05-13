package com.springboot.blog.services.impl;

import com.springboot.blog.Repository.CommentRepository;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.services.CommentServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServicesImpl implements CommentServices {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CommentServicesImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public CommentDTO getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }
        return convertToCommentDTO(comment);
    }

    @Override
    public CommentDTO updateComment(long postId, long commentId, CommentDTO newCommentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }
        comment.setName(newCommentDTO.getName());
        comment.setEmail(newCommentDTO.getEmail());
        comment.setCommentBody(newCommentDTO.getCommentBody());
        return convertToCommentDTO(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }
        commentRepository.delete(comment);
    }

    private Comment convertToComment(CommentDTO commentDTO) {
        // This code is replaced by ModelMapper
//        Comment comment = new Comment();
//        comment.setId(commentDTO.getId());
//        comment.setName(commentDTO.getName());
//        comment.setEmail(commentDTO.getEmail());
//        comment.setDate(commentDTO.getDate());
//        comment.setCommentBody(commentDTO.getCommentBody());

        Comment comment = modelMapper.map(commentDTO, Comment.class);

        return comment;
    }
//    sdf

    private CommentDTO convertToCommentDTO(Comment comment) {

//        This code is replace by ModelMapper
//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setId(comment.getId());
//        commentDTO.setName(comment.getName());
//        commentDTO.setEmail(comment.getEmail());
//        commentDTO.setDate(comment.getDate());
//        commentDTO.setCommentBody(comment.getCommentBody());

        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

        return commentDTO;
    }
}
