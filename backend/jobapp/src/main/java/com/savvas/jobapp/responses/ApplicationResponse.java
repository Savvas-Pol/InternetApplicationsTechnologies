package com.savvas.jobapp.responses;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicationResponse {
    private Integer id;
    private String application_date;
    private UserResponse user_id;
    private Integer ad_id;
}
