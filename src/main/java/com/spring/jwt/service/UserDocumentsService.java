package com.spring.jwt.service;

import com.spring.jwt.dto.UserDocumentsDto;
import com.spring.jwt.entity.UserDocuments;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.DocumentType;

@Service
public interface UserDocumentsService {

    UserDocumentsDto uploadPan(MultipartFile file);

    UserDocumentsDto uploadAadhaar(MultipartFile file);

    UserDocumentsDto uploadProfilePhoto(MultipartFile file);

    UserDocumentsDto uploadSalarySlip(MultipartFile file);

    UserDocumentsDto uploadBiodata(MultipartFile file);

    UserDocumentsDto uploadLeavingCertificate(MultipartFile file);

    UserDocumentsDto getUserDocumentsById(Integer id);
}


