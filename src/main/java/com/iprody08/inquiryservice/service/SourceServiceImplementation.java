package com.iprody08.inquiryservice.service;

import com.iprody08.inquiryservice.dao.SourceRepository;
import com.iprody08.inquiryservice.entity.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SourceServiceImplementation implements SourceService {
    @Autowired
    @Qualifier("sourceRepository")
    private SourceRepository sourceRepository;

    @Override
    public List<Source> getAll() {
        return sourceRepository.findAll();
    }

    @Override
    public void set(Source entity) {
        sourceRepository.save(entity);
    }

    @Override
    public Optional<Source> get(long id) {
        return sourceRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        sourceRepository.deleteById(id);
    }
}
