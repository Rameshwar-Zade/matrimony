package com.spring.jwt.service.impl;

import com.spring.jwt.dto.UserDocumentsDto;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserDocuments;
import com.spring.jwt.exception.ResourceNotFoundException;
import com.spring.jwt.mapper.UserDocumentsMapper;
import com.spring.jwt.repository.UserDocumentsRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.service.UserDocumentsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.DocumentType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class UserDocumentsServiceImpl implements UserDocumentsService {

    private final UserDocumentsRepository repository;

    private final UserRepository userRepository;

    public UserDocumentsServiceImpl(UserDocumentsRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    private final String UPLOAD_BASE_DIR = "uploads";

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

    public UserDocumentsDto uploadPan(MultipartFile file) {
        return uploadDocument(file, "pan");
    }

    public UserDocumentsDto uploadAadhaar(MultipartFile file) {
        return uploadDocument(file, "Aadhaar");
    }

    @Override
    public UserDocumentsDto uploadProfilePhoto(MultipartFile file) {
        return uploadDocument(file, "profilephoto");
    }

    @Override
    public UserDocumentsDto uploadSalarySlip(MultipartFile file) {
        return uploadDocument(file, "salaryslip");
    }

    @Override
    public UserDocumentsDto uploadBiodata(MultipartFile file) {
        return uploadDocument(file, "biodata");
    }

    @Override
    public UserDocumentsDto uploadLeavingCertificate(MultipartFile file) {
        return uploadDocument(file, "leavingcertificate");
    }

    private UserDocumentsDto uploadDocument(MultipartFile file, String documentType) {
        User user = getLoggedInUser();

        // ONE-TO-ONE: get existing or create
        UserDocuments documents = repository.findByUser(user)
                .orElseGet(() -> {
                    UserDocuments d = new UserDocuments();
                    d.setUser(user);
                    return d;
                });

        try {
            byte[] data = file.getBytes(); // ✅ convert to byte[]

            switch (documentType.toLowerCase()) {
                case "pan" -> documents.setPanCard(data);
                case "aadhaar" -> documents.setAdhaarPhoto(data);
                case "profilephoto" -> documents.setProfilePhoto(data);
                case "salaryslip" -> documents.setSalarySlip(data);
                case "biodata" -> documents.setBiodata(data);
                case "leavingcertificate" -> documents.setLeavingCertificate(data);
                default -> throw new IllegalArgumentException("Invalid document type");
            }

            repository.save(documents);
            return UserDocumentsMapper.toDto(documents);

        } catch (IOException e) {
            throw new RuntimeException("File upload failed for " + documentType, e);
        }

    }



    @Override
    public UserDocumentsDto getUserDocumentsById(Integer id) {

        UserDocuments documents=repository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Documents not found with Id "+ id));

        return UserDocumentsMapper.toDto(documents);
    }
}

