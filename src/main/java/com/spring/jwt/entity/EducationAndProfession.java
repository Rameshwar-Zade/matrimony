package com.spring.jwt.entity;


import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "educationAndProfessionalDetails")
@Getter
@Setter
public class EducationAndProfession {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer educationAndProfessionalDetailsId;

    // Dropdown: High School, Diploma, Graduation, Post Graduation

    @Column(nullable = false)
    private String education;

    // Renamed field

    @Column(name = "education_details", length = 45, nullable = false)
    private String educationDetails;

    @Column(length = 45, nullable = false)
    private String occupation;


    @Column(length = 45, nullable = false)
    private String occupationDetails;


    @Column(nullable = false)
    private Integer incomePerYear;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(length = 45)
    private String educationAndProfessionalDetailsCol;


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToOne(mappedBy = "educationAndProfessionalDetails")
    private Status status1;


}