package com.spring.jwt.service.impl;

import com.spring.jwt.dto.UserDocumentsDto;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserDocuments;
import com.spring.jwt.exception.ResourceNotFoundException;
import com.spring.jwt.mapper.UserDocumentsMapper;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.UserDocumentsRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.service.UserDocumentsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Transactional
@Service
public class UserDocumentsServiceImpl implements UserDocumentsService {

    private final UserDocumentsRepository userDocumentsRepository;

    private final UserRepository userRepository;

    private final CompleteProfileRepository completeProfileRepository;

    public UserDocumentsServiceImpl(UserDocumentsRepository userDocumentsRepository,
                                    UserRepository userRepository,
                                    CompleteProfileRepository completeProfileRepository) {
        this.userDocumentsRepository = userDocumentsRepository;
        this.userRepository = userRepository;
        this.completeProfileRepository = completeProfileRepository;
    }


    // 🔐 Get user from JWT
    private User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {

            throw new RuntimeException("User is not authenticated");
        }

        String email = authentication.getName();

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        return user;
    }


    @Override
    public UserDocumentsDto uploadAll(MultipartFile pan, MultipartFile aadhaar,
                                   MultipartFile profilePhoto, MultipartFile salarySlip,
                                   MultipartFile biodata,
                                   MultipartFile leavingCertificate) {

        User loggedInUser = getLoggedInUser();


        UserDocuments documents = userDocumentsRepository.findByUser(loggedInUser)
                .orElse(new UserDocuments());
        documents.setUser(loggedInUser);

        try{

        if (pan != null && !pan.isEmpty())
            documents.setPanCard(pan.getBytes());

        if (aadhaar != null && !aadhaar.isEmpty())
            documents.setAdhaarPhoto(aadhaar.getBytes());

        if (profilePhoto != null && !profilePhoto.isEmpty())
            documents.setProfilePhoto(profilePhoto.getBytes());

        if (salarySlip != null && !salarySlip.isEmpty())
            documents.setSalarySlip(salarySlip.getBytes());
        if (biodata != null && !biodata.isEmpty())
            documents.setBiodata(biodata.getBytes());
        if (leavingCertificate != null && !leavingCertificate.isEmpty())
            documents.setLeavingCertificate(leavingCertificate.getBytes());

    } catch (IOException e) {
        throw new RuntimeException("Error reading file data", e);
    }

    UserDocuments saved = userDocumentsRepository.save(documents);

        CompleteProfile cp=
                completeProfileRepository.findByUserId(loggedInUser.getId())
                        .orElseGet(() -> {
                            CompleteProfile newCp = new CompleteProfile();
                            newCp.setUserId(loggedInUser.getId());
                            return completeProfileRepository.save(newCp);
                        });

        cp.setUserDocuments(saved);

        // Save updated CompleteProfile
        completeProfileRepository.save(cp);

                            return UserDocumentsMapper.toDto(saved);

    }

    }










