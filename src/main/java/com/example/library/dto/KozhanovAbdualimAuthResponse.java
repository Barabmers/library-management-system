package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KozhanovAbdualimAuthResponse {
    private String token;
    private String username;
    private String role;
}