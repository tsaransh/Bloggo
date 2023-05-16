package com.springboot.blog.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.Set;


@Data
public class PostDTO {

    private Long id;
    @NotEmpty
    @Size(min=2,message = "Post title should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min=10 ,max = 240 ,message = "Post title should have at least 10 characters and not more that 240 characters")
    private String description;

    @NotEmpty
    private String content;
    private Date dataTime;
    private Set<CommentDTO> comments;

}
