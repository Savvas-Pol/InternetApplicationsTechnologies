package com.savvas.jobapp.requests;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CommentCreateRequest {
    @Column(columnDefinition="LONGTEXT")
    private String content;
    private int user_id;
    private int article_id;
}

