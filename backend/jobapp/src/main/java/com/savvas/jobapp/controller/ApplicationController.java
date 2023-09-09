package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceFoundException;
import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.ApplicationMapper;
import com.savvas.jobapp.model.Application;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.ApplicationRepository;
import com.savvas.jobapp.requests.ApplicationCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.ApplicationResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {
    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ApplicationMapper applicationMapper;

    @GetMapping("/all")
    public List<ApplicationResponse> all() {
        List<Application> all = applicationRepository.findAll();
        List<ApplicationResponse> result = applicationMapper.fromEntity(all);
        return result;
    }

    @PostMapping("/create")
    public ApplicationResponse create(@RequestBody ApplicationCreateRequest request) {
        Application entity = applicationMapper.toEntity(request);

        Application application = applicationRepository.saveAndFlush(entity);

        return applicationMapper.fromEntity(application);
    }

    @GetMapping("/id/{value}")
    public ApplicationResponse findByID(@PathVariable(value = "value") Integer value) {
        Optional<Application> application = applicationRepository.findById(value);

        if (application.isPresent()) {
            return applicationMapper.fromEntity(application.get());
        } else {
            throw new ResourceNotFoundException("Application", "id", value);
        }
    }

}
