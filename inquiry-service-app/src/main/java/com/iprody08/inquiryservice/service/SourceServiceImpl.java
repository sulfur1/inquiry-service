package com.iprody08.inquiryservice.service;

import com.iprody08.inquiryservice.dao.SourceRepository;
import com.iprody08.inquiryservice.dto.SourceDto;
import com.iprody08.inquiryservice.dto.mapper.SourceMapper;
import com.iprody08.inquiryservice.entity.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<SourceDto> findAll(Integer pageNo, Integer pageSize, String sortBy,
                                   String sortDirection, String filterBy) {

        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(direction, sortBy);

        Pageable paging = PageRequest.of(pageNo, pageSize, sort);

        List<Source> resultList = null;

        if (filterBy != null && !filterBy.isEmpty()) {
           resultList = sourceRepository.findAllWithInquiryAndFilter(filterBy, paging);
        } else {
            resultList = sourceRepository.findAllWithInquiry(paging);
        }

        return resultList
                .stream()
                .map(sourceMapper::sourceToSourceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SourceDto> findAll() {
        return null;
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
    public void deleteAll() {
        sourceRepository.deleteAll();
    }

    @Override
    public Optional<SourceDto> update(SourceDto sourceDto) {
        return sourceRepository.findById(sourceDto.getId())
                .map(source -> {
                    sourceMapper.updateSourceFromDto(sourceDto, source);
                    sourceRepository.save(source);
                    return sourceMapper.sourceToSourceDto(source);
                });
    }

    @Override
    public Page<SourceDto> getPagination(Integer pageNumber, Integer pageSize, String sort) {
        return null;
    }


}
