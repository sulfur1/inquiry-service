package com.iprody08.inquiryservice.dao;

import com.iprody08.inquiryservice.entity.Inquiry;
import com.iprody08.inquiryservice.entity.enums.InquiryStatus;
import org.springframework.data.jpa.domain.Specification;

public class InquirySpecifications {
    public static Specification<Inquiry> hasStatus(InquiryStatus status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Inquiry> hasComment(String comment) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("comment"), "%" + comment + "%");
    }

    public static Specification<Inquiry> hasNote(String note) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("note"), "%" + note + "%");
    }
}
