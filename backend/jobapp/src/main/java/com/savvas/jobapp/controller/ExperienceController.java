package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceFoundException;
import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.ExperienceMapper;
import com.savvas.jobapp.model.Education;
import com.savvas.jobapp.model.Experience;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.ExperienceRepository;
import com.savvas.jobapp.requests.ExperienceCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.ExperienceResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/experience")
public class ExperienceController {
    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    ExperienceMapper experienceMapper;

    @GetMapping("/all")
    public List<ExperienceResponse> all() {
        List<Experience> all = experienceRepository.findAll();
        List<ExperienceResponse> result = experienceMapper.fromEntity(all);
        return  result;
    }

    @PostMapping("/create")
    public ExperienceResponse create(@RequestBody ExperienceCreateRequest request) {
        Optional<Experience> byTitle = experienceRepository.findByTitle(request.getTitle());

        if (byTitle.isPresent()) {
            throw new ResourceFoundException("Experience", "title", request.getTitle());
        }
        Experience entity = experienceMapper.toEntity(request);

        Experience experience = experienceRepository.saveAndFlush(entity);

        return experienceMapper.fromEntity(experience);
    }

    @GetMapping("/id/{id}")
    public ExperienceResponse findByID(@PathVariable(value = "id") Integer id) {
        Optional<Experience> experience = experienceRepository.findById(id);

        if(experience.isPresent()) {
            return experienceMapper.fromEntity(experience.get());
        } else {
            throw new ResourceNotFoundException("Experience", "id", id);
        }
    }

    @GetMapping("/title/{value}")
    public ExperienceResponse findByTitle(@PathVariable(value = "value") String value) {
        Optional<Experience> experience = experienceRepository.findByTitle(value);

        if(experience.isPresent()) {
            return experienceMapper.fromEntity(experience.get());
        } else {
            throw new ResourceNotFoundException("Experience", "title", value);
        }
    }

}
