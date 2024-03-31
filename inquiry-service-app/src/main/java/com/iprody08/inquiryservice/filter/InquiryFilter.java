package com.iprody08.inquiryservice.filter;

import com.iprody08.inquiryservice.entity.enums.InquiryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryFilter {
    private InquiryStatus status;
    private String comment;
    private String note;

    public boolean checkFilterExists() {
        return status != null && !status.toString().isEmpty() || comment != null || note != null;
    }

}
