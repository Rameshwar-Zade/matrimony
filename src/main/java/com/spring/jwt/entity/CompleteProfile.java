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

    private Integer horoscopeId;

    private Integer partnerExpectationId;
    @Column(name = "contact_number_id")
    private Integer contactNumberId;

    @Column(name = "user_profile_id")
    private Integer userProfileId;

    @Column(name = "education_id")
    private Integer educationId;

    @Column(name = "family_background_id")
    private Integer familyBackgroundId;

}
