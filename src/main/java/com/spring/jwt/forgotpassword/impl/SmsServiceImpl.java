package com.spring.jwt.forgotpassword.impl;

import com.spring.jwt.forgotpassword.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public void sendOtp(String mobile, String otp) {
        // Fast2SMS / Twilio
        log.info("Sending OTP {} to mobile {}", otp, mobile);
    }
}
