package com.iprody08.inquiryservice.dao;

import com.iprody08.inquiryservice.entity.Source;
import org.springframework.data.jpa.domain.Specification;

public class SourceSpecification {
    public static Specification<Source> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%");
    }
}
