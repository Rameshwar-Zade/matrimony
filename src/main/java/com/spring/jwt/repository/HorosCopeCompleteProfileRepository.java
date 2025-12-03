package com.spring.jwt.repository;

import com.spring.jwt.entity.HorosCopeCompleteProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HorosCopeCompleteProfileRepository extends JpaRepository<HorosCopeCompleteProfile, Long> {
    Optional<HorosCopeCompleteProfile> findByUserId(Long userId);
}
