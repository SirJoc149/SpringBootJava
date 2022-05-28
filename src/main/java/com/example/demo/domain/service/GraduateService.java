package com.example.demo.domain.service;

import com.example.demo.domain.model.Graduate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface GraduateService {
    Page<Graduate> getAllGraduates(Pageable pageable);
    Graduate getGraduateById(Long id);
    ResponseEntity<?> deleteGraduate(Long id);
    Graduate updateGraduate(Long id, Graduate request);
    Graduate createGraduate(Graduate request);
}
