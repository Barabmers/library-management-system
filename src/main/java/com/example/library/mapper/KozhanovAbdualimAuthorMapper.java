package com.example.library.mapper;

import com.example.library.dto.KozhanovAbdualimAuthorDto;
import com.example.library.entity.KozhanovAbdualimAuthor;
import org.springframework.stereotype.Component;

@Component
public class KozhanovAbdualimAuthorMapper {

    public KozhanovAbdualimAuthorDto toDto(KozhanovAbdualimAuthor author) {
        if (author == null) return null;

        KozhanovAbdualimAuthorDto dto = new KozhanovAbdualimAuthorDto();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setBio(author.getBio());
        return dto;
    }

    public KozhanovAbdualimAuthor toEntity(KozhanovAbdualimAuthorDto dto) {
        if (dto == null) return null;

        KozhanovAbdualimAuthor author = new KozhanovAbdualimAuthor();
        author.setId(dto.getId());
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBio(dto.getBio());
        return author;
    }
}