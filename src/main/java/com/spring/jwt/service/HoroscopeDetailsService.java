package com.spring.jwt.service;

import com.spring.jwt.dto.HoroscopeDetailsRequestDTO;
import com.spring.jwt.dto.HoroscopeDetailsResponseDTO;

import java.util.List;

public interface HoroscopeDetailsService {

    HoroscopeDetailsResponseDTO createHoroscope(HoroscopeDetailsRequestDTO dto);

    HoroscopeDetailsResponseDTO getHoroscope(Integer id);

    List<HoroscopeDetailsResponseDTO> getAllHoroscopes();

    HoroscopeDetailsResponseDTO updateHoroscope(Integer id, HoroscopeDetailsRequestDTO dto);

    HoroscopeDetailsResponseDTO patchHoroscope(Integer id, HoroscopeDetailsRequestDTO dto);

    String deleteHoroscope(Integer id);
}