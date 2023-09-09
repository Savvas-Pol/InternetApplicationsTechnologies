package com.savvas.jobapp.repository;

import com.savvas.jobapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByEmail(String value);
    public Optional<User> findByFirstname(String value);
    public Optional<User> findByLastname(String value);
    public Optional<User> findByPhone(String value);
    public Optional<User> findByPhoto(String value);
}
