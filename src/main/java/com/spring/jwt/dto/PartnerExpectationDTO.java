package com.spring.jwt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerExpectationDTO {

    @NotNull(message = "ageFrom is required")
    private Integer ageFrom;

    @NotNull(message = "ageTo is required")
    private Integer ageTo;

    @NotNull(message = "heightFeet is required")
    private Integer heightFeet;

    @NotNull(message = "heightInches is required")
    private Integer heightInches;

    private String lookingFor;
    private String caste;
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
