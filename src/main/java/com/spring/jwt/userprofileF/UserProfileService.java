package com.spring.jwt.userprofileF;

import com.spring.jwt.entity.UserProfile;


public interface UserProfileService {

    UserProfile create(UserProfileDto dto);

    UserProfile updateUserProfile(Integer id, UserProfileDto dto);

}
