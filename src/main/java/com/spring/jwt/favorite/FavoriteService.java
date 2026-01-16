package com.spring.jwt.favorite;
import com.spring.jwt.entity.Favorite;

import java.util.List;

public interface FavoriteService {

    FavoriteResponseDTO addFavorite(Integer favoriteUserId);

    List<Favorite> getMyFavorites();

}
