package com.savvas.jobapp.responses;

import lombok.Data;

@Data
public class ConnectionRequestResponse {
    private Integer id;
    private UserResponse user1_id;
    private UserResponse user2_id;
}
