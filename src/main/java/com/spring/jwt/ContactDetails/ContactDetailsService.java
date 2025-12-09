package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;
import org.springframework.transaction.annotation.Transactional;

public interface ContactDetailsService {

    void createContactDetails(ContactDetailsDTO dto);

    ContactDetails getContactDetils();

    @Transactional
    void deleteByUserID(Integer userID);

    ContactDetails updateContact(Integer userId, ContactDetailsDTO dto);
}
