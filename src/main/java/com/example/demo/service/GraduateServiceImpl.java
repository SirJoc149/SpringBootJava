package com.example.demo.service;

import com.example.demo.domain.model.Graduate;
import com.example.demo.domain.repository.GraduateRepository;
import com.example.demo.domain.service.GraduateService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GraduateServiceImpl implements GraduateService {
    @Autowired
    private GraduateRepository graduateRepository;

    @Override
    public Page<Graduate> getAllGraduates(Pageable pageable) {
        return graduateRepository.findAll(pageable);
    }

    @Override
    public Graduate getGraduateById(Long id) {
        Graduate response = graduateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Graduated", "ID", id));
        return response;
    }

    @Override
    public ResponseEntity<?> deleteGraduate(Long id) {
        Graduate graduate = graduateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Graduated", "ID", id));
        graduateRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public Graduate updateGraduate(Long id, Graduate request) {
        Graduate graduate = graduateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Graduated", "ID", id));
        graduate.setName(request.getName());
        graduate.setApellido(request.getApellido());
        return graduateRepository.save(graduate);
    }

    @Override
    public Graduate createGraduate(Graduate request) {
        return graduateRepository.save(request);
    }
}
