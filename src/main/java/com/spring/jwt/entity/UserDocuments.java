package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "user_documents")
@Data
public class UserDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

   // private Integer userId;



    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte [] panCard;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profilePhoto;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] biodata;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] salarySlip;


    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] leavingCertificate;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] adhaarPhoto;

}

