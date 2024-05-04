package com.iprody08.inquiryservice.dto;

import com.iprody08.inquiryservice.entity.enums.InquiryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {

    private Long id;

    private SourceDto sourceId;

    private String comment;

    @Enumerated(EnumType.STRING)
    private InquiryStatus status;

    private String note;

    private Long productRefId;

    private Long customerRefId;

    private Long managerRefId;

    public InquiryDto(SourceDto sourceId, String comment, InquiryStatus status, String note) {
        this.sourceId = sourceId;
        this.comment = comment;
        this.status = status;
        this.note = note;
    }
}
