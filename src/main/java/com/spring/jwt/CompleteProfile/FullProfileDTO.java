package com.spring.jwt.CompleteProfile;


import com.spring.jwt.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class FullProfileDTO {

    // from User-Profile
    private String fullName;
    private Integer age;
    private Integer height;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String city;
    private String caste;
    private Integer weight;
    private String bloodGroup;
    private Boolean spectacle;
    private String complexion;
    private Boolean physicallyChallenged;
    private String diet;
    private Boolean lens;


    //from EducationAndProfession
    private String education;
    private String degree;
    private String occupation;
    private String occupationDetails;
    private Integer incomePerYear;

    //from HoroscopeDetails
    private Date dob;
    private String time;
    private String birthPlace;
    private String rashi;
    private String nakshatra;
    private String charan;
    private String nadi;
    private String gan;
    private String mangal;
    private String gotra;
    private String devak;


    //from FamilyBackgrounda
    private String father;
    private String mother;
    private String brothers;
    private String marriedBrothers;
    private String sisters;
    private String marriedSisters;
    private String parentResiding;
    private String parentOccupation;
    private String familyWealth;
    private String mamaSurname;
    private String mamaPlace;
    private String relativeSurnames;


    //from partnerPreferenceExceptation
    private String preferredCity;
    private String preferredEducation;
    private String preferredOccupation;
    private Integer expectedIncome;
    private Boolean mangalAcceptable;
    private String expectedCaste;
    private Double expectedHeight;

    //From Contact Details
    private String fullAddress;
    private Integer pinCode;
    private Long mobileNumber;
    private Long alternateNumber;

}