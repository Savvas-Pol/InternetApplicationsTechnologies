package com.savvas.jobapp.dto;

import lombok.Data;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:50, Pzt
 **/
@Data
public class LoginRequest {
    private String email;
    private String password;
}
