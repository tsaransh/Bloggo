package com.springboot.blog.payload;


import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PostResponse {

    private List<PostDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;

}
