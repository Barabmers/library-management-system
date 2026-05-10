package com.example.library.dto;

import com.example.library.entity.KozhanovAbdualimRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KozhanovAbdualimUserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private KozhanovAbdualimRole role;
}