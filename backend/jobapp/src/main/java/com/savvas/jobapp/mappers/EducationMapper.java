package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.Education;
import com.savvas.jobapp.model.Role;
import com.savvas.jobapp.model.Scope;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.ScopeRepository;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.EducationCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.EducationResponse;
import com.savvas.jobapp.responses.ScopeResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EducationMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ScopeRepository scopeRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ScopeMapper scopeMapper;

    public List<EducationResponse> fromEntity(List<Education> educations) {
        List<EducationResponse> data = new ArrayList<>();
        for (Education education : educations) {
            data.add(fromEntity(education));
        }
        return data;
    }

    public EducationResponse fromEntity(Education education) {
        EducationResponse educationResponse = new EducationResponse();
        educationResponse.setId(education.getId());
        educationResponse.setTitle(education.getTitle());

        ScopeResponse s = scopeMapper.fromEntity(education.getScope_id());
        educationResponse.setScope_id(s);
        educationResponse.setUser_id(education.getUser_id().getId());

        return educationResponse;
    }

    public Education toEntity(EducationCreateRequest request) {
        Education education = new Education();
        education.setTitle(request.getTitle());
        Optional<Scope> scope = scopeRepository.findById(request.getScope_id());
        education.setScope_id(scope.get());
        Optional<User> user = userRepository.findById(request.getUser_id());

        education.setUser_id(user.get());

        return education;
    }

}
