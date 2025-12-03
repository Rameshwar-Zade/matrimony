package com.spring.jwt.service.impl;

import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.entity.ExpectationCompleteProfile;
import com.spring.jwt.entity.ExpectationsComplete;
import com.spring.jwt.entity.HorosCopeCompleteProfile;

import com.spring.jwt.repository.ExpectationCompleteProfileRepository;
import com.spring.jwt.repository.ExpectationsCompleteRepository;
import com.spring.jwt.repository.HorosCopeCompleteProfileRepository;
import com.spring.jwt.service.PartnerExpectationService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PartnerExpectationServiceImpl implements PartnerExpectationService {

    @Autowired
    private ExpectationsCompleteRepository expectationsRepo;

    @Autowired
    private HorosCopeCompleteProfileRepository horosCopeCompleteRepo;

    @Autowired
    private ExpectationCompleteProfileRepository expectationCompleteRepo;

    @Override
    public Long saveExpectations(Long userId, PartnerExpectationDTO dto) {

        ExpectationsComplete existing = expectationsRepo.findByUserId(userId);

        ExpectationsComplete entity = (existing != null)
                ? existing
                : new ExpectationsComplete();

        entity.setUserId(userId);
        entity.setAgeFrom(dto.getAgeFrom());
        entity.setAgeTo(dto.getAgeTo());
        entity.setHeightFeet(dto.getHeightFeet());
        entity.setHeightInches(dto.getHeightInches());
        entity.setLookingFor(dto.getLookingFor());
        entity.setCaste(dto.getCaste());
        entity.setEducation(dto.getEducation());
        entity.setResidentStatus(dto.getResidentStatus());
        entity.setPreference(dto.getPreference());
        entity.setCountry(dto.getCountry());
        entity.setEatingHabits(dto.getEatingHabits());
        entity.setReligion(dto.getReligion());
        entity.setComplexion(dto.getComplexion());

        // Save expectation
        ExpectationsComplete saved = expectationsRepo.save(entity);

        // Get horoscope ID
        Long horoscopeId = horosCopeCompleteRepo.findByUserId(userId)
                .map(HorosCopeCompleteProfile::getHoroscopeId)
                .orElse(null);

        // Update link table
        ExpectationCompleteProfile link = expectationCompleteRepo
                .findByUserId(userId)
                .orElse(new ExpectationCompleteProfile());

        link.setUserId(userId);
        link.setHoroscopeId(horoscopeId);
        link.setPartnerExpectationId(saved.getId());

        expectationCompleteRepo.save(link);

        return saved.getId();
    }

    @Override
    public ExpectationsComplete getExpectations(Long userId) {
        return expectationsRepo.findByUserId(userId);
    }

    @Override
    public ExpectationsComplete updateExpectations(Long userId, PartnerExpectationDTO dto) {

        ExpectationsComplete existing = expectationsRepo.findByUserId(userId);

        if (existing == null) {
            throw new RuntimeException("No expectation data found");
        }

        existing.setAgeFrom(dto.getAgeFrom());
        existing.setAgeTo(dto.getAgeTo());
        existing.setHeightFeet(dto.getHeightFeet());
        existing.setHeightInches(dto.getHeightInches());
        existing.setLookingFor(dto.getLookingFor());
        existing.setCaste(dto.getCaste());
        existing.setEducation(dto.getEducation());
        existing.setResidentStatus(dto.getResidentStatus());
        existing.setPreference(dto.getPreference());
        existing.setCountry(dto.getCountry());
        existing.setEatingHabits(dto.getEatingHabits());
        existing.setReligion(dto.getReligion());
        existing.setComplexion(dto.getComplexion());

        return expectationsRepo.save(existing);
    }

    @Override
    public void deleteExpectations(Long userId) {

        ExpectationsComplete existing = expectationsRepo.findByUserId(userId);

        if (existing == null) {
            throw new RuntimeException("No expectation found for this user");
        }

        expectationCompleteRepo.findByUserId(userId)
                .ifPresent(link -> {
                    link.setPartnerExpectationId(null);
                    expectationCompleteRepo.save(link);
                });

        expectationsRepo.delete(existing);
    }
}
