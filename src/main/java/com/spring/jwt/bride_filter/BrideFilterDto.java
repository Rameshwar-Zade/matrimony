package com.spring.jwt.bride_filter;

import lombok.Data;

import java.util.List;


@Data
public class BrideFilterDto {


    private String gender;          // Female
    private String city;            // Pune
    private String marriedStatus;   // Unmarried
    private String country;         // India

    private List<String> educationOrProfession;

}


