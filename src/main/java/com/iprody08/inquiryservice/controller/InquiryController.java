package com.iprody08.inquiryservice.controller;

import com.iprody08.inquiryservice.dto.InquiryDto;
import com.iprody08.inquiryservice.service.InquiryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public final class InquiryController {
    private final InquiryService inquiryService;

    public InquiryController(final InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/inquiries/id/{id}")
    public InquiryDto findById(@PathVariable long id) {
        return inquiryService.findById(id);
    }

    @GetMapping("/inquiries")
    public List<InquiryDto> findAll() {
        return inquiryService.findAll();
    }

    @DeleteMapping("/inquiry/{id}")
    public void deleteById(@PathVariable long id) {
        inquiryService.deleteById(id);
    }

}
