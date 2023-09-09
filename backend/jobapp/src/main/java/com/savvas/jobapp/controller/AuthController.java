package com.savvas.jobapp.controller;

import com.savvas.jobapp.dto.LoginRequest;
import com.savvas.jobapp.dto.LoginResponse;
import com.savvas.jobapp.dto.LogoutResponse;
import com.savvas.jobapp.exceptions.ResourceFoundException;
import com.savvas.jobapp.mappers.UserMapper;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.UserResponse;
import com.savvas.jobapp.auth.AuthenticationService;
import com.savvas.jobapp.util.SecurityCipher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:52, Pzt
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@CookieValue(name = "accessToken", required = false) String accessToken, @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
        return authenticationService.login(loginRequest, decryptedAccessToken);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me() {
        return ResponseEntity.ok(authenticationService.getUserProfile());
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse create(@RequestBody UserCreateRequest request) {
        Optional<User> byEmail = userRepository.findByEmail(request.getEmail());

        if (byEmail.isPresent()) {
            throw new ResourceFoundException("User", "email", request.getEmail());
        }

        User entity = userMapper.toEntity(request);

        String plainPassword = entity.getPassword();
        String encodedPassword = passwordEncoder.encode(plainPassword);

        entity.setPassword(encodedPassword);

//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//
//        Set<ConstraintViolation<User>> violations = validator.validate(entity);
//        if (!violations.isEmpty()) {
//            throw new ConstraintViolationException(violations);
//        }

        User user = userRepository.saveAndFlush(entity);

        return userMapper.fromEntity(user);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<LogoutResponse> logout() {
        return authenticationService.logout();
    }
}
