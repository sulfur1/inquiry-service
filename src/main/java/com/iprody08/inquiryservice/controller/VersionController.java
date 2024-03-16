package com.iprody08.inquiryservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service")
public class TestController {
    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/info")
    public String welcome() {
        return "Welcome to the Inquiry Service! Current version is " + appVersion;
    }
}
