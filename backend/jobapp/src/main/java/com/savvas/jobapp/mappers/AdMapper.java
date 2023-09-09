package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.*;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.AdCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class AdMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ApplicationMapper applicationMapper;

    public List<AdResponse> fromEntity(List<Ad> ads) {
        List<AdResponse> data = new ArrayList<>();
        for (Ad ad : ads) {
            data.add(fromEntity(ad));
        }
        return data;
    }

    public AdResponse fromEntity(Ad ad) {
        AdResponse adResponse = new AdResponse();
        adResponse.setId(ad.getId());
        adResponse.setAuthored_at(ad.getAuthored_at());
        adResponse.setTitle(ad.getTitle());
        adResponse.setDetails(ad.getDetails());
        adResponse.setDeleted_at(ad.getDeleted_at());
        adResponse.setPublished_at(ad.getPublished_at());
        adResponse.setUpdated_at(ad.getUpdated_at());

        for (Application app : ad.getApplication()) {
            ApplicationResponse resp = applicationMapper.fromEntity(app);
            adResponse.getApplication().add(resp);
        }

        UserResponse u = userMapper.fromEntity(ad.getUser_id());
        adResponse.setUser_id(u);

        for (User entity : ad.getViews()) {
            UserResponse r = userMapper.fromEntity(entity);
            adResponse.getUser_views().add(r);
        }

        return adResponse;
    }

    public Ad toEntity(AdCreateRequest request) {
        Ad ad = new Ad();
        ad.setTitle(request.getTitle());
        ad.setDetails(request.getDetails());

        DateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        Date now = new Date();
        ad.setAuthored_at(formatter.format(now));

        Optional<User> user = userRepository.findById(request.getUser_id());
        ad.setUser_id(user.get());

        return ad;
    }

}
