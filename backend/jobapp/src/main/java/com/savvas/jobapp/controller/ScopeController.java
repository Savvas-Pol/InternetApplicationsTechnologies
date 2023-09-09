package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.ScopeMapper;
import com.savvas.jobapp.model.Role;
import com.savvas.jobapp.model.Scope;
import com.savvas.jobapp.repository.ScopeRepository;
import com.savvas.jobapp.responses.RoleResponse;
import com.savvas.jobapp.responses.ScopeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scope")
public class ScopeController {
    @Autowired
    ScopeRepository scopeRepository;

    @Autowired
    ScopeMapper scopeMapper;

    @GetMapping("/all")
    public List<ScopeResponse> all() {
        List<Scope> all = scopeRepository.findAll();
        List<ScopeResponse> result = scopeMapper.fromEntity(all);
        return result;
    }

    @GetMapping("/id/{value}")
    public ScopeResponse findByID(@PathVariable(value = "value") Integer value) {
        Optional<Scope> object = scopeRepository.findById(value);

        if (object.isPresent()) {
            return scopeMapper.fromEntity(object.get());
        } else {
            throw new ResourceNotFoundException("Ad", "id", value);
        }
    }

    @GetMapping("/description/{value}")
    public ScopeResponse findByDescription(@PathVariable(value = "value") String value) {
        Optional<Scope> scope = scopeRepository.findByDescription(value);

        if (scope.isPresent()) {
            return scopeMapper.fromEntity(scope.get());
        } else {
            throw new ResourceNotFoundException("Role", "email", value);
        }
    }
}
