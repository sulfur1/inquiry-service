package com.iprody08.inquiryservice.dto.mapper;

import com.iprody08.inquiryservice.dto.InquiryDto;
import com.iprody08.inquiryservice.entity.Inquiry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InquiryMapper {
    @Mapping(target = "sourceDto", source = "source")
    InquiryDto inquiryToInquiryDto(Inquiry inquiry);
    @Mapping(target = "source", source = "sourceDto")
    Inquiry inquiryDtoToInquiry(InquiryDto inquiryDto);
}
