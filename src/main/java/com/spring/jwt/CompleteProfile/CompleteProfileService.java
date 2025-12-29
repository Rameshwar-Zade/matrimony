package com.spring.jwt.CompleteProfile;

public interface CompleteProfileService {

    FullProfileDTO getCurrentUserFullProfile();

    FullProfileDTO getFullProfileByUserId(Integer userId);

    PublicProfileDTO getPublicProfile(Integer userId);

    BioDataDisplayDTO getBioData(Integer userId);
}
