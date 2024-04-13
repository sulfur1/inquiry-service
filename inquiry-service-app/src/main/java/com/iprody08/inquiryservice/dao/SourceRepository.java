package com.iprody08.inquiryservice.dao;

import com.iprody08.inquiryservice.entity.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SourceRepository extends JpaRepository<Source, Long> {
    @Query("SELECT s FROM Source s LEFT JOIN FETCH s.inquiries")
    Page<Source> findAllWithInquiry(Pageable paging);

    @EntityGraph(attributePaths = {"inquiries"})
    Page<Source> findAll(Specification<Source> spec, Pageable pageable);

}
