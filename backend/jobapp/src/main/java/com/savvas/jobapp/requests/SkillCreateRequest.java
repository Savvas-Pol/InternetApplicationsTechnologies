package com.savvas.jobapp.requests;

import lombok.Data;

@Data
public class SkillCreateRequest {
    private String description;
    private int user_id;
    private int scope_id;
}

