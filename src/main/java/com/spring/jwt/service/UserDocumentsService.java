package com.spring.jwt.service;

import com.spring.jwt.dto.UserDocumentsDto;
import com.spring.jwt.entity.UserDocuments;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface UserDocumentsService {

//    UserDocumentsDto uploadPan(MultipartFile file);
//
//    UserDocumentsDto uploadAadhaar(MultipartFile file);
//
//    UserDocumentsDto uploadProfilePhoto(MultipartFile file);
//
//    UserDocumentsDto uploadSalarySlip(MultipartFile file);
//
//    UserDocumentsDto uploadBiodata(MultipartFile file);
//
//    UserDocumentsDto uploadLeavingCertificate(MultipartFile file);

   // UserDocumentsDto getUserDocumentsById(Integer id);

    UserDocumentsDto uploadAll(MultipartFile pan, MultipartFile aadhaar,
                     MultipartFile profilePhoto, MultipartFile salarySlip,
                     MultipartFile biodata, MultipartFile leavingCertificate);

    UserDocumentsDto getLoggedInUser();



}


