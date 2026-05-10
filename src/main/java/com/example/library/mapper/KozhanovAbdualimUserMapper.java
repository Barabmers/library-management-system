package com.example.library.mapper;

import com.example.library.dto.KozhanovAbdualimUserDto;
import com.example.library.entity.KozhanovAbdualimUser;
import org.springframework.stereotype.Component;

@Component
public class KozhanovAbdualimUserMapper {

    public KozhanovAbdualimUserDto toDto(KozhanovAbdualimUser user) {
        if (user == null) return null;

        KozhanovAbdualimUserDto dto = new KozhanovAbdualimUserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public KozhanovAbdualimUser toEntity(KozhanovAbdualimUserDto dto) {
        if (dto == null) return null;

        KozhanovAbdualimUser user = new KozhanovAbdualimUser();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }
}