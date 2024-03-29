package com.iprody08.inquiryservice.service;

import com.iprody08.inquiryservice.dao.InquiryRepository;
import com.iprody08.inquiryservice.dto.InquiryDto;
import com.iprody08.inquiryservice.dto.mapper.InquiryMapper;
import com.iprody08.inquiryservice.entity.Inquiry;
import com.iprody08.inquiryservice.entity.enums.InquiryStatus;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;
    private final InquiryMapper inquiryMapper;

    public InquiryServiceImpl(final InquiryRepository inquiryRepository, final InquiryMapper inquiryMapper) {
        this.inquiryRepository = inquiryRepository;
        this.inquiryMapper = inquiryMapper;
    }
    @Override
    public List<InquiryDto> findAll() {
        return inquiryRepository.findAllWithSource()
                .stream()
                .map(inquiryMapper::inquiryToInquiryDto)
                .collect(Collectors.toList());
    }

    @Override
    public void save(InquiryDto inquiryDto) {
        final Inquiry inquiry = inquiryMapper.inquiryDtoToInquiry(inquiryDto);
        inquiry.setStatus(InquiryStatus.NEW);
        inquiryRepository.save(inquiry);
    }

    @Override
    public Optional<InquiryDto> findById(long id) {
        return inquiryRepository.findByIdWithSource(id).map(inquiryMapper::inquiryToInquiryDto);

    }

    @Override
    public void deleteById(long id) {
        inquiryRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        inquiryRepository.deleteAll();
    }

    @Override
    public Optional<InquiryDto> update(InquiryDto inquiryDto) {
        return inquiryRepository.findById(inquiryDto.getId())
                .map(inquiry -> {
                    inquiryMapper.updateInquiryFromDto(inquiryDto, inquiry);
                    inquiry.setStatus(inquiryDto.getStatus());
                    return inquiryMapper.inquiryToInquiryDto(inquiryRepository.save(inquiry));
                });
    }

    @Override
    public Page<InquiryDto> getPagination(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if (sort != null) {
            // with sorting
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            // without sorting
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        Page<Inquiry> inquiryPage = inquiryRepository.findAll(pageable);
        List<InquiryDto> inquiryDtos = inquiryPage.getContent().stream()
                .map(inquiryMapper::inquiryToInquiryDto)
                .collect(Collectors.toList());
        return new PageImpl<>(inquiryDtos, pageable, inquiryPage.getTotalElements());

    }

}
