package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private long id;
    @NotEmpty(message = "Name Should not be empty")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    private Date date;
    @NotEmpty
    @Size(min = 0,max = 240, message = "comment not be more than 240 words")
    private String commentBody;
}
