package com.spring.jwt.horoscopedetails;

import com.spring.jwt.dto.HoroscopeDetailsRequestDTO;
import com.spring.jwt.dto.HoroscopeDetailsResponseDTO;
import com.spring.jwt.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/horoscope")
@RequiredArgsConstructor
public class HoroscopeDetailsController {

    private final HoroscopeDetailsService service;
    private final JwtService jwtService;


    private Integer extractUserId(String authHeader) {
        String token = authHeader.startsWith("Bearer ")
                ? authHeader.substring(7)
                : authHeader;
        return jwtService.extractUserId(token);
    }


    @PostMapping("/create")
    public HoroscopeDetailsResponseDTO create(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody HoroscopeDetailsRequestDTO dto) {

        Integer userId = extractUserId(authHeader);
        dto.setUserId(userId);
        return service.createHoroscope(dto);
    }
    // by token only
    @GetMapping()
    public HoroscopeDetailsResponseDTO getMyHoroscope(
            @RequestHeader("Authorization") String authHeader) {

        Integer userId = extractUserId(authHeader);
        return service.getCurrentUserHoroscope(userId);
    }



    @PutMapping("/{id}")
    public HoroscopeDetailsResponseDTO update(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer id,
            @RequestBody HoroscopeDetailsRequestDTO dto) {

        Integer userId = extractUserId(authHeader);
        return service.updateHoroscope(id, userId, dto);
    }


    @PatchMapping("/{id}")
    public HoroscopeDetailsResponseDTO patch(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer id,
            @RequestBody HoroscopeDetailsRequestDTO dto) {

        Integer userId = extractUserId(authHeader);
        return service.patchHoroscope(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return service.deleteHoroscope(id);
    }
}
