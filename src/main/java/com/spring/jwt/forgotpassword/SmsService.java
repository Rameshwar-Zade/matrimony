package com.spring.jwt.forgotpassword;


public interface SmsService {
    void sendOtp(String mobile, String otp);
}
