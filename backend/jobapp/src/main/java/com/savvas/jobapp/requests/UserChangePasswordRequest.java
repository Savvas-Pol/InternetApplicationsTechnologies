package com.savvas.jobapp.requests;

import lombok.Data;

@Data
public class UserChangePasswordRequest {
    private String email;
    private String oldpassword;
    private String newpassword;
}

