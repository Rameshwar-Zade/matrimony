package com.spring.jwt.dto;

import lombok.Data;

@Data
public class ForgotOtpRequest {
    private String emailOrMobile;
}

