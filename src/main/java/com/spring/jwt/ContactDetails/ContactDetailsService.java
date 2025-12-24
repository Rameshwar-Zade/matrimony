package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.transaction.annotation.Transactional;

public interface ContactDetailsService {

    BaseResponseDTO createContactDetails(ContactDetailsDTO dto);

    ContactDetailsDTO getContactDetails();

    ContactDetailsDTO updateContact(ContactDetailsDTO dto);

    BaseResponseDTO deleteContactDetails();
}
