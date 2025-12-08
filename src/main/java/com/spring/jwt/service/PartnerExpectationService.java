package com.spring.jwt.service;

import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.entity.ExpectationsComplete;

import java.util.List;

public interface PartnerExpectationService {

    Long saveExpectations(Long userId, PartnerExpectationDTO dto);

    ExpectationsComplete getExpectations(Long userId);

    ExpectationsComplete updateExpectations(Long userId, PartnerExpectationDTO dto);

    List<ExpectationsComplete> getAllExpectations();

    ExpectationsComplete patchExpectations(Long userId, PartnerExpectationDTO dto);

    void deleteExpectations(Long userId);
}
