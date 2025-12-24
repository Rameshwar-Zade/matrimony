package com.spring.jwt.repository;

import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompleteProfileRepository extends JpaRepository<CompleteProfile, Integer> {
    Optional<CompleteProfile> findByUserId(Integer userId);

   // CompleteProfile findByUser(User loggedInUser);

   // Optional<CompleteProfile> findByUser(User loggedInUser);


}
