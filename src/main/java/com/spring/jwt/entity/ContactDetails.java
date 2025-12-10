package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "contact_number")
@Getter
@Setter
public class ContactDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contactNumberId;

    @Column(nullable = false, length = 100)
    private String fullAddress;

    @Column(length = 45, nullable = false)
    private String city;

    @Column(length = 6, nullable = false)
    private Integer pinCode;
    //Pincode must be 6 digits and valid

    @Column(length = 10, nullable = false, unique = true)
    private Long mobileNumber;

    @Column(length = 10, nullable = false)
    private Long alternateNumber;

    @OneToOne(mappedBy = "contactNumber")
    private Status status;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



}