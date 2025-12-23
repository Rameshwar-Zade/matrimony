package com.spring.jwt.userprofile;

import com.spring.jwt.utils.BaseResponseDTO;


public interface UserProfileService {

    BaseResponseDTO create(UserProfileDto dto);

    //UserProfile updateUserProfile(Integer id, UserProfileDto dto);

    UserProfileDto getProfile();

    BaseResponseDTO updateUserProfile(UserProfileDto dto);
}
