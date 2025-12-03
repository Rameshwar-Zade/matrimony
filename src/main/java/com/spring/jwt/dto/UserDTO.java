package com.spring.jwt.dto;

import com.spring.jwt.entity.Role;
import com.spring.jwt.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Schema(description = "userId of User", example = "10011")
    private Integer userId;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format"
    )
    private String email;

    @Pattern(
            regexp = "^[6-9][0-9]{9}$",
            message = "Mobile number must start with 6-9 and be exactly 10 digits"
    )
    private String mobileNumber;

    @Pattern(
            regexp = "Male|Female",
            message = "Gender must be Male or Female"
    )
    private String gender;

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must be at least 8 characters long, include 1 uppercase, 1 number, and 1 special character"
    )
    private String password;


    @Pattern(
            regexp = "SON|DAUGHTER|SISTER|RELATIVE_FRIEND|CLIENT_MARRIAGE_BUREAU",
            message = "Invalid profile. Valid values: SON, DAUGHTER, SISTER, RELATIVE_FRIEND, CLIENT_MARRIAGE_BUREAU"
    )
    private String profile;


    private Set<String> roles;
//    @Schema(
//    description = "Address of the customer", example = "A/P Pune Main Street Block no 8"
//    )
//    private String address;

//    @Schema(
//            description = "First Name of the customer", example = "John"
//    )
//    private String firstName;
//
//    @Schema(
//            description = "Last Name of the customer", example = "Doe"
//    )
//    private String lastName;

    @Pattern(
            regexp = "USER|ADMIN",
            message = "Invalid role. Valid options: USER, ADMIN"
    )
    private String role;


    public UserDTO(User user) {
        this.email = user.getEmail();
//        this.address = user.getAddress();
//        this.firstName = user.getFirstName();
//        this.lastName = user.getLastName();
        this.mobileNumber = user.getMobileNumber();
        this.gender = user.getGender();
        this.userId = user.getId();

        if (user.getRoles() != null) {
            this.roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        }
    }

    // Static method to create DTO from User entity
    public static UserDTO fromEntity(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setMobileNumber(user.getMobileNumber());
//        dto.setFirstName(user.getFirstName());
//        dto.setLastName(user.getLastName());
//        dto.setAddress(user.getAddress());
        dto.setUserId(user.getId());
        dto.setGender(user.getGender());

        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
        }

        return dto;
    }
}
