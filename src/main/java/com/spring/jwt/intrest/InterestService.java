package com.spring.jwt.intrest;

import java.util.List;


public interface InterestService {

    InterestResponseDTO sendInterest(Integer receiverId);

    InterestResponseDTO acceptInterest(Integer interestId);

    InterestResponseDTO declineInterest(Integer interestId);

    List<InterestDTO> getSentInterests();

    List<InterestDTO> getReceivedInterests();

    void cancelInterest(Integer interestId);
}

