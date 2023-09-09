package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.Experience;
import com.savvas.jobapp.model.Role;
import com.savvas.jobapp.model.Scope;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.ScopeRepository;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.ExperienceCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.ExperienceResponse;
import com.savvas.jobapp.responses.RoleResponse;
import com.savvas.jobapp.responses.ScopeResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ExperienceMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ScopeRepository scopeRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ScopeMapper scopeMapper;

    public List<ExperienceResponse> fromEntity(List<Experience> experiences) {
        List<ExperienceResponse> data = new ArrayList<>();
        for(Experience e : experiences) {
            data.add(fromEntity(e));
        }
        return data;
    }

    public ExperienceResponse fromEntity(Experience experience) {
        ExperienceResponse e = new ExperienceResponse();
        e.setId(experience.getId());
        e.setTitle(experience.getTitle());
        e.setDate_from(experience.getDate_from());
        e.setDate_to(experience.getDate_to());

        e.setUser_id(experience.getUser_id().getId());

        ScopeResponse s = scopeMapper.fromEntity(experience.getScope_id());
        e.setScope_id(s);

        return e;

    }

    public Experience toEntity(ExperienceCreateRequest request) {
        Experience experience = new Experience();
        experience.setTitle(request.getTitle());
        experience.setDate_from(request.getDate_from());
        experience.setDate_to(request.getDate_to());

        Optional<User> user = userRepository.findById(request.getUser_id());
        experience.setUser_id(user.get());

        Optional<Scope> scope = scopeRepository.findById(request.getScope_id());
        experience.setScope_id(scope.get());

        return experience;
    }
}
