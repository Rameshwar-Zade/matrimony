package com.spring.jwt.favorite;

import com.spring.jwt.entity.Favorite;
import com.spring.jwt.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/{favoriteUserId}")
    public ResponseEntity<FavoriteResponseDTO> addFavorite(@PathVariable Integer favoriteUserId) {

        FavoriteResponseDTO response = favoriteService.addFavorite(favoriteUserId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getMyFavorites() {

        List<Favorite> favorites = favoriteService.getMyFavorites();

        return ResponseEntity.ok(favorites);
    }

}
