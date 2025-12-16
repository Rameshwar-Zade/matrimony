package com.spring.jwt.CompleteProfile.impl;

import com.spring.jwt.CompleteProfile.CompleteProfileMapper;
import com.spring.jwt.CompleteProfile.CompleteProfileService;
import com.spring.jwt.CompleteProfile.FullProfileDTO;
import com.spring.jwt.CompleteProfile.PublicProfileDTO;
import com.spring.jwt.entity.*;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.exception.UserProfileNotFoundException;
import com.spring.jwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompleteProfileServiceImpl implements CompleteProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private CompleteProfileMapper mapper;

    @Autowired
    private CompleteProfileRepository completeProfileRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private HoroscopeDetailsRepository horoscopeDetailsRepository;

    @Autowired
    private FamilyBackgroundRepository familyBackgroundRepository;
    @Autowired
    private ExpectationsCompleteRepository expectationRepository;

    @Autowired
    private EducationAndProfessionRepository educationAndProfessionRepository;

    @Override
    public FullProfileDTO getCompleteProfile(Integer userId) {
        CompleteProfile cp=completeProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No details found For this user"));


        UserProfile up=userProfileRepository.findByUser_Id(userId)
                .orElseThrow(() -> new UserProfileNotFoundException(
                        "This User Doesn't Have Profile"));

        ContactDetails cd= contactDetailsRepository.findById(cp.getContactNumberId())
                .orElseThrow(() -> new UserProfileNotFoundException(
                        "This User Doesn't Have Contact Details"));

        ExpectationsComplete ec=expectationRepository.findById(cp.getPartnerExpectationId())
                .orElseThrow(() -> new UserProfileNotFoundException(
                "This User Doesn't Have Profile"));

        EducationAndProfession ep=educationAndProfessionRepository.findById(cp.getEducationId())
                .orElseThrow(() -> new UserProfileNotFoundException(
                        "This User Doesn't Have Education and profession Details"));

        FamilyBackground fb=familyBackgroundRepository.findById(cp.getFamilyBackgroundId())
                .orElseThrow(() -> new UserProfileNotFoundException(
                        "This User Doesn't Have FamilyBackground Details"));

        HoroscopeDetails hd=horoscopeDetailsRepository.findById(cp.getHoroscopeId())
                .orElseThrow(() -> new UserProfileNotFoundException(
                        "This User Doesn't Have horoscope Details"));

                return mapper.toFullDTO(up,cd,hd,ec,ep,fb);
    }


    @Override
    public PublicProfileDTO getPublicProfile(Integer userId) {

        CompleteProfile cp=completeProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No details found For this user"));

        UserProfile up=userProfileRepository.findById(cp.getUserProfileId())
                .orElseThrow(() -> new UserProfileNotFoundException(
                        "This User Doesn't Have Profile"));

        ExpectationsComplete ec=expectationRepository.findById(cp.getPartnerExpectationId())
                .orElseThrow(() -> new UserProfileNotFoundException(
                        "This User Doesn't Have Profile"));

        EducationAndProfession ep=educationAndProfessionRepository.findById(cp.getEducationId())
                .orElseThrow(() -> new UserProfileNotFoundException(
                        "This User Doesn't Have Education and profession Details"));

        FamilyBackground fb=familyBackgroundRepository.findById(cp.getFamilyBackgroundId())
                .orElseThrow(() -> new UserProfileNotFoundException(
                        "This User Doesn't Have FamilyBackground Details"));

        return mapper.toPublicDTO(up,ec,ep,fb);
    }
}
