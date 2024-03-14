package com.iprody08.inquiryservice.dto;

import com.iprody08.inquiryservice.entity.Inquiry;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InquiryMapper {

    InquiryMapper INSTANCE = Mappers.getMapper(InquiryMapper.class);

    @Mapping(source = "id", target = "id")
    InquiryDto inquiryToInquiryDto(Inquiry inquiry);

}
