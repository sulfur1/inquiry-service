package com.iprody08.inquiryservice.service;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();
    void save(T entity);
    T findById(long id);
    void deleteById(long id);
}
