package com.spring.jwt.dto;

import lombok.Data;

@Data
public class UserDocumentsDto {
    private Integer id;
    private Integer userId;

    private boolean panCard;
    private boolean adhaarPhoto;
    private boolean profilePhoto;
    private boolean salarySlip;
    private boolean biodata;
    private boolean leavingCertificate;


}
