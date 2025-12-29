package com.spring.jwt.userprofile;

import com.spring.jwt.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserProfileDto {

    private Integer userProfileId;

    @NotBlank(message = "Please Enter Firstname")
    @Pattern(regexp = "^[A-Za-z\\s'\\-]+$", message = "only letters Allowed")
    private String firstName;

    @NotBlank(message = "Please Enter Middlename")
    @Pattern(regexp = "^[A-Za-z\\s'\\-]+$", message = "only letters Allowed")
    private String middleName;

    @NotBlank(message = "Please Enter lastName")
    @Pattern(regexp = "^[A-Za-z\\s'\\-]+$", message = "only letters Allowed")
    private String lastName;

    @NotBlank(message = "Please Enter address")
    private String address;

    @NotBlank(message = "Please Enter taluka")
    @Pattern(regexp = "^[A-Za-z\\s'\\-]+$", message = "only letters Allowed")
    private String taluka;

    @NotBlank(message = "Please Enter district")
    @Pattern(regexp = "^[A-Za-z\\s'\\-]+$", message = "only letters Allowed")
    private String district;

    @NotNull(message = "Please Enter pinCode")
    private Integer pinCode;

    @NotNull(message = "Please Enter mobileNumber")
    private Long mobileNumber;

    @NotBlank(message = "Please Enter mail")
    @Email(message = "must be a valid email address")
    private String mail;

    @NotBlank(message = "status is required")
    private String status;

    @NotNull(message = "Please select Gender")
    private Gender gender;

    @NotBlank(message = "Please Enter religion")
    private String religion;

    @NotBlank(message = "Please Enter caste")
    @Pattern(regexp = "^[A-Za-z\\s'\\-]+$", message = "Enter Valid Caste")
    private String caste;

    @NotBlank(message = "Enter Valid Sub-Caste")
    private String subCaste;

    @NotBlank(message = "Please Choose Option.")
    private String maritalStatus;

    @NotNull(message = "Please Enter height")
    private Integer height;

    @NotNull(message = "Please Enter weight")
    private Integer weight;

    @NotBlank(message = " Please Enter bloodGroup")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Enter Valid Blood Group")
    private String bloodGroup;

    @NotBlank(message = "Please Enter complexion")
    private String complexion;

    @NotBlank(message = "Please Select diet")
    private String diet;

    @NotNull(message = "Please Select spectacle")
    private Boolean spectacle;

    @NotNull(message = "Please Select lens")
    private Boolean lens;

    @NotNull(message = "Please Select physicallyChallenged")
    private Boolean physicallyChallenged;

    @NotBlank(message = "homeTownDistrict is required")
    private String homeTownDistrict;

    @NotBlank(message = "nativeTaluka is required")
    private String nativeTaluka;

    @NotBlank(message = "currentCity is required")
    private String currentCity;

    @NotBlank(message = "userProfileCol")
    private String userProfileCol;




    @AssertTrue(message = "Mobile number must be 10 digits and start with 6-9")
    public boolean isMobileValid() {
        if (mobileNumber == null) return false;
        String number = String.valueOf(mobileNumber);
        return number.matches("^[6-9]\\d{9}$");
    }

}
