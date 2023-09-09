package com.savvas.jobapp.requests;

import lombok.Data;

import java.util.Date;

@Data
public class AdCreateRequest {
    private String title;
    private String details;

    private int user_id;
}

