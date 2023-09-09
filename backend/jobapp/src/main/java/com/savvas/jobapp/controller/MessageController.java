package com.savvas.jobapp.controller;

import com.savvas.jobapp.exceptions.ResourceNotFoundException;
import com.savvas.jobapp.mappers.MessageMapper;
import com.savvas.jobapp.model.Conversation;
import com.savvas.jobapp.model.Message;
import com.savvas.jobapp.repository.MessageRepository;
import com.savvas.jobapp.requests.ConversationCreateRequest;
import com.savvas.jobapp.requests.MessageCreateRequest;
import com.savvas.jobapp.responses.ConversationResponse;
import com.savvas.jobapp.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/message/")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageMapper messageMapper;

    @GetMapping("/all")
    public List<MessageResponse> all() {
        List<Message> all = messageRepository.findAll();
        List<MessageResponse> result = messageMapper.fromEntity(all);
        return result;
    }

    @PostMapping("/create")
    public MessageResponse create(@RequestBody MessageCreateRequest request) {
        Message entity = messageMapper.toEntity(request);

        Message message = messageRepository.saveAndFlush(entity);

        return messageMapper.fromEntity(message);
    }

    @GetMapping("/id/{value}")
    public MessageResponse findByID(@PathVariable(value = "value") Integer value) {
        Optional<Message> message = messageRepository.findById(value);

        if (message.isPresent()) {
            return messageMapper.fromEntity(message.get());
        } else {
            throw new ResourceNotFoundException("Message", "id", value);
        }
    }

    @GetMapping("/content/{value}")
    public MessageResponse findByContent(@PathVariable(value = "value") String value) {
        Optional<Message> message = messageRepository.findByContent(value);

        if (message.isPresent()) {
            return messageMapper.fromEntity(message.get());
        } else {
            throw new ResourceNotFoundException("Message", "content", value);
        }
    }
}
