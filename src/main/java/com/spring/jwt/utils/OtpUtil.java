package com.spring.jwt.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class OtpUtil {

    // Generate numeric OTP
    public static String generateOtp(int digits) {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < digits; i++) {
            otp.append(random.nextInt(10));  // 0-9
        }
        return otp.toString();
    }

    // Generate random salt
    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hash OTP + salt
    public static String hashOtp(String otp, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String combined = otp + salt;
            byte[] encoded = digest.digest(combined.getBytes());
            return Base64.getEncoder().encodeToString(encoded);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing OTP", e);
        }
    }

    // Verify OTP
    public static boolean verifyOtp(String otp, String hashedOtp, String salt) {
        return hashOtp(otp, salt).equals(hashedOtp);
    }

    public static String generateCaptchaText(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }

}
