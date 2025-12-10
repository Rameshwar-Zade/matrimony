package com.spring.jwt.utils;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CaptchaResponse {
    private String captchaId;
    private String captchaText;
}
