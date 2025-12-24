package com.spring.jwt.service.impl;

import com.spring.jwt.dto.BrideFilterDto;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.repository.UserProfileRepository;
import com.spring.jwt.service.BrideFilterService;
import com.spring.jwt.specification.UserProfileSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrideFilterServiceImpl implements BrideFilterService {

    private final UserProfileRepository userProfileRepository;


    @Override
    public List<UserProfile> filterBrides(BrideFilterDto dto) {
        return userProfileRepository.findAll(UserProfileSpecification.filterBy(dto));
    }
}
