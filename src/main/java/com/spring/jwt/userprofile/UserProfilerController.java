package com.spring.jwt.userprofile;

import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.utils.BaseResponseDTO;
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

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> create(@Valid @RequestBody UserProfileDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PatchMapping("/update")
    public ResponseEntity<BaseResponseDTO> update(@RequestBody UserProfileDto dto) {

        return ResponseEntity.status(HttpStatus.OK).body(service.updateUserProfile(dto));
    }


    @GetMapping("/get")
    public ResponseEntity<UserProfileDto> get() {
        return ResponseEntity.ok(service.getProfile());
    }
}
