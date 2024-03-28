package com.iprody08.inquiryservice.service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> findAll();
    void save(T entity);
    Optional<T> findById(long id);
    void deleteById(long id);
    void deleteAll();
    Optional<T> update(T entity);
    Page<T> getPagination(Integer pageNumber, Integer pageSize, String sort);

}
