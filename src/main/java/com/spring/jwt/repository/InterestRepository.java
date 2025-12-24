package com.spring.jwt.repository;

import com.spring.jwt.entity.Interest;
import com.spring.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Integer> {

    Optional<Interest> findBySenderAndReceiver(User sender, User receiver);

    List<Interest> findByReceiver(User receiver);

    List<Interest> findBySender(User sender);
}
