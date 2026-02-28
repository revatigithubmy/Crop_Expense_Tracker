package com.revati.farmersbuddy.controller;


import com.revati.farmersbuddy.dto.request.LoginRequestDTO;
import com.revati.farmersbuddy.dto.request.RegisterRequestDTO;
import com.revati.farmersbuddy.dto.response.AuthResponseDTO;
import com.revati.farmersbuddy.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }



}
