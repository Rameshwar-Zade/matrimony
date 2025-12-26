package com.spring.jwt.ContactDetails.Impl;

import com.spring.jwt.ContactDetails.ContactDetailsDTO;
import com.spring.jwt.ContactDetails.ContactDetailsMapper;
import com.spring.jwt.ContactDetails.ContactDetailsService;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.ContactDetailsAlreadyExistsException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.ContactDetailsRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public BaseResponseDTO createContactDetails(ContactDetailsDTO dto) {

        Integer userId = jwtService.extractUserId(jwtService.extractToken());

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        if (contactDetailsRepo.existsByUser_Id(userId)) {
            throw new ContactDetailsAlreadyExistsException("Contact Details already exists for this user");
        }

        if(contactDetailsRepo.existsByMobileNumber(dto.getMobileNumber())){
            throw new ContactDetailsAlreadyExistsException("This Mobile Number is already used by Another user");
        }

        ContactDetails contact = new ContactDetails();
        contact.setFullAddress(dto.getFullAddress());
        contact.setCity(dto.getCity());
        contact.setPinCode(dto.getPinCode());
        contact.setMobileNumber(dto.getMobileNumber());
        contact.setAlternateNumber(dto.getAlternateNumber());
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
        BaseResponseDTO response=new BaseResponseDTO();
        response.setUserID(userId);
        response.setCode("200");
        response.setMessage("Contact Details Added successfully");

        return response;
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

        if(contactDetailsRepo.existsByMobileNumber(dto.getMobileNumber())){
            throw new ContactDetailsAlreadyExistsException("This Mobile Number is already used by Another user");
        }
        ContactDetailsMapper.updateEntity(contact, dto);

        return ContactDetailsMapper.toDTO(
                contactDetailsRepo.save(contact)
        );
    }

    @Override
    public BaseResponseDTO deleteContactDetails() {

        Integer userId = jwtService.extractUserId(jwtService.extractToken());

        ContactDetails contact = contactDetailsRepo.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("Contact details not found"));

        contactDetailsRepo.delete(contact);
        BaseResponseDTO response=new BaseResponseDTO();
        response.setUserID(userId);
        response.setCode("200");
        response.setMessage("Contact Details Deleted successfully");

        return response;
    }
}
