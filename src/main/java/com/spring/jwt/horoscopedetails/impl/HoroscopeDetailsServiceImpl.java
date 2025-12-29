package com.spring.jwt.horoscopedetails.impl;

import com.spring.jwt.dto.HoroscopeDetailsRequestDTO;
import com.spring.jwt.dto.HoroscopeDetailsResponseDTO;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.HoroscopeDetails;
import com.spring.jwt.exception.HoroscopeAlreadyExistsException;
import com.spring.jwt.horoscopedetails.HoroscopeDetailsRepository;
import com.spring.jwt.horoscopedetails.HoroscopeDetailsService;
import com.spring.jwt.mapper.HoroscopeDetailsMapper;
import com.spring.jwt.repository.CompleteProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HoroscopeDetailsServiceImpl implements HoroscopeDetailsService {

    private final HoroscopeDetailsRepository repository;
    private final CompleteProfileRepository completeProfileRepository;

    @Override
    public HoroscopeDetailsResponseDTO createHoroscope(
            HoroscopeDetailsRequestDTO dto) {

        Integer userId = dto.getUserId();


        HoroscopeDetails existing = repository.findByUserId(userId);
        if (existing != null) {
            throw new HoroscopeAlreadyExistsException(
                    "Horoscope details already exist for this user. Please update instead of creating again."
            );
        }
        HoroscopeDetails entity = HoroscopeDetailsMapper.toEntity(dto);
        entity.setUserId(userId);

        HoroscopeDetails saved = repository.save(entity);

        CompleteProfile cp = completeProfileRepository.findByUserId(userId)
                .orElseGet(() -> {
                    CompleteProfile newCP = new CompleteProfile();
                    newCP.setUserId(userId);
                    return newCP;
                });

        cp.setHoroscopeId(saved.getHoroscopeDetailsId());
        completeProfileRepository.save(cp);

        return HoroscopeDetailsMapper.toDTO(saved);
    }
     // get by token only
    @Override
    public HoroscopeDetailsResponseDTO getCurrentUserHoroscope(Integer userId) {
        HoroscopeDetails entity = repository.findByUserId(userId);
        if (entity == null) {
            throw new RuntimeException("Horoscope not found for current user");
        }
        return HoroscopeDetailsMapper.toDTO(entity);
    }



    @Override
    public HoroscopeDetailsResponseDTO updateHoroscope(Integer id, Integer userId, HoroscopeDetailsRequestDTO dto) {
        HoroscopeDetails entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horoscope not found"));
        if (!entity.getUserId().equals(userId)) {
            throw new RuntimeException("You are not allowed to update this horoscope");
        }
        HoroscopeDetailsMapper.updateEntity(entity, dto);
        entity.setUserId(userId);
        HoroscopeDetails updated = repository.save(entity);
        return HoroscopeDetailsMapper.toDTO(updated);
    }


    @Override
    public HoroscopeDetailsResponseDTO patchHoroscope(Integer id, HoroscopeDetailsRequestDTO dto) {
        HoroscopeDetails entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horoscope not found"));
        HoroscopeDetailsMapper.patchEntity(entity, dto);
        HoroscopeDetails updated = repository.save(entity);
        return HoroscopeDetailsMapper.toDTO(updated);
    }
    @Override
    public String deleteHoroscope(Integer id) {
        HoroscopeDetails entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horoscope not found"));
        Integer userId = entity.getUserId();
        completeProfileRepository.findByUserId(userId)
                .ifPresent(cp -> {
                    cp.setHoroscopeId(null);
                    completeProfileRepository.save(cp);
                });
        repository.delete(entity);
        return "Horoscope deleted successfully";
    }
}
