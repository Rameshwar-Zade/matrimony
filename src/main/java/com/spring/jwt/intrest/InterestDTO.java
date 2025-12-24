package com.spring.jwt.intrest;

import com.spring.jwt.enums.InterestStatus;
import lombok.Data;

import java.time.Instant;


@Data
public class InterestDTO {
    private Integer interestId;
    private Integer senderId;
    private Integer receiverId;
    private InterestStatus status;
    private Instant createdAt;
}
