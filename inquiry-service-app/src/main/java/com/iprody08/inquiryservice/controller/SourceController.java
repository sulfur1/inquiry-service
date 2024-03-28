package com.iprody08.inquiryservice.controller;

import com.iprody08.inquiryservice.dto.SourceDto;
import com.iprody08.inquiryservice.exception_handlers.NotFoundException;
import com.iprody08.inquiryservice.service.SourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public final class SourceController {

    private static final String NO_SOURCE_WITH_ID_MESSAGE = "There is no Source with id";
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
                .orElseThrow(() -> new NotFoundException(String.format(NO_SOURCE_WITH_ID_MESSAGE, id)));
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
                .orElseThrow(() -> new NotFoundException(String.format(NO_SOURCE_WITH_ID_MESSAGE, id)));
    }

}
