package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceFoundException;
import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.ConversationMapper;
import com.savvas.jobapp.model.Conversation;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.ConversationRepository;
import com.savvas.jobapp.requests.ConversationCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.ConversationResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conversation")
public class ConversationController {
    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    ConversationMapper conversationMapper;

    @GetMapping("/all")
    public List<ConversationResponse> all() {
        List<Conversation> all = conversationRepository.findAll();
        List<ConversationResponse> result = conversationMapper.fromEntity(all);
        return result;
    }

    @PostMapping("/create")
    public ConversationResponse create(@RequestBody ConversationCreateRequest request) {
        Conversation entity = conversationMapper.toEntity(request);

        Conversation conversation = conversationRepository.saveAndFlush(entity);

        return conversationMapper.fromEntity(conversation);
    }

    @GetMapping("/id/{value}")
    public ConversationResponse findByID(@PathVariable(value = "value") Integer value) {
        Optional<Conversation> conversation = conversationRepository.findById(value);

        if (conversation.isPresent()) {
            return conversationMapper.fromEntity(conversation.get());
        } else {
            throw new ResourceNotFoundException("Conversation", "id", value);
        }
    }

    @GetMapping("/date/{value}")
    public ConversationResponse findByDate(@PathVariable(value = "value") Date value) {
        Optional<Conversation> conversation = conversationRepository.findByDate(value);

        if(conversation.isPresent()) {
            return conversationMapper.fromEntity(conversation.get());
        } else {
            throw new ResourceNotFoundException("Conversation", "date", value);
        }
    }
}
