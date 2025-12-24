package com.spring.jwt.CompleteProfile;


import com.spring.jwt.enums.Gender;

import java.util.List;

public interface CompleteProfileService {

    FullProfileDTO getCompleteProfile(Integer userId);

    PublicProfileDTO getPublicProfile(Integer userId);

    List<FullProfileDTO> getAllByGender(Gender gender);
}
