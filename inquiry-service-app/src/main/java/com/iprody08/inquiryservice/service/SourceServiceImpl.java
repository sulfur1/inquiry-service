package com.iprody08.inquiryservice.service;

import com.iprody08.inquiryservice.dao.SourceRepository;
import com.iprody08.inquiryservice.dto.SourceDto;
import com.iprody08.inquiryservice.dto.mapper.SourceMapper;
import com.iprody08.inquiryservice.entity.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SourceServiceImpl implements SourceService {
    private final SourceRepository sourceRepository;
    private final SourceMapper sourceMapper;

    @Autowired
    public SourceServiceImpl(final SourceRepository sourceRepository, final SourceMapper sourceMapper) {
        this.sourceRepository = sourceRepository;
        this.sourceMapper = sourceMapper;
    }

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
    public Optional<SourceDto> findById(long id) {
        return sourceRepository.findById(id)
                .map(sourceMapper::sourceToSourceDto);
    }

    @Override
    public void deleteById(long id) {
        sourceRepository.deleteById(id);
    }

    @Override
    public Optional<SourceDto> update(long id, SourceDto entity) {
        final Optional<Source> optionalSource = sourceRepository.findById(id);
        if (optionalSource.isPresent()) {
            final Source source = optionalSource.get();
            sourceMapper.updateSourceFromDto(entity, source);
            sourceRepository.save(source);
            return Optional.of(sourceMapper.sourceToSourceDto(source));
        } else {
            return Optional.empty();
        }
    }

}
