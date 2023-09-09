package com.savvas.jobapp.responses;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationResponse {
    private Integer id;
    private String date;
    private String content;
    private UserResponse sender_id;
    private UserResponse receiver_id;
}
