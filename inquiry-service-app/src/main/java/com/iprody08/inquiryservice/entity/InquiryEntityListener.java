package com.iprody08.inquiryservice.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class InquiryEntityListener {
    @PrePersist
    public void prePersist(Inquiry inquiry) {
        inquiry.setCreatedAt(LocalDateTime.now());
        inquiry.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(Inquiry inquiry) {
        inquiry.setUpdatedAt(LocalDateTime.now());
    }

}
