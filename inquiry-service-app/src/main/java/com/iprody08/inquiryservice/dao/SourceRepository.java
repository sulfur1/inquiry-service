package com.iprody08.inquiryservice.dao;

import com.iprody08.inquiryservice.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Long> {
    @Query("SELECT s FROM Source s JOIN FETCH s.inquiries")
    List<Source> findAllWithSources();
}

