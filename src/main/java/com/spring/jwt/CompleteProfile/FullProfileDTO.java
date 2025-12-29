package com.spring.jwt.CompleteProfile;


import com.spring.jwt.entity.*;
import lombok.Data;

@Data
public class FullProfileDTO {

    private UserProfile userProfile;
    private ContactDetails contactDetails;
    private HoroscopeDetails horoscope;
    private PartnerExpectation partnerExpectations;
    private EducationAndProfession education;
    private FamilyBackground familyBackground;

}