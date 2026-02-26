package com.revati.farmersbuddy.service;


import com.revati.farmersbuddy.dto.request.LoginRequestDTO;
import com.revati.farmersbuddy.dto.request.RegisterRequestDTO;
import com.revati.farmersbuddy.dto.response.AuthResponseDTO;

public interface AuthService {
    String register(RegisterRequestDTO request);

    AuthResponseDTO login(LoginRequestDTO request);
}
