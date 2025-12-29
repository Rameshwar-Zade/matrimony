package com.spring.jwt.service.impl;

import com.spring.jwt.dto.EducationAndProfessionDto;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.entity.FamilyBackground;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.ProfileNotFoundException;
import com.spring.jwt.exception.ResourceNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.mapper.EducationAndProfessionMapper;
import com.spring.jwt.mapper.FamilyBackgroundMapper;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.EducationAndProfessionRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.service.EducationAndProfessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EducationAndProfessionServiceImpl implements EducationAndProfessionService {

    @Autowired
    private final EducationAndProfessionRepository repository;
    private final CompleteProfileRepository completeProfileRepository;
    private final EducationAndProfessionMapper mapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JwtService jwtService;


    public EducationAndProfessionDto create( EducationAndProfessionDto dto) {
        String token = jwtService.extractToken();
        Integer userId = jwtService.extractUserId(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        if (repository.existsByUser_Id(userId)) {
            throw new ProfileNotFoundException(
                    "Education & Profession details already exist"
            );
        }


        EducationAndProfession entity =
                EducationAndProfessionMapper.toEntity(dto,user);

        repository.save(entity);

        EducationAndProfession savedEntity = repository.save(entity);

        Integer educationId =
                savedEntity.getEducationAndProfessionalDetailsId();


        CompleteProfile cp = completeProfileRepository.findByUserId(userId)
                .orElseGet(() -> {
                    CompleteProfile newCP = new CompleteProfile();
                    newCP.setUserId(userId);
                    return newCP;
                });

        cp.setEducationId(educationId);
        completeProfileRepository.save(cp);
        return EducationAndProfessionMapper.toDto(savedEntity);

    }

    @Override
    public EducationAndProfessionDto update(Integer id, EducationAndProfessionDto dto) {

        EducationAndProfession educationAndProfession = repository.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException(
                        "Education & Profession not found with id: " + id));
        try {
            educationAndProfession.setEducation(dto.getEducation());
            educationAndProfession.setEducationDetails(dto.getEducationDetails());
            educationAndProfession.setOccupation(dto.getOccupation());
            educationAndProfession.setOccupationDetails(dto.getOccupationDetails());
            educationAndProfession.setIncomePerYear(dto.getIncomePerYear());
            educationAndProfession.setEducationAndProfessionalDetailsCol(dto.getEducationAndProfessionalDetailsCol());

            // update user (if needed)

            if (dto.getUserId() != null) {
                User user = userRepository.findById(dto.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "User not found with id: " + dto.getUserId()));

                educationAndProfession.setUser(user);
            }

            EducationAndProfession updated = repository.save(educationAndProfession);

            return EducationAndProfessionMapper.toDto(updated);

        } catch (Exception e) {

            throw new RuntimeException("Failed to update Education & Profession: " + e.getMessage());
        }
    }


    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    @Override
    public EducationAndProfessionDto partialUpdate(
            Integer id, EducationAndProfessionDto dto) {

        EducationAndProfession entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Education & Profession not found with id " + id)
                );

        if (dto.getEducation() !=null)
            entity.setEducation(dto.getEducation());

        if (dto.getEducationDetails()!=null)
            entity.setEducationDetails(dto.getEducationDetails());
        if (dto.getOccupation() !=null)
            entity.setEducation(dto.getOccupation());
        if (dto.getOccupationDetails() !=null)
            entity.setOccupationDetails(dto.getOccupationDetails());
        if (dto.getIncomePerYear() !=null)
            entity.setIncomePerYear(dto.getIncomePerYear());
        if (dto.getStatus() !=null)
            entity.setIncomePerYear(dto.getIncomePerYear());
        if (dto.getEducationAndProfessionalDetailsCol() !=null)
            entity.setEducationAndProfessionalDetailsCol(dto.getEducationAndProfessionalDetailsCol());
        if (dto.getUserId() !=null){
            User user=userRepository.findById(dto.getUserId())
                    .orElseThrow(() ->
                    new ResourceNotFoundException("User not found with id" + dto.getUserId()));
            entity.setUser(user);

        }

        return mapper.toDto(entity);

    }

    @Override
    public EducationAndProfessionDto getByLoggedInUser() {
        // Get logged-in username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch user
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }

        EducationAndProfession entity = repository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Education And Profession not found for user: " + username));

        return EducationAndProfessionMapper.toDto(entity);
    }

}

