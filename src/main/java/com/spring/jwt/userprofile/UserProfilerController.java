package com.spring.jwt.userprofile;

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

    @PatchMapping("/update/{userId}")
    public ResponseEntity<String> update(@PathVariable Integer userId, @RequestBody UserProfileDto dto) {
        UserProfile updated = service.updateUserProfile(userId, dto);
        return ResponseEntity.status(HttpStatus.OK).body("User Profile is updated");
    }

    @GetMapping("/get")
    public ResponseEntity<UserProfile> get(){
        UserProfile profile=service.getProfile();
        return ResponseEntity.ok(profile);
    }

}
