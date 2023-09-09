package com.savvas.jobapp.mappers;

import com.savvas.jobapp.model.*;
import com.savvas.jobapp.repository.ConversationRepository;
import com.savvas.jobapp.requests.MessageCreateRequest;
import com.savvas.jobapp.responses.ConversationResponse;
import com.savvas.jobapp.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MessageMapper {
    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    ConversationMapper conversationMapper;

    public List<MessageResponse> fromEntity(List<Message> messages) {
        List<MessageResponse> data = new ArrayList<>();
        for (Message message : messages) {
            data.add(fromEntity(message));
        }
        return data;
    }

    public  MessageResponse fromEntity(Message message) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setId(message.getId());
        messageResponse.setContent(message.getContent());
        ConversationResponse c = conversationMapper.fromEntity(message.getConversation_id());
        messageResponse.setConversation_id(c);
        return messageResponse;
    }

    public Message toEntity(MessageCreateRequest request) {
        Message message = new Message();
        message.setContent(request.getContent());
        Optional<Conversation> conversation = conversationRepository.findById(request.getConversation_id());
        message.setConversation_id(conversation.get());

        return message;
    }
}
