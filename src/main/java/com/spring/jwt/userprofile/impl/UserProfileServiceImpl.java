package com.spring.jwt.userprofile.impl;

import com.spring.jwt.dto.BrideFilterDto;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.exception.ProfileNotFoundException;
import com.spring.jwt.exception.UserProfileNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.UserProfileRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.specification.UserProfileSpecification;
import com.spring.jwt.userprofile.UserProfileDto;
import com.spring.jwt.userprofile.UserProfileMapper;
import com.spring.jwt.userprofile.UserProfileService;
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
    private UserProfileRepository userProfileRepository;

    @Autowired
    private CompleteProfileRepository completeProfileRepository;

    @Override
    public void create(UserProfileDto dto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email);
                if(user==null)
                    throw new UserNotFoundExceptions("User not found");

        if (userProfileRepository.existsByUser_Id(user.getId())) {
            throw new UserProfileNotFoundException(
                    "User with id " + user.getId() + " already has a profile."
            );
        }
        UserProfile userProfile = UserProfileMapper.toEntity(dto);
        userProfile.setUser(user);
        userProfileRepository.save(userProfile);


        CompleteProfile cp = completeProfileRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    // Create new if not exists
                    CompleteProfile newCP = new CompleteProfile();
                    newCP.setUserId(user.getId());
                    newCP.setUserProfileId(
                            userProfileRepository.findByUserId(user.getId()).getUserProfileId()
                    );
                    return completeProfileRepository.save(newCP);
                });
        cp.setUserProfileId(userProfileRepository.findByUserId(user.getId()).getUserProfileId());
        cp.setUserId(user.getId());
        completeProfileRepository.save(cp);

    }


    @Override
    public UserProfile getProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email);
        if(user==null)
            throw new UserNotFoundExceptions("User not found");

         return userProfileRepository.findByUserId(user.getId());
    }

    @Override
    public UserProfile updateUserProfile(Integer userId, UserProfileDto dto) {

        UserProfile profile= userProfileRepository.findByUser_Id(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

        if (dto.getFirstName() != null) {
            profile.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            profile.setLastName(dto.getLastName());
        }
        if (dto.getMiddleName() != null) {
            profile.setMiddleName(dto.getMiddleName());
        }
        if (dto.getAddress() != null) {
            profile.setAddress(dto.getAddress());
        }
        if (dto.getTaluka() != null) {
            profile.setTaluka(dto.getTaluka());
        }
        if (dto.getDistrict() != null) {
            profile.setDistrict(dto.getDistrict());
        }
        if (dto.getPinCode() != null) {
            profile.setPinCode(dto.getPinCode());
        }
        if (dto.getMobileNumber() != null) {
            profile.setMobileNumber(dto.getMobileNumber());
        }
        if (dto.getGender() != null) {
            profile.setGender(dto.getGender());
        }
        if (dto.getReligion() != null) {
            profile.setReligion(dto.getReligion());
        }
        if (dto.getCaste() != null) {
            profile.setCaste(dto.getCaste());
        }
        if (dto.getMaritalStatus() != null) {
            profile.setMaritalStatus(dto.getMaritalStatus());
        }
        if (dto.getHeight() != null) {
            profile.setHeight(dto.getHeight());
        }
        if (dto.getWeight() != null) {
            profile.setWeight(dto.getWeight());
        }
        if (dto.getBloodGroup() != null) {
            profile.setBloodGroup(dto.getBloodGroup());
        }
        if (dto.getComplexion() != null) {
            profile.setComplexion(dto.getComplexion());
        }
        if (dto.getDiet() != null) {
            profile.setDiet(dto.getDiet());
        }
        if (dto.getSpectacle() != null) {
            profile.setSpectacle(dto.getSpectacle());
        }
        if (dto.getLens() != null) {
            profile.setLens(dto.getLens());
        }
        if (dto.getPhysicallyChallenged() != null) {
            profile.setPhysicallyChallenged(dto.getPhysicallyChallenged());
        }
        if (dto.getHomeTownDistrict() != null) {
            profile.setHomeTownDistrict(dto.getHomeTownDistrict());
        }
        if (dto.getNativeTaluka() != null) {
            profile.setNativeTaluka(dto.getNativeTaluka());
        }
        if (dto.getCurrentCity() != null) {
            profile.setCurrentCity(dto.getCurrentCity());
        }
        if (dto.getUserProfileCol() != null) {
            profile.setUserProfileCol(dto.getUserProfileCol());
        }

        return userProfileRepository.save(profile);
    }

    public List<UserProfile> filterBrides(BrideFilterDto dto){
        return userProfileRepository.findAll(
                UserProfileSpecification.filterBy(dto));


    }


}
