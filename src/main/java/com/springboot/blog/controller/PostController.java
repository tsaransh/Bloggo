package com.springboot.blog.controller;

import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.services.PostServices;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.utils.ApplicationConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostServices postServices;

    @Autowired
    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }


    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postServices.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPostS(
            @RequestParam(value = "pageNo", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ApplicationConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ApplicationConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ) {
        return postServices.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public PostDTO findById(@PathVariable Long id) {
        return ResponseEntity.ok(postServices.findById(id)).getBody();
    }

    @PutMapping("/{id}")
    public PostDTO updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable(name="id") long id) {
        return postServices.updatePost(postDTO,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id) {
        postServices.deletePost(id);
        return new ResponseEntity<>("Post entity deleted successfully.",HttpStatus.OK);
    }

}
