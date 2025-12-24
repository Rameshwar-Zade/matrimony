package com.spring.jwt.repository;

import com.spring.jwt.entity.HoroscopeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoroscopeDetailsRepository extends JpaRepository<HoroscopeDetails, Integer> {
    HoroscopeDetails findByUserId(Integer userId);
}

