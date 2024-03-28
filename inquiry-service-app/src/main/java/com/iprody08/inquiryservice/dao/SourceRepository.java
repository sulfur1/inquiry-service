package com.iprody08.inquiryservice.dao;

import com.iprody08.inquiryservice.entity.Source;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Long> {
    @Query("SELECT s FROM Source s LEFT JOIN FETCH s.inquiries")
    List<Source> findAllWithInquiry(Pageable paging);

    @Query("SELECT s FROM Source s LEFT JOIN FETCH s.inquiries WHERE (:filterBy IS NULL OR s.name = :filterBy)")
    List<Source> findAllWithInquiryAndFilter(String filterBy, Pageable paging);

}
