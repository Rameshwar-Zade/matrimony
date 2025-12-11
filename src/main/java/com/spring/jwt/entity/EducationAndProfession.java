package com.spring.jwt.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull(message = "Education cannot be null")
    @Column(nullable = false)
    private String education;

    // Renamed field
    @NotNull
    @Column(name = "education_details", length = 45, nullable = false)
    private String educationDetails;

    @NotNull(message = "Occupation cannot be null")
    @Column(length = 45, nullable = false)
    private String occupation;

    @NotBlank(message = "Occupation details cannot be blank")
    @Column(length = 45, nullable = false)
    private String occupationDetails;

    @NotNull(message = "Income Per Year cannot be null")
    @Positive(message = "Income Per Year must be positive")
    @Column(nullable = false)
    private Integer incomePerYear;

    @Column(length = 45)
    private String status1;

    @Column(length = 45)
    private String educationAndProfessionalDetailsCol;

//    @Column
//    private Integer userId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToOne(mappedBy = "educationAndProfessionalDetails")
    private Status status;
}