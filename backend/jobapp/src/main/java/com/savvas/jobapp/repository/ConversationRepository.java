package com.savvas.jobapp.repository;

import com.savvas.jobapp.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    public Optional<Conversation> findByDate(Date value);
}
