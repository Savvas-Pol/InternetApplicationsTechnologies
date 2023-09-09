package com.savvas.jobapp.responses;

import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {
    private Integer id;
    private String content;
    private UserResponse user_id;
    private Integer article_id;
}
