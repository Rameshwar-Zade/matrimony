package com.spring.jwt.intrest;

import com.spring.jwt.intrest.InterestDTO;
import com.spring.jwt.entity.Interest;

public class InterestMapper {

    public static com.spring.jwt.intrest.InterestDTO toDTO(Interest interest) {

        InterestDTO dto = new InterestDTO();
        dto.setInterestId(interest.getInterestId());
        dto.setSenderId(interest.getSender().getId());
        dto.setReceiverId(interest.getReceiver().getId());
        dto.setStatus(interest.getStatus());
        dto.setCreatedAt(interest.getCreatedAt());

        return dto;
    }
}
