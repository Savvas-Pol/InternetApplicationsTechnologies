package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.*;
import com.savvas.jobapp.repository.AdRepository;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.ApplicationCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.AdResponse;
import com.savvas.jobapp.responses.ApplicationResponse;
import com.savvas.jobapp.responses.RoleResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ApplicationMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AdRepository adRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdMapper adMapper;

    public List<ApplicationResponse> fromEntity(List<Application> applications) {
        List<ApplicationResponse> data = new ArrayList<>();
        for (Application application: applications) {
            data.add(fromEntity(application));
        }
        return data;
    }

    public ApplicationResponse fromEntity(Application application) {
        ApplicationResponse applicationResponse = new ApplicationResponse();
        applicationResponse.setId(application.getId());
        applicationResponse.setApplication_date(application.getApplication_date());
        UserResponse u = userMapper.fromEntity(application.getUser_id());
        applicationResponse.setUser_id(u);

        applicationResponse.setAd_id(application.getAd_id().getId());

        return applicationResponse;
    }

    public Application toEntity(ApplicationCreateRequest request) {
        Application application = new Application();

        DateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        Date now = new Date();
        application.setApplication_date(formatter.format(now));

        Optional<User> user = userRepository.findById(request.getUser_id());
        application.setUser_id(user.get());
        Optional<Ad> ad = adRepository.findById(request.getAd_id());
        application.setAd_id(ad.get());

        return application;
    }
}
