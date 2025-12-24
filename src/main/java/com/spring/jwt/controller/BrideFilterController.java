package com.spring.jwt.controller;


import com.spring.jwt.dto.BrideFilterDto;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.service.BrideFilterService;
import com.spring.jwt.userprofile.UserProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brides")
public class BrideFilterController {

    private final UserProfileService userProfileService;

    private final BrideFilterService brideFilterService;


    public BrideFilterController(UserProfileService userProfileService, BrideFilterService brideFilterService) {
        this.userProfileService = userProfileService;

        this.brideFilterService = brideFilterService;
    }

    @PostMapping("/filter")
    public List<UserProfile> filterBrides(@RequestBody BrideFilterDto dto){
        return brideFilterService.filterBrides(dto);
    }
    }

