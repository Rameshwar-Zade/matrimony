package com.spring.jwt.mapper;

import com.spring.jwt.dto.UserDocumentsDto;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserDocuments;

public class UserDocumentsMapper {

    private UserDocumentsMapper() { }

    public static UserDocumentsDto toDto(UserDocuments entity) {
        if (entity == null) return null;

        UserDocumentsDto dto = new UserDocumentsDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());

        // Convert byte[] to boolean for response

        dto.setPanCard(entity.getPanCard() != null);
        dto.setAdhaarPhoto(entity.getAdhaarPhoto() != null);
        dto.setProfilePhoto(entity.getProfilePhoto() != null);
        dto.setSalarySlip(entity.getSalarySlip() != null);
        dto.setBiodata(entity.getBiodata() != null);
        dto.setLeavingCertificate(entity.getLeavingCertificate() != null);

        return dto;
    }

    public static UserDocuments createEntity(com.spring.jwt.entity.User user) {
        UserDocuments entity = new UserDocuments();
        entity.setUser(user);
        return entity;
    }
}



