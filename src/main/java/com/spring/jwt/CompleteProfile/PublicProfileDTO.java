package com.spring.jwt.CompleteProfile;

import com.spring.jwt.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class  PublicProfileDTO {

    private Integer userId;
    private String lastName;
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer height;
    private String district;
    private String education;
    private String occupation;
    private String caste;

}
