package com.spring.jwt.mapper;

import com.spring.jwt.dto.EducationAndProfessionDto;
import com.spring.jwt.entity.EducationAndProfession;
import org.springframework.stereotype.Component;

@Component
public class EducationAndProfessionMapper {

    public static EducationAndProfessionDto toDto(EducationAndProfession entity) {
        if (entity == null) return null;

        EducationAndProfessionDto dto = new EducationAndProfessionDto();

        dto.setEducationAndProfessionalDetailsId(entity.getEducationAndProfessionalDetailsId());
        dto.setEducation(entity.getEducation());
        dto.setDegree(entity.getDegree());
        dto.setOccupation(entity.getOccupation());
        dto.setOccupationDetails(entity.getOccupationDetails());
        dto.setIncomePerYear(entity.getIncomePerYear());
        dto.setStatus(entity.getStatus1());
        dto.setEducationAndProfessionalDetailsCol(entity.getEducationAndProfessionalDetailsCol());
        dto.setUserId(entity.getUser().getId());

        return dto;
    }

    public static EducationAndProfession toEntity(EducationAndProfessionDto dto) {
        if (dto == null) return null;

        EducationAndProfession entity = new EducationAndProfession();

        if (dto.getEducationAndProfessionalDetailsId() != null) {
            entity.setEducationAndProfessionalDetailsId(dto.getEducationAndProfessionalDetailsId());
        }

        entity.setEducation(dto.getEducation());
        entity.setDegree(dto.getDegree());
        entity.setOccupation(dto.getOccupation());
        entity.setOccupationDetails(dto.getOccupationDetails());
        entity.setIncomePerYear(dto.getIncomePerYear());
        entity.setStatus1(dto.getStatus());
        entity.setEducationAndProfessionalDetailsCol(dto.getEducationAndProfessionalDetailsCol());

        return entity;

    }
}



