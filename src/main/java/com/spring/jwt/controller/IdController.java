package com.spring.jwt.controller;

import com.spring.jwt.dto.ApiResponse;
import com.spring.jwt.dto.UserDTO;
import com.spring.jwt.service.UserService;
import com.spring.jwt.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/id") // base path for this controller
public class IdController {

    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public IdController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyDetails(@RequestHeader("Authorization") String authHeader) {
        // Remove "Bearer " prefix if present
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;

        UserDTO user = userService.getUserFromToken(token);
        return ResponseEntity.ok(user);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
//        UserDTO user = userService.getUserById(id);
//        return ResponseEntity.ok(user);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
        jwtService.isValidToken(token);

        UserDTO user = userService.getUserById(id);

        ApiResponse<UserDTO> response = new ApiResponse<>(user);

        return ResponseEntity.ok(response);
    }





}
