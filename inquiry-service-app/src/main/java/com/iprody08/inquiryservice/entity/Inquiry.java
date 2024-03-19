package com.iprody08.inquiryservice.entity;

import com.iprody08.inquiryservice.entity.enums.InquiryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@EntityListeners(InquiryEntityListener.class)
@Table(name = "inquiries")
@Getter
@Setter
@NoArgsConstructor
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", nullable = true)
    private Source source;

    @Column(nullable = true, columnDefinition = "text")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InquiryStatus status;

    @Column(nullable = true, columnDefinition = "text")
    private String note;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
