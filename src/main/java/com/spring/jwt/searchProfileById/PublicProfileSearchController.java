package com.spring.jwt.searchProfileById;

import com.spring.jwt.dto.PublicProfileCardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/profiles")
@RequiredArgsConstructor
public class PublicProfileSearchController {

    private final PublicProfileSearchService service;

    @GetMapping("/search-by-id/{profileId}")
    public PublicProfileCardDTO searchByProfileId(
            @PathVariable Integer profileId) {

        return service.searchByProfileId(profileId);
    }
}
