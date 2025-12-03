package com.spring.jwt.repository;

import com.spring.jwt.entity.CompleteProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompleteProfileRepository extends JpaRepository<CompleteProfile,Integer> {
}
