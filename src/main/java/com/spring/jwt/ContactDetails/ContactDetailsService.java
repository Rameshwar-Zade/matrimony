package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;
import org.springframework.transaction.annotation.Transactional;

public interface ContactDetailsService {

    void createContactDetails(ContactDetailsDTO dto);

    ContactDetailsDTO getContactDetails();

    ContactDetailsDTO updateContact(ContactDetailsDTO dto);

    void deleteContactDetails();
}
