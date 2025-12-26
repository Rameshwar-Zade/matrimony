package com.spring.jwt.intrest;

import com.spring.jwt.entity.Interest;

public class InterestMapper {

    public static InterestDTO toDTO(Interest interest) {
        InterestDTO dto = new InterestDTO();
        dto.setInterestId(interest.getInterestId());
        dto.setSenderId(interest.getSender().getId());
        dto.setReceiverId(interest.getReceiver().getId());
        dto.setStatus(interest.getStatus());
        dto.setCreatedAt(interest.getCreatedAt());
        return dto;
    }
}

