package com.spring.jwt.CompleteProfile;

import com.spring.jwt.ContactDetails.ContactDetailsMapper;
import com.spring.jwt.entity.*;
import com.spring.jwt.mapper.EducationAndProfessionMapper;
import com.spring.jwt.mapper.FamilyBackgroundMapper;
import com.spring.jwt.mapper.HoroscopeDetailsMapper;
import com.spring.jwt.mapper.PartnerExpectationMapper;
import com.spring.jwt.userprofile.UserProfileMapper;
import org.springframework.stereotype.Component;

@Component
public class CompleteProfileMapper {

    //personal Information

    public FullProfileDTO toFullDTO(UserProfile up,
                                    ContactDetails cd,
                                    HoroscopeDetails hd,
                                    PartnerExpectation pe,
                                    EducationAndProfession ep,
                                    FamilyBackground fb) {

        FullProfileDTO dto = new FullProfileDTO();

        dto.setUserProfile(UserProfileMapper.toDTO(up));
        dto.setContactDetails(ContactDetailsMapper.toDTO(cd));
        dto.setHoroscope(HoroscopeDetailsMapper.toDTO(hd));
        dto.setPartnerExpectations(PartnerExpectationMapper.toDTO(pe));
        dto.setEducation(EducationAndProfessionMapper.toDto(ep));
        dto.setFamilyBackground(FamilyBackgroundMapper.toDto(fb));

        return dto;
    }

    public PublicProfileDTO toPublicDTO(UserProfile up,
                                        PartnerExpectation pe,
                                        EducationAndProfession ep,
                                        FamilyBackground fb){

        PublicProfileDTO dto = new PublicProfileDTO();

        dto.setUser(UserProfileMapper.toDTO(up));
        dto.setEducation(EducationAndProfessionMapper.toDto(ep));
        dto.setFamily(FamilyBackgroundMapper.toDto(fb));
        dto.setPartnerExpectations(PartnerExpectationMapper.toDTO(pe));

        return dto;
    }

}
