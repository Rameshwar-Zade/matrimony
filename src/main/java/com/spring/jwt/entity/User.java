package com.spring.jwt.entity;

import com.spring.jwt.utils.StringEncryptConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1, initialValue = 10000)
    @Column(name = "user_id")
    private Integer id;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format"
    )
    @Column(nullable = false, unique = true, length = 250)
    private String email;

    @Pattern(
            regexp = "^[6-9][0-9]{9}$",
            message = "Mobile number must start with 6-9 and be exactly 10 digits"
    )
    @Column(name = "mobile_no", nullable = false)
    private String mobileNumber;;


    @NotBlank(message = "Password cannot be empty")
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile", nullable = false)
    private ProfileType profile;


    public enum ProfileType {

        SON("Son"),
        DAUGHTER("Daughter"),
        SISTER("Sister"),
        RELATIVE_FRIEND("Relative/Friend"),
        CLIENT_MARRIAGE_BUREAU("Client-Marriage Bureau");

        public final String display;

        ProfileType(String display){
            this.display = display;
        }

        public static ProfileType fromDisplay(String value){
            return Arrays.stream(values())
                    .filter(p -> p.display.equalsIgnoreCase(value))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid profile name: " + value));
        }
    }





    @Pattern(regexp = "Male|Female", message = "Gender must be Male or Female")
    @Column(nullable = false)
    private String gender;

    @Column(name = "Status")
    private Boolean status;


    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "reset_password_token_expiry")
    private LocalDateTime resetPasswordTokenExpiry;
    
//    @Column(name = "device_fingerprint", length = 1024)
//    private String deviceFingerprint;
    
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    
    @Column(name = "login_attempts")
    private Integer loginAttempts = 0;
    
    @Column(name = "account_locked_until")
    private LocalDateTime accountLockedUntil;

    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles;


}
