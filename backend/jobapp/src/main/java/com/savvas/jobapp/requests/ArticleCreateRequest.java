package com.savvas.jobapp.requests;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class ArticleCreateRequest {
    private String title;
    private String content;
    private int user_id;
}
