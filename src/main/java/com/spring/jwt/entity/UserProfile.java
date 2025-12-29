package com.spring.jwt.entity;

import com.spring.jwt.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userProfile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userProfileId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String taluka;

    @Column(nullable = false)
    private String district;

    @Column(length = 6, nullable = false)
    private Integer pinCode;

    @Column(nullable = false)
    private Long mobileNumber;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String religion;

    @Column(nullable = false)
    private String caste;

    @Column(nullable = false)
    private String subCaste;

    @Column(nullable = false)
    private String maritalStatus;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private String bloodGroup;

    @Column(nullable = false)
    private String complexion;

    @Column(nullable = false)
    private String diet;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Boolean spectacle;

    @Column(nullable = false)
    private Boolean lens;

    @Column(nullable = false)
    private Boolean physicallyChallenged;

    @Column(nullable = false)
    private String homeTownDistrict;

    @Column(nullable = false)
    private String nativeTaluka;

    @Column(nullable = false)
    private String currentCity;

    @Column(nullable = false)
    private String userProfileCol;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}




//PinCode 6 digit
// Mobile no 10 digits must be starts with 6-9
//Height must be in decimal format
