package com.springboot.blog.payload;

import lombok.Data;

import java.util.Date;
import java.util.Set;


@Data
public class PostDTO {

    private Long id;
    private String title;
    private String description;
    private String content;
    private Date dataTime;
    private Set<CommentDTO> comments;

}
