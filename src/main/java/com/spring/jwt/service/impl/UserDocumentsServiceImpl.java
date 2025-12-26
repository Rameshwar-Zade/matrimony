package com.spring.jwt.service.impl;

import com.spring.jwt.dto.UserDocumentsDto;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.FamilyBackground;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserDocuments;
import com.spring.jwt.exception.ProfileNotFoundException;
import com.spring.jwt.exception.ResourceNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.mapper.FamilyBackgroundMapper;
import com.spring.jwt.mapper.UserDocumentsMapper;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.UserDocumentsRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.service.UserDocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.spring.jwt.jwt.JwtService;

import java.io.IOException;

@Transactional
@Service
public class UserDocumentsServiceImpl implements UserDocumentsService {

    private final UserDocumentsRepository userDocumentsRepository;

    private final UserRepository userRepository;

    private final CompleteProfileRepository completeProfileRepository;

    @Autowired
    private JwtService jwtService;

    public UserDocumentsServiceImpl(UserDocumentsRepository userDocumentsRepository,
                                    UserRepository userRepository,
                                    CompleteProfileRepository completeProfileRepository) {
        this.userDocumentsRepository = userDocumentsRepository;
        this.userRepository = userRepository;
        this.completeProfileRepository = completeProfileRepository;
    }
    @Override
    public UserDocumentsDto uploadAll(MultipartFile pan, MultipartFile aadhaar,
                                   MultipartFile profilePhoto, MultipartFile salarySlip,
                                   MultipartFile biodata,
                                   MultipartFile leavingCertificate) {

        String token = jwtService.extractToken();
        Integer userId = jwtService.extractUserId(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        if (userDocumentsRepository.existsByUser(user))
        {
            throw new ProfileNotFoundException(
                    "User documents already exist"
            );
        }
        UserDocuments documents = new UserDocuments();
        documents.setUser(user);


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
                completeProfileRepository.findByUserId(userId)
                        .orElseGet(() -> {
                            CompleteProfile newCp = new CompleteProfile();
                            newCp.setUserId(userId);
                            return completeProfileRepository.save(newCp);
                        });

        cp.setUserDocuments(saved);

        // Save updated CompleteProfile
        completeProfileRepository.save(cp);

                            return UserDocumentsMapper.toDto(saved);

    }

    // 🔐 Get user from JWT
    public UserDocumentsDto getLoggedInUser() {


        // Get logged-in username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch user
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }

        // Fetch by user
        UserDocuments entity = userDocumentsRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "UserDocuments not found for user: " + username));

        return UserDocumentsMapper.toDto(entity);
    }

    }










