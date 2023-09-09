package com.savvas.jobapp.repository;

import com.savvas.jobapp.model.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, Integer> {

    public Optional<Scope> findByDescription(String value);
}
