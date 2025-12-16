package com.spring.jwt.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "complete_profile")
@Getter
@Setter
public class CompleteProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Integer userId;

    @Column(nullable = true)
    private Integer horoscopeId;

    @Column(nullable = true)
    private Integer partnerExpectationId;

    @Column(name = "contact_number_id",nullable = true)
    private Integer contactNumberId;

    @Column(name = "user_profile_id",nullable = true)
    private Integer userProfileId;

    @Column(name = "education_id",nullable = true)
    private Integer educationId;

    @Column(name = "family_background_id",nullable = true)
    private Integer familyBackgroundId;

}

//package com.spring.jwt.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//
//@Entity
//@Data
//public class CompleteProfile {
//
//    @Id
//    @Column(nullable = false, updatable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer completeProfileId;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userProfileId")
//    private UserProfile userProfile;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "horoscopeDetailsId")
//    private HoroscopeDetails horoscopeDetails;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "educationId")
//    private EducationAndProfession educationAndProfession;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "familyBackgroundId")
//    private FamilyBackground familyBackground;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "expectationsId")
//    private PartnerPreference partnerPreference;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "contactNumberId")
//    private ContactDetails contactDetails;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//}
