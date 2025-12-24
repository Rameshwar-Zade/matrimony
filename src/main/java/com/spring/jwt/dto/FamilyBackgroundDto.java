package com.spring.jwt.dto;

import com.spring.jwt.entity.Status;
import lombok.Data;

@Data
public class FamilyBackgroundDto {

    private Integer familyBackgroundId;

    private String fatherName;

    private String motherName;

    private String fatherOccupation;

    private String motherOccupation;

    private String brother;

    private String marriedBrothers;

    private String sisters;

    private String marriedSisters;

    private Boolean interCasteInFamily;

    private String parentResiding;

    private String mamaSurname;

    private String mamaPlace;

    private String familyBackgroundCol;

    private String relativeSurnames;

   // private Integer userId;

   // private Integer statusId; // keep only id, not the entity

    public FamilyBackgroundDto() {
    }
}
