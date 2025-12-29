package com.spring.jwt.horoscopedetails;

import com.spring.jwt.dto.HoroscopeDetailsRequestDTO;
import com.spring.jwt.dto.HoroscopeDetailsResponseDTO;


public interface HoroscopeDetailsService {


    HoroscopeDetailsResponseDTO createHoroscope(HoroscopeDetailsRequestDTO dto);
    // only by token
    HoroscopeDetailsResponseDTO getCurrentUserHoroscope(Integer userId);
    HoroscopeDetailsResponseDTO updateHoroscope(Integer id, Integer userId, HoroscopeDetailsRequestDTO dto);
    HoroscopeDetailsResponseDTO patchHoroscope(Integer id, HoroscopeDetailsRequestDTO dto);
    String deleteHoroscope(Integer id);
}
