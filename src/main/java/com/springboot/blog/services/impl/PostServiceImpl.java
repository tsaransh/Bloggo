package com.springboot.blog.services.impl;

import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.services.PostServices;
import com.springboot.blog.payload.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostServices {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        // convert PostDTO to Post
        Post post = postDTOToPost(postDTO);

        Post newPost = postRepository.save(post);

        //convert Post into PostDTo
        postDTO = postTOPostDTO(newPost);
        return postDTO;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // create a pageable object
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> allPosts = postRepository.findAll(pageable);

        // get content from page object to list

        List<Post> listOfPost = allPosts.getContent();

        List<PostDTO> content = listOfPost.stream().map(post -> postTOPostDTO(post)).collect(Collectors.toList());

        PostResponse response = new PostResponse();
        response.setContent(content);
        response.setPageNo(allPosts.getNumber());
        response.setPageSize(allPosts.getSize());
        response.setTotalElement(allPosts.getTotalElements());
        response.setTotalPages(allPosts.getTotalPages());
        response.setLast(allPosts.isLast());

        return response;
    }

    @Override
    public PostDTO findById(Long id) {
        Post post =  postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return postTOPostDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO updatedPost, long id) {
        Post existingPost =  postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        existingPost.setDescription(existingPost.getDescription());

        return postTOPostDTO(postRepository.save(existingPost));
    }

    @Override
    public void deletePost(long id) {
        Post existingPost =  postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(existingPost);
    }


    // convert PostDTO to Post
    private Post postDTOToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());
        post.setDataTime(postDTO.getDataTime());
        return post;
    }

    //convert Post into PostDTo
    private PostDTO postTOPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());
        postDTO.setDataTime(post.getDataTime());
        return postDTO;
    }


}
