package com.savvas.jobapp.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String photo;
    private String description;
    private List<ExperienceResponse> experience = new ArrayList<>();
    private List<EducationResponse> education = new ArrayList<>();
    private List<SkillResponse> skill = new ArrayList<>();
    private RoleResponse role;
    private ScopeResponse scope_id_email;
    private ScopeResponse scope_id_phone;
    private ScopeResponse scope_id_photo;
}
