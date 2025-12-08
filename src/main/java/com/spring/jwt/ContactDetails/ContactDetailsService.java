package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;

public interface ContactDetailsService {

    void createContactDetails(ContactDetailsDTO dto);

    ContactDetails getContactDetils();
}
