package com.spring.jwt.CompleteProfile;


import com.spring.jwt.entity.User;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-profile")
public class CompleteProfileController{

    @Autowired
    private  CompleteProfileService completeProfileService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/get")
    public ResponseEntity<FullProfileDTO> getFullProfile() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email);
        if(user==null)
            throw new UserNotFoundExceptions("User not found");

        return ResponseEntity.ok(completeProfileService.getCompleteProfile(user.getId()));
    }

    @GetMapping("/public/get/{userId}")
    public ResponseEntity<PublicProfileDTO> getPublicProfile(Integer userId){

        return ResponseEntity.ok(completeProfileService.getPublicProfile(userId));
    }


}
