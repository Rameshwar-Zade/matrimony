package com.spring.jwt.repository;

import com.spring.jwt.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Integer> {
    ContactDetails findByUserId(Integer id);
}
