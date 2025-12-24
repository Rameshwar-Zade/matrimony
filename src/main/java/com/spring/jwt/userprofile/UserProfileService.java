package com.spring.jwt.userprofile;

import com.spring.jwt.dto.BrideFilterDto;
import com.spring.jwt.entity.UserProfile;

import java.util.List;


public interface UserProfileService {

    void create(UserProfileDto dto);

    //UserProfile updateUserProfile(Integer id, UserProfileDto dto);

    UserProfile getProfile();

    UserProfile updateUserProfile(Integer userId, UserProfileDto dto);

}
