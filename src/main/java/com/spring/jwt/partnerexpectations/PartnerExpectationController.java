package com.spring.jwt.partnerexpectations;

import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
public class PartnerExpectationController {

    private final PartnerExpectationService service;
    private final JwtService jwtService;


    private Integer extractUserId(String authHeader) {
        String token = authHeader.startsWith("Bearer ")
                ? authHeader.substring(7)
                : authHeader;
        return jwtService.extractUserId(token);
    }


    @PostMapping("/create")
    public PartnerExpectationDTO create(
            @RequestHeader("Authorization") String auth,
            @RequestBody PartnerExpectationDTO dto) {

        Integer userId = extractUserId(auth);
        return service.createExpectation(userId, dto);
    }

    // by token only
    @GetMapping()
    public PartnerExpectationDTO getMyExpectation(
            @RequestHeader("Authorization") String auth) {

        Integer userId = extractUserId(auth);
        return service.getCurrentUserExpectation(userId);
    }

    @PutMapping("/{id}")
    public PartnerExpectationDTO update(
            @RequestHeader("Authorization") String auth,
            @PathVariable Integer id,
            @RequestBody PartnerExpectationDTO dto) {

        Integer userId = extractUserId(auth);
        return service.updateExpectation(id, userId, dto);
    }

    @PatchMapping("/{id}")
    public PartnerExpectationDTO patch(
            @RequestHeader("Authorization") String auth,
            @PathVariable Integer id,
            @RequestBody PartnerExpectationDTO dto) {

        Integer userId = extractUserId(auth);
        return service.patchExpectation(id, userId, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteExpectation(id);
        return "Partner expectation deleted successfully";
    }
}
