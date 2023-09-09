package com.savvas.jobapp.requests;

import lombok.Data;

@Data
public class UserChangeEmailRequest {
    private String email;
    private String newemail;
}

