package com.spring.jwt.Contact_Us;

import lombok.Data;

@Data
public class ContactUsRequestDTO {

    private String fullName;
    private String email;
    private String mobileNumber;
    private String subject;
    private String message;
}
