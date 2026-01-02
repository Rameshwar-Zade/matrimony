package com.spring.jwt.favorite;

import com.spring.jwt.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    boolean existsByUserIdAndFavoriteUserId(Integer userId, Integer favoriteUserId);

    List<Favorite> findByUserId(Integer userId);

    void deleteByUserIdAndFavoriteUserId(Integer userId, Integer favoriteUserId);
}
