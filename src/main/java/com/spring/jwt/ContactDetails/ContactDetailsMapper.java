package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;

public class ContactDetailsMapper {

    public static ContactDetailsDTO toDTO(ContactDetails cd) {

        ContactDetailsDTO dto = new ContactDetailsDTO();
        dto.setFullAddress(cd.getFullAddress());
        dto.setCity(cd.getCity());
        dto.setPinCode(cd.getPinCode());
        dto.setMobileNumber(cd.getMobileNumber());
        dto.setAlternateNumber(cd.getAlternateNumber());

        return dto;
    }

    public static ContactDetails toEntity(ContactDetailsDTO dto) {

        ContactDetails cd = new ContactDetails();
        cd.setFullAddress(dto.getFullAddress());
        cd.setCity(dto.getCity());
        cd.setPinCode(dto.getPinCode());
        cd.setMobileNumber(dto.getMobileNumber());
        cd.setAlternateNumber(dto.getAlternateNumber());

        return cd;
    }

    public static void updateEntity(ContactDetails cd, ContactDetailsDTO dto) {

        if (dto.getFullAddress() != null)
            cd.setFullAddress(dto.getFullAddress());

        if (dto.getCity() != null)
            cd.setCity(dto.getCity());

        if (dto.getPinCode() != null)
            cd.setPinCode(dto.getPinCode());

        if (dto.getMobileNumber() != null)
            cd.setMobileNumber(dto.getMobileNumber());

        if (dto.getAlternateNumber() != null)
            cd.setAlternateNumber(dto.getAlternateNumber());
    }
}
