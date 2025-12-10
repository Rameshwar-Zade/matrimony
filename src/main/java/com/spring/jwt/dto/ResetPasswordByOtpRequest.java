package com.spring.jwt.dto;

import lombok.Data;

@Data
public class ResetPasswordByOtpRequest {
    private String mobile;
    private String tempToken;
    private String newPassword;
    private String confirmPassword;
    private String captchaId;
    private String captchaText;
}
