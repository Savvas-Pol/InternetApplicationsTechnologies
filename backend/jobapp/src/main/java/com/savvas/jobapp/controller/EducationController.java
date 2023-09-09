package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceFoundException;
import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.EducationMapper;
import com.savvas.jobapp.model.Education;
import com.savvas.jobapp.repository.EducationRepository;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.EducationCreateRequest;
import com.savvas.jobapp.responses.EducationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/education")
public class EducationController {
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    EducationMapper educationMapper;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<EducationResponse> all() {
        List<Education> all = educationRepository.findAll();
        List<EducationResponse> result = educationMapper.fromEntity(all);
        return result;
    }

    @PostMapping("/create")
    public EducationResponse create(@RequestBody EducationCreateRequest request) {
        Optional<Education> byTitle = educationRepository.findByTitle(request.getTitle());

        if (byTitle.isPresent()) {
            throw new ResourceFoundException("Education", "title", request.getTitle());
        }

        Education entity = educationMapper.toEntity(request);

        Education education = educationRepository.saveAndFlush(entity);

        return educationMapper.fromEntity(education);
    }
    @GetMapping("/id/{id}")
    public EducationResponse findByID(@PathVariable(value = "id") Integer id) {
        Optional<Education> education = educationRepository.findById(id);

        if (education.isPresent()) {
            return educationMapper.fromEntity(education.get());
        } else {
            throw new ResourceNotFoundException("Education", "id" , id);
        }
    }

//    @GetMapping("/title/{value}")
//    public EducationResponse findByTitle(@PathVariable(value = "value") String value) {
//        Optional<Education> education = educationRepository.findByTitle(value);
//
//        if (education.isPresent()) {
//            return educationMapper.fromEntity(education.get());
//        } else {
//            throw new ResourceNotFoundException("Education", "title" , value);
//        }
//    }
}
