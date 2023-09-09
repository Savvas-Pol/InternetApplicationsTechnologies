package com.savvas.jobapp.responses;

import lombok.Data;

@Data
public class SkillResponse {
    private Integer id;
    private String description;
    private Integer user_id;
    private ScopeResponse scope_id;
}
