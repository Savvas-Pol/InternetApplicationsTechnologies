package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceFoundException;
import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.SkillMapper;
import com.savvas.jobapp.model.Skill;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.SkillRepository;
import com.savvas.jobapp.requests.SkillCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.SkillResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skill")
public class SkillController {
    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SkillMapper skillMapper;

    @GetMapping("/all")
    public List<SkillResponse> all() {
        List<Skill> all = skillRepository.findAll();
        List<SkillResponse> result = skillMapper.fromEntity(all);
        return result;
    }

    @PostMapping("/create")
    public SkillResponse create(@RequestBody SkillCreateRequest request) {
        Optional<Skill> byDescription = skillRepository.findByDescription(request.getDescription());

        if (byDescription.isPresent()) {
            throw new ResourceFoundException("Skill", "description", request.getDescription());
        }

        Skill entity = skillMapper.toEntity(request);

        Skill skill = skillRepository.saveAndFlush(entity);

        return skillMapper.fromEntity(skill);
    }

    @GetMapping("/id/{value}")
    public SkillResponse findByID(@PathVariable(value = "value") Integer value) {
        Optional<Skill> skill = skillRepository.findById(value);

        if (skill.isPresent()) {
            return skillMapper.fromEntity(skill.get());
        } else {
            throw new ResourceNotFoundException("Skill", "id", value);
        }
    }

    @GetMapping("/description/{value}")
    public SkillResponse findByDescription(@PathVariable(value = "value") String value) {
        Optional<Skill> skill = skillRepository.findByDescription(value);

        if(skill.isPresent()) {
            return skillMapper.fromEntity(skill.get());
        } else {
            throw new ResourceNotFoundException("Skill", "description", value);
        }
    }
}
