package com.savvas.jobapp.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AdResponse {
    private Integer id;
    private String title;
    private String details;
    private String authored_at;
    private String deleted_at;
    private String published_at;
    private String updated_at;
    private UserResponse user_id;
    private List<ApplicationResponse> application = new ArrayList<>();
    private List<UserResponse> user_views = new ArrayList<>();
}
