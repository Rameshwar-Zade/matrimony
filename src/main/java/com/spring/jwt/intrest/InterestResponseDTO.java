package com.spring.jwt.intrest;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestResponseDTO {

    private String code;
    private String message;
    private Integer SenderID;
    private Integer receiverID;

}
