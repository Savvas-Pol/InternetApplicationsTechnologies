package com.savvas.jobapp.repository;

import com.savvas.jobapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    public Optional<Message> findByContent(String value);

}
