package com.iprody08.inquiryservice.controller;

import com.iprody08.inquiryservice.dto.SourceDto;
import com.iprody08.inquiryservice.exception_handlers.NotFoundException;
import com.iprody08.inquiryservice.service.SourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return sourceService.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no Source with id " + id));
    }

    @DeleteMapping("/sources/id/{id}")
    public void deleteById(@PathVariable long id) {
        sourceService.deleteById(id);
    }

    @PostMapping("/sources")
    public ResponseEntity<Void> save(@RequestBody SourceDto entity) {
        sourceService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/sources/id/{id}")
    public ResponseEntity<SourceDto> update(@PathVariable long id, @RequestBody SourceDto sourceDto) {
        return sourceService.update(id, sourceDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("There is no Source with id " + id));
    }

}
