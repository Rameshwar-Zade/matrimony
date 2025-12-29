package com.spring.jwt.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "complete_profile")
@Getter
@Setter
public class CompleteProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Integer userId;

    @Column(nullable = true)
    private Integer horoscopeId;

    @Column(nullable = true)
    private Integer partnerExpectationId;

    @Column(name = "contact_number_id",nullable = true)
    private Integer contactNumberId;

    @Column(name = "user_profile_id",nullable = true)
    private Integer userProfileId;

    @Column(name = "education_id",nullable = true)
    private Integer educationId;

    @Column(name = "family_background_id",nullable = true)
    private Integer familyBackgroundId;

    /* DOCUMENTS */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_documents_id")
    private UserDocuments userDocuments;

}