package com.spring.jwt.mapper;

import com.spring.jwt.dto.FamilyBackgroundDto;
import com.spring.jwt.entity.FamilyBackground;
import org.springframework.stereotype.Component;

public class FamilyBackgroundMapper {

    public static FamilyBackgroundDto toDto(FamilyBackground entity) {
        if (entity == null) return null;

        FamilyBackgroundDto familyBackgroundDto = new FamilyBackgroundDto();

        familyBackgroundDto.setFamilyBackgroundId(entity.getFamilyBackgroundId());
        familyBackgroundDto.setFatherName(entity.getFatherName());
        familyBackgroundDto.setMotherName(entity.getMotherName());
        familyBackgroundDto.setFatherOccupation(entity.getFatherOccupation());
        familyBackgroundDto.setMotherOccupation(entity.getMotherOccupation());
        familyBackgroundDto.setBrother(entity.getBrother());
        familyBackgroundDto.setMarriedBrothers(entity.getMarriedBrothers());
        familyBackgroundDto.setSisters(entity.getSisters());
        familyBackgroundDto.setMarriedSisters(entity.getMarriedSisters());
        familyBackgroundDto.setInterCasteInFamily(entity.getInterCasteInFamily());
        familyBackgroundDto.setParentResiding(entity.getParentResiding());
        familyBackgroundDto.setMamaSurname(entity.getMamaSurname());
        familyBackgroundDto.setMamaPlace(entity.getMamaPlace());
        familyBackgroundDto.setFamilyBackgroundCol(entity.getFamilyBackgroundCol());
        familyBackgroundDto.setRelativeSurnames(entity.getRelativeSurnames());
       // familyBackgroundDto.setUserId(entity.getUser().getId());
        return familyBackgroundDto;

    }
    public static FamilyBackground toEntity(FamilyBackgroundDto dto){
        if(dto==null){
            return null;
        }

        FamilyBackground entity=new FamilyBackground();
        entity.setFamilyBackgroundId(dto.getFamilyBackgroundId());
        entity.setFatherName(dto.getFatherName());
        entity.setMotherName(dto.getMotherName());
        entity.setFatherOccupation(dto.getFatherOccupation());
        entity.setMotherOccupation(dto.getMotherOccupation());
        entity.setBrother(dto.getBrother());
        entity.setMarriedBrothers(dto.getMarriedBrothers());
        entity.setSisters(dto.getSisters());
        entity.setMarriedSisters(dto.getMarriedSisters());
        entity.setInterCasteInFamily(dto.getInterCasteInFamily());
        entity.setParentResiding(dto.getParentResiding());
        entity.setMamaSurname(dto.getMamaSurname());
        entity.setMamaPlace(dto.getMamaPlace());
        entity.setRelativeSurnames(dto.getRelativeSurnames());
      //  entity.setUserId(dto.getUserId());

        return entity;


    }
    }



