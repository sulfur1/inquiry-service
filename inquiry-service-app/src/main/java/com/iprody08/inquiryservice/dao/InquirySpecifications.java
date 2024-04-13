package com.iprody08.inquiryservice.dao;

import com.iprody08.inquiryservice.entity.Inquiry;
import com.iprody08.inquiryservice.entity.enums.InquiryStatus;
import org.springframework.data.jpa.domain.Specification;

public class InquirySpecifications {
    public static Specification<Inquiry> hasStatus(InquiryStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.upper(root.get("status")), status.toString().toUpperCase());
    }

    public static Specification<Inquiry> hasComment(String comment) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(root.get("comment")), "%" + comment.toUpperCase() + "%");
    }

    public static Specification<Inquiry> hasNote(String note) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(root.get("note")), "%" + note.toUpperCase() + "%");
    }
}
