package com.savvas.jobapp.requests;

import lombok.Data;

import java.util.Date;

@Data
public class ExperienceCreateRequest {
    private String title;
    private int user_id;
    private String date_from;
    private String date_to;
    private int scope_id;
}

