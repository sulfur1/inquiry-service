package com.iprody08.inquiryservice.service;

import com.iprody08.inquiryservice.dao.SourceRepository;
import com.iprody08.inquiryservice.dto.SourceDto;
import com.iprody08.inquiryservice.dto.mapper.SourceMapper;
import com.iprody08.inquiryservice.entity.Source;
import com.iprody08.inquiryservice.exception_handlers.NoSuchDtoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceServiceImplementation implements SourceService {
    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private SourceMapper sourceMapper;

    @Override
    public List<SourceDto> findAll() {
        return sourceRepository.findAll()
                .stream()
                .map(sourceMapper::sourceToSourceDto).collect(Collectors.toList());
    }

    @Override
    public void save(SourceDto entity) {
        final Source source = sourceMapper.sourceDtoToSource(entity);
        sourceRepository.save(source);
    }

    @Override
    public SourceDto findById(long id) {
        return sourceRepository.findById(id)
                .map(sourceMapper::sourceToSourceDto)
                .orElseThrow(() -> new NoSuchDtoException("There is no Source with id " + id));
    }

    @Override
    public void deleteById(long id) {
        sourceRepository.deleteById(id);
    }
}
