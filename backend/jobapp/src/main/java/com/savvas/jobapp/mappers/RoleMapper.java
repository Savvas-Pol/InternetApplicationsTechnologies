package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.Role;
import com.savvas.jobapp.repository.RoleRepository;
import com.savvas.jobapp.responses.RoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapper {
    @Autowired
    RoleRepository roleRepository;

    public List<RoleResponse> fromEntity(List<Role> input) {
        List<RoleResponse> data = new ArrayList<>();
        for (Role o : input) {
            data.add(fromEntity(o));
        }
        return data;
    }

    public RoleResponse fromEntity(Role obj) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(obj.getId());
        roleResponse.setDescription(obj.getDescription());
        return roleResponse;
    }
}
