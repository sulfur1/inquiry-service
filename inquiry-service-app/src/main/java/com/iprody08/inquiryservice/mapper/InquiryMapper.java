package com.iprody08.inquiryservice.mapper;

import com.iprody08.inquiryservice.dto.InquiryDto;
import com.iprody08.inquiryservice.entity.Inquiry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InquiryMapper {

    @Mapping(target = "sourceId", source = "source")
    InquiryDto inquiryToInquiryDto(Inquiry inquiry);

    @Mapping(target = "source", source = "sourceId")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    Inquiry inquiryDtoToInquiry(InquiryDto inquiryDto);

    @Mapping(target = "source", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "comment", ignore = true)
    @Mapping(target = "note", ignore = true)
    @Mapping(target = "productRefId", ignore = true)
    @Mapping(target = "customerRefId", ignore = true)
    @Mapping(target = "managerRefId", ignore = true)
    void updateInquiryFromDto(InquiryDto inquiryDto, @MappingTarget Inquiry inquiry);
}
