package com.spring.jwt.userprofile;

import com.spring.jwt.entity.UserProfile;

public class UserProfileMapper {

    // ================= ENTITY To DTO =================
    public static UserProfileDto toDTO(UserProfile userProfile) {

        if (userProfile == null) {
            return null;
        }

        UserProfileDto dto = new UserProfileDto();

        dto.setUserProfileId(userProfile.getUserProfileId());
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
        dto.setSubCaste(userProfile.getSubCaste());
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

    // ================= DTO → ENTITY (CREATE) =================
    public static UserProfile toEntity(UserProfileDto dto) {

        if (dto == null) {
            return null;
        }

        UserProfile userProfile = new UserProfile();

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
        userProfile.setSubCaste(dto.getSubCaste());
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

    // ================= DTO → ENTITY (UPDATE / PATCH) =================
    public static void updateEntity(UserProfile profile, UserProfileDto dto) {

        if (profile == null || dto == null) {
            return;
        }

        if (dto.getFirstName() != null) profile.setFirstName(dto.getFirstName());
        if (dto.getMiddleName() != null) profile.setMiddleName(dto.getMiddleName());
        if (dto.getLastName() != null) profile.setLastName(dto.getLastName());
        if (dto.getAddress() != null) profile.setAddress(dto.getAddress());
        if (dto.getTaluka() != null) profile.setTaluka(dto.getTaluka());
        if (dto.getDistrict() != null) profile.setDistrict(dto.getDistrict());
        if (dto.getPinCode() != null) profile.setPinCode(dto.getPinCode());
        if (dto.getMobileNumber() != null) profile.setMobileNumber(dto.getMobileNumber());
        if (dto.getMail() != null) profile.setMail(dto.getMail());
        if (dto.getStatus() != null) profile.setStatus(dto.getStatus());
        if (dto.getGender() != null) profile.setGender(dto.getGender());
        if (dto.getReligion() != null) profile.setReligion(dto.getReligion());
        if (dto.getCaste() != null) profile.setCaste(dto.getCaste());
        if (dto.getSubCaste() != null) profile.setSubCaste(dto.getSubCaste());
        if (dto.getMaritalStatus() != null) profile.setMaritalStatus(dto.getMaritalStatus());
        if (dto.getHeight() != null) profile.setHeight(dto.getHeight());
        if (dto.getWeight() != null) profile.setWeight(dto.getWeight());
        if (dto.getBloodGroup() != null) profile.setBloodGroup(dto.getBloodGroup());
        if (dto.getComplexion() != null) profile.setComplexion(dto.getComplexion());
        if (dto.getDiet() != null) profile.setDiet(dto.getDiet());
        if (dto.getSpectacle() != null) profile.setSpectacle(dto.getSpectacle());
        if (dto.getLens() != null) profile.setLens(dto.getLens());
        if (dto.getPhysicallyChallenged() != null)
            profile.setPhysicallyChallenged(dto.getPhysicallyChallenged());
        if (dto.getHomeTownDistrict() != null)
            profile.setHomeTownDistrict(dto.getHomeTownDistrict());
        if (dto.getNativeTaluka() != null)
            profile.setNativeTaluka(dto.getNativeTaluka());
        if (dto.getCurrentCity() != null)
            profile.setCurrentCity(dto.getCurrentCity());
        if (dto.getUserProfileCol() != null)
            profile.setUserProfileCol(dto.getUserProfileCol());
    }
}
