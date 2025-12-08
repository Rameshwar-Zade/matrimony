package com.spring.jwt.repository;

import com.spring.jwt.dto.FamilyBackgroundDto;
import com.spring.jwt.entity.FamilyBackground;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyBackgroundRepository extends JpaRepository<FamilyBackground, Integer> {

}
