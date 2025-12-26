package com.spring.jwt.service;

import com.spring.jwt.dto.FamilyBackgroundDto;

import java.util.List;

public interface FamilyBackgroundService {
    FamilyBackgroundDto create(FamilyBackgroundDto dto);


    FamilyBackgroundDto update(Integer id, FamilyBackgroundDto dto);

    void delete(Integer id);

    FamilyBackgroundDto getByLoggedInUser();
}
