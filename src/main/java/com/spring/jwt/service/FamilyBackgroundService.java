package com.spring.jwt.service;

import com.spring.jwt.dto.FamilyBackgroundDto;

import java.util.List;

public interface FamilyBackgroundService {
    FamilyBackgroundDto create(FamilyBackgroundDto dto);

    FamilyBackgroundDto getById(Integer id);

    List<FamilyBackgroundDto> getAll();

    FamilyBackgroundDto update(Integer id, FamilyBackgroundDto dto);

    void delete(Integer id);
}
