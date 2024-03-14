package com.iprody08.inquiryservice.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EntityMapper<E, D> {

    @Mapping(source = "id", target = "id")
    D entityToDto(E entity);

    @Mapping(source = "id", target = "id")
    E dtoToEntity(D dto);
}
