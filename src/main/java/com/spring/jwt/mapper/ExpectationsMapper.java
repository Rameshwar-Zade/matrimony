package com.spring.jwt.mapper;

import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.entity.ExpectationsComplete;

public class ExpectationsMapper {

    public static ExpectationsComplete toEntity(PartnerExpectationDTO dto, Long userId) {

        ExpectationsComplete e = new ExpectationsComplete();

        e.setUserId(userId);
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
}
