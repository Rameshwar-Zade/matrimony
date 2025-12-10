package com.spring.jwt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EducationAndProfessionDto {

    private Integer EducationAndProfessionalDetailsId;

    private String education;
    @NotNull
    private String educationDetails;  // renamed from degree
    private String occupation;
    private String occupationDetails;
    private Integer incomePerYear;
    private String status;
    private String educationAndProfessionalDetailsCol;
    private Integer userId;
}


