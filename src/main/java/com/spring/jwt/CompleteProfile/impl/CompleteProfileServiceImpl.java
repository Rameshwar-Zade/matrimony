package com.spring.jwt.CompleteProfile.impl;

import com.spring.jwt.CompleteProfile.*;
import com.spring.jwt.entity.*;
import com.spring.jwt.exception.IncompleteProfileException;
import com.spring.jwt.horoscopedetails.HoroscopeDetailsRepository;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.partnerexpectations.PartnerExpectationsRepository;
import com.spring.jwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CompleteProfileServiceImpl implements CompleteProfileService {

    @Autowired
    private CompleteProfileRepository completeProfileRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private ContactDetailsRepository contactDetailsRepository;
    @Autowired
    private HoroscopeDetailsRepository horoscopeRepository;
    @Autowired
    private PartnerExpectationsRepository partnerExpectationRepository;
    @Autowired
    private EducationAndProfessionRepository educationRepository;
    @Autowired
    private FamilyBackgroundRepository familyBackgroundRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    public FullProfileDTO getCurrentUserFullProfile() {
        return getFullProfileByUserId(jwtService.extractUserId(jwtService.extractToken()));
    }

    @Override
    public FullProfileDTO getFullProfileByUserId(Integer userId) {

        CompleteProfile cp = completeProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

              UserProfile up=  userProfileRepository.findById(cp.getUserProfileId())
                      .orElseThrow(() -> new IncompleteProfileException("Incomplete User Profile"));

              ContactDetails cd=  contactDetailsRepository.findById(cp.getContactNumberId())
                      .orElseThrow(() -> new IncompleteProfileException("Incomplete Contact Details"));

              HoroscopeDetails hd=  horoscopeRepository.findById(cp.getHoroscopeId())
                      .orElseThrow(() -> new IncompleteProfileException("Incomplete Horoscope Details"));

              PartnerExpectation pe=  partnerExpectationRepository.findById(cp.getPartnerExpectationId())
                      .orElseThrow(() -> new IncompleteProfileException("Incomplete Partner Expectation Details"));

              EducationAndProfession ed=  educationRepository.findById(cp.getEducationId())
                      .orElseThrow(() -> new IncompleteProfileException("Incomplete Education Details"));

              FamilyBackground fb=  familyBackgroundRepository.findById(cp.getFamilyBackgroundId())
                      .orElseThrow(() -> new IncompleteProfileException("Incomplete Family background Details"));



        return CompleteProfileMapper.toFullProfileDTO(up,cd,hd,pe,ed,fb);
    }

    @Override
    public PublicProfileDTO getPublicProfile(Integer userId) {

        CompleteProfile cp = completeProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        UserProfile up=  userProfileRepository.findById(cp.getUserProfileId())
                .orElseThrow(() -> new IncompleteProfileException("Incomplete User Profile Details"));

        HoroscopeDetails hd=  horoscopeRepository.findById(cp.getHoroscopeId())
                .orElseThrow(() -> new IncompleteProfileException("Incomplete Horoscope Details"));

        EducationAndProfession ed=  educationRepository.findById(cp.getEducationId())
                .orElseThrow(() -> new IncompleteProfileException("Incomplete Education Details"));


        return CompleteProfileMapper.toPublicProfileDTO(up,ed,hd);
    }

    @Override
    public BioDataDisplayDTO getBioData(Integer userId) {

        CompleteProfile cp = completeProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        UserProfile up=  userProfileRepository.findById(cp.getUserProfileId())
                .orElseThrow(() -> new IncompleteProfileException("Incomplete Contact Details"));

        HoroscopeDetails hd=  horoscopeRepository.findById(cp.getHoroscopeId())
                .orElseThrow(() -> new IncompleteProfileException("Incomplete Horoscope Details"));

        PartnerExpectation pe=  partnerExpectationRepository.findById(cp.getPartnerExpectationId())
                .orElseThrow(() -> new IncompleteProfileException("Incomplete Partner expectation Details"));

        EducationAndProfession ed=  educationRepository.findById(cp.getEducationId())
                .orElseThrow(() -> new IncompleteProfileException("Incomplete Education Details"));

        FamilyBackground fb=  familyBackgroundRepository.findById(cp.getFamilyBackgroundId())
                .orElseThrow(() -> new IncompleteProfileException("Incomplete Family Background Details"));

        return CompleteProfileMapper.toBioDataDTO(up, ed, hd, fb, pe);
    }
}
