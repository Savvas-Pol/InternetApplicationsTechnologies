package com.savvas.jobapp.requests;

import lombok.Data;

import java.util.Date;

@Data
public class ConversationCreateRequest {
    private Integer id;
    private String date;

    private int user1_id;
    private int user2_id;
}

