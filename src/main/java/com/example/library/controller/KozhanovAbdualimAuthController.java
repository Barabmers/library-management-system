package com.example.library.controller;

import com.example.library.dto.KozhanovAbdualimAuthResponse;
import com.example.library.dto.KozhanovAbdualimLoginRequest;
import com.example.library.dto.KozhanovAbdualimRegisterRequest;
import com.example.library.service.KozhanovAbdualimAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class KozhanovAbdualimAuthController {

    private final KozhanovAbdualimAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<KozhanovAbdualimAuthResponse> register(@Valid @RequestBody KozhanovAbdualimRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<KozhanovAbdualimAuthResponse> login(@Valid @RequestBody KozhanovAbdualimLoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}