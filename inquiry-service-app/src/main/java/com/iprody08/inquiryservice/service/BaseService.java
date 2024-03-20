package com.iprody08.inquiryservice.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> findAll();
    void save(T entity);
    Optional<T> findById(long id);
    void deleteById(long id);
    Optional<T> update(long id, T entity);
}
