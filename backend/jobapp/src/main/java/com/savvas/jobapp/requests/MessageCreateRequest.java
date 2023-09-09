package com.savvas.jobapp.requests;

import lombok.Data;

@Data
public class MessageCreateRequest {
    private String content;
    private int conversation_id;
}

