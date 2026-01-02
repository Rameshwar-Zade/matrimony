package com.spring.jwt.favorite.impl;

import com.spring.jwt.entity.Favorite;
import com.spring.jwt.favorite.FavoriteRepository;
import com.spring.jwt.favorite.FavoriteResponseDTO;
import com.spring.jwt.favorite.FavoriteService;
import com.spring.jwt.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public FavoriteResponseDTO addFavorite(Integer favoriteUserId) {
        Integer userId= jwtService.extractUserId(jwtService.extractToken());

        FavoriteResponseDTO response = new FavoriteResponseDTO();

        if (Objects.equals(userId, favoriteUserId)) {
            response.setFavorited(false);
            response.setMessage("You cannot favorite your own profile");
            return response;
        }

        boolean exists = favoriteRepository.existsByUserIdAndFavoriteUserId(userId, favoriteUserId);

        if (exists) {
            favoriteRepository.deleteByUserIdAndFavoriteUserId(userId, favoriteUserId);
            response.setFavorited(false);
            response.setMessage("Removed from favorites");
            return response;
        }

        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setFavoriteUserId(favoriteUserId);
        favorite.setCreatedAt(LocalDateTime.now());

        favoriteRepository.save(favorite);

        response.setFavorited(true);
        response.setMessage("Added to favorites");

        return response;
    }

    @Override
    public List<Favorite> getMyFavorites() {
        Integer userId= jwtService.extractUserId(jwtService.extractToken());
        return favoriteRepository.findByUserId(userId);
    }


}
