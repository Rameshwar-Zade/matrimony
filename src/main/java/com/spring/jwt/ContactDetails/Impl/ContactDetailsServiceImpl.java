package com.spring.jwt.ContactDetails.Impl;

import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.User;

import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.ContactDetailsRepository;
import com.spring.jwt.ContactDetails.ContactDetailsService;
import com.spring.jwt.ContactDetails.ContactDetailsDTO;
import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
        if (user == null) {
            throw new UserNotFoundExceptions("Authenticated user not found", HttpStatus.UNAUTHORIZED);
        }

        ContactDetails existing = contactDetailsRepo.findByUserId(user.getId());
        if (existing != null) {
           throw new ResponseStatusException(HttpStatus.CONFLICT, "Contact details already exist for this user");
        }
            ContactDetails contact=new ContactDetails();

            contact.setFullAddress(dto.getFullAddress());
            contact.setCity(dto.getCity());
            contact.setPinCode(dto.getPinCode());
            contact.setMobileNumber(dto.getMobileNumber());
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

    @Transactional
    @Override
    public void deleteByUserID(Integer userID) {
        ContactDetails contact = contactDetailsRepo.findByUser_Id(userID)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No contact details found"));

        contactDetailsRepo.deleteByUserId(userID);

    }

    @Override
    public ContactDetails updateContact(Integer userId, ContactDetailsDTO dto) {

        ContactDetails contact=contactDetailsRepo.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("No contact details found"));

        if (dto.getFullAddress() != null) {
            contact.setFullAddress(dto.getFullAddress());
        }
        if (dto.getCity() != null) {
            contact.setCity(dto.getCity());
        }
        if (dto.getPinCode() != null) {
            contact.setPinCode(dto.getPinCode());
        }
        if (dto.getMobileNumber() != null) {
            contact.setMobileNumber(dto.getMobileNumber());
        }
        if (dto.getAlternateNumber() != null) {
            contact.setAlternateNumber(dto.getAlternateNumber());
        }

        return contactDetailsRepo.save(contact);
    }
}
