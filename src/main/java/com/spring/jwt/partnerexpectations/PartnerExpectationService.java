package com.spring.jwt.partnerexpectations;

import com.spring.jwt.dto.PartnerExpectationDTO;

public interface PartnerExpectationService {

    PartnerExpectationDTO createExpectation(Integer userId, PartnerExpectationDTO dto);
    // byy token only
    PartnerExpectationDTO getCurrentUserExpectation(Integer userId);
    PartnerExpectationDTO updateExpectation( Integer id, Integer userId, PartnerExpectationDTO dto);
    PartnerExpectationDTO patchExpectation(Integer id, Integer userId, PartnerExpectationDTO dto);
    void deleteExpectation(Integer id, Integer userId);
}
