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
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Integer userId;

    private Long horoscopeId;

    private Long partnerExpectationId;
    @Column(name = "contact_number_id")
    private Integer contactNumberId;

    @Column(name = "user_profile_id")
    private Integer userProfileId;
}
