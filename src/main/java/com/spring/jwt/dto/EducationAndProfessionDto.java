package com.spring.jwt.dto;

import lombok.Data;

@Data
public class EducationAndProfessionDto {

    private Integer EducationAndProfessionalDetailsId;

    private String education;
    private String degree;
    private String occupation;
    private String occupationDetails;
    private Integer incomePerYear;
    private String status;
    private String educationAndProfessionalDetailsCol;
    private Integer userId;
}


