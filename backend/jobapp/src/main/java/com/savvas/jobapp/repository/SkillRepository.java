package com.savvas.jobapp.repository;

import com.savvas.jobapp.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    public Optional<Skill> findByDescription(String value);
}
