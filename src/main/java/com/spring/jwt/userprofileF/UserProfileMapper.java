package com.spring.jwt.userprofileF;

import com.spring.jwt.entity.UserProfile;

public class UserProfileMapper {

    public static UserProfileDto toDto(UserProfile userProfile) {

        if (userProfile == null)
            return null;

        UserProfileDto dto = new UserProfileDto();

        //dto.setUserProfileId(userProfile.getUserProfileId());
        dto.setFirstName(userProfile.getFirstName());
        dto.setMiddleName(userProfile.getMiddleName());
        dto.setLastName(userProfile.getLastName());
        dto.setAddress(userProfile.getAddress());
        dto.setTaluka(userProfile.getTaluka());
        dto.setDistrict(userProfile.getDistrict());
        dto.setPinCode(userProfile.getPinCode());
        dto.setMobileNumber(userProfile.getMobileNumber());
        dto.setMail(userProfile.getMail());
        dto.setStatus(userProfile.getStatus());
        dto.setGender(userProfile.getGender());
        dto.setReligion(userProfile.getReligion());
        dto.setCaste(userProfile.getCaste());
        dto.setMaritalStatus(userProfile.getMaritalStatus());
        dto.setHeight(userProfile.getHeight());
        dto.setWeight(userProfile.getWeight());
        dto.setBloodGroup(userProfile.getBloodGroup());
        dto.setComplexion(userProfile.getComplexion());
        dto.setDiet(userProfile.getDiet());
        dto.setSpectacle(userProfile.getSpectacle());
        dto.setLens(userProfile.getLens());
        dto.setPhysicallyChallenged(userProfile.getPhysicallyChallenged());
        dto.setHomeTownDistrict(userProfile.getHomeTownDistrict());
        dto.setNativeTaluka(userProfile.getNativeTaluka());
        dto.setCurrentCity(userProfile.getCurrentCity());
        dto.setUserProfileCol(userProfile.getUserProfileCol());

        return dto;
    }

    public static UserProfile toEntity(UserProfileDto dto) {

        if (dto == null)
            return null;

        UserProfile userProfile = new UserProfile();
        //userProfile.setUserProfileId(dto.getUserProfileId());
        userProfile.setFirstName(dto.getFirstName());
        userProfile.setMiddleName(dto.getMiddleName());
        userProfile.setLastName(dto.getLastName());
        userProfile.setAddress(dto.getAddress());
        userProfile.setTaluka(dto.getTaluka());
        userProfile.setDistrict(dto.getDistrict());
        userProfile.setPinCode(dto.getPinCode());
        userProfile.setMobileNumber(dto.getMobileNumber());
        userProfile.setMail(dto.getMail());
        userProfile.setStatus(dto.getStatus());
        userProfile.setGender(dto.getGender());
        userProfile.setReligion(dto.getReligion());
        userProfile.setCaste(dto.getCaste());
        userProfile.setMaritalStatus(dto.getMaritalStatus());
        userProfile.setHeight(dto.getHeight());
        userProfile.setWeight(dto.getWeight());
        userProfile.setBloodGroup(dto.getBloodGroup());
        userProfile.setComplexion(dto.getComplexion());
        userProfile.setDiet(dto.getDiet());
        userProfile.setSpectacle(dto.getSpectacle());
        userProfile.setLens(dto.getLens());
        userProfile.setPhysicallyChallenged(dto.getPhysicallyChallenged());
        userProfile.setHomeTownDistrict(dto.getHomeTownDistrict());
        userProfile.setNativeTaluka(dto.getNativeTaluka());
        userProfile.setCurrentCity(dto.getCurrentCity());
        userProfile.setUserProfileCol(dto.getUserProfileCol());

        return userProfile;
    }
}
