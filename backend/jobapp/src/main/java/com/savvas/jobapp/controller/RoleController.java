package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.AdMapper;
import com.savvas.jobapp.mappers.RoleMapper;
import com.savvas.jobapp.model.Ad;
import com.savvas.jobapp.model.Role;
import com.savvas.jobapp.model.Role;
import com.savvas.jobapp.repository.RoleRepository;
import com.savvas.jobapp.responses.AdResponse;
import com.savvas.jobapp.responses.RoleResponse;
import com.savvas.jobapp.responses.RoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleMapper roleMapper;

    @GetMapping("/all")
    public List<RoleResponse> all() {
        List<Role> all = roleRepository.findAll();
        List<RoleResponse> result = roleMapper.fromEntity(all);
        return result;
    }

    @GetMapping("/id/{value}")
    public RoleResponse findByID(@PathVariable(value = "value") Integer value) {
        Optional<Role> object = roleRepository.findById(value);

        if (object.isPresent()) {
            return roleMapper.fromEntity(object.get());
        } else {
            throw new ResourceNotFoundException("Ad", "id", value);
        }
    }

    @GetMapping("/description/{value}")
    public RoleResponse findByDescription(@PathVariable(value = "value") String value) {
        Optional<Role> Role = roleRepository.findByDescription(value);

        if (Role.isPresent()) {
            return roleMapper.fromEntity(Role.get());
        } else {
            throw new ResourceNotFoundException("Role", "email", value);
        }
    }
    
}
