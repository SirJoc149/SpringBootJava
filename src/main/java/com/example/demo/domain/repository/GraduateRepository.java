package com.example.demo.domain.repository;

import com.example.demo.domain.model.Graduate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GraduateRepository extends JpaRepository<Graduate, Long> {
    public Optional<Graduate> findByName(String name);
}
