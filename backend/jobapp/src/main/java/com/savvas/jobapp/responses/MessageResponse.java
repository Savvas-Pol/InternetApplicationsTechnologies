package com.savvas.jobapp.responses;

import lombok.Data;

@Data
public class MessageResponse {
    private Integer id;
    private String content;
    private ConversationResponse conversation_id;
}
