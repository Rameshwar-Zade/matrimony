package com.spring.jwt.userprofileF;

import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.exception.UserProfileNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserProfileRepository;
import com.spring.jwt.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserProfileRepository userProfileRepository;

//    @Override
//    public UserProfile create(UserProfileDto dto) {
//
//            UserProfile userProfile = UserProfileMapper.toEntity(dto);
//
//        if (dto.getUserId() != null) {
//            Long uid = dto.getUserId();
//            if (userProfileRepository.existsByUser_Id(uid)) {
//                throw new UserProfileNotFoundException("User with id " + uid + " already has a profile you can update instead creating");
//            }
//            User user = userRepo.findById(uid)
//                    .orElseThrow(() -> new UserNotFoundExceptions("User not found with id " + uid));
//            userProfile.setUser(user);
//        }
//            return userProfileRepository.save(userProfile);
//        }

    @Override
    public UserProfile create(UserProfileDto dto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepo.findByEmail(email);
                if(user==null)
                    throw new UserNotFoundExceptions("User not found");

        if (userProfileRepository.existsByUser_Id(user.getId())) {
            throw new UserProfileNotFoundException(
                    "User with id " + user.getId() + " already has a profile; update instead."
            );
        }
        UserProfile userProfile = UserProfileMapper.toEntity(dto);
        userProfile.setUser(user);
        return userProfileRepository.save(userProfile);
    }


    @Override
    public UserProfile updateUserProfile(Integer userId, UserProfileDto dto) {
        UserProfile userProfile=userProfileRepository.findByUserId(userId);
        return userProfileRepository.save(userProfile);
    }




}
