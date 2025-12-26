package com.spring.jwt.service.impl;

import com.spring.jwt.dto.FamilyBackgroundDto;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.entity.FamilyBackground;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.ProfileNotFoundException;
import com.spring.jwt.exception.ResourceNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.mapper.FamilyBackgroundMapper;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.FamilyBackgroundRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.service.FamilyBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FamilyBackgroundServiceImpl implements FamilyBackgroundService {

        private final UserRepository userRepository;
        private final FamilyBackgroundRepository familyBackgroundRepository;
        private final CompleteProfileRepository completeProfileRepository;
    @Autowired
    private JwtService jwtService;

    public FamilyBackgroundServiceImpl(UserRepository userRepository,
                                       FamilyBackgroundRepository familyBackgroundRepository, CompleteProfileRepository completeProfileRepository) {
        this.userRepository = userRepository;
        this.familyBackgroundRepository = familyBackgroundRepository;
        this.completeProfileRepository = completeProfileRepository;
    }


    @Override
    public FamilyBackgroundDto create(FamilyBackgroundDto dto) {

        String token = jwtService.extractToken();
        Integer userId = jwtService.extractUserId(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        if (familyBackgroundRepository.existsByUser_Id(userId)) {
            throw new ProfileNotFoundException(
                    "familyBackground details already exist"
            );
        }


      // Convert DTO → Entity
       FamilyBackground entity = FamilyBackgroundMapper.toEntity(dto,user);

        familyBackgroundRepository.save(entity);

        FamilyBackground savedEntity = familyBackgroundRepository.save(entity);

        // ----------- NEW CODE: Update COMPLETE PROFILE ----------------

        Integer familyBackgroundId = savedEntity.getFamilyBackgroundId();

        CompleteProfile cp = completeProfileRepository.findByUserId(userId)
                .orElseGet(() -> {
                    CompleteProfile newCP = new CompleteProfile();
                    newCP.setUserId(userId);
                    return newCP;
                });

        cp.setFamilyBackgroundId(familyBackgroundId);

        completeProfileRepository.save(cp);

        return FamilyBackgroundMapper.toDto(savedEntity);
    }

    public FamilyBackgroundDto getById(Integer id){
        FamilyBackground entity = familyBackgroundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "FamilyBackground details not found with id: " + id));

        return FamilyBackgroundMapper.toDto(entity);
    }

    public List<FamilyBackgroundDto> getAll(){
        List<FamilyBackground> entityList = familyBackgroundRepository.findAll();
        return entityList.stream()
                .map(FamilyBackgroundMapper::toDto)
                .collect(Collectors.toList());
        }

        public FamilyBackgroundDto update(Integer id,FamilyBackgroundDto dto){
         FamilyBackground entity=familyBackgroundRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Family Background not found for id " + id));
            entity.setFatherOccupation(dto.getFatherOccupation());
            entity.setMotherOccupation(dto.getMotherOccupation());
            entity.setBrother(dto.getBrother());
            entity.setMarriedBrothers(dto.getMarriedBrothers());
            entity.setSisters(dto.getSisters());
            entity.setMarriedSisters(dto.getMarriedSisters());
            entity.setInterCasteInFamily(dto.getInterCasteInFamily());
            entity.setParentResiding(dto.getParentResiding());
            entity.setMamaSurname(dto.getMamaSurname());
            entity.setMamaPlace(dto.getMamaPlace());
            entity.setFamilyBackgroundCol(dto.getFamilyBackgroundCol());
            entity.setRelativeSurnames(dto.getRelativeSurnames());
            FamilyBackground updated = familyBackgroundRepository.save(entity);

            return FamilyBackgroundMapper.toDto(updated);


        }

    @Override
    public void delete(Integer id) {

        FamilyBackground entity = familyBackgroundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Family Background not found with id: " + id));

        familyBackgroundRepository.delete(entity);
    }

    @Override
    public FamilyBackgroundDto getByLoggedInUser() {


        // Get logged-in username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch user
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }

        // Fetch FamilyBackground by user
        FamilyBackground entity = familyBackgroundRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "FamilyBackground not found for user: " + username));

        return FamilyBackgroundMapper.toDto(entity);
    }

    }




