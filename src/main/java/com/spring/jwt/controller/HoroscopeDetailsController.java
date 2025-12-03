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


    @PostMapping("/create")
    public HoroscopeDetailsResponseDTO create(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody HoroscopeDetailsRequestDTO dto) {

        String token = authHeader.startsWith("Bearer ") ?
                authHeader.substring(7) : authHeader;

        Long userId = jwtService.extractUserId(token);
        dto.setUserId(userId.intValue());
        return service.createHoroscope(dto);
    }

    @GetMapping("/{id}")
    public HoroscopeDetailsResponseDTO getOne(@PathVariable Integer id) {
        return service.getHoroscope(id);
    }

    @GetMapping
    public List<HoroscopeDetailsResponseDTO> getAll() {
        return service.getAllHoroscopes();
    }

    @PutMapping("/{id}")
    public HoroscopeDetailsResponseDTO update(
            @PathVariable Integer id,
            @RequestBody HoroscopeDetailsRequestDTO dto) {
        return service.updateHoroscope(id, dto);
    }

    @PatchMapping("/{id}")
    public HoroscopeDetailsResponseDTO patch(
            @PathVariable Integer id,
            @RequestBody HoroscopeDetailsRequestDTO dto) {
        return service.patchHoroscope(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return service.deleteHoroscope(id);
    }
}
