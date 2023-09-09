package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.Conversation;
import com.savvas.jobapp.model.Role;
import com.savvas.jobapp.model.Scope;
import com.savvas.jobapp.model.User;
import com.savvas.jobapp.repository.UserRepository;
import com.savvas.jobapp.requests.ConversationCreateRequest;
import com.savvas.jobapp.requests.UserCreateRequest;
import com.savvas.jobapp.responses.ConversationResponse;
import com.savvas.jobapp.responses.RoleResponse;
import com.savvas.jobapp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ConversationMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public List<ConversationResponse> fromEntity(List<Conversation> conversations) {
        List<ConversationResponse> data = new ArrayList<>();
        for (Conversation conversation : conversations) {
            data.add(fromEntity(conversation));
        }
        return data;
    }

    public ConversationResponse fromEntity(Conversation conversation) {
        ConversationResponse conversationResponse = new ConversationResponse();
        conversationResponse.setId(conversation.getId());
        conversationResponse.setDate(conversation.getDate());

        UserResponse u1 = userMapper.fromEntity(conversation.getUser1_id());
        UserResponse u2 = userMapper.fromEntity(conversation.getUser2_id());

        conversationResponse.setUser1_id(u1);
        conversationResponse.setUser2_id(u2);


        return conversationResponse;
    }

    public Conversation toEntity(ConversationCreateRequest request) {
        Conversation conversation = new Conversation();
        Optional<User> user1 = userRepository.findById(request.getUser1_id());
        conversation.setUser1_id(user1.get());
        Optional<User> user2 = userRepository.findById(request.getUser2_id());
        conversation.setUser2_id(user2.get());
        conversation.setDate(request.getDate());

        return conversation;
    }

}

