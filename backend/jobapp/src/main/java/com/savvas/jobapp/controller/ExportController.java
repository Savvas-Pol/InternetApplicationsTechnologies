package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.*;
import com.savvas.jobapp.model.Ad;
import com.savvas.jobapp.repository.*;
import com.savvas.jobapp.requests.AdCreateRequest;
import com.savvas.jobapp.responses.AdResponse;
import com.savvas.jobapp.responses.ExportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/export")
public class ExportController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    AdRepository adRepository;

    @Autowired
    AdMapper adMapper;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    ExperienceMapper experienceMapper;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentMapper commentMapper;

    private ExportResponse retrieveData() {
        ExportResponse res = new ExportResponse();

        res.setUsers(userMapper.fromEntity(userRepository.findAll()));
        res.setArticles(articleMapper.fromEntity(articleRepository.findAll()));
        res.setAds(adMapper.fromEntity(adRepository.findAll()));
        res.setExperience(experienceMapper.fromEntity(experienceRepository.findAll()));
        res.setComments(commentMapper.fromEntity(commentRepository.findAll()));

        return res;
    }

    @GetMapping(value="/xml", produces= MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('administrator')")
    public ExportResponse xml() {
        return retrieveData();
    }

    @GetMapping(value="/json", produces=MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('administrator')")
    public ExportResponse json() {
        return retrieveData();
    }
}
