package com.spring.jwt.controller;

import com.spring.jwt.dto.UserDocumentsDto;
import com.spring.jwt.entity.UserDocuments;
import com.spring.jwt.mapper.UserDocumentsMapper;
import com.spring.jwt.service.UserDocumentsService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.DocumentType;

import java.io.IOException;

@RestController
@RequestMapping("/api/user-documents")
@RequiredArgsConstructor
public class UserDocumentsController {

    private final UserDocumentsService userDocumentsService;

    // ----------------- Upload PAN -----------------
    @PostMapping(value = "/upload/pan", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDocumentsDto> uploadPan(@RequestParam("file") MultipartFile file) {
        UserDocumentsDto dto = userDocumentsService.uploadPan(file);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/upload/aadhaar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDocumentsDto> uploadAadhaar(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(userDocumentsService.uploadAadhaar(file));
    }
    @PostMapping(value = "/upload/profilephoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDocumentsDto> uploadProfilePhoto(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(userDocumentsService.uploadProfilePhoto(file));
    }
    @PostMapping(value="/upload/salaryslip",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<UserDocumentsDto> uploadSalarySlip(@RequestParam("file")MultipartFile file){
        return ResponseEntity.ok(userDocumentsService.uploadSalarySlip(file));
    }

    @PostMapping(value = "/upload/biodata",consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDocumentsDto> uploadBiodata(@RequestParam("file")MultipartFile file){
        return ResponseEntity.ok(userDocumentsService.uploadBiodata(file));

    }
    @PostMapping(value="/upload/leavingcertificate",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDocumentsDto> uploadleavingCertificate(@RequestParam("file")MultipartFile file){
        return ResponseEntity.ok(userDocumentsService.uploadLeavingCertificate(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDocumentsDto> getUserDocumentsById(@PathVariable Integer id) {

        return ResponseEntity.ok(userDocumentsService.getUserDocumentsById(id));

}


}

