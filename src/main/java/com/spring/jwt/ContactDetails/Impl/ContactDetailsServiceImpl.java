package com.spring.jwt.ContactDetails.Impl;

import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.User;

import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.ContactDetailsRepository;
import com.spring.jwt.ContactDetails.ContactDetailsService;
import com.spring.jwt.dto.ContactDetailsDTO;
import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class  ContactDetailsServiceImpl implements ContactDetailsService {

    @Autowired
    private ContactDetailsRepository contactDetailsRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CompleteProfileRepository completeProfileRepository;

    @Override
    public void createContactDetails(ContactDetailsDTO dto) {

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepo.findByEmail(email);

            ContactDetails contact=new ContactDetails();

            contact.setFullAddress(dto.getFullAddress());
            contact.setCity(dto.getCity());
            contact.setPinCode(dto.getPinCode());
            contact.setMoNumber(dto.getMoNumber());
            contact.setAlternateNumber(dto.getAlternateNumber());
            contact.setUser(user);
            contactDetailsRepo.save(contact);

            CompleteProfile cp=new CompleteProfile();
            cp.setContactNumberId(contactDetailsRepo.findByUserId(user.getId()).getContactNumberId());
            cp.setUserId(user.getId());
            completeProfileRepository.save(cp);

    }

    @Override
    public ContactDetails getContactDetils() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email);

        return contactDetailsRepo.findByUserId(user.getId());
    }
}
