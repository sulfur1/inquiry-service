package com.iprody08.inquiryservice.controller;

import com.iprody08.inquiryservice.dto.SourceDto;
import com.iprody08.inquiryservice.service.SourceService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@RestController
@RequestMapping("/api/v1")
public final class SourceController {
    private final SourceService sourceService;

    public SourceController(final SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping("/sources")
    public List<SourceDto> findAll() {
        return sourceService.findAll();
    }

    @GetMapping("/sources/id/{id}")
    public SourceDto findById(@PathVariable long id) {
        return sourceService.findById(id);
    }

    @DeleteMapping("/source/{id}")
    public void deleteById(@PathVariable long id) {
        sourceService.deleteById(id);
    }

}
