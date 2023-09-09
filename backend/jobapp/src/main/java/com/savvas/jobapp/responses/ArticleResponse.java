package com.savvas.jobapp.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleResponse {
    private Integer id;
    private String title;
    private String content;
    private String authored_at;
    private String deleted_at;
    private String published_at;
    private String updated_at;
    private List<CommentResponse> comment = new ArrayList<>();
    private UserResponse user_id;
    private List<UserResponse> users_liked = new ArrayList<>();
}
