package com.spring.jwt.repository;

import com.spring.jwt.entity.EducationAndProfession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationAndProfessionRepository extends JpaRepository<EducationAndProfession, Integer> {
}




