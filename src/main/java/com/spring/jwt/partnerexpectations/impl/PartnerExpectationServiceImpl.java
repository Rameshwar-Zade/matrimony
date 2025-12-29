package com.spring.jwt.partnerexpectations.impl;

import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.PartnerExpectation;
import com.spring.jwt.exception.PartnerExpectationAlreadyExistsException;
import com.spring.jwt.exception.PartnerExpectationNotFoundException;
import com.spring.jwt.mapper.PartnerExpectationMapper;
import com.spring.jwt.partnerexpectations.PartnerExpectationService;
import com.spring.jwt.partnerexpectations.PartnerExpectationsRepository;
import com.spring.jwt.repository.CompleteProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PartnerExpectationServiceImpl implements PartnerExpectationService {

    private final PartnerExpectationsRepository expectationsRepo;
    private final CompleteProfileRepository completeProfileRepo;
    private final PartnerExpectationMapper mapper;

    @Override
    public PartnerExpectationDTO createExpectation(
            Integer userId,
            PartnerExpectationDTO dto) {

        PartnerExpectation existing = expectationsRepo.findByUserId(userId);

        if (existing != null) {
            throw new PartnerExpectationAlreadyExistsException(
                    "Partner expectations already exist. Please update instead of creating again."
            );
        }

        PartnerExpectation entity = mapper.toEntity(dto);
        entity.setUserId(userId);

        PartnerExpectation saved = expectationsRepo.save(entity);

        CompleteProfile cp = completeProfileRepo.findByUserId(userId)
                .orElseThrow(() ->
                        new IllegalStateException("Complete profile not created yet"));

        cp.setPartnerExpectationId(saved.getId());
        completeProfileRepo.save(cp);

        return PartnerExpectationMapper.toDTO(saved);
    }

    // get by token
    @Override
    public PartnerExpectationDTO getCurrentUserExpectation(Integer userId) {

        PartnerExpectation entity = expectationsRepo.findByUserId(userId);

        if (entity == null) {
            throw new PartnerExpectationNotFoundException("Partner expectation not found");
        }

        return PartnerExpectationMapper.toDTO(entity);
    }

    @Override
    public PartnerExpectationDTO updateExpectation(
            Integer id,
            Integer userId,
            PartnerExpectationDTO dto) {

        PartnerExpectation entity = expectationsRepo.findById(id)
                .orElseThrow(() ->
                        new PartnerExpectationNotFoundException("Partner expectation not found"));

        if (!entity.getUserId().equals(userId)) {
            throw new RuntimeException("You are not allowed to update this expectation");
        }

        mapper.updateEntity(entity, dto);
        return PartnerExpectationMapper.toDTO(
                expectationsRepo.save(entity)
        );
    }

    @Override
    public PartnerExpectationDTO patchExpectation(
            Integer id,
            Integer userId,
            PartnerExpectationDTO dto) {

        PartnerExpectation entity = expectationsRepo.findById(id)
                .orElseThrow(() ->
                        new PartnerExpectationNotFoundException("Partner expectation not found"));

        if (!entity.getUserId().equals(userId)) {
            throw new RuntimeException("You are not allowed to update this expectation");
        }

        mapper.updateEntity(entity, dto);

        return PartnerExpectationMapper.toDTO(
                expectationsRepo.save(entity)
        );
    }

    @Override
    public void deleteExpectation(Integer id, Integer userId) {

        PartnerExpectation entity = expectationsRepo.findById(id)
                .orElseThrow(() ->
                        new PartnerExpectationNotFoundException("Partner expectation not found"));

        if (!entity.getUserId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete this expectation");
        }

        completeProfileRepo.findByUserId(userId)
                .ifPresent(cp -> {
                    cp.setPartnerExpectationId(null);
                    completeProfileRepo.save(cp);
                });

        expectationsRepo.delete(entity);
    }
}
