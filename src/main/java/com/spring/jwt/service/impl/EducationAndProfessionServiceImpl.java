package com.spring.jwt.service.impl;

import com.spring.jwt.dto.EducationAndProfessionDto;
import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.ResourceNotFoundException;
import com.spring.jwt.mapper.EducationAndProfessionMapper;
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
    private final EducationAndProfessionMapper mapper;
    @Autowired
    private final UserRepository userRepository;


    public EducationAndProfessionDto create(EducationAndProfessionDto dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new RuntimeException("User not found for username: " + username);
        }

        EducationAndProfession entity = EducationAndProfessionMapper.toEntity(dto);

        entity.setUser(user);

        user.setEducationAndProfession(entity);

        EducationAndProfession savedEntity = repository.save(entity);

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
    public EducationAndProfessionDto getById(Integer id) {
        EducationAndProfession entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Education & Profession not found with id: " + id));

        return EducationAndProfessionMapper.toDto(entity);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

}

