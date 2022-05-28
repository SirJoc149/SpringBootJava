package com.example.demo.controller;

import com.example.demo.domain.model.Graduate;
import com.example.demo.domain.service.GraduateService;
import com.example.demo.resource.GraduateResource;
import com.example.demo.resource.SaveGraduateResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class GraduatesController {
    @Autowired
    private GraduateService graduateService;

    @Autowired
    private ModelMapper mapper;


    @GetMapping(value = "graduates")
    public Page<Graduate> getGraudates(Pageable pageable) {
        return graduateService.getAllGraduates(pageable);
    }


    // Hace el mismo trabajo que haciendo getAllGraduates - solo que este se  implementa la interfaz o eso parece
    // todo: cuanto tiempo ?
    @GetMapping(value = "gradPage")
    public Page<GraduateResource> getGraudatesPage(Pageable pageable) {
        Page<Graduate> page = graduateService.getAllGraduates(pageable);
        List<GraduateResource> resources = page.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping(value = "graduates/{id}")
    public Graduate getGraudateById(@PathVariable Long id) {
        return graduateService.getGraduateById(id);
    }

    @PutMapping(value = "graduates/{id}")
    public Graduate getGraudateById(@PathVariable Long id, @Valid @RequestBody SaveGraduateResource resource) {
        return graduateService.updateGraduate(id, this.convertToEntity(resource));
    }

    @PostMapping(value = "graduates")
    public Graduate createGraudates(@Valid @RequestBody SaveGraduateResource resource) {
        return graduateService.createGraduate(convertToEntity(resource));
    }

    private Graduate convertToEntity(SaveGraduateResource resource) {
        return mapper.map(resource, Graduate.class);
    }

    private GraduateResource convertToResource(Graduate entity) {
        return mapper.map(entity, GraduateResource.class);
    }
}
