package com.spring.jwt.partnerexpectations;

import com.spring.jwt.entity.PartnerExpectation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerExpectationsRepository extends JpaRepository<PartnerExpectation, Integer> {
    PartnerExpectation findByUserId(Integer userId);

}
