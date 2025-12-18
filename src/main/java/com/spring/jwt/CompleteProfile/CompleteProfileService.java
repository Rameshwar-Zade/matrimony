package com.spring.jwt.CompleteProfile;


import java.util.List;

public interface CompleteProfileService {

    FullProfileDTO getCompleteProfile(Integer userId);

    PublicProfileDTO getPublicProfile(Integer userId);

    List<FullProfileDTO> getAllByGender(String gender);
}
