package com.savvas.jobapp.repository;

import com.savvas.jobapp.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

    public Optional<Education> findByTitle(String value);

}
