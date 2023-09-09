package com.savvas.jobapp.requests;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String phone;
    private String photo;
    private String description;

    private int role_id;
    private int scope_id_phone;
    private int scope_id_photo;
    private int scope_id_email;
}

