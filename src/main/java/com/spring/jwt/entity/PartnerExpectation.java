package com.spring.jwt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "partner_expectation")
@Getter
@Setter
@NoArgsConstructor
public class PartnerExpectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;

    private Integer ageFrom;
    private Integer ageTo;

    private Integer heightFeet;
    private Integer heightInches;

    private String lookingFor;
    private String caste;

    @NotBlank
    private String education;

    private String residentStatus;
    private String income;
    private String country;
    private String eatingHabits;
    private String religion;
    private String city;
    private String occupation;
    private String mangal;
    private String complexion;
}
