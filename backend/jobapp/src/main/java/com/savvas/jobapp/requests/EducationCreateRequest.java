package com.savvas.jobapp.requests;

import lombok.Data;

@Data
public class EducationCreateRequest {
    private Integer id;
    private String title;
    private int user_id;
    private int scope_id;
}

