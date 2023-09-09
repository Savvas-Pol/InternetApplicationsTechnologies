package com.savvas.jobapp.repository;

import com.savvas.jobapp.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    public Optional<Notification> findByDate(Date value);
    public Optional<Notification> findByContent(String value);
}
