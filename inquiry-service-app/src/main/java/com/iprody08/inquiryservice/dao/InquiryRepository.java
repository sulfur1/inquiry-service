package com.iprody08.inquiryservice.dao;

import com.iprody08.inquiryservice.entity.Inquiry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>, JpaSpecificationExecutor<Inquiry> {
    @Query("SELECT i FROM Inquiry i JOIN FETCH i.source")
    List<Inquiry> findAllWithSource(Pageable paging);

    List<Inquiry> findAllAndFilter(Specification<Inquiry> spec, Pageable paging);

    @Query("SELECT i FROM Inquiry i JOIN FETCH i.source WHERE i.id = :id")
    Optional<Inquiry> findByIdWithSource(@Param("id") long id);
}
