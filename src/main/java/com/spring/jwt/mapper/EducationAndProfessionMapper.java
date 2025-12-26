package com.spring.jwt.mapper;

import com.spring.jwt.dto.EducationAndProfessionDto;
import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.entity.User;
import org.springframework.stereotype.Component;

@Component
public class EducationAndProfessionMapper {

    public static EducationAndProfessionDto toDto(EducationAndProfession entity) {

        EducationAndProfessionDto dto = new EducationAndProfessionDto();

        dto.setEducationAndProfessionalDetailsId(entity.getEducationAndProfessionalDetailsId());
        dto.setEducation(entity.getEducation());
        dto.setEducationDetails(entity.getEducationDetails());
        dto.setOccupation(entity.getOccupation());
        dto.setOccupationDetails(entity.getOccupationDetails());
        dto.setIncomePerYear(entity.getIncomePerYear());
        dto.setStatus1(entity.getStatus1());
        dto.setEducationAndProfessionalDetailsCol(entity.getEducationAndProfessionalDetailsCol());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }

    public static EducationAndProfession toEntity(EducationAndProfessionDto dto,User user) {

        EducationAndProfession entity = new EducationAndProfession();

        if (dto.getEducationAndProfessionalDetailsId() != null) {
            entity.setEducationAndProfessionalDetailsId(dto.getEducationAndProfessionalDetailsId());
        }

        entity.setEducation(dto.getEducation());
        entity.setEducationDetails(dto.getEducationDetails());
        entity.setOccupation(dto.getOccupation());
        entity.setOccupationDetails(dto.getOccupationDetails());
        entity.setIncomePerYear(dto.getIncomePerYear());
        entity.setStatus1(dto.getStatus1());
        entity.setEducationAndProfessionalDetailsCol(dto.getEducationAndProfessionalDetailsCol());
        entity.setUser(user);

        return entity;

    }
}



