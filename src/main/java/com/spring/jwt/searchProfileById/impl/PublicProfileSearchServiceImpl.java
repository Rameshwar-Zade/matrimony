package com.spring.jwt.searchProfileById.impl;

import com.spring.jwt.dto.PublicProfileCardDTO;
import com.spring.jwt.entity.*;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.horoscopedetails.HoroscopeDetailsRepository;
import com.spring.jwt.repository.*;
import com.spring.jwt.searchProfileById.PublicProfileSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class PublicProfileSearchServiceImpl
        implements PublicProfileSearchService {

    private final CompleteProfileRepository completeProfileRepository;
    private final UserProfileRepository userProfileRepository;
    private final HoroscopeDetailsRepository horoscopeDetailsRepository;
    private final EducationAndProfessionRepository educationRepository;

    @Override
    public PublicProfileCardDTO searchByProfileId(Integer profileId) {

        CompleteProfile cp = completeProfileRepository
                .findByUserProfileId(profileId)
                .orElseThrow(() ->
                        new UserNotFoundExceptions("Profile not found"));

        UserProfile userProfile = userProfileRepository
                .findById(cp.getUserProfileId())
                .orElseThrow(() ->
                        new UserNotFoundExceptions("User profile not found"));

        HoroscopeDetails horoscope = horoscopeDetailsRepository
                .findById(cp.getHoroscopeId())
                .orElseThrow(() ->
                        new UserNotFoundExceptions("Horoscope not found"));

        EducationAndProfession edu = educationRepository
                .findById(cp.getEducationId())
                .orElseThrow(() ->
                        new UserNotFoundExceptions("Education details not found"));

        PublicProfileCardDTO dto = new PublicProfileCardDTO();

        dto.setProfileId(profileId);
        dto.setFullName(userProfile.getFirstName() + " " + userProfile.getLastName());
        dto.setBirthDate(horoscope.getDob());
        dto.setAge(calculateAge(horoscope.getDob()));
        dto.setHeight(userProfile.getHeight() + " cm");
        dto.setEducation(edu.getEducation());
        dto.setOccupation(edu.getOccupation());
        dto.setResidenceCity(userProfile.getCurrentCity());
        dto.setCaste(userProfile.getCaste());

        // Profile Photo
        if (cp.getUserDocuments() != null &&
                cp.getUserDocuments().getProfilePhoto() != null) {

            dto.setProfilePhotoBase64(
                    Base64.getEncoder()
                            .encodeToString(cp.getUserDocuments().getProfilePhoto())
            );
        }

        return dto;
    }

    private Integer calculateAge(java.util.Date dob) {
        LocalDate birthDate = dob.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
