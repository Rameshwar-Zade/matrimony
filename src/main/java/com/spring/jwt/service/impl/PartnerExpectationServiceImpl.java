package com.spring.jwt.service.impl;

import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.ExpectationsComplete;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.ExpectationsCompleteRepository;
import com.spring.jwt.service.PartnerExpectationService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PartnerExpectationServiceImpl implements PartnerExpectationService {

    @Autowired
    private ExpectationsCompleteRepository expectationsRepo;

    @Autowired
    private CompleteProfileRepository completeProfileRepo;

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

        ExpectationsComplete saved = expectationsRepo.save(entity);

        // Use CompleteProfile instead of ExpectationCompleteProfile
        CompleteProfile link =
                completeProfileRepo.findByUserId(userId)
                        .orElse(new CompleteProfile());

        link.setUserId(userId);
        link.setPartnerExpectationId(saved.getId());

        completeProfileRepo.save(link);

        return saved.getId();
    }

    @Override
    public ExpectationsComplete getExpectations(Long userId) {
        return expectationsRepo.findByUserId(userId);
    }

    @Override
    public List<ExpectationsComplete> getAllExpectations() {
        return expectationsRepo.findAll();
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
    public ExpectationsComplete patchExpectations(Long userId, PartnerExpectationDTO dto) {

        ExpectationsComplete existing = expectationsRepo.findByUserId(userId);

        if (existing == null) {
            throw new RuntimeException("No expectation data found");
        }

        if (dto.getAgeFrom() != null) existing.setAgeFrom(dto.getAgeFrom());
        if (dto.getAgeTo() != null) existing.setAgeTo(dto.getAgeTo());
        if (dto.getHeightFeet() != null) existing.setHeightFeet(dto.getHeightFeet());
        if (dto.getHeightInches() != null) existing.setHeightInches(dto.getHeightInches());
        if (dto.getLookingFor() != null) existing.setLookingFor(dto.getLookingFor());
        if (dto.getCaste() != null) existing.setCaste(dto.getCaste());
        if (dto.getEducation() != null) existing.setEducation(dto.getEducation());
        if (dto.getResidentStatus() != null) existing.setResidentStatus(dto.getResidentStatus());
        if (dto.getPreference() != null) existing.setPreference(dto.getPreference());
        if (dto.getCountry() != null) existing.setCountry(dto.getCountry());
        if (dto.getEatingHabits() != null) existing.setEatingHabits(dto.getEatingHabits());
        if (dto.getReligion() != null) existing.setReligion(dto.getReligion());
        if (dto.getComplexion() != null) existing.setComplexion(dto.getComplexion());

        return expectationsRepo.save(existing);
    }

    @Override
    public void deleteExpectations(Long userId) {

        ExpectationsComplete existing = expectationsRepo.findByUserId(userId);

        if (existing == null) {
            throw new RuntimeException("No expectation found for this user");
        }

        completeProfileRepo.findByUserId(userId)
                .ifPresent(link -> {
                    link.setPartnerExpectationId(null);
                    completeProfileRepo.save(link);
                });

        expectationsRepo.delete(existing);
    }
}
