package com.spring.jwt.CompleteProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class CompleteProfileController {

    @Autowired
    private CompleteProfileService service;

    @GetMapping("/me/full")
    public ResponseEntity<FullProfileDTO> myFullProfile() {
        return ResponseEntity.ok(service.getCurrentUserFullProfile());
    }

    @GetMapping("/full/{userId}")
    public ResponseEntity<FullProfileDTO> fullProfile(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.getFullProfileByUserId(userId));
    }

    @GetMapping("/public/{userId}")
    public ResponseEntity<PublicProfileDTO> publicProfile(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.getPublicProfile(userId));
    }

    @GetMapping("/biodata/{userId}")
    public ResponseEntity<BioDataDisplayDTO> biodata(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.getBioData(userId));
    }
}
