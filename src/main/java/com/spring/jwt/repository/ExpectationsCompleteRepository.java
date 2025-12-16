package com.spring.jwt.repository;

import com.spring.jwt.entity.ExpectationsComplete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpectationsCompleteRepository extends JpaRepository<ExpectationsComplete, Integer> {
    ExpectationsComplete findByUserId(Integer userId);

}
