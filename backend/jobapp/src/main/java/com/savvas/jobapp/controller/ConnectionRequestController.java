package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceFoundException;
import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.ConnectionRequestMapper;
import com.savvas.jobapp.model.ConnectionRequest;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.ConnectionRequestRepository;
import com.savvas.jobapp.requests.ConnectionRequestCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.ConnectionRequestResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/connection_request")
public class ConnectionRequestController {
    @Autowired
    ConnectionRequestRepository connectionRequestRepository;

    @Autowired
    ConnectionRequestMapper connectionRequestMapper;

    @GetMapping("/all")
    public List<ConnectionRequestResponse> all() {
        List<ConnectionRequest> all = connectionRequestRepository.findAll();
        List<ConnectionRequestResponse> result = connectionRequestMapper.fromEntity(all);
        return result;
    }

    @PostMapping("/create")
    public ConnectionRequestResponse create(@RequestBody ConnectionRequestCreateRequest request) {
        ConnectionRequest entity = connectionRequestMapper.toEntity(request);
        ConnectionRequest connectionRequest = connectionRequestRepository.saveAndFlush(entity);

        return connectionRequestMapper.fromEntity(connectionRequest);
    }

    @GetMapping("/id/{value}")
    public ConnectionRequestResponse findByID(@PathVariable(value = "value") Integer value) {
        Optional<ConnectionRequest> connectionRequest = connectionRequestRepository.findById(value);

        if (connectionRequest.isPresent()) {
            return connectionRequestMapper.fromEntity(connectionRequest.get());
        } else {
            throw new ResourceNotFoundException("ConnectionRequest", "id", value);
        }
    }

}
