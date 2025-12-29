package com.spring.jwt.forgotpassword;

import com.spring.jwt.dto.ResetPasswordRequest;

public interface ForgotPasswordService {

    void sendOtp(String emailOrMobile);

    void resetPassword(ResetPasswordRequest request);
}
