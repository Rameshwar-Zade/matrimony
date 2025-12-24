package com.spring.jwt.repository;

import com.spring.jwt.dto.FamilyBackgroundDto;
import com.spring.jwt.entity.FamilyBackground;
import com.spring.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyBackgroundRepository extends JpaRepository<FamilyBackground, Integer> {

    Optional<FamilyBackground> findByUser(User user);

}
