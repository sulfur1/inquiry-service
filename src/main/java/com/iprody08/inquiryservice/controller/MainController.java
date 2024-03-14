package com.iprody08.inquiryservice.controller;

import com.iprody08.inquiryservice.entity.Inquiry;
import com.iprody08.inquiryservice.entity.Source;
import com.iprody08.inquiryservice.exception_handlers.NoSuchEntityException;
import com.iprody08.inquiryservice.service.InquiryService;
import com.iprody08.inquiryservice.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@RestController
@RequestMapping("/api/version/1")
public final class  MainController {
    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private SourceService sourceService;

    @GetMapping("/inquiries")
    public List<Inquiry> findAllInquiries() {
        return inquiryService.findAll();
    }

    @GetMapping("/sources")
    public List<Source> findAllISources() {
        return sourceService.findAll();
    }

    @GetMapping("/inquiries/id/{id}")
    public Inquiry findByIdInquiry(@PathVariable long id) {
        return inquiryService.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("There is no inquiry with id " + id));
    }

    @GetMapping("/sources/id/{id}")
    public Source findByIdSource(@PathVariable long id) {
        return sourceService.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("There is no sources with id " + id));
    }

    @DeleteMapping("/inquiry/{id}")
    public void deleteByIdInquiry(@PathVariable long id) {
        inquiryService.deleteById(id);
    }

    @DeleteMapping("/source/{id}")
    public void deleteByIdSource(@PathVariable long id) {
        sourceService.deleteById(id);
    }

}
