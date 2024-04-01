package com.iprody08.inquiryservice.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SourceFilter implements DtoFilter {
    private String name;

    @Override
    public boolean checkFilterExists() {
        return name != null;
    }
}
