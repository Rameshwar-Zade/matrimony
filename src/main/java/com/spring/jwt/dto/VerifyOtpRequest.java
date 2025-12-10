package com.spring.jwt.dto;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    private String emailOrMobile;
    private String otp;
}
