package com.spring.jwt.controller;

import com.spring.jwt.dto.UserDocumentsDto;
import com.spring.jwt.entity.UserDocuments;
import com.spring.jwt.service.UserDocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user-documents")
@RequiredArgsConstructor
public class UserDocumentsController {

    private final UserDocumentsService userDocumentsService;



    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<UserDocumentsDto> UploadAllDocuments(
            @RequestParam(required = false) MultipartFile pan,
            @RequestParam(required = false) MultipartFile aadhaar,
            @RequestParam(required = false) MultipartFile profilePhoto,
            @RequestParam(required = false) MultipartFile salarySlip,
            @RequestParam(required = false) MultipartFile biodata,
            @RequestParam(required = false) MultipartFile leavingCertificate) {
        return ResponseEntity.ok(userDocumentsService.uploadAll(
                pan,
                aadhaar,
                profilePhoto,
                salarySlip,
                biodata,
                leavingCertificate
                )
        );
    }

}



