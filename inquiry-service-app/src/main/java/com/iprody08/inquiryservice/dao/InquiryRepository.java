package com.iprody08.inquiryservice.dao;

import com.iprody08.inquiryservice.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    @Query("SELECT i FROM Inquiry i JOIN FETCH i.source")
    List<Inquiry> findAllWithSource();

    @Query("SELECT i FROM Inquiry i JOIN FETCH i.source WHERE i.id = :id")
    Optional<Inquiry> findByIdWithSource(@Param("id") long id);
}
