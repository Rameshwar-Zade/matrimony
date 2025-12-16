package com.spring.jwt.CompleteProfile;



public interface CompleteProfileService {

    FullProfileDTO getCompleteProfile(Integer userId);

    PublicProfileDTO getPublicProfile(Integer userId);

}
