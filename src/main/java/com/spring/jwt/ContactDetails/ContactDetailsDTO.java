package com.spring.jwt.ContactDetails;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetailsDTO {

    @NotBlank(message = "Full address cannot be empty")
    private String fullAddress;

    @NotBlank(message = "City cannot be empty")
    private String city;


    @NotNull(message = "Pin code cannot be null")
    @Min(value = 100000, message = "Pin code must be 6 digits")
    @Max(value = 999999, message = "Pin code must be 6 digits")
    private Integer pinCode;


    @NotNull(message = "Mobile number cannot be null")
    private Long mobileNumber;


    private Long alternateNumber;



    @AssertTrue(message = "Invalid mobile number: must be 10 digits and start with 6-9")
    public boolean isMobileNumberValid() {
        if (mobileNumber == null) return false;
        return String.valueOf(mobileNumber).matches("^[6-9]\\d{9}$");
    }

    @AssertTrue(message = "Invalid alternate number: must be 10 digits and start with 6-9")
    public boolean isAlternateNumberValid() {
        if (alternateNumber == null) return true; // optional field
        return String.valueOf(alternateNumber).matches("^[6-9]\\d{9}$");
    }
}
