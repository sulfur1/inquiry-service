package com.iprody08.inquiryservice.controller;

import com.iprody08.inquiryservice.dto.VersionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1")
public class VersionController {
    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/info")
    public VersionDto welcome() {
        final LocalDateTime timestamp = LocalDateTime.now();
        return new VersionDto(appVersion, timestamp);
    }
}
