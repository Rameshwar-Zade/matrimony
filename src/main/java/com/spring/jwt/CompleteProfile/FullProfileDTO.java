package com.spring.jwt.CompleteProfile;


import com.spring.jwt.ContactDetails.ContactDetailsDTO;
import com.spring.jwt.dto.EducationAndProfessionDto;
import com.spring.jwt.dto.FamilyBackgroundDto;
import com.spring.jwt.dto.HoroscopeDetailsResponseDTO;
import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.userprofile.UserProfileDto;
import lombok.Data;

@Data
public class FullProfileDTO {

    private UserProfileDto userProfile;
    private ContactDetailsDTO contactDetails;
    private HoroscopeDetailsResponseDTO horoscope;
    private PartnerExpectationDTO partnerExpectations;
    private EducationAndProfessionDto education;
    private FamilyBackgroundDto familyBackground;

}