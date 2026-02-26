package com.revati.farmersbuddy.service.impl;

import com.revati.farmersbuddy.dto.request.LoginRequestDTO;
import com.revati.farmersbuddy.dto.request.RegisterRequestDTO;
import com.revati.farmersbuddy.dto.response.AuthResponseDTO;
import com.revati.farmersbuddy.entity.Farmer;
import com.revati.farmersbuddy.exception.ResourceNotFoundException;
import com.revati.farmersbuddy.exception.UnauthorizedException;
import com.revati.farmersbuddy.repository.FarmerRepository;
import com.revati.farmersbuddy.service.AuthService;
import com.revati.farmersbuddy.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final FarmerRepository farmerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(RegisterRequestDTO request) {

        if (farmerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Farmer farmer = Farmer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .village(request.getVillage())
                .role("ROLE_FARMER")
                .build();

        farmerRepository.save(farmer);

        return "Farmer registered successfully";
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {

        Farmer farmer = farmerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Farmer not found"));

        if (!passwordEncoder.matches(request.getPassword(), farmer.getPassword())) {
            throw new UnauthorizedException("Invalid password");
        }

        String token = jwtUtil.generateToken(farmer.getEmail());

        return new AuthResponseDTO(token);
    }


}
