package com.spring.jwt.forgotpassword;

import com.spring.jwt.dto.ForgotPasswordRequest;
import com.spring.jwt.dto.ResetPasswordRequest;
import com.spring.jwt.utils.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDto> forgotPassword(
          @Valid @RequestBody ForgotPasswordRequest request) {

        forgotPasswordService.sendOtp(request.getEmailOrMobile());

        ResponseDto response = new ResponseDto();
        response.setMessage(" OTP has been sent");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDto> resetPassword(
            @RequestBody ResetPasswordRequest request) {

        forgotPasswordService.resetPassword(request);

        ResponseDto response = new ResponseDto();
        response.setMessage("Password reset successfully");
        return ResponseEntity.ok(response);
    }
}
