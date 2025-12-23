package com.spring.jwt.ContactDetails.Impl;

import com.spring.jwt.ContactDetails.ContactDetailsDTO;
import com.spring.jwt.ContactDetails.ContactDetailsMapper;
import com.spring.jwt.ContactDetails.ContactDetailsService;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.ContactDetailsRepository;
import com.spring.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ContactDetailsServiceImpl implements ContactDetailsService {

    @Autowired
    private ContactDetailsRepository contactDetailsRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CompleteProfileRepository completeProfileRepository;

    @Override
    public void createContactDetails(ContactDetailsDTO dto) {

        Integer userId = jwtService.extractUserId(jwtService.extractToken());

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        if (contactDetailsRepo.existsByUser_Id(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Contact details already exist"
            );
        }

        ContactDetails contact = ContactDetailsMapper.toEntity(dto);
        contact.setUser(user);
        contactDetailsRepo.save(contact);

        CompleteProfile cp = completeProfileRepository
                .findByUserId(userId)
                .orElseGet(() -> {
                    CompleteProfile newCP = new CompleteProfile();
                    newCP.setUserId(userId);
                    return newCP;
                });

        cp.setContactNumberId(contact.getContactNumberId());
        completeProfileRepository.save(cp);
    }

    @Override
    public ContactDetailsDTO getContactDetails() {

        Integer userId = jwtService.extractUserId(jwtService.extractToken());

        ContactDetails contact = contactDetailsRepo.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("Contact details not found"));

        return ContactDetailsMapper.toDTO(contact);
    }

    @Override
    public ContactDetailsDTO updateContact(ContactDetailsDTO dto) {

        Integer userId = jwtService.extractUserId(jwtService.extractToken());

        ContactDetails contact = contactDetailsRepo.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("Contact details not found"));

        ContactDetailsMapper.updateEntity(contact, dto);

        return ContactDetailsMapper.toDTO(
                contactDetailsRepo.save(contact)
        );
    }

    @Override
    public void deleteContactDetails() {

        Integer userId = jwtService.extractUserId(jwtService.extractToken());

        ContactDetails contact = contactDetailsRepo.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("Contact details not found"));

        contactDetailsRepo.delete(contact);
    }
}
