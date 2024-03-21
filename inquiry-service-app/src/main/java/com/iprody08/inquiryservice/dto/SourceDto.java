package com.iprody08.inquiryservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SourceDto {

    private Long id;

    private String name;

    private List<InquiryDto> inquiryDto;

}
