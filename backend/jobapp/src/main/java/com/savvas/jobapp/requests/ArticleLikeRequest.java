package com.savvas.jobapp.requests;

import lombok.Data;

@Data
public class ArticleLikeRequest {
    private int article_id;
    private int user_id;
}
