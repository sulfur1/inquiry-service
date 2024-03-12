package com.iprody08.inquiryservice.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> getAll();
    void set(T entity);
    Optional<T> get(long id);
    void delete(long id);
}
