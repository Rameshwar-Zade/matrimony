package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;

public class ContactDetailsMapper {
    public static ContactDetailsDTO toDTO(ContactDetails cd) {

        ContactDetailsDTO dto=new ContactDetailsDTO();

        dto.setFullAddress(cd.getFullAddress());
        dto.setCity(cd.getCity());
        dto.setPinCode(cd.getPinCode());
        dto.setMobileNumber(cd.getMobileNumber());
        dto.setAlternateNumber(cd.getAlternateNumber());

        return dto;
    }
}
