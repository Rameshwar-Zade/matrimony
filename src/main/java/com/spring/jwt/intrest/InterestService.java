package com.spring.jwt.intrest;

import com.spring.jwt.enums.InterestStatus;

import java.util.List;
import java.util.Optional;

public interface InterestService {

    void sendInterest(Integer receiverId);

    void respondInterest(Integer interestId, InterestStatus status);

    List<InterestDTO> getSentInterests();

    List<InterestDTO> getReceivedInterests();

    void cancelInterest(Integer interestId);

}
