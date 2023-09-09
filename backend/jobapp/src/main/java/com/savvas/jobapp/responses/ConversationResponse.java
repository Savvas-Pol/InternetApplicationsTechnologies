package com.savvas.jobapp.responses;

import lombok.Data;

import java.util.Date;

@Data
public class ConversationResponse {
    private Integer id;
    private String date;
    private UserResponse user1_id;
    private UserResponse user2_id;
}
