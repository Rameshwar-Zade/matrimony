package com.spring.jwt.repository;

import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationAndProfessionRepository extends JpaRepository<EducationAndProfession, Integer> {
    boolean existsByUser_Id(Integer id);

    Optional<EducationAndProfession> findByUser(User user);

}




