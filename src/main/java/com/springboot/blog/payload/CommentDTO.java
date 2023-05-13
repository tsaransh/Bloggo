package com.springboot.blog.payload;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private long id;
    private String name;
    private String email;
    private Date date;
    private String commentBody;
}
