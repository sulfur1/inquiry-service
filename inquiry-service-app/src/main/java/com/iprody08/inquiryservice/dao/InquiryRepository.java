package com.iprody08.inquiryservice.dao;

import com.iprody08.inquiryservice.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    @Query("SELECT i FROM Inquiry i JOIN FETCH i.source")
    List<Inquiry> findAllWithInquiry();
}
