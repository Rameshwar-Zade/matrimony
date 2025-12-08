package com.spring.jwt.controller;

import com.spring.jwt.dto.PartnerExpectationDTO;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.service.PartnerExpectationService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner")
public class PartnerExpectationController {

    @Autowired
    private PartnerExpectationService partnerService;

    @Autowired
    private JwtService jwtService;

    private Long getUserId(String auth) {
        String token = auth.substring(7);
        return jwtService.extractUserId(token);
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String auth,
                                  @Valid @RequestBody PartnerExpectationDTO dto) {
        Long userId = getUserId(auth);
        Long id = partnerService.saveExpectations(userId, dto);
        return ResponseEntity.ok("Saved with ID: " + id);
    }


    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestHeader("Authorization") String auth) {
        Long userId = getUserId(auth);
        return ResponseEntity.ok(partnerService.getExpectations(userId));
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String auth,
                                    @Valid @RequestBody PartnerExpectationDTO dto) {
        Long userId = getUserId(auth);
        return ResponseEntity.ok(partnerService.updateExpectations(userId, dto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String auth) {
        // Only token validity is checked - userId not required
        return ResponseEntity.ok(partnerService.getAllExpectations());
    }

    // 🔹 PATCH EXPECTATION (PARTIAL UPDATE)
    @PatchMapping("/patch")
    public ResponseEntity<?> patch(@RequestHeader("Authorization") String auth,
                                   @RequestBody PartnerExpectationDTO dto) {
        Long userId = getUserId(auth);
        return ResponseEntity.ok(partnerService.patchExpectations(userId, dto));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String auth) {
        Long userId = getUserId(auth);
        partnerService.deleteExpectations(userId);
        return ResponseEntity.ok("Deleted successfully");
    }
}
