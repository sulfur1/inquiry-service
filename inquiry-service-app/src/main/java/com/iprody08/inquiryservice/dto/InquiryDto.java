package com.iprody08.inquiryservice.dto;

import com.iprody08.inquiryservice.entity.enums.InquiryStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class InquiryDto {

    private Long id;

    private SourceDto sourceDto;

    private String comment;

    private InquiryStatus status;

    private String note;

}
