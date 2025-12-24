package com.spring.jwt.userprofile.impl;

import com.spring.jwt.dto.BrideFilterDto;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.exception.ProfileNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.UserProfileRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.specification.UserProfileSpecification;
import com.spring.jwt.userprofile.UserProfileDto;
import com.spring.jwt.userprofile.UserProfileMapper;
import com.spring.jwt.userprofile.UserProfileService;
import com.spring.jwt.utils.BaseResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private CompleteProfileRepository completeProfileRepository;

    // ================= CREATE =================
    @Override
    public BaseResponseDTO create(UserProfileDto dto) {

        String token = jwtService.extractToken();
        Integer userId = jwtService.extractUserId(token);

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));


        if (userProfileRepository.existsByUser_Id(user.getId())) {
            throw new ProfileNotFoundException("User already has a profile");
        }

        UserProfile profile = UserProfileMapper.toEntity(dto);

        if (userProfileRepository.existsByMobileNumber(profile.getMobileNumber())) {
            throw new ProfileNotFoundException("This Mobile Number Already Exist For Another User");
        }
        profile.setUser(user);
        userProfileRepository.save(profile);

        CompleteProfile cp = completeProfileRepository
                .findByUserId(userId)
                .orElseGet(() -> {
                    CompleteProfile newCp = new CompleteProfile();
                    newCp.setUserId(userId);
                    return newCp;
                });

        cp.setUserProfileId(profile.getUserProfileId());
        completeProfileRepository.save(cp);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setUserID(userId);
        response.setCode("200");
        response.setMessage("UserProfile created successfully");

        return response;
    }

    // ================= GET PROFILE =================
    @Override
    public UserProfileDto getProfile() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundExceptions("User not found");
        }

        UserProfile profile = userProfileRepository.findByUserId(user.getId());

        if (profile == null) {
            throw new ProfileNotFoundException("Profile not found");
        }
            return UserProfileMapper.toDTO(profile);

    }
        // ================= UPDATE PROFILE =================
        @Override
        public BaseResponseDTO updateUserProfile (UserProfileDto dto){

            String token = jwtService.extractToken();
            //Extract userId from JWT
            Integer userId = jwtService.extractUserId(token);

            //Fetch profile of THIS user only
            UserProfile profile = userProfileRepository.findByUserId(userId);

            if (profile == null) {
                throw new ProfileNotFoundException("Profile not found");
            }

                //Update fields safely
                UserProfileMapper.updateEntity(profile, dto);

                userProfileRepository.save(profile);
            BaseResponseDTO response = new BaseResponseDTO();
            response.setUserID(userId);
            response.setCode("200");
            response.setMessage("UserProfile Updated successfully");

            return response;

            }

}

