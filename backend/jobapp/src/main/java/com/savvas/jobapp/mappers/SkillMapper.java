package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.Role;
import com.savvas.jobapp.model.Scope;
import com.savvas.jobapp.model.Skill;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.ScopeRepository;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.SkillCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.RoleResponse;
import com.savvas.jobapp.responses.ScopeResponse;
import com.savvas.jobapp.responses.SkillResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SkillMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ScopeRepository scopeRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ScopeMapper scopeMapper;

    public List<SkillResponse> fromEntity(List<Skill> skills) {
        List<SkillResponse> data = new ArrayList<>();
        for (Skill skill: skills) {
            data.add(fromEntity(skill));
        }
        return data;
    }

    public SkillResponse fromEntity(Skill skill) {
        SkillResponse skillResponse = new SkillResponse();
        skillResponse.setId(skill.getId());
        skillResponse.setDescription(skill.getDescription());

        ScopeResponse s = scopeMapper.fromEntity(skill.getScope_id());
        skillResponse.setScope_id(s);
        skillResponse.setUser_id(skill.getUser_id().getId());

        return skillResponse;
    }

    public Skill toEntity(SkillCreateRequest request) {
        Skill skill = new Skill();
        skill.setDescription(request.getDescription());
        Optional<Scope> scope = scopeRepository.findById(request.getScope_id());
        skill.setScope_id(scope.get());
        Optional<User> user = userRepository.findById(request.getUser_id());
        skill.setUser_id(user.get());

        return skill;
    }
}
