package com.spring.jwt.searchProfileById;

import com.spring.jwt.dto.PublicProfileCardDTO;

public interface PublicProfileSearchService {

    PublicProfileCardDTO searchByProfileId(Integer profileId);
}