package com.iprody08.inquiryservice.service;

import com.iprody08.inquiryservice.dao.InquiryRepository;
import com.iprody08.inquiryservice.dto.InquiryDto;
import com.iprody08.inquiryservice.dto.mapper.InquiryMapper;
import com.iprody08.inquiryservice.entity.Inquiry;
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
        return inquiryRepository.findAll()
                .stream()
                .map(inquiryMapper::inquiryToInquiryDto)
                .collect(Collectors.toList());
    }

    @Override
    public void save(InquiryDto inquiryDto) {
        final Inquiry inquiry = inquiryMapper.inquiryDtoToInquiry(inquiryDto);
        inquiryRepository.save(inquiry);
    }
    @Override
    public Optional<InquiryDto> findById(long id) {
        return  inquiryRepository
                .findById(id).map(inquiryMapper::inquiryToInquiryDto);
    }

    @Override
    public void deleteById(long id) {
        inquiryRepository.deleteById(id);
    }

    @Override
    public Optional<InquiryDto> update(long id, InquiryDto inquiryDto) {
        final Optional<Inquiry> optionalInquiry = inquiryRepository.findById(id);
        if (optionalInquiry.isPresent()) {
            final Inquiry inquiry = optionalInquiry.get();
            inquiryMapper.updateInquiryFromDto(inquiryDto, inquiry);
            inquiryRepository.save(inquiry);
            return Optional.of(inquiryMapper.inquiryToInquiryDto(inquiry));
        } else {
            return Optional.empty();
        }
    }

}
