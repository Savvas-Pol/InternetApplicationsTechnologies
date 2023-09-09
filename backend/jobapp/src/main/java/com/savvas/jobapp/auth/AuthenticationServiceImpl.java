package com.savvas.jobapp.auth;

import com.savvas.jobapp.dto.LoginRequest;
import com.savvas.jobapp.dto.LoginResponse;
import com.savvas.jobapp.dto.LogoutResponse;
import com.savvas.jobapp.dto.Token;
import com.savvas.jobapp.mappers.UserMapper;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.responses.UserResponse;
import com.savvas.jobapp.util.CookieUtil;
import com.savvas.jobapp.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:55, Pzt
 **/
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationService authenticationService;


    private void addAccessTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

    private void removeAccessTokenCoookie(HttpHeaders httpHeaders) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.destroyAccessTokenCookie().toString());
    }



    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken) {
        String email = loginRequest.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found with email " + email));

        HttpHeaders responseHeaders = new HttpHeaders();
        Token newAccessToken = tokenProvider.generateAccessToken(user.getEmail());
        addAccessTokenCookie(responseHeaders, newAccessToken);

        LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS, "Auth successful. Tokens are created in cookie.");
        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
    }

    @Override
    public ResponseEntity<LogoutResponse> logout() {
        LogoutResponse response = new LogoutResponse(LogoutResponse.SuccessFailure.SUCCESS, "Auth successful. Tokens are created in cookie.");

        HttpHeaders responseHeaders = new HttpHeaders();

        removeAccessTokenCoookie(responseHeaders);

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    @Override
    public UserResponse getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            User user = userRepository.findByEmail(customUserDetails.getUsername()).orElseThrow(() -> new IllegalArgumentException("User not found with email " + customUserDetails.getUsername()));
            return userMapper.fromEntity(user);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Me is not available, please login again");
        }
    }


}
