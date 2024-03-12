package com.iprody08.inquiryservice.controller;

import com.iprody08.inquiryservice.entity.Inquiry;
import com.iprody08.inquiryservice.entity.Source;
import com.iprody08.inquiryservice.service.InquiryService;
import com.iprody08.inquiryservice.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public final class  MainController {
    @Autowired
    private InquiryService inquiryService;
    @Autowired
    private SourceService sourceService;

    @GetMapping("/inquiries")
    public List<Inquiry> showAllInquiries() {
        return inquiryService.getAll();
    }

    @GetMapping("/sources")
    public List<Source> showAllISources() {
        return sourceService.getAll();
    }

    @GetMapping("/inquiries/id/{id}")
    public Optional<Inquiry> showInquiry(@PathVariable long id) {
        final Optional<Inquiry> inquiry = inquiryService.get(id);
        return inquiry;
    }

    @GetMapping("/sources/id/{id}")
    public Optional<Source> showSource(@PathVariable long id) {
        final Optional<Source> source = sourceService.get(id);
        return source;
    }

    @DeleteMapping("/inquiry/{id}")
    public String deleteInquiry(@PathVariable long id) {
        inquiryService.delete(id);
        return "Inquiry " + id + "was deleted";
    }

    @DeleteMapping("/source/{id}")
    public String deleteSource(@PathVariable long id) {
        sourceService.delete(id);
        return "Source " + id + "was deleted";
    }

    @GetMapping("/test")
    public String welcome() {
        return "Welcome to the Inquiry Service!";
    }
}
