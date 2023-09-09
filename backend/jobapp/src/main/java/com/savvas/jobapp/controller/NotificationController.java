package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceFoundException;
import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.NotificationMapper;
import com.savvas.jobapp.model.Notification;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.NotificationRepository;
import com.savvas.jobapp.requests.NotificationCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.NotificationResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationMapper notificationMapper;

    @GetMapping("/all")
    public List<NotificationResponse> all() {
        List<Notification> all = notificationRepository.findAll();
        List<NotificationResponse> result = notificationMapper.fromEntity(all);
        return result;
    }

    @PostMapping("/create")
    public NotificationResponse create(@RequestBody NotificationCreateRequest request) {
        Notification entity = notificationMapper.toEntity(request);

        Notification notification = notificationRepository.saveAndFlush(entity);

        return notificationMapper.fromEntity(notification);
    }

    @GetMapping("/id/{value}")
    public NotificationResponse findByID(@PathVariable(value = "value") Integer value) {
        Optional<Notification> notification = notificationRepository.findById(value);

        if (notification.isPresent()) {
            return notificationMapper.fromEntity(notification.get());
        } else {
            throw new ResourceNotFoundException("Notification", "id", value);
        }
    }

    @GetMapping("/date/{value}")
    public NotificationResponse findByDate(@PathVariable(value = "value") Date value) {
        Optional<Notification> notification = notificationRepository.findByDate(value);

        if(notification.isPresent()) {
            return notificationMapper.fromEntity(notification.get());
        } else {
            throw new ResourceNotFoundException("Notification", "date" , value);
        }
    }

    @GetMapping("/content/{value}")
    public NotificationResponse findByContent(@PathVariable(value = "value") String value) {
        Optional<Notification> notification = notificationRepository.findByContent(value);

        if(notification.isPresent()) {
            return notificationMapper.fromEntity(notification.get());
        } else {
            throw new ResourceNotFoundException("Notification", "content" , value);
        }
    }
}
