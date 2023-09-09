package com.savvas.jobapp.responses;

import lombok.Data;

import java.util.Date;

@Data
public class ExperienceResponse {
    private Integer id;
    private String title;
    private Integer user_id;
    private String date_from;
    private String date_to;
    private ScopeResponse scope_id;
}
