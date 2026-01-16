package com.spring.jwt.favorite;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FavoriteResponseDTO {

    private boolean favorited;
    private String message;


}
