package com.spring.jwt.userprofile;

import com.spring.jwt.entity.UserProfile;


public interface UserProfileService {

    UserProfile create(UserProfileDto dto);

    //UserProfile updateUserProfile(Integer id, UserProfileDto dto);

    UserProfile getProfile();

}
