package com.spring.jwt.CompleteProfile;

import com.spring.jwt.dto.EducationAndProfessionDto;
import com.spring.jwt.dto.FamilyBackgroundDto;
import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.entity.FamilyBackground;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.userprofile.UserProfileDto;
import lombok.Data;

@Data
public class PublicProfileDTO {

    //Before Login
    private UserProfileDto user; // name, age, height, city

    private EducationAndProfessionDto education;// Occupation Details , Income

    private FamilyBackgroundDto family;  //residentAddress

    private PartnerExpectationDTO partnerExpectations;


}
