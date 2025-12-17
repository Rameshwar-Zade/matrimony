package com.spring.jwt.repository;

import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDocumentsRepository extends JpaRepository<UserDocuments, Integer> {

    Optional<UserDocuments> findByUser(User user);
}
