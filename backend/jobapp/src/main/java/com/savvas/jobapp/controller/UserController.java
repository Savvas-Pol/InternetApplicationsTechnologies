package com.savvas.jobapp.controller;

import com.savvas.jobapp.auth.AuthenticationService;
import com.savvas.jobapp.auth.AuthenticationServiceImpl;
import com.savvas.jobapp.exceptions.GeneralBadRequest;
import com.savvas.jobapp.exceptions.ResourceFoundException;
import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.UserMapper;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.UserChangeEmailRequest;
import com.savvas.jobapp.requests.UserChangePasswordRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;


    @GetMapping("/all")
    public List<UserResponse> all() {
        List<User> all = userRepository.findAll();
        List<UserResponse> result = userMapper.fromEntity(all);
        return result;
    }

    @PostMapping("/create")
    public UserResponse create(@RequestBody UserCreateRequest request) {

        UserResponse userProfile = authenticationService.getUserProfile();


        Optional<User> byEmail = userRepository.findByEmail(request.getEmail());

        if (byEmail.isPresent()) {
            throw new ResourceFoundException("User", "email", request.getEmail());
        }

        User entity = userMapper.toEntity(request);

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

    @PostMapping("/password")
    public UserResponse changePassword(@RequestBody UserChangePasswordRequest request) {
        Optional<User> byEmail = userRepository.findByEmail(request.getEmail());

        if (!byEmail.isPresent()) {
            throw new ResourceNotFoundException("User", "email", request.getEmail());
        }

        User entity = byEmail.get();

        String plainPassword = request.getOldpassword();

        if (!passwordEncoder.matches(plainPassword, entity.getPassword())) {
            throw new GeneralBadRequest("User", "Passwords do not match:" + plainPassword + " vs " + entity.getPassword());
        }

        String plainNewPassword = request.getNewpassword();
        String encodedNewPassword = passwordEncoder.encode(plainNewPassword);

        entity.setPassword(encodedNewPassword);

        User user = userRepository.saveAndFlush(entity);

        return userMapper.fromEntity(user);
    }

    @PostMapping("/email")
    public UserResponse changeEmail(@RequestBody UserChangeEmailRequest request) {
        Optional<User> byEmail = userRepository.findByEmail(request.getEmail());

        if (!byEmail.isPresent()) {
            throw new ResourceNotFoundException("User", "email", request.getEmail());
        }

        User entity = byEmail.get();

        if (!entity.getEmail().equals(request.getEmail())) {
            throw new GeneralBadRequest("User", "Emails do not match");
        }

        entity.setEmail(request.getNewemail());

        User user = userRepository.saveAndFlush(entity);

        return userMapper.fromEntity(user);
    }


    @GetMapping("/id/{id}")
    public UserResponse findByID(@PathVariable(value = "id") Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return userMapper.fromEntity(user.get());
        } else {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }

    @GetMapping("/network/{id}")
    public List<UserResponse>  findFriendsByID(@PathVariable(value = "id") Integer id) {
        Optional<User> user = userRepository.findById(id);


        if (user.isPresent()) {
            return userMapper.fromEntity(user.get().getConnectedUsers());
        } else {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }

    @GetMapping("/email/{value}")
    public UserResponse findByEmail(@PathVariable(value = "value") String value) {
        Optional<User> user = userRepository.findByEmail(value);

        if (user.isPresent()) {
            return userMapper.fromEntity(user.get());
        } else {
            throw new ResourceNotFoundException("User", "email", value);
        }
    }

    @GetMapping("/firstname/{value}")
    public UserResponse findByFirstname(@PathVariable(value = "value") String value) {
        Optional<User> user = userRepository.findByFirstname(value);

        if (user.isPresent()) {
            return userMapper.fromEntity(user.get());
        } else {
            throw new ResourceNotFoundException("User", "firstname", value);
        }
    }

    @GetMapping("/lastname/{value}")
    public UserResponse findByLastname(@PathVariable(value = "value") String value) {
        Optional<User> user = userRepository.findByLastname(value);

        if (user.isPresent()) {
            return userMapper.fromEntity(user.get());
        } else {
            throw new ResourceNotFoundException("User", "lastname", value);
        }
    }

    @GetMapping("/phone/{value}")
    public UserResponse findByPhone(@PathVariable(value = "value") String value) {
        Optional<User> user = userRepository.findByPhone(value);

        if (user.isPresent()) {
            return userMapper.fromEntity(user.get());
        } else {
            throw new ResourceNotFoundException("User", "phone", value);
        }
    }

    @GetMapping("/photo/{value}")
    public UserResponse findByPhoto(@PathVariable(value = "value") String value) {
        Optional<User> user = userRepository.findByPhoto(value);

        if (user.isPresent()) {
            return userMapper.fromEntity(user.get());
        } else {
            throw new ResourceNotFoundException("User", "photo", value);
        }
    }
}
