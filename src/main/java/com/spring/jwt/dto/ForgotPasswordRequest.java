package com.spring.jwt.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class ForgotPasswordRequest {

    @NotBlank(message = "Email or mobile number is required")
    @Pattern(
            regexp = "^(\\+?[0-9]{10,12}|[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,})$",
            message = "Enter a valid email or mobile number"
    )
    private String emailOrMobile;
}
