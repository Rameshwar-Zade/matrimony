package com.spring.jwt.mapper;

import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.entity.ExpectationsComplete;
import org.springframework.stereotype.Component;

@Component
public class PartnerExpectationMapper {

    // DTO → Entity (CREATE)
    public ExpectationsComplete toEntity(PartnerExpectationDTO dto) {
        if (dto == null) return null;

        ExpectationsComplete e = new ExpectationsComplete();
        e.setAgeFrom(dto.getAgeFrom());
        e.setAgeTo(dto.getAgeTo());
        e.setHeightFeet(dto.getHeightFeet());
        e.setHeightInches(dto.getHeightInches());
        e.setLookingFor(dto.getLookingFor());
        e.setCaste(dto.getCaste());
        e.setEducation(dto.getEducation());
        e.setResidentStatus(dto.getResidentStatus());
        e.setPreference(dto.getPreference());
        e.setCountry(dto.getCountry());
        e.setEatingHabits(dto.getEatingHabits());
        e.setReligion(dto.getReligion());
        e.setComplexion(dto.getComplexion());
        return e;
    }


    public void updateEntity(ExpectationsComplete e,
                             PartnerExpectationDTO dto) {

        if (dto.getAgeFrom() != null)
            e.setAgeFrom(dto.getAgeFrom());

        if (dto.getAgeTo() != null)
            e.setAgeTo(dto.getAgeTo());

        if (dto.getHeightFeet() != null)
            e.setHeightFeet(dto.getHeightFeet());

        if (dto.getHeightInches() != null)
            e.setHeightInches(dto.getHeightInches());

        if (dto.getLookingFor() != null)
            e.setLookingFor(dto.getLookingFor());

        if (dto.getCaste() != null)
            e.setCaste(dto.getCaste());

        if (dto.getEducation() != null)
            e.setEducation(dto.getEducation());

        if (dto.getResidentStatus() != null)
            e.setResidentStatus(dto.getResidentStatus());

        if (dto.getPreference() != null)
            e.setPreference(dto.getPreference());

        if (dto.getCountry() != null)
            e.setCountry(dto.getCountry());

        if (dto.getEatingHabits() != null)
            e.setEatingHabits(dto.getEatingHabits());

        if (dto.getReligion() != null)
            e.setReligion(dto.getReligion());

        if (dto.getComplexion() != null)
            e.setComplexion(dto.getComplexion());
    }

    public static PartnerExpectationDTO toDTO(ExpectationsComplete pe) {
        PartnerExpectationDTO dto= new PartnerExpectationDTO();

        dto.setAgeFrom(pe.getAgeFrom());
        dto.setAgeTo(pe.getAgeTo());
        dto.setHeightFeet(pe.getHeightFeet());
        dto.setHeightInches(pe.getHeightInches());
        dto.setLookingFor(pe.getLookingFor());
        dto.setCaste(pe.getCaste());
        dto.setEducation(pe.getEducation());
        dto.setResidentStatus(pe.getResidentStatus());
        dto.setPreference(pe.getPreference());
        dto.setCountry(pe.getCountry());
        dto.setEatingHabits(pe.getEatingHabits());
        dto.setReligion(pe.getReligion());
        dto.setComplexion(pe.getComplexion());

        return dto;
    }
}

