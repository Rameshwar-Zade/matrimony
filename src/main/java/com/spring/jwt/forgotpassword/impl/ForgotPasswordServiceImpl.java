package com.spring.jwt.forgotpassword.impl;


import com.spring.jwt.forgotpassword.ForgotPasswordService;
import com.spring.jwt.dto.ResetPasswordRequest;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.InvalidRequestException;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.forgotpassword.SmsService;
import com.spring.jwt.utils.EmailVerificationService.EmailVerification;
import com.spring.jwt.utils.EmailVerificationService.EmailVerificationRepo;
import com.spring.jwt.utils.EmailVerificationService.EmailVerificationService;
import com.spring.jwt.utils.EmailVerificationService.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final UserRepository userRepository;
    private final EmailVerificationService emailVerificationService;
    private final EmailVerificationRepo emailVerificationRepo;
    private final PasswordEncoder passwordEncoder;
    private final SmsService smsService;

    @Override
    public void sendOtp(String emailOrMobile) {

        if (emailOrMobile == null || emailOrMobile.isBlank()) {
            return; 
        }

        User user;

        if (emailOrMobile.contains("@")) {
            user = userRepository.findByEmail(emailOrMobile);
        } else {
            user = userRepository.findByMobileNumber(emailOrMobile).orElse(null);
        }

        if (user == null) {
            return;
        }

        String otp = OtpUtil.generateOtp(6);


        if (user.getEmail() != null) {
            emailVerificationService.sendForgotPasswordOtp(user.getEmail());
        }


    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {

        if (!"VALID".equals(request.getCaptchaToken())) {
            throw new InvalidRequestException("Invalid captcha");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new InvalidRequestException("Passwords do not match");
        }

        EmailVerification verification =
                emailVerificationRepo.findByEmail(request.getEmail())
                        .orElseThrow(() -> new InvalidRequestException("OTP verification required"));

        if (!EmailVerification.STATUS_VERIFIED.equals(verification.getStatus())) {
            throw new InvalidRequestException("OTP not verified");
        }

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new InvalidRequestException("Invalid request");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        emailVerificationRepo.delete(verification);
    }
}
