package com.spring.jwt.userprofileF;

import com.spring.jwt.entity.UserProfile;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfilerController {

    @Autowired
    private UserProfileService service;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody UserProfileDto dto) {
        UserProfile created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Profile is created for User ID:"+created.getUser().getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> update(@PathVariable Integer id, @RequestBody UserProfileDto dto) {
        UserProfile updated = service.updateUserProfile(id, dto);
        return ResponseEntity.ok(updated);
    }

}
