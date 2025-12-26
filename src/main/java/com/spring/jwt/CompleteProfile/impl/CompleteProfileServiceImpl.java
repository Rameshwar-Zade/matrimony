package com.spring.jwt.CompleteProfile.impl;

import com.spring.jwt.CompleteProfile.CompleteProfileMapper;
import com.spring.jwt.CompleteProfile.CompleteProfileService;
import com.spring.jwt.CompleteProfile.FullProfileDTO;
import com.spring.jwt.CompleteProfile.PublicProfileDTO;
import com.spring.jwt.entity.*;
import com.spring.jwt.enums.Gender;
import com.spring.jwt.exception.ProfileNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.exception.UserProfileNotFoundException;
import com.spring.jwt.horoscopedetails.HoroscopeDetailsRepository;
import com.spring.jwt.partnerexpectations.PartnerExpectationsRepository;
import com.spring.jwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private PartnerExpectationsRepository expectationRepository;

    @Autowired
    private EducationAndProfessionRepository educationAndProfessionRepository;

    @Override
    public FullProfileDTO getCompleteProfile(Integer userId) {

        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }

        // Must exist, else user does not have a full profile yet
        CompleteProfile cp = completeProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No details found For this user"));

        // UserProfile must exist
        UserProfile up = userProfileRepository.findByUserId(userId);
        if (up == null) {
            throw new ProfileNotFoundException("Profile not found");
        }

        // Public profile should NOT fail if these details are missing
        ContactDetails cd = contactDetailsRepository.findById(cp.getContactNumberId()).orElse(null);
        PartnerExpectation ec = expectationRepository.findById(cp.getPartnerExpectationId()).orElse(null);
        EducationAndProfession ep = educationAndProfessionRepository.findById(cp.getEducationId()).orElse(null);
        FamilyBackground fb = familyBackgroundRepository.findById(cp.getFamilyBackgroundId()).orElse(null);
        HoroscopeDetails hd = horoscopeDetailsRepository.findById(cp.getHoroscopeId()).orElse(null);

        return mapper.toFullDTO(up, cd, hd, ec, ep, fb);
    }


    @Override
    public PublicProfileDTO getPublicProfile(Integer userId) {

        // Main profile must exist
        CompleteProfile cp = completeProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("No details found For this user"));

        UserProfile up = userProfileRepository.findById(cp.getUserProfileId())
                .orElseThrow(() -> new UserProfileNotFoundException("This User Doesn't Have Profile"));

        // Public profile should NOT fail if these details are missing
        PartnerExpectation ec = expectationRepository.findById(cp.getPartnerExpectationId()).orElse(null);
        EducationAndProfession ep = educationAndProfessionRepository.findById(cp.getEducationId()).orElse(null);
        FamilyBackground fb = familyBackgroundRepository.findById(cp.getFamilyBackgroundId()).orElse(null);

        return mapper.toPublicDTO(up, ec, ep, fb);
    }



    @Override
    public List<FullProfileDTO> getAllByGender(Gender gender) {

        List<UserProfile> profiles = userProfileRepository.findByGender(gender);
        List<FullProfileDTO> result = new ArrayList<>();

        for (UserProfile profile : profiles) {

            if (profile.getUser() == null || profile.getUser().getId() == null) {
                System.out.println("Skipping profile with no user linked. ProfileId = " + profile.getUserProfileId());
                continue;
            }

            Integer userId = profile.getUser().getId();

            try {
                FullProfileDTO dto = getCompleteProfile(userId);
                result.add(dto);
            } catch (Exception ex) {
                System.out.println("Skipping incomplete profile for userId = " + userId);
            }
        }

        return result;
    }

}