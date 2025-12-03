package com.spring.jwt.repository;

import com.spring.jwt.entity.ExpectationCompleteProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpectationCompleteProfileRepository extends JpaRepository<ExpectationCompleteProfile, Long> {
    Optional<ExpectationCompleteProfile> findByUserId(Long userId);
}
