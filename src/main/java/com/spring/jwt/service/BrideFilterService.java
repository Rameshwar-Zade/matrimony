package com.spring.jwt.service;

import com.spring.jwt.dto.BrideFilterDto;
import com.spring.jwt.entity.UserProfile;

import java.util.List;

public interface BrideFilterService {

        List<UserProfile> filterBrides(BrideFilterDto dto);

    }


