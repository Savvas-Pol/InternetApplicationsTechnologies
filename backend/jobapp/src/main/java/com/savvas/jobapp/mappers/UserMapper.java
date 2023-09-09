package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.*;
import com.savvas.jobapp.repository.EducationRepository;
import com.savvas.jobapp.repository.RoleRepository;
import com.savvas.jobapp.repository.ScopeRepository;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserMapper {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ScopeRepository scopeRepository;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    ScopeMapper scopeMapper;

    @Autowired
    SkillMapper skillMapper;

    @Autowired
    EducationMapper educationMapper;

    @Autowired
    ExperienceMapper experienceMapper;

    @Autowired
    ArticleMapper articleMapper;


    public List<UserResponse> fromEntity(List<User> users) {
        List<UserResponse> data = new ArrayList<UserResponse>();
        for (User u : users) {
            data.add(fromEntity(u));
        }
        return data;
    }

    public UserResponse fromEntity(User u) {
        UserResponse r = new UserResponse();
        r.setId(u.getId());
        r.setEmail(u.getEmail());
        r.setFirstname(u.getFirstname());
        r.setLastname(u.getLastname());
        r.setPhone(u.getPhone());
        r.setPhoto(u.getPhoto());
        r.setDescription(u.getDescription());

        if (u.getExperience() != null) {
            for (Experience exp : u.getExperience()) {
                ExperienceResponse er = experienceMapper.fromEntity(exp);
                r.getExperience().add(er);
            }
        }

        if (u.getEducation() != null){
            for (Education edu : u.getEducation()) {
                EducationResponse e = educationMapper.fromEntity(edu);
                r.getEducation().add(e);
            }
        }

        if (u.getSkill() != null) {
            for (Skill skill : u.getSkill()) {
                SkillResponse s = skillMapper.fromEntity(skill);
                r.getSkill().add(s);
            }
        }

        RoleResponse r1 = roleMapper.fromEntity(u.getRole());
        r.setRole(r1);

        ScopeResponse s1 = scopeMapper.fromEntity(u.getScope_id_email());
        ScopeResponse s2 = scopeMapper.fromEntity(u.getScope_id_phone());
        ScopeResponse s3 = scopeMapper.fromEntity(u.getScope_id_photo());

        r.setScope_id_email(s1);
        r.setScope_id_phone(s2);
        r.setScope_id_photo(s3);

        return r;
    }

    public User toEntity(UserCreateRequest request) {
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setPhoto(request.getPhoto());
        user.setDescription(request.getDescription());

        Optional<Role> role = roleRepository.findById(request.getRole_id());
        user.setRole(role.get());

        Optional<Scope> scope1 = scopeRepository.findById(request.getScope_id_email());
        user.setScope_id_email(scope1.get());

        Optional<Scope> scope2 = scopeRepository.findById(request.getScope_id_phone());
        user.setScope_id_phone(scope2.get());

        Optional<Scope> scope3 = scopeRepository.findById(request.getScope_id_photo());
        user.setScope_id_photo(scope3.get());

        return user;
    }
}
