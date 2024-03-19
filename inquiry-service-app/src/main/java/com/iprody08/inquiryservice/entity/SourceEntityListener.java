package com.iprody08.inquiryservice.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class SourceEntityListener {
    @PrePersist
    public void prePersist(Source source) {
        source.setCreatedAt(LocalDateTime.now());
        source.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(Source source) {
        source.setUpdatedAt(LocalDateTime.now());
    }

}
