package com.spring.jwt.repository;

import com.spring.jwt.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Integer> {

    ContactDetails findByUserId(Integer id);

    Optional<ContactDetails> findByUser_Id(Integer userId);

    boolean existsByUser_Id(Integer userId);
}
