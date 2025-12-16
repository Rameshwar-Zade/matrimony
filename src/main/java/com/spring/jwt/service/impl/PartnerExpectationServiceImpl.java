package com.spring.jwt.service.impl;

import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.ExpectationsComplete;
import com.spring.jwt.entity.User;
import com.spring.jwt.mapper.PartnerExpectationMapper;
import com.spring.jwt.mapper.PartnerExpectationMapper;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.ExpectationsCompleteRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.service.PartnerExpectationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PartnerExpectationServiceImpl implements PartnerExpectationService {

    private final ExpectationsCompleteRepository expectationsRepo;
    private final UserRepository userRepository;
    private final CompleteProfileRepository completeProfileRepo;
    private final PartnerExpectationMapper mapper;


    @Override
    public Integer saveExpectations(PartnerExpectationDTO dto) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Integer userId = user.getId();

        ExpectationsComplete entity =
                expectationsRepo.findByUserId(userId);

        if (entity == null) {
            entity = mapper.toEntity(dto);
            entity.setUserId(userId);
        } else {
            mapper.updateEntity(entity, dto);
        }
        entity.setUserId(userId);
        ExpectationsComplete saved = expectationsRepo.save(entity);

        CompleteProfile profile = completeProfileRepo.findByUserId(userId)
                .orElseThrow(() ->
                        new IllegalStateException("Complete profile not created"));

        profile.setPartnerExpectationId(saved.getId());
        completeProfileRepo.save(profile);

        return saved.getId();
    }


    @Override
    public ExpectationsComplete getExpectations(Integer userId) {
        return expectationsRepo.findByUserId(userId);
    }


    @Override
    public List<ExpectationsComplete> getAllExpectations() {
        return expectationsRepo.findAll();
    }


    @Override
    public ExpectationsComplete updateExpectations(
            Integer userId,
            PartnerExpectationDTO dto) {

        ExpectationsComplete existing =
                expectationsRepo.findByUserId(userId);

        if (existing == null) {
            throw new RuntimeException("No expectation data found");
        }

        mapper.updateEntity(existing, dto);
        return expectationsRepo.save(existing);
    }

    @Override
    public ExpectationsComplete patchExpectations(
            Integer userId,
            PartnerExpectationDTO dto) {

        ExpectationsComplete existing =
                expectationsRepo.findByUserId(userId);

        if (existing == null) {
            throw new RuntimeException("No expectation data found");
        }

        mapper.updateEntity(existing, dto);
        return expectationsRepo.save(existing);
    }


    @Override
    public void deleteExpectations(Integer userId) {

        ExpectationsComplete existing =
                expectationsRepo.findByUserId(userId);

        if (existing == null) {
            throw new RuntimeException("No expectation found for this user");
        }

        completeProfileRepo.findByUserId(userId)
                .ifPresent(cp -> {
                    cp.setPartnerExpectationId(null);
                    completeProfileRepo.save(cp);
                });

        expectationsRepo.delete(existing);
    }
}
