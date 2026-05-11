package com.example.library.service;

import com.example.library.dto.KozhanovAbdualimAuthResponse;
import com.example.library.dto.KozhanovAbdualimLoginRequest;
import com.example.library.dto.KozhanovAbdualimRegisterRequest;
import com.example.library.entity.KozhanovAbdualimRole;
import com.example.library.entity.KozhanovAbdualimUser;
import com.example.library.repository.KozhanovAbdualimUserRepository;
import com.example.library.security.KozhanovAbdualimJwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KozhanovAbdualimAuthService {

    private final KozhanovAbdualimUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final KozhanovAbdualimJwtUtil jwtUtil;

    public KozhanovAbdualimAuthResponse register(KozhanovAbdualimRegisterRequest request) {
        log.info("Registering new user: {}", request.getUsername());

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already taken");
        }

        KozhanovAbdualimUser user = new KozhanovAbdualimUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : KozhanovAbdualimRole.READER);

        KozhanovAbdualimUser saved = userRepository.save(user);

        String token = jwtUtil.generateToken(saved);

        return new KozhanovAbdualimAuthResponse(token, saved.getUsername(), saved.getRole().name());
    }

    public KozhanovAbdualimAuthResponse login(KozhanovAbdualimLoginRequest request) {
        log.info("User logging in: {}", request.getUsername());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        KozhanovAbdualimUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        String token = jwtUtil.generateToken(user);

        return new KozhanovAbdualimAuthResponse(token, user.getUsername(), user.getRole().name());
    }
}