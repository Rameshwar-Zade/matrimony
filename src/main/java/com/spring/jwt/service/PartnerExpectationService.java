package com.spring.jwt.service;

import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.entity.ExpectationsComplete;

import java.util.List;

public interface PartnerExpectationService {

    Integer saveExpectations(PartnerExpectationDTO dto);

    ExpectationsComplete getExpectations(Integer userId);

    ExpectationsComplete updateExpectations(Integer userId, PartnerExpectationDTO dto);

    List<ExpectationsComplete> getAllExpectations();

    ExpectationsComplete patchExpectations(Integer userId, PartnerExpectationDTO dto);

    void deleteExpectations(Integer userId);
}
