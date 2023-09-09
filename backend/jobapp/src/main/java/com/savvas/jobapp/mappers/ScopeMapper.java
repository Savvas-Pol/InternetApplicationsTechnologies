package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.Scope;
import com.savvas.jobapp.responses.ScopeResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScopeMapper {
    public List<ScopeResponse> fromEntity(List<Scope> input) {
        List<ScopeResponse> data = new ArrayList<>();
        for (Scope o : input) {
            data.add(fromEntity(o));
        }
        return data;
    }

    public ScopeResponse fromEntity(Scope obj) {
        ScopeResponse ScopeResponse = new ScopeResponse();
        ScopeResponse.setId(obj.getId());
        ScopeResponse.setDescription(obj.getDescription());
        return ScopeResponse;
    }
}
