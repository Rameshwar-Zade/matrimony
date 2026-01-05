package com.spring.jwt.Contact_Us;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsRepository
        extends JpaRepository<ContactUsMessage,Integer> {
}
