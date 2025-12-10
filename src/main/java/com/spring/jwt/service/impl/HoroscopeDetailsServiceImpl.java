package com.spring.jwt.service.impl;

import com.spring.jwt.dto.HoroscopeDetailsRequestDTO;
import com.spring.jwt.dto.HoroscopeDetailsResponseDTO;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.HoroscopeDetails;
import com.spring.jwt.mapper.HoroscopeDetailsMapper;
import com.spring.jwt.repository.CompleteProfileRepository;
import com.spring.jwt.repository.HoroscopeDetailsRepository;
import com.spring.jwt.service.HoroscopeDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HoroscopeDetailsServiceImpl implements HoroscopeDetailsService {

    private final HoroscopeDetailsRepository repository;
    private final CompleteProfileRepository completeProfileRepository;

    @Override
    public HoroscopeDetailsResponseDTO createHoroscope(HoroscopeDetailsRequestDTO dto) {

        // Save main horoscope details
        HoroscopeDetails entity = HoroscopeDetailsMapper.toEntity(dto);
        HoroscopeDetails saved = repository.save(entity);

        Integer userId = dto.getUserId();
        Integer horoscopeId = saved.getHoroscopeDetailsId();

        // Update complete_profile table
        CompleteProfile cp = completeProfileRepository.findByUserId(userId)
                .orElse(new CompleteProfile());

        cp.setUserId(userId);
        cp.setHoroscopeId(horoscopeId);

        completeProfileRepository.save(cp);

        return HoroscopeDetailsMapper.toDTO(saved);
    }

    @Override
    public HoroscopeDetailsResponseDTO getHoroscope(Integer id) {
        HoroscopeDetails entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horoscope not found"));
        return HoroscopeDetailsMapper.toDTO(entity);
    }

    @Override
    public List<HoroscopeDetailsResponseDTO> getAllHoroscopes() {
        return repository.findAll()
                .stream()
                .map(HoroscopeDetailsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HoroscopeDetailsResponseDTO updateHoroscope(Integer id, HoroscopeDetailsRequestDTO dto) {
        HoroscopeDetails entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horoscope not found"));

        HoroscopeDetailsMapper.updateEntity(entity, dto);

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
        repository.deleteById(id);
        return "Horoscope deleted successfully";
    }
}
