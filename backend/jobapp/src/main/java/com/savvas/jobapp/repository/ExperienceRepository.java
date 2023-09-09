package com.savvas.jobapp.repository;

import com.savvas.jobapp.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

    public Optional<Experience> findByTitle(String value);
}
