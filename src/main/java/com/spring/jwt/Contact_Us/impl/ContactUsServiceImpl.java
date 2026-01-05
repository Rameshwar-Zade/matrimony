package com.spring.jwt.Contact_Us.impl;

import com.spring.jwt.Contact_Us.ContactUsMessage;
import com.spring.jwt.Contact_Us.ContactUsRepository;
import com.spring.jwt.Contact_Us.ContactUsRequestDTO;
import com.spring.jwt.Contact_Us.ContactUsService;
import com.spring.jwt.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Override
    public void submitMessage(ContactUsRequestDTO request) {

        if (request == null) {
            throw new BaseException(
                    String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "Request body cannot be null"
            );
        }

        if (request.getFullName() == null || request.getEmail() == null
                || request.getPhoneNumber() == null
                || request.getSubject() == null) {

            throw new BaseException(
                    String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "Required fields are missing"
            );
        }

        ContactUsMessage message = new ContactUsMessage();
        message.setFullName(request.getFullName());
        message.setEmail(request.getEmail());
        message.setPhoneNumber(request.getPhoneNumber());
        message.setSubject(request.getSubject());
        message.setMessage(request.getMessage());
        message.setCreatedAt(LocalDateTime.now());

        contactUsRepository.save(message);
    }
}
