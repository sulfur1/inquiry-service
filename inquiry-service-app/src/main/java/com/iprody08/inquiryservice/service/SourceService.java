package com.iprody08.inquiryservice.service;

import com.iprody08.inquiryservice.dto.SourceDto;
import com.iprody08.inquiryservice.filter.SourceFilter;

import java.util.List;

public interface SourceService extends BaseService<SourceDto> {
    List<SourceDto> findAll(Integer pageNo, Integer pageSize, String sortBy,
                            String sortDirection, SourceFilter filterBy);
}

