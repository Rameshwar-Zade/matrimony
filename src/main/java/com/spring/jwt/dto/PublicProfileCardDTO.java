package com.spring.jwt.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PublicProfileCardDTO {

    private Integer profileId;
    private String fullName;
    private Date birthDate;
    private Integer age;
    private String height;
    private String education;
    private String occupation;
    private String residenceCity;
    private String caste;
    private String profilePhotoBase64;
}