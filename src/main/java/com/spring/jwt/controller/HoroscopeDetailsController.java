package com.spring.jwt.controller;

import com.spring.jwt.dto.HoroscopeDetailsRequestDTO;
import com.spring.jwt.dto.HoroscopeDetailsResponseDTO;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.service.HoroscopeDetailsService;
import com.spring.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horoscope")
@RequiredArgsConstructor
public class HoroscopeDetailsController {

    private final HoroscopeDetailsService service;
    private final JwtService jwtService;

    private Long extractUserId(String authHeader) {
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
        return jwtService.extractUserId(token);
    }

    @PostMapping("/create")
    public HoroscopeDetailsResponseDTO create(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody HoroscopeDetailsRequestDTO dto) {

        Long userId = extractUserId(authHeader);
        dto.setUserId(userId.intValue());
        return service.createHoroscope(dto);
    }

    @GetMapping("/{id}")
    public HoroscopeDetailsResponseDTO getOne(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer id) {

        Long userId = extractUserId(authHeader);
        return service.getHoroscope(id);
    }

    @GetMapping
    public List<HoroscopeDetailsResponseDTO> getAll(
            @RequestHeader("Authorization") String authHeader) {

        Long userId = extractUserId(authHeader);
        return service.getAllHoroscopes();
    }

    @PutMapping("/{id}")
    public HoroscopeDetailsResponseDTO update(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer id,
            @RequestBody HoroscopeDetailsRequestDTO dto) {

        Long userId = extractUserId(authHeader);
        return service.updateHoroscope(id, dto);
    }

    @PatchMapping("/{id}")
    public HoroscopeDetailsResponseDTO patch(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer id,
            @RequestBody HoroscopeDetailsRequestDTO dto) {

        Long userId = extractUserId(authHeader);
        return service.patchHoroscope(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer id) {

        Long userId = extractUserId(authHeader);
        return service.deleteHoroscope(id);
    }
}

