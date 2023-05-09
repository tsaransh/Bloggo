package com.springboot.blog.services;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;

public interface PostServices {

    public PostDTO createPost(PostDTO postDTO);

    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    public PostDTO findById(Long id);

    PostDTO updatePost(PostDTO updatedPost, long id);

    void deletePost(long id);
}
