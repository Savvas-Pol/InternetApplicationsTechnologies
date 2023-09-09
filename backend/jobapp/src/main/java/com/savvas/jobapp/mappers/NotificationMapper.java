package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.Notification;
import com.savvas.jobapp.model.Role;
import com.savvas.jobapp.model.Scope;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.NotificationCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.NotificationResponse;
import com.savvas.jobapp.responses.RoleResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class NotificationMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public List<NotificationResponse> fromEntity(List<Notification> notifications) {
        List<NotificationResponse> data = new ArrayList<>();
        for (Notification notification: notifications) {
            data.add(fromEntity(notification));
        }
        return data;
    }

    public NotificationResponse fromEntity(Notification notification) {
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setId(notification.getId());
        notificationResponse.setDate(notification.getDate());
        notificationResponse.setContent(notification.getContent());
        UserResponse u1 = userMapper.fromEntity(notification.getSender_id());
        notificationResponse.setSender_id(u1);
        UserResponse u2 = userMapper.fromEntity(notification.getReceiver_id());
        notificationResponse.setReceiver_id(u2);
        return notificationResponse;
    }

    public Notification toEntity(NotificationCreateRequest request) {
        Notification notification = new Notification();
        notification.setContent(request.getContent());
        notification.setDate(request.getDate());
        Optional<User> u1 = userRepository.findById(request.getSender_id());
        notification.setSender_id(u1.get());
        Optional<User> u2 = userRepository.findById(request.getReceiver_id());
        notification.setReceiver_id(u2.get());

        return notification;
    }
}
