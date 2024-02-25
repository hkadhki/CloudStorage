package com.example.clouddiploma.service;

import com.example.clouddiploma.dto.LoginRequest;
import com.example.clouddiploma.dto.LoginResponse;
import com.example.clouddiploma.jwt.JwtGenerator;
import com.example.clouddiploma.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final AuthRepository authRepository;


    public LoginResponse login(LoginRequest loginRequest) {
        final var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.login(), loginRequest.password());
        final var authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var token = jwtGenerator.generateToken(authentication);
        authRepository.putTokenAndUsername(token,loginRequest.login());
        return new LoginResponse(token);
    }

    public void logout(String authToken) {
        authRepository.removeTokenAndUsernameByToken(authToken);
    }
}