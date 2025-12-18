package com.spring.jwt.CompleteProfile;


import com.spring.jwt.entity.User;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-profile")
public class CompleteProfileController{

    @Autowired
    private  CompleteProfileService completeProfileService;

    @Autowired
    private UserRepository userRepo;

    // ---------------------- Get full profile -----------------------

    @GetMapping("/get")
    public ResponseEntity<FullProfileDTO> getFullProfile() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email);
        if(user==null)
            throw new UserNotFoundExceptions("User not found");

        return ResponseEntity.ok(completeProfileService.getCompleteProfile(user.getId()));
    }

    // ---------------------- Get public profile -----------------------
    @GetMapping("/public/get/{userId}")
    public ResponseEntity<PublicProfileDTO> getPublicProfile(@PathVariable Integer userId) {
        PublicProfileDTO dto = completeProfileService.getPublicProfile(userId);
        return ResponseEntity.ok(dto);
    }


    // ---------------------- GET ALL PROFILES BY GENDER -----------------------
    @GetMapping("/get/gender/{gender}")
    public ResponseEntity<List<FullProfileDTO>> getAllByGender(@PathVariable String gender) {

        List<FullProfileDTO> profiles = completeProfileService.getAllByGender(gender);

        return ResponseEntity.ok(profiles);
    }

}
