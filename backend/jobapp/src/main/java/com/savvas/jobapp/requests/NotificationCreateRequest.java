package com.savvas.jobapp.requests;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationCreateRequest {
    private String date;
    private String content;

    private int sender_id;
    private int receiver_id;
}

