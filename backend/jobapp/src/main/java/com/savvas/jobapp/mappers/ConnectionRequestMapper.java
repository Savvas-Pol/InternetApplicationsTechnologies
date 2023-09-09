package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.ConnectionRequest;
import com.savvas.jobapp.model.Role;
import com.savvas.jobapp.model.Scope;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.ConnectionRequestRepository;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.ConnectionRequestCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.ConnectionRequestResponse;
import com.savvas.jobapp.responses.RoleResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ConnectionRequestMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public List<ConnectionRequestResponse> fromEntity(List<ConnectionRequest> connectionRequests) {
        List<ConnectionRequestResponse> data = new ArrayList<>();
        for (ConnectionRequest connectionRequest : connectionRequests) {
            data.add(fromEntity(connectionRequest));
        }
        return data;
    }

    public ConnectionRequestResponse fromEntity(ConnectionRequest connectionRequest) {
        ConnectionRequestResponse connectionRequestResponse = new ConnectionRequestResponse();
        connectionRequestResponse.setId(connectionRequest.getId());
        UserResponse u1 = userMapper.fromEntity(connectionRequest.getUser1_id());
        connectionRequestResponse.setUser1_id(u1);
        UserResponse u2 = userMapper.fromEntity(connectionRequest.getUser2_id());
        connectionRequestResponse.setUser2_id(u2);
        return connectionRequestResponse;
    }

    public ConnectionRequest toEntity(ConnectionRequestCreateRequest request) {
        ConnectionRequest connectionRequest = new ConnectionRequest();

        Optional<User> user1 = userRepository.findById(request.getUser1_id());
        connectionRequest.setUser1_id(user1.get());

        Optional<User> user2 = userRepository.findById(request.getUser2_id());
        connectionRequest.setUser2_id(user2.get());

        return connectionRequest;
    }
}

