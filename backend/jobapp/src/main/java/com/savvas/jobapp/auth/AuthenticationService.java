package com.savvas.jobapp.auth;

import com.savvas.jobapp.dto.LoginRequest;
import com.savvas.jobapp.dto.LoginResponse;
import com.savvas.jobapp.dto.LogoutResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.http.ResponseEntity;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:54, Pzt
 **/
public interface AuthenticationService {
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken);
    ResponseEntity<LogoutResponse> logout();

    UserResponse getUserProfile();
}
