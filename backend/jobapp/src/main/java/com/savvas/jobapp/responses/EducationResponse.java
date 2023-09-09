package com.savvas.jobapp.responses;

import lombok.Data;

@Data
public class EducationResponse {
    private Integer id;
    private String title;
    private Integer user_id;
    private ScopeResponse scope_id;
}
